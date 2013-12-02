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
package com.square.client.gwt.client.presenter.personne;

import java.util.ArrayList;
import java.util.List;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.mvp.presenter.Presenter;
import org.scub.foundation.framework.gwt.module.client.mvp.view.View;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.client.gwt.client.controller.AppControllerConstants;
import com.square.client.gwt.client.event.OpenPersonEvent;
import com.square.client.gwt.client.event.OpenPersonneMoraleEvent;
import com.square.client.gwt.client.event.UpdateTabNameEvent;
import com.square.client.gwt.client.exception.ControleIntegriteExceptionGwt;
import com.square.client.gwt.client.model.ConstantesModel;
import com.square.client.gwt.client.model.DimensionCritereRechercheTypeRelationModel;
import com.square.client.gwt.client.model.ItemValueModel;
import com.square.client.gwt.client.model.PersonneMoraleRelationModel;
import com.square.client.gwt.client.model.PersonnePhysiqueRelationModel;
import com.square.client.gwt.client.model.PersonneRelationModel;
import com.square.client.gwt.client.model.RelationCriteresRechercheModel;
import com.square.client.gwt.client.model.RelationInfosModel;
import com.square.client.gwt.client.model.RelationModel;
import com.square.client.gwt.client.service.DimensionServiceGwtAsync;
import com.square.client.gwt.client.service.PersonneServiceGwtAsync;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsBlocViewImpl;
import com.square.client.gwt.client.view.personne.relations.PersonneRelationsViewImplConstants;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composant.aide.gwt.client.event.DemandeAideEvent;
import com.square.composant.aide.gwt.client.event.DemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasDemandeAideEventHandler;
import com.square.composant.aide.gwt.client.event.HasUpdateAideEventHandler;
import com.square.composant.aide.gwt.client.event.UpdateAideEvent;
import com.square.composant.aide.gwt.client.event.UpdateAideEventHandler;
import com.square.composant.aide.gwt.client.service.AideServiceGwtAsync;
import com.square.composant.contrat.square.client.presenter.IconeContratPresenter;
import com.square.composant.contrat.square.client.view.IconeContratViewImpl;
import com.square.composants.graphiques.lib.client.composants.DecoratedCalendrierDateBox;
import com.square.composants.graphiques.lib.client.composants.model.PopupInfoConfiguration;
import com.square.composants.graphiques.lib.client.composants.model.RapportModel;
import com.square.composants.graphiques.lib.client.event.ControleIntegriteExceptionEvent;
import com.square.composants.graphiques.lib.client.event.IconeErreurChampManager;
import com.square.composants.graphiques.lib.client.popups.DecoratedInfoPopup;

/**
 * Presenter pour la page de relations familiales.
 * @author cblanchard - SCUB
 */
public class PersonneRelationsPresenter extends Presenter {

    /** Le service sur les personnes. */
    private PersonneServiceGwtAsync personneRpcService;

    /** Le service sur les dimensions. */
    private DimensionServiceGwtAsync dimensionRpcService;

    /** Vue associé au presenter. */
    private PersonneRelationsView view;

    /** Identifiant de la personne principale. */
    private Long idPersonne;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtreGroupements;

    /** identifiant limitation pour les groupement. */
    private List<Long> filtrePasDansGroupements;

    /** Les infos à modifier dans la relation. */
    private List<InfosModificationLigneRelation> infosModification;

    private PersonneRelationsPresenterConstants presenterConstants;

    private ConstantesModel constantes;

    private AideServiceGwtAsync aideService;

    /**
     * Constructeur par defaut.
     * @param eventBus le bus des évenements
     * @param personneRpcService service rpc pour les personnes
     * @param dimensionRpcService service rpc pour les dimensions
     * @param view la vue associé au presenter
     * @param constantes les constantes (service de mapping)
     * @param idPersonne l'identifiant de la personne principale.
     * @param filtreGroupements permet de limiter l'affichage des relations à un groupement.
     * @param filtrePasDansGroupements permet de limiter l'affichage des relations à un groupement.
     * @param aideService service d'aide.
     */
    public PersonneRelationsPresenter(HandlerManager eventBus, PersonneServiceGwtAsync personneRpcService, DimensionServiceGwtAsync dimensionRpcService,
        PersonneRelationsView view, ConstantesModel constantes, List<Long> filtreGroupements, Long idPersonne, List<Long> filtrePasDansGroupements,
        AideServiceGwtAsync aideService) {
        super(eventBus);
        this.personneRpcService = personneRpcService;
        this.dimensionRpcService = dimensionRpcService;
        this.view = view;
        this.idPersonne = idPersonne;
        this.filtreGroupements = filtreGroupements;
        this.filtrePasDansGroupements = filtrePasDansGroupements;
        this.presenterConstants = GWT.create(PersonneRelationsPresenterConstants.class);
        this.constantes = constantes;
        this.aideService = aideService;
    }

