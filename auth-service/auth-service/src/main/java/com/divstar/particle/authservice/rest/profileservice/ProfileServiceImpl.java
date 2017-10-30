package com.divstar.particle.authservice.rest.profileservice;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divstar.particle.authservice.exceptions.AccountConfirmErrorException;
import com.divstar.particle.authservice.exceptions.AccountIsBannedException;
import com.divstar.particle.authservice.exceptions.AccountNotFoundException;
import com.divstar.particle.authservice.exceptions.AccountRegistrationEmailExistsException;
import com.divstar.particle.authservice.exceptions.AccountRegistrationUsernameExistsException;
import com.divstar.particle.authservice.rest.enums.ProfileLevels;
import com.divstar.particle.authservice.rest.languageservice.LanguageService;
import com.divstar.particle.authservice.rest.tos.Credentials;
import com.divstar.particle.authservice.rest.tos.PersistableProfile;
import com.divstar.particle.authservice.rest.tos.RegisterableProfile;

@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private LanguageService languageService;

	@Override
	public Optional<PersistableProfile> login(final Credentials credentials) {
		return Optional.of(
				profileRepository.findByUsernameAndPassword(
						credentials.getUsername(), credentials.getPassword()));
	}

	@Override
	public List<PersistableProfile> getAccounts() {
		return profileRepository.findAll();
	}

	@Override
	public PersistableProfile getAccount(final int accountId) {
		final PersistableProfile account = profileRepository.findById(accountId);
		if (account == null) {
			throw new AccountNotFoundException(accountId);
		} else if (ProfileServiceImpl.LOGGER.isInfoEnabled()) {
			ProfileServiceImpl.LOGGER.info("Requested: getAccount({})->[{}]", accountId, account.toString());
		}
		return account;
	}

	@Override
	public PersistableProfile registerAccount(final RegisterableProfile registrationAccount) {
		final PersistableProfile account = new PersistableProfile(registrationAccount);
		if (account.getLanguage() == null) {
			account.setLanguage(languageService.getDefaultLanguage());
		}

		final boolean usernameExists = profileRepository.existsByUsername(account.getUsername());
		final boolean emailExists = profileRepository.existsByEmail(account.getEmail());

		if (usernameExists) {
			throw new AccountRegistrationUsernameExistsException(registrationAccount.getUsername());
		} else if (emailExists) {
			throw new AccountRegistrationEmailExistsException(registrationAccount.getEmail());
		} else {
			profileRepository.save(account);
		}

		return account;
	}

	@Override
	public boolean confirmAccount(final int accountId) {
		final PersistableProfile account = profileRepository.findById(accountId);
		if (account == null) {
			throw new AccountConfirmErrorException(accountId);
		} else {
			account.setConfirmed(true);
			profileRepository.save(account);
		}
		return account.isConfirmed();
	}

	@Override
	public boolean activateAccount(final int accountId) {
		final PersistableProfile account = profileRepository.findById(accountId);

		if (!ProfileLevels.BANNED.equals(account.getLevel())) {
			account.setActivate(true);
			profileRepository.save(account);
		} else {
			throw new AccountIsBannedException();
		}

		return true;
	}

	@Override
	public boolean banAccount(final int accountId) {
		final PersistableProfile account = profileRepository.findById(accountId);
		if (account == null) {
			throw new AccountNotFoundException(accountId);
		} else {
			account.setLevel(ProfileLevels.BANNED);
			account.setActivate(false);
			profileRepository.save(account);
		}

		return true;
	}

}
