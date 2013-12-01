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
package com.square.composant.emails.square.server.service;

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;
import org.scub.foundation.framework.core.model.dto.SearchUtilDto;
import org.scub.foundation.framework.gwt.module.client.dto.IdentifiantLibelleGwt;
import org.scub.foundation.framework.gwt.module.client.pagination.RemotePagingResultsGwt;
import org.springframework.security.context.SecurityContextHolder;

import com.square.composant.emails.square.client.model.DestinataireRechercheResultatsModel;
import com.square.composant.emails.square.client.model.EmailModel;
import com.square.composant.emails.square.client.model.GroupeEmailModel;
import com.square.composant.emails.square.client.model.RechercheEmailRequeteModel;
import com.square.composant.emails.square.client.model.RechercheEmailResultatModel;
import com.square.composant.emails.square.client.model.UtilisateurRechercheQueryModel;
import com.square.composant.emails.square.client.service.EmailServiceGWT;
import com.square.mail.core.dto.gestionmails.CritereRechercheNombreEmailDto;
import com.square.mail.core.dto.gestionmails.EmailDto;
import com.square.mail.core.dto.gestionmails.GroupeEmailDto;
import com.square.mail.core.dto.gestionmails.RechercheDestinataireQueryDto;
import com.square.mail.core.dto.gestionmails.RechercheDestinataireResultatsDto;
import com.square.mail.core.dto.gestionmails.RechercheEmailRequeteDto;
import com.square.mail.core.dto.gestionmails.RechercheEmailResultatDto;
import com.square.mail.core.service.interfaces.gestionmails.EmailMappingService;
import com.square.mail.core.service.interfaces.gestionmails.EmailService;

/**
 * Implémentation du service EmailServiceGWT.
 * @author nnadeau - SCUB
 */
@SuppressWarnings("deprecation")
public class EmailServiceGWTImpl implements EmailServiceGWT {

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    /** Service des emails. */
    private EmailService emailService;

    /** Service des emails. */
    private EmailMappingService emailMappingService;

