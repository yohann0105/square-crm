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
package com.square.client.gwt.server.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;
import org.scub.foundation.framework.base.exception.TechnicalException;

/**
 * Implémentation du converter.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class StringConverter implements Converter {

    private static Map<Class<? extends Collection>, Class<? extends Collection>> collectionClassMapping;

    static {
    	collectionClassMapping = new HashMap<Class<? extends Collection>, Class<? extends Collection>>();
    	collectionClassMapping.put(Set.class, HashSet.class);
    	collectionClassMapping.put(List.class, ArrayList.class);
    }

    private static final String TYPE_STRING = "String";

    private static final String TYPE_CALENDAR = "Calendar";

    private static final String SEPARATEUR_TYPE_LISTE = ":";

    private static final String SEPARATEUR_ELEMENTS_LISTE = ";";

    private static final String SEPARATEUR_PROPRIETES_OBJET = ",";

    private static final String SEPARATEUR_PROPRIETE_OBJET = "-";

    /** Format expression. */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @SuppressWarnings("unchecked")
    @Override
    public Object convert(Class type, Object value) {
        try {
            // mapping vers collection
            if (Collection.class.isAssignableFrom(type)) {
                final String valueString = (String) value;
                // on recupere le type de l'objet
                if (valueString.indexOf(SEPARATEUR_TYPE_LISTE) == -1) {
                    throw new TechnicalException("La liste devrait être typée");
                }
                final String[] split = valueString.split(SEPARATEUR_TYPE_LISTE);
                final String typeObjet = split[0];
                // on decoupe chaque element de la liste
                final String[] elements = split[1].split(SEPARATEUR_ELEMENTS_LISTE);
                final Collection listeElements = collectionClassMapping.get(type).newInstance();
                for (String element : elements) {
                    final Class cls = Class.forName(typeObjet);
                    // on cree l'objet
                    if (element.indexOf(SEPARATEUR_PROPRIETES_OBJET) == -1) {
                        final Constructor ct = cls.getConstructor(String.class);
                        final Object object = ct.newInstance(element);
                        // si il s'agit un objet simple (Integer, String, Long), on copie juste la valeur
                        listeElements.add(object);
                    } else {
                        // on cree l'objet
                        final Constructor ct = cls.getConstructor();
                        final Object object = ct.newInstance();
                        // on decoupe chaque propriété
                        final String[] proprietes = element.split(SEPARATEUR_PROPRIETES_OBJET);
                        for (String propriete : proprietes) {
                            // on remplit l'objet avec ses propriétés
                            final String[] valeur = propriete.split(SEPARATEUR_PROPRIETE_OBJET);
                            BeanUtils.copyProperty(object, valeur[0], valeur[1]);
                        }
                        listeElements.add(object);
                    }
                }
                return listeElements;
            }
            // mapping vers Calendar
            else if (type.getSimpleName().equals(TYPE_CALENDAR)) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(simpleDateFormat.parse(String.valueOf(value)));
                return calendar;
            }
            // mapping vers String
            else if (type.getSimpleName().equals(TYPE_STRING)) {
                // mapping depuis Calendar
                if (value instanceof Calendar) {
                    return simpleDateFormat.format(((Calendar) value).getTime());
                }
                // mapping depuis List
                else if (value instanceof Collection) {
                    final StringBuffer valeur = new StringBuffer();
                    // on parcours les objets de la liste
                    for (Object object : (Collection) value) {
                        // on stocke le type de l'objet au debut pour pouvoir le caster dans l'autre sens
                        if (valeur.length() == 0) {
                            valeur.append(object.getClass().getName() + SEPARATEUR_TYPE_LISTE);
                        } else {
                            // on separe les differents elements de la liste
                            valeur.append(SEPARATEUR_ELEMENTS_LISTE);
                        }
                        // si on est sur des types simples, on ajoute juste la valeur
                        if (object instanceof Long || object instanceof String || object instanceof Integer) {
                            valeur.append(object.toString());
                        } else {
                            // pour les objets complexes, on recupere les valeurs de chaque propriétés
                            final Map<String, Object> map = BeanUtils.describe(object);
                            int i = 0;
                            for (String key : map.keySet()) {
                                final Object keyValue = map.get(key);
                                if (!key.equals("class") && keyValue != null) {
                                    if (i > 0) {
                                        // on separe les differentes proprietes de l'objet
                                        valeur.append(SEPARATEUR_PROPRIETES_OBJET);
                                    }
                                    // on ajoute la propriété si valeur non null et pas de type class
                                    valeur.append(key).append(SEPARATEUR_PROPRIETE_OBJET).append(map.get(key));
                                    i++;
                                }
                            }
                        }
                    }
                    return valeur.toString();
                }
                // sinon on caste simplement en string
                return new org.apache.commons.beanutils.converters.StringConverter().convert(type, value);
            }
            return value;
        } catch (ClassNotFoundException e) {
            throw new TechnicalException(e);
        } catch (IllegalAccessException e) {
            throw new TechnicalException(e);
        } catch (InvocationTargetException e) {
            throw new TechnicalException(e);
        } catch (ParseException e) {
            throw new TechnicalException(e);
        } catch (NoSuchMethodException e) {
            throw new TechnicalException(e);
        } catch (IllegalArgumentException e) {
            throw new TechnicalException(e);
        } catch (InstantiationException e) {
            throw new TechnicalException(e);
        }
    }
}
