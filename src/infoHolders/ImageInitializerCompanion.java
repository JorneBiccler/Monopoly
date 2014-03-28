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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jorne
 */
public class ImageInitializerCompanion {
    public ImageView imageView;
    private Image image;

    public ImageInitializerCompanion(String imageURL) {
        this.image = new Image(imageURL);
    }
    public void initialize(){
        imageView.setImage(image);
    }
    public ImageView getImageView(){
        return imageView;
    }
    

    
}
