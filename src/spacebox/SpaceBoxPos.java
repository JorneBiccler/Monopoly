/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package spacebox;

import javafx.geometry.Orientation;

/**
 * Enum type die de soorten vakjes voorstelt. En voor deze de bijhorende layout
 * eigenschappen mee kan geven.
 *
 * @author Jorne Biccler
 */
public enum SpaceBoxPos {

    VERTUP("spaceBox-VertUp", Orientation.VERTICAL, 60, 120),
    VERTDOWN("spaceBox-VertDown", Orientation.VERTICAL, 60, 120),
    HORLEFT("spaceBox-HorLeft", Orientation.HORIZONTAL, 120, 60),
    HORRIGHT("spaceBox-HorRight", Orientation.HORIZONTAL, 120, 60),
    CORNER("spaceBox-Corner", Orientation.HORIZONTAL, 120, 120);

    private final String cssClass;
    private final Orientation orientation;
    private final double width;
    private final double height;

    private SpaceBoxPos(String cssClass, Orientation orientation, double width, double height) {
        this.height = height;
        this.width = width;
        this.cssClass = cssClass;
        this.orientation = orientation;
    }

    public String getCSSClass() {
        return cssClass;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}
