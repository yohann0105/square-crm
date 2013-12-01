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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.InfosPersonneSyncDto;
import com.square.core.model.dto.PersonneCreationAssureSocialDto;
import com.square.core.model.dto.PersonneCreationVivierDto;
import com.square.core.model.dto.PersonneCriteresRechercheDto;
import com.square.core.model.dto.PersonneDto;
import com.square.core.model.dto.PersonnePhysiqueCopieDto;
import com.square.core.model.dto.PersonnePhysiqueIdCriteresRechercheDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.SquareMappingService;

/**
 * Mock pour le service PersonnePhysiqueService.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {

    private static final String NOM_PERSONNE_MARTIN = "Martin";

	private static final int VINGT_SIX = 26;

	private static final int VINGT_QUATRE = 24;

	private static final int SEIZE = 16;

	private static final int TRENTE_SIX = 36;

	private static final int TRENTE_QUATRE = 34;

	private static final Long VINGT = 20L;

	private SquareMappingService squareMappingService;

    @Override
    public void controlerTelephone(TelephoneDto telephoneDto) {
    }

    @Override
    public PersonneDto creerPersonnePhysique(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, CoordonneesDto coordonnees) {
        return null;
    }

    @Override
    public PersonneDto creerPersonnePhysique(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, AdresseDto adresse, EmailDto email,
        TelephoneDto telephone) {
        return null;
    }

    @Override
    public PersonneDto creerUneCopiePersonne(PersonnePhysiqueCopieDto infosCopie) {
        return null;
    }

    @Override
    public void mettreAJourInfosAdhesion(List<InfosPersonneSyncDto> listeInfos) {
    }

    @Override
    public PersonneDto modifierPersonnePhysique(PersonneDto personne) {
        return null;
    }

    @Override
    public RemotePagingResultsDto<PersonneSimpleDto> rechercherPersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres) {
        final List<PersonneSimpleDto> resultat = new ArrayList<PersonneSimpleDto>();
        if (!"110".equals(criteres.getCriterias().getNumeroClient())) {
            if (criteres.getCriterias().getNumeroClient().equals("01")) {
                final PersonneSimpleDto personne = new PersonneSimpleDto();
                personne.setId(1L);
                resultat.add(personne);
            } else if (criteres.getCriterias().getNumeroClient().equals("07")) {
                final PersonneSimpleDto personne = new PersonneSimpleDto();
                personne.setId(7L);
                resultat.add(personne);
            } else {
                final PersonneSimpleDto personne = new PersonneSimpleDto();
                personne.setId(Long.parseLong(criteres.getCriterias().getNumeroClient()));
                resultat.add(personne);
            }
        }
        final RemotePagingResultsDto<PersonneSimpleDto> resultatsPagines = new RemotePagingResultsDto<PersonneSimpleDto>();
        resultatsPagines.setListResults(resultat);
        resultatsPagines.setTotalResults(resultat.size());
        return resultatsPagines;
    }

    private Calendar getDate(int age, int month, int day) {
        final Calendar date = Calendar.getInstance();
        final int yearAuj = date.get(Calendar.YEAR);
        date.clear();
        date.set(yearAuj - age, month - 1, day);
        return date;
    }

    @Override
    public PersonneDto rechercherPersonneParIdentifiant(Long id) {
        final PersonneDto personneDto = new PersonneDto();
        final IdentifiantLibelleDto statutPersonne = new IdentifiantLibelleDto();
        final IdentifiantLibelleDto civilite = new IdentifiantLibelleDto();
        civilite.setIdentifiant(1L);
        civilite.setLibelle("Monsieur");
        if (id.equals(1L)) {
            personneDto.setIdentifiant(1L);
            personneDto.setNom(NOM_PERSONNE_MARTIN);
            personneDto.setPrenom("Pierre");
            statutPersonne.setIdentifiant(5L);
            statutPersonne.setLibelle("Actif loi madelin");
            personneDto.setStatut(statutPersonne);
            personneDto.setCivilite(civilite);
            personneDto.setNumClient("1015036");
            personneDto.setDateNaissance(getDate(VINGT_SIX, 1, 1));
        } else if (id.equals(2L)) {
            personneDto.setIdentifiant(2L);
            personneDto.setNom(NOM_PERSONNE_MARTIN);
            personneDto.setPrenom("Claire");
            statutPersonne.setIdentifiant(1L);
            statutPersonne.setLibelle("Actif");
            personneDto.setStatut(statutPersonne);
            personneDto.setCivilite(civilite);
            personneDto.setNumClient("1015026");
        } else if (id.equals(3L)) {
            personneDto.setIdentifiant(3L);
            personneDto.setNom(NOM_PERSONNE_MARTIN);
            personneDto.setPrenom("Pascal");
            statutPersonne.setIdentifiant(2L);
            statutPersonne.setLibelle("Retraité");
            personneDto.setStatut(statutPersonne);
            personneDto.setCivilite(civilite);
            personneDto.setNumClient("1015016");
            personneDto.setDateNaissance(getDate(VINGT_QUATRE, 1, 1));
        } else if (id.equals(4L)) {
            personneDto.setIdentifiant(4L);
            personneDto.setNom(NOM_PERSONNE_MARTIN);
            personneDto.setPrenom("Raymond");
            statutPersonne.setIdentifiant(1L);
            statutPersonne.setLibelle("Actif");
            personneDto.setStatut(statutPersonne);
            personneDto.setCivilite(civilite);
            personneDto.setNumClient("1015006");
            personneDto.setDateNaissance(getDate(SEIZE, 1, 1));
        } else if (id.equals(12L)) {
            personneDto.setIdentifiant(12L);
            personneDto.setNom(NOM_PERSONNE_MARTIN);
            personneDto.setPrenom("Guillaume");
            statutPersonne.setIdentifiant(1L);
            statutPersonne.setLibelle("Actif");
            personneDto.setStatut(statutPersonne);
            personneDto.setCivilite(civilite);
            personneDto.setNumClient("1015046");
            personneDto.setDateNaissance(getDate(TRENTE_SIX, 1, 1));
        } else if (id.equals(VINGT)) {
            personneDto.setIdentifiant(VINGT);
            personneDto.setNom("Tulipe");
            personneDto.setPrenom("Françoise");
            statutPersonne.setIdentifiant(1L);
            statutPersonne.setLibelle("Actif");
            personneDto.setStatut(statutPersonne);
            personneDto.setCivilite(civilite);
            personneDto.setNumClient("1015056");
            personneDto.setDateNaissance(getDate(TRENTE_QUATRE, 1, 1));
        }
        return personneDto;
    }

    @Override
    public PersonneSimpleDto rechercherPersonneSimpleParIdentifiant(Long id) {
        final PersonneSimpleDto personne = new PersonneSimpleDto();
        personne.setId(id);
        personne.setNumeroClient("000" + id);
        personne.setCivilite(new IdentifiantLibelleDto(1L, "Madame"));
        personne.setNom("Personne" + id);
        personne.setPrenom("prenom" + id);
        if (id.equals(1L)) {
            personne.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNaturePersonneAdherent()));
        } else if (id.equals(3L)) {
            personne.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNaturePersonneBeneficiaireProspect()));
        } else {
            personne.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNaturePersonneProspect()));
        }
        return personne;
    }

    @Override
    public PersonneDto creerPersonnePhysiqueVivier(PersonneCreationVivierDto vivierDto) {
        return null;
    }

    @Override
    public PersonneDto rechercherPersonneParIdentifiantExterieur(String id) {
        final PersonneDto personne = new PersonneDto();
        final IdentifiantLibelleDto statutPersonne = new IdentifiantLibelleDto();
        final IdentifiantLibelleDto civiliteMonsieur = new IdentifiantLibelleDto();
        civiliteMonsieur.setIdentifiant(1L);
        civiliteMonsieur.setLibelle("Monsieur");
        if (id.equals("wJsQC0000a3VW")) {
            personne.setIdentifiant(1L);
            personne.setNom("GUILLEMETTE");
            personne.setPrenom("Anthony");
            statutPersonne.setIdentifiant(5L);
            statutPersonne.setLibelle("Actif loi madelin");
            personne.setStatut(statutPersonne);
            personne.setCivilite(civiliteMonsieur);
        }
        return personne;
    }

    @Override
    public FichierDto exporterRecherchePersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonneDto creerPersonnePhysiqueAssureSocial(PersonneCreationAssureSocialDto assureSocialDto) {
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
    public void transformerVivierEnProspect(Long uidPersonne) {

    }

    @Override
    public PersonneDto creerPersonnePhysiqueGestionVivier(PersonneDto personneDto, List<BeneficiaireDto> listeBeneficiaire, AdresseDto adresse, EmailDto email,
        TelephoneDto telephone) {
        return null;
    }

    @Override
    public boolean hasMembreFamilleNaturePersonne(Long idPersonne, List<Long> listeIdsNaturesPersonne) {
        return false;
    }

    @Override
    public List<PersonneDto> rechercherPersonneParRequete(String requete) {
        return null;
    }

    @Override
    public void indexerListePersonnesPhysiques(List<Long> listeIdsPersonnesAIndexer) {
    }

	@Override
	public List<Long> rechercherIdPersonneParCriteres(PersonnePhysiqueIdCriteresRechercheDto criteres) {
		return null;
	}

}
