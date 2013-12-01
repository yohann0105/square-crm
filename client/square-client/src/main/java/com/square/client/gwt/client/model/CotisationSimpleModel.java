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
 * Model contenant les informations sur la cotisation.
 * @author Farh ZARROUK (farh.zarrouk@gmail.com) - SCUB
 */

public class CotisationSimpleModel implements IsSerializable {

    /**
     * Identifiant de la cotisations.
     */
    private Long id;

    /**
     * Code du contrat.
     */
    private String codeContrat;

    /**
     * Opération.
     */
    private String operation;

    /**
     * Report.
     */
    private Double report;

    /**
     * Remarque report.
     */
    private String remarqueReport;

    /**
     * Remise.
     */
    private Double remise;

    /**
     * Remarque remise.
     */
    private String remarqueRemise;

    /**
     * Total.
     */
    private Double total;

    /**
     * Remarque total.
     */
    private String remarqueTotal;

    /**
     * Montant réglé.
     */
    private Double montantRegle;

    /**
     * Remarque montant réglé.
     */
    private String remarqueMontantRegle;

    /**
     * Renvoie l'identifiant de la cotisation.
     * @return identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'identifiant de la cotisation.
     * @param id identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Renvoie le code du contart.
     * @return
     */
    public String getCodeContrat() {
        return codeContrat;
    }

    /**
     * Modifie le code du contart.
     * @param codeContrat code du contrat
     */
    public void setCodeContrat(String codeContrat) {
        this.codeContrat = codeContrat;
    }

    /**
     * Renvoie l'opération de la cotisation.
     * @return operation opération de la cotisation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Modifie l'operation de la cotisation.
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Renvoie le montant reporté.
     * @return report
     */
    public Double getReport() {
        return report;
    }

    /**
     * Modifie le montant reporté.
     * @param report dans report
     */
    public void setReport(Double report) {
        this.report = report;
    }

    /**
     * Renvoie la remarque de report.
     * @return remarqueReport
     */
    public String getRemarqueReport() {
        return remarqueReport;
    }

    /**
     * Modifie la remarque report.
     * @param remarqueReport
     */
    public void setRemarqueReport(String remarqueReport) {
        this.remarqueReport = remarqueReport;
    }

    /**
     * Renvoie la remise.
     * @return remise
     */
    public Double getRemise() {
        return remise;
    }

    /**
     * Modifie la remise.
     * @param remise
     */
    public void setRemise(Double remise) {
        this.remise = remise;
    }

    /**
     * Renvoie la remarque de la remise.
     * @return remarqueRemise
     */
    public String getRemarqueRemise() {
        return remarqueRemise;
    }

    /**
     * Modifie la remarque de la remise.
     * @param remarqueRemise
     */
    public void setRemarqueRemise(String remarqueRemise) {
        this.remarqueRemise = remarqueRemise;
    }

    /**
     * Renvoie le total.
     * @return total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Modifie le total.
     * @param total
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Renvoie la remarque du total.
     * @return remarqueTotal
     */
    public String getRemarqueTotal() {
        return remarqueTotal;
    }

    /**
     * Modifie la remarque du total.
     * @param remarqueTotal
     */
    public void setRemarqueTotal(String remarqueTotal) {
        this.remarqueTotal = remarqueTotal;
    }

    /**
     * Renvoie le montant réglé.
     * @return montantRegle
     */
    public Double getMontantRegle() {
        return montantRegle;
    }

    /**
     * Modifie le montant réglé.
     * @param montantRegle
     */
    public void setMontantRegle(Double montantRegle) {
        this.montantRegle = montantRegle;
    }

    /**
     * Renvoie la remarque du montant réglé.
     * @return remarqueMontantRegle
     */
    public String getRemarqueMontantRegle() {
        return remarqueMontantRegle;
    }

    /**
     * Modifie la remarque du montant réglé.
     * @param remarqueMontantRegle
     */
    public void setRemarqueMontantRegle(String remarqueMontantRegle) {
        this.remarqueMontantRegle = remarqueMontantRegle;
    }
}
