/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Partner klasse die bij een MonopolyBoard hoort
 *
 * @author Jorne Biccler
 */
public class MonopolyBoardCompanion {

    public HBox hBoxUp;
    public VBox vBoxLeft;
    public HBox hBoxDown;
    public VBox vBoxRight;
    public StackPane infoStackPane;
  

    /**
     * Methode die een de elementen van de array in de gepaste volgorde plaatst
     * in de bijhorende boxen.
     * @param ar some array with nodes
     */
    public void placeSpaceBoxes(Node[] ar) {
        hBoxDown.getChildren().add(ar[0]);
        hBoxUp.getChildren().add(ar[10]);
        for (int i = 1; i < 10; i++) {
            vBoxLeft.getChildren().add(ar[10 - i]);
            vBoxRight.getChildren().add(ar[20 + i]);
            hBoxUp.getChildren().add(ar[10 + i]);
            hBoxDown.getChildren().add(ar[40 - i]);

        }
        hBoxUp.getChildren().add(ar[20]);
        hBoxDown.getChildren().add(ar[30]);
    }

    public void setBoardCenter(Node node) {
        infoStackPane.getChildren().add(node);
    }

}
