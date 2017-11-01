package com.divstar.particle.authservice.rest.accountservice.exceptions;

import java.text.MessageFormat;

/**
 * This exception is thrown if an error during account registration has occurred.
 * <p>
 * <i>Note:</i> this class is abstract, because concrete exceptions are expected to be created.
 * 
 * @author divstar
 *
 */
public abstract class AccountRegistrationException extends RuntimeException {

	private static final long serialVersionUID = 3415625752664754048L;

	/**
	 * 
	 * @param field
	 * @param value
	 */
	public AccountRegistrationException(final String field, final String value) {
		super(MessageFormat.format("Account could not be registered ({0} ''{1}'' already exists)!", field, value));
	}
}
