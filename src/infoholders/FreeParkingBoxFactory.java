/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor FreeParkingBoxen.
 *
 * @author Jorne Biccler
 */
public class FreeParkingBoxFactory implements InfoBoxFactory {

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new FreeParkingBox(space, infoProp);
    }
}
