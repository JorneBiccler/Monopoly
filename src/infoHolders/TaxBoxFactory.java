/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoHolders;

import monopoly.Space;

/**
 *
 * @author jorne
 */
public class TaxBoxFactory implements InfoBoxFactory{

    @Override
    public InfoBox create(Space space, String infoProp) {
        return new TaxBox(space, infoProp);
    }
    
}
