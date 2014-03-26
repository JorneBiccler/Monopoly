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
public class TaxInfoCompanion extends InfoCompanion {

    public Label infoLabel;
    public ImageView imageView;
    private Image image;
    private int amount;

    public TaxInfoCompanion(Space space, String infoProp) {
        super(space, infoProp);
        this.image = new Image("/resources/tax.png");
        this.amount = space.getAmount();
    }

    public void initialize() {
        infoLabel.setText(getInfoProp() + ":" + amount);
        imageView.setImage(image);
    }
}
