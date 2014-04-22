/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Partner klasse voor een RectangleBox
 *
 * @author Jorne Biccler
 */
public class RectangleBoxCompanion {

    public Rectangle rectangle;

    /**
     * methode die het kleur van de een rectangle verandert
     */
    public void changeRectangleColor(Color color) {
        rectangle.setFill(color);
    }
}
