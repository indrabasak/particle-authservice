package com.divstar.particle.authservice.rest.accountservice.exceptions;

import com.divstar.particle.authservice.rest.accountservice.AccountService;

/**
 * This exception is thrown if a critical operation (e.g.
 * {@link AccountService#activateAccount(int)}) was to be executed on a banned
 * account.
 * 
 * @author divstar
 */
public class AccountIsBannedException extends RuntimeException {

	private static final long serialVersionUID = 1635392576955474097L;

}
