/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.layout.VBox;
import monopoly.Space;

/*
 * VBox die alle info rond 'tax' types weergeeft
 * @author Jorne Biccler
 */
public class TaxBox extends InfoBox {

    private TaxLabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;
    private int taxAmount;

    public TaxBox(Space space, String propString) {
        super(space, propString);
        taxAmount = space.getAmount();
        String imageURL = "/resources/" + space.getType().toLowerCase() + ".png";
        replaceImageBox(new ImageBox(imageURL));
        String fxmlPath = "/infoHolders/TaxLabelBox.fxml";
        labelBoxCompanion = new TaxLabelBoxCompanion(taxAmount);
        labelBox = new LabelBox(labelBoxCompanion, fxmlPath);
        addNodeLabelBox(labelBox);

    }

    public int getTaxAmount() {
        return taxAmount;
    }

}
