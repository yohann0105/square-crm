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
package com.square.adherent.noyau.service.test.mock;

import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseCreationDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.AdresseSimpleDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneBaseDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonneRelationDto;
import com.square.core.model.dto.RelationCriteresRechercheDto;
import com.square.core.model.dto.RelationDto;
import com.square.core.model.dto.RelationInfosDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Mock pour le service PersonneService.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PersonneServiceImpl implements PersonneService {

    private SquareMappingService squareMappingService;

    @Override
    public RelationDto creerRelation(RelationDto relation) {
        return null;
    }

    @Override
    public void modifierRelation(RelationDto relation) {
    }

    @Override
    public void modifierRelations(List<RelationDto> relations) {
    }

    @Override
    public List<AdresseSimpleDto> rechercherAdresseSimpleParIdPersonne(Long idPersonne) {
        return null;
    }

    @Override
    public CoordonneesDto rechercherCoordonneesParIdPersonne(Long idPersonne) {
        if (idPersonne.equals(1L) || idPersonne.equals(2L) || idPersonne.equals(4L) || idPersonne.equals(6L) || idPersonne.equals(7L)) {
            final CoordonneesDto coordonnees = new CoordonneesDto();
            final EmailDto email = new EmailDto();
            email.setAdresse("test@test.fr");
            email.setNatureEmail(new IdentifiantLibelleDto(squareMappingService.getIdNatureEmailPersonnel()));
            coordonnees.getEmails().add(email);
            return coordonnees;
        } else if (idPersonne.equals(3L)) {
            final CoordonneesDto coordonnees = new CoordonneesDto();
            final EmailDto email = new EmailDto();
            email.setAdresse("");
            email.setNatureEmail(new IdentifiantLibelleDto(squareMappingService.getIdNatureEmailPersonnel()));
            coordonnees.getEmails().add(email);
            return coordonnees;
        }
        return null;
    }

    @Override
    public PersonneBaseDto rechercherPersonneParId(Long id) {
        final PersonneDto personneDto = new PersonneDto();
        personneDto.setIdentifiant(1L);
        personneDto.setNom("Martin");
        personneDto.setPrenom("Pierre");
        return personneDto;
    }

    @Override
    public RelationDto rechercherRelationParId(Long idRelation) {
        return null;
    }

    @Override
    public void supprimerPersonne(Long idPersonne) {
    }

    @Override
    public void transfererPersonneAAgence(Long idPersonne, Long idAgence) {
    }

    @Override
    public void transfererPersonneACommercial(Long idPersonne, Long idCommercial) {
    }

    @Override
    public void transfererRelations(Long idPersonneSource, Long idPersonneCible) {
    }

    @Override
    public int countRelationsParCriteres(RelationCriteresRechercheDto criteres) {
        return 0;
    }

    @Override
    public void mettreAJourCoordonneesExtId(CoordonneesDto coordonnees) {
    }

    @Override
    public RemotePagingResultsDto<RelationInfosDto<? extends PersonneRelationDto>> rechercherRelationsParCritreres(
        RemotePagingCriteriasDto<RelationCriteresRechercheDto> criteres) {
        return null;
    }

    /**
     * Set the squareMappingService value.
     * @param squareMappingService the squareMappingService to set
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    @Override
    public AdresseCreationDto ajouterNouvelleAdresse(Long idPersonne, AdresseDto adresseDto, Boolean impacterFamille) {
        return null;
    }

    @Override
    public List<PersonneDto> rechercherPersonnesParAdresse(Long adresseId) {
        return null;
    }

    @Override
    public List<PersonneDto> rechercherPersonnesParEmail(Long emailId) {
        return null;
    }

    @Override
    public List<PersonneDto> rechercherPersonnesParTelephone(Long telephoneId) {
        return null;
    }

    @Override
    public List<RelationDto> rechercherRelationDoubleSens(RelationCriteresRechercheDto criteres) {
        return null;
    }

	@Override
	public int desactiverRelations(List<Long> idsRelationsADesactiver) {
		return 0;
	}

	@Override
	public List<Long> rechercherIdsRelationsADesactiver(Calendar date,
			int pagination) {
		return null;
	}

    @Override
    public CoordonneesDto creerOuMettreAJourCoordonnees(CoordonneesDto coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        return null;
    }

    @Override
    public EmailDto creerOuMettreAJourEmail(Long idPersonne, EmailDto email, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        return null;
    }

    @Override
    public TelephoneDto creerOuMettreAJourTelephone(Long idPersonne, TelephoneDto telephone, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        return null;
    }
}
