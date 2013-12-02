package com.square.mail.core.service.implementations.email;

import java.util.regex.Pattern;

import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;

import com.square.core.model.dto.RapportDto;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.MailDto;
import com.square.mail.core.service.interfaces.email.MailService;
import com.square.mail.core.util.MessageKeyUtil;

/**
 * Implémentatio du service Mail.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 7 sept. 2012
 *
 */
public class MailServiceImpl implements MailService {

	private MessageSourceUtil messageSourceUtil;

	@Override
	public String envoyerMail(MailDto mail) {
		return null;
	}

	@Override
	public String envoyerMailDepuisModele(EmailAvecModeleDto emailAvecModeleDto) {
		return null;
	}

	@Override
	public Boolean verifierEmail(MailDto email, RapportDto rapportErreur) {
	        boolean hasErreur = false;
	        if (email.getExpediteur() == null || "".equals(email.getExpediteur())) {
	        	rapportErreur.ajoutRapport("EmailDto.expediteur", messageSourceUtil.get(MessageKeyUtil.ERREUR_EXPEDITEUR_ABSENT), true);
	            hasErreur = true;
	        }
	        if (!checkAdresseMail(email.getExpediteur())) {
	        	rapportErreur.ajoutRapport("EmailDto.expediteur", messageSourceUtil.get(MessageKeyUtil.ERREUR_EXPEDITEUR_INCORRECT), true);
	            hasErreur = true;
	        }
	        if (email.getListeDestinataires() == null || email.getListeDestinataires().size() == 0) {
	        	rapportErreur.ajoutRapport("EmailDto.listeDestinataires", messageSourceUtil.get(MessageKeyUtil.ERREUR_DESTINATAIRE_ABSENT), true);
	            hasErreur = true;
	        }
	        for (String personneEmail : email.getListeDestinataires()) {
	            if (!checkAdresseMail(personneEmail)) {
	            	rapportErreur.ajoutRapport("EmailDto.listeDestinataires",  messageSourceUtil.get(MessageKeyUtil.ERREUR_DESTINATAIRE_INCORRECT), true);
	                hasErreur = true;
	                break;
	            }
	        }
	        if (email.getTitre() == null || "".equals(email.getTitre())) {
	        	rapportErreur.ajoutRapport("EmailDto.titre",  messageSourceUtil.get(MessageKeyUtil.ERREUR_OBJET_ABSENT), true);
	            hasErreur = true;
	        }
	        return hasErreur;
	}
	/**
     * Vérifie si le format d'une adresse mail est correct.
     * @param mail l'adresse mail à tester.
     * @return true si correct, false si non.
     */
    private boolean checkAdresseMail(final String mail) {
        return Pattern.matches("\\b[A-Za-z0-9\\._\\-~#]+@[A-Za-z0-9][A-Za-z0-9\\._\\-~#]*\\.[A-Za-z]{2,4}\\b", mail);
    }
}
