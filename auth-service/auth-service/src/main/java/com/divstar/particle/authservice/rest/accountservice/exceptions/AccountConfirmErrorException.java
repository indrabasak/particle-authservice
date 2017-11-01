package com.divstar.particle.authservice.rest.accountservice.exceptions;

import java.text.MessageFormat;

/**
 * This exception is thrown if confirmation of an account was not possible.
 * 
 * @author divstar
 */
public class AccountConfirmErrorException extends RuntimeException {

	private static final long serialVersionUID = 1635392576955474097L;

	/**
	 * Constructor.
	 * 
	 * @param accountId
	 *            (int) the accountId to confirm
	 */
	public AccountConfirmErrorException(final int accountId) {
		super(MessageFormat.format("Account with given accountId (ID = ''{0}'') could not be confirmed!", accountId));
	}
}
