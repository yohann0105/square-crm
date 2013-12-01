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
package com.square.composant.tarificateur.square.client.presenter.ligne.devis;

import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.square.composant.tarificateur.square.client.ComposantTarificateur;
import com.square.composant.tarificateur.square.client.content.i18n.ComposantTarificateurConstants;
import com.square.composant.tarificateur.square.client.event.AffichagePopupSelectionProduitEvent;
import com.square.composant.tarificateur.square.client.model.ConstantesModel;
import com.square.composant.tarificateur.square.client.model.opportunite.devis.ligne.LigneDevisModel;
import com.square.composant.tarificateur.square.client.model.personne.BeneficiaireModel;

/**
 * Presenter des lignes de devis.
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class LigneDevisPresenter extends Presenter {

    /** La vue. */
    private LigneDevisView view;

    private LigneDevisModel ligneDevis;

    private boolean isLectureSeule;

    private String typeDevis;

    private ConstantesModel constantesApp;

    /**
     * Constructeur du présenter.
     * @param eventBus le bus
     * @param constantesApp les constantes d'application
     * @param ligneDevis la ligne de devis a charger
     * @param isLectureSeule flag lecture seule
     * @param typeDevis typeDevis
     * @param listeBeneficiaires la liste des benefs
     * @param ligneDevisView la vue
     */
    public LigneDevisPresenter(HandlerManager eventBus, ConstantesModel constantesApp, LigneDevisModel ligneDevis, final boolean isLectureSeule,
        final String typeDevis, List<BeneficiaireModel> listeBeneficiaires, LigneDevisView ligneDevisView) {
        super(eventBus);
        this.view = ligneDevisView;
        this.ligneDevis = ligneDevis;
        this.isLectureSeule = isLectureSeule;
        this.typeDevis = typeDevis;
        this.constantesApp = constantesApp;
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {
        view.getBtnVisuGrille().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                voirGrillePrestations(ligneDevis.getIdentifiantProduit());
            }
        });
        view.getBtnModifierLigne().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fireEventLocalBus(new AffichagePopupSelectionProduitEvent(ligneDevis.getIdentifiantProduit(), ligneDevis.getIdentifiantDevis(),
                    typeDevis, ligneDevis.getIdentifiant()));
            }
        });
    }

    @Override
    public void onShow(HasWidgets container) {
        // on récupère la finalité de la proposition
        mettraAJourFinalite();

        view.getLCreateur().setText(ligneDevis.getGestionnaire() != null ? ligneDevis.getGestionnaire().getLibelle() : null);
        view.getLDateCreation().setText(ligneDevis.getDateCreation());
        view.getLProvenance().setText(ligneDevis.getSourceLigneDevis() != null ? ligneDevis.getSourceLigneDevis().getLibelle() : null);

        final Long idFinalite =
            ligneDevis.getFinalite().getIdentifiant() != null ? ligneDevis.getFinalite().getIdentifiant() : constantesApp.getIdFinaliteEnCours();
        final boolean afficherBoutonModifier = !isLectureSeule && idFinalite.equals(constantesApp.getIdFinaliteEnCours());
        view.activerBoutonModifierLigne(afficherBoutonModifier);

        // on charge les lignes de devis
        view.chargerLigneDevisPrincipale(ligneDevis, isLectureSeule);

        // on ajoute les evenements sur la ligne principale
        ajouterEvenementsLigne(ligneDevis, null, false);
        // on ajoute les evenements sur les lignes liées
        if (ligneDevis.getListeLignesDevisLiees() != null) {
            for (LigneDevisModel ligneLiee : ligneDevis.getListeLignesDevisLiees()) {
                ajouterEvenementsLigne(ligneLiee, ligneDevis.getIdentifiant(), true);
            }
        }

        container.add(view.asWidget());
    }

    /**
     * Affiche les evenements sur une ligne de devis.
     */
    private void ajouterEvenementsLigne(final LigneDevisModel ligne, final Long idLignePrincipale, final boolean ligneLiee) {
        if (!ligneLiee) {
            // on crée les listeners
            final ValueChangeHandler<Boolean> changeRadioHandler = new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    repercuteModifsLignesLiees(ligne);
                }
            };

            // on affecte les listeners
            view.getMapRbLigneAdhesion().get(ligne.getIdentifiant()).addValueChangeHandler(changeRadioHandler);
            view.getMapRbLigneRefus().get(ligne.getIdentifiant()).addValueChangeHandler(changeRadioHandler);
            view.getMapRbLigneEnCours().get(ligne.getIdentifiant()).addValueChangeHandler(changeRadioHandler);
            view.getMapRbLigneCorbeille().get(ligne.getIdentifiant()).addValueChangeHandler(changeRadioHandler);
        }
    }

    /**
     * Selectionne la finalité des lignes liees obligatoires.
     */
    private void repercuteModifsLignesLiees(final LigneDevisModel ligne) {
        final boolean rbAdhesion = view.getMapRbLigneAdhesion().get(ligne.getIdentifiant()).getValue();
        final boolean rbRefus = view.getMapRbLigneRefus().get(ligne.getIdentifiant()).getValue();
        final boolean rbCorbeille = view.getMapRbLigneCorbeille().get(ligne.getIdentifiant()).getValue();
        final boolean rbEnCours = view.getMapRbLigneEnCours().get(ligne.getIdentifiant()).getValue();
        for (LigneDevisModel ligneLiee : ligne.getListeLignesDevisLiees()) {
            view.getMapRbLigneAdhesion().get(ligneLiee.getIdentifiant()).setValue(rbAdhesion);
            view.getMapRbLigneRefus().get(ligneLiee.getIdentifiant()).setValue(rbRefus);
            view.getMapRbLigneCorbeille().get(ligneLiee.getIdentifiant()).setValue(rbCorbeille);
            view.getMapRbLigneEnCours().get(ligneLiee.getIdentifiant()).setValue(rbEnCours);
        }
    }

    /**
     * Met à jour l'affichage des boutons.
     */
    private void mettraAJourFinalite() {
        String styleFinalite = "";
        // on applique le style suivant le statut
        final Long idFinalite =
            ligneDevis.getFinalite().getIdentifiant() != null ? ligneDevis.getFinalite().getIdentifiant() : constantesApp.getIdFinaliteEnCours();
        if (idFinalite.equals(constantesApp.getIdFinaliteAcceptee())) {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteAcceptee();
        }
        else if (idFinalite.equals(constantesApp.getIdFinaliteRefusee())) {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteRefusee();
        }
        else if (idFinalite.equals(constantesApp.getIdFinaliteCorbeille())) {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteCorbeille();
        }
        else {
            styleFinalite = ComposantTarificateur.RESOURCES.css().finaliteNonRenseignee();
        }
        view.setStylePrimaryNameFinaliteLigneDevis(styleFinalite);
    }

    /**
     * Affiche la grille de prestations en PDF.
     * @param identifiantProduit l'identifiant du produit
     */
    private void voirGrillePrestations(Integer idProduit) {
        final String appelServlet =
            ComposantTarificateurConstants.URL_SERVLET_GRILLE_PRESTA_PDF + "?" + ComposantTarificateurConstants.PARAM_ID_PRODUIT + "=" + idProduit;
        view.voirGrillePrestations(appelServlet);
    }

    /**
     * Interface de la vue.
     */
    public interface LigneDevisView extends View {
        /**
         * Affiche la popup d'erreur.
         * @param errorPopupConfiguration la configuration
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasValue<Boolean> getCbTransfertLigne();

        /**
         * active / désactive la case a cocher.
         * @param actif vrai ou faux
         */
        void activerCbTransfertLigne(boolean actif);

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasClickHandlers getBtnVisuGrille();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasClickHandlers getBtnModifierLigne();

        /**
         * Définit le style primaire de la finalité de la ligne de devis.
         * @param styleName le style
         */
        void setStylePrimaryNameFinaliteLigneDevis(String styleName);

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasText getLDateCreation();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasText getLCreateur();

        /**
         * Recupere le handler.
         * @return le handler
         */
        HasText getLProvenance();

        /**
         * Active le bouton.
         * @param actif flag actif
         */
        void activerBoutonModifierLigne(boolean actif);

        /**
         * Charge les données d'une ligne de devis.
         * @param ligneDevis la ligne de devis
         * @param isLectureSeule flag lecture seule
         */
        void chargerLigneDevisPrincipale(LigneDevisModel ligneDevis, boolean isLectureSeule);

        /**
         * Recupere les radios boutons.
         * @return la map des radios boutons
         */
        Map<Long, HasValue<Boolean>> getMapRbLigneAdhesion();

        /**
         * Recupere les radios boutons.
         * @return la map des radios boutons
         */
        Map<Long, HasValue<Boolean>> getMapRbLigneRefus();

        /**
         * Recupere les radios boutons.
         * @return la map des radios boutons
         */
        Map<Long, HasValue<Boolean>> getMapRbLigneCorbeille();

        /**
         * Recupere les radios boutons.
         * @return la map des radios boutons
         */
        Map<Long, HasValue<Boolean>> getMapRbLigneEnCours();

        /**
         * Recupere les checkboxs.
         * @return la map des checkboxs
         */
        Map<Long, HasValue<Boolean>> getMapCbImprimerLigne();

        /**
         * Affiche le message d'erreur.
         */
        void afficherPopupErreurSelectionImpression();

        /**
         * Stoppe le message de chargement.
         */
        void stopperPopupChargement();

        /**
         * Appel la servlet de grilles de prestations.
         * @param url l'url
         */
        void voirGrillePrestations(String url);

        /**
         * Récupère la liste des lignes de devis sélectionnées.
         * @return les identifiants des lignes de devis sélectionnées.
         */
        List<Long> getLignesDevisSelectionnees();
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        
    }
}
