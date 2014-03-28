/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class UtilityBox extends InfoBox {

    private PurchasableLabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;
    
    public UtilityBox(Space space, String propString) {
        super(space, propString);
        String imageURL = "/resources/" + space.getId().replaceAll("space.", "") + ".png";
        replaceImageBox(new ImageBox(imageURL));
        labelBoxCompanion = new PurchasableLabelBoxCompanion(propString, space.getCost());
        String fxmlURL = "/infoHolders/PurchasableLabelBox.fxml";
        labelBox = new LabelBox(labelBoxCompanion, fxmlURL);
        replaceLabelBox(labelBox);        
    }
}
