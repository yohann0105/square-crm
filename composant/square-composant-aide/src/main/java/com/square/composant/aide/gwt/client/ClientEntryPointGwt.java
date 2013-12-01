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
package com.square.composant.aide.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwt;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;

/**
 * Point d'entré de l'application.
 * @author Goumard Stéphane (stephane.goumard@scub.net) - SCUB
 */
public final class ClientEntryPointGwt implements EntryPoint {

    /** Service example. */
    private AideServiceGwtAsync aideService;

    /** Largeur de la fenêtre client.*/
    private static final int CLIENT_WIDTH = 600;

    /**
     * {@inheritDoc}
     */
    public void onModuleLoad() {
        // Create the instance of service
        this.aideService = GWT.create(AideServiceGwt.class);

        firefox3compatibility();

        final Long idChampSansAide = 1999L;
        final Long idChampAvecAide = 1788L;
        final Long idChampAvecAideUtilisateur = 1831L;


        final AideComposant aideComposantSansAideUtilisateur = new AideComposant(idChampSansAide, false);
        final AideComposant aideComposantAvecAideUtilisateur = new AideComposant(idChampAvecAideUtilisateur, false);
        final AideComposant aideComposantSansAideAdministrateur = new AideComposant(idChampSansAide, true);
        final AideComposant aideComposantAvecAideAdministrateur = new AideComposant(idChampAvecAide, true);

        final VerticalPanel conteneur = new VerticalPanel();
        conteneur.setWidth(String.valueOf(CLIENT_WIDTH));
        conteneur.setSpacing(10);
        conteneur.add(aideComposantSansAideUtilisateur);
        conteneur.add(aideComposantAvecAideUtilisateur);
        conteneur.add(aideComposantSansAideAdministrateur);
        conteneur.add(aideComposantAvecAideAdministrateur);

        aideComposantSansAideAdministrateur.setClientWidth(CLIENT_WIDTH);

        conteneur.setCellHorizontalAlignment(aideComposantSansAideUtilisateur, HasHorizontalAlignment.ALIGN_RIGHT);
        conteneur.setCellHorizontalAlignment(aideComposantAvecAideUtilisateur, HasHorizontalAlignment.ALIGN_RIGHT);
        conteneur.setCellHorizontalAlignment(aideComposantSansAideUtilisateur, HasHorizontalAlignment.ALIGN_RIGHT);
        conteneur.setCellHorizontalAlignment(aideComposantSansAideAdministrateur, HasHorizontalAlignment.ALIGN_RIGHT);
        conteneur.setCellHorizontalAlignment(aideComposantAvecAideAdministrateur, HasHorizontalAlignment.ALIGN_LEFT);

        final List<AideComposant> listComposants = new ArrayList<AideComposant>();
        listComposants.add(aideComposantSansAideUtilisateur);
        listComposants.add(aideComposantAvecAideUtilisateur);
        listComposants.add(aideComposantSansAideAdministrateur);
        listComposants.add(aideComposantAvecAideAdministrateur);
        for (AideComposant aideComposant : listComposants) {
            aideComposant.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
            aideComposant.addUpdateAideEventHandler(new UpdateAideEventHandler() {
                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());

                }
            });
        }

        RootPanel.get().add(conteneur);
    }

    private static native void firefox3compatibility()
    /*-{
        if (!$doc.getBoxObjectFor) { $doc.getBoxObjectFor = function (element) {
        var box = element.getBoundingClientRect();
        return { "x" : box.left, "y" : box.top, "width" : box.width, "height" : box.height, "screenX": box.left, "screenY":box.top }; } }
     }-*/;
}
