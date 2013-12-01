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
package com.square.core.model.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 * Implementation d'un call back hibernate.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */
public class FormatObjectHibernateListener implements PreUpdateEventListener, PreInsertEventListener {

    /** Liste des champs a exclure. */
    private List<String> champsAExclure;

    /** Liste des classes concernées. */
    @SuppressWarnings("unchecked")
	private List<Class> classesConcernees;

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 8370245337822046981L;

    private Logger logger = RootLogger.getLogger(FormatObjectHibernateListener.class);

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        logger.debug("Hibernate Event Update " + event.getEntity());
        udpateEntity(event.getEntity(), event.getPersister(), event.getState());
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        logger.debug("Hibernate Event Insert " + event.getEntity());
        udpateEntity(event.getEntity(), event.getPersister(), event.getState());
        return false;
    }

    /**
     * Formate un objet.
     * @param objet l'objet à formater.
     */
    private void udpateEntity(Object entity, EntityPersister persister, Object[] state) {
        if (classesConcernees.contains(entity.getClass())) {
            final String[] properties = persister.getPropertyNames();
            for (int i = 0; i < properties.length; i++) {
                if (!champsAExclure.contains(properties[i])) {
                    if (state[i] instanceof String && state[i] != null) {
                        final String value = formaterChaine(state[i].toString()).toLowerCase();
                        state[i] = value;
                        try {
                            PropertyUtils.setSimpleProperty(entity, properties[i], value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * Supprimer les espaces et tirets au debut et à la fin de la chaine.
     * @param str str
     * @return str
     */
    private String formaterChaine(String str) {
        String ouput = str;
        final String word = "[0-9a-zA-ZéèêàâôîïûçÉÈÊÀÂÔÎÏÛÇ]";
        final String notWord = "[\\s\\-]";
        final Pattern pattern = Pattern.compile("(" + notWord + "*)(" + word + ")(" + word + "*)(" + notWord + "*)(" + word + "*)(" + notWord + "*)");
        final Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            ouput = matcher.group(1).replaceAll("\\W", "").trim() + matcher.group(2).trim().toUpperCase() + matcher.group(3);

            if (!"".equals(matcher.group(5))) {
                ouput += matcher.group(4) + matcher.group(5) + matcher.group(6).replaceAll("\\W", "").trim();
            }
        }
        return ouput.trim();
    }

    /**
     * Définition de champsAExclure.
     * @param champsAExclure the champsAExclure to set
     */
    public void setChampsAExclure(List<String> champsAExclure) {
        this.champsAExclure = champsAExclure;
    }

    /**
     * Définition de classesConcernees.
     * @param classesConcernees the classesConcernees to set
     */
    @SuppressWarnings("unchecked")
	public void setClassesConcernees(List<Class> classesConcernees) {
        this.classesConcernees = classesConcernees;
    }

}
