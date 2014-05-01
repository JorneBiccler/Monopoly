/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor InfoBoxen.
 *
 * @author Jorne Biccler
 */
public interface InfoBoxFactory {

    public InfoBox create(Space space, String infoProp );
}
