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
package com.square.fusion.api.service.test.mock;

import java.util.Calendar;
import java.util.List;

import com.square.core.model.dto.ActionNotificationInfosDto;
import com.square.core.model.dto.ConstantesDto;
import com.square.core.model.dto.NumeroRoDto;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Mock du service de mapping Square.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class SquareMappingServiceImpl implements SquareMappingService {

    @Override
    public NumeroRoDto convertirNroVersNss(String nro) {
        return null;
    }

    @Override
    public String convertirNssVersNro(String nss, String cless) {
        return null;
    }

    @Override
    public ActionNotificationInfosDto getActionNotificationParId(Long id) {
        return null;
    }

    @Override
    public Long getIdActionPrioriteParDefaut() {
        return null;
    }

    @Override
    public Long getIdCSPPersonneParDefaut() {
        return null;
    }

    @Override
    public Long getIdCiviliteMadame() {
        return null;
    }

    @Override
    public Long getIdCiviliteMademoiselle() {
        return null;
    }

    @Override
    public Long getIdCiviliteMonsieur() {
        return null;
    }

    @Override
    public Long getIdGroupementFamille() {
        return null;
    }

    @Override
    public Long getIdGroupementTiers() {
        return null;
    }

    @Override
    public Long getIdNatureActionInternet() {
        return null;
    }

    @Override
    public Long getIdNatureAdressePrincipale() {
        return 1L;
    }

    @Override
    public Long getIdNatureAdresseSecondaire() {
        return 2L;
    }

    @Override
    public Long getIdNatureEmailPersonnel() {
        return null;
    }

    @Override
    public Long getIdNatureMobilePrive() {
        return null;
    }

    @Override
    public Long getIdNatureMobileTravail() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneBeneficiaire() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneParDefaut() {
        return null;
    }

    @Override
    public Long getIdNatureTelephoneFixe() {
        return null;
    }

    @Override
    public Long getIdNotification(Calendar dateAction, Calendar dateNotification) {
        return null;
    }

    @Override
    public Long getIdObjetActionTransformationAia() {
        return null;
    }

    @Override
    public Long getIdPaysFrance() {
        return null;
    }

    @Override
    public Long getIdPaysParDefaut() {
        return null;
    }

    @Override
    public Long getIdProfessionPersonneParDefaut() {
        return null;
    }

    @Override
    public Long getIdReseauVentePersonneParDefaut() {
        return null;
    }

    @Override
    public Long getIdRessource() {
        return null;
    }

    @Override
    public Long getIdResultatOpportunite() {
        return null;
    }

    @Override
    public Long getIdResultatRelance() {
        return null;
    }

    @Override
    public Long getIdSegmentPersonneParDefaut() {
        return null;
    }

    @Override
    public Long getIdSituationFamilialePersonneParDefaut() {
        return null;
    }

    @Override
    public Long getIdStatutActionParDefaut() {
        return null;
    }

    @Override
    public Long getIdStatutAnnuler() {
        return null;
    }

    @Override
    public Long getIdStatutCampagneActive() {
        return null;
    }

    @Override
    public Long getIdStatutOpportuniteAccepte() {
        return null;
    }

    @Override
    public Long getIdStatutOpportuniteCorbeille() {
        return null;
    }

    @Override
    public Long getIdStatutOpportuniteNonRenseigne() {
        return null;
    }

    @Override
    public Long getIdStatutOpportuniteRefuse() {
        return null;
    }

    @Override
    public Long getIdStatutOpportuniteTransforme() {
        return null;
    }

    @Override
    public Long getIdStatutTerminer() {
        return null;
    }

    @Override
    public Long getIdTypeActionOpportunite() {
        return null;
    }

    @Override
    public Long getIdTypeActionRelance() {
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
    public String getLibelleTypeActionOpportunite() {
        return null;
    }

    @Override
    public List<ActionNotificationInfosDto> getListeActionNotifications() {
        return null;
    }

    @Override
    public List<Long> getListeIdsNaturesTelephoneMobile() {
        return null;
    }

    @Override
    public String getProprietePersonneAgence() {
        return null;
    }

    @Override
    public String getProprietePersonneCodePostal() {
        return null;
    }

    @Override
    public String getProprietePersonneCommercial() {
        return null;
    }

    @Override
    public String getProprietePersonneCommune() {
        return null;
    }

    @Override
    public String getProprietePersonneDateNaissance() {
        return null;
    }

    @Override
    public String getProprietePersonneNature() {
        return null;
    }

    @Override
    public String getProprietePersonneNom() {
        return null;
    }

    @Override
    public String getProprietePersonneNumeroClient() {
        return null;
    }

    @Override
    public String getProprietePersonnePrenom() {
        return null;
    }

    @Override
    public String getProprietePersonneSegment() {
        return null;
    }

    @Override
    public Long getIdPaysFranceMetropolitaine() {
        return null;
    }

    @Override
    public Long getIdGroupementProfessionnel() {
        return null;
    }

    @Override
    public Long getIdGroupementRelationTypeEchecParrainageAdherent() {
        return null;
    }

    @Override
    public Long getIdGroupementRelationTypeEchecParrainageGarantie() {
        return null;
    }

    @Override
    public Long getIdGroupementRelationTypeEnCoursParrainage() {
        return null;
    }

    @Override
    public Long getIdNatureActionTelephoneSortant() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneAdherent() {
        final Long id = 3L;
        return id;
    }

    @Override
    public Long getIdNaturePersonneBeneficiaireProspect() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneProspect() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneVivier() {
        return null;
    }

    @Override
    public Long getIdObjetActionRelance() {
        return null;
    }

    @Override
    public Long getIdReseauVenteCourtage() {
        return null;
    }

    @Override
    public Long getIdSegmentIndividuel() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionRelanceParrainage() {
        return null;
    }

    @Override
    public Long getIdStatutAFaire() {
        return null;
    }

    @Override
    public Long getIdStatutActionTermine() {
        return null;
    }

    @Override
    public Long getIdTypeActionCommercial() {
        return null;
    }

    @Override
    public Long getIdTypeRelationFilleul() {
        return null;
    }

    @Override
    public Long getIdTypeRelationFilleulDejaAdherent() {
        return null;
    }

    @Override
    public Long getIdTypeRelationFilleulEnCoursCreation() {
        return null;
    }

    @Override
    public Long getIdTypeRelationFilleulGarantieIncompatible() {
        return null;
    }

    @Override
    public String getInfosTeletransmissionCaisseNon() {
        return null;
    }

    @Override
    public String getInfosTeletransmissionCaisseOui() {
        return null;
    }

    @Override
    public String getLibelleNonRenseigne() {
        return null;
    }

    @Override
    public String getOrderByActionAttributionAgence() {
        return null;
    }

    @Override
    public String getOrderByActionAttributionRessource() {
        return null;
    }

    @Override
    public String getOrderByActionDateAction() {
        return null;
    }

    @Override
    public String getOrderByActionDateCreation() {
        return null;
    }

    @Override
    public String getOrderByActionId() {
        return null;
    }

    @Override
    public String getOrderByActionObjet() {
        return null;
    }

    @Override
    public String getOrderByActionPriorite() {
        return null;
    }

    @Override
    public String getOrderByActionSsObjet() {
        return null;
    }

    @Override
    public String getOrderByActionStatut() {
        return null;
    }

    @Override
    public String getOrderByActionType() {
        return null;
    }

    @Override
    public String getOrderByRelationDateDebut() {
        return null;
    }

    @Override
    public String getOrderByRelationTypeOrdre() {
        return null;
    }

    @Override
    public String getProprietePrestationActe() {
        return null;
    }

    @Override
    public String getProprietePrestationDateSoins() {
        return null;
    }

    @Override
    public String getProprietePrestationDepensesEngagees() {
        return null;
    }

    @Override
    public String getProprietePrestationNomDestinataire() {
        return null;
    }

    @Override
    public String getProprietePrestationRefDecompte() {
        return null;
    }

    @Override
    public String getProprietePrestationRemboursementRO() {
        return null;
    }

    @Override
    public String getProprietePrestationRemboursementSmatis() {
        return null;
    }

    @Override
    public String getProprietePrestationResteACharge() {
        return null;
    }

    @Override
    public String getRoleSquareBatch() {
        return null;
    }

    @Override
    public String getRoleSquareCampagne() {
        return null;
    }

    @Override
    public String getStatutPaiementPaye() {
        return null;
    }

    @Override
    public Long getIdObjetActionEditique() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionEditionBa() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionEnvoiBaParMail() {
        return null;
    }

    @Override
    public Long getIdObjetActionClient() {
        return null;
    }

    @Override
    public int getDecalageDateActionRelance() {
        return 0;
    }

    @Override
    public int getDecalageDateAffichageAction() {
        return 0;
    }

    @Override
    public Long getIdActionPrioriteHaute() {
        return null;
    }

    @Override
    public Long getIdActionPrioriteUrgente() {
        return null;
    }

    @Override
    public Long getIdEtatActifRessource() {
        return null;
    }

    @Override
    public Long getIdNatureActionCourrierEntrant() {
        return null;
    }

    @Override
    public Long getIdNatureActionCourrierSortant() {
        return null;
    }

    @Override
    public Long getIdNatureActionEmailEntrant() {
        return null;
    }

    @Override
    public Long getIdNatureActionEmailSortant() {
        return null;
    }

    @Override
    public Long getIdNatureActionInterne() {
        return null;
    }

    @Override
    public Long getIdNatureFaxPrive() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneDecede() {
        return null;
    }

    @Override
    public Long getIdNatureTelephoneErrone() {
        return null;
    }

    @Override
    public Long getIdNatureTelephoneFixeTravail() {
        return null;
    }

    @Override
    public Long getIdObjetActionCourrier() {
        return null;
    }

    @Override
    public Long getIdObjetActionNouvelleAdhesion() {
        return null;
    }

    @Override
    public Long getIdObjetModificationAdressePostale() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionAdhesionEnLigne() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionAvecChangementDepartement() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionSansChangementDepartement() {
        return null;
    }

    @Override
    public Long getIdStatutOpportuniteEnAttente() {
        return null;
    }

    @Override
    public Long getIdTypeActionDocument() {
        return null;
    }

    @Override
    public Long getIdTypeActionModification() {
        return null;
    }

    @Override
    public List<Long> getListeIdsStatutsLoiMadelin() {
        return null;
    }

    @Override
    public String getProprietePersonneDateCreation() {
        return null;
    }

    @Override
    public Long getIdEtatInactifRessource() {
        return null;
    }

    @Override
    public Long getIdNatureActionAchatFichiers() {
        return null;
    }

    @Override
    public Long getIdentifiantAgenceFrance() {
        return null;
    }

    @Override
    public Long getIdentifiantRoleGestionnaireOpportunites() {
        return null;
    }

    @Override
    public String getRoleSquareAnimateur() {
        return null;
    }

    @Override
    public Long getIdFonctionCCD() {
        return null;
    }

    @Override
    public Long getIdFonctionCMP() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdStatutActionAFaire() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdStatutActionAnnule() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIdNaturePersonneAssureSocial() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRoleSquareAdmin() {
        return null;
    }

    @Override
    public Long getIdCiviliteNonRenseigne() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneCorrespondant() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneMoraleProspect() {
        return null;
    }

    @Override
    public Long getIdObjetActionChgtCompoFamiliale() {
        return null;
    }

    @Override
    public Long getIdObjetActionDemandeDevisOuPEC() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionCollectif() {
        return null;
    }

    @Override
    public Long getIdTypeActionCotisation() {
        return null;
    }

    @Override
    public Long getIdTypeActionEServices() {
        return null;
    }

    @Override
    public Long getIdTypeActionPrestationsSinistres() {
        return null;
    }

    @Override
    public Long getIdTypeActionRadiation() {
        return null;
    }

    @Override
    public Long getIdTypeActionRecoursGracieux() {
        return null;
    }

    @Override
    public Long getIdTypeRelationAssureSociale() {
        return null;
    }

    @Override
    public Long getIdTypeRelationCorrespondant() {
        return null;
    }

    @Override
    public String getLibelleAPourEnfant() {
        return null;
    }

    @Override
    public String getOrderByActionDateTerminee() {
        return null;
    }

    @Override
    public Long getIdAgencePoleFidelisation() {
        return null;
    }

    @Override
    public Long getIdAgenceInternet() {
        return null;
    }

    @Override
    public Long getIdObjetActionControle() {
        return null;
    }

    @Override
    public Long getIdObjetActionJustificatifIdentite() {
        return null;
    }

    @Override
    public Long getIdSousObjetActionRelance() {
        return null;
    }

    @Override
    public Long getIdTypeActionLab() {
        return null;
    }

    @Override
    public ConstantesDto getConstantes() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneBeneficiaireVivier() {
        return null;
    }

    @Override
    public Long getIdFonctionAnimateur() {
        return null;
    }

    @Override
    public Long getIdFonctionCC() {
        return null;
    }

    @Override
    public Long getIdNatureActionVisiteSortante() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneBeneficiaireAdherent() {
        return null;
    }

    @Override
    public Long getIdNaturePersonneMoraleAdherent() {
        return null;
    }

    @Override
    public Long getIdReseauIndividuel() {
        return null;
    }

    @Override
    public String getOrderByRelationActif() {
        return null;
    }

    @Override
    public String getOrderByRelationDateFin() {
        return null;
    }

}
