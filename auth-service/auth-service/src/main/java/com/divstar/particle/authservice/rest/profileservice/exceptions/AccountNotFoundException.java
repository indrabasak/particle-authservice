package com.divstar.particle.authservice.rest.profileservice.exceptions;

import java.text.MessageFormat;

import com.divstar.particle.authservice.rest.tos.PersistableProfile;

/**
 * This exception is thrown if an account is expected, yet not found.
 * <p>
 * All methods attempting to find an account by its ID are supposed to throw
 * this exception if no (or several) accounts have been found.
 * 
 * @author divstar
 *
 */
public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6952472260877409154L;

	/**
	 * Constructor.
	 * 
	 * @param accountId
	 *            (int) accountId, that had no {@link PersistableProfile}-object corresponding to it
	 */
	public AccountNotFoundException(final int accountId) {
		super(MessageFormat.format("No account was found for given accountId (ID = ''{0}'')!", accountId));
	}
}
