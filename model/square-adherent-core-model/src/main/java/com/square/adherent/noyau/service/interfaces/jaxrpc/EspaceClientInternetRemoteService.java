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
package com.square.adherent.noyau.service.interfaces.jaxrpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface.
 *
 */
public interface EspaceClientInternetRemoteService extends Remote {

    /**
     * Service permettant d'encrypter un mot de passe.
     * @param motDePasse le mot de passe non crypté
     * @return le mot de passe ecnrypté
     * @throws RemoteException exception distante.
     */
    String encrypterMotDePasse(String motDePasse) throws RemoteException;

    /**
     * Permet de décrypter un mot de passe encrypté.
     * @param mdpEncrypte le mot de passe encrypté
     * @return le mot de passe décrypté
     * @throws RemoteException exception distante.
     */
    String decrypterMotDePasse(String mdpEncrypte) throws RemoteException;
}