    /**
     * {@inheritDoc}
     */
    public List<IdentifiantLibelleGwt> getFiltresServices() {
        final List<IdentifiantLibelleDto> listeServicesDTO = emailService.getFiltresServices();
        return mapperDozerBean.mapList(listeServicesDTO, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<IdentifiantLibelleGwt> getFiltresUtilisateurs(Long idService) {
        final List<IdentifiantLibelleDto> listeUtilisateursDTO = emailService.getFiltresUtilisateurs(idService);
        return mapperDozerBean.mapList(listeUtilisateursDTO, IdentifiantLibelleGwt.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RemotePagingResultsGwt<RechercheEmailResultatModel> rechercherEmailsParCriteres(RechercheEmailRequeteModel criterias) {

        // Mapping des critères de recherche
        final SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> criteresDTO =
            new SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto>();
        final RechercheEmailRequeteDto critereDTO = mapperDozerBean.map(criterias, RechercheEmailRequeteDto.class);
        criteresDTO.setCriterionDto(critereDTO);
        criteresDTO.setFirstResult(criterias.getPagination().getFirstResult());
        criteresDTO.setMaxResult(criterias.getPagination().getMaxResult());
        critereDTO.setOrdreAscendant(true);
        critereDTO.setOrdreColonne("email.dateEnvoi");
        critereDTO.setLoginUtilisateurConnecte(SecurityContextHolder.getContext().getAuthentication().getName());
        critereDTO.setIsRechercheFullText(false);

        // Lancement de la recherche
        final SearchUtilDto<RechercheEmailRequeteDto, RechercheEmailResultatDto> resultatsDTO = emailService.rechercherEmailsParCriteres(criteresDTO);

        // Mapping des résultats
        final List<RechercheEmailResultatModel> listeResultatsGWT = mapperDozerBean.mapList(resultatsDTO.getResults(), RechercheEmailResultatModel.class);

        // Création de l'objet resultat
        final RemotePagingResultsGwt<RechercheEmailResultatModel> results = new RemotePagingResultsGwt<RechercheEmailResultatModel>();
        results.setListResults(listeResultatsGWT);
        results.setTotalResults(resultatsDTO.getTotalSearchResult().intValue());
        return results;
    }

    /**
     * {@inheritDoc}
     */
    public GroupeEmailModel getGroupeEmailParEmail(Long idEmail) {
        final GroupeEmailDto groupeEmailDto = emailService.getGroupeEmailParEmail(idEmail);
        final GroupeEmailModel groupeEmailModel = mapperDozerBean.map(groupeEmailDto, GroupeEmailModel.class);
        return groupeEmailModel;
    }

    /**
     * {@inheritDoc}
     */
    public void changerEtatEmail(Long idEmail, Long idEtat) {
        emailService.changerEtatEmail(idEmail, idEtat);
    }

    /**
     * {@inheritDoc}
     */
    public void changerEtatEmailsByGroupeEmail(Long idGroupeEmail, Long idEtat) {
        emailService.changerEtatEmailsByGroupeEmail(idGroupeEmail, idEtat);
    }

    /**
     * {@inheritDoc}
     */
    public void verrouillerEmailsByGroupeEmail(Long idGroupeEmail, Boolean verrouiller) {
        emailService.verrouillerEmailsByGroupeEmail(idGroupeEmail, verrouiller);
    }

    /**
     * {@inheritDoc}
     */
    public EmailModel envoyerEmail(EmailModel email) {
        EmailDto emailDto = (EmailDto) mapperDozerBean.map(email, EmailDto.class);
        emailDto = emailService.envoyerEmail(emailDto);
        return (EmailModel) mapperDozerBean.map(emailDto, EmailModel.class);
    }

    /**
     * {@inheritDoc}
     */
    public EmailModel envoyerEmailEtArchiver(EmailModel email, Long idGroupeEmail) {
        // on mappe et envoie la mail
        EmailDto emailDto = (EmailDto) mapperDozerBean.map(email, EmailDto.class);
        emailDto = emailService.envoyerEmail(emailDto);
        // on archive le groupe lié au mail
        emailService.changerEtatEmailsByGroupeEmail(idGroupeEmail, emailMappingService.getIdEtatArchive());
        return (EmailModel) mapperDozerBean.map(emailDto, EmailModel.class);
    }

    /**
     * {@inheritDoc}
     */
    public void transfererGroupe(List<Long> listeGroupes, Long idUtilisateurDestination) {
        emailService.transfererGroupe(listeGroupes, idUtilisateurDestination);
    }

    /**
     * {@inheritDoc}
     */
    public DestinataireRechercheResultatsModel rechercherEmailsByNomOrEmail(UtilisateurRechercheQueryModel query) {
        // Mapping des critères de recherche
        final RechercheDestinataireQueryDto crietereDto = mapperDozerBean.map(query, RechercheDestinataireQueryDto.class);

        // Appel du service
        final RechercheDestinataireResultatsDto resultDto = emailService.rechercherEmailsByNomOrEmail(crietereDto);

        // Mapping du résultat
        final DestinataireRechercheResultatsModel resultGwt = mapperDozerBean.map(resultDto, DestinataireRechercheResultatsModel.class);
        return resultGwt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNombreTotalEmails() {
        return emailService.getNombreTotalEmails();
    }

    /**
     * Set the mapperDozerBean value.
     * @param mapperDozerBean the mapperDozerBean to set
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Set the emailService value.
     * @param emailService the emailService to set
     */
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Set the emailMappingService value.
     * @param emailMappingService the emailMappingService to set
     */
    public void setEmailMappingService(EmailMappingService emailMappingService) {
        this.emailMappingService = emailMappingService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNombreEmailsAdherent(String numeroAdherent) {
        final CritereRechercheNombreEmailDto critere = new CritereRechercheNombreEmailDto();
        critere.setNumeroAdherent(numeroAdherent);
        return emailService.getNombreEmailsByCriteres(critere);
    }

}
