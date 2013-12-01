package com.square.composant.envoi.email.square.client.content.i18n;

import com.google.gwt.i18n.client.Messages;
/**
 * Interface des Messages du composant.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 12 sept. 2012
 *
 */
public interface ComposantEnvoiEmailMessages extends Messages {

	/**
	 * Message.
	 * @return le message
	 */
	String erreurAbscencePieceJointe();

	/**
	 * Message.
	 * @return le message
	 */
	String erreurRecuperationPieceJointe();

    /**
     * Message indiquant l'envoi de l'email en cours.
     * @return le message.
     */
    String messageEnvoiMailEnCours();

    /**
     * Message indiquant que l'email a été envoyé.
     * @return le message.
     */
    String emailEnvoye();

	/**
	 * Message.
	 * @return le message
	 */
	String erreurUploadFichierNomIncorrect();

	/**
	 * Message.
	 * @return le message
	 */
	String erreurUploadFichier();
}
