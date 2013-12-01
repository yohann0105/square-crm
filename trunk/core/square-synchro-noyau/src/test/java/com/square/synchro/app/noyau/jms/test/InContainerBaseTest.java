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
package com.square.synchro.app.noyau.jms.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.junit.After;
import org.junit.Before;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.scub.foundation.framework.core.test.DbunitBaseTestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Super classe pour les tests unitaires in container.
 * @author nnadeau - SCUB
 */
public abstract class InContainerBaseTest {

    /**
     * Récupération du service RMI sans sécurité.
     * @param serviceInterface l'interface du service.
     * @param serviceUrl l'url duu service.
     * @return le service rmi.
     */
    public Object getServiceRmi(Class < ? extends Object > serviceInterface, String serviceUrl) {
        final RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setBeanClassLoader(serviceInterface.getClassLoader());
        factory.setServiceInterface(serviceInterface);
        factory.setServiceUrl(serviceUrl);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    /**
     * Initialisation de la base de test.
     * @throws Exception exception.
     */
    @Before
    public void setUpBaseTestCase() throws Exception {
        final List < SqlConfigParam > params = getScriptsSqlBeforeExecution();
        for (SqlConfigParam param : params) {
            executeScriptSql(param);
        }
    }


    /**
     * Nettoyage de la base de test.
     * @throws Exception exception.
     */
    @After
    public void tearDownBaseTestCase() throws Exception {
        final List < SqlConfigParam > params = getScriptsSqlAfterExecution();
        for (SqlConfigParam param : params) {
            executeScriptSql(param);
        }
    }

    /**
     * Méthode à surcharger pour jouer des fichiers sql avant exécution des tests unitaires.
     * @return la liste des fichiers sql à jouer.
     */
    public List < SqlConfigParam > getScriptsSqlBeforeExecution() {
        return new ArrayList < SqlConfigParam > ();
    }

    /**
     * Méthode à surcharger pour jouer des fichiers sql après exécution des tests unitaires.
     * @return la liste des fichiers sql à jouer.
     */
    public List < SqlConfigParam > getScriptsSqlAfterExecution() {
        return new ArrayList < SqlConfigParam > ();
    }

    /**
     * Retourne le fichier de properties où sont stockés les paramètres.
     * @return le fichier de properties.
     */
    public String getScriptSqlPropertiesParam() {
        return "./conf/test/filters/filters.properties"; //$NON-NLS-1$
    }

    /**
     * Retourne le login de l'utilisateur.
     * @return le login
     */
    public String getLogin() {
        return "smatisCrmNoyau_user"; //$NON-NLS-1$
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     * @return le mot de passe
     */
    public String getMotDePasse() {
        return "smatisCrmNoyau_user"; //$NON-NLS-1$
    }

    /**
     * Joue un script sql.
     * @param param le script sql à jouer.
     */
    private void executeScriptSql(SqlConfigParam param) {
        final Properties prop = new Properties();
        try {
            final FileInputStream in = new FileInputStream(getScriptSqlPropertiesParam());
            prop.load(in);
            in.close();

            Class.forName(prop.getProperty(param.getSqlConfigParamName().getDriverClassParamName()));
            final Connection con = DriverManager.getConnection(prop.getProperty(param.getSqlConfigParamName().getConnectionUrlParamName()),
                    prop.getProperty(param.getSqlConfigParamName().getConnectionUserNameParamName()),
                    prop.getProperty(param.getSqlConfigParamName().getConnectionPasswordParamName()));
            con.setAutoCommit(true);
            final Statement stmt = con.createStatement();

            final Scanner scan = new Scanner(new File(param.getFile()));
            scan.useDelimiter("\\s*\\n\\s*"); //$NON-NLS-1$

            while (scan.hasNext()) {
                final String str = scan.next();
                if (str.indexOf("--") != 0) { //$NON-NLS-1$
                    try {
                        stmt.execute(str);
                    }
                    catch (SQLException e) {
                        throw new TechnicalException(e);
                    }
                }
            }
            scan.close();

            stmt.close();
            con.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Classe Configuration d'un script sql. **/
    public class SqlConfigParam {
        private String file;
        private SqlConfigParamName sqlConfigParamName;

        /**
         * Constructeur.
         * @param file le fichier.
         * @param paramName la configuration des paramètres du script sql.
         */
        public SqlConfigParam(String file, SqlConfigParamName paramName) {
            this.file = file;
            this.sqlConfigParamName = paramName;
        }

        /**
         * Get the file value.
         * @return the file
         */
        public String getFile() {
            return file;
        }

        /**
         * Get the sqlConfigParamName value.
         * @return the sqlConfigParamName
         */
        public SqlConfigParamName getSqlConfigParamName() {
            return sqlConfigParamName;
        }
    }

    /** Classe Configuration des paramétres d'un script sql. **/
    public class SqlConfigParamName {
        private String driverClassParamName;
        private String connectionUrlParamName;
        private String connectionUserNameParamName;
        private String connectionPasswordParamName;

        /**
         * Constructeur.
         * @param driverClassParamName le driver.
         * @param connectionUrlParamName l'url de connection.
         * @param connectionUserNameParamName le login de connection.
         * @param connectionPasswordParamName le mot de passe de connection.
         */
        public SqlConfigParamName(String driverClassParamName, String connectionUrlParamName, String connectionUserNameParamName,
                String connectionPasswordParamName) {
            this.driverClassParamName = driverClassParamName;
            this.connectionUrlParamName = connectionUrlParamName;
            this.connectionUserNameParamName = connectionUserNameParamName;
            this.connectionPasswordParamName = connectionPasswordParamName;
        }

        /**
         * Get the driverClassParamName value.
         * @return the driverClassParamName
         */
        public String getDriverClassParamName() {
            return driverClassParamName;
        }

        /**
         * Get the connectionUrlParamName value.
         * @return the connectionUrlParamName
         */
        public String getConnectionUrlParamName() {
            return connectionUrlParamName;
        }

        /**
         * Get the connectionUserNameParamName value.
         * @return the connectionUserNameParamName
         */
        public String getConnectionUserNameParamName() {
            return connectionUserNameParamName;
        }

        /**
         * Get the connectionPasswordParamName value.
         * @return the connectionPasswordParamName
         */
        public String getConnectionPasswordParamName() {
            return connectionPasswordParamName;
        }
    }

    /**
     * Recuperer un Bean Spring dans le Contexte.
     * @param beanId identifiant du bean.
     * @return une instance du bean demandé.
     */
    protected final Object getBeanSpring(final String beanId)
    {
        return SpringFactory.getInstance(getFichierContextSpringSup()).getBean(beanId);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message.
     * @param obj le premier objet de la comparaison.
     * @param objDeux le second objet de la comparaison.
     */
    protected void assertEquals(String message, Object obj, Object objDeux) {
        TestCase.assertEquals(message, obj, objDeux);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message.
     * @param obj le premier objet de la comparaison.
     * @param objDeux le second objet de la comparaison.
     */
    protected void assertNotSame(String message, Object obj, Object objDeux) {
        TestCase.assertNotSame(message, obj, objDeux);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message.
     * @param obj l'objet dont on veut vérifier la nulité.
     */
    protected void assertNull(String message, Object obj) {
        TestCase.assertNull(message, obj);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message.
     * @param obj l'objet dont on veut vérifier la non-nulité.
     */
    protected void assertNotNull(String message, Object obj) {
        TestCase.assertNotNull(message, obj);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message.
     * @param exp l'expression dont on veut vérifier qu'elle retourne true.
     */
    protected void assertTrue(final String message, Boolean exp) {
        TestCase.assertTrue(message, exp);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message.
     * @param exp l'expression dont on veut vérifier qu'elle retourne false.
     */
    protected void assertFalse(final String message, Boolean exp) {
        TestCase.assertFalse(message, exp);
    }

    /**
     * Classe de test simplifié pour forcer l'utilisation préconisée (avec message) des asserts.
     * @param message le message à afficher pour remonter un échec.
     */
    protected void fail(final String message) {
        TestCase.fail(message);
    }

    /**
     * 
     * Classe permettant d'accéder aux fichiers de configuration.
     */
    private static final class SpringFactory
    {
        /**
         * Logger.
         */
        private static Logger logger = RootLogger.getLogger(DbunitBaseTestCase.class);

        /** Nom du fichier de configuration service. */
        private static final String[] FICHIERS_CONFIGURATION = new String[] {"selligent-api-context.xml", //$NON-NLS-1$
            "selligentMappingConfiguration.xml", "serviceSelligentImporterContext.xml"}; //$NON-NLS-1$ //$NON-NLS-2$

        /** Instance de l'objet - pattern singleton. */
        private static ApplicationContext instance;

        /**
         * 
         * Comme nous utilisons le pattern singleton, le constructeur est privé.
         * 
         */
        private SpringFactory() {
        }

        /**
         * 
         * Retourne une instance du loader.
         * @return instance du loader
         */
        private static synchronized ApplicationContext getInstance(final String[] fichierConfigurationsSup)
        {
            if (instance == null)
            {
                //controle doublon fichier par defaut.
                final Set < String > listeFichiersConfiguration = new HashSet < String > ();
                for (int indexDeux = 0; indexDeux < FICHIERS_CONFIGURATION.length; indexDeux++)
                {
                    listeFichiersConfiguration.add(FICHIERS_CONFIGURATION[indexDeux]);
                    logger.debug(Messages.getString("InContainerBaseTest.8") + FICHIERS_CONFIGURATION[indexDeux]); //$NON-NLS-1$
                }
                for (int index = 0; index < fichierConfigurationsSup.length; index++)
                {
                    if (!listeFichiersConfiguration.contains(fichierConfigurationsSup[index]))
                    {
                        listeFichiersConfiguration.add(fichierConfigurationsSup[index]);
                        logger.debug(Messages.getString("InContainerBaseTest.9") + fichierConfigurationsSup[index]); //$NON-NLS-1$
                    }
                }
                instance = new ClassPathXmlApplicationContext(listeFichiersConfiguration.toArray(new String[listeFichiersConfiguration.size()]));
            }

            return instance;
        }
    }

    /**
     * Recuperer une liste de fichiers suplémentaire de contexte spring.
     * Vous pouvez redefinir cette methode dans chaque test pour une liste de fichiers de configuration supplémentaire.
     * @return un tableau avec le nom de chaque fichier disponible dans le classPath de l'application.
     */
    protected String[] getFichierContextSpringSup()
    {
        return new String[0];
    }
}
