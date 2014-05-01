/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor GoToJailBoxen.
 *
 * @author Jorne Biccler
 */
public class GoToJailBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new GoToJailBox(space, infoProp);
    }
}
