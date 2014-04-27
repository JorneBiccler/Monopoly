/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package infoholders;

import monopoly.GameModel;
import monopoly.Space;

/**
 *
 * @author jorne
 */
public class GoToJailBox extends SpecialBox{

    public GoToJailBox(Space space, String propString  ) {
        super(space, propString);
    }

    @Override
    public void doAction(GameModel gameModel) {
        gameModel.sentCurrentPlayerToJail();
    }
    
}
