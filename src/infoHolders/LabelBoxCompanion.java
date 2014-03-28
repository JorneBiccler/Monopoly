/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import javafx.scene.control.Label;

/**
 *
 * @author jorne
 */
public class LabelBoxCompanion {
    public Label infoLabel;
    private String infoProp;

    public LabelBoxCompanion(String infoProp) {
        this.infoProp = infoProp;
    }
    public void initialize(){
        infoLabel.setText(infoProp);
    }
    public void changeInfoLabelText(String infoString){
        infoLabel.setText(infoString);
    }
    
}
