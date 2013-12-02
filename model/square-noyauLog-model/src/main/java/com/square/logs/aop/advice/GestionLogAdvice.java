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
package com.square.logs.aop.advice;

import java.util.Arrays;
import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;

import com.square.logs.LogLevel;
import com.square.logs.core.model.dto.LogDto;
import com.square.logs.service.interfaces.LogService;

/**
 * advice des gestion de logs.
 * @author mohamed - SCUB
 */
public class GestionLogAdvice {

    private LogService logService;

    private String application;

    private LogLevel logLevel = LogLevel.INFO;

    private int profondeur = 2;

    /**
     * ajoute le log de l'appel service.
     * @param point l'appel intercepté
     * @return Object retour de l'appel
     * @throws Throwable exception
     */
    public Object log(ProceedingJoinPoint point) throws Throwable {
        Object retVal = null;
        final String methodName = point.getSignature().toLongString();
        final Object[] args = point.getArgs();

        final LogDto logDto = new LogDto();
        logDto.setApplication(application);
        logDto.setProfondeur(profondeur);
        logDto.setDate(Calendar.getInstance());
        logDto.setSignature(methodName);

        try {
            logDto.setParametres(Arrays.asList(args));
            retVal = point.proceed();
            logDto.setResultat(retVal);
            if (logLevel == LogLevel.INFO) {
            	logService.ajouterLog(logDto);
            }
            return retVal;
        } catch (Throwable e) {
        	if (logLevel != LogLevel.NONE) {
        		logDto.setTraceException(e.getMessage());
        		logService.ajouterLog(logDto);
        	}
            throw e;
        }
    }

    /**
     * setter de logService.
     * @param logService the logService to set
     */
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    /**
     * Set the application value.
     * @param application the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Définit le niveau de log : INFO, ERROR ou NONE.
     * @param logLevel le niveau de log
     */
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * Définit la profondeur d'introspection des beans à logger.
	 * @param profondeur la profondeur d'introspection des beans à logger.
	 */
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
}
