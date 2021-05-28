package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;

/**
 * ChooseProductionOutputMessage Class defines a response message to be sent to the client
 * after a ChooseProductionOutput request.
 */
public class ChoseProductionOutputMessage extends MessageToClient {
    /** The player's strongbox is needed to update the client's View */
    private RedStrongbox strongbox;

    /**
     * Constructor for ChooseProductionOutputMessage Class.
     */
    public ChoseProductionOutputMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_PRODUCTION_OUTPUT;
    }

    /**
     * Setter for "strongbox" attribute in ChooseProductionOutputMessage Class.
     */
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Getter for "strongbox" attribute in ChooseProductionOutputMessage Class.
     */
    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getNickname().equals(this.getPlayerNickname())) {
                    player.setStrongbox(this.strongbox);
                    player.setPossibleActions(this.possibleActions);
                }

                else {
                    //TODO error message
                }
    }
}
