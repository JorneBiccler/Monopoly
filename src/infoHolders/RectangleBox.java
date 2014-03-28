/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jorne
 */
public class RectangleBox extends VBox{
        private RectangleBoxCompanion rectangleCompanion;

    public RectangleBox(Color color) {
        try {
            FXMLLoader imageLoader = new FXMLLoader(
                    getClass().getResource("RectangleBox.fxml"));
            imageLoader.setRoot(this);
            rectangleCompanion = new RectangleBoxCompanion(color);
            imageLoader.setController(rectangleCompanion);
            imageLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }    
    public void changeRectangleColor(Color color ){
        rectangleCompanion.changeRectangleColor(color);
    }
}
