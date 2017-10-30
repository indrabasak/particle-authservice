package com.divstar.particle.authservice.rest.languageservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divstar.particle.authservice.exceptions.NoLanguageFoundException;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Language getLanguage(final String languageCode) {
		return Optional.ofNullable(languageRepository.findByLanguageCode(languageCode))
					   .orElse(getDefaultLanguage());
	}

	@Override
	public Language getDefaultLanguage() {
		return languageRepository.findAll().stream().findFirst().orElseThrow(NoLanguageFoundException::new);
	}

	@Override
	public List<Language> getLanguages() {
		return languageRepository.findAll();
	}

}
