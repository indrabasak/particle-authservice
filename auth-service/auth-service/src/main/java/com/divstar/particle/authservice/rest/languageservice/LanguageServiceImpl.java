package com.divstar.particle.authservice.rest.languageservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.divstar.particle.authservice.rest.languageservice.exceptions.NoLanguageFoundException;
import com.divstar.particle.authservice.rest.tos.Language;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Language getLanguage(final String languageCode) {
		return Optional.ofNullable(languageRepository.findByLanguageCode(languageCode))
					   .orElse(Language.getDefaultLanguage());
	}

	@Override
	public List<Language> getLanguages() {
		return languageRepository.findAll();
	}

	/**
	 * This method sets the default language in {@link Language}.
	 * <p>
	 * In particular it uses {@link Language#setDefaultLanguage(Language) to set the default language retrieved using
	 * {@link LanguageService#getDefaultLanguage()}.
	 * <p>
	 * <i>Note:</i> this method is invoked once the application finished starting up. The default language (first in list) is not supposed
	 * to change during service-uptime.
	 */
	@EventListener(ApplicationReadyEvent.class)
	private void setDefaultLanguageOnStartup() {
		Language.setDefaultLanguage(
				languageRepository.findAll().stream().findFirst().orElseThrow(NoLanguageFoundException::new));
	}
}
