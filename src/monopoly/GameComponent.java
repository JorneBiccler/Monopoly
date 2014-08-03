/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import dialogs.InfoDialog;
import gamecomponents.PlayerTabComponent;
import java.io.IOException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import monopoly.dice.Dice;

/**
 * Component die de grote lijnen van de spelstatus weergeeft, i.e. de
 * achtergrond wordt geladen,er wordt een box met allerlij informatie rond
 * spelers, en vorige gebeurtenissen gecreerd, op het gepaste moment worden ook
 * de dobbselstenen aangemaakt, indien de window met dit component gesloten
 * wordt, dan wordt ook het window met de dobbelstenen gesloten.
 *
 * @author Jorne Biccler
 */
public class GameComponent extends HBox implements InvalidationListener {

    private final GameCompanion companion;
    private GameModel gameModel;
    private Dice dice;
    public static ListWrapper logListWrapper = new ListWrapper();

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
        this.gameModel = new GameModel(playerList, this);
        dice = new Dice();
        gameModel.addListener(this);
        companion.fillBoardStackPane(new MonopolyBoardComponent(gameModel));
        companion.setRightBoxVisible();
        for (Player pl : playerList) {
            PlayerTabComponent tempTabComp = new PlayerTabComponent(pl);
            Tab tempTab = new Tab(pl.getName());
            tempTab.setContent(tempTabComp);
            companion.addTab(tempTab);
            pl.addListener(this);
        }
        invalidated(gameModel);
        getDiceButton().setOnAction(new DiceButtonHandler(new DiceHandler()));
        getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                dice.close();
            }
        });
    }

    /**
     * De label met de huidige speler wordt aangepast, en als er een speler is
     * die een negatieve balans is wordt de disableGame methode opgeroepen.
     */
    @Override
    public void invalidated(Observable o) {
        companion.setCurrentPlayer(gameModel.getCurrentPlayer());
        companion.setJackpot(gameModel.getJackpot());
        for (Player pl : gameModel.getListOfPlayers()) {
            if (pl.getBalance() < 0) {
                disableGame();
            }
        }

    }

    public Button getDiceButton() {
        return companion.getDiceButton();
    }

    public Dice getDice() {
        return dice;
    }

    /**
     * binnenklasse die een standaard diceCallback implementeert, in het
     * bijzonder wordt het gameModel van het feit dat de dobbelstene werden
     * gegooid op de hoogte gebracht.
     */
    private class DiceHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            int diceSum = 0;
            for (int value : dice.getLastRoll()) {
                diceSum += value;
            }
            logListWrapper.addMessage("diceThrow", new Object[]{gameModel.getCurrentPlayer().getName(),
               diceSum});
            gameModel.diceAction(diceSum);
        }
    }

    /**
     * binneklasse die de actie van de 'gooi met de dobbelsteen' knop voorstelt,
     * in het bijzonder wordt er de callback van de dice ingesteldt, met behulp
     * van de binneklasse DiceHandler.
     */
    private class DiceButtonHandler implements EventHandler<ActionEvent> {

        private DiceHandler diceHandler;

        public DiceButtonHandler(DiceHandler diceHandler) {
            this.diceHandler = diceHandler;
        }

        @Override
        public void handle(ActionEvent t) {
            getDiceButton().setDisable(true);
            dice.roll(diceHandler);
        }
    }

    /**
     * method die opgeroepen wordt als het spel moet stoppen, er wordt een
     * dialoog weergegeven die aangeeft wie gewonnen is. Hierna sluit het gehele
     * spel.
     */
    public void disableGame() {
        Player winner = gameModel.getCurrentPlayer();
        for (Player pl : gameModel.getListOfPlayers()) {
            if (pl.getBalance() > winner.getBalance()) {
                winner = pl;
            }
        }
        companion.getDiceButton().setDisable(true);
        InfoDialog dialog = new InfoDialog("De winaar is " + winner.getName(), "Gameover",
                new EventHandler<Event>() {
                    @Override
                    public void handle(Event t) {
                        getScene().getWindow().hide();
                        dice.close();
                    }
                });
        dialog.show();
    }

}
