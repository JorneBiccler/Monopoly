/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor StartBoxen.
 *
 * @author Jorne Biccler
 */
public class StartBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new StartBox(space, infoProp);
    }
}
