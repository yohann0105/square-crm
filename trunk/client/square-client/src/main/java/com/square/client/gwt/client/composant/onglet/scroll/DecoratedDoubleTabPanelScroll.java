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
package com.square.client.gwt.client.composant.onglet.scroll;

import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Reprise de DecoratedTabPanel pour rajouter une srcollbar dans les onglets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class DecoratedDoubleTabPanelScroll extends DoubleTabPanelScroll {
    private static final String DEFAULT_STYLENAME = "gwt-DecoratedTabPanel";

    /**
     * Constructeur.
     * @param nbOngletsStatiques nombre d'onglets statiques
     * @param width largeur du scrollPanel, en unités CSS (e.g. "10px", "1em")
     */
    public DecoratedDoubleTabPanelScroll(int nbOngletsStatiques, int width) {
        super(nbOngletsStatiques, width);
        setStylePrimaryName(DEFAULT_STYLENAME);
    }

    @Override
    protected SimplePanel createTabTextWrapper() {
        return new ExtendedPanel(ExtendedTabBar.TAB_ROW_STYLES, 1);
    }
}
