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
package com.square.noyauLog.core.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.scub.foundation.framework.base.exception.BusinessException;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;

import com.square.logs.core.model.dto.LogDto;
import com.square.logs.service.interfaces.LogService;

/**
 * Classe de test de LogService.
 * @author mohamed - SCUB
 */
public class LogServiceTest extends DbunitBaseTestCase {
	private final Logger logger = Logger.getLogger(getClass());

	private static final int NB_THREADS = 20;
	private static final int NB_LOGS = 40;

    private LogService logService;

    /**
     * Méthode appellée avant chaque test unitaire.
     */
    @Before
    public void setUp() {
        logService = (LogService) getBeanSpring("logService");
    }

    /**
     * Teste l'ajout d'un log dans la base via le service asynchrone.
     */
    @Test
    public void testAjouterLog() {
        try {
            logService.ajouterLog(null);
            fail(Messages.getString("LogServiceTest.1"));
        } catch (BusinessException e) {
        	assertEquals(Messages.getString("LogServiceTest.2"), e.getMessage(), Messages.getString("LogServiceTest.3"));
        }
        final List<Object> params = new ArrayList<Object>();
        final LogDto logDto = new LogDto();
        // on utilise le log comme parametre, pourquoi pas?
        params.add(logDto);
        logDto.setDate(Calendar.getInstance());
        logDto.setProfondeur(3);
        logDto.setParametres(params);
        logDto.setTraceException("exception");
        logDto.setResultat("résultat");

        // On crée des threads concurrents qui vont générer des logs
        final Runnable ajoutLogsRunnable = new Runnable() {
			@Override
			public void run() {
		        for (int i = 0; i < NB_LOGS; i++) {
		        	logService.ajouterLog(logDto);
		        }
			}
		};
		for (int i = 0; i < NB_THREADS; i++) {
			final Thread thread = new Thread(ajoutLogsRunnable);
			thread.start();
		}

        // le service étant asynchrone on attend qu'il ait fini de traiter les messages
        int nbLogsEnAttente;
    	while ((nbLogsEnAttente = logService.getLogsEnAttente().size()) > 0) {
    		try {
    			logger.info(nbLogsEnAttente + Messages.getString("LogServiceTest.6"));
    			Thread.sleep(10);
    		} catch (InterruptedException ie) {
    			logger.warn(Messages.getString("LogServiceTest.7"), ie);
    		}
    	}

        final Calendar date = Calendar.getInstance();
        assertEquals(Messages.getString("LogServiceTest.8"), NB_THREADS * NB_LOGS, logService.getNbLogsBeforeDate(date));

    	// On fait le ménage
    	final int pagination = 50;
    	int count = 0;
        logger.info(Messages.getString("LogServiceTest.9") + pagination + Messages.getString("LogServiceTest.10"));
        List<Long> listeIds = logService.getIdsLogsBeforeDate(date, pagination);
        while (listeIds.size() > 0) {
            // Suppression des anciens logs
            logger.info(Messages.getString("LogServiceTest.11") + pagination + Messages.getString("LogServiceTest.12"));
            count += logService.supprimerLogsByIds(listeIds);
            logger.info(Messages.getString("LogServiceTest.13") + count + Messages.getString("LogServiceTest.14"));
            logger.info(Messages.getString("LogServiceTest.15") + pagination + Messages.getString("LogServiceTest.16"));
            listeIds = logService.getIdsLogsBeforeDate(date, pagination);
        }

    	assertEquals(Messages.getString("LogServiceTest.17"), NB_THREADS * NB_LOGS, count);
    }
}
