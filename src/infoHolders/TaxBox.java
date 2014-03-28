/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class TaxBox extends InfoBox {

    private TaxLabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;

    public TaxBox(Space space, String propString) {
        super(space, propString);
        String imageURL = "/resources/" + space.getType().toLowerCase() + ".png";
        replaceImageBox(new ImageBox(imageURL));
        String fxmlPath = "/infoHolders/TaxLabelBox.fxml";
            labelBoxCompanion = new TaxLabelBoxCompanion(space.getAmount(), propString);
        labelBox = new LabelBox(labelBoxCompanion, fxmlPath);
        replaceLabelBox(labelBox);

    }

}
