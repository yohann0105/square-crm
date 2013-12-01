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
package com.square.adherent.noyau.service.jaxrpc;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import com.square.adherent.noyau.service.interfaces.jaxrpc.EspaceClientInternetRemoteService;

/**
 *  Endpoint pour l'export webservice des services espace client internet.
 */
public class EspaceClientInternetEndPoint extends ServletEndpointSupport implements EspaceClientInternetRemoteService {

    private StandardPBEStringEncryptor passwordEncryptor;

    /** {@inheritDoc} */
    @Override
    protected void onInit() throws ServiceException {
        passwordEncryptor = (StandardPBEStringEncryptor) getWebApplicationContext().getBean("passwordEncryptor");
        super.onInit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String decrypterMotDePasse(String mdpEncrypte) throws RemoteException {
        return passwordEncryptor.decrypt(mdpEncrypte);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encrypterMotDePasse(String motDePasse) throws RemoteException {
        return passwordEncryptor.encrypt(motDePasse);
    }

}
