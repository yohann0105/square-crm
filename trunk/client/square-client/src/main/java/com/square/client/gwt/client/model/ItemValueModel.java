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
package com.square.client.gwt.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Model permettant de stocker un item et une value.
 * @author cblanchard - SCUB
 */
public class ItemValueModel implements IsSerializable {

    /** Item. */
    private String item;

    /** Value. */
    private String value;

    /**
     * Renvoi la valeur de item.
     * @return item
     */
    public String getItem() {
        return item;
    }

    /**
     * Modifie item.
     * @param item la nouvelle valeur de item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Renvoi la valeur de value.
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Modifie value.
     * @param value la nouvelle valeur de value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
