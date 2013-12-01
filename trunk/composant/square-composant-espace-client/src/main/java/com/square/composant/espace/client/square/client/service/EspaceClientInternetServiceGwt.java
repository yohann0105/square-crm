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
package com.square.composant.espace.client.square.client.service;

import org.scub.foundation.framework.gwt.module.client.exception.GwtRunTimeExceptionGwt;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.square.composant.espace.client.square.client.model.espace.client.EspaceClientInternetModel;

/**
 * Interface synchrone des services GWT pour la classe {@link EspaceClientInternetService}.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
@RemoteServiceRelativePath(value = "handler/espaceClientInternetService")
public interface EspaceClientInternetServiceGwt extends RemoteService {

    /**
     * Permet d'envoyer à une personne son mot de passe permettant l'accès à l'espace client.
     * @param login l'identifiant de connexion de la personne qui souhaite retrouver son mot de passe
     * @param envoyerParMail spécifie si la personne souhaite recevoir le mot de passe par email
     * @param envoyerParSms spécifie si la personne souhaite recevoir le mot de passe par sms
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    void envoyerMotDePassePerdu(String login, boolean envoyerParMail, boolean envoyerParSms) throws GwtRunTimeExceptionGwt;

    /**
     * Récupère l'espace client d'une personne.
     * @param uidPersonne identifiant de la personne
     * @return l'espace client de la personne.
     * @throws GwtRunTimeExceptionGwt Exception levée à l'exécution par GWT.
     */
    EspaceClientInternetModel getEspaceClientInternet(Long uidPersonne) throws GwtRunTimeExceptionGwt;
}
