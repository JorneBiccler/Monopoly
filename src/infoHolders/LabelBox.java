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
public class LabelBox extends VBox{
    private LabelBoxCompanion companion;
    public LabelBox(LabelBoxCompanion companion, String fxmlpath) {
    
           try {
            FXMLLoader loader = new FXMLLoader(
                    companion.getClass().getResource(fxmlpath));
            loader.setRoot(this);
            this.companion = companion;
            loader.setController(companion);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
           this.getStyleClass().add("center");
    }

    public LabelBoxCompanion getCompanion() {
        return companion;
    }
    
}
