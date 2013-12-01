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
package com.square.composant.tarificateur.square.client.service;

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.tarificateur.square.client.model.CritereModeleDevisModel;
import com.square.composant.tarificateur.square.client.model.ListeProduitsAdherentModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosAdhesionModel;
import com.square.composant.tarificateur.square.client.model.adhesion.InfosGlobalesAdhesionModel;
import com.square.composant.tarificateur.square.client.model.opportunite.InfosOpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.OpportuniteModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.DevisModificationModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.EnregistrementFinaliteDevisModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.TransfertDevisModel;

/**
 * Interface des services liés aux services du tarificateur square.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/tarificateurService")
public interface TarificateurServiceGwt extends RemoteService {
    /**
     * Créé une opportunité ou retourne l'opportunité existante si elle a déjà été créée.
     * @param infosOpportunite informations de l'opportunité à créer/récupérer.
     * @return l'opportunité créée/trouvée.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    OpportuniteModel getOrCreateOpportunite(InfosOpportuniteModel infosOpportunite) throws GwtRunTimeExceptionGwt;

    /**
     * Récupération d'un devis a partir de son identifiant.
     * @param id l'id
     * @return le devis
     */
    DevisModel getDevis(Long id) throws GwtRunTimeExceptionGwt;

    /**
     * Enregistre la finalité d'un devis et de ses lignes de devis en recalculant la finalité de l'opportunité.
     * @param enregistrementFinaliteDevis le Model Gwt permettant l'enregistrement
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void enregistrerFinaliteDevis(EnregistrementFinaliteDevisModel enregistrementFinaliteDevis) throws GwtRunTimeExceptionGwt;

    /**
     * Récupèreles motifs de devis suivant une suggestion.
     * @param suggest la suggestion
     * @param idMotifDevisOriginel id du motif de devis enregistré (au cas où il soit obselete)
     * @return les différents motifs de devis.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<IdentifiantLibelleGwt> getMotifsDevisByCriteres(String suggest, Long idMotifDevisOriginel) throws GwtRunTimeExceptionGwt;

    /**
     * Transfert les lignes d'un devis vers un autre devis.
     * @param transfertDevis les informations pour le transfert
     * @param infosOpp informations de l'opportunité courante
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    void transfererDevis(TransfertDevisModel transfertDevis, InfosOpportuniteModel infosOpp) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des caisses du département dans lequel habite la personne passée en paramètre.
     * @param idPersonne l'identifiant de la personne.
     * @param idRegime l'identifiant du régime.
     * @return la liste des caisses du département.
     * @throws GwtRunTimeExceptionGwt exception levée par GWT.
     */
    List<IdentifiantLibelleGwt> getListeCaisses(Long idPersonne, Long idRegime) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère les informations d'adhésion d'un devis.
     * @param idDevis identifiant du devis.
     * @return informations d'adhésion pour le devis.
     * @throws GwtRunTimeExceptionGwt exception levée par GWT.
     */
    InfosGlobalesAdhesionModel getInfosAdhesion(Long idDevis) throws GwtRunTimeExceptionGwt;

    /**
     * Met à jour les informations supplémentaire pour adhérer.
     * @param infosAdhesion les informations supplémentaires pour adhérer.
     * @throws GwtRunTimeExceptionGwt exception levée par GWT.
     */
    void updateInfosAdhesion(InfosAdhesionModel infosAdhesion) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère les modèles de devis par critères.
     * @param critere les critères de recherche.
     * @return la liste des modèles de devis trouvés.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<IdentifiantLibelleGwt> getListeModelesDevisByCritere(CritereModeleDevisModel critere) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des bénéficiaires du prospect d'un devis pour des lignes de vis sélectionnées.
     * @param devis le devis.
     * @param listeIdsLignesSelectionnees la liste des identifiants des lignes de devis sélectionnées.
     * @return la liste des bénéficiaires.
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<IdentifiantLibelleGwt> getBeneficiairesFromProspectByDevis(DevisModel devis, List<Long> listeIdsLignesSelectionnees) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère la liste des produits pour un adherent.
     * @param idPersonne le idPersonne
     * @return la liste des produits
     * @throws GwtRunTimeExceptionGwt erreur authentifiée.
     */
    List<ListeProduitsAdherentModel> getListeProduitsAdherent(Long idPersonne) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des modes de paiements par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherModesPaiementParCriteres(IdentifiantLibelleGwt criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des periodicites de paiements par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherPeriodicitesPaiementParCriteres(IdentifiantLibelleGwt criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Recherche des jours de paiements par critères.
     * @param criteres les critères de recherche
     * @return la liste d'identifiant libelle correspondant aux critères de recherche
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    List<IdentifiantLibelleGwt> rechercherJoursPaiementParCriteres(IdentifiantLibelleGwt criteres) throws GwtRunTimeExceptionGwt;

    /**
     * Modifie un devis.
     * @param devisModificationModel le DTO contenant de modification du devis
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    void modifierDevis(DevisModificationModel devisModificationModel) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère l'opportunité correspondant au numéro de transaction passé en paramètre.
     * @param numeroTransaction numéro de la transaction.
     * @return l'opportunité trouvée.
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution.
     */
    OpportuniteModel getOpportuniteByNumTransaction(String numeroTransaction) throws GwtRunTimeExceptionGwt;

    /**
     * Détermine si l'utilisateur connecté peut accéder à la recherche d'opportunité par numéro de transaction.
     * @return true si l'utilisateur a accès à la recherche, false sinon
     * @throws GwtRunTimeExceptionGwt exception lors de l'exécution
     */
    Boolean peutRechercherOppParTransaction() throws GwtRunTimeExceptionGwt;

    /**
     * Construit les fichiers à imprimer.
     * @param idDevis l'identifiant du devis
     * @param lignesDevis les ids des lignes de devis à imprimer
     * @param modeledDevis les ids des modèles de devis à imprimer
     * @param beneficiaires les ids des bénéficiaires
     * @param referenceOpportunite la référence de l'opportunité
     * @return la liste des fichiers à imprimer
     */
    Long construireFichiersDevisAImprimer(Long idDevis, String lignesDevis, String modeledDevis,
    		String beneficiaires, String referenceOpportunite) throws GwtRunTimeExceptionGwt;
}
