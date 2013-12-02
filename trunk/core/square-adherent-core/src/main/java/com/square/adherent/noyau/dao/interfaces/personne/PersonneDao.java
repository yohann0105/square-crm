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
package com.square.adherent.noyau.dao.interfaces.personne;

import java.util.List;

import com.square.adherent.noyau.dto.prestation.CriteresPersonnesNotificationSmsDto;

/**
 * Interface d'accès aux données liées aux personnes.
 * @author Nicolas PELTIER (nicolas.peltier@scub.net) - SCUB
 */
public interface PersonneDao {

    /**
     * Récupère la liste des identifiants des bénéficiaires d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return la liste des identifiants
     */
    List<Long> getListeBeneficiairesPersonne(Long idPersonne);

    /**
     * Récupère l'adhérent principal d'une personne.
     * @param idPersonne l'identifiant de la personne
     * @return l'id de l'adhérent principal
     */
    Long getAdherentPrincipalPersonne(Long idPersonne);

    /**
     * Récupère la liste des personnes a notifier par sms suivant les criteres.
     * @param criteres les criteres de recherche
     * @return la liste des infos portables des personnes
     */
    List<Long> getListePersonnesNotificationSmsByCriteres(CriteresPersonnesNotificationSmsDto criteres);

}
