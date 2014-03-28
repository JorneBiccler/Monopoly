/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 *
 * @author jorne
 */
public class ImageBox extends VBox {

    private ImageInitializerCompanion imageCompanion;

    public ImageBox(String imageURL) {
        try {
            FXMLLoader imageLoader = new FXMLLoader(
                    getClass().getResource("ImageInitializer.fxml"));
            imageLoader.setRoot(this);
            imageCompanion = new ImageInitializerCompanion(imageURL);
            imageLoader.setController(imageCompanion);
            imageLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getStyleClass().add("center");
        prefHeightProperty().bind(imageCompanion.getImageView().fitHeightProperty());
    }
}
