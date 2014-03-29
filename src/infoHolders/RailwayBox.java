/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.layout.VBox;
import monopoly.Space;

/*
 * InfoBox met extra informatie rond het specifieke RailWay type.
 * @author Jorne Biccler
 */
public class RailwayBox extends InfoBox {

    private LabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;

    public RailwayBox(Space space, String propString) {
        super(space, propString);
        String imageURL = "/resources/railway.png";
        replaceImageBox(new ImageBox(imageURL));
        labelBoxCompanion = new RentableLabelBoxCompanion(space.getCost(), space.getRent0());
        String fxmlPath = "/infoHolders/RentableLabelBox.fxml";
        labelBox = new LabelBox(labelBoxCompanion, fxmlPath);
        addNodeLabelBox(labelBox);
    }
}
