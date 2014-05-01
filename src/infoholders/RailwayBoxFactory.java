/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor RailwayBoxen.
 *
 * @author Jorne Biccler
 */
public class RailwayBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp ) {
        return new RailwayBox(space, infoProp);
    }

}
