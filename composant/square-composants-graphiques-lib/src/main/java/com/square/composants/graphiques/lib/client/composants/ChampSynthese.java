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
package com.square.composants.graphiques.lib.client.composants;

import java.util.Date;

import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.util.DateUtil;
import org.scub.foundation.framework.gwt.module.client.util.HasStringValue;
import org.scub.foundation.framework.gwt.module.client.util.composants.ExplorableComposite;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasStringValueChangeHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.StringValueChangeEvent;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.StringValueChangeEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.square.composant.aide.gwt.client.AideComposant;
import com.square.composants.graphiques.lib.client.ComposantsGraphiquesConstants;
import com.square.composants.graphiques.lib.client.event.CloseBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.CloseBlocSyntheseEventHandler;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEvent;
import com.square.composants.graphiques.lib.client.event.OpenBlocSyntheseEventHandler;

/**
 * Représente un champ de synthèse qui passe automatiquement éditable lors de l'ouverture du bloc résumé dans lequel il est placé.
 * @author Juanito Goncalves (juanito.goncalves@scub.net) - SCUB
 */
@SuppressWarnings("unchecked")
public class ChampSynthese extends ExplorableComposite implements OpenBlocSyntheseEventHandler, CloseBlocSyntheseEventHandler, ValueChangeHandler,
        SelectionHandler<SuggestOracle.Suggestion>, StringValueChangeEventHandler {

    /** Le champ. */
    private Widget champ;

    /** Le label. */
    private Label label;

    /** Le label en ouverture. */
    private Label labelOuverture;

    /** Le champ sous sa forme lecture seule. */
    private Label champLectureSeule;

    /** Icone pour l'affichage de l'erreur. */
    private IconeErreurChamp icone;

    /** Icone pour l'affichage de l'aide. */
    private AideComposant aide;

    /** On affiche toujours les labels (par défaut, on les masque en mode édition pour gagner de la place. */
    private boolean toujoursAfficherLabels = false;

    /** On affiche les champs que lors du dépliement. */
    private boolean afficherChampDepliement = false;

    /** . Nombre maximum de caractères à afficher pour le champ en lecteur seule **/
    private int nbMaxCaracteres = -1; // -1 par défaut affichera tous les caractères

    /** Le champ Synthese avec lequelle on veut fusionner dans une même case. */
    private ChampSynthese fusionnerAvec;

    private ComposantsGraphiquesConstants constants;
    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, icone, null, toujoursAfficherLabels, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels, boolean afficherChampDepliement,
        ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, icone, null, toujoursAfficherLabels, afficherChampDepliement, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels,
        ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, null, toujoursAfficherLabels, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, null, toujoursAfficherLabels, afficherChampDepliement, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Widget widget, String libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels) {
        this(widget, new Label(libelleLabel), icone, toujoursAfficherLabels);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Widget widget, String libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this(widget, new Label(libelleLabel), icone, toujoursAfficherLabels, afficherChampDepliement);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Widget widget, String libelleLabel, String libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels) {
        this(widget, new Label(libelleLabel), new Label(libelleLabelOuverture), icone, toujoursAfficherLabels);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Widget widget, String libelleLabel, String libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement) {
        this(widget, new Label(libelleLabel), new Label(libelleLabelOuverture), icone, toujoursAfficherLabels, afficherChampDepliement);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param nbMaxCaracteres nombre maximum de caractères qui seront affichés pour le champs en lecture seule
     */
    public ChampSynthese(Widget widget, String libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels, int nbMaxCaracteres) {
        this(widget, new Label(libelleLabel), icone, null, toujoursAfficherLabels, nbMaxCaracteres, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param nbMaxCaracteres nombre maximum de caractères qui seront affichés pour le champs en lecture seule
     */
    public ChampSynthese(Widget widget, String libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels, boolean afficherChampDepliement,
        int nbMaxCaracteres) {
        this(widget, new Label(libelleLabel), icone, null, toujoursAfficherLabels, afficherChampDepliement, nbMaxCaracteres, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param nbMaxCaracteres nombre maximum de caractères qui seront affichés pour le champs en lecture seule
     */
    public ChampSynthese(Widget widget, String libelleLabel, String libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels,
        int nbMaxCaracteres) {
        this(widget, new Label(libelleLabel), new Label(libelleLabelOuverture), icone, null, toujoursAfficherLabels, nbMaxCaracteres, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param nbMaxCaracteres nombre maximum de caractères qui seront affichés pour le champs en lecture seule
     */
    public ChampSynthese(Widget widget, String libelleLabel, String libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement, int nbMaxCaracteres) {
        this(widget, new Label(libelleLabel), new Label(libelleLabelOuverture), icone, null, toujoursAfficherLabels, afficherChampDepliement, nbMaxCaracteres,
            null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels) {
        this(widget, libelleLabel, icone, null, toujoursAfficherLabels, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this(widget, libelleLabel, icone, null, toujoursAfficherLabels, afficherChampDepliement, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, null, toujoursAfficherLabels, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, null, toujoursAfficherLabels, afficherChampDepliement, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param nbMaxCaracteres le nombre maximal de caractères
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, AideComposant aide, boolean toujoursAfficherLabels, int nbMaxCaracteres) {
        this(widget, libelleLabel, icone, aide, toujoursAfficherLabels, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param nbMaxCaracteres le nombre maximal de caractères
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, AideComposant aide, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement, int nbMaxCaracteres) {
        this(widget, libelleLabel, icone, aide, toujoursAfficherLabels, afficherChampDepliement, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param nbMaxCaracteres le nombre maximal de caractères
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, AideComposant aide,
        boolean toujoursAfficherLabels, int nbMaxCaracteres) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, aide, toujoursAfficherLabels, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param nbMaxCaracteres le nombre maximal de caractères
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, AideComposant aide,
        boolean toujoursAfficherLabels, boolean afficherChampDepliement, int nbMaxCaracteres) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, aide, toujoursAfficherLabels, afficherChampDepliement, -1, null);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, AideComposant aide, boolean toujoursAfficherLabels,
        ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, icone, aide, toujoursAfficherLabels, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, AideComposant aide, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, icone, aide, toujoursAfficherLabels, afficherChampDepliement, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, AideComposant aide,
        boolean toujoursAfficherLabels, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, aide, toujoursAfficherLabels, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, AideComposant aide,
        boolean toujoursAfficherLabels, boolean afficherChampDepliement, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, aide, toujoursAfficherLabels, afficherChampDepliement, -1, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param nbMaxCaracteres le nombre maximal de caractères
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, AideComposant aide, boolean toujoursAfficherLabels, int nbMaxCaracteres,
        ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, null, icone, aide, toujoursAfficherLabels, nbMaxCaracteres, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param nbMaxCaracteres le nombre maximal de caractères
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, IconeErreurChamp icone, AideComposant aide, boolean toujoursAfficherLabels,
        boolean afficherChampDepliement, int nbMaxCaracteres, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, null, icone, aide, toujoursAfficherLabels, afficherChampDepliement, nbMaxCaracteres, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param nbMaxCaracteres le nombre maximal de caractères
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, AideComposant aide,
        boolean toujoursAfficherLabels, int nbMaxCaracteres, ChampSynthese fusionnerAvec) {
        this(widget, libelleLabel, libelleLabelOuverture, icone, aide, toujoursAfficherLabels, false, nbMaxCaracteres, fusionnerAvec);
    }

    /**
     * Constructeur par défaut.
     * @param widget le champ pour l'édition
     * @param libelleLabel la valeur du label
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param icone icone indiquant les éventuelles erreurs
     * @param aide aide indiquant la nature du champs
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     * @param nbMaxCaracteres le nombre maximal de caractères
     * @param fusionnerAvec Le champ Synthese avec lequelle on veut fusionner une case
     */
    public ChampSynthese(Widget widget, Label libelleLabel, Label libelleLabelOuverture, IconeErreurChamp icone, AideComposant aide,
        boolean toujoursAfficherLabels, boolean afficherChampDepliement, int nbMaxCaracteres, ChampSynthese fusionnerAvec) {
        this.nbMaxCaracteres = nbMaxCaracteres;
        this.champ = widget;
        this.champ.setVisible(false);
        this.champLectureSeule = new Label();
        this.champLectureSeule.setVisible(true);
        this.label = libelleLabel;
        this.labelOuverture = libelleLabelOuverture;
        this.fusionnerAvec = fusionnerAvec;
        this.label.setWordWrap(false);
        if (this.labelOuverture != null) {
            this.labelOuverture.setWordWrap(false);
        }

        this.icone = icone;
        this.aide = aide;
        this.toujoursAfficherLabels = toujoursAfficherLabels;
        this.afficherChampDepliement = afficherChampDepliement;
        this.constants = GWT.create(ComposantsGraphiquesConstants.class);

        if (champ instanceof HorizontalPanel) {
            initChamp(((HorizontalPanel) champ).getWidget(0));
        } else {
            initChamp(champ);
        }

        construireWidget();
    }

    /**
     * initialise le champ.
     * @param champWidget le champ.
     */
    private void initChamp(Widget champWidget) {
        // Initialise le champ selon le type.
        if (champWidget instanceof HasSelectionHandlers<?>) {
            ((HasSelectionHandlers<SuggestOracle.Suggestion>) champWidget).addSelectionHandler(this);
        }
        if (champWidget instanceof HasValueChangeHandlers<?>) {
            ((HasValueChangeHandlers<String>) champWidget).addValueChangeHandler(this);
        }
        if (champWidget instanceof HasStringValueChangeHandler) {
            ((HasStringValueChangeHandler) champWidget).addStringValueChangeEventHandler(this);
        }

        // Initialisation de la valeur du label avec la valeur actuelle du champ
        if (champWidget instanceof CheckBox) {
            this.champLectureSeule.setText(Boolean.TRUE.equals(((CheckBox) champWidget).getValue()) ? constants.oui() : constants.non());
        }
        else if (champWidget instanceof HasText) {
            this.champLectureSeule.setText(((HasText) champWidget).getText());
        }
        else if (champWidget instanceof HasStringValue) {
            this.champLectureSeule.setText(((HasStringValue) champWidget).getStringValue());
        }
        else if (champWidget instanceof HasValue) {
            if (((HasValue) champWidget).getValue() != null) {
                this.champLectureSeule.setText(((HasValue) champWidget).getValue().toString());
            }
        }
    }

    /**
     * Constructeur par défaut.
     * @param libelleLabel le libellé
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(String libelleLabel, boolean toujoursAfficherLabels) {
        this("", libelleLabel, toujoursAfficherLabels);
    }

    /**
     * Constructeur par défaut.
     * @param libelleLabel le libellé
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(String libelleLabel, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this("", libelleLabel, toujoursAfficherLabels, afficherChampDepliement);
    }

    /**
     * Constructeur par défaut.
     * @param libelleLabel le libellé
     * @param libelleLabelOuverture le libellé en ouverture
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(String libelleLabel, String libelleLabelOuverture, boolean toujoursAfficherLabels) {
        this(null, libelleLabel, libelleLabelOuverture, toujoursAfficherLabels);
    }

    /**
     * Constructeur par défaut.
     * @param libelleLabel le libellé
     * @param libelleLabelOuverture le libellé en ouverture
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(String libelleLabel, String libelleLabelOuverture, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this(null, libelleLabel, libelleLabelOuverture, toujoursAfficherLabels, afficherChampDepliement);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     */
    public ChampSynthese(Label label, String libelleLabel) {
        this(label, libelleLabel, false, false);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabelOuverture le libellé en ouverture
     * @param libelleLabel le libellé
     */
    public ChampSynthese(Label label, String libelleLabel, String libelleLabelOuverture) {
        this(label, libelleLabel, libelleLabelOuverture, false, false);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Label label, String libelleLabel, boolean toujoursAfficherLabels) {
        this(label, new Label(libelleLabel), toujoursAfficherLabels);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Label label, String libelleLabel, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this(label, new Label(libelleLabel), toujoursAfficherLabels, afficherChampDepliement);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param libelleLabelOuverture le libellé en ouverture
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Label label, String libelleLabel, String libelleLabelOuverture, boolean toujoursAfficherLabels) {
        this(label, new Label(libelleLabel), new Label(libelleLabelOuverture), toujoursAfficherLabels);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param libelleLabelOuverture le libellé en ouverture
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Label label, String libelleLabel, String libelleLabelOuverture, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this(label, new Label(libelleLabel), new Label(libelleLabelOuverture), toujoursAfficherLabels, afficherChampDepliement);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Label label, Label libelleLabel, boolean toujoursAfficherLabels) {
        this(label, libelleLabel, toujoursAfficherLabels, false);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Label label, Label libelleLabel, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this.label = libelleLabel;
        this.label.setWordWrap(false);
        if (label == null) {
            this.champLectureSeule = this.label;
        } else {
            this.champLectureSeule = label;
            this.champLectureSeule.setWordWrap(true);
        }
        this.toujoursAfficherLabels = toujoursAfficherLabels;
        this.afficherChampDepliement = afficherChampDepliement;
        construireWidget();
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     */
    public ChampSynthese(Label label, Label libelleLabel, Label libelleLabelOuverture, boolean toujoursAfficherLabels) {
        this(label, libelleLabel, libelleLabelOuverture, toujoursAfficherLabels, false);
    }

    /**
     * Constructeur par défaut.
     * @param label le label
     * @param libelleLabel le libellé
     * @param libelleLabelOuverture la valeur du label en ouverture
     * @param toujoursAfficherLabels indique si l'on doit afficher les labels en permanence
     * @param afficherChampDepliement n'affiche le champ qu'au dépliement
     */
    public ChampSynthese(Label label, Label libelleLabel, Label libelleLabelOuverture, boolean toujoursAfficherLabels, boolean afficherChampDepliement) {
        this.label = libelleLabel;
        this.labelOuverture = libelleLabelOuverture;
        this.label.setWordWrap(false);
        if (this.labelOuverture != null) {
            this.labelOuverture.setWordWrap(false);
        }
        if (label == null) {
            this.champLectureSeule = this.label;
        } else {
            this.champLectureSeule = label;
            this.champLectureSeule.setWordWrap(true);
        }
        this.toujoursAfficherLabels = toujoursAfficherLabels;
        this.afficherChampDepliement = afficherChampDepliement;
        construireWidget();
    }

    /** Construction du composant. */
    private void construireWidget() {
        final HorizontalPanel bloc = new HorizontalPanel();
        bloc.setSpacing(3);
        bloc.setStyleName("enteteChamp");

        if (this.champ != null && champ instanceof CheckBox) {
            bloc.add(this.champ);
            if (!"".equals(this.label.getText())) {
                bloc.add(this.label);
                if (this.labelOuverture != null) {
                    bloc.add(this.labelOuverture);
                    labelOuverture.setVisible(false);
                }
            }
            bloc.setCellVerticalAlignment(this.champ, HasAlignment.ALIGN_MIDDLE);
        } else {
            if (!"".equals(this.label.getText())) {
                bloc.add(this.label);
                if (this.labelOuverture != null) {
                    bloc.add(this.labelOuverture);
                    labelOuverture.setVisible(false);
                }
            }
        }

        bloc.add(this.champLectureSeule);

        if (this.champ != null && !(champ instanceof CheckBox)) {
            bloc.add(this.champ);
            bloc.setCellVerticalAlignment(this.champ, HasAlignment.ALIGN_MIDDLE);
        }

        if (this.icone != null) {
            bloc.add(icone);
            icone.setVisible(false);
            bloc.setCellVerticalAlignment(icone, HasAlignment.ALIGN_MIDDLE);
        }

        if (this.aide != null) {
            bloc.add(aide);
            aide.setVisible(false);
            bloc.setCellVerticalAlignment(aide, HasAlignment.ALIGN_MIDDLE);
        }

        bloc.setCellVerticalAlignment(this.label, HasAlignment.ALIGN_MIDDLE);
        if (this.labelOuverture != null) {
            bloc.setCellVerticalAlignment(this.labelOuverture, HasAlignment.ALIGN_MIDDLE);
        }
        bloc.setCellVerticalAlignment(this.champLectureSeule, HasAlignment.ALIGN_MIDDLE);
        bloc.setVisible(!afficherChampDepliement);
        this.initWidget(bloc);
    }

    @Override
    public void onOpen(OpenBlocSyntheseEvent event) {
        if (this.champ != null) {
            this.champLectureSeule.setVisible(false);
            this.champ.setVisible(true);
            if (this.icone != null) {
                this.icone.setVisible(true);
            }
            if (this.aide != null) {
                this.aide.setVisible(true);
            }
        }
        if (!toujoursAfficherLabels) {
            // On masque les labels, car non utiles en mode édition
            this.label.setVisible(false);
            if (this.labelOuverture != null) {
                this.labelOuverture.setVisible(false);
            }
            this.champLectureSeule.setVisible(false);
        } else if (labelOuverture != null && labelOuverture.getText() != null && !"".equals(labelOuverture.getText())) {
            labelOuverture.setVisible(true);
            label.setVisible(false);
        }

        setVisible(true);
    }

    @Override
    public void onClose(CloseBlocSyntheseEvent event) {
        this.label.setVisible(true);
        this.champLectureSeule.setVisible(true);

        if (this.champ != null) {
            this.champ.setVisible(false);
            if (this.icone != null) {
                this.icone.setVisible(false);
            }
            if (this.aide != null) {
                this.aide.setVisible(false);
            }
        }

        if (this.labelOuverture != null) {
            labelOuverture.setVisible(false);
        }
        label.setVisible(true);

        setVisible(!afficherChampDepliement);
    }

    @Override
    public void onValueChange(ValueChangeEvent event) {
        if (event.getValue() instanceof String) {
            this.champLectureSeule.setText(event.getValue().toString());
        } else if (event.getValue() instanceof Date) {
            this.champLectureSeule.setText(DateUtil.getString((Date) event.getValue()));
        } else if (event.getValue() instanceof Boolean) {
            this.champLectureSeule.setText(Boolean.TRUE.equals(event.getValue()) ? constants.oui() : constants.non());
        } else if (event.getValue() instanceof IdentifiantLibelleGwt) {
            this.champLectureSeule.setText(((IdentifiantLibelleGwt) event.getValue()).getLibelle());
        } else if (event.getSource() instanceof HasStringValue) {
            this.champLectureSeule.setText(((HasStringValue) event.getSource()).getStringValue());
        } else if (event.getSource() instanceof HasValue) {
            if (((HasValue) event.getSource()).getValue() != null) {
                this.champLectureSeule.setText(((HasValue) event.getSource()).getValue().toString());
            }
        }

        if (nbMaxCaracteres != -1 && champLectureSeule.getText().length() > nbMaxCaracteres) { // limite si superieur a la constante
            // limite le nombre de caractères
            final String text = champLectureSeule.getText();
            String textLimite = text;

            textLimite = textLimite.substring(0, nbMaxCaracteres) + "...";

            champLectureSeule.setText(textLimite);
            champLectureSeule.setTitle(text); // affiche le texte complet
        }
    }

    /**
     * Retourne le champ à fusionner.
     * @return le champ a fusionner.
     */
    public ChampSynthese getChampFusionner() {
        return fusionnerAvec;
    }

    @Override
    public void onSelection(SelectionEvent<Suggestion> event) {
        this.champLectureSeule.setText(event.getSelectedItem().getDisplayString());
    }

    @Override
    public void onStringValueChange(StringValueChangeEvent event) {
        this.champLectureSeule.setText(event.getStringValue());
    }

}
