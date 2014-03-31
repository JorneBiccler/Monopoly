/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import monopoly.Space;

/*
 * InfoBox horende bij 'speciale' types, bvb. START,JAIL,..
 * i.h.b. types waarbij er enkel een infolabel en image nodig is.
 * @author Jorne Biccler
 */
public class SpecialBox extends InfoBox {

    public SpecialBox(Space space, String propString) {
        super(space, propString);
        String imageURL = "/resources/" + space.getType().toLowerCase() + ".png";
        replaceViewBox(new ImageBox(imageURL));
    }

}
