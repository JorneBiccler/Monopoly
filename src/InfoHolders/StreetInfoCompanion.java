/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class StreetInfoCompanion extends PurchasableInfoCompanion {

    int[] rent;
    int rentIndex;
    public Label huurLabel;
    public Rectangle areaRectangle;

    public StreetInfoCompanion(Space space, String infoProp) {
        super(space, infoProp);
        this.rent = space.getRentArray();
        this.rentIndex = 0;

    }

    public void initialize() {
        super.initialize();
        huurLabel.setText("Huur: " + rent[rentIndex]);
    }

    public int[] getRent() {
        return rent;
    }

    public void setRent(int[] rent) {
        this.rent = rent;
    }

    public int getRentIndex() {
        return rentIndex;
    }

    public void setRentIndex(int rentIndex) {
        this.rentIndex = rentIndex;
    }

}
