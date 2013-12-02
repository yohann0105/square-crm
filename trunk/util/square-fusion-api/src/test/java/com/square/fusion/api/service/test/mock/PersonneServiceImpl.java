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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingResultsDto;

import com.square.core.model.dto.AdresseCreationDto;
import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.AdresseSimpleDto;
import com.square.core.model.dto.CodePostalCommuneDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.IdentifiantLibelleBooleanDto;
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
 * Mock du service Personne (Square).
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonneServiceImpl implements PersonneService {

    private static final String LIBELLE_PAYS_FRANCE = "FRANCE";
    private static final String LIBELLE_NATURE_VOIE_RUE = "Rue";

    /** Flag de transfert de la personne à un commercial. */
    private boolean transfertPersonneACommercialEffectue = false;

    /** Flag de transfert de la personne à une agence. */
    private boolean transfertPersonneAAgenceEffectue = false;

    /** Flag de transfert des relations de la personne. */
    private boolean transfertRelationsEffectue = false;

    /** Flag de suppression de la personne. */
    private boolean suppressionPersonneEffectuee = false;

    /** Coordonnées modifiées. */
    private CoordonneesDto coordonneesModifiees;

    /** Service mapping Square. */
    private SquareMappingService squareMappingService;

    @Override
    public CoordonneesDto creerOuMettreAJourCoordonnees(CoordonneesDto coordonnees, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        coordonneesModifiees = coordonnees;
        return null;
    }

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
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        if (idPersonne1.equals(idPersonne)) {
            return getCoordonneesPersonne1();
        } else if (idPersonne2.equals(idPersonne2)) {
            return getCoordonneesPersonne2();
        }
        final CoordonneesDto coordonneesDto = new CoordonneesDto();
        coordonneesDto.setIdPersonne(idPersonne);
        return coordonneesDto;
    }

    @Override
    public PersonneBaseDto rechercherPersonneParId(Long id) {
        return null;
    }

    @Override
    public RelationDto rechercherRelationParId(Long idRelation) {
        return null;
    }

    @Override
    public void supprimerPersonne(Long idPersonne) {
        final Long idPersonne2 = 2L;
        if (idPersonne2.equals(idPersonne)) {
            suppressionPersonneEffectuee = true;
        }
    }

    @Override
    public void transfererPersonneAAgence(Long idPersonne, Long idAgence) {
        final Long idPersonne1 = 1L;
        final Long idAgence2 = 2L;
        if (idPersonne1.equals(idPersonne) && idAgence2.equals(idAgence)) {
            transfertPersonneAAgenceEffectue = true;
        }
    }

    @Override
    public void transfererPersonneACommercial(Long idPersonne, Long idCommercial) {
        final Long idPersonne1 = 1L;
        final Long idCommercial2 = 2L;
        if (idPersonne1.equals(idPersonne) && idCommercial2.equals(idCommercial)) {
            transfertPersonneACommercialEffectue = true;
        }
    }

    @Override
    public void transfererRelations(Long idPersonneSource, Long idPersonneCible) {
        final Long idPersonne1 = 1L;
        final Long idPersonne2 = 2L;
        if (idPersonne2.equals(idPersonneSource) && idPersonne1.equals(idPersonneCible)) {
            transfertRelationsEffectue = true;
        }
    }

    /**
     * Construit les coordonnées de la personne 1.
     * @return les coordonnées
     */
    private CoordonneesDto getCoordonneesPersonne1() {
        final CoordonneesDto coordonnees = new CoordonneesDto();
        coordonnees.setIdPersonne(1L);

        final CodePostalCommuneDto angouleme = new CodePostalCommuneDto(1L);
        angouleme.setCodePostal(new IdentifiantLibelleDto(1L, "16000"));
        angouleme.setCommune(new IdentifiantLibelleDto(1L, "ANGOULEME"));
        angouleme.setLibelleAcheminement("ANGOULEME");
        final CodePostalCommuneDto stMichel = new CodePostalCommuneDto(2L);
        stMichel.setCodePostal(new IdentifiantLibelleDto(2L, "16470"));
        stMichel.setCommune(new IdentifiantLibelleDto(2L, "SAINT-MICHEL"));
        stMichel.setLibelleAcheminement("SAINT-MICHEL");

        // Adresses
        final List<AdresseDto> listeAdresses = new ArrayList<AdresseDto>();
        // Adresse 4 : principale, en cours
        final AdresseDto adresse4 = new AdresseDto();
        adresse4.setIdentifiant(4L);
        adresse4.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        adresse4.setDateDebut(getCalendarByDate("01/01/2000"));
        adresse4.setNpai(false);
        adresse4.setNumVoie("147");
        adresse4.setTypeVoie(new IdentifiantLibelleDto(1L, LIBELLE_NATURE_VOIE_RUE));
        adresse4.setNomVoie("de Limoges");
        adresse4.setCodePostalCommune(angouleme);
        adresse4.setPays(new IdentifiantLibelleBooleanDto(1L, LIBELLE_PAYS_FRANCE));
        listeAdresses.add(adresse4);
        // Adresse 5 : secondaire, en cours
        final AdresseDto adresse5 = new AdresseDto();
        adresse5.setIdentifiant(5L);
        adresse5.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdresseSecondaire()));
        adresse5.setDateDebut(getCalendarByDate("01/01/2000"));
        adresse5.setNpai(false);
        adresse5.setNumVoie("19");
        adresse5.setTypeVoie(new IdentifiantLibelleDto(1L, LIBELLE_NATURE_VOIE_RUE));
        adresse5.setNomVoie("de la Loge");
        adresse5.setCodePostalCommune(stMichel);
        adresse5.setPays(new IdentifiantLibelleBooleanDto(1L, LIBELLE_PAYS_FRANCE));
        listeAdresses.add(adresse5);
        coordonnees.setAdresses(listeAdresses);

        // Téléphones
        final List<TelephoneDto> listeTelephones = new ArrayList<TelephoneDto>();
        // Tél 4 : nature 1, différent de la source
        final TelephoneDto telephone4 = new TelephoneDto();
        telephone4.setId(4L);
        telephone4.setNature(new IdentifiantLibelleDto(1L, "Nature Téléphone 1"));
        telephone4.setNumero("01 01 01 01 01");
        listeTelephones.add(telephone4);
        // Tél 5 : nature 2, n'existe pas dans la source
        final TelephoneDto telephone5 = new TelephoneDto();
        telephone5.setId(5L);
        telephone5.setNature(new IdentifiantLibelleDto(2L, "Nature Téléphone 2"));
        telephone5.setNumero("02 02 02 02 02");
        listeTelephones.add(telephone5);
        // Tél 6 : nature 4, égal à celui de la source
        final TelephoneDto telephone6 = new TelephoneDto();
        telephone6.setId(6L);
        telephone6.setNature(new IdentifiantLibelleDto(4L, "Nature Téléphone 4"));
        telephone6.setNumero("03 03 03 03 03");
        listeTelephones.add(telephone6);
        coordonnees.setTelephones(listeTelephones);

        // Mails
        final List<EmailDto> listeEmails = new ArrayList<EmailDto>();
        // Mail 4 : nature 1, différent de la source
        final EmailDto email4 = new EmailDto();
        email4.setIdentifiant(4L);
        email4.setNatureEmail(new IdentifiantLibelleDto(1L, "Nature Mail 1"));
        email4.setAdresse("email0101@test.net");
        listeEmails.add(email4);
        // Mail 5 : nature 2, n'existe pas dans la source
        final EmailDto email5 = new EmailDto();
        email5.setIdentifiant(5L);
        email5.setNatureEmail(new IdentifiantLibelleDto(2L, "Nature Mail 2"));
        email5.setAdresse("email0202@test.net");
        listeEmails.add(email5);
        // Mail 6 : nature 4, égal à celui de la source
        final EmailDto email6 = new EmailDto();
        email6.setIdentifiant(6L);
        email6.setNatureEmail(new IdentifiantLibelleDto(4L, "Nature Mail 4"));
        email6.setAdresse("email0303@test.net");
        listeEmails.add(email6);
        coordonnees.setEmails(listeEmails);

        return coordonnees;
    }

    /**
     * Construit les coordonnées de la personne 2.
     * @return les coordonnées
     */
    private CoordonneesDto getCoordonneesPersonne2() {
        final CoordonneesDto coordonnees = new CoordonneesDto();
        coordonnees.setIdPersonne(1L);

        final CodePostalCommuneDto angouleme = new CodePostalCommuneDto(1L);
        angouleme.setCodePostal(new IdentifiantLibelleDto(1L, "16000"));
        angouleme.setCommune(new IdentifiantLibelleDto(1L, "ANGOULEME"));
        angouleme.setLibelleAcheminement("ANGOULEME");
        final CodePostalCommuneDto stMichel = new CodePostalCommuneDto(2L);
        stMichel.setCodePostal(new IdentifiantLibelleDto(2L, "16470"));
        stMichel.setCommune(new IdentifiantLibelleDto(2L, "SAINT-MICHEL"));
        stMichel.setLibelleAcheminement("SAINT-MICHEL");

        // Adresses
        final List<AdresseDto> listeAdresses = new ArrayList<AdresseDto>();
        // Adresse 1 : principale, pas en cours
        final AdresseDto adresse1 = new AdresseDto();
        adresse1.setIdentifiant(1L);
        adresse1.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        adresse1.setDateDebut(getCalendarByDate("01/01/2000"));
        adresse1.setDateFin(getCalendarByDate("01/01/2005"));
        adresse1.setNpai(false);
        adresse1.setNumVoie("15");
        adresse1.setTypeVoie(new IdentifiantLibelleDto(1L, LIBELLE_NATURE_VOIE_RUE));
        adresse1.setNomVoie("de Poitiers");
        adresse1.setCodePostalCommune(angouleme);
        adresse1.setPays(new IdentifiantLibelleBooleanDto(1L, LIBELLE_PAYS_FRANCE));
        listeAdresses.add(adresse1);
        // Adresse 2 : principale, en cours (ville différente)
        final AdresseDto adresse2 = new AdresseDto();
        adresse2.setIdentifiant(2L);
        adresse2.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdressePrincipale()));
        adresse2.setDateDebut(getCalendarByDate("01/01/2003"));
        adresse2.setDateFin(getCalendarByDate("01/01/2100"));
        adresse2.setNpai(false);
        adresse2.setNumVoie("147");
        adresse2.setTypeVoie(new IdentifiantLibelleDto(1L, LIBELLE_NATURE_VOIE_RUE));
        adresse2.setNomVoie("de Limoges");
        adresse2.setCodePostalCommune(angouleme);
        adresse2.setPays(new IdentifiantLibelleBooleanDto(1L, LIBELLE_PAYS_FRANCE));
        listeAdresses.add(adresse2);
        // Adresse 5 : secondaire, en cours
        final AdresseDto adresse3 = new AdresseDto();
        adresse3.setIdentifiant(3L);
        adresse3.setNature(new IdentifiantLibelleDto(squareMappingService.getIdNatureAdresseSecondaire()));
        adresse3.setDateDebut(getCalendarByDate("01/01/2000"));
        adresse3.setNpai(false);
        adresse3.setNumVoie("19");
        adresse3.setTypeVoie(new IdentifiantLibelleDto(1L, LIBELLE_NATURE_VOIE_RUE));
        adresse3.setNomVoie("de la Loge");
        adresse3.setCodePostalCommune(stMichel);
        adresse3.setPays(new IdentifiantLibelleBooleanDto(1L, LIBELLE_PAYS_FRANCE));
        listeAdresses.add(adresse3);
        coordonnees.setAdresses(listeAdresses);

        // Téléphones
        final List<TelephoneDto> listeTelephones = new ArrayList<TelephoneDto>();
        // Tél 1 : nature 1, différent de la cible
        final TelephoneDto telephone1 = new TelephoneDto();
        telephone1.setId(1L);
        telephone1.setNature(new IdentifiantLibelleDto(1L, "Nature Téléphone 1"));
        telephone1.setNumero("01 01 01 02 02");
        listeTelephones.add(telephone1);
        // Tél 2 : nature 3, n'existe pas dans la cible
        final TelephoneDto telephone2 = new TelephoneDto();
        telephone2.setId(2L);
        telephone2.setNature(new IdentifiantLibelleDto(3L, "Nature Téléphone 3"));
        telephone2.setNumero("01 02 03 04 05");
        listeTelephones.add(telephone2);
        // Tél 3 : nature 4, égal à celui de la cible
        final TelephoneDto telephone3 = new TelephoneDto();
        telephone3.setId(3L);
        telephone3.setNature(new IdentifiantLibelleDto(4L, "Nature Téléphone 4"));
        telephone3.setNumero("03 03 03 03 03");
        listeTelephones.add(telephone3);
        coordonnees.setTelephones(listeTelephones);

        // Mails
        final List<EmailDto> listeEmails = new ArrayList<EmailDto>();
        // Mail 1 : nature 1, différent de la cible
        final EmailDto email1 = new EmailDto();
        email1.setIdentifiant(1L);
        email1.setNatureEmail(new IdentifiantLibelleDto(1L, "Nature Mail 1"));
        email1.setAdresse("email0102@test.net");
        listeEmails.add(email1);
        // Mail 2 : nature 3, n'existe pas dans la cible
        final EmailDto email2 = new EmailDto();
        email2.setIdentifiant(2L);
        email2.setNatureEmail(new IdentifiantLibelleDto(3L, "Nature Mail 3"));
        email2.setAdresse("email0105@test.net");
        listeEmails.add(email2);
        // Mail 3 : nature 4, égal à celui de la cible
        final EmailDto email3 = new EmailDto();
        email3.setIdentifiant(3L);
        email3.setNatureEmail(new IdentifiantLibelleDto(4L, "Nature Mail 4"));
        email3.setAdresse("email0303@test.net");
        listeEmails.add(email3);
        coordonnees.setEmails(listeEmails);

        return coordonnees;
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
        } catch (ParseException e) {
            throw new TechnicalException("Erreur lors du parsing de la date " + date);
        }
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Récupère la valeur de transfertPersonneACommercialEffectue.
     * @return la valeur de transfertPersonneACommercialEffectue
     */
    public boolean isTransfertPersonneACommercialEffectue() {
        return transfertPersonneACommercialEffectue;
    }

    /**
     * Récupère la valeur de transfertPersonneAAgenceEffectue.
     * @return la valeur de transfertPersonneAAgenceEffectue
     */
    public boolean isTransfertPersonneAAgenceEffectue() {
        return transfertPersonneAAgenceEffectue;
    }

    /**
     * Récupère la valeur de transfertRelationsEffectue.
     * @return la valeur de transfertRelationsEffectue
     */
    public boolean isTransfertRelationsEffectue() {
        return transfertRelationsEffectue;
    }

    /**
     * Récupère la valeur de suppressionPersonneEffectuee.
     * @return la valeur de suppressionPersonneEffectuee
     */
    public boolean isSuppressionPersonneEffectuee() {
        return suppressionPersonneEffectuee;
    }

    /**
     * Récupère la valeur de coordonneesModifiees.
     * @return la valeur de coordonneesModifiees
     */
    public CoordonneesDto getCoordonneesModifiees() {
        return coordonneesModifiees;
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
    public EmailDto creerOuMettreAJourEmail(Long idPersonne, EmailDto email, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        return null;
    }

    @Override
    public TelephoneDto creerOuMettreAJourTelephone(Long idPersonne, TelephoneDto telephone, Boolean impacterFamille, Boolean forcerDesactivationEservices) {
        return null;
    }

    @Override
    public int desactiverRelations(List<Long> idsRelationsADesactiver) {
        return 0;
    }

    @Override
    public List<Long> rechercherIdsRelationsADesactiver(Calendar date, int pagination) {
        return null;
    }

}
