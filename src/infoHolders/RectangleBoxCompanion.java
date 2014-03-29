/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Companion klasse voor een RectangleBox
 * @author Jorne Biccler
 */
public class RectangleBoxCompanion {

    public Rectangle rectangle;
    public Color color;

    public RectangleBoxCompanion(Color color) {
        this.color = color;
    }

    public void initialize() {
        rectangle.setFill(color);
    }

    //methode die het kleur van de een rectangle verandert
    public void changeRectangleColor(Color color) {
        rectangle.setFill(color);
    }
}
