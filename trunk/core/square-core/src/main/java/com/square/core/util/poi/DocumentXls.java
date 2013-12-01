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
package com.square.core.util.poi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.scub.foundation.framework.base.dto.FichierDto;
import org.scub.foundation.framework.base.exception.TechnicalException;

/**
 * Classe utilitaire pour exporter des données dans un tableau excel.
 * @author Anthony GUILLEMETTE (anthony.guillemette@scub.net) - SCUB
 */
public class DocumentXls {

    private static final int SHEET_MAX_ROWS = 65536;

    private static final String TYPE_MIME_XLS = "application/vnd.ms-excel";

    /** Largeur des marges gauche et droite du fichier excel pour l'export des utilisateurs. */
    private static final double LARGEUR_MARGE = 0.5;

    /** Hauteur de la police d'écriture. */
    private static final short ENTETE_FONT_HEIGHT = 11;

    private HSSFWorkbook classeur;

    private String nomFichier;

    private String[] entetes;

    private Integer[] entetesWidth;

    private Logger logger = Logger.getLogger(this.getClass());

    private int row;

    /**
     * Constructeur.
     * @param nomFichier nom du fichier
     * @param entetes liste des champs d'entete
     */
    public DocumentXls(String nomFichier, String[] entetes) {
        this(nomFichier, entetes, null);
    }

    /**
     * Constructeur.
     * @param nomFichier nom du fichier
     * @param entetes liste des champs d'entete
     * @param entetesWidth liste des largeurs d'entete
     */
    public DocumentXls(String nomFichier, String[] entetes, Integer[] entetesWidth) {
        this.nomFichier = nomFichier;
        this.entetes = entetes;
        this.entetesWidth = entetesWidth;
        genererEnteteDocument();
    }

    /**
     * Genere l'entete du document.
     */
    private void genererEnteteDocument() {
        // Création du classeur
        classeur = new HSSFWorkbook();
        creerPage();

        // Définition de l'impression
        final HSSFPrintSetup impression = classeur.getSheetAt(0).getPrintSetup();
        impression.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        impression.setLandscape(true);
    }

    /**
     * Recupere la page courante ou la suivante si la courante est pleine.
     * @return la page
     */
    private HSSFSheet recupererPage() {
        // recuperation de la derniere page
        HSSFSheet page = classeur.getSheetAt(classeur.getNumberOfSheets() - 1);
        // on verifie si il y a encore de la place sur la page
        if (page.getPhysicalNumberOfRows() == SHEET_MAX_ROWS) {
            page = creerPage();
        }
        return page;
    }

    /** Cree une page. */
    private HSSFSheet creerPage() {
        final HSSFSheet page = classeur.createSheet();
        page.setMargin(HSSFSheet.LeftMargin, LARGEUR_MARGE);
        page.setMargin(HSSFSheet.RightMargin, LARGEUR_MARGE);

        row = 0;

        if (entetesWidth != null) {
            // Définition de la largeur des colonnes
            int col = 0;
            for (Integer enteteWidth : entetesWidth) {
                if (enteteWidth != null) {
                    page.setColumnWidth(col, enteteWidth);
                }
                col++;
            }
        }
        final HSSFCellStyle styleEntete = classeur.createCellStyle();
        final HSSFFont policeEntete = classeur.createFont();
        policeEntete.setFontHeightInPoints(ENTETE_FONT_HEIGHT);
        policeEntete.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleEntete.setFont(policeEntete);

        // Affichage des titres de colonnes
        ajouterLigne(entetes, styleEntete);

        return page;
    }

    /**
     * Recupere la ligne courante.
     * @return la ligne
     */
    private HSSFRow recupererLigne(HSSFSheet page) {
        return page.createRow(row++);
    }

    /**
     * Ajoute une ligne au document.
     * @param donnees les données de la ligne
     */
    public void ajouterLigne(String[] donnees) {
        ajouterLigne(donnees, null);
    }

    /**
     * Ajoute une ligne au document.
     * @param donnees les données de la ligne
     * @param style le style à mettre sur les cellules de la ligne
     */
    public void ajouterLigne(String[] donnees, HSSFCellStyle style) {
        // Récupération de la page
        final HSSFSheet page = recupererPage();
        // Récupération d'une ligne
        final HSSFRow ligne = recupererLigne(page);

        int col = 0;
        for (String donnee : donnees) {
            // Déclaration d'une cellule
            final HSSFCell cellule = ligne.createCell(col++);
            final HSSFRichTextString rtsValue = new HSSFRichTextString(donnee);
            cellule.setCellValue(rtsValue);
            if (style != null) {
                cellule.setCellStyle(style);
            }
        }
    }

    /**
     * Retourne le document avec son contenu.
     * @return le document
     */
    public FichierDto cloturerDocument() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            classeur.write(out);
            return new FichierDto(nomFichier, TYPE_MIME_XLS, out.toByteArray());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new TechnicalException("Erreur lors de l'ecriture du document");
        }
    }
}
