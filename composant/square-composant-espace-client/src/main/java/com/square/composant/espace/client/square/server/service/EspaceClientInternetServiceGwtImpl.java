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
package com.square.composant.espace.client.square.server.service;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.adherent.noyau.service.interfaces.AdherentService;
import com.square.adherent.noyau.service.interfaces.EspaceClientInternetService;
import com.square.composant.espace.client.square.client.model.espace.adherent.InfosOptionsAdherentModel;
import com.square.composant.espace.client.square.client.model.espace.client.EspaceClientInternetModel;
import com.square.composant.espace.client.square.client.service.EspaceClientInternetServiceGwt;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Implémentation serveur des services GWT pour les services de l'espace client.
 * @author Nicolas Prouteau (nicolas.prouteau@scub.net) - SCUB
 */
public class EspaceClientInternetServiceGwtImpl implements EspaceClientInternetServiceGwt {

    private EspaceClientInternetService espaceClientInternetService;

    private MapperDozerBean mapperDozerBean;

    private PersonneService personneService;

    private PersonnePhysiqueService personnePhysiqueService;

    private SquareMappingService squareMappingService;

    private AdherentService adherentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void envoyerMotDePassePerdu(String login, boolean envoyerParMail, boolean envoyerParSms) {
        espaceClientInternetService.envoyerMotDePassePerdu(login, envoyerParMail, envoyerParSms);
    }

    @Override
    public EspaceClientInternetModel getEspaceClientInternet(Long uidPersonne) {
        final EspaceClientInternetModel espaceClientInternetModel =
            mapperDozerBean.map(espaceClientInternetService.getEspaceClientInternet(uidPersonne), EspaceClientInternetModel.class);

        // On récupère les coordonnées de le personne à partir de Square.
        final CoordonneesDto coordonneesAdherent = personneService.rechercherCoordonneesParIdPersonne(uidPersonne);
        if (coordonneesAdherent != null) {
            if (!coordonneesAdherent.getTelephones().isEmpty()) {
                for (TelephoneDto telephone : coordonneesAdherent.getTelephones()) {
                    if (telephone.getNature() != null && squareMappingService.getIdNatureMobilePrive().equals(telephone.getNature().getIdentifiant())) {
                        espaceClientInternetModel.setTelephone(telephone.getNumero());
                        break;
                    }
                }
                if (espaceClientInternetModel.getTelephone() == null || "".equals(espaceClientInternetModel.getTelephone())) {
                    espaceClientInternetModel.setTelephone(coordonneesAdherent.getTelephones().get(0).getNumero());
                }
            }
            if (!coordonneesAdherent.getEmails().isEmpty()) {
                final EmailDto emailAdherent = coordonneesAdherent.getEmails().get(0);
                espaceClientInternetModel.setEmail(emailAdherent.getAdresse());
            }
        }

        // on recupere la personne pour savoir si il s'agit d'un adherent
        final PersonneSimpleDto personneSimple = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(uidPersonne);
        if (personneSimple.getNature().getIdentifiant().equals(squareMappingService.getIdNaturePersonneAdherent())) {
            // on recupere les infos de l'espace adherent
            final InfosOptionsAdherentModel infosOptionsAdherentModel = mapperDozerBean.map(adherentService.getInfosOptionsAdherent(uidPersonne),
                InfosOptionsAdherentModel.class);
            espaceClientInternetModel.setInfosOptionsAdherentModel(infosOptionsAdherentModel);
        }

        return espaceClientInternetModel;
    }

    /**
     * Setter function.
     * @param espaceClientInternetService the espaceClientInternetService to set
     */
    public void setEspaceClientInternetService(EspaceClientInternetService espaceClientInternetService) {
        this.espaceClientInternetService = espaceClientInternetService;
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the personneService value.
     * @param personneService the personneService to set
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }

    /**
     * Set the adherentService value.
     * @param adherentService the adherentService to set
     */
    public void setAdherentService(AdherentService adherentService) {
        this.adherentService = adherentService;
    }

    /**
     * Set the personnePhysiqueService value.
     * @param personnePhysiqueService the personnePhysiqueService to set
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Set the squareMappingService value.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

}
