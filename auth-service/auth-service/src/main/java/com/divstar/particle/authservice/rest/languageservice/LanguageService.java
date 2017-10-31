package com.divstar.particle.authservice.rest.languageservice;

import java.util.List;

import com.divstar.particle.authservice.rest.tos.Language;

/**
 * Defines a service used to perform language-specific actions.
 * <i>Note:</i> see http://www.loc.gov/standards/iso639-2/php/code_list.php for a complete list of language-codes.
 * 
 * @author divstar
 */
public interface LanguageService {
	/**
	 * This method attempts to retrieve a language for the given language-code.
	 * 
	 * @param languageCode
	 *            (String) language-code
	 * @return ({@link Language}) language with the given language-code or (if not available) the default language
	 */
	Language getLanguage(final String languageCode);

	/**
	 * This method attempts to retrieve all languages ({@link Language}-objects).
	 * 
	 * @return (List&lt;{@link Language}&gt;) list of languages ({@link Language}-objects)
	 */
	List<Language> getLanguages();
}
