/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import monopoly.Area;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class StreetBox extends InfoBox {

    private RentableLabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;
    private RectangleBox rectBox;
    private int[] rentAr;
    private int currentRentIndex;
    private Area area;
    public StreetBox(Space space, String propString) {
        super(space, propString);
        this.rentAr = space.getRentArray();
        rectBox = new RectangleBox(Color.AQUA);
        replaceImageBox(rectBox);
        labelBoxCompanion = new RentableLabelBoxCompanion(propString, space.getCost(),rentAr[0]);
        String fxmlURL = "/infoHolders/RentableLabelBox.fxml";
        labelBox = new LabelBox(labelBoxCompanion, fxmlURL);
        replaceLabelBox(labelBox);
    }

    public int getCurrentRentIndex() {
        return currentRentIndex;
    }

    public void setCurrentRentIndex(int currentRentIndex) {
        this.currentRentIndex = currentRentIndex;
    }
    public int getCurrentRent(){
        return rentAr[currentRentIndex];
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
        changeRectColor(area.getAreaColor());
    }
    public void changeRectColor(Color color){
        rectBox.changeRectangleColor(color);
        System.out.println(color);
    }
}
