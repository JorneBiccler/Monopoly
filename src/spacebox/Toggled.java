/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package spacebox;

/**
 * Enum die correpondeert met het al dan niet getoggled zijn van een vakje deze
 * houdt ook de styleclass die hierbij houdt bij.
 *
 * @author Jorne Biccler
 */
public enum Toggled {

    TOGGLED("spaceBox-selected"), UNTOGGLED("spaceBox-unselected");
    private final String cssClass;

    private Toggled(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCSSClass() {
        return cssClass;
    }
}
