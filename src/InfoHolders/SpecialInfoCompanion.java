/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
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
public class SpecialInfoCompanion extends InfoCompanion {

    public ImageView imageView;
    public Label infoLabel;
    private Image image;
    
    public SpecialInfoCompanion(Space space, String infoProp) {
        super(space, infoProp);
        String imageString = "/resources/" + space.getType().toLowerCase() + ".png";
        this.image = new Image(imageString);
    }

    public void initialize() {
        imageView.setImage(image);
        infoLabel.setText(getInfoProp());
    }

}
