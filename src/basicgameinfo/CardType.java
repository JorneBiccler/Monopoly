/*
 * Auteur: Jorne Biccler
 * Project: ugentopoly
 * Vak: Programmeren 2
 */
package basicgameinfo;

import dialogs.InfoDialog;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import monopoly.GameComponent;
import monopoly.GameModel;
import monopoly.MonopolyBoardComponent;
import monopoly.Player;

/**
 * Enum die de mogelijke soorten van kaartjes bevat. Ook heeft iedere soort een
 * methode doAction die de actie corresponderend met het type uitvoert. Voor
 * uitgebreide info zie de spelregels van deze monopoly implementatie.
 *
 * @author Jorne Biccler
 */
public enum CardType {

    /**
     * de doAction roept hier een nieuw infoDialog op, als deze gesloten wordt
     * of er op ok-geklikt wordt dan zal de huidige speler een jailCard in zijn
     * bezit krijgen.
     */
    JAIL {
                public void doAction(final GameModel gameModel, final Card card, final SpaceType type) {
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                @Override
                                public void handle(Event t) {
                                    gameModel.getCurrentPlayer().addJailCard(type, card);
                                    GameComponent.logListWrapper.addMessage("getJailCard", new Object[]{gameModel.getCurrentPlayer().getName()});
                                    gameModel.nextTurn();
                                }
                            }
                    );
                }
            },
    /**
     * de doAction roept hier een nieuw infoDialog op, als deze gesloten wordt
     * of er op ok-geklikt wordt dan zal de huidige speler op de gapste manier
     * verplaatst worden.
     */
    MOVE {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    gameModel.setCurrentPlayerPosition(card.getPosition(), card.isCollect(), true);
                                }
                            });
                }

            },
    /**
     * de doAction roept hier een nieuw infoDialog op, als deze gesloten wordt
     * of er op ok-geklikt wordt dan zal de huidige speler geld ontvongen of aan
     * geld aan de bonus pot toevoegen.
     */
    MONEY {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    final Player currentPlayer = gameModel.getCurrentPlayer();
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    if (card.getAmount() <= 0) {
                                        gameModel.increaseJackpot(-card.getAmount());
                                        GameComponent.logListWrapper.addMessage("fundJackpot", new Object[]{
                                            currentPlayer.getName(), -card.getAmount()});
                                    } else {
                                        GameComponent.logListWrapper.addMessage("getMoney", new Object[]{
                                            currentPlayer.getName(), card.getAmount()});
                                    }
                                    currentPlayer.increaseBalance(card.getAmount());

                                    gameModel.nextTurn();
                                }
                            });
                }
            },
    /**
     * de doAction roept hier een nieuw infoDialog op, als deze gesloten wordt
     * of er op ok-geklikt wordt dan zal de huidige speler op de gapste manier
     * verplaatst worden.
     */
    MOVEREL {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    gameModel.relativeCurrentPlayerPositionChange(card.getRelative(), card.isCollect(), true);
                                }
                            }
                    );
                }

            },
    /**
     * de doAction roept hier een nieuw infoDialog op, als deze gesloten wordt
     * of er op ok-geklikt wordt dan zal de huidige speler aan de andere spelers
     * een gepast bedrag betalen ofwel van alle spelers een gepast bedrag
     * krijgen
     */
    PLAYERS_MONEY {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    final Player currentPlayer = gameModel.getCurrentPlayer();
                    final int amount = card.getAmount();
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    for (Player pl : gameModel.getListOfPlayers()) {
                                        if (amount <= 0 && pl != currentPlayer) {
                                            currentPlayer.decreaseBalance(amount);
                                            pl.increaseBalance(-amount);

                                        } else if (pl != currentPlayer) {
                                            currentPlayer.increaseBalance(amount);
                                            pl.decreaseBalance(amount);
                                        }
                                    }
                                    if (amount <= 0) {
                                        GameComponent.logListWrapper.addMessage("payMoneyAll",
                                                new Object[]{currentPlayer.getName(), -amount});
                                    } else {
                                        GameComponent.logListWrapper.addMessage("getMoneyAll",
                                                new Object[]{currentPlayer.getName(), amount});
                                    }
                                    gameModel.nextTurn();
                                }
                            });
                }

            };

    /**
     * Standaard actie horende bij een kaartje.
     */
    public abstract void doAction(final GameModel gameModel, final Card card, SpaceType type);

    public String toLowerCase() {
        return name().toLowerCase();
    }

    private static void createInfoDialog(Card card, SpaceType type, EventHandler<Event> okHandler) {
        Stage infoDialog = new InfoDialog(
                MonopolyBoardComponent.boardProperties.getProperty(card.getId()), type.toLowerCase() + "kaart", okHandler);
        infoDialog.show();
    }

}
