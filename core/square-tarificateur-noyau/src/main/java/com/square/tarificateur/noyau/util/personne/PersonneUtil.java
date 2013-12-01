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
package com.square.tarificateur.noyau.util.personne;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scub.foundation.framework.base.mapping.util.MapperDozerBean;

import com.square.core.model.dto.AdresseDto;
import com.square.core.model.dto.CoordonneesDto;
import com.square.core.model.dto.EmailDto;
import com.square.core.model.dto.PersonneSimpleDto;
import com.square.core.model.dto.TelephoneDto;
import com.square.core.service.interfaces.PersonnePhysiqueService;
import com.square.core.service.interfaces.PersonneService;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.tarificateur.noyau.bean.personne.InfosPersonneSquareBean;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireDto;
import com.square.tarificateur.noyau.dto.personne.BeneficiaireTarificateurDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.model.personne.Beneficiaire;
import com.square.tarificateur.noyau.model.personne.Personne;

/**
 * Classe utilitaire pour le mapping des personnes.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class PersonneUtil {

    /** Service de mapping Square. */
    private SquareMappingService squareMappingService;

    /** Service PersonnePhysique. */
    private PersonnePhysiqueService personnePhysiqueService;

    /** Service Personne. */
    private PersonneService personneService;

    /** MapperDozerBean. */
    private MapperDozerBean mapperDozerBean;

    /**
     * Recherche l'adresse principale en cours parmi une liste d'adresses.
     * @param listeAdresses la liste d'adresses
     * @return l'adresse, null si pas trouvée
     */
    public AdresseDto rechercherAdressePrincipaleEnCours(List<AdresseDto> listeAdresses) {
        final Long idNatureAdressePrincipale = squareMappingService.getIdNatureAdressePrincipale();
        final Calendar aujourdhui = Calendar.getInstance();
        // Parcours de la liste d'adresses
        if (listeAdresses != null) {
            for (AdresseDto adresse : listeAdresses) {
                // Si c'est une adresse principale
                if (adresse.getNature() != null && idNatureAdressePrincipale.equals(adresse.getNature().getIdentifiant())) {
                    // Test si l'adresse est active
                    if (adresse.getDateDebut().before(aujourdhui) && (adresse.getDateFin() == null || adresse.getDateFin().after(aujourdhui))) {
                        return adresse;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Recherche l'adresse secondaire en cours parmi une liste d'adresses.
     * @param listeAdresses la liste d'adresses
     * @return l'adresse, null si pas trouvée
     */
    public AdresseDto rechercherAdresseSecondaireEnCours(List<AdresseDto> listeAdresses) {
        final Long idNatureAdresseSecondaire = squareMappingService.getIdNatureAdresseSecondaire();
        final Calendar aujourdhui = Calendar.getInstance();
        // Parcours de la liste d'adresses
        if (listeAdresses != null) {
            for (AdresseDto adresse : listeAdresses) {
                // Si c'est une adresse principale
                if (adresse.getNature() != null && idNatureAdresseSecondaire.equals(adresse.getNature().getIdentifiant())) {
                    // Test si l'adresse est active
                    if (adresse.getDateDebut().before(aujourdhui) && (adresse.getDateFin() == null || adresse.getDateFin().after(aujourdhui))) {
                        return adresse;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Recherche un mail de la nature demandée dans une liste de mails.
     * @param listeEmails la liste de mails
     * @param idNature l'identifiant de la nature du mail
     * @return le mail recherché, null si pas trouvé
     */
    public EmailDto rechercherEmailParNature(List<EmailDto> listeEmails, Long idNature) {
        if (listeEmails != null) {
            // Parcours de la liste des mails
            for (EmailDto email : listeEmails) {
                if (email.getNatureEmail() != null && idNature.equals(email.getNatureEmail().getIdentifiant())) {
                    return email;
                }
            }
        }
        return null;
    }

    /**
     * Recherche un téléphone de la nature demandée dans une liste de téléphones.
     * @param listeTelephones la liste de téléphones
     * @param idNature l'identifiant de la nature du téléphone
     * @return le téléphone recherché, null si pas trouvé
     */
    public TelephoneDto rechercherTelephoneParNature(List<TelephoneDto> listeTelephones, Long idNature) {
        if (listeTelephones != null) {
            // Parcours de la liste des téléphones
            for (TelephoneDto telephone : listeTelephones) {
                if (telephone.getNature() != null && idNature.equals(telephone.getNature().getIdentifiant())) {
                    return telephone;
                }
            }
        }
        return null;
    }

    /**
     * Mappe une Personne en PersonneDto.
     * @param personne la personne
     * @param infosPersonnes le cache des infos de personnes
     * @return le DTO PersonneDto
     */
    public PersonneDto mapperPersonneEnPersonneDto(Personne personne, InfosPersonneSquareBean infosPersonnes) {
        // Récupération de la personne square
        final com.square.core.model.dto.PersonneDto personneSquare = getPersonnePhysique(personne.getEidPersonne(), infosPersonnes);
        // Mapping PersonneDto Square --> PersonneDto Tarificateur
        final PersonneDto personneDto = mapperDozerBean.map(personneSquare, PersonneDto.class);
        // Mapping Modèle Personne --> PersonneDto Tarificateur (pour écraser les infos par celles du tarificateur)
        mapperDozerBean.map(personne, personneDto);

        // Remplissage des coordonnées
        remplirCoordonneesPersonneDto(personne.getEidPersonne(), personneDto, infosPersonnes);

        // Mapping Modèle Adresse --> AdresseDto Tarificateur (pour écraser les infos par celles du tarificateur)
        if (personne.getAdressePrincipale() != null) {
            if (personneDto.getAdressePrincipale() == null) {
                personneDto.setAdressePrincipale(new com.square.tarificateur.noyau.dto.personne.AdresseDto());
            }
            mapperDozerBean.map(personne.getAdressePrincipale(), personneDto.getAdressePrincipale());
        }

        // Bénéficiaires
        if (personne.getListeBeneficiaires() != null) {
            final Map<Long, BeneficiaireDto> mapBeneficiaires = new HashMap<Long, BeneficiaireDto>();
            for (Beneficiaire beneficiaire : personne.getListeBeneficiaires()) {
                final PersonneDto beneficiairePersonneDto = mapperPersonneEnPersonneDto(beneficiaire.getPersonneCible(), infosPersonnes);
                final BeneficiaireDto beneficiaireDto = mapperDozerBean.map(beneficiairePersonneDto, BeneficiaireDto.class);
                beneficiaireDto.setIdLienFamilial(beneficiaire.getLienFamilial().getId());
                // il peut arriver qu'un bénéficiaire apparaisse plusieurs fois dans les relations de la personne, on ne le met qu'une fois
                mapBeneficiaires.put(beneficiaireDto.getEidPersonne(), beneficiaireDto);
            }
            personneDto.setListeBeneficiaires(new ArrayList<BeneficiaireDto>(mapBeneficiaires.values()));
        }

        return personneDto;
    }

    /**
     * Mappe une PersonneTarificateurDto en PersonneDto.
     * @param personneTarificateurDto le DTO PersonneTarificateurDto
     * @param infosPersonnes le cache des infos de personnes
     * @return le DTO PersonneDto
     */
    public PersonneDto mapperPersonneTarificateurEnPersonneDto(PersonneTarificateurDto personneTarificateurDto, InfosPersonneSquareBean infosPersonnes) {
        // Récupération de la personne square
        final com.square.core.model.dto.PersonneDto personneSquare = getPersonnePhysique(personneTarificateurDto.getEidPersonne(), infosPersonnes);
        // Mapping PersonneDto Square --> PersonneDto Tarificateur
        final PersonneDto personneDto = mapperDozerBean.map(personneSquare, PersonneDto.class);
        // Mapping Modèle Personne --> PersonneDto Tarificateur (pour écraser les infos par celles du tarificateur)
        mapperDozerBean.map(personneTarificateurDto, personneDto);

        // Remplissage des coordonnées
        remplirCoordonneesPersonneDto(personneTarificateurDto.getEidPersonne(), personneDto, infosPersonnes);

        // Mapping Modèle Adresse --> AdresseDto Tarificateur (pour écraser les infos par celles du tarificateur)
        if (personneTarificateurDto.getAdressePrincipale() != null) {
            if (personneDto.getAdressePrincipale() == null) {
                personneDto.setAdressePrincipale(new com.square.tarificateur.noyau.dto.personne.AdresseDto());
            }
            mapperDozerBean.map(personneTarificateurDto.getAdressePrincipale(), personneDto.getAdressePrincipale());
        }

        // Bénéficiaires
        if (personneTarificateurDto.getListeBeneficiaires() != null) {
            final List<BeneficiaireDto> listeBeneficiaires = new ArrayList<BeneficiaireDto>();
            for (BeneficiaireTarificateurDto beneficiaire : personneTarificateurDto.getListeBeneficiaires()) {
                final PersonneDto beneficiairePersonneDto = mapperPersonneTarificateurEnPersonneDto(beneficiaire, infosPersonnes);
                final BeneficiaireDto beneficiaireDto = mapperDozerBean.map(beneficiairePersonneDto, BeneficiaireDto.class);
                beneficiaireDto.setIdLienFamilial(beneficiaire.getIdLienFamilial());
                listeBeneficiaires.add(beneficiaireDto);
            }
            personneDto.setListeBeneficiaires(listeBeneficiaires);
        }

        return personneDto;
    }

    /**
     * Remplit les coordonnées du DTO de la personne à partir de l'identifiant Square de la personne.
     * @param eidPersonne l'identifiant Square de la personne
     * @param infosPersonnes le cache des infos de personnes
     * @param personneDto le DTO de la personne à remplir
     */
    private void remplirCoordonneesPersonneDto(Long eidPersonne, PersonneDto personneDto, InfosPersonneSquareBean infosPersonnes) {
        // Recherche des coordonnées de la personne
        final CoordonneesDto coordonneesDto = getCoordonnees(eidPersonne, infosPersonnes);

        // Adresse principale
        com.square.tarificateur.noyau.dto.personne.AdresseDto adressePrincipaleDto = null;
        // Récupération de l'adresse Square
        final AdresseDto adressePrincipaleSquare = rechercherAdressePrincipaleEnCours(coordonneesDto.getAdresses());
        if (adressePrincipaleSquare != null) {
            // Mapping Adresse Square --> AdresseDto Tarificateur
            adressePrincipaleDto = mapperDozerBean.map(adressePrincipaleSquare, com.square.tarificateur.noyau.dto.personne.AdresseDto.class);
        }
        personneDto.setAdressePrincipale(adressePrincipaleDto);

        // Adresse secondaire
        // Récupération de l'adresse Square
        final AdresseDto adresseSecondaireSquare = rechercherAdresseSecondaireEnCours(coordonneesDto.getAdresses());
        if (adresseSecondaireSquare != null) {
            // Mapping Adresse Square --> AdresseDto Tarificateur
            mapperDozerBean.map(adresseSecondaireSquare, com.square.tarificateur.noyau.dto.personne.AdresseDto.class);
        }
        personneDto.setAdressePrincipale(adressePrincipaleDto);

        // Email
        final Long idNatureEmailPersonnel = squareMappingService.getIdNatureEmailPersonnel();
        final EmailDto emailSquare = rechercherEmailParNature(coordonneesDto.getEmails(), idNatureEmailPersonnel);
        if (emailSquare != null) {
            final com.square.tarificateur.noyau.dto.personne.EmailDto emailDto =
                mapperDozerBean.map(emailSquare, com.square.tarificateur.noyau.dto.personne.EmailDto.class);
            personneDto.setEmail(emailDto);
        }

        // Téléphone fixe
        final Long idNatureTelephoneFixe = squareMappingService.getIdNatureTelephoneFixe();
        final TelephoneDto telephoneFixeSquare = rechercherTelephoneParNature(coordonneesDto.getTelephones(), idNatureTelephoneFixe);
        if (telephoneFixeSquare != null) {
            final com.square.tarificateur.noyau.dto.personne.TelephoneDto telephoneDto =
                mapperDozerBean.map(telephoneFixeSquare, com.square.tarificateur.noyau.dto.personne.TelephoneDto.class);
            personneDto.setTelephoneFixe(telephoneDto);
        }

        // Téléphone portable
        final Long idNatureMobilePrive = squareMappingService.getIdNatureMobilePrive();
        final TelephoneDto telephonePortableSquare = rechercherTelephoneParNature(coordonneesDto.getTelephones(), idNatureMobilePrive);
        if (telephonePortableSquare != null) {
            final com.square.tarificateur.noyau.dto.personne.TelephoneDto telephoneDto =
                mapperDozerBean.map(telephonePortableSquare, com.square.tarificateur.noyau.dto.personne.TelephoneDto.class);
            personneDto.setTelephonePortable(telephoneDto);
        }

        // Téléphone bureau
        final Long idNatureMobileTravail = squareMappingService.getIdNatureMobileTravail();
        final TelephoneDto telephoneBureauSquare = rechercherTelephoneParNature(coordonneesDto.getTelephones(), idNatureMobileTravail);
        if (telephoneBureauSquare != null) {
            final com.square.tarificateur.noyau.dto.personne.TelephoneDto telephoneDto =
                mapperDozerBean.map(telephoneBureauSquare, com.square.tarificateur.noyau.dto.personne.TelephoneDto.class);
            personneDto.setTelephoneBureau(telephoneDto);
        }

        // Téléphone fax
        final Long idNatureFaxPrive = squareMappingService.getIdNatureFaxPrive();
        final TelephoneDto telephoneFaxSquare = rechercherTelephoneParNature(coordonneesDto.getTelephones(), idNatureFaxPrive);
        if (telephoneFaxSquare != null) {
            final com.square.tarificateur.noyau.dto.personne.TelephoneDto telephoneDto =
                mapperDozerBean.map(telephoneFaxSquare, com.square.tarificateur.noyau.dto.personne.TelephoneDto.class);
            personneDto.setTelephoneFax(telephoneDto);
        }
    }

    /**
     * Récupère une personne Square par son identifiant en recherchant dans le cache ou en la récupérant par le service.
     * @param eidPersonne l'identifiant Square de la personne
     * @param infosPersonnes le cache des infos de personnes
     * @return le DTO de la personne Square
     */
    public com.square.core.model.dto.PersonneDto getPersonnePhysique(Long eidPersonne, InfosPersonneSquareBean infosPersonnes) {
        // Recherche dans le cache
        com.square.core.model.dto.PersonneDto personneDto = infosPersonnes.getMapPersonnesPhysiques().get(eidPersonne);
        if (personneDto == null) {
            // Recherche par le service
            personneDto = personnePhysiqueService.rechercherPersonneParIdentifiant(eidPersonne);
            // Ajout dans le cache
            infosPersonnes.getMapPersonnesPhysiques().put(eidPersonne, personneDto);
        }
        return personneDto;
    }

    /**
     * Récupère une personne Square par son identifiant en recherchant dans le cache ou en la récupérant par le service.
     * @param eidPersonne l'identifiant Square de la personne
     * @param infosPersonnes le cache des infos de personnes
     * @return le DTO de la personne Square
     */
    public PersonneSimpleDto getPersonneSimple(Long eidPersonne, InfosPersonneSquareBean infosPersonnes) {
        // Recherche dans le cache
        PersonneSimpleDto personneSimpleDto = infosPersonnes.getMapPersonnesSimples().get(eidPersonne);
        if (personneSimpleDto == null) {
            // Recherche par le service
            personneSimpleDto = personnePhysiqueService.rechercherPersonneSimpleParIdentifiant(eidPersonne);
            // Ajout dans le cache
            infosPersonnes.getMapPersonnesSimples().put(eidPersonne, personneSimpleDto);
        }
        return personneSimpleDto;
    }

    /**
     * Récupère les coordonnées d'une personne Square par son identifiant en recherchant dans le cache ou en la récupérant par le service.
     * @param eidPersonne l'identifiant Square de la personne
     * @param infosPersonnes le cache des infos de personnes
     * @return le DTO des coordonnées de la personne Square
     */
    public CoordonneesDto getCoordonnees(Long eidPersonne, InfosPersonneSquareBean infosPersonnes) {
        // Recherche dans le cache
        CoordonneesDto coordonneesDto = infosPersonnes.getMapCoordonnees().get(eidPersonne);
        if (coordonneesDto == null) {
            // Recherche par le service
            coordonneesDto = personneService.rechercherCoordonneesParIdPersonne(eidPersonne);
            // Ajout dans le cache
            infosPersonnes.getMapCoordonnees().put(eidPersonne, coordonneesDto);
        }
        return coordonneesDto;
    }

    /**
     * Définit la valeur de squareMappingService.
     * @param squareMappingService la nouvelle valeur de squareMappingService
     */
    public void setSquareMappingService(SquareMappingService squareMappingService) {
        this.squareMappingService = squareMappingService;
    }

    /**
     * Définit la valeur de personnePhysiqueService.
     * @param personnePhysiqueService la nouvelle valeur de personnePhysiqueService
     */
    public void setPersonnePhysiqueService(PersonnePhysiqueService personnePhysiqueService) {
        this.personnePhysiqueService = personnePhysiqueService;
    }

    /**
     * Définit la valeur de mapperDozerBean.
     * @param mapperDozerBean la nouvelle valeur de mapperDozerBean
     */
    public void setMapperDozerBean(MapperDozerBean mapperDozerBean) {
        this.mapperDozerBean = mapperDozerBean;
    }

    /**
     * Définit la valeur de personneService.
     * @param personneService la nouvelle valeur de personneService
     */
    public void setPersonneService(PersonneService personneService) {
        this.personneService = personneService;
    }
}
