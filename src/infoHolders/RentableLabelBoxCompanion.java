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
public class RentableLabelBoxCompanion extends PurchasableLabelBoxCompanion{
    public Label rentLabel;
    private int currentRent;
    public RentableLabelBoxCompanion(String infoProp, int cost, int currentRent) {
        super(infoProp, cost);
        this.currentRent = currentRent;
    }
    public void initialize(){
        super.initialize();
        rentLabel.setText("huur:  €" + currentRent);
    }
    public void changeRentLabel(int currentRent){
        rentLabel.setText("huur:  €" + currentRent);
    }
    
}
