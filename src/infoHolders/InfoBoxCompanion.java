/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class InfoBoxCompanion {
    private Space space;
    public Label infoLabel;
    public VBox imageBox;
    public VBox infoBox;
    private String infoProp;
    public InfoBoxCompanion(Space space, String infoProp) {
        this.space = space;
        this.infoProp = infoProp;
    }
    public void initialize(){
        infoLabel.setText(infoProp);
    }
    
}
