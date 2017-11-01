package com.divstar.particle.authservice.rest.accountservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divstar.particle.authservice.rest.tos.PersistableAccount;

/**
 * This interface represents a repository used to <b>access the database</b>.
 * <p>
 * It contains default methods as well as custom ones and <u>does not have an implicit implementation</u>.<br>
 * Note, that its the method names that - by default - define the functionality they provide!
 * 
 * @author divstar
 *
 */
public interface AccountRepository extends JpaRepository<PersistableAccount, String> {
	/**
	 * This method checks whether an account with a given accountId exists or not.
	 * 
	 * @param accountId
	 *            (int) id of the account, whose existence we want to verify
	 * @return (boolean) true if an account with the given accountId exists, false otherwise
	 */
	boolean existsById(final int accountId);

	/**
	 * This method checks whether an account with a given username exists or not.
	 * 
	 * @param username
	 *            (String) username of the account, whose existence we want to verify
	 * @return (boolean) true if an account with the given username exists, false otherwise
	 */
	boolean existsByUsername(final String username);

	/**
	 * This method checks whether an account with a given email exists or not.
	 * 
	 * @param email
	 *            (String) email of the account, whose existence we want to verify
	 * @return (boolean) true if an account with the given email exists, false otherwise
	 */
	boolean existsByEmail(final String email);

	/**
	 * This method attempts to find an account with a given accountId and return the resulting account ({@link PersistableAccount}-object).
	 * 
	 * @param accountId
	 *            (int) id of the account we want to find
	 * @return ({@link PersistableAccount}) account ({@link PersistableAccount}-object) corresponding to the id or null if not found
	 */
	PersistableAccount findById(final int accountId);

	/**
	 * This method attempts to find an account with a given username and return the resulting account ({@link PersistableAccount}-object).
	 * 
	 * @param username
	 *            (String) username of the account we want to find
	 * @return ({@link PersistableAccount}) account ({@link PersistableAccount}-object) corresponding to the username or null if not found
	 */
	PersistableAccount findByUsername(final String username);

	/**
	 * This method attempts to find an account with a given username and password and return the resulting account
	 * ({@link PersistableAccount}-object).
	 * 
	 * @param username
	 *            (String) username of the account we want to find
	 * @param password
	 *            (String) password of the account we want to find (TODO: hashed and salted passwords should be compared here!)
	 * @return ({@link PersistableAccount}) account ({@link PersistableAccount}-object) corresponding to the username or null if not found
	 */
	PersistableAccount findByUsernameAndPassword(String username, String password);
}
