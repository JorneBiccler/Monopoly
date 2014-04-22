/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Klasse waarvan een object alle info rond 'areas' bijhoudt, in het bijzonder
 * is ze zo opgesteld dat ook JAXB er gebruik van kan maken
 *
 * @author Jorne Biccler
 */
public class Area {

    private String id;
    private String colorString;

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