    @Override
    public void onShow(HasWidgets container) {
        initListeRelations();
        container.add(view.asWidget());
    }

    @Override
    public View asView() {
        return this.view;
    }

    @Override
    public void onBind() {

    }

    /** Récupère les relations. */
    public void initListeRelations() {
        infosModification = new ArrayList<InfosModificationLigneRelation>();
        final RelationCriteresRechercheModel criteres = new RelationCriteresRechercheModel();
        criteres.setIdPersonne(idPersonne);
        criteres.setGroupements(filtreGroupements);
        criteres.setPasDansGroupements(filtrePasDansGroupements);
        view.afficherLoadingPopup();
        personneRpcService.rechercherRelationsParCritreres(criteres, new AsyncCallback<List<RelationInfosModel<? extends PersonneRelationModel>>>() {
            @Override
            public void onSuccess(List<RelationInfosModel<? extends PersonneRelationModel>> result) {
                if (result != null) {
                    if (filtrePasDansGroupements != null) {
                        fireEventLocalBus(new UpdateTabNameEvent(idPersonne, result.size(), presenterConstants.relations()));
                    }
                    if (filtreGroupements != null) {
                        fireEventLocalBus(new UpdateTabNameEvent(idPersonne, result.size(), presenterConstants.famille()));
                    }
                    view.supprimerBlocs();
                    // Parcours des relations
                    int index = 0;
                    for (final RelationInfosModel<? extends PersonneRelationModel> relation : result) {
                        // AJOUT DU BLOC A LA VUE
                        view.ajouterBlocRelation(formerBlocRelation(relation, index));
                        index++;
                    }
                }
                view.onRpcServiceSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });
    }

    /**
     * Former un Bloc de relation à partir d'une relation.
     * @param relation la relation source.
     * @param index l'index
     */
    private PersonneRelationsBlocViewImpl formerBlocRelation(final RelationInfosModel<? extends PersonneRelationModel> relation, int index) {
        // Création d'un bloc
        final PersonneRelationsBlocViewImpl bloc = new PersonneRelationsBlocViewImpl(relation.getType().getLibelle(), index, constantes.isHasRoleAdmin());

        // Construction de la liste des identifiants des composants d'aide de la vue associée à ce presenter
        final List<Long> listeIdsComposantsAides = new ArrayList<Long>();
        for (final AideComposant composant : bloc.getListAideCompsant()) {
            listeIdsComposantsAides.add(composant.getId());
        }

        // Recherche des composants d'aide existant pour les rendre visible
        aideService.rechercherIdsComposantsAides(listeIdsComposantsAides, new AsyncCallback<List<Long>>() {
            @Override
            public void onSuccess(List<Long> result) {
                if (result != null) {
                    for (final AideComposant composant : bloc.getListAideCompsant()) {
                        composant.setVisible(result.contains(composant.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
            }
        });

        for (HasUpdateAideEventHandler handler : bloc.getListeUpdateEventHandler()) {
            handler.addUpdateAideEventHandler(new UpdateAideEventHandler() {
                @Override
                public void onUpdateAide(UpdateAideEvent event) {
                    aideService.creerOuModifierAide(event.getAide(), event.getCallBack());
                }
            });
        }
        for (HasDemandeAideEventHandler handler : bloc.getListeDemandeEventHandler()) {
            handler.addDemandeAideEventHandler(new DemandeAideEventHandler() {
                @Override
                public void onDemandeAide(DemandeAideEvent event) {
                    aideService.rechercherAide(event.getIdComposant(), event.getCallBack());
                }
            });
        }
        // handler sur listbox
        bloc.getSuggestListBoxTypeRelationHandler().addSuggestHandler(new SuggestListBoxSuggestEventHandler<ItemValueModel>() {
            @Override
            public void suggest(SuggestListBoxSuggestEvent<ItemValueModel> event) {
                final DimensionCritereRechercheTypeRelationModel criteres = new DimensionCritereRechercheTypeRelationModel();
                criteres.setGroupements(filtreGroupements);
                criteres.setPasDansGroupements(filtrePasDansGroupements);
                criteres.setVisible(true);
                criteres.setLibelle(event.getSuggest());
                criteres.setInverse(true);
                dimensionRpcService.chargerListeRelation(criteres, event.getCallBack());
            }
        });

        // chargement de la valeur sur listbox
        final ItemValueModel itemValue = new ItemValueModel();
        itemValue.setItem(relation.getType().getIdentifiant() + "#" + relation.getType().getLibelle());
        itemValue.setValue(relation.getType().getLibelle());
        bloc.getSuggestListBoxTypeRelationValue().setValue(itemValue);

        // chargement de la date de fin
        if (relation.getDateFin() != null) {
            bloc.getDateFin().setValue(DateUtil.getDate(relation.getDateFin()));
        } else {
            bloc.getDateFin().clean();
        }
        if (relation.isActif()) {
            bloc.afficherStyleRelationEnCours();
        } else {
            bloc.afficherStyleRelationTerminee();
        }

        // chargement du libelle de la personne et du clickhandler de l'image
        final StringBuffer libellePersonne = new StringBuffer();
        ClickHandler chPersonne = null;

        if (relation.getPersonne() instanceof PersonnePhysiqueRelationModel) {
            final PersonnePhysiqueRelationModel personnePhysique = (PersonnePhysiqueRelationModel) relation.getPersonne();

            if (personnePhysique.getCivilite() != null) {
                libellePersonne.append(personnePhysique.getCivilite().getLibelle());
            }
            libellePersonne.append(" ").append(personnePhysique.getNom() != null ? personnePhysique.getNom() : "");
            libellePersonne.append(" ").append(personnePhysique.getPrenom() != null ? personnePhysique.getPrenom() : "");
            if (personnePhysique.getNaturePersonne() != null
                && constantes.getIdNaturePersonneDecede().equals(personnePhysique.getNaturePersonne().getIdentifiant())) {
                libellePersonne.append(" " + presenterConstants.infoPersonneDecede());
            }

            chPersonne = new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    fireEventGlobalBus(new OpenPersonEvent(personnePhysique.getId(), personnePhysique.getNum(),
                       personnePhysique.getNom() != null ? personnePhysique.getNom() : "",
                       personnePhysique.getPrenom() != null ? personnePhysique.getPrenom() : "",
                       (personnePhysique.getNaturePersonne() != null) ? personnePhysique.getNaturePersonne().getIdentifiant() : null));
                }
            };
        } else {
            final PersonneMoraleRelationModel personneMorale = (PersonneMoraleRelationModel) relation.getPersonne();

            libellePersonne.append(personneMorale.getRaisonSociale());

            chPersonne = new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    fireEventGlobalBus(new OpenPersonneMoraleEvent(personneMorale.getId(),
                    (personneMorale.getNaturePersonne() != null) ? personneMorale.getNaturePersonne().getIdentifiant() : null,
                    personneMorale.getRaisonSociale()));
                }
            };
        }

        bloc.getlPersonneCible().setText(libellePersonne.toString());
        bloc.getImageSynthese().addClickHandler(chPersonne);

        // Affichage ou non de l'icone du contrat
        final IconeContratPresenter iconeContratPresenter = new IconeContratPresenter(eventBus, new IconeContratViewImpl(), relation.getIdPersonnePrincipale(),
            relation.getIdPersonne());
        iconeContratPresenter.showPresenter(bloc.getConteneurImageContrat());

        infosModification.add(new InfosModificationLigneRelation(relation, bloc));
        return bloc;
    }

    private void activerRelation(Long idRelation, boolean active) {
        personneRpcService.activerRelation(idRelation, active, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    view.stopAllLoadingPopup();
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(view.getViewConstants().messageErreurModification()));
                } else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(Void result) {
                view.onRpcServiceSuccess();
                initListeRelations();
                final PopupInfoConfiguration config = new PopupInfoConfiguration(presenterConstants.notificationRelationMaj(),
                    AppControllerConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });
    }

