package com.square.fusion.api.service.test;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe utilitaire utilisé pour internationnalisé les chaines des test unitaires.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 7 sept. 2012
 *
 */
public final class Messages {
	private static final String BUNDLE_NAME = "com.square.fusion.api.service.test.messages";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}
	/**
	 * Récupérere la chaine correspondant à la key.
	 * @param key la clé
	 * @return la chaine
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
