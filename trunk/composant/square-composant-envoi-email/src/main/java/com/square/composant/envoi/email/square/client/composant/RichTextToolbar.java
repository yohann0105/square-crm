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
package com.square.composant.envoi.email.square.client.composant;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.square.composant.envoi.email.square.client.presenter.ComposantEnvoiEmailPresenter;

/**
 * RichTextToolBar personnalisée pour le composant d'envoi d'email de square.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class RichTextToolbar extends Composite {

    /**
     * Images.
     */
    public interface Images extends ClientBundle {

        /** {@inheritDoc} */
        @Source("com/square/composant/envoi/email/square/public/themes/default/images/bold.gif")
        ImageResource bold();

        /** {@inheritDoc} */
        @Source("com/square/composant/envoi/email/square/public/themes/default/images/italic.gif")
        ImageResource italic();

        /** {@inheritDoc} */
        @Source("com/square/composant/envoi/email/square/public/themes/default/images/underline.gif")
        ImageResource underline();
    }

    /**
     * This {@link Constants} interface is used to make the toolbar's strings internationalizable.
     */
    public interface Strings extends Constants {

        /** {@inheritDoc} */
        String bold();

        /** {@inheritDoc} */
        String italic();

        /** {@inheritDoc} */
        String underline();

        /** {@inheritDoc} */
        String foreground();

        /** {@inheritDoc} */
        String black();

        /** {@inheritDoc} */
        String blue();

        /** {@inheritDoc} */
        String green();

        /** {@inheritDoc} */
        String red();

        /** {@inheritDoc} */
        String white();

        /** {@inheritDoc} */
        String yellow();
    }

    /**
     * We use an inner EventListener class to avoid exposing event methods on the RichTextToolbar itself.
     */
    private class EventHandler implements ClickHandler, ChangeHandler, KeyUpHandler {

        public void onClick(ClickEvent event) {
            final Object sender = event.getSource();
            if (sender == bold) {
                formater.toggleBold();
            } else if (sender == italic) {
                formater.toggleItalic();
            } else if (sender == underline) {
                formater.toggleUnderline();
            } else if (sender == richText) {
                // We use the RichTextArea's onKeyUp event to update the toolbar status.
                // This will catch any cases where the user moves the cursur using the
                // keyboard, or uses one of the browser's built-in keyboard shortcuts.
                updateStatus();
            }
        }

        public void onChange(ChangeEvent event) {
            final Object sender = event.getSource();
            if (sender == foreColors) {
                formater.setForeColor(foreColors.getValue(foreColors.getSelectedIndex()));
                foreColors.setSelectedIndex(0);
            }
        }

        public void onKeyUp(KeyUpEvent event) {
            final Object sender = event.getSource();
            if (sender == richText) {
                // We use the RichTextArea's onKeyUp event to update the toolbar status.
                // This will catch any cases where the user moves the cursur using the
                // keyboard, or uses one of the browser's built-in keyboard shortcuts.
                updateStatus();
            }
        }
    }

    private Images images = (Images) GWT.create(Images.class);

    private Strings strings = (Strings) GWT.create(Strings.class);

    private EventHandler listener = new EventHandler();

    private RichTextArea richText;

    private RichTextArea.Formatter formater;

    private VerticalPanel outer = new VerticalPanel();

    private HorizontalPanel containerPanel = new HorizontalPanel();

    private HorizontalPanel panel = new HorizontalPanel();

    private ToggleButton bold;

    private ToggleButton italic;

    private ToggleButton underline;

    private ListBox foreColors;

    /**
     * Creates a new toolbar that drives the given rich text area.
     * @param richText the rich text area to be controlled
     */
    public RichTextToolbar(RichTextArea richText) {
        this.richText = richText;
        this.formater = richText.getFormatter();

        containerPanel.add(panel);
        outer.add(containerPanel);
        containerPanel.setWidth("100%");

        initWidget(outer);
        setStyleName("gwt-RichTextToolbar");
        richText.addStyleName(ComposantEnvoiEmailPresenter.RESSOURCES.css().hasRichTextToolbar());

        // ajout des boutons
        addBoutons();
    }

    private void addBoutons() {
        bold = createToggleButton(images.bold(), strings.bold());
        panel.add(bold);
        italic = createToggleButton(images.italic(), strings.italic());
        panel.add(italic);
        underline = createToggleButton(images.underline(), strings.underline());
        panel.add(underline);
        foreColors = createColorList(strings.foreground());
        panel.add(foreColors);

        // We only use these listeners for updating status, so don't hook them up
        // unless at least basic editing is supported.
        richText.addKeyUpHandler(listener);
        richText.addClickHandler(listener);
    }

    private ListBox createColorList(String caption) {
        final ListBox lb = new ListBox();
        lb.addChangeHandler(listener);
        lb.setVisibleItemCount(1);

        lb.addItem(caption);
        lb.addItem(strings.white(), "white");
        lb.addItem(strings.black(), "black");
        lb.addItem(strings.red(), "red");
        lb.addItem(strings.green(), "green");
        lb.addItem(strings.yellow(), "yellow");
        lb.addItem(strings.blue(), "blue");
        return lb;
    }

    /**
     * Create a toggle button.
     * @param img image.
     * @param tip tip.
     * @return the toggle button.
     */
    private ToggleButton createToggleButton(ImageResource img, String tip) {
        final ToggleButton tb = new ToggleButton(new Image(img));
        tb.addClickHandler(listener);
        tb.setTitle(tip);
        return tb;
    }

    /**
     * Updates the status of all the stateful buttons.
     */
    private void updateStatus() {
        bold.setDown(formater.isBold());
        italic.setDown(formater.isItalic());
        underline.setDown(formater.isUnderlined());
    }

    /**
     * Permet d'activer ou désactiver la toolbar.
     * @param enabled flag true ou false
     */
    public void setEnabled(boolean enabled) {
        // on desactive tous les elements désactivables du topPanel
        for (int i = 0; i < panel.getWidgetCount(); i++) {
            final Widget widget = panel.getWidget(i);
            if (widget instanceof FocusWidget) {
                ((FocusWidget) widget).setEnabled(enabled);
            }
        }
    }
}
