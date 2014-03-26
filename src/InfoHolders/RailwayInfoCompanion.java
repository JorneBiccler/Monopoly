/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class RailwayInfoCompanion extends PurchasableInfoCompanion {

    public Label huurLabel;
    public ImageView imageView;
    private Image image;
    private int rent;

    public RailwayInfoCompanion(Space space, String infoProp) {
        super(space, infoProp);
        this.rent = space.getRent0();
        this.image = new Image("/resources/railway.png");
    }

    
    @Override
    public void initialize() {
        super.initialize();
        imageView.setImage(image);
        huurLabel.setText("Huur: " + rent);
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

}
