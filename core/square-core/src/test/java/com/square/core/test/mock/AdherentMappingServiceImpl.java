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
package com.square.core.test.mock;

import java.util.List;

import com.square.adherent.noyau.service.interfaces.AdherentMappingService;

/**
 * Mock.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class AdherentMappingServiceImpl implements AdherentMappingService {

    @Override
    public Long getIdActeNonRemboursable() {
        return null;
    }

    @Override
    public Long getIdFamilleGarantieAssistance() {
        final Long id = 2554676L;
        return id;
    }

    @Override
    public Long getIdFamilleGarantieBonus() {
        return null;
    }

    @Override
    public Long getIdFamilleGarantieExoneration() {
        final Long id = 2554726L;
        return id;
    }

    @Override
    public Long getIdFamilleGarantieSante() {
        final Long id = 2554701L;
        return id;
    }

    @Override
    public Long getIdMoyenPaiementCheque() {
        return null;
    }

    @Override
    public Long getIdMoyenPaiementEspece() {
        return null;
    }

    @Override
    public Long getIdMoyenPaiementMandat() {
        return null;
    }

    @Override
    public Long getIdMoyenPaiementVirement() {
        return null;
    }

    @Override
    public Long getIdMoyenPaiementVirementEncaissement() {
        return null;
    }

    @Override
    public Long getIdNatureConnexionEspaceClient() {
        return null;
    }

    @Override
    public Long getIdNatureContratPrevoyance() {
        return null;
    }

    @Override
    public Long getIdNatureContratSante() {
        return null;
    }

    @Override
    public Long getIdOrigineDecompteAlmerys() {
        return null;
    }

    @Override
    public Long getIdOrigineDecompteIndu() {
        return null;
    }

    @Override
    public Long getIdRoleGarantieAssure() {
        return null;
    }

    @Override
    public Long getIdRoleGarantieConcubin() {
        final Long id = 2554631L;
        return id;
    }

    @Override
    public Long getIdRoleGarantieConjoint() {
        final Long id = 2554632L;
        return id;
    }

    @Override
    public Long getIdSegmentCollectif() {
        return null;
    }

    @Override
    public Long getIdSegmentCollectifIndividualise() {
        return null;
    }

    @Override
    public Long getIdStatutContratEnCours() {
        return null;
    }

    @Override
    public Long getIdStatutContratFutur() {
        return null;
    }

    @Override
    public Long getIdStatutContratResilie() {
        return null;
    }

    @Override
    public Long getIdStatutGarantieEnCours() {
        return null;
    }

    @Override
    public Long getIdStatutGarantieFutur() {
        return null;
    }

    @Override
    public Long getIdStatutGarantieResiliee() {
        return null;
    }

    @Override
    public Long getIdStatutGarantieSansEffet() {
        return null;
    }

    @Override
    public String getIdStatutPaiementDecompteNonPaye() {
        return null;
    }

    @Override
    public String getIdStatutPaiementDecomptePaye() {
        return null;
    }

    @Override
    public Long getIdTypeOptionEnvoiMutuellementEmail() {
        return 2L;
    }

    @Override
    public Long getIdTypeOptionEnvoiRelevesPrestationEmail() {
        return 1L;
    }

    @Override
    public Long getIdTypeOptionEnvoiSms() {
        return 5L;
    }

    @Override
    public Long getIdTypePayeurMultipart() {
        return null;
    }

    @Override
    public Long getIdTypePayeurSouscripteur() {
        return null;
    }

    @Override
    public List<Long> getListeOriginesDecomptesExcluesEnvoiSms() {
        return null;
    }

    @Override
    public Integer getNiveauImportanceStatutGarantie(Long idStatutGarantie) {
        return null;
    }

    @Override
    public int getNombreJoursDifferesEnvoiMail() {
        return 0;
    }

    @Override
    public String getOrderDecompteByActe() {
        return null;
    }

    @Override
    public String getOrderDecompteByBase() {
        return null;
    }

    @Override
    public String getOrderDecompteByBeneficiaire() {
        return null;
    }

    @Override
    public String getOrderDecompteByDateReglement() {
        return null;
    }

    @Override
    public String getOrderDecompteByDateSoin() {
        return null;
    }

    @Override
    public String getOrderDecompteByDepense() {
        return null;
    }

    @Override
    public String getOrderDecompteById() {
        return null;
    }

    @Override
    public String getOrderDecompteByNumero() {
        return null;
    }

    @Override
    public String getOrderDecompteByNumeroCouplage() {
        return null;
    }

    @Override
    public String getOrderDecompteByRaC() {
        return null;
    }

    @Override
    public String getOrderDecompteByRbtProf() {
        return null;
    }

    @Override
    public String getOrderDecompteByRbtRO() {
        return null;
    }

    @Override
    public String getOrderDecompteByRbtSmatis() {
        return null;
    }

    @Override
    public String getOrderDecompteByTaux() {
        return null;
    }

    @Override
    public Integer getOrdrePopulation(String libellePopulation) {
        return null;
    }

    @Override
    public String getOrderCotisationDateDebut() {
        return null;
    }

    @Override
    public String getOrderCotisationMontant() {
        return null;
    }

    @Override
    public String getOrderCotisationMontantRegle() {
        return null;
    }

    @Override
    public String getOrderCotisationSituation() {
        return null;
    }

    @Override
    public String getOrderDecompteByDateFinSoin() {
        return null;
    }

    @Override
    public String getOrderDecompteByNomDestinataireReglement() {
        return null;
    }

    @Override
    public String getOrderDecomptesByDateDebutSoin() {
        return null;
    }

    @Override
    public String getOrderDecomptesByDateFinSoin() {
        return null;
    }

    @Override
    public String getOrderDecomptesByNomBeneficiaireSoins() {
        return null;
    }

    @Override
    public String getOrderDecomptesByRbtSmatis() {
        return null;
    }

    @Override
    public Long getIdMotifResiliationErreurDeSaisie() {
        return null;
    }

    @Override
    public Long getIdMotifResiliationSansEffet() {
        return null;
    }

    @Override
    public Long getIdNatureContratAutre() {
        return null;
    }

    @Override
    public Long getIdTypePayeurAssure() {
        return null;
    }

    @Override
    public String getIdNatureReglementTiersSante() {
        return null;
    }

    @Override
    public Long getIdTypeRelationConjoint() {
        return null;
    }

    @Override
    public Long getIdTypeRelationEnfant() {
        return null;
    }

	@Override
	public Long getIdContratSante() {
		return null;
	}

	@Override
	public Long getIdFrequencePrelevementMensuelle() {
		return null;
	}

	@Override
	public Long getIdMoyenPaiementPrelevement() {
		return null;
	}

	@Override
	public int getJourPaiementPrelevementCinq() {
		return 0;
	}

}
