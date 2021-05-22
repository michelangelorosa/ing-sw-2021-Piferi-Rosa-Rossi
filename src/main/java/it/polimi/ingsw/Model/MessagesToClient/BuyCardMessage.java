package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

/**
 * BuyCardMessage Class contains data for a response message to be sent to the client
 * after a BuyCard request.
 */
public class BuyCardMessage extends MessageToClient{
    RedResourceStack temporaryResources;

    /**
     * Constructor for BuyCardMessage Class.
     */
    public BuyCardMessage(int playerId) {
        this.actionDone = ActionType.BUY_CARD;
        this.playerId = playerId;
    }

    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getTurnPosition() == this.playerId) {
                    player.setPossibleActions(this.possibleActions);
                    player.setTemporaryResources(this.temporaryResources);
                }

                else {
                    //TODO error message
                }

    }
}
