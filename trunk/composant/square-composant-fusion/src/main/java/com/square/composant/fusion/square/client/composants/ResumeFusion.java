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
package com.square.composant.fusion.square.client.composants;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.square.composant.fusion.square.client.model.ConstantesModel;
import com.square.composant.fusion.square.client.model.PersonneSourceFusionModel;
import com.square.composant.fusion.square.client.model.ProprieteFusionnableEmailModel;
import com.square.composant.fusion.square.client.model.ProprieteFusionnableTelephoneModel;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenter;
import com.square.composant.fusion.square.client.presenter.composant.fusion.ComposantFusionPresenterConstants;

/**
 * Composant affichant le résumé de la fusion avant son exécution.
 * @author Nicolas NADEAU (nicolas.nadeau@scub.net) - SCUB
 */
public class ResumeFusion extends VerticalPanel {

    /** Constantes du composant. */
    private ResumeFusionConstants composantConstants;

    /** Contenu du bloc. */
    private VerticalPanel pContenu;

    /** FlexTable contenant les champs fusionnés dans la personne cible. */
    private FlexTable ftResumeChamps;

    /** Constantes. */
    private ConstantesModel constantes;

    /**
     * Constructeur.
     * @param constantes les constantes.
     */
    public ResumeFusion(ConstantesModel constantes) {
        this.constantes = constantes;
        this.setWidth(ComposantFusionPresenterConstants.POURCENT_100);

        // Création des constantes du composant
        composantConstants = (ResumeFusionConstants) GWT.create(ResumeFusionConstants.class);

        pContenu = new VerticalPanel();
        pContenu.setWidth(ComposantFusionPresenterConstants.POURCENT_100);
        pContenu.addStyleName(ComposantFusionPresenter.RESOURCES.css().contenuBloc());
        this.add(pContenu);
        ftResumeChamps = new FlexTable();
        ftResumeChamps.addStyleName(ComposantFusionPresenter.RESOURCES.css().tableau());
        ftResumeChamps.setWidth(ComposantFusionPresenterConstants.POURCENT_100);

        // Construction des titres des colonnes du tableau
        final Label lTitreChamps = new Label(composantConstants.titreColonneChamp());
        final Label lTitreValeur = new Label(composantConstants.titreColonneValeur());
        ftResumeChamps.setWidget(0, 0, lTitreChamps);
        ftResumeChamps.setWidget(0, 1, lTitreValeur);
        ftResumeChamps.getFlexCellFormatter().addStyleName(0, 0, ComposantFusionPresenter.RESOURCES.css().titreColonne());
        ftResumeChamps.getFlexCellFormatter().addStyleName(0, 1, ComposantFusionPresenter.RESOURCES.css().titreColonne());
        pContenu.add(ftResumeChamps);
        this.add(pContenu);

        final CaptionPanel pBloc = new CaptionPanel(composantConstants.titreResumeFusion());
        pBloc.add(pContenu);
        this.add(pBloc);
    }

