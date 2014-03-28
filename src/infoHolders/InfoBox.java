/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import monopoly.MonopolyBoardModel;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class InfoBox extends VBox {

    private int position;
    private Space space;
    private InfoBoxCompanion companion;
    private MonopolyBoardModel positionModel;

    public InfoBox(Space space, String propString) {

        this.position = space.getPosition();
        try {
            FXMLLoader loader = new FXMLLoader(
                    InfoBox.class.getResource("InfoBox.fxml"));
            loader.setRoot(this);

//            this.companion = factories.get(space.getType()).createInfoCompanion(space, propString);
            this.companion = new InfoBoxCompanion(space, propString);
            loader.setController(companion);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public MonopolyBoardModel getModel() {
        return positionModel;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addLabel(Label label) {
        companion.addLabel(label);
    }

    public void replaceImageBox(VBox imageBox) {
        getChildren().set(0, imageBox);
    }

    public void replaceLabelBox(VBox labelBox) {
        getChildren().set(1, labelBox);
    }

}
