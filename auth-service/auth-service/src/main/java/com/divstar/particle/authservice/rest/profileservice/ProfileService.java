package com.divstar.particle.authservice.rest.profileservice;

import java.util.List;
import java.util.Optional;

import com.divstar.particle.authservice.rest.tos.Credentials;
import com.divstar.particle.authservice.rest.tos.PersistableProfile;
import com.divstar.particle.authservice.rest.tos.RegisterableProfile;

/**
 * Defines a service used to perform account-specific actions.
 * 
 * @author divstar
 *
 */
public interface ProfileService {
	/**
	 * This method attempts to log in using given credentials.
	 * 
	 * @param credentials
	 *            ({@link Credentials}) credentials containing username and password
	 * @return ({@link Optional}&lt;{@link PersistableProfile}&gt;) either a {@link PersistableProfile}-object containing account
	 *         information or null if no account was found matching the given {@link Credentials}
	 */
	Optional<PersistableProfile> login(final Credentials credentials);

	/**
	 * This method attempts to retrieve all accounts ({@link PersistableProfile}-objects).
	 * 
	 * @return (List&lt;{@link PersistableProfile}&gt;) list of accounts ({@link PersistableProfile}-objects)
	 */
	List<PersistableProfile> getAccounts();

	/**
	 * This method attempts to retrieve a single account ({@link PersistableProfile}-object).
	 * 
	 * @param accountId
	 *            (int) account to retrieve
	 * @return ({@link PersistableProfile}) account in its serialized (JSON) format
	 */
	PersistableProfile getAccount(final int accountId);

	/**
	 * This method attempts to register an account using a {@link RegisterableProfile}, containing credentials.
	 * 
	 * @param registrationAccount
	 *            ({@link RegisterableProfile}) minimal profile containing credentials and other important data
	 * @return ({@link PersistableProfile}) persisted account awaiting confirmation and activation
	 */
	PersistableProfile registerAccount(final RegisterableProfile registrationAccount);

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
