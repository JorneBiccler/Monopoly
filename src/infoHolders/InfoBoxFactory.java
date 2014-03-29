/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoHolders;

import monopoly.Space;

/*
 * Factory klasse die InfoBoxen creëert.
 * @author jorne
 */
public interface InfoBoxFactory {

    public InfoBox create(Space space, String infoProp);
}
