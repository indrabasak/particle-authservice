package com.divstar.particle.authservice.exceptions;

/**
 * This exception is thrown if during registration an account is expected to be created,
 * yet the given <b>email address already exists</b> in the database.
 * 
 * @author divstar
 *
 */
public class AccountRegistrationEmailExistsException extends AccountRegistrationException {

	private static final long serialVersionUID = -1280365582790286762L;

	/**
	 * Constructor.
	 * 
	 * @param email
	 *            (String) e-mail, that seems to exist
	 */
	public AccountRegistrationEmailExistsException(final String email) {
		super("email", email);
	}
}
