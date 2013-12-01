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
package com.square.fusion.api.service.test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe de base pour les tests unitaires.
 * @author Juanito Goncalves (juanito.goncalves@scub.net) - SCUB
 */
public abstract class BaseTestCase {

    /**
     * Recuperer un Bean Spring dans le Contexte.
     * @param beanId identifiant du bean.
     * @return une instance du bean demandé.
     */
    protected final Object getBeanSpring(final String beanId) {
        return SpringFactory.getInstance().getBean(beanId);
    }

    /**
     * AssertEquals .
     * @param message message à afficher.
     * @param obj premier objet à comparer.
     * @param objDeux deuxiéme objet à comparer.
     */
    protected void assertEquals(String message, Object obj, Object objDeux) {
        TestCase.assertEquals(message, obj, objDeux);
    }

    /**
     * AssertNotSame .
     * @param message message à afficher.
     * @param obj premier objet à comparer.
     * @param objDeux deuxiéme objet à comparer.
     */
    protected void assertNotSame(String message, Object obj, Object objDeux) {
        TestCase.assertNotSame(message, obj, objDeux);
    }

    /**
     * AssertNull .
     * @param message message à afficher.
     * @param obj premier objet à comparer.
     */
    protected void assertNull(String message, Object obj) {
        TestCase.assertNull(message, obj);
    }

    /**
     * AssertNotNull .
     * @param message message à afficher.
     * @param obj premier objet à comparer.
     */
    protected void assertNotNull(String message, Object obj) {
        TestCase.assertNotNull(message, obj);
    }

    /**
     * AssertTrue .
     * @param message message à afficher.
     * @param exp premier objet à comparer.
     */
    protected void assertTrue(final String message, Boolean exp) {
        TestCase.assertTrue(message, exp);
    }

    /**
     * AssertFalse .
     * @param message message à afficher.
     * @param exp premier objet à comparer.
     */
    protected void assertFalse(final String message, Boolean exp) {
        TestCase.assertFalse(message, exp);
    }

    /**
     * Erreur lors d'un traitement.
     * @param message le message à afficher.
     */
    protected void fail(final String message) {
        TestCase.fail(message);
    }

    /** Classe permettant d'accéder aux fichiers de configuration. */
    private static final class SpringFactory {
        /** Logger. */
        private static Logger logger = RootLogger.getLogger(BaseTestCase.class);

        /** Nom du fichier de configuration service. */
        private static final String[] FICHIERS_CONFIGURATION = new String[] {"applicationContext.xml", "square-fusion-api-context.xml"};

        /** Instance de l'objet - pattern singleton. */
        private static ApplicationContext instance;

        /**
         * Comme nous utilisons le pattern singleton, le constructeur est privé.
         */
        private SpringFactory() {
        }

        /**
         * Retourne une instance du loader.
         * @return instance du loader
         */
        private static synchronized ApplicationContext getInstance() {
            if (instance == null) {
                // controle doublon fichier par defaut.
                final Set<String> listeFichiersConfiguration = new HashSet<String>();
                for (int indexDeux = 0; indexDeux < FICHIERS_CONFIGURATION.length; indexDeux++) {
                    listeFichiersConfiguration.add(FICHIERS_CONFIGURATION[indexDeux]);
                    logger.debug(Messages.getString("BaseTestCase.2") + FICHIERS_CONFIGURATION[indexDeux]);
                }
                instance = new ClassPathXmlApplicationContext(listeFichiersConfiguration.toArray(new String[listeFichiersConfiguration.size()]));
            }

            return instance;
        }
    }
}
