package com.divstar.particle.authservice.rest.tos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.divstar.particle.authservice.rest.profileservice.enums.ProfileLevels;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class describes a complete persisted profile object.
 * In contrast to objects of the {@link RegisterableProfile}-class objects of this class resemble a persisted user profile in the database.
 * <p>
 * Usually issued from this microservice to the client after successful user authentication.
 * 
 * @author divstar
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Profiles")
public class PersistableProfile extends RegisterableProfile {

	@Id
	@GeneratedValue
	private int			  id;
	@Column(nullable = false)
	private boolean		  confirmed	= false;
	@Column(nullable = false)
	private boolean		  activate	= false;
	private String		  salt;
	private String		  md5;
	private String		  sha1;
	private String		  sha256;
	@Column(nullable = false)
	private ProfileLevels level		= ProfileLevels.PENDING;
	private LocalDateTime joined;
	private LocalDateTime lastVisited;

	public PersistableProfile(final RegisterableProfile registrationAccount) {
		// Take username and password and create a persistable profile
		this.setUsername(registrationAccount.getUsername());
		this.setPassword(registrationAccount.getPassword());
		// Set Registerable-attributes
		this.setEmail(registrationAccount.getEmail());
		this.setLanguage(registrationAccount.getLanguage());
	}

	@Override
	public String toString() {
		return new StringBuilder(PersistableProfile.class.getSimpleName())
																		  .append(" {")
																		  .append("id : '").append(getId()).append("',")
																		  .append("name : '").append(getUsername()).append("',")
																		  .append("password : '").append(getPassword()).append("',")
																		  .append("salt : '").append(salt).append("',")
																		  .append("md5 : '").append(md5).append("',")
																		  .append("sha1 : '").append(sha1).append("',")
																		  .append("sha256 : '").append(sha256).append("',")
																		  .append("confirmed : '").append(confirmed).append("',")
																		  .append("active : '").append(activate).append("',")
																		  .append("'}")
																		  .toString();
	}
}