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
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.client.gwt.client.bundle.SquareResources;

/**
 * TabPanel scrollable.
 * 
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 *
 */
public class SimpleTabPanelScroll extends Composite implements HasBeforeSelectionHandlers<Integer>, HasSelectionHandlers<Integer>, HasWidgets, HasAnimation, IndexedPanel {

    /**
     * This extension of DeckPanel overrides the public mutator methods to prevent
     * external callers from adding to the state of the DeckPanel.
     * <p>
     * Removal of Widgets is supported so that WidgetCollection.WidgetIterator
     * operates as expected.
     * </p>
     * <p>
     * We ensure that the DeckPanel cannot become of of sync with its associated
     * TabBar by delegating all mutations to the TabBar to this implementation of
     * DeckPanel.
     * </p>
     */
    private static class TabbedDeckPanel extends DeckPanel {
      private UnmodifiableTabBar tabBar;

      public TabbedDeckPanel(UnmodifiableTabBar tabBar) {
        this.tabBar = tabBar;
      }

      @Override
      public void add(Widget w) {
        throw new UnsupportedOperationException(
            "Use TabPanel.add() to alter the DeckPanel");
      }

      @Override
      public void clear() {
        throw new UnsupportedOperationException(
            "Use TabPanel.clear() to alter the DeckPanel");
      }

      @Override
      public void insert(Widget w, int beforeIndex) {
        throw new UnsupportedOperationException(
            "Use TabPanel.insert() to alter the DeckPanel");
      }

      @Override
      public boolean remove(Widget w) {
        // Removal of items from the TabBar is delegated to the DeckPanel
        // to ensure consistency
        final int idx = getWidgetIndex(w);
        if (idx != -1) {
          tabBar.removeTabProtected(idx);
          return super.remove(w);
        }

        return false;
      }

      protected void insertProtected(Widget w, String tabText, boolean asHTML,
          int beforeIndex) {

        // Check to see if the TabPanel already contains the Widget. If so,
        // remove it and see if we need to shift the position to the left.
        final int idx = getWidgetIndex(w);
        if (idx != -1) {
          remove(w);
          if (idx < beforeIndex) {
            beforeIndex--;
          }
        }

        tabBar.insertTabProtected(tabText, asHTML, beforeIndex);
        super.insert(w, beforeIndex);
      }

      protected void insertProtected(Widget w, Widget tabWidget, int beforeIndex) {

        // Check to see if the TabPanel already contains the Widget. If so,
        // remove it and see if we need to shift the position to the left.
        final int idx = getWidgetIndex(w);
        if (idx != -1) {
          remove(w);
          if (idx < beforeIndex) {
            beforeIndex--;
          }
        }

        tabBar.insertTabProtected(tabWidget, beforeIndex);
        super.insert(w, beforeIndex);
      }
    }

    /**
     * This extension of TabPanel overrides the public mutator methods to prevent
     * external callers from modifying the state of the TabBar.
     */
    private class UnmodifiableTabBar extends ExtendedTabBar {
      @Override
      public void insertTab(String text, boolean asHTML, int beforeIndex) {
        throw new UnsupportedOperationException(
            "Use TabPanel.insert() to alter the TabBar");
      }

      @Override
      public void insertTab(Widget widget, int beforeIndex) {
        throw new UnsupportedOperationException(
            "Use TabPanel.insert() to alter the TabBar");
      }

      public void insertTabProtected(String text, boolean asHTML, int beforeIndex) {
        super.insertTab(text, asHTML, beforeIndex);
      }

      public void insertTabProtected(Widget widget, int beforeIndex) {
        super.insertTab(widget, beforeIndex);
      }

      @Override
      public void removeTab(int index) {
        // It's possible for removeTab() to function correctly, but it's
        // preferable to have only TabbedDeckPanel.remove() be operable,
        // especially since TabBar does not export an Iterator over its values.
        throw new UnsupportedOperationException(
            "Use TabPanel.remove() to alter the TabBar");
      }

      public void removeTabProtected(int index) {
        super.removeTab(index);
      }

    }

    private UnmodifiableTabBar tabBar;

    private TabbedDeckPanel deck;

    private ExtendedScrollPanel scrollPanel;

    /**
     * Constructeur.
     * @param width largeur du scrollPanel, en unités CSS (e.g. "10px", "1em")
     */
    public SimpleTabPanelScroll(int width) {
        final VerticalPanel panel = new VerticalPanel();
        tabBar = new UnmodifiableTabBar();
        deck = new TabbedDeckPanel(tabBar);
        scrollPanel = new ExtendedScrollPanel(tabBar, width);
        scrollPanel.addStyleName(SquareResources.INSTANCE.css().scrollPanel());
        panel.add(scrollPanel);
        panel.add(deck);

        panel.setCellHeight(deck, "100%");
        tabBar.setWidth("100%");

        tabBar.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                scrollPanel.ensureVisible((UIObject) (tabBar.getTab(event.getSelectedItem())));
                scrollPanel.refreshScrollButtons();
                deck.showWidget(event.getSelectedItem());
                fireSelectionEvent(event.getSelectedItem());
            }
        });

        tabBar.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
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
     * Inserts a widget into the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the requested index.
     * @param widget the widget to be inserted
     * @param tabText the text to be shown on its tab
     * @param asHTML <code>true</code> to treat the specified text as HTML
     * @param beforeIndex the index before which it will be inserted
     */
    public void insert(Widget widget, String tabText, boolean asHTML, int beforeIndex) {
        // Delegate updates to the TabBar to our DeckPanel implementation
        deck.insertProtected(widget, tabText, asHTML, beforeIndex);
        scrollPanel.refreshScrollButtons();
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
        scrollPanel.refreshScrollButtons();
    }

    /**
     * Inserts a widget into the tab panel. If the Widget is already attached to the TabPanel, it will be moved to the requested index.
     * @param widget the widget to be inserted
     * @param tabText the text to be shown on its tab
     * @param beforeIndex the index before which it will be inserted
     */
    public void insert(Widget widget, String tabText, int beforeIndex) {
        insert(widget, tabText, false, beforeIndex);
        scrollPanel.refreshScrollButtons();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        while (getWidgetCount() > 0) {
            remove(getWidget(0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Widget> iterator() {
        return deck.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Widget w) {
        return deck.remove(w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnimationEnabled() {
        return deck.isAnimationEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimationEnabled(boolean enable) {
        deck.setAnimationEnabled(enable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget getWidget(int index) {
        return deck.getWidget(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidgetCount() {
        return deck.getWidgetCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidgetIndex(Widget child) {
        return deck.getWidgetIndex(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(int index) {
        return deck.remove(index);
    }

    /**
     * Accesseur tabBar.
     * @return la tabBar.
     */
    public TabBar getTabBar() {
        return tabBar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
        return addHandler(handler, BeforeSelectionEvent.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
        return addHandler(handler, SelectionEvent.getType());
    }

    /**
     * Rafraichit les boutons du scrollPanel.
     */
    public void refreshScrollButtons() {
        scrollPanel.refreshScrollButtons();
    }

}
