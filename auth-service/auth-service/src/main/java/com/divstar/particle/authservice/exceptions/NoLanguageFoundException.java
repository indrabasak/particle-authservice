package com.divstar.particle.authservice.exceptions;

import com.divstar.particle.authservice.rest.languageservice.Language;

/**
 * This exception is thrown if not a single language is present in the LANGUAGES-table ({@link Language}-entity).
 * 
 * @author divstar
 *
 */
public class NoLanguageFoundException extends RuntimeException {

	private static final long serialVersionUID = 7285528842799348832L;

	/**
	 * Constructor.
	 */
	public NoLanguageFoundException() {
		super("No language found!");
	}
}
