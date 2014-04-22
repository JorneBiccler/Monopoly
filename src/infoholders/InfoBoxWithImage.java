/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoholders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.GameModel;
import monopoly.Space;

/**
 *
 * @author Jorne Biccler
 */
public abstract class InfoBoxWithImage extends InfoBox {

    protected Image image;
    protected final ImageView imageView = new ImageView();

    public InfoBoxWithImage(Space space, String propString, GameModel gameModel) {
        super(space, propString, gameModel);
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
