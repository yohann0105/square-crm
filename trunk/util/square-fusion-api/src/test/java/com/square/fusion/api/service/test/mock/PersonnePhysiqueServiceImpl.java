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
package com.square.fusion.api.service.test.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.BeneficiaireDto;
import com.square.core.model.dto.CaisseSimpleDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.DimensionRessourceDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantEidLibelleDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
import com.square.core.model.dto.InfoSanteDto;
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

/**
 * Mock du service Personne Physique (Square).
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {

    /** Personne modifiée. */
    private PersonneDto personneModifiee;

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
        personneModifiee = personne;
        return null;
    }

    @Override
    public RemotePagingResultsDto<PersonneSimpleDto> rechercherPersonneFullTextParCriteres(RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres) {
        return null;
    }

    @Override
    public PersonneDto rechercherPersonneParIdentifiant(Long id) {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        final Long idPersonne4 = 4L;
        final Long idPersonne100 = 100L;
        final Long idPersonne101 = 101L;

        // Personne 4 inexistante
        if (idPersonne4.equals(id)) {
            throw new BusinessException("Personne inexistante");
        }
        else if (idPersonne1.equals(id)) {
            return getPersonne1();
        }
        else if (idPersonne2.equals(id)) {
            return getPersonne2();
        }
        else if (idPersonne100.equals(id)) {
            // Personne inexistante
            throw new BusinessException("Personne inexistante");
        }
        else if (idPersonne101.equals(id)) {
            // Personne inexistante
            return null;
        }
        else {
            final PersonneDto personne = new PersonneDto();
            personne.setIdentifiant(id);
            return personne;
        }
    }

    @Override
    public PersonneSimpleDto rechercherPersonneSimpleParIdentifiant(Long id) {
        return null;
    }

    /**
     * Crée le DTO de la personne 1.
     * @return la personne
     */
    private PersonneDto getPersonne1() {
        final PersonneDto personne = new PersonneDto();
        final Long idPersonne1 = 1L;
        personne.setIdentifiant(idPersonne1);
        personne.setNumClient("NumClient1");
        personne.setInfoSante(new InfoSanteDto());
        personne.getInfoSante().setNro("181101634101712");
        personne.setIdext("idExt1");
        personne.setCivilite(new IdentifiantLibelleDto(1L, "Monsieur"));
        personne.setPrenom("NIcôlas");
        personne.setNom("PELTIER");
        personne.setNomJeuneFille(null);
        personne.setDateNaissance(getCalendarByDate("03/10/1981"));
        personne.setDateDeces(getCalendarByDate("01/01/2005"));
        personne.setNaturePersonne(new IdentifiantLibelleDto(3L, "Adhérent"));
        personne.setDecede(true);
        personne.getInfoSante().setCaisse(new CaisseSimpleDto(1L, "Caisse 1", "1"));
        personne.getInfoSante().setCaisseRegime(new IdentifiantLibelleDto(1L, "Régime 1"));
        personne.setSegment(new IdentifiantLibelleDto(1L, "Individuel"));
        personne.setReseauVente(new IdentifiantLibelleDto(1L, "Interne"));
        personne.setCsp(new IdentifiantLibelleDto(9L, "NC"));
        personne.setNbEnfants(0);
        personne.setSitFam(new IdentifiantLibelleDto(3L, "NC"));
        personne.setProfession(null);
        personne.setStatut(new IdentifiantLibelleDto(1L, "Actif"));
        final DimensionRessourceDto createur = new DimensionRessourceDto();
        createur.setIdentifiant(1L);
        createur.setNom("NomCreateur1");
        createur.setPrenom("PrenomCreateur1");
        personne.setCreateur(createur);
        final DimensionRessourceDto commercial = new DimensionRessourceDto();
        commercial.setIdentifiant(1L);
        commercial.setNom("NomCommercial1");
        commercial.setPrenom("PrenomCommercial1");
        personne.setCommercial(commercial);
        personne.setAgence(new IdentifiantEidLibelleDto(1L, "Agence1"));
        return personne;
    }

    /**
     * Crée le DTO de la personne 2.
     * @return la personne
     */
    private PersonneDto getPersonne2() {
        final PersonneDto personne = new PersonneDto();
        final Long idPersonne2 = 2L;
        personne.setIdentifiant(idPersonne2);
        personne.setNumClient("NumClient2");
        personne.setInfoSante(new InfoSanteDto());
        personne.getInfoSante().setNro("181101634101712");
        personne.setIdext("idExt2");
        personne.setCivilite(new IdentifiantLibelleDto(2L, "Madame"));
        personne.setPrenom("Nicolas");
        personne.setNom("PELTIER");
        personne.setNomJeuneFille(null);
        personne.setDateNaissance(getCalendarByDate("03/10/1981"));
        personne.setNaturePersonne(new IdentifiantLibelleDto(1L, "Prospect"));
        personne.setDecede(false);
        personne.getInfoSante().setCaisse(new CaisseSimpleDto(1L, "Caisse 1", "1"));
        personne.getInfoSante().setCaisseRegime(new IdentifiantLibelleDto(1L, "Régime 1"));
        personne.setSegment(new IdentifiantLibelleDto(1L, "Individuel"));
        personne.setReseauVente(new IdentifiantLibelleDto(1L, "Interne"));
        personne.setCsp(null);
        personne.setNbEnfants(0);
        personne.setSitFam(new IdentifiantLibelleDto(2L, "Marié"));
        personne.setProfession(new IdentifiantLibelleBooleanDto(1L, "NC"));
        personne.setStatut(new IdentifiantLibelleDto(1L, "Actif"));
        final DimensionRessourceDto createur = new DimensionRessourceDto();
        createur.setIdentifiant(1L);
        createur.setNom("NomCreateur1");
        createur.setPrenom("PrenomCreateur1");
        personne.setCreateur(createur);
        final DimensionRessourceDto commercial = new DimensionRessourceDto();
        commercial.setIdentifiant(2L);
        commercial.setNom("NomCommercial2");
        commercial.setPrenom("PrenomCommercial2");
        personne.setCommercial(commercial);
        personne.setAgence(new IdentifiantEidLibelleDto(2L, "Agence2"));
        return personne;
    }

    /**
     * Construit un calendar à partir d'une date de la forme "dd/MM/yyyy".
     * @param date la date
     * @return l'objet Calendar
     */
    private static Calendar getCalendarByDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            return calendar;
        }
        catch (ParseException e) {
            throw new TechnicalException("Erreur lors du parsing de la date " + date);
        }
    }

    /**
     * Récupère la valeur de personneModifiee.
     * @return la valeur de personneModifiee
     */
    public PersonneDto getPersonneModifiee() {
        return personneModifiee;
    }

    @Override
    public void controlerTelephone(TelephoneDto telephoneDto) {
    }

    @Override
    public PersonneDto creerPersonnePhysiqueVivier(PersonneCreationVivierDto vivierDto) {
        return null;
    }

    @Override
    public PersonneDto rechercherPersonneParIdentifiantExterieur(String id) {
        return null;
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

    @Override
    public void transformerVivierEnProspect(Long uidPersonne) {
        return;
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
	public List<Long> rechercherIdPersonneParCriteres(
			PersonnePhysiqueIdCriteresRechercheDto criteres) {
		// TODO Auto-generated method stub
		return null;
	}

}
