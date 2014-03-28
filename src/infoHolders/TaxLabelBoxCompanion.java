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
public class TaxLabelBoxCompanion extends LabelBoxCompanion{
    public Label amountLabel;
    private int amount;

    public TaxLabelBoxCompanion(int amount, String infoProp) {
        super(infoProp);
        this.amount = amount;
    }
    @Override
    public void initialize(){
        super.initialize();
        amountLabel.setText("\u20ac" + amount);
    }
    public void setAmountLabel(int amount){
         amountLabel.setText("\u20ac" + amount);
    }
    
}
