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
package com.square.core.dao.interfaces;

import java.util.List;

import com.square.core.model.Aide;

/**
 * Interface du dao de l'aide.
 * @author mohamedAli - SCUB
 */
public interface AideDao {
    /**
     * création d'an aide.
     * @param aide dto de l'aide à creer
     */
    void creerAide(Aide aide);

    /**
     * recupére un aide à travers son id.
     * @param id id de l'aide à recuperer
     * @return l'aide trouvé
     */
    Aide rechercherAideparId(Long id);

    /**
     * récupérer aide par id Composant.
     * @param idComposant idComposant
     * @return l'aide trouvé
     */
    Aide rechercherAideparIdComposant(Long idComposant);

    /**
     * Recherche des aides par leurs identifiants.
     * @param listeIdsComposant la liste des identifiants des composants des aides à rechercher.
     * @return la liste des identifiants des composants des aides trouvées.
     */
    List<Long> rechercherIdsComposantsAides(List<Long> listeIdsComposant);
}
