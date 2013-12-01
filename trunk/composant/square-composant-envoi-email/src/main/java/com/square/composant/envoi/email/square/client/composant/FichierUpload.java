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
package com.square.composant.envoi.email.square.client.composant;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.loading.LoadingPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.LoadingPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.square.composant.envoi.email.square.client.constantes.ComposantEnvoiEmailConstants;
import com.square.composant.envoi.email.square.client.content.i18n.ComposantEnvoiEmailMessages;
import com.square.composant.envoi.email.square.client.event.FinUploadPieceJointeEvent;

/**
 * Composant d'upload de fichier.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class FichierUpload extends FormPanel implements SubmitHandler, SubmitCompleteHandler {

    /** Composant d'upload de fichier. */
    private FileUpload fileUpload = new FileUpload();

    /** Bus d'évènements. */
    private HandlerManager eventBus;

    /** Constantes du composant d'upload de fichier. */
    private FichierUploadConstants constantes;

    /** Interface des messages de l'application.*/
    private ComposantEnvoiEmailMessages composantMessages = GWT.create(ComposantEnvoiEmailMessages.class);

    /**
     * Constructeur.
     * @param eventBus le bus d'évènements.
     */
    public FichierUpload(HandlerManager eventBus) {
        super();
        this.eventBus = eventBus;
        this.constantes = GWT.create(FichierUploadConstants.class);
        initForm();
        initComposants();
    }

    /** Initialisation du formulaire. */
    private void initForm() {
        // Action à appeler lors du submit
        this.setAction("uploadFichierEnvoiEmail");

        // Encodage
        this.setEncoding(FormPanel.ENCODING_MULTIPART);

        // Méthode de soumission
        this.setMethod(FormPanel.METHOD_POST);

        // Ajout du handler pour la soumission du formulaire
        this.addSubmitHandler(this);
        this.addSubmitCompleteHandler(this);
    }

    /** Initialisation des composants. */
    private void initComposants() {
        final HorizontalPanel table = new HorizontalPanel();
        fileUpload.setName("uploadFormElement");
        table.add(fileUpload);
        this.setWidget(table);
    }

    @Override
    public void onSubmit(SubmitEvent event) {
        final LoadingPopupConfiguration loadingPopupConf = new LoadingPopupConfiguration(constantes.chargementPieceJointeEnCours());
        LoadingPopup.afficher(loadingPopupConf);
        // Définition de l'action
        setAction(GWT.getModuleBaseURL() + ComposantEnvoiEmailConstants.URL_SERVLET_UPLOAD_FICHIER);
    }

    @Override
    public void onSubmitComplete(SubmitCompleteEvent event) {
        LoadingPopup.stopAll();
        String resultat = event.getResults().replaceAll("<[^>]*>", "");
        if (!"".equals(resultat)) {
            // S'il n'y a pas d'erreur on formate le contenu du résultat
            if (resultat.startsWith(ComposantEnvoiEmailConstants.PARAM_NOM_FICHIER)) {
                // Publication de l'évènement pour la mise a jour de l'affichage des pièces jointes
                eventBus.fireEvent(new FinUploadPieceJointeEvent(URL.decodeComponent(resultat)));
            }
            else if (resultat.equals(ComposantEnvoiEmailConstants.ERREUR_UPLOAD_FICHIER_NOM_INCORRECT)) {
            		resultat = composantMessages.erreurUploadFichierNomIncorrect();
            	} else if (resultat.equals(ComposantEnvoiEmailConstants.ERREUR_UPLOAD_FICHIER)) {
            		resultat = composantMessages.erreurUploadFichier();
            	} else {
	                final ErrorPopupConfiguration errorPopupConfiguration = new ErrorPopupConfiguration(resultat);
	                ErrorPopup.afficher(errorPopupConfiguration);
            	}
        }
    }

    /**
     * Teste si un fichier a été sélectionné.
     * @return true si un fichier a été sélectionné, false sinon
     */
    public boolean isFichierSelectionne() {
        return !fileUpload.getFilename().equals("");
    }

}
