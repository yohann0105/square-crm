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
package com.square.adherent.noyau.service.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.adherent.noyau.dto.cotisation.CodeAiaLibelleDto;
import com.square.adherent.noyau.dto.cotisation.CotisationDto;
import com.square.adherent.noyau.dto.cotisation.CotisationsCriteresRechercheDto;
import com.square.adherent.noyau.dto.cotisation.DetailCotisationDto;
import com.square.adherent.noyau.dto.cotisation.DetailEncaissementDto;
import com.square.adherent.noyau.dto.cotisation.RetourCotisationDto;
import com.square.adherent.noyau.service.interfaces.CotisationService;

/**
 * Tests unitaires sur les cotisations.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class CotisationServiceTest extends DbunitBaseTestCase {

    private CotisationService cotisationService;

    /** Méthode appellée avant chaque test unitaire. */
    @Before
    public void setUp() {
        cotisationService = (CotisationService) getBeanSpring("cotisationService");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"adherentMappingContext.xml"};
    }

    @Override
    protected String getXmlDateSet() {
        return "dataset-contrat.xml";
    }

    /** Test du service rechercherModesPaiementParCriteres. */
    @Test
    public void testRechercherModesPaiementParCriteres() {
//        String libelle = "";
        //FIXME Données à insérer dans le MOCK
//        List<IdentifiantLibelleDto> modesPaiements = cotisationService.rechercherModesPaiementParCriteres(libelle);
//        assertEquals(Messages.getString("CotisationServiceTest.5"), 3, modesPaiements.size());
//        assertEquals(Messages.getString("CotisationServiceTest.6"), "Chèque", modesPaiements.get(0).getLibelle());
//        assertEquals(Messages.getString("CotisationServiceTest.8"), "Prélevement", modesPaiements.get(1).getLibelle());
//        assertEquals(Messages.getString("CotisationServiceTest.10"), "Virement", modesPaiements.get(2).getLibelle());
//
//        libelle = "Vire";
//        modesPaiements = cotisationService.rechercherModesPaiementParCriteres(libelle);
//        assertEquals(Messages.getString("CotisationServiceTest.13"), 1, modesPaiements.size());
//        assertEquals(Messages.getString("CotisationServiceTest.14"), "Virement", modesPaiements.get(0).getLibelle());
    }

    /** Test du service rechercherSituationsParCriteres. */
    @Test
    public void testRechercherSituationsParCriteres() {
          //FIXME Données à insérer dans le MOCK
//        String libelle = Messages.getString("CotisationServiceTest.16");
//        List<CodeAiaLibelleDto> situations = cotisationService.rechercherSituationsParCriteres(libelle);
//        assertEquals(Messages.getString("CotisationServiceTest.17"), 7, situations.size());
//        assertEquals(Messages.getString("CotisationServiceTest.18"), "Annulante", situations.get(0).getLibelle());
//        assertEquals(Messages.getString("CotisationServiceTest.20"), "Annulée", situations.get(1).getLibelle());
//
//        libelle = Messages.getString("CotisationServiceTest.22");
//        situations = cotisationService.rechercherSituationsParCriteres(libelle);
//        assertEquals(Messages.getString("CotisationServiceTest.23"), 1, situations.size());
//        assertEquals(Messages.getString("CotisationServiceTest.24"), "En instance", situations.get(0).getLibelle());
    }

    /** Test du service recupererCotisations. */
    @Test
    public void testRecupererCotisations() {
        final CotisationsCriteresRechercheDto criterias = new CotisationsCriteresRechercheDto();
        criterias.setUidPersonne(1L);
        //FIXME Données à insérer dans le MOCK
//        final RemotePagingCriteriasDto<CotisationsCriteresRechercheDto> criteres =
//            new RemotePagingCriteriasDto<CotisationsCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
//        final RetourCotisationDto retour = cotisationService.recupererCotisations(criteres);
//
//        assertEquals(Messages.getString("CotisationServiceTest.26"), 1, retour.getResultatsCotisation().getListResults().size());
//        final CotisationDto cotisation = retour.getResultatsCotisation().getListResults().get(0);
//        assertEquals(Messages.getString("CotisationServiceTest.27"), "Prélevement", cotisation.getModePaiement().getLibelle());
//        assertEquals(Messages.getString("CotisationServiceTest.29"), "Soldée", cotisation.getSituation().getLibelle());
//
//        assertEquals(Messages.getString("CotisationServiceTest.31"), 1, cotisation.getListeDetailsEncaissement().size());
//        final DetailEncaissementDto detailEncaissement = cotisation.getListeDetailsEncaissement().get(0);
//        assertEquals(Messages.getString("CotisationServiceTest.32"), "Prélevement", detailEncaissement.getMoyenPaiement().getLibelle());
//        assertNotNull(Messages.getString("CotisationServiceTest.34"), detailEncaissement.getStatut());
//        assertEquals(Messages.getString("CotisationServiceTest.35"), "Soldée", detailEncaissement.getStatut().getLibelle());
//
//        assertEquals(Messages.getString("CotisationServiceTest.37"), 1, cotisation.getListeDetailsEncaissement().size());
//        final DetailCotisationDto detailCotisation = cotisation.getListeDetailsCotisation().get(0);
//        assertNotNull(Messages.getString("CotisationServiceTest.38"), detailCotisation.getBeneficiaire());
//        assertEquals(Messages.getString("CotisationServiceTest.39"), "Anthony", detailCotisation.getBeneficiaire().getPrenom());
//        assertEquals(Messages.getString("CotisationServiceTest.41"), "SMATIS EXCELLENCE C", detailCotisation.getGarantie());
    }
}
