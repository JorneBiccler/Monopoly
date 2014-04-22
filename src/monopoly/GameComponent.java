/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import gamecomponents.PlayerTabComponent;
import java.io.IOException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Jorne Biccler
 */
public class GameComponent extends HBox implements InvalidationListener {

    private ObservableList<Player> playerList;
    private final GameCompanion companion;
    private GameModel gameModel;
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

    public void initializeGame(ObservableList<Player> playerList) throws IOException, JAXBException {
        this.playerList = playerList;
        this.gameModel = new GameModel(playerList);
        gameModel.addListener(this);
        companion.fillBoardStackPane(new MonopolyBoardComponent(gameModel));
        companion.setRightBoxVisible();
        for (Player pl : playerList) {
            PlayerTabComponent tempTabComp = new PlayerTabComponent(pl);
            Tab tempTab = new Tab(pl.getName());
            tempTab.setContent(tempTabComp);
            companion.addTab(tempTab);
        }
        diceButton.setStandardDiceHandler(new StandardDiceHandler());
        companion.addNodeRightVBox(diceButton);

    }

    @Override
    public void invalidated(Observable o) {
        companion.setCurrentPlayer(gameModel.getCurrentPlayer());
    }

    class StandardDiceHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            int currentPos =  gameModel.getCurrentPlayer().getCurrentPosition();
            int diceSum = 0;
            for(int value: diceButton.getLastRoll()){
                diceSum +=value;
            }
                    
            int newPos = (currentPos + diceSum)%40;
            gameModel.getCurrentPlayer().setCanDoAction(true);
            gameModel.getCurrentPlayer().setCurrentPosition(newPos);
        }

    }



}
