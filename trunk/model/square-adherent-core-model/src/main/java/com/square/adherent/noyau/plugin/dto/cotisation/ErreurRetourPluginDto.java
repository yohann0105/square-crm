package com.square.adherent.noyau.plugin.dto.cotisation;

import java.io.Serializable;

/**
 * Les erreurs en retour.
 * @author Goumard Stephane (stephane.goumard@scub.net)
 */
public class ErreurRetourPluginDto implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 211655542064110042L;

    /**
     * Level.
     */
    private String level;

    /**
     * Class.
     */
    private String clazz;

    /**
     * Code.
     */
    private String code;

    /**
     * label.
     */
    private String label;

    /**
     * Get the level value.
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Set the level value.
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Get the clazz value.
     * @return the clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Set the clazz value.
     * @param clazz the clazz to set
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    /**
     * Get the code value.
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code value.
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the label value.
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label value.
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
