package com.divstar.particle.authservice.rest.accountservice;

import java.util.List;
import java.util.Optional;

import com.divstar.particle.authservice.rest.tos.Credentials;
import com.divstar.particle.authservice.rest.tos.PersistableAccount;
import com.divstar.particle.authservice.rest.tos.RegisterableAccount;

/**
 * Defines a service used to perform account-specific actions.
 * 
 * @author divstar
 *
 */
public interface AccountService {
	/**
	 * This method attempts to log in using given credentials.
	 * 
	 * @param credentials
	 *            ({@link Credentials}) credentials containing username and password
	 * @return ({@link Optional}&lt;{@link PersistableAccount}&gt;) either a {@link PersistableAccount}-object containing account
	 *         information or null if no account was found matching the given {@link Credentials}
	 */
	Optional<PersistableAccount> login(final Credentials credentials);

	/**
	 * This method attempts to retrieve all accounts ({@link PersistableAccount}-objects).
	 * 
	 * @return (List&lt;{@link PersistableAccount}&gt;) list of accounts ({@link PersistableAccount}-objects)
	 */
	List<PersistableAccount> getAccounts();

	/**
	 * This method attempts to retrieve a single account ({@link PersistableAccount}-object).
	 * 
	 * @param accountId
	 *            (int) account to retrieve
	 * @return ({@link PersistableAccount}) account in its serialized (JSON) format
	 */
	PersistableAccount getAccount(final int accountId);

	/**
	 * This method attempts to register an account using a {@link RegisterableAccount}, containing credentials.
	 * 
	 * @param registrationAccount
	 *            ({@link RegisterableAccount}) minimal account containing credentials and other important data
	 * @return ({@link PersistableAccount}) persisted account awaiting confirmation and activation
	 */
	PersistableAccount registerAccount(final RegisterableAccount registrationAccount);

	/**
	 * This method attempts to confirm a given account.
	 * 
	 * @param accountId
	 *            (int) account to confirm
	 * @return (boolean) true if the account was confirmed, false otherwise
	 */
	boolean confirmAccount(final int accountId);

	/**
	 * This method attempts to activate a given account.
	 * 
	 * @param accountId
	 *            (int) account to activate
	 * @return (boolean) true if the account was activated, false otherwise
	 */
	boolean activateAccount(final int accountId);

	/**
	 * This method attempts to ban a given account.
	 * 
	 * @param accountId
	 *            (int) account to ban
	 * @return (boolean) true if the account was banned, false otherwise
	 */
	boolean banAccount(final int accountId);
}
