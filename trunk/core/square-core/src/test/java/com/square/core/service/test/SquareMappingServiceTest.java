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
/**
 * 
 */
package com.square.core.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.messagesource.MessageSourceUtil;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.core.model.dto.ActionNotificationInfosDto;
import com.square.core.service.interfaces.SquareMappingService;
import com.square.core.util.SquareMappingKeyUtil;

/**
 * Service de Mapping.
 * @author Goumard Stephane (Scub) - SCUB
 */
public class SquareMappingServiceTest extends DbunitBaseTestCase {
    /**
     * Service de mapping.
     */
    private SquareMappingService squareMappingService;

    /**
     * Gestion des messages.
     */
    private MessageSourceUtil messageSourceUtil;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        squareMappingService = (SquareMappingService) getBeanSpring("squareMappingService");
        messageSourceUtil = (MessageSourceUtil) getBeanSpring("messageSourceUtil");
        createSecureContext("user", "user");
    }

    /** {@inheritDoc} */
    public String[] getFichierContextSpringSup() {
        return new String[] {"squareMappingContext.xml"};
    }

    /**
     * Test sur la récupération des heures.
     * @throws ParseException ParseException
     */
    @Test
    public void testSurLesNotifications() throws ParseException {

        final List<ActionNotificationInfosDto> listes = squareMappingService.getListeActionNotifications();
        assertTrue(Messages.getString("SquareMappingServiceTest.5"), listes.size() > 0);
        final Calendar date = Calendar.getInstance();
        date.clear();
        date.set(2010, 1, 15, 3, 15);

        ActionNotificationInfosDto notification = squareMappingService.getActionNotificationParId(1L);
        Calendar soustraction = Calendar.getInstance();
        soustraction.clear();
        Long dateTime = -notification.getNotification().getTimeInMillis() + date.getTimeInMillis();
        soustraction.setTimeInMillis(dateTime);

        Calendar dateAttendus = Calendar.getInstance();
        dateAttendus.clear();
        dateAttendus.set(2010, 1, 15, 3, 00);

        assertEquals(Messages.getString("SquareMappingServiceTest.6"), soustraction, dateAttendus);

        notification = squareMappingService.getActionNotificationParId(2L);
        soustraction = Calendar.getInstance();
        soustraction.clear();
        dateTime = -notification.getNotification().getTimeInMillis() + date.getTimeInMillis();
        soustraction.setTimeInMillis(dateTime);

        dateAttendus = Calendar.getInstance();
        dateAttendus.clear();
        dateAttendus.set(2010, 1, 15, 2, 45);

        assertEquals(Messages.getString("SquareMappingServiceTest.7"), soustraction, dateAttendus);

        notification = squareMappingService.getActionNotificationParId(3L);
        soustraction = Calendar.getInstance();
        soustraction.clear();
        dateTime = -notification.getNotification().getTimeInMillis() + date.getTimeInMillis();
        soustraction.setTimeInMillis(dateTime);

        dateAttendus = Calendar.getInstance();
        dateAttendus.clear();
        dateAttendus.set(2010, 1, 15, 2, 15);
        final SimpleDateFormat sdf = new SimpleDateFormat(Messages.getString("SquareMappingServiceTest.8"));
        assertEquals(Messages.getString("SquareMappingServiceTest.9") + sdf.format(soustraction.getTime()), soustraction, dateAttendus);

        notification = squareMappingService.getActionNotificationParId(4L);
        soustraction = Calendar.getInstance();
        soustraction.clear();
        dateTime = -notification.getNotification().getTimeInMillis() + date.getTimeInMillis();
        soustraction.setTimeInMillis(dateTime);

        dateAttendus = Calendar.getInstance();
        dateAttendus.clear();
        dateAttendus.set(2010, 1, 14, 22, 15);

        assertEquals(Messages.getString("SquareMappingServiceTest.10") + sdf.format(soustraction.getTime()), soustraction, dateAttendus);
    }

    /**
     * Test de la récupération de notification à partir de deux dates.
     */
    @Test
    public void testRechercheNotificationParDate() {
        // Test avec deux dates nulle
        try {
            squareMappingService.getIdNotification(null, null);
            fail(Messages.getString("SquareMappingServiceTest.11"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("SquareMappingServiceTest.12"), messageSourceUtil.get(
            		SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_NULL), be.getMessage());
        }

        // Test avec une date nulle
        try {
            squareMappingService.getIdNotification(null, Calendar.getInstance());
            fail(Messages.getString("SquareMappingServiceTest.13"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("SquareMappingServiceTest.14"), messageSourceUtil.get(
            		SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_NULL), be.getMessage());
        }
        // Test avec une autre date nulle
        try {
            squareMappingService.getIdNotification(Calendar.getInstance(), null);
            fail(Messages.getString("SquareMappingServiceTest.15"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("SquareMappingServiceTest.16"), messageSourceUtil.get(
            		SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_NULL), be.getMessage());
        }

        // Test de la récupération avec un interval inexistant
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        date1.set(2010, 1, 1, 12, 00, 00);

        final Calendar date2 = Calendar.getInstance();
        date2.clear();
        date2.set(2010, 1, 1, 11, 44, 00);
        assertNull(Messages.getString("SquareMappingServiceTest.17"), squareMappingService.getIdNotification(date1, date2));

        // Test de la récupération de 15 minutes
        final Calendar date3 = Calendar.getInstance();
        date3.clear();
        date3.set(2010, 1, 1, 11, 45, 00);
        assertNotNull(Messages.getString("SquareMappingServiceTest.18"), squareMappingService.getIdNotification(date1, date3));
        assertEquals(Messages.getString("SquareMappingServiceTest.19"), 1L, squareMappingService.getIdNotification(date1, date3));

        // Test de la récupération de 30 minutes
        final Calendar date4 = Calendar.getInstance();
        date4.clear();
        date4.set(2010, 1, 1, 11, 30, 00);
        assertNotNull(Messages.getString("SquareMappingServiceTest.20"), squareMappingService.getIdNotification(date1, date4));
        assertEquals(Messages.getString("SquareMappingServiceTest.21"), 2L, squareMappingService.getIdNotification(date1, date4));

        // Essai avec les dates inversées
        try {
            squareMappingService.getIdNotification(date4, date1);
            fail(Messages.getString("SquareMappingServiceTest.22"));
        }
        catch (BusinessException be) {
            assertEquals(Messages.getString("SquareMappingServiceTest.23"), messageSourceUtil.get(
            		SquareMappingKeyUtil.MESSAGE_ERREUR_DATE_ACTION_INF_DATE_NOTIFICATION), be
                    .getMessage());
        }
    }
}
