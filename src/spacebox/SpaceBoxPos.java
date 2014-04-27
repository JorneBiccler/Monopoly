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

    VERTUP("spaceBox-VertUp", Orientation.VERTICAL, 60, 120,90),
    VERTDOWN("spaceBox-VertDown", Orientation.VERTICAL, 60, 120,270),
    HORLEFT("spaceBox-HorLeft", Orientation.HORIZONTAL, 120, 60,0),
    HORRIGHT("spaceBox-HorRight", Orientation.HORIZONTAL, 120, 60,180),
    CORNER("spaceBox-Corner", Orientation.HORIZONTAL, 120, 120,0);

    private final String cssClass;
    private final Orientation orientation;
    private final double width;
    private final double height;
    private final double rotate;
    private SpaceBoxPos(String cssClass, Orientation orientation, double width, double height,double rotate) {
        this.height = height;
        this.width = width;
        this.cssClass = cssClass;
        this.orientation = orientation;
        this.rotate = rotate;
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

    public double getRotate() {
        return rotate;
    }

}
