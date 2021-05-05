package it.polimi.ingsw.View.MessagesToClient;


import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;

public class ChoseCardSlotMessage extends MessageToClient {
    /** row and column of the deck and the slot int position are required to update the Client's View */
    private int row;
    private int column;
    private int slot;

    /**
     * Constructor for ChooseCardSlotMessage Class
     */
    public ChoseCardSlotMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_CARD_SLOT;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS")) {
            for (Player player : game.getPlayers())
                if (player.getTurnPosition() == this.playerId) {
                    player.setPossibleActions(this.possibleActions);
                    player.getSlots().addCard(this.slot, game.getDevelopmentCardTable().drawCardFromDeck(this.row, this.column));
                }

        }
        else {
            //TODO error message
        }
    }
}
