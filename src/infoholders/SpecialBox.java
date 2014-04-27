/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import monopoly.Space;

/**
 * InfoBox horende bij 'speciale' types, bvb. START,JAIL,.. i.h.b. types waarbij
 * er enkel een infolabel en image nodig is.
 *
 * @author Jorne Biccler
 */
abstract class SpecialBox extends InfoBoxWithImage {

    public SpecialBox(Space space, String propString ) {
        super(space, propString);
        String imagePath = "/resources/" + space.getType().toLowerCase() + ".png";
        initializeImageView(imagePath);
    }



}
