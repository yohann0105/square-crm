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
package com.square.core.test.mock;

import java.util.ArrayList;
import java.util.List;

import com.square.adherent.noyau.dto.adherent.BeneficiaireDto;
import com.square.adherent.noyau.dto.adherent.InfosCompteAdherentDto;
import com.square.adherent.noyau.dto.adherent.InfosEspaceAdherentDto;
import com.square.adherent.noyau.dto.adherent.InfosIdentifiantPersonneDto;
import com.square.adherent.noyau.dto.adherent.InfosOptionsAdherentDto;
import com.square.adherent.noyau.dto.adherent.OptionAdherentDto;
import com.square.adherent.noyau.dto.adherent.PersonneDto;
import com.square.adherent.noyau.dto.adherent.contrat.CoupleBaremeDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeCriteresDto;
import com.square.adherent.noyau.dto.adherent.contrat.GarantieBaremeDto;
import com.square.adherent.noyau.dto.adherent.prestations.DemandePriseEnChargeDto;
import com.square.adherent.noyau.dto.prestation.CriteresPersonnesNotificationSmsDto;
import com.square.adherent.noyau.dto.produit.ReserveProduitBancoDto;
import com.square.adherent.noyau.service.interfaces.AdherentService;

/**
 * Mock service adherent.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class AdherentServiceImpl implements AdherentService {

    private static final long ID_TYPE_OPTION_ENVOI_SMS = 5L;

    private static final long ID_TYPE_OPTION_ENVOI_MUTUELLEMENT_MAIL = 2L;

    private static final long ID_TYPE_OPTION_ENVOI_RELEVE_PRESTATION_MAIL = 1L;

    @Override
    public InfosEspaceAdherentDto getInfosEspaceAdherent(Long uidPersonne) {
        return null;
    }

    @Override
    public List<Long> getListeAdherentsByIdMagazineEnvoye(Long idMagazine) {
        return null;
    }

    @Override
    public List<Long> getListeAdherentsByIdOptionSouscrite(Long idOption) {
        return null;
    }

    @Override
    public List<Long> getListeBeneficiairesPersonne(Long idPersonne) {
        final List<Long> listeIdsBeneficiaires = new ArrayList<Long>();
        return listeIdsBeneficiaires;
    }

    @Override
    public List<Long> getListeIdsTypesOptionsSouscrites(Long uidPersonne) {
        final List<Long> idsTypesOptions = new ArrayList<Long>();
        if (uidPersonne != null) {
            if (uidPersonne.equals(4L)) {
                idsTypesOptions.add(ID_TYPE_OPTION_ENVOI_RELEVE_PRESTATION_MAIL);
                idsTypesOptions.add(ID_TYPE_OPTION_ENVOI_MUTUELLEMENT_MAIL);
                idsTypesOptions.add(ID_TYPE_OPTION_ENVOI_SMS);
            }
        }
        return idsTypesOptions;
    }

    @Override
    public List<Long> getListePersonnesNotificationSmsByCriteres(CriteresPersonnesNotificationSmsDto criteres) {
        return null;
    }

    @Override
    public boolean hasPersonneEspaceAdherent(Long uidPersonne) {
        return false;
    }

    @Override
    public boolean isPersonneAssocieeAContrat(Long uidPersonne) {
        return false;
    }

    @Override
    public boolean isPersonnePossedeOption(Long idPersonne, Long idTypeOption) {
        return false;
    }

    @Override
    public void mettreAJourInfosCompteAdherent(InfosCompteAdherentDto infosCompteAdherent) {
    }

    @Override
    public List<OptionAdherentDto> mettreAJourListeOptionsAdherent(Long uidPersonne, List<OptionAdherentDto> listeOptions) {
        return null;
    }

    @Override
    public void mettreAJourMotDePasse(Long idPersonne, String motDePasse) {
    }

    @Override
    public InfosOptionsAdherentDto getInfosOptionsAdherent(Long uidPersonne) {
        return null;
    }

    @Override
    public BeneficiaireDto ajouterBeneficiaire(Long idAdherent, BeneficiaireDto beneficiaireDto) {
        return null;
    }

    @Override
    public void demanderPriseEnCharge(DemandePriseEnChargeDto demandePriseEnChargeDto) {

    }

    @Override
    public void desactiverOptionsEnvoiParEmail(Long uidPersonne) {
    }

    @Override
    public Long getIdAdherentPrincipal(Long idPersonne) {
        return null;
    }

    @Override
    public void desactiverOptionsEnvoiParTelephone(Long uidPersonne) {
    }

    @Override
    public List<ReserveProduitBancoDto> getReserveProduitBancoByUser(Long idAdherent) {
        return null;
    }

    @Override
    public InfosIdentifiantPersonneDto getInfosAdherentPrincipalByNumAdherent(String numAdherent) {
        return null;
    }

    @Override
    public List<PersonneDto> getListeAssurePrincipalParPagination(int debutPagination, int taillePagination) {
        return null;
    }

    @Override
    public List<PersonneDto> getListeBeneficaireParPagination(int debutPagination, int taillePagination) {
        return null;
    }

    @Override
    public List<CoupleBaremeDto> getListeCouplesBaremesAdherent(GarantieBaremeCriteresDto criteres) {
        return null;
    }

    @Override
    public List<GarantieBaremeDto> getListeGarantiesBaremesAdherent(GarantieBaremeCriteresDto criteres) {
        return null;
    }

    @Override
    public List<BeneficiaireDto> getListeInfosBeneficiairesPersonne(Long idPersonne) {
        return null;
    }

    @Override
    public PersonneDto getPersonneById(Long idPersonne) {
        return null;
    }

    @Override
    public PersonneDto getPersonneByNumClient(String numClient) {
        return null;
    }

}
