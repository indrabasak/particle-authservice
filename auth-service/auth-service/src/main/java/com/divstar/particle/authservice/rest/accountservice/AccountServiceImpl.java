package com.divstar.particle.authservice.rest.accountservice;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divstar.particle.authservice.rest.accountservice.enums.AccountLevels;
import com.divstar.particle.authservice.rest.accountservice.exceptions.AccountConfirmErrorException;
import com.divstar.particle.authservice.rest.accountservice.exceptions.AccountIsBannedException;
import com.divstar.particle.authservice.rest.accountservice.exceptions.AccountNotFoundException;
import com.divstar.particle.authservice.rest.accountservice.exceptions.AccountRegistrationEmailExistsException;
import com.divstar.particle.authservice.rest.accountservice.exceptions.AccountRegistrationUsernameExistsException;
import com.divstar.particle.authservice.rest.tos.Credentials;
import com.divstar.particle.authservice.rest.tos.PersistableAccount;
import com.divstar.particle.authservice.rest.tos.RegisterableAccount;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Optional<PersistableAccount> login(final Credentials credentials) {
		return Optional.of(
				accountRepository.findByUsernameAndPassword(
						credentials.getUsername(), credentials.getPassword()));
	}

	@Override
	public List<PersistableAccount> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public PersistableAccount getAccount(final int accountId) {
		final PersistableAccount account = accountRepository.findById(accountId);
		if (account == null) {
			throw new AccountNotFoundException(accountId);
		} else if (AccountServiceImpl.LOGGER.isInfoEnabled()) {
			AccountServiceImpl.LOGGER.info("Requested: getAccount({})->[{}]", accountId, account.toString());
		}
		return account;
	}

	@Override
	public PersistableAccount registerAccount(final RegisterableAccount registrationAccount) {
		final PersistableAccount account = new PersistableAccount(registrationAccount);

		final boolean usernameExists = accountRepository.existsByUsername(account.getUsername());
		final boolean emailExists = accountRepository.existsByEmail(account.getEmail());

		if (usernameExists) {
			throw new AccountRegistrationUsernameExistsException(registrationAccount.getUsername());
		} else if (emailExists) {
			throw new AccountRegistrationEmailExistsException(registrationAccount.getEmail());
		} else {
			accountRepository.save(account);
		}

		return account;
	}

	@Override
	public boolean confirmAccount(final int accountId) {
		final PersistableAccount account = accountRepository.findById(accountId);
		if (account == null) {
			throw new AccountConfirmErrorException(accountId);
		} else {
			account.setConfirmed(true);
			accountRepository.save(account);
		}
		return account.isConfirmed();
	}

	@Override
	public boolean activateAccount(final int accountId) {
		final PersistableAccount account = accountRepository.findById(accountId);

		if (!AccountLevels.BANNED.equals(account.getLevel())) {
			account.setActivate(true);
			accountRepository.save(account);
		} else {
			throw new AccountIsBannedException();
		}

		return true;
	}

	@Override
	public boolean banAccount(final int accountId) {
		final PersistableAccount account = accountRepository.findById(accountId);
		if (account == null) {
			throw new AccountNotFoundException(accountId);
		} else {
			account.setLevel(AccountLevels.BANNED);
			account.setActivate(false);
			accountRepository.save(account);
		}

		return true;
	}

}
