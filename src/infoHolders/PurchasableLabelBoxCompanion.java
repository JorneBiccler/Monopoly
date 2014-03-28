/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import javafx.scene.control.Label;
import monopoly.Player;

/**
 *
 * @author jorne
 */
public class PurchasableLabelBoxCompanion extends LabelBoxCompanion{
    private int cost;
    public Label costLabel;
    private Player owner;
    public Label ownerLabel;
    public Label ownerTextLabel;
    public PurchasableLabelBoxCompanion(String infoProp, int cost) {
        super(infoProp);
        this.cost = cost;
    }
    
    public void initialize(){
        super.initialize();
        costLabel.setText("kostprijs:  €"+cost);
        ownerTextLabel.setText("huidige eigenaar:");
        ownerLabel.setText("niamand");
        ownerLabel.getStyleClass().add("center");
    }
    public void renewCost(int cost){
        this.cost = cost;
        costLabel.setText("kostprijs:  €"+cost);
    }
    
    public Player getOwner() {
        return owner;
    }

    public void renewtOwner(Player owner) {
        this.owner = owner;
        ownerLabel.setText(owner.getName());
    }
    
    
}
