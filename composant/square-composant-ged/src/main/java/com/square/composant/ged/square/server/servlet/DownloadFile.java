package com.square.composant.ged.square.server.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.context.SecurityContextHolder;
import org.scub.foundation.framework.base.exception.TechnicalException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.square.document.core.dto.CriteresRechercheDocumentDto;
import com.square.document.core.dto.DocumentDto;
import com.square.document.core.service.interfaces.GedService;

/**
 * Servlet permettant le téléchargement d'un fichier.
 * @author Julie Jageneau
 */
public class DownloadFile extends HttpServlet {

    /** Url de la servlet d'export de recherche. */
    public static final String URL_SERVLET_DOWNLOAD_FILE = "servlet/downloadFile";

    private GedService gedService;

    /***
     * Récupération du login (Acegi & Spring security supportés.
     * @return le login
     */
    private String getLoginUtilisateurCourant() {
        return SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null ? SecurityContextHolder
                .getContext().getAuthentication().getName() : org.springframework.security.context.SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        gedService = (GedService) context.getBean("gedService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getQueryString();
        if (!id.isEmpty() && !id.trim().isEmpty()) {
            final CriteresRechercheDocumentDto criteres = new CriteresRechercheDocumentDto();
            final List<String> listeIds = new ArrayList<String>();
            listeIds.add(id);
            criteres.setIds(listeIds);
            criteres.setNumeroClient(getLoginUtilisateurCourant());

            final DocumentDto document = gedService.getDocumentByCriteres(criteres, getLoginUtilisateurCourant());
            
            // Définition du type de la réponse
            resp.setContentType(document.getTypeMime());
            // Nom du fichier
            resp.setHeader("Content-disposition", "attachment; filename=" + document.getNom());
            // Copie du contenu du fichier dans le flux de sortie
            final OutputStream out = resp.getOutputStream();
            out.write(document.getContenu());
            out.close();
        } else {
            throw new TechnicalException("Une erreur est survenue : des paramètres de servlet sont manquants");
        }
    }
}
