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

import java.util.List;

import org.scub.foundation.framework.base.dto.IdentifiantLibelleDto;

import com.square.tarificateur.noyau.dto.personne.DimensionCriteresLienFamilialRechercheDto;
import com.square.tarificateur.noyau.dto.personne.PersonneDto;
import com.square.tarificateur.noyau.dto.personne.PersonneTarificateurDto;
import com.square.tarificateur.noyau.service.interfaces.TarificateurPersonneService;

/**
 * Mock du service TarificateurPersonne.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public class TarificateurPersonneServiceImpl implements TarificateurPersonneService {

    /** Flag de transfert des EID de la personne. */
    private boolean transfertRelationsEffectue = false;

    @Override
    public void transfererEidPersonne(Long eidPersonneSource, Long eidPersonneCible) {
        final Long eidPersonne1 = 1L;
        final Long eidPersonne2 = 2L;
        if (eidPersonne2.equals(eidPersonneSource) && eidPersonne1.equals(eidPersonneCible)) {
            transfertRelationsEffectue = true;
        }
    }

    /**
     * Récupère la valeur de transfertRelationsEffectue.
     * @return la valeur de transfertRelationsEffectue
     */
    public boolean isTransfertRelationsEffectue() {
        return transfertRelationsEffectue;
    }

    @Override
    public PersonneDto getPersonneByEid(Long eidPersonne) {
        return null;
    }

    @Override
    public PersonneDto getPersonneById(Long idPersonne) {
        return null;
    }

    @Override
    public PersonneDto createOrUpdatePersonne(PersonneTarificateurDto personne) {
        return null;
    }

    @Override
    public List<PersonneDto> getAssuresSociauxByIdDevis(Long idDevis) {
        return null;
    }

    @Override
    public List<IdentifiantLibelleDto> rechercherLiensFamiliauxParCriteres(DimensionCriteresLienFamilialRechercheDto criteres) {
        return null;
    }

    @Override
    public IdentifiantLibelleDto rechercherRelationAssureSocial(Long idPersonne) {
        return null;
    }

    @Override
    public PersonneDto createAssureSocial(PersonneDto assureSocial, Long idDevis, boolean fromOuvertureOpp) {
        return null;
    }

}
