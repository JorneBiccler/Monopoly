/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package basicgameinfo;

import java.util.EnumSet;

/**
 * Opsom type die de verschillende type's van vakjes bijhoudt. Deze houdt ook
 * een enumSet van 'speciale' types bij, dit zijn de types die slechts één label
 * met info hebben, en een enumSet van de types die 'koopbaar' zijn bij
 *
 * @author Jorne Biccler
 */
public enum SpaceType {

    START, JAIL, CHANCE, CHEST, GO_TO_JAIL, RAILWAY, UTILITY, STREET, TAX, FREE_PARKING;

    public static final EnumSet<SpaceType> SPECIAL_TYPES = EnumSet.of(START, JAIL,
            GO_TO_JAIL, FREE_PARKING);
    public static final EnumSet<SpaceType> OWNABLE_TYPES = EnumSet.of(STREET, UTILITY, RAILWAY);

    public String toLowerCase() {
        return name().toLowerCase();
    }

}
