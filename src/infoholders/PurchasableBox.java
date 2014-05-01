/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package infoholders;

import basicgameinfo.Space;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import monopoly.*;

/**
 * Uitbreiding van InfoBox deze toont de info weer van vakjes die koopbaar zijn,
 * er is dus een owner label, en een ownermodel, indien het model verandert moeten 
 * de gepaste labels aangepast worden.
 * @author Jorne Biccler
 */
public abstract class PurchasableBox extends InfoBox implements InvalidationListener {

    protected OwnerModel model = new OwnerModel();
    protected int initialRent;
    protected int cost;

    public PurchasableBox(Space space, String propString) {
        super(space, propString);
        initialRent = space.getRent0();
        cost = space.getCost();
        model.addListener(this);
    }

    public OwnerModel getModel() {
        return model;
    }

    /**
     * methode die de String die in een standaard ActionDialog getoond moet worden
     * opmaakt.
     */
    protected String createActionString() {
        ResourceBundle bundle = ResourceBundle.getBundle("resources/allKindsOfText");
        String pattern = bundle.getString("standardBuy");
        MessageFormat mf = new MessageFormat(pattern);
        return mf.format(new Object[]{propString,cost});
    }

     /**
     * Standaard eventHandler die gebruikt wordt bij een koopbaar vakje,
     * indien het vakje gekocht wordt wordt het ownerModel aangepast,
     * de koper zijn balans wordt angepast, ...
     */    
    protected class StandardActionYesHandler implements EventHandler<Event> {

        private final Player currentPlayer;
        private final GameModel gameModel;

        public StandardActionYesHandler(GameModel gameModel) {
            this.currentPlayer = gameModel.getCurrentPlayer();
            this.gameModel = gameModel;
        }

        public void handle(Event t) {
            currentPlayer.addProperty(PurchasableBox.this);
            model.setOwner(currentPlayer);
            model.getOwner().decreaseBalance(cost);

            GameComponent.logListWrapper.addMessage("buyPropertyString", new Object[]{
                currentPlayer.getName(), propString, cost});
            gameModel.nextTurn();
        }
    }

    /**
     * standaard noHandler die in deze setting gebruikt wordt, doet niets meer dan 
     * aan het gameModel door te geven dat er een volgende beurt mag komen.
     */
    
    protected class StandardActionNoHandler implements EventHandler<Event> {

        private final GameModel gameModel;

        public StandardActionNoHandler(GameModel gameModel) {
            this.gameModel = gameModel;
        }

        @Override
        public void handle(Event t) {
            gameModel.nextTurn();
        }
    }

}


