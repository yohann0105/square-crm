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
package com.square.adherent.noyau.model.dimension;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Super Classe abtraite pour les tables de dimension avec eid.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
@MappedSuperclass
public abstract class ModelEidDim extends ModelDim {

    private static final long serialVersionUID = 7604781616587172771L;

    @Column(name = "EID")
    private String eid;

    /**
     * Get the value of eid.
     * @return the eid
     */
    public String getEid() {
        return eid;
    }

    /**
     * Set the value of eid.
     * @param eid the eid to set
     */
    public void setEid(String eid) {
        this.eid = eid;
    }
}
