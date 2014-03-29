/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import monopoly.Space;

/*
 * Klasse die een VBox uitbreidt, en twee kinderen heeft, een VBox viewBox en
 * een VBox labelBox. deze klasse houdt ook de positie en het space-object
 * waarvoor hij informatie levert bij,
 *
 * @author Jorne Biccler
 */
public class InfoBox extends VBox {

    private int position;
    private Space space;
    private InfoBoxCompanion companion;

    public InfoBox(Space space, String propString) {

        this.position = space.getPosition();
        try {
            FXMLLoader loader = new FXMLLoader(
                    InfoBox.class.getResource("InfoBox.fxml"));
            loader.setRoot(this);
            this.companion = new InfoBoxCompanion(space, propString);
            loader.setController(companion);
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        companion.getLabelBox().getStyleClass().add("labelBox");

    }

    public int getPosition() {
        return position;
    }

    // methode die de ImageBox vervangt
    public void replaceImageBox(VBox imageBox) {
        getChildren().set(0, imageBox);
    }

    // methode die extra kinderen toevoegd aan de LabelBox
    public void addNodeLabelBox(Node node) {
        companion.getLabelBox().getChildren().add(node);
    }

}
