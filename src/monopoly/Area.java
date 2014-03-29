/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlAttribute;

/*
 * Klasse die alle info rond 'areas' bijhoudt,
 * in het bijzonder is ze zo opgesteld dat ook JAXB er gebruik van kan maken
 * @author jorne
 */
public class Area {

    private String id;
    private String colorString;
    private int house;

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

    @XmlAttribute
    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

}
