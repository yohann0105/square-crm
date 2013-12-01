package com.square.client.gwt.server.test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe gérant les messages du test du String converter.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 16 sept. 2012
 *
 */
public final class Messages {
	private static final String BUNDLE_NAME = "com.square.client.gwt.server.test.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}
	/**
	 * Récupére le message correspondant à la clé.
	 * @param key la clé
	 * @return le message
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
