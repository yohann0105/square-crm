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
package com.square.adherent.noyau.service.test.mock;

import com.square.mail.core.service.interfaces.email.EnvoiEmailMappingService;

/**
 * Mock pour le service EnvoiEmailMappingService.
 * @author nnadeau - SCUB
 */
public class EnvoiEmailMappingServiceImpl implements EnvoiEmailMappingService {

    private static final Long QUINZE = 15L;

	private static final Long VINGT_QUATRE = 24L;

	/**
     * {@inheritDoc}
     */
    public Long getIdModeleChangementMotDePasse() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleConfirmationSouscriptionEnLigne() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeConfirmationAjoutPanier() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeContactAdherent() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeContactProspect() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeDevisAssuranceAuto() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeDevisAssuranceHabitation() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeDevisPrevoyanceAutre() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeDevisPrevoyanceTeleassistance() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeDevisSantePrevoyance() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeDossierPro() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandeParrainage() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleDemandePriseEnCharge() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleEnvoiMagazineMutuellement() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleEnvoiRelance1() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleEnvoiRelance2() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleEnvoiRelevePrestations() {
        return QUINZE;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleMotDePasseOublie() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Long getIdModeleEnvoiDocumentsPieceJointe() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdModeleConfirmationCreationEspaceClient() {
        return VINGT_QUATRE;
    }

}
