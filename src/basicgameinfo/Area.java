/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package basicgameinfo;

import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Klasse waarvan een object alle info rond 'areas' bijhoudt, in het bijzonder
 * is ze zo opgesteld dat ook JAXB er gebruik van kan maken. Er is ook een
 * variabele numberOfStreets die kan gebruikt worden om in te stellen hoeveel
 * straaten er bij deze area horen
 *
 * @author Jorne Biccler
 */
public class Area {

    private String id;
    private String colorString;
    private int numberOfStreets = 0;

    public int getNumberOfStreets() {
        return numberOfStreets;
    }

    /**
     * Tel één op bij het aantal straten
     */
    public void incrementNumberOfStreets() {
        numberOfStreets++;
    }

    /**
     * Converter van de colorString naar het gepaste kleur.
     */
    public Color getAreaColor() {
        Color color = Color.web(colorString);
        return color;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getColor() {
        return colorString;
    }

    public void setColor(String str) {
        this.colorString = str;
    }

}
