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

import com.google.gwt.user.client.rpc.AsyncCallback;
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
 * Interface pour les appels asynchrones des services du tarificateur square.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public interface TarificateurServiceGwtAsync {
    /**
     * Créé une opportunité ou retourne l'opportunité existante si elle a déjà été créée.
     * @param infosOpportunite informations de l'opportunité à créer/récupérer.
     * @param callback l'objet de retour asynchrone
     */
    void getOrCreateOpportunite(InfosOpportuniteModel infosOpportunite, AsyncCallback<OpportuniteModel> callback);

    /**
     * Récupération d'un devis a partir de son identifiant.
     * @param id l'id
     * @param callback le callback
     */
    void getDevis(Long id, AsyncCallback<DevisModel> callback);

    /**
     * Enregistre la finalité d'un devis et de ses lignes de devis en recalculant la finalité de l'opportunité.
     * @param enregistrementFinaliteDevis le Model Gwt permettant l'enregistrement
     * @param callback l'objet de retour asynchrone
     */
    void enregistrerFinaliteDevis(EnregistrementFinaliteDevisModel enregistrementFinaliteDevis, AsyncCallback<Object> callback);

    /**
     * Récupèreles motifs de devis suivant une suggestion.
     * @param suggest la suggestion
     * @param idMotifDevisOriginel id du motif de devis enregistré (au cas où il soit obselete)
     * @param callback l'objet de retour asynchrone
     */
    void getMotifsDevisByCriteres(String suggest, Long idMotifDevisOriginel, AsyncCallback<List<IdentifiantLibelleGwt>> callback);

    /**
     * Transfert les lignes d'un devis vers un autre devis.
     * @param transfertDevis les informations pour le transfert
     * @param infosOpp les infos de l'opp courante
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void transfererDevis(TransfertDevisModel transfertDevis, InfosOpportuniteModel infosOpp, AsyncCallback<Object> asyncCallback);

    /**
     * Récupère la liste des caisses du département dans lequel habite la personne passée en paramètre.
     * @param idPersonne l'identifiant de la personne.
     * @param idRegime l'identifiant du régime.
     * @param callback la liste des caisses du département.
     */
    void getListeCaisses(Long idPersonne, Long idRegime, AsyncCallback<List<IdentifiantLibelleGwt>> callback);

    /**
     * Récupère les informations d'adhésion d'un devis.
     * @param idDevis identifiant du devis.
     * @param callback informations d'adhésion pour le devis.
     */
    void getInfosAdhesion(Long idDevis, AsyncCallback<InfosGlobalesAdhesionModel> callback);

    /**
     * Met à jour les informations supplémentaire pour adhérer.
     * @param infosAdhesion les informations supplémentaires pour adhérer.
     * @param callback void.
     */
    void updateInfosAdhesion(InfosAdhesionModel infosAdhesion, AsyncCallback<Void> callback);

    /**
     * Récupère les modèles de devis par critères.
     * @param critere les critères de recherche.
     * @param asyncCallback l'objet de retour asynchrone
     */
    void getListeModelesDevisByCritere(CritereModeleDevisModel critere, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Récupère la liste des bénéficiaires du prospect d'un devis pour des lignes de devis sélectionnées.
     * @param devis le devis.
     * @param listeIdsLignesSelectionnees la liste des identifiants des lignes de devis sélectionnées.
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void getBeneficiairesFromProspectByDevis(DevisModel devis, List<Long> listeIdsLignesSelectionnees,
        AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Récupère la liste des produits pour un adherent.
     * @param idPersonne le idPersonne
     * @param callback l'objet de retour asynchrone
     */
    void getListeProduitsAdherent(Long idPersonne, AsyncCallback<List<ListeProduitsAdherentModel>> callback);

    /**
     * Recherche des modes de paiement par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherModesPaiementParCriteres(IdentifiantLibelleGwt criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des periodicites de paiement par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherPeriodicitesPaiementParCriteres(IdentifiantLibelleGwt criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Recherche des jours de paiement par critères.
     * @param criteres les critères de recherche
     * @param asyncCallback le callback
     */
    void rechercherJoursPaiementParCriteres(IdentifiantLibelleGwt criteres, AsyncCallback<List<IdentifiantLibelleGwt>> asyncCallback);

    /**
     * Modifie un devis.
     * @param devisModificationModel le DTO contenant de modification du devis
     * @param asyncCallback l'objet de retour asynchrone.
     */
    void modifierDevis(DevisModificationModel devisModificationModel, AsyncCallback<Void> asyncCallback);

    /**
     * Récupère l'opportunité correspondant au numéro de transaction passé en paramètre.
     * @param numeroTransaction numéro de la transaction.
     * @param asyncCallback l'opportunité trouvée.
     */
    void getOpportuniteByNumTransaction(String numeroTransaction, AsyncCallback<OpportuniteModel> asyncCallback);

    /**
     * Détermine si l'utilisateur connecté peut accéder à la recherche d'opportunité par numéro de transaction.
     * @param asyncCallback true si l'utilisateur a accès à la recherche, false sinon
     */
    void peutRechercherOppParTransaction(AsyncCallback<Boolean> asyncCallback);

    /**
     * Construit les fichiers à imprimer.
     * @param idDevis l'identifiant du devis
     * @param lignesDevis les ids des lignes de devis à imprimer
     * @param modeledDevis les ids des modèles de devis à imprimer
     * @param beneficiaires les ids des bénéficiaires
     * @param referenceOpportunite la référence de l'opportunité
     * @param asyncCallback la liste des fichiers à imprimer
     */
    void construireFichiersDevisAImprimer(Long idDevis, String lignesDevis, String modeledDevis,
    		String beneficiaires, String referenceOpportunite, AsyncCallback<Long> asyncCallback);
}
