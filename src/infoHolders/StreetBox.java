/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import monopoly.Area;
import monopoly.Space;

/*
 * VBox die alle info rond 'street' types weergeeft
 * @author Jorne Biccler
 */
public class StreetBox extends InfoBox {

    private RentableLabelBoxCompanion labelBoxCompanion;
    private VBox labelBox;
    private RectangleBox rectBox;
    private int[] rentAr;
    private int currentRentIndex;
    private Area area;

    public StreetBox(Space space, String propString) {
        super(space, propString);
        this.rentAr = space.getRentArray();
        rectBox = new RectangleBox(Color.AQUA);
        replaceImageBox(rectBox);
        labelBoxCompanion = new RentableLabelBoxCompanion(space.getCost(), rentAr[0]);
        String fxmlURL = "/infoHolders/RentableLabelBox.fxml";
        labelBox = new LabelBox(labelBoxCompanion, fxmlURL);
        addNodeLabelBox(labelBox);
    }

    public int getCurrentRentIndex() {
        return currentRentIndex;
    }

    //methode de 'rentIndex' verandert en het label aanpast
    public void setCurrentRentIndex(int currentRentIndex) {
        this.currentRentIndex = currentRentIndex;
        labelBoxCompanion.changeRentLabel(currentRentIndex);
    }

    public int getCurrentRent() {
        return rentAr[currentRentIndex];
    }

    public Area getArea() {
        return area;
    }

    //methode die de area 'set' en ook de kleur van de Rectangle aanpast
    public void setArea(Area area) {
        this.area = area;
        rectBox.changeRectangleColor(area.getAreaColor());
    }

}