    /**
     * Objet qui me permet une association entre une ligne et une relation.
     * @author Goumard Stephane (scub) - SCUB
     */
    private class InfosModificationLigneRelation {
        private RelationModel relation;

        private PersonneRelationsBlocView bloc;

        /**
         * Conctructeur par defaut.
         * @param relation la relation
         * @param bloc le bloc
         */
        public InfosModificationLigneRelation(RelationModel relation, PersonneRelationsBlocView bloc) {
            this.relation = relation;
            this.bloc = bloc;
        }

        /**
         * retourne la valeur.
         * @return the relation
         */
        public RelationModel getRelation() {
            return relation;
        }

        /**
         * retourne la valeur.
         * @return the ligne
         */
        public PersonneRelationsBlocView getBloc() {
            return bloc;
        }
    }

    /**
     * Permet la modification d'une liste de relation d'une personne.
     */
    public void modifierRelations() {
        final List<RelationModel> relations = new ArrayList<RelationModel>();
        for (InfosModificationLigneRelation info : infosModification) {
            final PersonneRelationsBlocView ligne = info.getBloc();
            final RelationModel relation = info.getRelation();
            relation.setDateFin(ligne.getDateFin().getValue() == null ? "" : DateUtil.getString(ligne.getDateFin().getValue()));
            if (ligne.getSuggestListBoxTypeRelationValue().getValue() != null) {
                relation.setAncienType(relation.getType());
                final String[] tokenType = ligne.getSuggestListBoxTypeRelationValue().getValue().getItem().split("#");
                relation.setType(new IdentifiantLibelleGwt(Long.valueOf(tokenType[0]), tokenType[1]));
            }

            relations.add(relation);
        }
        view.afficherLoadingPopup();
        personneRpcService.modifierRelations(relations, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ControleIntegriteExceptionGwt) {
                    view.stopAllLoadingPopup();
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(view.getViewConstants().messageErreurModification()));
                } else {
                    view.onRpcServiceFailure(new ErrorPopupConfiguration(caught));
                }
            }

            @Override
            public void onSuccess(Void result) {
                view.onRpcServiceSuccess();
                initListeRelations();
                final PopupInfoConfiguration config = new PopupInfoConfiguration(presenterConstants.notificationRelationMaj(),
                    AppControllerConstants.NOTIFICATION_TIME_OUT);
                new DecoratedInfoPopup(config).show();
            }
        });
    }

    /**
     * Teste la validité des dates.
     * @return true si les dates sont valides, false sinon
     */
    public boolean isDatesValides() {
        boolean datesValides = true;
        final RapportModel rapport = new RapportModel();
        for (int index = 0; index < infosModification.size(); index++) {
            final PersonneRelationsBlocView ligne = infosModification.get(index).getBloc();
            if (!ligne.getDateFin().isDateValide()) {
                rapport.ajoutRapport(presenterConstants.champDateFinRelation() + index, presenterConstants.erreurDateFinRelationInvalide(), true);
                datesValides = false;
                ligne.getIconeErreurChampManager().fireEvent(new ControleIntegriteExceptionEvent(rapport));
            }
        }
        return datesValides;
    }

    /**
     * Interface pour la vue PersonnePhysiqueCreationView.
     */
    public interface PersonneRelationsView extends View {

        /** Supprime les bloc de relations. */
        void supprimerBlocs();

        /**
         * permet d'ajouter un bloc.
         * @param bloc ?
         */
        void ajouterBlocRelation(PersonneRelationsBlocViewImpl bloc);

        /**
         * Les constantes de la vue.
         * @return .
         */
        PersonneRelationsViewImplConstants getViewConstants();

        /**
         * Afficher la popup de chargement.
         */
        void afficherLoadingPopup();

        /**
         * Notification a la vue reussite d'un appel RPc.
         */
        void onRpcServiceSuccess();

        /**
         * Permet d'arreter les popups de chargements.
         */
        void stopAllLoadingPopup();

        /**
         * Affiche une popUp d'erreur.
         * @param errorPopupConfiguration la configuration de la popup d'erreur
         */
        void onRpcServiceFailure(ErrorPopupConfiguration errorPopupConfiguration);

    }

    /**
     * Interface de la vue des bloc de relations.
     */
    public interface PersonneRelationsBlocView extends View {
        /**
         * Retourne l'image du bloc de relation.
         * @return .
         */
        HasClickHandlers getImageSynthese();

        /**
         * Retourne la gestion des icones d'erreur du bloc.
         * @return the iconeErreurChampManager
         */
        IconeErreurChampManager getIconeErreurChampManager();

        /**
         * Renvoi la valeur de suggestListBoxTypeRelation.
         * @return suggestListBoxTypeRelation
         */
        HasSuggestListBoxHandler<ItemValueModel> getSuggestListBoxTypeRelationHandler();

        /**
         * Renvoi la valeur de dateFin.
         * @return dateFin
         */
        DecoratedCalendrierDateBox getDateFin();

        /**
         * Renvoi le valeur de suggestListBoxTypeRelationValue.
         * @return SuggestListBoxTypeRelationValue
         */
        HasValue<ItemValueModel> getSuggestListBoxTypeRelationValue();

        /**
         * Renvoi la valeur de prenomSource.
         * @return prenomSource
         */
        HasText getlPersonneCible();

        /**
         * Accessaur sur le conteneur de l'image des contrats.
         * @return le conteneur.
         */
        VerticalPanel getConteneurImageContrat();

        /**
         * Récupére la listes des composants d'aides.
         * @return List<AideComposant>
         */
        List<AideComposant> getListAideCompsant();

        /**
         * Récupére la liste des composants d'aides avec demandeEventHandler.
         * @return List<HasDemandeAideEventHandler>
         */
        List<HasDemandeAideEventHandler> getListeDemandeEventHandler();

        /**
         * Récupére la liste des composants d'aides avec demandeEventHandler.
         * @return List<HasUpdateAideEventHandler>
         */
        List<HasUpdateAideEventHandler> getListeUpdateEventHandler();

        /**
         * Affiche le style terminée.
         */
        void afficherStyleRelationTerminee();

        /**
         * Affiche le style terminée.
         */
        void afficherStyleRelationEnCours();

    }

    @Override
    public void onDetach() {
        GWT.log("onDetach " + this);
    }
}