    /**
     * Remplit le tableau avec les champs à fusionner.
     * @param personneSourceEncours la personne source en cours.
     */
    public void chargerResume(PersonneSourceFusionModel personneSourceEncours) {
        // On vide la tableau
        while (ftResumeChamps.getRowCount() > 1) {
            ftResumeChamps.removeRow(1);
        }

        int row = 1;
        if (personneSourceEncours.getNro().getAFusionner() != null && personneSourceEncours.getNro().getAFusionner()) {
            final String libelle = composantConstants.numRo();
            String value = "";
            if (personneSourceEncours.getNro() != null && personneSourceEncours.getNro().getValeur() != null) {
                value = personneSourceEncours.getNro().getValeur();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getCivilite().getAFusionner() != null && personneSourceEncours.getCivilite().getAFusionner()) {
            final String libelle = composantConstants.civilite();
            String value = "";
            if (personneSourceEncours.getCivilite().getValeur() != null && personneSourceEncours.getCivilite().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getCivilite().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getNom().getAFusionner() != null && personneSourceEncours.getNom().getAFusionner()) {
            final String libelle = composantConstants.nom();
            String value = "";
            if (personneSourceEncours.getNom().getValeur() != null) {
                value = personneSourceEncours.getNom().getValeur();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getNomJeuneFille().getAFusionner() != null && personneSourceEncours.getNomJeuneFille().getAFusionner()) {
            final String libelle = composantConstants.nomJeuneFille();
            String value = "";
            if (personneSourceEncours.getNomJeuneFille().getValeur() != null) {
                value = personneSourceEncours.getNomJeuneFille().getValeur();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getPrenom().getAFusionner() != null && personneSourceEncours.getPrenom().getAFusionner()) {
            final String libelle = composantConstants.prenom();
            String value = "";
            if (personneSourceEncours.getPrenom().getValeur() != null) {
                value = personneSourceEncours.getPrenom().getValeur();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getDateNaissance().getAFusionner() != null && personneSourceEncours.getDateNaissance().getAFusionner()) {
            final String libelle = composantConstants.dateNaissance();
            String value = "";
            if (personneSourceEncours.getDateNaissance().getValeur() != null) {
                value = personneSourceEncours.getDateNaissance().getValeur();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getDateDeces().getAFusionner() != null && personneSourceEncours.getDateDeces().getAFusionner()) {
            final String libelle = composantConstants.dateDeces();
            String value = "";
            if (personneSourceEncours.getDateDeces().getValeur() != null) {
                value = personneSourceEncours.getDateDeces().getValeur();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getDecede().getAFusionner() != null && personneSourceEncours.getDecede().getAFusionner()) {
            final String libelle = composantConstants.decede();
            String value = "";
            if (personneSourceEncours.getDecede().getValeur() != null && personneSourceEncours.getDecede().getValeur()) {
                value = composantConstants.oui();
            }
            else {
                value = composantConstants.non();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getSitFam().getAFusionner() != null && personneSourceEncours.getSitFam().getAFusionner()) {
            final String libelle = composantConstants.situationFam();
            String value = "";
            if (personneSourceEncours.getSitFam().getValeur() != null && personneSourceEncours.getSitFam().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getSitFam().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getProfession().getAFusionner() != null && personneSourceEncours.getProfession().getAFusionner()) {
            final String libelle = composantConstants.profession();
            String value = "";
            if (personneSourceEncours.getProfession().getValeur() != null && personneSourceEncours.getProfession().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getProfession().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getNaturePersonne().getAFusionner() != null && personneSourceEncours.getNaturePersonne().getAFusionner()) {
            final String libelle = composantConstants.naturePersonne();
            String value = "";
            if (personneSourceEncours.getNaturePersonne().getValeur() != null && personneSourceEncours.getNaturePersonne().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getNaturePersonne().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getStatut().getAFusionner() != null && personneSourceEncours.getStatut().getAFusionner()) {
            final String libelle = composantConstants.statut();
            String value = "";
            if (personneSourceEncours.getStatut().getValeur() != null && personneSourceEncours.getStatut().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getStatut().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getCaisse().getAFusionner() != null && personneSourceEncours.getCaisse().getAFusionner()) {
            final String libelle = composantConstants.caisse();
            String value = "";
            if (personneSourceEncours.getCaisse().getValeur() != null && personneSourceEncours.getCaisse().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getCaisse().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getCaisseRegime().getAFusionner() != null && personneSourceEncours.getCaisseRegime().getAFusionner()) {
            final String libelle = composantConstants.caisseRegime();
            String value = "";
            if (personneSourceEncours.getCaisseRegime().getValeur() != null && personneSourceEncours.getCaisseRegime().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getCaisseRegime().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getCsp().getAFusionner() != null && personneSourceEncours.getCsp().getAFusionner()) {
            final String libelle = composantConstants.csp();
            String value = "";
            if (personneSourceEncours.getCsp().getValeur() != null && personneSourceEncours.getCsp().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getCsp().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getSegment().getAFusionner() != null && personneSourceEncours.getSegment().getAFusionner()) {
            final String libelle = composantConstants.segment();
            String value = "";
            if (personneSourceEncours.getSegment().getValeur() != null && personneSourceEncours.getSegment().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getSegment().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getReseauVente().getAFusionner() != null && personneSourceEncours.getReseauVente().getAFusionner()) {
            final String libelle = composantConstants.reseauVente();
            String value = "";
            if (personneSourceEncours.getReseauVente().getValeur() != null && personneSourceEncours.getReseauVente().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getReseauVente().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getCommercial().getAFusionner() != null && personneSourceEncours.getCommercial().getAFusionner()) {
            final String libelle = composantConstants.commercial();
            String value = "";
            if (personneSourceEncours.getCommercial().getValeur() != null && personneSourceEncours.getCommercial().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getCommercial().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getAgence().getAFusionner() != null && personneSourceEncours.getAgence().getAFusionner()) {
            final String libelle = composantConstants.agence();
            String value = "";
            if (personneSourceEncours.getAgence().getValeur() != null && personneSourceEncours.getAgence().getValeur().getLibelle() != null) {
                value = personneSourceEncours.getAgence().getValeur().getLibelle();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getAdressePrincipale().getAFusionner() != null && personneSourceEncours.getAdressePrincipale().getAFusionner()) {
            final String libelle = composantConstants.adressePrincipale();
            String value = "";
            if (personneSourceEncours.getAdressePrincipale().getValeur() != null
                    && personneSourceEncours.getAdressePrincipale().getValeur().getLibelleAdresse() != null) {
                value = personneSourceEncours.getAdressePrincipale().getValeur().getLibelleAdresse();
            }
            ajouterLigneResume(row++, libelle, value);
        }
        if (personneSourceEncours.getListeTelephones() != null && !"".equals(personneSourceEncours.getListeTelephones().size() > 0)) {
            for (ProprieteFusionnableTelephoneModel telephone : personneSourceEncours.getListeTelephones()) {
                if (telephone.getAFusionner() != null && telephone.getAFusionner()) {
                    String libelle = "";
                    if (constantes.getIdNatureTelephoneFixe().equals(telephone.getValeur().getNature().getIdentifiant())) {
                        libelle = composantConstants.telFixe();
                    }
                    else {
                        libelle = composantConstants.telPortable();
                    }
                    String value = "";
                    if (telephone.getValeur().getNumero() != null) {
                        value = telephone.getValeur().getNumero();
                    }
                    ajouterLigneResume(row++, libelle, value);
                }
            }
        }
        if (personneSourceEncours.getListeEmails() != null && !"".equals(personneSourceEncours.getListeEmails().size() > 0)) {
            for (ProprieteFusionnableEmailModel email : personneSourceEncours.getListeEmails()) {
                if (email.getAFusionner() != null && email.getAFusionner()) {
                    final String libelle = composantConstants.email();
                    String value = "";
                    if (email.getValeur().getAdresse() != null) {
                        value = email.getValeur().getAdresse();
                    }
                    ajouterLigneResume(row++, libelle, value);
                }
            }
        }

        if (row == 1) {
            final Label lAucunChampAFusionner = new Label(composantConstants.aucunChampAFusionner());
            ftResumeChamps.setWidget(1, 0, lAucunChampAFusionner);
            ftResumeChamps.getFlexCellFormatter().setColSpan(1, 0, 2);
            ftResumeChamps.getFlexCellFormatter().setHorizontalAlignment(1, 0, ALIGN_CENTER);
        }
    }

    /**
     * Ajoute une ligne au résumé.
     * @param row la ligne.
     * @param libelle le libellé du champ.
     * @param valeur la valeur du champ.
     */
    private void ajouterLigneResume(int row, String libelle, String valeur) {
        String styleLigne = "";
        ftResumeChamps.setWidget(row, 0, new Label(libelle));
        ftResumeChamps.setWidget(row, 1, new Label(valeur));

        // Définition du style pour alterner ligne sombre et ligne claire
        if (row % 2 == 1) {
            styleLigne = ComposantFusionPresenter.RESOURCES.css().cellClair();
        }
        else {
            styleLigne = ComposantFusionPresenter.RESOURCES.css().cellSombre();
        }
        ftResumeChamps.getCellFormatter().addStyleName(row, 0, styleLigne);
        ftResumeChamps.getCellFormatter().addStyleName(row, 1, styleLigne);
    }
}
