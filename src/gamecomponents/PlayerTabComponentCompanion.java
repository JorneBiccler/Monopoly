/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecomponents;

import infoholders.InfoBox;
import infoholders.InfoBoxWithImage;
import infoholders.StreetBox;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import monopoly.SpaceType;

/**
 *
 * @author jorne
 */
public class PlayerTabComponentCompanion {

    public Label balanceLabel;
    public Label positionLabel;
    public ListView<InfoBox> ownedCardsListView;
    public ObservableList<InfoBox> ownedCardsList;
    private String initialPositionString;
    private String initialBalanceString;

    public PlayerTabComponentCompanion(ObservableList<InfoBox> ownedCardsList) {
        this.ownedCardsList = ownedCardsList;

    }

    public void initialize() {
        initialBalanceString = balanceLabel.getText();
        initialPositionString = positionLabel.getText();
        ownedCardsListView.setItems(ownedCardsList);

        ownedCardsListView.setCellFactory(new Callback<ListView<InfoBox>, ListCell<InfoBox>>() {
            @Override
            public ListCell<InfoBox> call(ListView<InfoBox> ownedCardsListView) {
                return new CardListCell();
            }
        });

    }

    public void changeBalance(int balance) {
        balanceLabel.setText(initialBalanceString + balance);
    }

    public void changePosition(String positionID) {
        positionLabel.setText(initialPositionString + positionID);
    }

    public class CardListCell extends ListCell<InfoBox> {

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
                    InfoBoxWithImage tempBox = (InfoBoxWithImage) infoBox;
                    ImageView tempImView = new ImageView(tempBox.getImage());
                    tempImView.setFitHeight(30);
                    tempImView.setPreserveRatio(true);
                    setGraphic(tempImView);
                    setText(tempBox.getPropString());
                }
            }
        }
    }

}
