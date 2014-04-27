/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoholders;

import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.Space;

/**
 *
 * @author Jorne Biccler
 */
public abstract class PurchasableBoxWithImage extends PurchasableBox implements InvalidationListener {

    protected int initialRent;
    protected Image image;
    protected final ImageView imageView = new ImageView();
    private int cost;

    public PurchasableBoxWithImage(Space space, String propString) {
        super(space, propString);
        initialRent = space.getRent0();
        cost = space.getCost();
        model.addListener(this);
        imageView.setFitHeight(70);
        imageView.setFitWidth(130);
        imageView.setPreserveRatio(true);
        addNodeViewBox(imageView);
    }

    public Image getImage() {
        return image;
    }

    protected void initializeImageView(String imagePath) {
        image = new Image(imagePath);
        imageView.setImage(image);
        double necessaryHeight = (double) imageView.getBoundsInParent().getHeight();
        imageView.setFitHeight(necessaryHeight);
    }

}
