/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import basicgameinfo.SpaceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.GameComponent;
import monopoly.GameModel;

/**
 * VBox die alle info rond 'tax' types weergeeft
 *
 * @author Jorne Biccler
 */
public class TaxBox extends InfoBox implements HasImage {

    private final ImageView imageView;

    private final TaxLabelBoxCompanion labelBoxCompanion;
    private final int taxAmount;

    public TaxBox(Space space, String propString) {
        super(space, propString);
        if (SpaceType.TAX != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        taxAmount = space.getAmount();
        String imagePath = "/resources/" + space.getType().toLowerCase() + ".png";
        imageView = new StandardImageViewWithImage(imagePath);
        addNodeViewBox(imageView);
        String labelBoxFxmlPath = "TaxLabelBox.fxml";
        labelBoxCompanion = new TaxLabelBoxCompanion(taxAmount);
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    /**
     * De huidige speler moet het gespecificieerde bedrag betalen waarna
     * dit aan de bonuspot wordt toegevoegd.
     */
    @Override
    public void doAction(GameModel gameModel) {
        gameModel.getCurrentPlayer().decreaseBalance(taxAmount);
        gameModel.increaseJackpot(taxAmount);
        GameComponent.logListWrapper.addMessage("payTax", new Object[]{
            gameModel.getCurrentPlayer().getName(), taxAmount});
        gameModel.nextTurn();
    }

    @Override
    public Image getImage() {
        return imageView.getImage();
    }

}
