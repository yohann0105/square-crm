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

import com.square.core.model.plugin.HabilitationSquarePlugin;

/**
 * Mock pour le plugin habilitation.
 * @author Sylla Mohamed Lamine (lamine08@gmail.com) - SCUB
 */
public class HabilitationSquarePluginMock implements HabilitationSquarePlugin {

    @Override
    public String getEidByLogin(String login) {
        final String eid = "1";
        return eid;
    }

    /** {@inheritDoc} */
    public String getEidResponsableByCodePostal(String codePostal) {
        if ("000".equals(codePostal)) {
            return "1500";
        }
        else if ("001".equals(codePostal)) {
            return "2001";
        }
        else if ("33000".equals(codePostal)) {
            return "1500";
        }
        return "3";
    }

    @Override
    public Long getIdentifiantRoleSquareAdmin() {
        final Long id = 365470166L;
        return id;
    }

    @Override
    public boolean hasUtilisateurRole(String login, Long idRole) {
        return false;
    }

    @Override
    public Long getIdentifiantRoleGestionnaireOpportunites() {
        final Long id = 365470167L;
        return id;
    }

    @Override
    public Long getIdentifiantAgenceFrance() {
        final Long id = 365470200L;
        return id;
    }

    @Override
    public boolean hasUtilisateurByIdRole(Long idUtilisateur, Long idRole) {
        return false;
    }

    @Override
    public String getEidAgenceByCodePostal(String codePostal) {
        String eid = null;
        if ("001".equals(codePostal)) {
            eid = "1";
        } else {
            eid = "2";
        }
        return eid;
    }

    @Override
    public Long getIdentifiantRoleSquare() {
        final Long id = 365469833L;
        return id;
    }
}
