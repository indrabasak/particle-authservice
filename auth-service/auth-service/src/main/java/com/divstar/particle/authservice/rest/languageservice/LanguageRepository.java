package com.divstar.particle.authservice.rest.languageservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents a repository used to <b>access the database</b>.
 * <p>
 * It contains default methods as well as custom ones and <u>does not have an implicit implementation</u>.<br>
 * Note, that its the method names that - by default - define the functionality they provide!
 * 
 * @author divstar
 *
 */
public interface LanguageRepository extends JpaRepository<Language, String> {
	/**
	 * This method checks whether a given languageCode has a corresponding {@link Language}-object inside the database.
	 * 
	 * @param languageCode
	 *            (String) language-code
	 * @return (boolean) true if there is a {@link Language}-object for this languageCode, false otherwise
	 */
	boolean existsByLanguageCode(final String languageCode);

	/**
	 * This method attempts to retrieve a {@link Language}-object corresponding to the given language-code.
	 * 
	 * @param languageCode
	 *            (String) language-code
	 * @return ({@link Language}) the language-object corresponding to the language-code given
	 */
	Language findByLanguageCode(final String languageCode);
}
