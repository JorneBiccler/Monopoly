/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import monopoly.Space;

/*
 * VBox die alle info rond 'utility' types weergeeft
 * @author jorne
 */
public class UtilityBox extends InfoBox {

    private PurchasableLabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;
    private int cost;

    public UtilityBox(Space space, String propString) {
        super(space, propString);
        cost = space.getCost();
        String imageURL = "/resources/" + space.getId().replaceAll("space.", "") + ".png";
        replaceViewBox(new ImageBox(imageURL));
        labelBoxCompanion = new PurchasableLabelBoxCompanion(cost);
        String fxmlURL = "/infoHolders/PurchasableLabelBox.fxml";
        labelBox = new LabelBox(labelBoxCompanion, fxmlURL);
        addNodeBottomBox(labelBox);
    }

    public int getCost() {
        return cost;
    }

}
