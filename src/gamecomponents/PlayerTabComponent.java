/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gamecomponents;

import java.io.IOException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import monopoly.Player;

/**
 *
 * @author jorne
 */
public class PlayerTabComponent extends VBox implements InvalidationListener{
    private final Player player;
    private final PlayerTabComponentCompanion companion;

    public PlayerTabComponent(Player player) {
        this.player = player;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("PlayerTabComponent.fxml"));
            loader.setRoot(this);
            this.companion = new PlayerTabComponentCompanion(player.getOwnedProperties());
            loader.setController(companion);

            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        player.addListener(this);
        changeBalanceLabel(player.getBalance());
    }

    @Override
    public void invalidated(Observable o) {
        changePositionLabel("aan te passen");
        changeBalanceLabel(player.getBalance());
        companion.changeOwnsJailCard(player.hasJailCard());
    }
    
    private void changePositionLabel(String positionID){
        companion.changePosition(positionID);
    }
    
    private void changeBalanceLabel(int balance){
        companion.changeBalance(balance);
    }
    
    public Player getModel(){
        return player;
    }
   
            
    
    
    
    
    
}
