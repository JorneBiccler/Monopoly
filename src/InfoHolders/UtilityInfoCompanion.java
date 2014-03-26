/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class UtilityInfoCompanion extends PurchasableInfoCompanion {

    public ImageView imageView;
    private Image image;

    public UtilityInfoCompanion(Space space, String infoProp) {
        super(space, infoProp);
        String imageString = "/resources/" + space.getId().replaceAll("space.", "") + ".png";
        this.image = new Image(imageString);
    }

    public void initialize(){
        super.initialize();
        imageView.setImage(image);
    }
}
