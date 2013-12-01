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
import com.google.gwt.user.client.ui.TabBar;

/**
 * Reprise de DecoratedTabBar pour rajouter une srcollbar dans les onglets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ExtendedTabBar extends TabBar {
    /** Style de la table des lignes. */
    static final String[] TAB_ROW_STYLES = {"tabTop", "tabMiddle"};

    /** Style par défaut. */
    static final String STYLENAME_DEFAULT = "gwt-DecoratedTabBar";

    /**
     * Creates an empty {@link DecoratedTabBar}.
     */
    public ExtendedTabBar() {
        super();
        setStylePrimaryName(STYLENAME_DEFAULT);
    }

    @Override
    protected SimplePanel createTabTextWrapper() {
        final SimplePanel panel = new ExtendedPanel(TAB_ROW_STYLES, 1);
        return panel;
    }
}
