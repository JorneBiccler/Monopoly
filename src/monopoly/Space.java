/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Klasse waarvan de objecten een plaats van het monopoly spel bijhouden i.h.b.
 * zo geschreven dat deze van JAXB gebruik kan maken
 *
 * @author jorne
 */
public class Space {

    private SpaceType type;
    private String area;
    private int position;
    private String id;
    private int cost;
    private int rent0;
    private int amount;
 


    @XmlAttribute
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @XmlAttribute
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @XmlAttribute
    public SpaceType getType() {
        return type;
    }

    public void setType(SpaceType type) {
        this.type = type;
    }

    @XmlAttribute
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @XmlAttribute
    public int getRent0() {
        return rent0;
    }

    public void setRent0(int rent0) {
        this.rent0 = rent0;
    }

}
