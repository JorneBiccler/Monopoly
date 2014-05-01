/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Partnerklasse horende bij een GameComponent.
 *
 * @author Jorne Biccler
 */
public class GameCompanion {

    public ImageView backgroundView;
    public StackPane boardStackPane;
    public VBox rightVBox;
    public TabPane playerTabPane;
    public ListView<String> logListView;
    public Label currentPlayer;
    public Label jackpotLabel;
    public Button diceButton;
    private String initialCurrentPlayerStr;
    private String initialJackpotLabelStr;
    private final Image backgroundImage = new Image("/resources/bord.png");

    public void initialize() {
        backgroundView.setImage(backgroundImage);
        initialCurrentPlayerStr = currentPlayer.getText();
        initialJackpotLabelStr= jackpotLabel.getText();
        logListView.setItems(GameComponent.logListWrapper.getList());
    }

    public void setObservableLogListView(ObservableList<String> list) {
        logListView.setItems(list);
    }

    public void fillBoardStackPane(Node node) {
        boardStackPane.getChildren().add(node);
    }

    public void addTab(Tab tab) {
        playerTabPane.getTabs().add(tab);
    }

    public void setRightBoxVisible() {
        rightVBox.setVisible(true);
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer.setText(initialCurrentPlayerStr + player.getName());
    }

    public void addNodeRightVBox(Node node) {
        rightVBox.getChildren().add(node);
    }

    public Button getDiceButton() {
        return diceButton;
    }
    
    public void setJackpot(int amount){
        jackpotLabel.setText(initialJackpotLabelStr + amount);
    }

}
