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
package com.square.composant.ged.square.client.view.listedetaillee;

import java.util.List;

/**
 * Bloc pour le transport d'informations entre la vue et le controller.
 * @author jgoncalves - SCUB
 */
public class BlocListeDocumentsDto {
    private String titre;
    private List<LigneDocumentDto> listeLignes;

    /**
     * constructeur.
     * @param titre le titre
     * @param listeLignes la liste
     */
    public BlocListeDocumentsDto(String titre, List<LigneDocumentDto> listeLignes) {
        this.titre = titre;
        this.listeLignes = listeLignes;
    }

    /**
     * Récupération de titre.
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }
    /**
     * Définition de titre.
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    /**
     * Récupération de listeLignes.
     * @return the listeLignes
     */
    public List<LigneDocumentDto> getListeLignes() {
        return listeLignes;
    }
    /**
     * Définition de listeLignes.
     * @param listeLignes the listeLignes to set
     */
    public void setListeLignes(List<LigneDocumentDto> listeLignes) {
        this.listeLignes = listeLignes;
    }
}
