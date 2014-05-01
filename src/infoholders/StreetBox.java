/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Area;
import basicgameinfo.Space;
import basicgameinfo.SpaceType;
import javafx.scene.paint.Color;

/**
 * Extensie van RentableBox die met een Street type correspondeert, in het
 * bijzonder is er dus een rectangle/kleur aanwezig, die met de kleur van de
 * eigenaar correspondeert.
 *
 * @author Jorne Biccler
 */
public class StreetBox extends RentableBox {

    private final RectangleBox rectBox;
    private Area area;

    public StreetBox(Space space, String propString, Area area) {
        super(space, propString);
        if (SpaceType.STREET != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        this.cost = space.getCost();
        rectBox = new RectangleBox(area.getAreaColor());
        addNodeViewBox(rectBox);
        this.area = area;
    }

    /**
     * Als de eigenaar alle straten horende bij een area heeft wordt de rent
     * verdubbeld;
     */
    public int getRent() {
        if (model.getOwner() != null) {
            if (model.getOwner().numberOwnedOfSomeArea(area) == area.getNumberOfStreets()) {
                return 2 * initialRent;
            }
        }
        return initialRent;
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

}
