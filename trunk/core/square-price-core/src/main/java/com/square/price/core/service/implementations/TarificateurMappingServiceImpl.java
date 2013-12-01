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
package com.square.price.core.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.square.price.core.service.interfaces.TarificateurMappingService;
/**
 * Implémentation service TarificateurMappingService.
 * @author "Arnaud Brochain (arnaud.brochain@scub.net)" 7 sept. 2012
 *
 */
public class TarificateurMappingServiceImpl implements TarificateurMappingService {

	private static final Integer IDENTIFIANT_CRITERE_MOIS = 11;

	private static final Integer IDENTIFIANT_CRITERE_MONTANT = 12;

	private static final Integer IDENTIFIANT_CRITERE_MONTANT_DEUX = 13;

	private static final Integer IDENTIFIANT_CRITERE_PRIME_SOUSCRITE = 14;

	private static final Integer IDENTIFIANT_CRITERE_TAUX = 15;

	private static final Integer IDENTIFIANT_CRITERE_TAUX_COUVERTURE = 16;

	private static final Integer IDENTIFIANT_CRITERE_ZONE_TARIFAIRE = 17;

	private static final Integer IDENTIFIANT_DECES_INCAPACITE_CRITERE_CAPITAL_RISQUE = 18;

	@Override
	public String getConstanteCodeTarificationAdherent() {
		return "adherent";
	}

	@Override
	public Long getConstanteIdModeTarificationAdherent() {
		return 1l;
	}

	@Override
	public Long getConstanteIdModeTarificationBeneficiaire() {
		return 2l;
	}

	@Override
	public Integer getConstanteIdModeTarificationFamille() {
		return 3;
	}

	@Override
	public String getConstanteLienFamilleAssurePrincipal() {
		return "assure";
	}

	@Override
	public String getConstanteLienFamilleConjoint() {
		return "conjoint";
	}

	@Override
	public String getConstanteLienFamilleEnfant() {
		return "enfant";
	}

	@Override
	public String getConstanteTypeHTMLCalendar() {
		return "calendar";
	}

	@Override
	public String getConstanteTypeHTMLSelect() {
		return "select";
	}

	@Override
	public Integer getIdentifiantApplicationGestcom() {
		return 1;
	}

	@Override
	public Integer getIdentifiantCategorieSante() {
		return 1;
	}

	@Override
	public Integer getIdentifiantCritereAgeMax() {
		return 1;
	}

	@Override
	public Integer getIdentifiantCritereAgeMin() {
		return 2;
	}

	@Override
	public Integer getIdentifiantCritereCapital() {
		return 3;
	}

	@Override
	public Integer getIdentifiantCritereCapitalRisque() {
		return 4;
	}

	@Override
	public Integer getIdentifiantCritereCompositionFamille() {
		return 5;
	}

	@Override
	public Integer getIdentifiantCritereDateNaissance() {
		return 6;
	}

	@Override
	public Integer getIdentifiantCritereDureePret() {
		return 7;
	}

	@Override
	public Integer getIdentifiantCritereGarantieSouscrite() {
		return 8;
	}

	@Override
	public Integer getIdentifiantCritereGeneration() {
		return 9;
	}

	@Override
	public Integer getIdentifiantCritereLienFamille() {
		return 10;
	}

	@Override
	public Integer getIdentifiantCritereMois() {
		return IDENTIFIANT_CRITERE_MOIS;
	}

	@Override
	public Integer getIdentifiantCritereMontant() {
		return IDENTIFIANT_CRITERE_MONTANT;
	}

	@Override
	public Integer getIdentifiantCritereMontantDeux() {
		return IDENTIFIANT_CRITERE_MONTANT_DEUX;
	}

	@Override
	public Integer getIdentifiantCriterePrimeSouscrite() {
		return IDENTIFIANT_CRITERE_PRIME_SOUSCRITE;
	}

	@Override
	public Integer getIdentifiantCritereTaux() {
		return IDENTIFIANT_CRITERE_TAUX;
	}

	@Override
	public Integer getIdentifiantCritereTauxCouverture() {
		return IDENTIFIANT_CRITERE_TAUX_COUVERTURE;
	}

	@Override
	public Integer getIdentifiantCritereZoneTarifaire() {
		return IDENTIFIANT_CRITERE_ZONE_TARIFAIRE;
	}

	@Override
	public Integer getIdentifiantDecesIncapaciteCritereCapitalRisque() {
		return IDENTIFIANT_DECES_INCAPACITE_CRITERE_CAPITAL_RISQUE;
	}

	@Override
	public Integer getIdentifiantFamilleBonus1() {
		return 2;
	}

	@Override
	public Integer getIdentifiantFamilleBonus2() {
		return 3;
	}

	@Override
	public Integer getIdentifiantFamilleNo() {
		return 1;
	}

	@Override
	public Integer getIdentifiantGestionIndividuelle() {
		return 1;
	}

	@Override
	public Integer getIdentifiantModePaiementMensuel() {
		return 1;
	}

	@Override
	public Integer getIdentifiantModePaiementUnique() {
		return 2;
	}

	@Override
	public Integer getIdentifiantVetusteGammeOuvertVente() {
		return 1;
	}

	@Override
	public List<Integer> getListeProduitsAExclureRegles() {
		return new ArrayList<Integer>();
	}

	@Override
	public List<Integer> getListeProduitsBonus1() {
		return new ArrayList<Integer>();
	}

	@Override
	public List<Integer> getListeProduitsBonus2() {
		return new ArrayList<Integer>();
	}

	@Override
	public String getProprieteGammeProduitOrdreAffichage() {
		return "";
	}

	@Override
	public Integer getIdentifiantReseauFrance() {
		return 1;
	}

}
