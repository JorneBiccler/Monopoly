/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import javafx.scene.layout.VBox;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class SpecialBox extends InfoBox{

    public SpecialBox(Space space, String propString) {
        super(space, propString);
        String imageURL = "/resources/" + space.getType().toLowerCase() + ".png";
        replaceImageBox(new ImageBox(imageURL));
    }
    
}
