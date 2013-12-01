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
package com.square.composants.graphiques.lib.client.event;

import java.util.HashSet;
import java.util.Set;

import org.scub.foundation.framework.gwt.module.client.util.popup.error.ErrorPopup;
import org.scub.foundation.framework.gwt.module.client.util.popup.util.ErrorPopupConfiguration;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Widget;
import com.square.composants.graphiques.lib.client.composants.IconeErreurChamp;
import com.square.composants.graphiques.lib.client.composants.model.SousRapportModel;

/**
 * Permet de gérer une liste IconeErreurChamp.
 * 
 * @author Goumard Stephane (Scub) - SCUB
 */
public class IconeErreurChampManager extends HandlerManager {
	/** La liste des IconeErreurChamp gérée par ce manager. */
	private Set<IconeErreurChamp> icones;

	/** Constructeur. */
	public IconeErreurChampManager() {
		super(null);
		icones = new HashSet<IconeErreurChamp>();
	}

	/**
	 * Permet de demander une nouvelle instance d'IconeManager.
	 * 
	 * @param nomChamp
	 *            le nom de champ de mapping.
	 * @param composant
	 *            le composant associé.
	 * @return une instance.
	 */
	public IconeErreurChamp createInstance(final String nomChamp, HasHandlers composant) {
		final IconeErreurChamp icone = new IconeErreurChamp(nomChamp, composant);
		this.addHandler(ControleIntegriteExceptionEvent.TYPE, icone);
		icones.add(icone);
		return icone;
	}

	@Override
	public void fireEvent(final GwtEvent<?> event) {
		super.fireEvent(event);

		DeferredCommand.addCommand(new Command() {
			public void execute() {
				// On vérifie que l'une des icônes d'erreur a été rendue visible
				boolean erreurRemontee = false;
				for (IconeErreurChamp icone : icones) {
					boolean erreur = icone.isErreur();
					Widget parent = icone;
					while (parent != null) {
						erreur &= parent.isVisible();
						parent = parent.getParent();
					}
					erreurRemontee |= erreur;
				}
				// si ça n'est pas le cas on affiche l'erreur dans un popup
				if (event instanceof ControleIntegriteExceptionEvent) {
					final ControleIntegriteExceptionEvent ciee = (ControleIntegriteExceptionEvent) event;
					// on affiche la popup dans tous les cas isForcerPopupErreur = true
					if (!erreurRemontee || (ciee.isForcerPopupErreur() != null && ciee.isForcerPopupErreur())) {
						final Set<String> messages = new HashSet<String>();
						final StringBuilder messageSb = new StringBuilder();
						for (SousRapportModel sousRapport : ciee.getRapport().getRapports().values()) {
							if (sousRapport.getMessage() != null && !sousRapport.getMessage().isEmpty()) {
								messages.add(sousRapport.getMessage());
							}
						}
						if (messages.size() > 0) {
							final String listeAPuce = messages.size() > 1 ? "&bull; " : "";
							for (String message : messages) {
								messageSb.append(listeAPuce).append(message).append("<br/>");
							}
							ErrorPopup.afficher(new ErrorPopupConfiguration(messageSb.toString()));
						}
					}
				}
			}
		});
	}
}
