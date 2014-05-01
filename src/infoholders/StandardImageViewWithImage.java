/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoholders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Klasse die een imageView voorstelt zoals die zo vaak gebruikt wordt in deze
 * context (i.e. met de juiste breedte/hoogte/...
 * 
 * @author Jorne Biccler
 */
public class StandardImageViewWithImage extends ImageView {

    private final Image image;

    public StandardImageViewWithImage(String imagePath) {
        image = new Image(imagePath);
        setImage(image);
        setFitHeight(70);
        setFitWidth(130);
        setPreserveRatio(true);
    }

}
