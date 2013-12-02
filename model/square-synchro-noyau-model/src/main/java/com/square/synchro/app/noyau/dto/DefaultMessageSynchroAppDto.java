/*
 * This file is a part of Square, Customer Relationship Management Software for insurance's companies
 * Copyright (C) 2010-2012  SCUB <square@scub.net> - Mutuelle SMATIS FRANCE  <square@smatis.fr >
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.square.synchro.app.noyau.dto;

import java.io.Serializable;

/**
 * Represente un message pour le noyau de synchronisation d'application.
 * @author Goumard Stephane (stephane.goumard@scub.net). - SCUB
 */
public class DefaultMessageSynchroAppDto implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 6083399231196115374L;

    /** Variable static identification action plus objet. */
    public static final String MSOH_GESTIONMAILS_SEND_MAIL = "GESTIONMAILS_SEND_MAIL";

    /** Variable static identification action plus objet. */
    public static final String MSOH_SQUARE_SEND_MAIL = "SQUARE_SEND_MAIL";

    /** Variable static identification action plus objet. */
    public static final String MSOH_SQUARE_TRANSFO_AIA = "SQUARE_TRANSFO_AIA";

    /** Variable static identification action plus objet. */
    public static final String MSOH_HABILITATIONS_UPDATE_UTILISATEUR = "HABILITATIONS_UPDATE_UTILISATEUR";

    /** Variable static identification action plus objet. */
    public static final String MSOH_HABILITATIONS_ACTIVER_UTILISATEUR = "HABILITATIONS_ACTIVER_UTILISATEUR";

    /** Variable static identification action plus objet. */
    public static final String MSOH_HABILITATIONS_CREATEORUPDATE_AGENCE = "HABILITATIONS_CREATEORUPDATE_AGENCE";

    /** Variable static identification action plus objet. */
    public static final String MSOH_HABILITATIONS_SUPPRIMER_AGENCE = "HABILITATIONS_SUPPRIMER_AGENCE";

    /** Variable static identification action plus objet. */
    public static final String MSOH_SQUARE_GED_IMPMAIL_DEVIS = "MSOH_SQUARE_GED_IMPMAIL_DEVIS";

    /** Variable static identification action plus objet. */
    public static final String MSOH_SQUARE_GED_FUSION_DOCUMENT = "MSOH_SQUARE_GED_FUSION_DOCUMENT";

    /**
     * Permet de recuperer le nom d'application dans un entete de message.
     * @param msoh l'entete de message.
     * @return le nom de l'application.
     */
    public static String getAppFromMsgOrigineHeader(String msoh) {
        return msoh.split("_")[0];
    }

    /**
     * Permet de recuperer le nom d'application dans un entete de message.
     * @param msoh l'entete de message.
     * @return le nom de l'application.
     */
    public static String getTypeObjetFromMsgOrigineHeader(String msoh) {
        return msoh.split("_")[2];
    }

    /** Variable Origine. */
    private String msgSynchroOrigineHeader;

    /** Object du message. **/
    private String msgSynchroIdObjet;

    /** Login de l'utilisateur connecté pour la sécurité. */
    private String msgSynchroSecurityLogin;

    /** Indicateur d'action de suivi post traitement. */
    private String msgSynchroActionPost;

    /**
     * Identifiant de l'objet à synchroniser.
     * @return the msgSynchroIdObjet
     */
    public String getMsgSynchroIdObjet() {
        return msgSynchroIdObjet;
    }

    /**
     * Fixer l'identifaint de l'objet à synchroniser.
     * @param msgSynchroIdObjet the msgSynchroIdObjet to set
     */
    public void setMsgSynchroIdObjet(String msgSynchroIdObjet) {
        this.msgSynchroIdObjet = msgSynchroIdObjet;
    }

    /**
     * Retourne l'identifiant header pour ce message.
     * @return .
     */
    public String getMsgSynchroOrigineHeader() {
        return msgSynchroOrigineHeader;
    }

    /**
     * Fixer la valeur.
     * @param msgSynchroOrigineHeader the msgSynchroOrigineHeader to set
     */
    public void setMsgSynchroOrigineHeader(String msgSynchroOrigineHeader) {
        this.msgSynchroOrigineHeader = msgSynchroOrigineHeader;
    }

    /**
     * Récupère la valeur de msgSynchroSecurityLogin.
     * @return la valeur de msgSynchroSecurityLogin
     */
    public String getMsgSynchroSecurityLogin() {
        return msgSynchroSecurityLogin;
    }

    /**
     * Définit la valeur de msgSynchroSecurityLogin.
     * @param msgSynchroSecurityLogin la nouvelle valeur de msgSynchroSecurityLogin
     */
    public void setMsgSynchroSecurityLogin(String msgSynchroSecurityLogin) {
        this.msgSynchroSecurityLogin = msgSynchroSecurityLogin;
    }

	/**
	 * Renvoie la valeur de msgSynchroActionPost.
	 * @return msgSynchroActionPost
	 */
	public String getMsgSynchroActionPost() {
		return msgSynchroActionPost;
	}

	/**
	 * Modifie msgSynchroActionPost.
	 * @param msgSynchroActionPost la nouvelle valeur de msgSynchroActionPost
	 */
	public void setMsgSynchroActionPost(String msgSynchroActionPost) {
		this.msgSynchroActionPost = msgSynchroActionPost;
	}
}
