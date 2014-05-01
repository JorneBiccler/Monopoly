/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */

package infoholders;

import basicgameinfo.Space;

/**
 * Factory voor JailBoxen.
 * @author Jorne Biccler
 */
public class JailBoxFactory implements InfoBoxFactory{

    @Override
    public InfoBox create(Space space, String infoProp) {
       return new JailBox( space,  infoProp);
    }
    
}
