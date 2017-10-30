package com.divstar.particle.authservice.rest.tos;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * This class describes a credentials object containing the username and password.
 * Objects of this class are usually used for login purposes.
 * <p>
 * Usually sent from client to this microservice during the login process.
 * 
 * @author divstar
 */
@Data
@MappedSuperclass
public class Credentials {
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Override
	public String toString() {
		return new StringBuilder(Credentials.class.getSimpleName())
																		  .append(" {")
																		  .append("name : '").append(username).append("',")
																		  .append("password : '").append(password).append("',")
																		  .append("'}")
																		  .toString();
	}
}