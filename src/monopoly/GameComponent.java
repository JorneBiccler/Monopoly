/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import gamecomponents.DiceButton;
import gamecomponents.PlayerTabComponent;
import java.io.IOException;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jorne Biccler
 */
public class GameComponent extends HBox implements InvalidationListener {

    private ObservableList<Player> playerList;
    private final GameCompanion companion;
    private GameModel gameModel;
    private ObservableList<String> logList = FXCollections.observableArrayList();
    public static final DiceButton diceButton = new DiceButton(); 
    
    public GameComponent() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Game.fxml"));
            loader.setRoot(this);
            this.companion = new GameCompanion();
            loader.setController(companion);

            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void initializeGame(ObservableList<Player> playerList) {
        this.playerList = playerList;
        this.gameModel = new GameModel(playerList,logList);
        gameModel.addListener(this);
        companion.setCurrentPlayer(gameModel.getCurrentPlayer());
        companion.fillBoardStackPane(new MonopolyBoardComponent(gameModel));
        companion.setRightBoxVisible();
        for (Player pl : playerList) {
            PlayerTabComponent tempTabComp = new PlayerTabComponent(pl);
            Tab tempTab = new Tab(pl.getName());
            tempTab.setContent(tempTabComp);
            companion.addTab(tempTab);
        }
        diceButton.setStandardDiceHandler(new StandardDiceHandler());
        diceButton.setJailHandler(new JailDiceHandler());
        companion.addNodeRightVBox(diceButton);
        
    }

    @Override
    public void invalidated(Observable o) {
        companion.setCurrentPlayer(gameModel.getCurrentPlayer());
    }

    class StandardDiceHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            int diceSum = 0;
            for(int value: diceButton.getLastRoll()){
                diceSum +=value;
            }   
            gameModel.standardDiceAction(diceSum);
        }

    }
    
    class JailDiceHandler implements EventHandler<ActionEvent>{
                @Override
        public void handle(ActionEvent t) {
            List<Integer> lastRoll = diceButton.getLastRoll();
            if(lastRoll.get(0).equals(lastRoll.get(1))){
                gameModel.getCurrentPlayer().setInJail(false);
                gameModel.relativeModelPosition(2*lastRoll.get(0),true,true);
            }
            else{
                gameModel.doGameAction();
            }
        }
        
    }


}
