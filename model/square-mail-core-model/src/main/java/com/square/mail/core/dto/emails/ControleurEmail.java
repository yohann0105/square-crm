package com.square.mail.core.dto.emails;

import java.util.regex.Pattern;

/**
 * Controleur de mails valides.
 * @author nnadeau
 */
public final class ControleurEmail {

    /** Constructeur privé. */
    private ControleurEmail() {
    }

    /**
     * Méthode de validation d'un mail.
     * @param mail : mail à vérifier.
     * @return true si le mail est valide, false sinon.
     */
    public static boolean checkAdresseMail(final String mail) {
        return Pattern.matches("\\b[A-Za-z0-9\\._\\-~#]+@[A-Za-z0-9][A-Za-z0-9\\._\\-~#]*\\.[A-Za-z]{2,4}\\b", mail);
    }
}
