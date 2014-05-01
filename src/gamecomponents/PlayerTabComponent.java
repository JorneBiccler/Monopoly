/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package gamecomponents;

import java.io.IOException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import monopoly.MonopolyBoardComponent;
import monopoly.Player;

/**
 * Standaard uitbreiding van VBox die diene om als Tab-Obsjecten te gebruiken in
 * de main gameComponent. Deze is bedoelt om de standaard informatie van de
 * status van een speler bij te houden (eigendommen, plaats, ...), en luistert
 * bijgevolg naar een speler model.
 *
 * @author Jorne Biccler
 */
public class PlayerTabComponent extends VBox implements InvalidationListener {

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
        changePositionLabel(player.getCurrentPosition());
    }

    /**
     * Als deze methode opgeroepen wordt zal het positieLabel en de balans op de
     * gepaste wijze aangepost worden.
     */
    @Override
    public void invalidated(Observable o) {
        changeBalanceLabel(player.getBalance());
        changeOwnsJailCard(player.hasJailCard());
        changePositionLabel(player.getCurrentPosition());
    }

    private void changeOwnsJailCard(boolean ownsJailCarde) {
        companion.changeOwnsJailCard(ownsJailCarde);
    }

    private void changeBalanceLabel(int balance) {
        companion.changeBalance(balance);
    }

    public Player getModel() {
        return player;
    }

    /**
     * past het positieLabel op de gepaste manier aan, gegeven de huidige
     * positie.
     */
    private void changePositionLabel(int position) {
        String propertyKey = MonopolyBoardComponent.board.getSpaceIdMap().get(position);
        companion.changePosition(MonopolyBoardComponent.boardProperties.getProperty(propertyKey));
    }

}
