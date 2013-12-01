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

import java.util.List;

import org.scub.foundation.framework.gwt.module.client.util.composants.decorated.DecoratedFocusWidget;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.HasSuggestListBoxHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxComposite;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxSuggestEventHandler;
import org.scub.foundation.framework.gwt.module.client.util.composants.suggestbox.SuggestListBoxComposite.SuggestListBoxCompositeProperties;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.square.composants.graphiques.lib.client.bundle.SquareLibSuggestListBoxCompositeResource;
import com.square.composants.graphiques.lib.client.bundle.SquareLibSuggestListBoxSingleResource;

/**
 * SuggestListBox décorée.
 * @param <TypeResult> le type de l'objet manipulé
 * @author Anthony Guillemette (anthony.guillemette@scub.net) - SCUB
 */
public class DecoratedSuggestListBoxComposite<TypeResult> extends DecoratedFocusWidget implements HasSuggestListBoxHandler<TypeResult>,
        HasValue<List<TypeResult>> {

    private SuggestListBoxComposite<TypeResult> suggestListBox;

    private static final String STYLENAME = "decoratedSuggestListBoxComposite";

    /**
     * Constructeur du composant suggestListBox décoré.
     * @param properties les properties
     */
    public DecoratedSuggestListBoxComposite(SuggestListBoxCompositeProperties<TypeResult> properties) {
        super(STYLENAME);
        suggestListBox =
            new SuggestListBoxComposite<TypeResult>(properties, SquareLibSuggestListBoxCompositeResource.INSTANCE,
                SquareLibSuggestListBoxSingleResource.INSTANCE);
        setComposant(suggestListBox);

    }

    /** {@inheritDoc} */
    public List<TypeResult> getValue() {
        return suggestListBox.getValue();
    }

    @Override
    public HandlerRegistration addSuggestHandler(SuggestListBoxSuggestEventHandler<TypeResult> handler) {
        return suggestListBox.addSuggestHandler(handler);
    }

    @Override
    public void setValue(List<TypeResult> value) {
        suggestListBox.setValue(value);
    }

    @Override
    public void setValue(List<TypeResult> value, boolean fireEvents) {
        suggestListBox.setValue(value, fireEvents);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<TypeResult>> handler) {
        return suggestListBox.addValueChangeHandler(handler);
    }

    /**
     * Permet de lancer la recherche dès l'affichage de la sélection multiple.
     * @param afficher vrai ou faux
     */
    public void afficherResultatsOuverturePopupSelectionMultiple(boolean afficher) {
        suggestListBox.setSuggestOnPopupOpen(afficher);
    }

    @Override
    protected void onEnsureDebugId(String baseID) {
        suggestListBox.ensureDebugId(baseID);
    }

    /**
     * Effacer le contenu du composant.
     */
    public void clean() {
        suggestListBox.clean();
        suggestListBox.setValue(null);
    }

    /**
     * Définit le nombre minimum de caractères à taper avant le lancement de la recherche sur la sélection multiple.
     * @param nbMinCaracteresFireEventSuggestMultiple le nombre minimum de caractères à taper
     */
    public void setNbMinCaracteresFireEventSuggestMultiple(int nbMinCaracteresFireEventSuggestMultiple) {
        suggestListBox.setNbMinCaracteresFireEventSuggestMultiple(nbMinCaracteresFireEventSuggestMultiple);
    }

    /**
     * Définit la hauteur du scrollPanel des résultats et de la sélection pour la sélection multiple.
     * @param height la hauteur
     */
    public void setScrollPanelSuggestMultipleHeight(String height) {
        suggestListBox.setScrollPanelSuggestMultipleHeight(height);
    }

    @Override
    public void setEnabled(boolean enabled) {
        suggestListBox.setEnabled(enabled);
    }

}
