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
package com.square.adherent.noyau.service.implementations;

import java.util.List;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.square.adherent.noyau.dao.interfaces.EspaceClientInternetDao;
import com.square.adherent.noyau.dto.adherent.connexion.IdentifiantsConnexionDto;
import com.square.adherent.noyau.model.data.espace.client.EspaceClientInternet;
import com.square.adherent.noyau.service.interfaces.AdherentMappingService;
import com.square.adherent.noyau.service.interfaces.UserDetailAdherentSmatisService;
import com.square.adherent.noyau.util.MessageKeyUtil;

/**
 * Implémentation du service d'authentification des adhérents SMATIS.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class UserDetailAdherentSmatisServiceImpl implements UserDetailAdherentSmatisService {

    /** Logger. */
    private final Logger logger = Logger.getLogger(this.getClass());

    private EspaceClientInternetDao espaceClientInternetDao;

    private AdherentMappingService adherentMappingService;

    @SuppressWarnings("unused")
	private StandardPBEStringEncryptor passwordEncryptor;

    /** MessageSourceUtil. */
    private MessageSourceUtil messageSourceUtil;

    /**
     * Rôle associé aux adhérents.
     */
    private String roleAdherent;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        final String erreurAdherentNonTrouve = messageSourceUtil.get(MessageKeyUtil.ERROR_AUCUN_ADHERENT_TROUVE,
        		new String[] {username});
        logger.info(messageSourceUtil.get(MessageKeyUtil.LOGGER_INFO_DEMANDE_INFO_ADHERENT_AVEC_LOGIN,
        		new String[] {username}));

        final Long idNatureConnexionEspaceClient = adherentMappingService.getIdNatureConnexionEspaceClient();

        final IdentifiantsConnexionDto identifiants = new IdentifiantsConnexionDto();
        identifiants.setLogin(username.trim());
        List<EspaceClientInternet> listeConnexions = espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants,
            idNatureConnexionEspaceClient, true);

        // Si on ne trouve pas de connexion et le numéro de client commence par 0, on réessaye sans tenir compte du 0.
        if (listeConnexions.size() == 0 && identifiants.getLogin().trim().startsWith("0")) {
            final String numeroClientSansZero = identifiants.getLogin().trim().substring(1);
            identifiants.setLogin(numeroClientSansZero);
            listeConnexions = espaceClientInternetDao.getListeEspaceClientInternetsByIdentifiantsAndNature(identifiants, idNatureConnexionEspaceClient, true);
        }

        if (listeConnexions.size() != 1) {
            logger.fatal(erreurAdherentNonTrouve);
            throw new UsernameNotFoundException(erreurAdherentNonTrouve);
        }
        final EspaceClientInternet infosConnexionAdherent = listeConnexions.get(0);
        final GrantedAuthority[] authorities = new GrantedAuthority[] {new GrantedAuthorityImpl(roleAdherent)};

        return new User(infosConnexionAdherent.getUidPersonne().toString(), infosConnexionAdherent.getMotDePasse(),
            infosConnexionAdherent.isActive(), true, true, true, authorities);
    }

    /**
     * Setter function.
     * @param roleAdherent the roleAdherent to set
     */
    public void setRoleAdherent(String roleAdherent) {
        this.roleAdherent = roleAdherent;
    }

    /**
     * Setter function.
     * @param passwordEncryptor the passwordEncryptor to set
     */
    public void setPasswordEncryptor(StandardPBEStringEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    /**
     * Setter function.
     * @param espaceClientInternetDao the espaceClientInternetDao to set
     */
    public void setEspaceClientInternetDao(EspaceClientInternetDao espaceClientInternetDao) {
        this.espaceClientInternetDao = espaceClientInternetDao;
    }

    /**
     * Définit la valeur de adherentMappingService.
     * @param adherentMappingService la nouvelle valeur de adherentMappingService
     */
    public void setAdherentMappingService(AdherentMappingService adherentMappingService) {
        this.adherentMappingService = adherentMappingService;
    }

}
