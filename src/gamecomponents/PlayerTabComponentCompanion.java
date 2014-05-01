/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package gamecomponents;

import basicgameinfo.SpaceType;
import infoholders.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
 * Partner klasse horende bij een PlayerTabComponent, deze houdt de initiele
 * tekst van de labels zoals gespecificieerd in het fxml bestand bij.
 *
 * @author Jorne Biccler
 */
public class PlayerTabComponentCompanion {

    public Label balanceLabel;
    public Label positionLabel;
    public Label jailCardLabel;
    public ListView<InfoBox> ownedCardsListView;
    public ObservableList<InfoBox> ownedCardsList;
    private String initialPositionString;
    private String initialBalanceString;
    private String initialJailCardString;

    public PlayerTabComponentCompanion(ObservableList<InfoBox> ownedCardsList) {
        this.ownedCardsList = ownedCardsList;
    }

    /**
     * standaard initialize methode, merk op dat de listview een nieuwe
     * cellFactory krijgt, deze geeft CardListCells terug.
     */
    public void initialize() {
        initialBalanceString = balanceLabel.getText();
        initialPositionString = positionLabel.getText();
        ownedCardsListView.setItems(ownedCardsList);
        initialJailCardString = jailCardLabel.getText();
        jailCardLabel.setText(initialJailCardString + "neen");
        ownedCardsListView.setCellFactory(new Callback<ListView<InfoBox>, ListCell<InfoBox>>() {
            @Override
            public ListCell<InfoBox> call(ListView<InfoBox> ownedCardsListView) {
                return new CardListCell();
            }
        });
    }

    public void changeOwnsJailCard(boolean ownsJailCard) {
        if (ownsJailCard) {
            jailCardLabel.setText(initialJailCardString + "ja");
        } else {
            jailCardLabel.setText(initialJailCardString + "neen");
        }
    }

    public void changeBalance(int balance) {
        balanceLabel.setText(initialBalanceString + balance);
    }

    public void changePosition(String positionID) {
        positionLabel.setText(initialPositionString + positionID);
    }

    /**
     * Binnenklasse die instelt hoe de cellen van de ListView eruit moeten zien
     * deze hebben de gewenste graphic en tekst.
     */
    private class CardListCell extends ListCell<InfoBox> {

        @Override
        public void updateItem(InfoBox infoBox, boolean empty) {
            super.updateItem(infoBox, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (infoBox.getSpaceType() == SpaceType.STREET) {
                    StreetBox tempBox = (StreetBox) infoBox;
                    setGraphic(new Rectangle(30, 30, tempBox.getColor()));
                    setText(infoBox.getPropString());
                } else {
                    HasImage hasImage = (HasImage) infoBox;
                    ImageView tempImView = new ImageView(hasImage.getImage());
                    tempImView.setFitHeight(30);
                    tempImView.setFitWidth(30);
                    tempImView.setPreserveRatio(true);
                    setGraphic(tempImView);
                    setText(infoBox.getPropString());
                }
            }
        }
    }

}
