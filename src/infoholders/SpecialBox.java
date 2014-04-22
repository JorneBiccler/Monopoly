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
 * InfoBox horende bij 'speciale' types, bvb. START,JAIL,.. i.h.b. types waarbij
 * er enkel een infolabel en image nodig is.
 *
 * @author Jorne Biccler
 */
public class SpecialBox extends InfoBoxWithImage {

    public SpecialBox(Space space, String propString, GameModel gameModel) {
        super(space, propString, gameModel);
        if (!SpaceType.SPECIAL_TYPES.contains(space.getType())) {
            throw new IllegalArgumentException("er werd een ongeldig type ingegeven");
        }
        String imagePath = "/resources/" + space.getType().toLowerCase() + ".png";
        initializeImageView(imagePath);
    }

    @Override
    public void doAction(Player currentPlayer) {
    }

}
