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

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Reprise DecoratorPanel pour rajouter une srcollbar dans les onglets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ExtendedPanel extends SimplePanel {
    /**
     * The default style name.
     */
    private static final String DEFAULT_STYLENAME = "gwt-DecoratorPanel";

    /**
     * The default styles applied to each row.
     */
    private static final String[] DEFAULT_ROW_STYLENAMES = {"top", "middle", "bottom"};

    /**
     * Create a new row with a specific style name. The row will contain three cells (Left, Center, and Right), each prefixed with the specified style name.
     * This method allows Widgets to reuse the code on a DOM level, without creating a DecoratorPanel Widget.
     * @param styleName the style name
     * @return the new row {@link Element}
     */
    static Element createTR(String styleName) {
        final Element trElem = DOM.createTR();
        setStyleName(trElem, styleName);
        if (LocaleInfo.getCurrentLocale().isRTL()) {
            DOM.appendChild(trElem, createTD(styleName + "Right"));
            DOM.appendChild(trElem, createTD(styleName + "Center"));
            DOM.appendChild(trElem, createTD(styleName + "Left"));
        }
        else {
            DOM.appendChild(trElem, createTD(styleName + "Left"));
            DOM.appendChild(trElem, createTD(styleName + "Center"));
            DOM.appendChild(trElem, createTD(styleName + "Right"));
        }
        return trElem;
    }

    /**
     * Create a new table cell with a specific style name.
     * @param styleName the style name
     * @return the new cell {@link Element}
     */
    private static Element createTD(String styleName) {
        final Element tdElem = DOM.createTD();
        final Element inner = DOM.createDiv();
        DOM.appendChild(tdElem, inner);
        setStyleName(tdElem, styleName);
        setStyleName(inner, styleName + "Inner");
        return tdElem;
    }

    /**
     * The container element at the center of the panel.
     */
    private Element containerElem;

    /**
     * The table body element.
     */
    private Element tbody;

    /**
     * Create a new {@link DecoratorPanel}.
     */
    public ExtendedPanel() {
        this(DEFAULT_ROW_STYLENAMES, 1);
    }

    /**
     * Creates a new panel using the specified style names to apply to each row. Each row will contain three cells (Left, Center, and Right). The Center cell in
     * the containerIndex row will contain the {@link Widget}.
     * @param rowStyles an array of style names to apply to each row
     * @param containerIndex the index of the container row
     */
    ExtendedPanel(String[] rowStyles, int containerIndex) {
        super(DOM.createTable());

        // Add a tbody
        final Element table = getElement();
        tbody = DOM.createTBody();
        DOM.appendChild(table, tbody);
        DOM.setElementPropertyInt(table, "cellSpacing", 0);
        DOM.setElementPropertyInt(table, "cellPadding", 0);

        // Add each row
        for (int i = 0; i < rowStyles.length; i++) {
            final Element row = createTR(rowStyles[i]);
            DOM.appendChild(tbody, row);
            if (i == containerIndex) {
                containerElem = DOM.getFirstChild(DOM.getChild(row, 1));
            }
        }

        // Set the overall style name
        setStyleName(DEFAULT_STYLENAME);
    }

    /**
     * Get a specific Element from the panel.
     * @param row the row index
     * @param cell the cell index
     * @return the Element at the given row and cell
     */
    protected Element getCellElement(int row, int cell) {
        final Element tr = DOM.getChild(tbody, row);
        final Element td = DOM.getChild(tr, cell);
        return DOM.getFirstChild(td);
    }

    @Override
    protected Element getContainerElement() {
        return containerElem;
    }
}
