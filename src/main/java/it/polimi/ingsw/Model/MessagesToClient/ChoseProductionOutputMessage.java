package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ChooseProductionOutputMessage Class defines a response message to be sent to the client
 * after a ChooseProductionOutput request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedStrongbox "strongbox": the new Strongbox to be set to the corresponding player in the
 *     Client's View</li>
 * </ul>
 * @author redrick99
 */
public class ChoseProductionOutputMessage extends MessageToClient {
    private RedStrongbox strongbox;

    /**
     * Constructor for ChooseProductionOutputMessage Class.
     */
    public ChoseProductionOutputMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_PRODUCTION_OUTPUT;
    }

    /**
     * Setter for "strongbox" attribute.
     */
    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Getter for "strongbox" attribute.
     */
    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = this.getPlayer(userInteraction);

        if(this.success()) {
            player.setStrongbox(this.strongbox);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
