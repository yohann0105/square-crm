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

import java.util.Iterator;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Accessibility;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;

/**
 * Reprise de TabPanel pour rajouter une srcollbar dans les onglets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class DoubleTabPanelScroll extends Composite implements HasSelectionHandlers<Integer>,
        HasBeforeSelectionHandlers<Integer>, HasWidgets, HasAnimation, IndexedPanel {

    /**
     * This extension of DeckPanel overrides the public mutator methods to prevent external callers from adding to the state of the DeckPanel.
     * <p>
     * Removal of Widgets is supported so that WidgetCollection.WidgetIterator operates as expected.
     * </p>
     * <p>
     * We ensure that the DeckPanel cannot become of of sync with its associated TabBar by delegating all mutations to the TabBar to this implementation of
     * DeckPanel.
     * </p>
     */
    private static class TabbedDeckPanel extends DeckPanel {
        private ExtendedDoubleTabBar tabBar;

        public TabbedDeckPanel(ExtendedDoubleTabBar tabBar) {
            this.tabBar = tabBar;
        }

        @Override
        public void add(Widget w) {
            throw new UnsupportedOperationException("Use TabPanel.add() to alter the DeckPanel");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Use TabPanel.clear() to alter the DeckPanel");
        }

        @Override
        public void insert(Widget w, int beforeIndex) {
            throw new UnsupportedOperationException("Use TabPanel.insert() to alter the DeckPanel");
        }

        @Override
        public boolean remove(Widget w) {
            // Removal of items from the TabBar is delegated to the DeckPanel to ensure consistency
            final int idx = getWidgetIndex(w);
            if (idx != -1) {
                tabBar.removeTabProtected(idx);
                return super.remove(w);
            }

            return false;
        }

        protected void insertProtected(Widget w, String tabText, boolean asHTML, int beforeIndex) {
            // Check to see if the TabPanel already contains the Widget. If so,
            // remove it and see if we need to shift the position to the left.
            final int idx = getWidgetIndex(w);
            int beforeIdx = beforeIndex;
            if (idx != -1) {
                remove(w);
                if (idx < beforeIndex) {
                    beforeIdx--;
                }
            }

            tabBar.insertTabProtected(tabText, asHTML, beforeIdx);
            super.insert(w, beforeIdx);
        }

        protected void insertProtected(Widget w, Widget tabWidget, int beforeIndex) {
            // Check to see if the TabPanel already contains the Widget. If so,
            // remove it and see if we need to shift the position to the left.
            final int idx = getWidgetIndex(w);
            int beforeIdx = beforeIndex;
            if (idx != -1) {
                remove(w);
                if (idx < beforeIdx) {
                    beforeIdx--;
                }
            }

            tabBar.insertTabProtected(tabWidget, beforeIdx);
            super.insert(w, beforeIdx);
        }
    }

    /**
     * This extension of TabPanel overrides the public mutator methods to prevent external callers from modifying the state of the TabBar.
     */
    private class ExtendedDoubleTabBar {
        private TabBar staticTabBar;

        private TabBar dynamicTabBar;

        private int indexLimite;

        public ExtendedDoubleTabBar(TabBar staticTabBar, TabBar dynamicTabBar, int indexLimite) {
            this.staticTabBar = staticTabBar;
            this.dynamicTabBar = dynamicTabBar;
            this.indexLimite = indexLimite;
        }

        public void insertTabProtected(String text, boolean asHTML, int beforeIndex) {
            if (beforeIndex < indexLimite) {
                staticTabBar.insertTab(text, asHTML, beforeIndex);
            } else {
                dynamicTabBar.insertTab(text, asHTML, beforeIndex);
            }
        }

        public void insertTabProtected(Widget widget, int beforeIndex) {
            if (beforeIndex < indexLimite) {
                staticTabBar.insertTab(widget, beforeIndex);
            } else {
                dynamicTabBar.insertTab(widget, beforeIndex - indexLimite);
            }
        }

        public void removeTabProtected(int index) {
            if (index < indexLimite) {
                staticTabBar.removeTab(index);
            } else {
                dynamicTabBar.removeTab(index - indexLimite);
            }
        }

        public void selectTab(int index) {
            if (index < indexLimite) {
                staticTabBar.selectTab(index);
            } else {
                dynamicTabBar.selectTab(index - indexLimite);
            }
        }

        public int getSelectedTab() {
            if (staticTabBar.getSelectedTab() != -1) {
                return staticTabBar.getSelectedTab();
            } else {
                return dynamicTabBar.getSelectedTab() + indexLimite;
            }
        }

    }

    /** Première barre statique. */
    private ExtendedTabBar staticTabBar;

    /** Barre scrollable pour les onglets dynamiques. */
    private ExtendedTabBar dynamicTabBar;

    private int nbOngletsStatiques;

    private ExtendedDoubleTabBar tabBar;

    private TabbedDeckPanel deck;

    private ExtendedScrollPanel scrollPanel;

    /**
     * Creates an empty tab panel.
     * @param nbOngletsStatiques nombre d'onglets statiques
     * @param width largeur du scrollPanel, en unités CSS (e.g. "10px", "1em")
     */
    public DoubleTabPanelScroll(int nbOngletsStatiques, int width) {
        this.nbOngletsStatiques = nbOngletsStatiques;
        dynamicTabBar = new ExtendedTabBar();
        staticTabBar = new ExtendedTabBar();
        tabBar = new ExtendedDoubleTabBar(staticTabBar, dynamicTabBar, nbOngletsStatiques);
        deck = new TabbedDeckPanel(tabBar);
        scrollPanel = new ExtendedScrollPanel(dynamicTabBar, width);
        scrollPanel.addStyleName(SquareResources.INSTANCE.css().scrollPanel());
        final VerticalPanel panel = new VerticalPanel();

        final FlexTable panelTabBars = new FlexTable();
        panelTabBars.getFlexCellFormatter().addStyleName(0, 0, SquareResources.INSTANCE.css().ongletsStatiques());
        panelTabBars.addStyleName(SquareResources.INSTANCE.css().barreOnglets());
        panelTabBars.setWidget(0, 0, staticTabBar);
        panelTabBars.setCellPadding(0);
        panelTabBars.setCellSpacing(0);

        panelTabBars.setWidget(0, 1, scrollPanel);
        panelTabBars.getFlexCellFormatter().addStyleName(0, 1, SquareResources.INSTANCE.css().ongletsDynamiques());
        panelTabBars.getFlexCellFormatter().setHorizontalAlignment(0, 1, HorizontalPanel.ALIGN_LEFT);

        panel.add(panelTabBars);
        panel.add(deck);
        panel.setCellHeight(deck, "100%");

        final int idxDecalage = nbOngletsStatiques;
        dynamicTabBar.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                staticTabBar.selectTab(-1);
                scrollPanel.ensureVisible((UIObject) (dynamicTabBar.getTab(event.getSelectedItem())));
                scrollPanel.refreshScrollButtons();
                deck.showWidget(event.getSelectedItem() + idxDecalage);
                fireSelectionEvent(event.getSelectedItem() + idxDecalage);
            }
        });

        dynamicTabBar.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
            @Override
            public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
                fireBeforeSelectionEvent(event.getItem() + idxDecalage);
            }
        });

        staticTabBar.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                dynamicTabBar.selectTab(-1);
                deck.showWidget(event.getSelectedItem());
                fireSelectionEvent(event.getSelectedItem());
            }
        });
        staticTabBar.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
            @Override
            public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
                fireBeforeSelectionEvent(event.getItem());
            }
        });
        initWidget(panel);
        setStyleName("gwt-TabPanel");
        deck.setStyleName("gwt-TabPanelBottom");
        // Add a11y role "tabpanel"
        Accessibility.setRole(deck.getElement(), Accessibility.ROLE_TABPANEL);
    }

    /**
     * Gets the tab bar within this tab panel.
     * Adding or removing tabs from from the TabBar is not supported and will throw UnsupportedOperationExceptions.
     * @return the tab bar
     */
    public TabBar getStaticTabBar() {
        return staticTabBar;
    }

    /**
     * Gets the tab bar within this tab panel.
     * Adding or removing tabs from from the TabBar is not supported and will throw UnsupportedOperationExceptions.
     * @return the tab bar
     */
    public TabBar getDynamicTabBar() {
        return dynamicTabBar;
    }

    private void fireSelectionEvent(Integer selectedItem) {
        SelectionEvent.fire(this, selectedItem);
    }

    private void fireBeforeSelectionEvent(Integer selectedItem) {
        BeforeSelectionEvent.fire(this, selectedItem);
    }

    /**
     * Ajoute un widget.
     * @param w le widget a ajouter.
     */
    public void add(Widget w) {
        throw new UnsupportedOperationException("A tabText parameter must be specified with add().");
    }

    /**
     * Adds a widget to the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the right-most index.
     * @param w the widget to be added
     * @param tabText the text to be shown on its tab
     */
    public void add(Widget w, String tabText) {
        insert(w, tabText, getWidgetCount());
    }

    /**
     * Adds a widget to the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the right-most index.
     * @param w the widget to be added
     * @param tabText the text to be shown on its tab
     * @param asHTML <code>true</code> to treat the specified text as HTML
     */
    public void add(Widget w, String tabText, boolean asHTML) {
        insert(w, tabText, asHTML, getWidgetCount());
    }

    /**
     * Adds a widget to the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the right-most index.
     * @param w the widget to be added
     * @param tabWidget the widget to be shown in the tab
     */
    public void add(Widget w, Widget tabWidget) {
        insert(w, tabWidget, getWidgetCount());
    }

    /**
     * Ajoute un handler sur "l'avant sélection".
     * @param handler le handler.
     * @return le retour.
     */
    public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
        return addHandler(handler, BeforeSelectionEvent.getType());
    }

    /**
     * Ajoute un handler sur la sélection.
     * @param handler le handler.
     * @return le retour.
     */
    public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
        return addHandler(handler, SelectionEvent.getType());
    }

    /**
     * Nettoye le composant.
     */
    public void clear() {
        while (getWidgetCount() > 0) {
            remove(getWidget(0));
        }
    }

    /**
     * Gets the deck panel within this tab panel. Adding or removing Widgets from the DeckPanel is not supported and will throw UnsupportedOperationExceptions.
     * @return the deck panel
     */
    public DeckPanel getDeckPanel() {
        return deck;
    }

    /**
     * Récupère un widget par son index.
     * @param index l'index du widget.
     * @return le widget.
     */
    public Widget getWidget(int index) {
        return deck.getWidget(index);
    }

    /**
     * Récupère le nombre de widget.
     * @return le nombre de widget.
     */
    public int getWidgetCount() {
        return deck.getWidgetCount();
    }

    /**
     * Recupère l'index d'un widget.
     * @param widget le widget.
     * @return l'index du widget.
     */
    public int getWidgetIndex(Widget widget) {
        return deck.getWidgetIndex(widget);
    }

    /**
     * Inserts a widget into the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the requested index.
     * @param widget the widget to be inserted
     * @param tabText the text to be shown on its tab
     * @param asHTML <code>true</code> to treat the specified text as HTML
     * @param beforeIndex the index before which it will be inserted
     */
    public void insert(Widget widget, String tabText, boolean asHTML, int beforeIndex) {
        // Delegate updates to the TabBar to our DeckPanel implementation
        deck.insertProtected(widget, tabText, asHTML, beforeIndex);
        if (beforeIndex > nbOngletsStatiques) {
            scrollPanel.refreshScrollButtons();
        }
    }

    /**
     * Inserts a widget into the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the requested index.
     * @param widget the widget to be inserted.
     * @param tabWidget the widget to be shown on its tab.
     * @param beforeIndex the index before which it will be inserted.
     */
    public void insert(Widget widget, Widget tabWidget, int beforeIndex) {
        // Delegate updates to the TabBar to our DeckPanel implementation
        deck.insertProtected(widget, tabWidget, beforeIndex);
        if (beforeIndex > nbOngletsStatiques) {
            scrollPanel.refreshScrollButtons();
        }
    }

    /**
     * Inserts a widget into the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the requested index.
     * @param widget the widget to be inserted
     * @param tabText the text to be shown on its tab
     * @param beforeIndex the index before which it will be inserted
     */
    public void insert(Widget widget, String tabText, int beforeIndex) {
        insert(widget, tabText, false, beforeIndex);
        if (beforeIndex > nbOngletsStatiques) {
            scrollPanel.refreshScrollButtons();
        }
    }

    /**
     * Indique si l'animation est autorisée.
     * @return true si l'autorisation est autorisée, false sinon.
     */
    public boolean isAnimationEnabled() {
        return deck.isAnimationEnabled();
    }

    /**
     * Récupère l'itérateur du DeckPanel.
     * @return l'itérateur.
     */
    public Iterator<Widget> iterator() {
        // The Iterator returned by DeckPanel supports removal and will invoke
        // TabbedDeckPanel.remove(), which is an active function.
        return deck.iterator();
    }

    /**
     * Supprime l'élément à l'index spécifié.
     * @param index l'index.
     * @return true si la suppression a bien eût lieu, false si le widget n'est pas présent.
     */
    public boolean remove(int index) {
        final boolean result = deck.remove(index);
        if (index + 1 > nbOngletsStatiques) {
            scrollPanel.refreshScrollButtons();
            scrollPanel.setScrollPosition(0);
        }
        return result;
    }

    /**
     * Supprime l'élément à le widget spécifié.
     * @param widget le widget.
     * @return true si la suppression a bien eût lieu, false si le widget n'est pas présent.
     */
    public boolean remove(Widget widget) {
        final boolean result = deck.remove(widget);
        scrollPanel.refreshScrollButtons();
        scrollPanel.setScrollPosition(0);
        return result;
    }

    /**
     * Programmatically selects the specified tab.
     * @param index the index of the tab to be selected
     */
    public void selectTab(int index) {
        tabBar.selectTab(index);
    }

    /**
     * Programmatically get the selected tab.
     * @return the index
     */
    public int getSelectedTab() {
        return tabBar.getSelectedTab();
    }

    /**
     * Active / Désactive l'animation.
     * @param enable activation / désactivation.
     */
    public void setAnimationEnabled(boolean enable) {
        deck.setAnimationEnabled(enable);
    }

    /**
     * Create a {@link SimplePanel} that will wrap the contents in a tab. Subclasses can use this method to wrap tabs in decorator panels.
     * @return a {@link SimplePanel} to wrap the tab contents, or null to leave tabs unwrapped
     */
    protected SimplePanel createTabTextWrapper() {
        return null;
    }

    /**
     * <b>Affected Elements:</b>
     * <ul>
     * <li>-bar = The tab bar.</li>
     * <li>-bar-tab# = The element containing the content of the tab itself.</li>
     * <li>-bar-tab-wrapper# = The cell containing the tab at the index.</li>
     * <li>-bottom = The panel beneath the tab bar.</li>
     * </ul>
     * @param baseID .
     * @see UIObject#onEnsureDebugId(String)
     */
    @Override
    protected void onEnsureDebugId(String baseID) {
        super.onEnsureDebugId(baseID);
        dynamicTabBar.ensureDebugId(baseID + "-dynbar");
        staticTabBar.ensureDebugId(baseID + "-bar");
        deck.ensureDebugId(baseID + "-bottom");
    }

    /**
     * Getter.
     * @return the scrollPanel
     */
    public ExtendedScrollPanel getScrollPanel() {
        return scrollPanel;
    }

}
