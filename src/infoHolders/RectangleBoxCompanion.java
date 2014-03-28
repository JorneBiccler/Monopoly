/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jorne
 */
public class RectangleBoxCompanion {
    public Rectangle rectangle;
    public Color color;

    public RectangleBoxCompanion(Color color) {
        this.color = color;
    }

    public void initialize(){
        rectangle.setFill(color);
    }
    public void changeRectangleColor(Color color){
        rectangle.setFill(color);
    }
}
