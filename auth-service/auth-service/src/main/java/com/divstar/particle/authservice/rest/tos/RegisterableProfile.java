package com.divstar.particle.authservice.rest.tos;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.divstar.particle.authservice.rest.languageservice.converters.LanguageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * This class describes a profile, which potentially contains all information required to register a new profile.
 * In contrast to objects of the {@link Credentials}-class objects of this class are usually used for registration purposes.
 * <p>
 * Usually sent from client to this microservice during the registration process.
 * 
 * @author divstar
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@JsonInclude(JsonInclude.Include.ALWAYS)
public class RegisterableProfile extends Credentials {
	@Column(nullable = false, unique = true)
	private String email;

	@OneToOne
	@Convert(converter = LanguageConverter.class)
	private Language language;

	/**
	 * Default constructor.
	 */
	public RegisterableProfile() {
		// set language to default; will be overridden if valid languageCode is provided
		this.language = Language.getDefaultLanguage();
	}

	@Override
	public String toString() {
		return new StringBuilder(RegisterableProfile.class.getSimpleName())
																		   .append(" {")
																		   .append("name : '").append(getUsername()).append("',")
																		   .append("password : '").append(getPassword()).append("',")
																		   .append("email : '").append(email).append("',")
																		   .append("language : [").append(language.toString())
																		   .append("]}")
																		   .toString();
	}
}
