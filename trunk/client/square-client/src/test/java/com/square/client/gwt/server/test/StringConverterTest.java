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
package com.square.client.gwt.server.test;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.base.paging.RemotePagingCriteriasDto;
import org.scub.foundation.framework.base.paging.RemotePagingSort;
import org.scub.foundation.framework.core.test.AbstractBaseTestCase;

import com.square.client.gwt.server.util.StringConverter;
import com.square.core.model.dto.PersonneCriteresRechercheDto;

/**
 * Classe de test du converter.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class StringConverterTest extends AbstractBaseTestCase {

    /**
     * Test de mapping des criteres.
     */
    @Test
    public void testMapperCriteres() {
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("StringConverterTest.0"));
        final PersonneCriteresRechercheDto criterias = new PersonneCriteresRechercheDto();
        criterias.setAdresse("uneAdresse");
        criterias.setDateNaissance(Calendar.getInstance());
        final List<Long> idsAgences = new ArrayList<Long>();
        idsAgences.add(1L);
        idsAgences.add(5L);
        idsAgences.add(8L);
        criterias.setIdAgences(idsAgences);
        criterias.setRechercheStricte(true);
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            new RemotePagingCriteriasDto<PersonneCriteresRechercheDto>(criterias, 0, Integer.MAX_VALUE);
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort("nom", RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        listeSorts.add(new RemotePagingSort("dateFin", RemotePagingSort.REMOTE_PAGING_SORT_ASC));
        criteres.setListeSorts(listeSorts);

        resetConverter();
        final Map<String, String> map = mapperCriteres(criteres);

        boolean existCle = false;
        boolean existDateNaissance = false;
        boolean existIdsAgences = false;
        boolean existRechercheStricte = false;
        boolean existListeSorts = false;
        boolean existFirstResult = false;
        boolean existMaxResult = false;
        boolean existSupprime = false;
        for (String cle : map.keySet()) {
            if (cle.equals("adresse")) {
                assertEquals(Messages.getString("StringConverterTest.5"), "uneAdresse", map.get(cle));
                existCle = true;
            } else if (cle.equals("dateNaissance")) {
                assertEquals(Messages.getString("StringConverterTest.8"), sdf.format(Calendar.getInstance().getTime()), map.get(cle));
                existDateNaissance = true;
            } else if (cle.equals("idAgences")) {
                assertEquals(Messages.getString("StringConverterTest.10"), "java.lang.Long:1;5;8", map.get(cle));
                existIdsAgences = true;
            } else if (cle.equals(Messages.getString("StringConverterTest.12"))) {
                assertEquals(Messages.getString("StringConverterTest.13"), "true", map.get(cle));
                existRechercheStricte = true;
            } else if (cle.equals("listeSorts")) {
                assertEquals(Messages.getString("StringConverterTest.16"),
                    Messages.getString("StringConverterTest.17"), map.get(cle));
                existListeSorts = true;
            } else if (cle.equals("firstResult")) {
                assertEquals(Messages.getString("StringConverterTest.19"), "0", map.get(cle));
                existFirstResult = true;
            } else if (cle.equals("maxResult")) {
                assertEquals(Messages.getString("StringConverterTest.22"), String.valueOf(Integer.MAX_VALUE), map.get(cle));
                existMaxResult = true;
            } else if (cle.equals("supprime")) {
                assertEquals(Messages.getString("StringConverterTest.24"), String.valueOf(Boolean.FALSE), map.get(cle));
                existSupprime = true;
            } else {
                fail(Messages.getString("StringConverterTest.25") + cle);
            }
        }
        assertTrue(Messages.getString("StringConverterTest.26"), existCle);
        assertTrue(Messages.getString("StringConverterTest.27"), existDateNaissance);
        assertTrue(Messages.getString("StringConverterTest.28"), existIdsAgences);
        assertTrue(Messages.getString("StringConverterTest.29"), existRechercheStricte);
        assertTrue(Messages.getString("StringConverterTest.30"), existListeSorts);
        assertTrue(Messages.getString("StringConverterTest.31"), existFirstResult);
        assertTrue(Messages.getString("StringConverterTest.32"), existMaxResult);
        assertTrue(Messages.getString("StringConverterTest.33"), existSupprime);
    }

    /**
     * Test de recuperation des criteres.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRecupererCriteres() {
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("StringConverterTest.34"));

        final Map<String, String> map = new HashMap<String, String>();
        map.put("adresse", "uneAdresse");
        map.put("dateNaissance", sdf.format(Calendar.getInstance().getTime()));
        map.put("idAgences", "java.lang.Long:1;5;8");
        map.put("rechercheStricte", "true");
        map.put("listeSorts", Messages.getString("StringConverterTest.43"));

        resetConverter();
        final RemotePagingCriteriasDto<PersonneCriteresRechercheDto> criteres =
            (RemotePagingCriteriasDto<PersonneCriteresRechercheDto>) recupererCriteres(map, new PersonneCriteresRechercheDto());

        final Calendar date = Calendar.getInstance();
        final Calendar dateCopie = Calendar.getInstance();
        date.clear();
        date.set(dateCopie.get(Calendar.YEAR), dateCopie.get(Calendar.MONTH), dateCopie.get(Calendar.DAY_OF_MONTH));
        final List<Long> idsAgences = new ArrayList<Long>();
        idsAgences.add(1L);
        idsAgences.add(5L);
        idsAgences.add(8L);
        final List<RemotePagingSort> listeSorts = new ArrayList<RemotePagingSort>();
        listeSorts.add(new RemotePagingSort("nom", RemotePagingSort.REMOTE_PAGING_SORT_DESC));
        listeSorts.add(new RemotePagingSort("dateFin", RemotePagingSort.REMOTE_PAGING_SORT_ASC));

        assertEquals(Messages.getString("StringConverterTest.46"), Messages.getString("StringConverterTest.47"),
        		criteres.getCriterias().getAdresse());
        assertEquals(Messages.getString("StringConverterTest.48"), date, criteres.getCriterias().getDateNaissance());
        assertEquals(Messages.getString("StringConverterTest.49"), true, criteres.getCriterias().isRechercheStricte());
        assertEquals(Messages.getString("StringConverterTest.50"), idsAgences.toString(), criteres.getCriterias().getIdAgences().toString());
        boolean existTriNom = false;
        boolean existTriDateFin = false;
        for (RemotePagingSort remotePagingSort : listeSorts) {
            if (remotePagingSort.getSortField().equals("nom")) {
                assertEquals(Messages.getString("StringConverterTest.52"), RemotePagingSort.REMOTE_PAGING_SORT_DESC,
                		remotePagingSort.getSortAsc());
                existTriNom = true;
            } else if (remotePagingSort.getSortField().equals("dateFin")) {
                assertEquals(Messages.getString("StringConverterTest.54"), RemotePagingSort.REMOTE_PAGING_SORT_ASC,
                		remotePagingSort.getSortAsc());
                existTriDateFin = true;
            } else {
                fail(Messages.getString("StringConverterTest.55") + remotePagingSort.getSortField());
            }
        }
        assertTrue(Messages.getString("StringConverterTest.56"), existTriNom);
        assertTrue(Messages.getString("StringConverterTest.57"), existTriDateFin);
    }

    /**
     * Enregistrement des converter.
     */
    private void resetConverter() {
        ConvertUtils.deregister();
        ConvertUtils.register(new StringConverter(), String.class);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> mapperCriteres(RemotePagingCriteriasDto<?> criteres) {
        try {
            final Map<String, String> map = BeanUtils.describe(criteres.getCriterias());
            map.putAll(BeanUtils.describe(criteres));
            // on nettoie la map
            final Set<String> keyset = new HashSet<String>(map.keySet());
            for (String key : keyset) {
                if (StringUtils.isBlank(map.get(key)) || key.equals(Messages.getString("StringConverterTest.58")) || key.equals("criterias")) {
                    map.remove(key);
                }
            }
            return map;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new TechnicalException(Messages.getString("StringConverterTest.60"));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new TechnicalException(Messages.getString("StringConverterTest.61"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new TechnicalException(Messages.getString("StringConverterTest.62"));
        }
    }

    @SuppressWarnings("unchecked")
    private RemotePagingCriteriasDto<?> recupererCriteres(Map<String, String> map, Object criteres) {
        try {
            BeanUtils.populate(criteres, map);
            final RemotePagingCriteriasDto<?> criterias = new RemotePagingCriteriasDto(criteres, 0, Integer.MAX_VALUE);
            BeanUtils.populate(criterias, map);
            return criterias;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new TechnicalException(Messages.getString("StringConverterTest.63"));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new TechnicalException(Messages.getString("StringConverterTest.64"));
        }
    }
}
