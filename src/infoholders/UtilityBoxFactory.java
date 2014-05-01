/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor UtilityBoxen.
 *
 * @author Jorne Biccler
 */
public class UtilityBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new UtilityBox(space, infoProp);
    }

}
