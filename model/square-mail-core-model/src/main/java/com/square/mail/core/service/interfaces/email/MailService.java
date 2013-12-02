package com.square.mail.core.service.interfaces.email;

import com.square.core.model.dto.RapportDto;
import com.square.mail.core.dto.emails.EmailAvecModeleDto;
import com.square.mail.core.dto.emails.MailDto;


/**
 * Interface des services liés aux mails.
 * @author nnadeau
 *
 */
public interface MailService {

    /**
     * Envoi le mail spécifié.
     * @param mail : le mail à envoyer.
     * @return le message id.
     */
    String envoyerMail(final MailDto mail);

    /**
     * Envoie un email généré à partir du modèle d'email et des informations passées en paramètre.
     * @param emailAvecModeleDto les informations et le modèle d'email.
     * @return le message id.
     */
    String envoyerMailDepuisModele(EmailAvecModeleDto emailAvecModeleDto);

    /**
     * Permet de vérifier les informations d'un mail.
     * @param email l'email
     * @param rapportErreur le rapport d'erreur
     * @return le boolean
     */
    Boolean verifierEmail(MailDto email, RapportDto rapportErreur);
}
