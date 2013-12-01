package com.square.document.core.dao.interfaces;


import java.util.List;

import com.square.document.core.model.Tag;

/**
 * Dao pour le tag (type).
 * @author tlebigre
 *
 */
public interface TagDao {

    /**
     * Recuperer un tag par son identifiant.
     * @param id l'identifiant du tag à retourner.
     * @return le tag
     */
    Tag rechercherTagParId(final Long id);

    /**
     * Récupération de tous les tags de la base de données.
     * @return la liste des tags
     */
    List<Tag> getListeTag();

    /**
     * Récupération du tag par critère.
     * @param nom nom du tag à récupérer
     * @return le tag
     */
    Tag getTagByCriteria(String nom);

    /**
     * Modification du tag.
     * @param tag nouveau tag
     */
    void updateTag(Tag tag);
}
