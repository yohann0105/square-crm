package com.square.adherent.noyau.plugin;

import com.square.adherent.noyau.plugin.dto.sms.CreationSmsDto;
import com.square.adherent.noyau.plugin.dto.sms.SmsDto;

/**
 * Plugin pour l'envoi de SMS.
 * @author Juanito Goncalves (juanito.goncalves@scub.net)
 */
public interface SmsPlugin {

    /**
     * Permet d'envoyer un sms.
     * @param id l'identifiant du sms
     * @return le sms
     */
    SmsDto envoyerSms(Long id);

    /**
     * Permet de créer un sms.
     * @param creationSms les infos pour la création du sms
     * @return le sms
     */
    SmsDto creerSms(CreationSmsDto creationSms);
}
