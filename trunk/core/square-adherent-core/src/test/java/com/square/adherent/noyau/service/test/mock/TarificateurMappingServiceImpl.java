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

import java.util.List;

import com.square.price.core.service.interfaces.TarificateurMappingService;

/**
 * Mock pour le service TarificateurMappingService.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class TarificateurMappingServiceImpl implements TarificateurMappingService {

    @Override
    public String getConstanteCodeTarificationAdherent() {
        return null;
    }

    @Override
    public String getConstanteLienFamilleAssurePrincipal() {
        return null;
    }

    @Override
    public String getConstanteLienFamilleConjoint() {
        return null;
    }

    @Override
    public String getConstanteLienFamilleEnfant() {
        return null;
    }

    @Override
    public String getConstanteTypeHTMLCalendar() {
        return null;
    }

    @Override
    public String getConstanteTypeHTMLSelect() {
        return null;
    }

    @Override
    public Integer getIdentifiantApplicationGestcom() {
        return null;
    }

    @Override
    public Integer getIdentifiantCategorieSante() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereAgeMax() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereAgeMin() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereCapital() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereCapitalRisque() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereCompositionFamille() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereDateNaissance() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereDureePret() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereGarantieSouscrite() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereGeneration() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereLienFamille() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereMois() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereMontant() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereMontantDeux() {
        return null;
    }

    @Override
    public Integer getIdentifiantCriterePrimeSouscrite() {
        final Integer idCritereMontantSouscrit = 12;
        return idCritereMontantSouscrit;
    }

    @Override
    public Integer getIdentifiantCritereTaux() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereTauxCouverture() {
        return null;
    }

    @Override
    public Integer getIdentifiantCritereZoneTarifaire() {
        return null;
    }

    @Override
    public Integer getIdentifiantDecesIncapaciteCritereCapitalRisque() {
        return null;
    }

    @Override
    public Integer getIdentifiantFamilleBonus1() {
        return null;
    }

    @Override
    public Integer getIdentifiantFamilleBonus2() {
        return null;
    }

    @Override
    public Integer getIdentifiantFamilleNo() {
        return null;
    }

    @Override
    public Integer getIdentifiantGestionIndividuelle() {
        return null;
    }

    @Override
    public Integer getIdentifiantModePaiementMensuel() {
        return null;
    }

    @Override
    public Integer getIdentifiantModePaiementUnique() {
        return null;
    }

    @Override
    public Integer getIdentifiantVetusteGammeOuvertVente() {
        return null;
    }

    @Override
    public List<Integer> getListeProduitsAExclureRegles() {
        return null;
    }

    @Override
    public List<Integer> getListeProduitsBonus1() {
        return null;
    }

    @Override
    public List<Integer> getListeProduitsBonus2() {
        return null;
    }

    @Override
    public String getProprieteGammeProduitOrdreAffichage() {
        return null;
    }

    @Override
    public Long getConstanteIdModeTarificationAdherent() {
        return null;
    }

    @Override
    public Long getConstanteIdModeTarificationBeneficiaire() {
        return null;
    }

    @Override
    public Integer getConstanteIdModeTarificationFamille() {
        return null;
    }

	@Override
	public Integer getIdentifiantReseauFrance() {
		return null;
	}

}
