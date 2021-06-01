package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * BuyCardMessage Class contains data for a response message to be sent to the client
 * after a BuyCard request.
 */
public class BuyCardMessage extends MessageToClient{
    RedResourceStack temporaryResources;

    /**
     * Constructor for BuyCardMessage Class.
     */
    public BuyCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.BUY_CARD;
    }

    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("SUCCESS")) {
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers())
                if (player.getNickname().equals(this.getPlayerNickname())) {
                    player.setPossibleActions(this.possibleActions);
                    player.setTemporaryResources(this.temporaryResources);
                }
        }

        else {
            userInteraction.getUi().displayError(this.error);
        }

    }
}
