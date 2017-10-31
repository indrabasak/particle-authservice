package com.divstar.particle.authservice.rest.tos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.divstar.particle.authservice.rest.languageservice.converters.LanguageDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describes the entity for a language.
 * <p>
 * <i>Note:</i> there is no auto-generated ID-column; instead the languageCode (e.g. "en" or "de") is the id.
 * 
 * @author divstar
 *
 */
@JsonDeserialize(using = LanguageDeserializer.class)
@Data
@NoArgsConstructor
@Entity(name = "Languages")
public class Language {
	private static Language defaultLanguage;

	@Id
	@Column(nullable = false, unique = true)
	private String	languageCode;
	@Column(nullable = false, unique = true)
	private String	name;
	@Column(nullable = false, unique = true)
	private String	i18nName;
	private boolean	localizeUi = false;
	private boolean	canChoose  = false;

	@Override
	public String toString() {
		return new StringBuilder(Language.class.getSimpleName())
																.append(" {")
																.append("languageCode : '").append(languageCode).append("',")
																.append("name : '").append(name).append("',")
																.append("i18nName : '").append(i18nName).append("',")
																.append("localizeUi : '").append(localizeUi).append("',")
																.append("canChoose : '").append(canChoose)
																.append("'}")
																.toString();
	}

	/**
	 * This method sets the class attribute {@link Language#defaultLanguage}.
	 * 
	 * @param defaultLanguage
	 *            ({@link Language}) default language
	 */
	public static void setDefaultLanguage(final Language defaultLanguage) {
		Language.defaultLanguage = defaultLanguage;
	}

	/**
	 * @return ({@link Language}) default language
	 */
	public static Language getDefaultLanguage() {
		return Language.defaultLanguage;
	}
}
