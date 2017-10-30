package com.divstar.particle.authservice.rest.languageservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.divstar.particle.authservice.rest.converters.LanguageDeserializer;
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
	@Id
	@Column(nullable = false, unique = true)
	private String	languageCode;
	@Column(nullable = false, unique = true)
	private String	name;
	@Column(nullable = false, unique = true)
	private String	i18nName;
	private boolean	localizeUi = false;
	private boolean	canChoose  = false;
}
