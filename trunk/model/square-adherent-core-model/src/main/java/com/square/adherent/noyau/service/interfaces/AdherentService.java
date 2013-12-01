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
package com.square.adherent.noyau.service.interfaces;

import java.util.List;

import com.square.adherent.noyau.dto.adherent.BeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.InfosCompteAdherentDto;
import com.square.adherent.noyau.dto.adherent.InfosEspaceAdherentDto;
import com.square.adherent.noyau.dto.adherent.InfosIdentifiantPersonneDto;
import com.square.adherent.noyau.dto.adherent.InfosOptionsAdherentDto;
import com.square.adherent.noyau.dto.adherent.OptionAdherentDto;
import com.square.adherent.noyau.dto.adherent.PersonneDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoupleBaremeDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeDto;
import com.square.adherent.noyau.dto.adherent.prestations.DemandePriseEnChargeDto;
import com.square.adherent.noyau.dto.prestation.CriteresPersonnesNotificationSmsDto;
import com.square.adherent.noyau.dto.produit.ReserveProduitBancoDto;

/**
 * Services relatifs aux adhérents.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface AdherentService {

    /**
     * Récupère les informations de l'espace personnel d'un adhérent.
     * @param uidPersonne identifiant unique de l'adhérent.
     * @return informations de l'espace personnel de l'adhérent.
     * @deprecated utiliser le service d'espaceClientService
     */
    @Deprecated
    InfosEspaceAdherentDto getInfosEspaceAdherent(Long uidPersonne);

    /**
     * Récupère les informations des options d'un adhérent.
     * @param uidPersonne identifiant unique de l'adhérent.
     * @return informations des options de l'adhérent.
     */
    InfosOptionsAdherentDto getInfosOptionsAdherent(Long uidPersonne);

    /**
     * Met à jour les options de l'adhérent.
     * @param uidPersonne identifiant de l'adhérent.
     * @param listeOptions liste des options à mettre à jour.
     * @return les options mises à jour.
     */
    List<OptionAdherentDto> mettreAJourListeOptionsAdherent(Long uidPersonne, List<OptionAdherentDto> listeOptions);

    /**
     * Met à jour le mot de passe d'une personne.
     * @param idPersonne lidentifiant Square de la personne.
     * @param motDePasse le nouveau mot de passe.
     */
    void mettreAJourMotDePasse(Long idPersonne, String motDePasse);

    /**
     * Récupère les infos de la réserve du produit banco d'un adhérent par son identifiant.
     * @param idAdherent l'identifiant de l'adhérent.
     * @return la garantie trouvée.
     */
    List<ReserveProduitBancoDto> getReserveProduitBancoByUser(Long idAdherent);

    /**
     * Récupère la liste des identifiants des bénéficiaires d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return la liste des identifiants des bénéficiaires.
     */
    List<Long> getListeBeneficiairesPersonne(Long idPersonne);

    /**
     * Récupération de l'adhérent principal d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return l'identifiant de la personne principale
     */
    Long getIdAdherentPrincipal(Long idPersonne);

    /**
     * Détermine si une personne possède un espace adhérent.
     * @param uidPersonne identifiant de la personne.
     * @return true si la personne possède un espace adhérent, false si non.
     */
    boolean hasPersonneEspaceAdherent(Long uidPersonne);

    /**
     * Détermine si la personne est associée à au moins un contrat (bénéficiaire de garantie) .
     * @param uidPersonne identifiant de la personne
     * @return true si la personne est associée à au moins un contrat, false sinon
     */
    boolean isPersonneAssocieeAContrat(Long uidPersonne);

    /**
     * Indique si un adhérent possède une option spécifiée.
     * @param idPersonne l'identifiant de la personne.
     * @param idTypeOption l'identifiant du type de l'option
     * @return true si le compte possède l'option, sinon false.
     */
    boolean isPersonnePossedeOption(Long idPersonne, Long idTypeOption);

    /**
     * Récupère la liste des identifiants des types d'options souscrites par une personne.
     * @param uidPersonne identifiant de la personne.
     * @return la liste des identifiants des types d'options souscrites
     */
    List<Long> getListeIdsTypesOptionsSouscrites(Long uidPersonne);

    /**
     * Met à jour les informations du compte adhérent.
     * @param infosCompteAdherent informations du compte adherent
     */
    void mettreAJourInfosCompteAdherent(InfosCompteAdherentDto infosCompteAdherent);

    /**
     * Désactive les options d'envoie par email d'une personne.
     * @param uidPersonne l'identifiant Square de la personne.
     */
    void desactiverOptionsEnvoiParEmail(Long uidPersonne);

    /**
     * Désactive les options d'envoie par email d'une personne.
     * @param uidPersonne l'identifiant Square de la personne.
     */
    void desactiverOptionsEnvoiParTelephone(Long uidPersonne);

    /**
     * Récupère la liste des personnes a qui envoyer un sms pour notifier un remboursement, par criteres.
     * @param criteres criteres de recherche.
     * @return la liste des infos portables des personnes
     */
    List<Long> getListePersonnesNotificationSmsByCriteres(CriteresPersonnesNotificationSmsDto criteres);

    /**
     * Récupére la liste des identifiants des adhérents ayant souscrit à une option précise.
     * @param idOption l'identifiant de l'option souscrite.
     * @return la liste des identifiants d'adhérents.
     */
    List<Long> getListeAdherentsByIdOptionSouscrite(Long idOption);

    /**
     * Récupére la liste des identifiants des adhérents qui ont déjà reçu le magazine.
     * @param idMagazine l'identifiant du magazine.
     * @return la liste des identifiants des adhérents.
     */
    List<Long> getListeAdherentsByIdMagazineEnvoye(Long idMagazine);

    /**
     * Transmet la demande de prise en charge de l'adhérent auprès de la Smatis.
     * @param demandePriseEnChargeDto la demande de prise en charge
     */
    void demanderPriseEnCharge(DemandePriseEnChargeDto demandePriseEnChargeDto);

    /**
     * Ajoute un bénéficiaire à l'adhérent.
     * @param idAdherent l'identifiant de l'adhérent
     * @param beneficiaireDto le nouveau bénéficiaire
     * @return le bénéficiaire créé.
     */
    BeneficiaireDto ajouterBeneficiaire(Long idAdherent, BeneficiaireDto beneficiaireDto);

    /**
     * Recupere les garanties baremes d'un adherent par criteres.
     * @param criteres les criteres
     * @return la liste des garanties baremes
     */
    List<GarantieBaremeDto> getListeGarantiesBaremesAdherent(GarantieBaremeCriteresDto criteres);

    /**
     * Recupere des couples de baremes de garanties d'adherent suivant des criteres.
     * @param criteres les criteres
     * @return la liste des garanties baremes
     */
    List<CoupleBaremeDto> getListeCouplesBaremesAdherent(GarantieBaremeCriteresDto criteres);

    /**
     * Récupère une personne par son identifiant.
     * @param idPersonne l'identifiant de la personne.
     * @return la personne trouvée.
     */
    PersonneDto getPersonneById(Long idPersonne);

    /**
     * Récupère une personne par son numéro de client.
     * @param numClient le numéro de client.
     * @return la personne trouvée.
     */
    PersonneDto getPersonneByNumClient(final String numClient);

    /**
     * Récupère la liste des bénéficiaires d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return la liste des bénéficiaires.
     */
    List<BeneficiaireDto> getListeInfosBeneficiairesPersonne(Long idPersonne);

    /**
     * Récupère la liste paginée des assurés principaux.
     * @param debutPagination le début de la pagination.
     * @param taillePagination la taille de la pagination.
     * @return la liste des assurés principaux.
     */
    List<PersonneDto> getListeAssurePrincipalParPagination(int debutPagination, int taillePagination);

    /**
     * Récupère la liste paginée des bénéficiaires.
     * @param debutPagination le début de pagination.
     * @param taillePagination la taille de pagination.
     * @return la liste des bénéficiaires.
     */
    List<PersonneDto> getListeBeneficaireParPagination(int debutPagination, int taillePagination);

    /**
     * Retourne les infos d'identifiants (onum et numéro adhérent) de l'adhérent principal d'un adhérent par son numéro d'adherent (qui peut être lui même).
     * (getNumeroAdherentPrincipalByNumAdherent)
     * @param numAdherent le numéro de l'adhérent.
     * @return l'adherent principal.
     */
    InfosIdentifiantPersonneDto getInfosAdherentPrincipalByNumAdherent(String numAdherent);
}
