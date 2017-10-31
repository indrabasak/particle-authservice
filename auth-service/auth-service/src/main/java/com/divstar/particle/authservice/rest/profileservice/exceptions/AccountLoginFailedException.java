package com.divstar.particle.authservice.rest.profileservice.exceptions;

import java.text.MessageFormat;

import com.divstar.particle.authservice.rest.tos.Credentials;

/**
 * This exception is thrown if login fails, because there either is no account for a given username
 * or the password for the account with the given username is invalid.
 * 
 * @author divstar
 *
 */
public class AccountLoginFailedException extends RuntimeException {

	private static final long serialVersionUID = -115611762718886109L;

	/**
	 * Constructor.
	 * 
	 * @param credentials
	 *            ({@link Credentials}) credentials containing the username and password, that have been rejected by the system
	 */
	public AccountLoginFailedException(final Credentials credentials) {
		super(MessageFormat.format(
				"Login into account failed (username = ''{0}'')! Either account with given username does not exist or the password was invalid!",
				credentials.getUsername()));
	}
}
