/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package monopoly;

import javafx.beans.property.SimpleIntegerProperty;

/*
 * Een extensie van SimpleIntegerProperty die enkel waarden binnen een bepaald interval toelaat
 * @author Jorne Biccler
 */
public class MinMaxIntegerProperty extends SimpleIntegerProperty {

    private int maxValue;
    private int minValue;

    public MinMaxIntegerProperty(int i, int minValue, int maxValue) {
        super(i);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void set(int i) {
        if (i <= maxValue && i >= minValue) {
            super.set(i);
        } else {
            throw new RuntimeException("out of bounds min max property");
        }
    }

    @Override
    public void setValue(Number number) {
        if (number.intValue() <= maxValue && number.intValue() >= minValue) {
            super.setValue(number);
        } else {
            throw new RuntimeException("out of bounds min max property");
        }
    }

}
