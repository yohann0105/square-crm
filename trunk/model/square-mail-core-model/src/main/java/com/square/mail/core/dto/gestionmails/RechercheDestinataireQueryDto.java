package com.square.mail.core.dto.gestionmails;

import java.io.Serializable;


/**
 * DTO représentant les critères de recherche pour récupérer les adresses mails des éventuels destinataires.
 * @author nnadeau
 */
public class RechercheDestinataireQueryDto implements Serializable {

    /** Identifiant de sérialisation. */
    private static final long serialVersionUID = 2796176830156600013L;

    /** Critère sur le nom, le prénom ou l'email de l'utilisateur. */
    private String critereEmailNomPrenom;

    /** Pagination. */
    private PagingDto pagination;

    /** Tri sur le nom. */
    private OrderByDto orderBy;

    /**
     * Get the critereEmailNomPrenom value.
     * @return the critereEmailNomPrenom
     */
    public String getCritereEmailNomPrenom() {
        return critereEmailNomPrenom;
    }

    /**
     * Set the critereEmailNomPrenom value.
     * @param critereEmailNomPrenom the critereEmailNomPrenom to set
     */
    public void setCritereEmailNomPrenom(String critereEmailNomPrenom) {
        this.critereEmailNomPrenom = critereEmailNomPrenom;
    }

    /**
     * Get the pagination value.
     * @return the pagination
     */
    public PagingDto getPagination() {
        return pagination;
    }

    /**
     * Set the pagination value.
     * @param pagination the pagination to set
     */
    public void setPagination(PagingDto pagination) {
        this.pagination = pagination;
    }

    /**
     * Get the orderBy value.
     * @return the orderBy
     */
    public OrderByDto getOrderBy() {
        return orderBy;
    }

    /**
     * Set the orderBy value.
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(OrderByDto orderBy) {
        this.orderBy = orderBy;
    }
}
