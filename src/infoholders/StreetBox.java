/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.paint.Color;
import monopoly.*;

/**
 * VBox die alle info rond 'street' types weergeeft
 *
 * @author Jorne Biccler
 */
public class StreetBox extends PurchasableBox implements InvalidationListener{

    private final RentableLabelBoxCompanion labelBoxCompanion;
    private final RectangleBox rectBox;
    private final int initialrent;
    private final int cost;
    private Area area;

    public StreetBox(Space space, String propString, Area area) {
        super(space, propString);
        if (SpaceType.STREET != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        this.cost = space.getCost();
        this.initialrent = space.getRent0();
        rectBox = new RectangleBox(area.getAreaColor());
        addNodeViewBox(rectBox);
        labelBoxCompanion = new RentableLabelBoxCompanion(space.getCost(), initialrent);
        String labelBoxFxmlPath = "RentableLabelBox.fxml";
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
        this.area = area;
    }

    public int getRent() {
        if(model.getOwner() != null){
            int count1 = 0;
            int count2 = 0;
            for(Space sp : MonopolyBoardComponent.board.getSpaces()){
                if(sp.getType() == SpaceType.STREET){
                    if(space.getArea().equals(sp.getArea())){
                        count1++;
                    }
                }
            }
            for(InfoBox box : model.getOwner().getOwnedProperties()){
                if(box.getSpaceType() == SpaceType.STREET){
                    StreetBox cast = (StreetBox) box;
                    if(cast.getArea().equals(getArea())){
                    count2++;
                    }
                }
            }
            if(count1 == count2){
                return 2*initialrent;
            }
        }
        return initialrent;
    }

    public Area getArea() {
        return area;
    }

    /**
     * methode die de area 'set' en ook de kleur van de Rectangle aanpast
     */
    public void setArea(Area area) {
        this.area = area;
        rectBox.changeRectangleColor(area.getAreaColor());
    }

    public Color getColor() {
        return area.getAreaColor();
    }

     public int getCost() {
        return cost;
    }

    @Override
    public void invalidated(Observable o) {
        labelBoxCompanion.renewRent(getRent());
        labelBoxCompanion.renewOwner(model.getOwner());           
    }

}
