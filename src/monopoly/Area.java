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


    public Color getAreaColor() {
                System.out.println(id);
            Color color = Color.web(colorString);
        System.out.println(color);
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

    public int getIndex(){
        return Integer.parseInt(getId().replaceAll("area",""));
    }
}
