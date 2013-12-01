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
package com.square.tarificateur.noyau.service.interfaces;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.dto.InfosAdhesionDto;
import com.square.tarificateur.noyau.dto.InfosBaDto;
import com.square.tarificateur.noyau.dto.InfosOpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.ClotureDevisQueryDto;
import com.square.tarificateur.noyau.dto.devis.CritereModeleDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheLigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.CriteresRechercheOpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.DevisDto;
import com.square.tarificateur.noyau.dto.devis.DevisModificationDto;
import com.square.tarificateur.noyau.dto.devis.EnregistrementFinaliteDevisDto;
import com.square.tarificateur.noyau.dto.devis.LigneDevisDto;
import com.square.tarificateur.noyau.dto.devis.MotifDevisDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteDto;
import com.square.tarificateur.noyau.dto.devis.OpportuniteModificationDto;
import com.square.tarificateur.noyau.dto.devis.TransfertDevisDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.dto.produit.adherent.ListeProduitsAdherentDto;
import com.square.tarificateur.noyau.dto.selecteur.produit.SelecteurProduitDto;

/**
 * Services de tarification.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public interface TarificateurService {

    /**
     * Créé une opportunité ou retourne l'opportunité existante si elle a déjà été créée.
     * @param infosOpportunite informations de l'opportunité à créer/récupérer.
     * @return l'opportunité créée/trouvée.
     */
    OpportuniteDto getOrCreateOpportunite(InfosOpportuniteDto infosOpportunite);

    /**
     * Créé un devis lié à l'opportunité spécifié en paramètre.
     * @param infosOpportunite informations de l'opportunité liée au devis à créer.
     * @return le devis créé.
     */
    DevisDto createDevis(InfosOpportuniteDto infosOpportunite);

    /**
     * Transfert des lignes d'un devis vers un autre devis.
     * @param transfertDevisDto le DTO contenant les informations pour le transfert
     * @return le devis crée
     */
    DevisDto transfererDevis(TransfertDevisDto transfertDevisDto);

    /**
     * Enregistre la finalité d'un devis et de ses lignes de devis en recalculant la finalité de l'opportunité.
     * @param enregistrementFinaliteDevisDto le DTO permettant l'enregistrement
     */
    void enregistrerFinaliteDevis(EnregistrementFinaliteDevisDto enregistrementFinaliteDevisDto);

    /**
     * Retourne un objet selecteur du produit à partir d'une produit et d'un devis pour construire l'IHM.
     * @param idProduit l'identifiant du produit
     * @param idDevis l'identifiant du devis (null si nouveau devis)
     * @param nouvellePersonnePrincipale la nouvelle personne principale (utilisée pour un nouveau devis)
     * @return un objet selecteur du produit representant l'IHM a construire
     */
    SelecteurProduitDto getSelecteurParProduit(final Integer idProduit, final Long idDevis, final PersonneTarificateurDto nouvellePersonnePrincipale);

    /**
     * Recuperer un objet selecteur du produit à partir d'une ligne de devis.
     * @param idLigneDevis l'identifiant de la ligne
     * @param idDevis l'identifiant du devis
     * @return un objet selecteur du produit representant l'IHM a construire
     */
    SelecteurProduitDto getSelecteurProduitParLigneDevis(final Long idLigneDevis, final Long idDevis);

    /**
     * Recuperer un objet selecteur du produit pour un adherent.
     * @param idDevis l'identifiant du devis
     * @param produitAia le produitAia
     * @param garantieAia la garantieAia
     * @param nouvellePersonnePrincipale la nouvelle personne principale (utilisée pour un nouveau devis)
     * @return un objet selecteur du produit representant l'IHM a construire
     */
    SelecteurProduitDto getSelecteurProduitParProduitsAdherent(final Long idDevis, final String produitAia, final String garantieAia,
        final PersonneTarificateurDto nouvellePersonnePrincipale);

    /**
     * Ajouter une ligne de devis à partir d'un objet selecteur du produit.
     * @param selecteurProduit objet selecteur du produit
     * @return la ligne de devis
     */
    LigneDevisDto addLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit);

    /**
     * Ajout d'une ligne de devis à un devis.
     * @param idDevis l'identifiant du devis.
     * @param ligneDevis les informations de la nouvelle ligne de devis.
     * @return la ligne de devis apres la creation.
     */
    LigneDevisDto addLigneDevis(final Long idDevis, LigneDevisDto ligneDevis);

    /**
     * Ajouter une ligne de devis à partir d'un objet selecteur du produit pour un nouveau devis.
     * @param selecteurProduit objet selecteur du produit
     * @param infosOpportunite les infos de l'opp
     * @return la ligne de devis
     */
    LigneDevisDto addLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit, InfosOpportuniteDto infosOpportunite);

    /**
     * Modifie une ligne de devis à partir d'un objet selecteur du produit.
     * @param selecteurProduit objet selecteur du produit
     * @return la ligne de devis
     */
    LigneDevisDto updateLigneDevisParSelecteurProduit(SelecteurProduitDto selecteurProduit);

    /**
     * Récupère la liste des produits pour un adherent.
     * @param idPersonne L'identifiant de la personne
     * @return la liste des produits
     */
    List<ListeProduitsAdherentDto> getListeProduitsAdherent(Long idPersonne);

    /**
     * Récupère la liste des régimes.
     * @return la liste des régimes.
     */
    List<IdentifiantLibelleDto> getListeRegimes();

    /**
     * Récupère la liste des caisses du département dans lequel habite la personne passée en paramètre.
     * @param idPersonne l'identifiant de la personne.
     * @param idRegime le régime sélectionné
     * @return la liste des caisses du département.
     */
    List<IdentifiantLibelleDto> getListeCaisses(Long idPersonne, Long idRegime);

    /**
     * Récupère l'ensemble des moyens de paiement.
     * @return les différents moyens de paiement.
     */
    List<IdentifiantLibelleDto> getListeMoyensPaiement();

    /**
     * Récupère l'ensemble des motifs de devis.
     * @return les différents motifs de devis.
     */
    List<MotifDevisDto> getListeMotifsDevis();

    /**
     * Récupère les motifs de devis correspondant au libelle.
     * @param libelle le libelle
     * @return les différents motifs de devis.
     */
    List<MotifDevisDto> getMotifsDevisByCriteres(String libelle);

    /**
     * Récupère un motif de devis.
     * @param id l'id
     * @return le motif de devis
     */
    MotifDevisDto getMotifDevis(Long id);

    /**
     * Récupère l'ensemble des périodicités de paiement.
     * @return les différentes périodicités de paiement.
     */
    List<IdentifiantLibelleDto> getListePeriodicitesPaiement();

    /**
     * Récupère l'ensemble des jours du mois pour le paiement.
     * @return les différents jours du mois pour le paiement.
     */
    List<IdentifiantLibelleDto> getListeJoursPaiement();

    /**
     * Récupère des moyens de paiement par criteres.
     * @param criteres criteres
     * @return les différents moyens de paiement.
     */
    List<IdentifiantLibelleDto> rechercherMoyensPaiementParCriteres(IdentifiantLibelleDto criteres);

    /**
     * Récupère des périodicités de paiement par criteres.
     * @param criteres criteres
     * @return les différents moyens de paiement.
     */
    List<IdentifiantLibelleDto> rechercherPeriodicitesPaiementParCriteres(IdentifiantLibelleDto criteres);

    /**
     * Récupère des jours de paiement par criteres.
     * @param criteres criteres
     * @return les différents moyens de paiement.
     */
    List<IdentifiantLibelleDto> rechercherJoursPaiementParCriteres(IdentifiantLibelleDto criteres);

    /**
     * Récupère un moyen de paiement par id.
     * @param id id
     * @return les différents moyens de paiement.
     */
    IdentifiantLibelleDto rechercherMoyenPaiementParId(Long id);

    /**
     * Récupère une périodicité de paiement par id.
     * @param id id
     * @return les différents moyens de paiement.
     */
    IdentifiantLibelleDto rechercherPeriodicitePaiementParId(Long id);

    /**
     * Récupère un jour de paiement par id.
     * @param id id
     * @return les différents moyens de paiement.
     */
    IdentifiantLibelleDto rechercherJourPaiementParId(Long id);

    /**
     * Récupère les informations d'adhésion d'un devis.
     * @param idDevis identifiant du devis.
     * @return informations d'adhésion pour le devis.
     */
    InfosAdhesionDto getInfosAdhesion(Long idDevis);

    /**
     * Met à jour les informations supplémentaire pour adhérer.
     * @param infosAdhesion les informations supplémentaires pour adhérer.
     */
    void updateInfosAdhesion(InfosAdhesionDto infosAdhesion);

    /**
     * Récupère la liste des modèles de devis en fonction de critères.
     * @param criteres les critères de recherche.
     * @return la liste des modèles de devis trouvés.
     */
    List<IdentifiantLibelleDto> getListeModelesDevisByCriteres(CritereModeleDevisDto criteres);

    /**
     * Récupère la liste des identifiants de produits deja affectés à un devis.
     * @param idDevis l'id du devis
     * @return la liste des ids de produits
     */
    List<Integer> getListeIdsProduitsParIdDevis(Long idDevis);

    /**
     * Recherche de devis par criteres.
     * @param criteres la liste des criteres.
     * @return la liste des devis.
     */
    List<DevisDto> getListeDevisByCriteres(CriteresRechercheDevisDto criteres);

    /**
     * Permet de determiner la valeur du critere compo famille à partir d'une ligne de devis.
     * @param idLigneDevis l'identifiant de la ligne de devis.
     * @return la valeur du citere.
     */
    String getValeurCritereCodeCompoFamille(Long idLigneDevis);

    /**
     * Demande de cloture de ligne de devis.
     * @param query la requête de cloture.
     */
    void cloturerDevisQuery(ClotureDevisQueryDto query);

    /**
     * Permet de recuperer une ligne de devis par son identifiant.
     * @param idLigneDevis identifiant de la ligne de devis.
     * @return la ligne de devis null si aucune occurence.
     */
    LigneDevisDto getLigneDevisParIdentifiant(Long idLigneDevis);

    /**
     * Permet de recalculer une ligne de devis.
     * @param idDevis l'identifiant du devis.
     * @param ligneDevis la ligne de devis à recalculer.
     * @return la ligne de devis apres recalcule.
     */
    LigneDevisDto recalculerLigneDevis(final Long idDevis, final LigneDevisDto ligneDevis);

    /**
     * Permet de recuperer la valeur du critere compositionf famille en fonction d'une liste de Beneficiaire.
     * @param beneficiaires la liste des beneficiaires.
     * @return la valeur du code.
     */
    String getValeurCritereCodeCompoFamille(List<BeneficiaireDto> beneficiaires);

    /**
     * Mise à jour de la famille d'un devis.
     * @param idDevis identifant du devis.
     * @param personne les nouvelles informations de mise à jour.
     */
    void majFamilleDevis(final Long idDevis, PersonneTarificateurDto personne);

    /**
     * Met à jour une opportunité.
     * @param opportuniteModificationDto le DTO contenant les infos à modifier
     */
    void modifierOpportunite(OpportuniteModificationDto opportuniteModificationDto);

    /**
     * Recupere la liste des ids de fichiers qui n'ont pas été téléchargés.
     * @return la liste des ids
     */
    List<String> getIdsBaAdhesionNonTelecharges();

    /**
     * Met à jour les infos d'un BA sur l'adhésion.
     * @param infos les infos
     * @return id du devis accepté lié à l'adhésion
     */
    Long mettreAJourInfosFichier(InfosBaDto infos);

    /**
     * Recuperation de l'opportunite par son identifiant.
     * @param idOpportunite identifaint de l'opp.
     * @return l'opp ou null.
     */
    OpportuniteDto getOpportuniteById(Long idOpportunite);

    /**
     * Récupère l'opportunité correspondant au numéro de transaction passé en paramètre.
     * @param numeroTransaction numéro de la transaction.
     * @return l'opportunité trouvée.
     */
    OpportuniteDto getOpportuniteByNumTransaction(String numeroTransaction);

    /**
     * Permet de recuperer des lignes de devis en fonction de critères.
     * @param criteres criteres
     * @return la ligne de devis null si aucune occurence.
     */
    List<LigneDevisDto> getListeLignesDevisParCriteres(CriteresRechercheLigneDevisDto criteres);

    /**
     * Modifie un devis.
     * @param devisModificationDto le DTO contenant de modification du devis
     */
    void modifierDevis(DevisModificationDto devisModificationDto);

    /**
     * Récupère le bénéficiaire demandé par sa cible.
     * @param idBeneficiaire l'identifiant du bénéficiaire cible
     * @return le bénéficiaire
     */
    BeneficiaireDto getBeneficiaireByCible(Long idBeneficiaire);

    /**
     * Supprime les lignes de devis d'un devis.
     * @param idDevis l'identifiant du devis.
     */
    void deleteLigneDevisfromDevis(Long idDevis);

    /**
     * Supprime une ligne de devis.
     * @param idLigneDevis l'identifiant de la ligne de devis à supprimer.
     */
    void deleteLigneDevis(Long idLigneDevis);

    /**
     * Compare les informations des familles de 2 personnes.
     * @param personneAComparer les informations de la famille à comparer.
     * @param idPersonneReference l'identifiant de la personne de référence pour la comparaison.
     * @return true si les familles sont identiques, false si non.
     */
    boolean comparerFamille(PersonneTarificateurDto personneAComparer, Long idPersonneReference);

    /**
     * Modifie la source d'une ligne de devis et de ses lignes liées.
     * @param idLigne la ligne
     * @param idSource la source
     */
    void modifierSourceLigneDevis(Long idLigne, Long idSource);

    /**
     * Retourne les identifiants des opportunités correspondant aux critères spécifiés.
     * @param criteres les critères de recherche.
     * @return les identifiants des opportunités correspondant aux critères spécifiés.
     */
    List<Long> rechercherIdsOpportunitesByCritere(CriteresRechercheOpportuniteDto criteres);

    /**
     * Modifie la sélection de l'ensemble des lignes d'un devis pour l'impression.
     * @param idDevis l'identifiant du devis.
     * @param isSelectionne booléen spécifiant si les lignes de devis sont sélectionnées pour impression ou non.
     */
    void modifierSelectionPourImpression(Long idDevis, boolean isSelectionne);

    /**
     * Service permettant de savoir si une personne est bénéficiaire sur une opportunité transformée.
     * @param eidPersonne eid de la personne
     * @return la ligne de devis null si aucune occurence.
     */
    Boolean personneIsBeneficiaireSurOppTransforme(Long eidPersonne);

    /**
     * Récupére la liste des bénéficiaires d'un prospect en focntion d'un devis.
     * @param devis le devis
     * @param listeIdsLignesSelectionnees les lignes séléctionnées.
     * @return la liste des bénéficiaires
     */
    List<IdentifiantLibelleDto> getBeneficiairesFromProspectByDevis(DevisDto devis, List<Long> listeIdsLignesSelectionnees);
}
