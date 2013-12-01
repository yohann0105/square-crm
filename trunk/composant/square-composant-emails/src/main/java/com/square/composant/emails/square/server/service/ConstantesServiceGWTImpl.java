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
package com.square.composant.emails.square.server.service;

import com.square.composant.emails.square.client.model.ConstantesModel;
import com.square.composant.emails.square.client.service.ConstantesServiceGWT;
import com.square.mail.core.service.interfaces.gestionmails.EmailMappingService;
import com.square.user.core.service.interfaces.UtilisateurMappingService;

/**
 * Implémentation de l'interface ConstantesServiceGWT.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class ConstantesServiceGWTImpl implements ConstantesServiceGWT {

    private EmailMappingService emailMappingService;

    private UtilisateurMappingService utilisateurMappingService;

    /** {@inheritDoc} */
    public ConstantesModel getConstantesApplication() {

        // Création de l'objet de retour
        final ConstantesModel constantesApplicationGWT = new ConstantesModel();

        constantesApplicationGWT.setIdEtatArchive(emailMappingService.getIdEtatArchive());
        constantesApplicationGWT.setIdEtatNouveau(emailMappingService.getIdEtatNouveau());
        constantesApplicationGWT.setIdEtatVerrouille(emailMappingService.getIdEtatVerrouille());

        constantesApplicationGWT.setConstanteProprieteNom(emailMappingService.getProprieteNom());
        constantesApplicationGWT.setConstanteProprietePrenom(emailMappingService.getProprietePrenom());
        constantesApplicationGWT.setConstanteProprieteNumeroAdherent(emailMappingService.getProprieteNumeroAdherent());
        constantesApplicationGWT.setConstanteProprieteSujet(emailMappingService.getProprieteSujet());
        constantesApplicationGWT.setConstanteProprieteDateEnvoi(emailMappingService.getProprieteDateEnvoi());
        constantesApplicationGWT.setConstanteProprieteUtilisateur(emailMappingService.getProprieteUtilisateur());
        constantesApplicationGWT.setConstanteProprieteService(emailMappingService.getProprieteService());
        constantesApplicationGWT.setConstanteProprieteEtat(emailMappingService.getProprieteEtat());
        constantesApplicationGWT.setConstanteProprieteNomUtilisateur(utilisateurMappingService.getProprieteUtilisateurNom());

        return constantesApplicationGWT;
    }

    /**
     * Set the emailMappingService value.
     * @param emailMappingService the emailMappingService to set
     */
    public void setEmailMappingService(EmailMappingService emailMappingService) {
        this.emailMappingService = emailMappingService;
    }

    /**
     * Set the utilisateurMappingService value.
     * @param utilisateurMappingService the utilisateurMappingService to set
     */
    public void setUtilisateurMappingService(UtilisateurMappingService utilisateurMappingService) {
        this.utilisateurMappingService = utilisateurMappingService;
    }

}