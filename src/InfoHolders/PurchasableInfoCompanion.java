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
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class PurchasableInfoCompanion extends InfoCompanion{
    public Label infoLabel;
    public Label kostLabel;
    public Label ownerLabel;
    private Player owner;
    private int cost;

    public PurchasableInfoCompanion(Space space, String infoProp) {
        super(space, infoProp);
        cost = space.getCost();
    }
    public void initialize(){
        infoLabel.setText(getInfoProp());
        kostLabel.setText("kost:" + cost);
        ownerLabel.setText("Huidige Eigenaar: " + "niemand" );
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        ownerLabel.setText("Huidige Eigenaar: " + owner.getName());
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    
    
}
