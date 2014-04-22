/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.GameModel;
import monopoly.Player;
import monopoly.Space;
import monopoly.SpaceType;

/**
 * VBox die alle info rond 'tax' types weergeeft
 *
 * @author Jorne Biccler
 */
public class TaxBox extends InfoBoxWithImage {

    private final TaxLabelBoxCompanion labelBoxCompanion;
    private final int taxAmount;

    public TaxBox(Space space, String propString, GameModel gameModel) {
        super(space, propString, gameModel);
        if (SpaceType.TAX != space.getType()) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        taxAmount = space.getAmount();
        String imagePath = "/resources/" + space.getType().toLowerCase() + ".png";
        initializeImageView(imagePath);
        String labelBoxFxmlPath = "TaxLabelBox.fxml";
        labelBoxCompanion = new TaxLabelBoxCompanion(taxAmount);
        createLabelBox(labelBoxCompanion, labelBoxFxmlPath);
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    @Override
    public void doAction(Player currentPlayer) {
        currentPlayer.setBalance(currentPlayer.getBalance() - taxAmount);
    }

     
    
}
