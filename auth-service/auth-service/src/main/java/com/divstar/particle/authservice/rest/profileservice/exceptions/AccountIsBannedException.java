package com.divstar.particle.authservice.rest.profileservice.exceptions;

import com.divstar.particle.authservice.rest.profileservice.ProfileService;

/**
 * This exception is thrown if a critical operation (e.g.
 * {@link ProfileService#activateAccount(int)}) was to be executed on a banned
 * account.
 * 
 * @author divstar
 */
public class AccountIsBannedException extends RuntimeException {

	private static final long serialVersionUID = 1635392576955474097L;

}
