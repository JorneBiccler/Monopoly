/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author jorne
 */
public class Area {

    private String id;
    private String colorString;
    private int house;
    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
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
    public String getColorString() {
        return colorString;
    }

    public void setColorString(String str) {
        this.colorString = str;
        color = Color.web(str);
    }
    @XmlAttribute
    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

}
