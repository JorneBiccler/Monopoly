/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private  String initialCurrentPlayerStr;

    
    private final Image backgroundImage = new Image("/resources/bord.png");
    
    public void setObservableLogListView(ObservableList<String> list){
        logListView.setItems(list);
    }
    
    public void initialize() {
        backgroundView.setImage(backgroundImage);
        initialCurrentPlayerStr = currentPlayer.getText();
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
    public void setCurrentPlayer(Player player){
        currentPlayer.setText(initialCurrentPlayerStr + player.getName());
    }
    
    
    public void addNodeRightVBox(Node node){
        rightVBox.getChildren().add(node);
    }
    
    
}
