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
public class RailwayBox extends InfoBox {

    private LabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;

    public RailwayBox(Space space, String propString) {
        super(space, propString);
        String imageURL = "/resources/railway.png";
        replaceImageBox(new ImageBox(imageURL));
        labelBoxCompanion = new RentableLabelBoxCompanion(propString, space.getCost(), space.getRent0());
        String fxmlPath = "/infoHolders/RentableLabelBox.fxml";
        labelBox = new LabelBox(labelBoxCompanion, fxmlPath);
        replaceLabelBox(labelBox);
    }
}
