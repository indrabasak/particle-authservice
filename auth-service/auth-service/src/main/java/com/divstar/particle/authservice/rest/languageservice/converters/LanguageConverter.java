package com.divstar.particle.authservice.rest.languageservice.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.divstar.particle.authservice.rest.languageservice.LanguageService;
import com.divstar.particle.authservice.rest.tos.Language;

/**
 * This class is a custom {@link AttributeConverter}-converter for {@link Language}-objects.
 * <p>
 * It explains in a JPA manner how to convert the entity to a column in a database and vice versa.
 * 
 * @author divstar
 *
 */
@Converter
public class LanguageConverter implements AttributeConverter<Language, String> {
	@Autowired
	private LanguageService languageService;

	@Override
	public String convertToDatabaseColumn(final Language attribute) {
		return attribute.getLanguageCode();
	}

	@Override
	public Language convertToEntityAttribute(final String dbData) {
		return languageService.getLanguage(dbData);
	}

}
