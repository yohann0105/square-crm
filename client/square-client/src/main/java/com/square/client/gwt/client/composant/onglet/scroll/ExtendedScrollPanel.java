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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasScrollHandlers;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;
import com.square.client.gwt.client.bundle.theme.smatis.SmatisResources;

/**
 * ScrollPanel personnalisé pour la gestion des onglets.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ExtendedScrollPanel extends Composite implements HasScrollHandlers {

    private SimplePanel scrollPanel;

    private ExtendedTabBar tabBar;

    private Grid layoutScrollPanel;

    private PushButton btnLeft;

    private PushButton btnRight;

    private PushButton btnLeftDisabled;

    private PushButton btnRightDisabled;

    private int indexOfLastVisibleWidget = 0;

    private Image imgScrollGauche;

    private Image imgScrollGaucheDisabled;

    private Image imgScrollDroite;

    private Image imgScrollDroiteDisabled;

    /**
     * Creates a new scroll panel with the given child widget.
     * @param tabBar the widget to be wrapped by the scroll panel
     * @param width largeur du scrollPanel, en unités CSS (e.g. "10px", "1em")
     */
    public ExtendedScrollPanel(final ExtendedTabBar tabBar, int width) {
        this.tabBar = tabBar;

        imgScrollGauche = new Image(SmatisResources.INSTANCE.iconeOngletScrollGauche());
        imgScrollGaucheDisabled = new Image(SmatisResources.INSTANCE.iconeOngletScrollGaucheDisable());
        imgScrollDroite = new Image(SmatisResources.INSTANCE.iconeOngletScrollDroite());
        imgScrollDroiteDisabled = new Image(SmatisResources.INSTANCE.iconeOngletScrollDroiteDisable());

        scrollPanel = new SimplePanel();
        scrollPanel.add(tabBar);

        btnLeft = new PushButton(imgScrollGauche);
        btnLeft.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                btnRight.setVisible(true);
                btnRightDisabled.setVisible(false);
                final int scrollPosBefore = getHorizontalScrollPosition();
                if (scrollPosBefore > 0) {
                    btnLeft.setVisible(true);
                    btnLeftDisabled.setVisible(false);
                }
                else {
                    btnLeft.setVisible(false);
                    btnLeftDisabled.setVisible(true);
                }
                while (scrollPosBefore != 0 && scrollPosBefore == getHorizontalScrollPosition()) {
                    indexOfLastVisibleWidget = Math.max(0, indexOfLastVisibleWidget - 1);
                    ensureVisible((UIObject) (tabBar.getTab(indexOfLastVisibleWidget)));
                }

                // on grise les boutons apres le déplacement du scroll bar (il se peut qu'il y'ait un problème d'arrondi à un près)
                if (getHorizontalScrollPosition() <= 1) {
                    btnLeft.setVisible(false);
                    btnLeftDisabled.setVisible(true);
                }
            }
        });
        btnLeft.setStylePrimaryName("optionsButton");
        btnLeft.setVisible(false);
        btnLeftDisabled = new PushButton(imgScrollGaucheDisabled);
        btnLeftDisabled.setStylePrimaryName("optionsButton");
        btnLeftDisabled.setVisible(false);

        btnRight = new PushButton(imgScrollDroite);
        btnRight.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                btnLeft.setVisible(true);
                btnLeftDisabled.setVisible(false);
                final int scrollPosBefore = getHorizontalScrollPosition();
                final int scrollMax = tabBar.getOffsetWidth() - scrollPanel.getOffsetWidth();
                if (scrollPosBefore < scrollMax) {
                    btnRight.setVisible(true);
                    btnRightDisabled.setVisible(false);
                }
                else {
                    btnRight.setVisible(false);
                    btnRightDisabled.setVisible(true);
                }
                while (scrollPosBefore != scrollMax && scrollPosBefore == getHorizontalScrollPosition()) {
                    indexOfLastVisibleWidget = Math.min(tabBar.getTabCount() - 1, indexOfLastVisibleWidget + 1);
                    ensureVisible((UIObject) (tabBar.getTab(indexOfLastVisibleWidget)));
                }

                indexOfLastVisibleWidget = Math.min(tabBar.getTabCount() - 1, indexOfLastVisibleWidget + 1);
                ensureVisible((UIObject) (tabBar.getTab(indexOfLastVisibleWidget)));

                // on grise les boutons apres le déplacement du scroll bar (il se peut qu'il y'ait un problème d'arrondi à un près)
                if (getHorizontalScrollPosition() >=  scrollMax - 1) {
                    btnRight.setVisible(false);
                    btnRightDisabled.setVisible(true);
                }
            }
        });
        btnRight.setStylePrimaryName("optionsButton");
        btnRight.setVisible(false);
        btnRightDisabled = new PushButton(imgScrollDroiteDisabled);
        btnRightDisabled.setStylePrimaryName("optionsButton");
        btnRightDisabled.setVisible(false);

        final HorizontalPanel pBtnLeft = new HorizontalPanel();
        pBtnLeft.setSpacing(0);
        pBtnLeft.add(btnLeft);
        pBtnLeft.add(btnLeftDisabled);
        final HorizontalPanel pBtnRight = new HorizontalPanel();
        pBtnRight.setSpacing(0);
        pBtnRight.add(btnRight);
        pBtnRight.add(btnRightDisabled);

        // Layout du composant
        layoutScrollPanel = new Grid(1, 3);
        layoutScrollPanel.setCellPadding(0);
        layoutScrollPanel.setCellSpacing(0);
        layoutScrollPanel.setWidget(0, 0, scrollPanel);
        layoutScrollPanel.setWidget(0, 1, pBtnLeft);
        layoutScrollPanel.setWidget(0, 2, pBtnRight);
        layoutScrollPanel.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_BOTTOM);
        layoutScrollPanel.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_BOTTOM);

        initWidget(layoutScrollPanel);

        setAlwaysShowScrollBars(false);
        // Prevent IE standard mode bug when a AbsolutePanel is contained.
        DOM.setStyleAttribute(scrollPanel.getElement(), "position", "relative");
        this.setWidth(width);
    }

    /**
     * Refresh the scroll buttons after inserting a new tab to the panel.
     */
    void refreshScrollButtons() {
        final int nbTabs = tabBar.getTabCount();
        if (nbTabs > 0) {
            indexOfLastVisibleWidget = nbTabs - 1;
            final int indexSelectedTab = tabBar.getSelectedTab();
            if (indexSelectedTab >= 0 && indexSelectedTab < tabBar.getTabCount()) {
                ensureVisible((UIObject) (tabBar.getTab(tabBar.getSelectedTab())));
            } else {
                ensureVisible((UIObject) (tabBar.getTab(indexOfLastVisibleWidget)));
            }
            if (tabBar.getOffsetWidth() > 0 && tabBar.getOffsetWidth() > scrollPanel.getOffsetWidth()) {
                // La tabBar est trop grande pour être affichée entièrement
                if (indexSelectedTab == 0) {
                    // Si le premier onglet est selectionné, on active la navigation vers la droite
                    btnLeft.setVisible(false);
                    btnRight.setVisible(true);
                    btnLeftDisabled.setVisible(true);
                    btnRightDisabled.setVisible(false);
                    return;
                } else if (indexSelectedTab == tabBar.getTabCount() - 1) {
                    // Si le dernier onglet est selectionné, on active la navigation vers la gauche
                    btnLeft.setVisible(true);
                    btnRight.setVisible(false);
                    btnLeftDisabled.setVisible(false);
                    btnRightDisabled.setVisible(true);
                    return;
                } else {
                	btnLeft.setVisible(true);
                    btnRight.setVisible(true);
                    btnLeftDisabled.setVisible(false);
                    btnRightDisabled.setVisible(false);
                    return;
                }
            }
        }
        // La tabBar a assez d'espace pour afficher les onglets, on cache les boutons de navivation
        btnLeft.setVisible(false);
        btnRight.setVisible(false);
        btnLeftDisabled.setVisible(false);
        btnRightDisabled.setVisible(false);
    }

    /**
     * Ajoute un handler sur le scroll.
     * @param handler le handler à ajouter.
     * @return .
     */
    public HandlerRegistration addScrollHandler(ScrollHandler handler) {
        return addDomHandler(handler, ScrollEvent.getType());
    }

    /**
     * Ensures that the specified item is visible, by adjusting the panel's scroll position.
     * @param item the item whose visibility is to be ensured
     */
    public void ensureVisible(UIObject item) {
        final Element scroll = scrollPanel.getElement();
        final Element element = item.getElement();
        ensureVisibleImpl(scroll, element);
    }

    /**
     * Gets the horizontal scroll position.
     * @return the horizontal scroll position, in pixels
     */
    public int getHorizontalScrollPosition() {
        return DOM.getElementPropertyInt(scrollPanel.getElement(), "scrollLeft");
    }

    /**
     * Gets the vertical scroll position.
     * @return the vertical scroll position, in pixels
     */
    public int getScrollPosition() {
        return DOM.getElementPropertyInt(scrollPanel.getElement(), "scrollTop");
    }

    /**
     * Scroll to the bottom of this panel.
     */
    public void scrollToBottom() {
        setScrollPosition(DOM.getElementPropertyInt(scrollPanel.getElement(), "scrollHeight"));
    }

    /**
     * Scroll to the far left of this panel.
     */
    public void scrollToLeft() {
        setHorizontalScrollPosition(0);
    }

    /**
     * Scroll to the far right of this panel.
     */
    public void scrollToRight() {
        setHorizontalScrollPosition(DOM.getElementPropertyInt(scrollPanel.getElement(), "scrollWidth"));
    }

    /**
     * Scroll to the top of this panel.
     */
    public void scrollToTop() {
        setScrollPosition(0);
    }

    /**
     * Sets whether this panel always shows its scroll bars, or only when necessary.
     * @param alwaysShow <code>true</code> to show scroll bars at all times
     */
    public void setAlwaysShowScrollBars(boolean alwaysShow) {
        DOM.setStyleAttribute(scrollPanel.getElement(), "overflow", alwaysShow ? "scroll" : "hidden");
    }

    /**
     * Sets the horizontal scroll position.
     * @param position the new horizontal scroll position, in pixels
     */
    public void setHorizontalScrollPosition(int position) {
        DOM.setElementPropertyInt(scrollPanel.getElement(), "scrollLeft", position);
    }

    /**
     * Sets the vertical scroll position.
     * @param position the new vertical scroll position, in pixels
     */
    public void setScrollPosition(int position) {
        DOM.setElementPropertyInt(scrollPanel.getElement(), "scrollTop", position);
    }

    /**
     * Sets the object's width. This width does not include decorations such as border, margin, and padding.
     * @param width the object's new width, in pixels units (e.g. "10px")
     */
    public void setWidth(int width) {
        final int intWidth = width - imgScrollGauche.getWidth() - imgScrollDroite.getWidth();
        scrollPanel.setWidth(intWidth + "px");
        layoutScrollPanel.setWidth(width + "px");
    }

    private native void ensureVisibleImpl(Element scroll, Element e) /*-{
                                                                     if (!e)
                                                                     return;

                                                                     var item = e;
                                                                     var realOffset = 0;
                                                                     var realOffsetH = 0;
                                                                     while (item && (item != scroll)) {
                                                                     realOffset += item.offsetTop;
                                                                     realOffsetH += item.offsetLeft;
                                                                     item = item.offsetParent;
                                                                     }

                                                                     scroll.scrollTop = realOffset - scroll.offsetHeight / 2;
                                                                     scroll.scrollLeft = realOffsetH - scroll.offsetWidth / 2;
                                                                     }-*/;

}
