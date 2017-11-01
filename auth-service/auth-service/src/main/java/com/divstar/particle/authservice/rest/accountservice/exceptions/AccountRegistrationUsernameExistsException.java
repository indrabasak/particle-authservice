package com.divstar.particle.authservice.rest.accountservice.exceptions;

/**
 * This exception is thrown if an account is expected to be created (during the registration process),
 * but the given <b>username already exists</b> in the database.
 * 
 * @author divstar
 *
 */
public class AccountRegistrationUsernameExistsException extends AccountRegistrationException {

	private static final long serialVersionUID = -3847104256154836546L;

	/**
	 * Constructor.
	 * 
	 * @param username
	 *            (String) name of the user, that had been rejected by the system
	 */
	public AccountRegistrationUsernameExistsException(final String username) {
		super("user", username);
	}

}
