/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author jorne
 */
public class Space {

    private String type;
    private String area;
    private int position;
    private String id;
    private int cost;
    private int rent0;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int rent5;
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
    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    @XmlAttribute
    public int getRent1() {
        return rent1;
    }

    public void setRent1(int rent1) {
        this.rent1 = rent1;
    }

    @XmlAttribute
    public int getRent2() {
        return rent2;
    }

    public void setRent2(int rent2) {
        this.rent2 = rent2;
    }

    @XmlAttribute
    public int getRent3() {
        return rent3;
    }

    public void setRent3(int rent3) {
        this.rent3 = rent3;
    }

    @XmlAttribute
    public int getRent4() {
        return rent4;
    }

    public void setRent4(int rent4) {
        this.rent4 = rent4;
    }

    @XmlAttribute
    public int getRent5() {
        return rent5;
    }

    public void setRent5(int rent5) {
        this.rent5 = rent5;
    }

    public int[] getRentArray() {
        int[] ar = {rent0, rent1, rent2, rent3, rent4, rent5};
        return ar;
    }
    
}
