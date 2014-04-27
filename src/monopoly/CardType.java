/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import dialogs.InfoDialogComponent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author Jorne Biccler
 */
public enum CardType {

    JAIL {
                public void doAction(final GameModel gameModel, final Card card, final SpaceType type) {
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                @Override
                                public void handle(Event t) {
                                    gameModel.getCurrentPlayer().addJailCard(type, card);
                                    gameModel.doGameAction();                                 
                                }
                            }
                    );
                }
            },
    MOVE {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    gameModel.setModelPosition(card.getPosition(), false, true);
                                }
                            }
                    );
                }

            },
    MONEY {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    final Player currentPlayer = gameModel.getCurrentPlayer();
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    if (card.getAmount() <= 0) {
                                        gameModel.increaseJackpot(-1*card.getAmount());
                                    }
                                    currentPlayer.increaseBalance(card.getAmount());
                                    gameModel.doGameAction();
                                }
                            }
                    );
                }

            },
    MOVEREL {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    gameModel.relativeModelPosition(card.getRelative(), card.isCollect(), true);
                                }
                            }
                    );
                }

            },
    PLAYERS_MONEY {
                public void doAction(final GameModel gameModel, final Card card, SpaceType type) {
                    final Player currentPlayer = gameModel.getCurrentPlayer();
                    final int amount = card.getAmount();

                    createInfoDialog(card, type,
                            new EventHandler<Event>() {
                                public void handle(Event t) {
                                    for (Player pl : gameModel.getPossibleModels()) {
                                        if (amount <= 0 && pl != currentPlayer) {
                                            currentPlayer.decreaseBalance(amount);
                                            pl.increaseBalance(amount);

                                        } else if (pl != currentPlayer) {
                                            currentPlayer.increaseBalance(amount);
                                            pl.decreaseBalance(amount);
                                        }
                                        gameModel.doGameAction();
                                    }
                                }
                            });
                }

            };

    public abstract void doAction(final GameModel gameModel, final Card card, SpaceType type);

    public String toLowerCase() {
        return name().toLowerCase();
    }

    private static void createInfoDialog(Card card, SpaceType type, EventHandler<Event> okHandler) {
        Stage infoDialog = new InfoDialogComponent(
                MonopolyBoardComponent.boardProperties.getProperty(card.getId()), type.toLowerCase() + "kaart", okHandler);
        infoDialog.show();
    }

}
