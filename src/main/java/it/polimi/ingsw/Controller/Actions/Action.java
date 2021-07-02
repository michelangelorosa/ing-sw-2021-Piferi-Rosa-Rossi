package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.Model.MessagesToClient.CheatMessage.CheatMessage;

import java.io.Serializable;

/**
 * Action Class defines common Data and Methods to perform an action on the model after a Client's request.
 * <p>
 * An Action subclass type object is created by the Client and sent to the Server to be processed.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>String "nickname": name of the player who performed the action</li>
 *     <li>ActionType "actionType": enum indicating the Action requested</li>
 *     <li>String "response": contains the Controller's response after trying to perform the action</li>
 *     <li>String "ILLEGAL_ACTION": standard message to be used when a player cannot perform a certain type of Action</li>
 * </ul>
 *
 * @author redrick99
 */
public class Action implements Serializable {
    private String nickname;
    protected ActionType actionType;
    protected String response = null;
    protected static final String ILLEGAL_ACTION = "You cannot perform this action at this moment";

    /**
     * Getter for "nickname" attribute.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setter for "nickname" attribute.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter for "actionType" attribute.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "response" attribute.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Checks if the Action subclass parameters are correct and can be used by the Model's Classes to perform
     * the given action.
     * @return true if parameters are correct.
     */
    public boolean isCorrect() {
        return false;
    }

    /**
     * Checks if the request is logically applicable by the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the action can be applied by the Model with the given parameters.
     */
    public boolean canBeApplied(ActionController actionController) { return false; }

    /**
     * Checks if the action requested can be performed by the player by assuring that the List of
     * Possible Actions in the Model's Player contains the ActionType describing the Action coming from
     * the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the action can be performed.
     */
    public boolean canDoAction(ActionController actionController) {
        if(actionController.getGame().getCurrentPlayer().canDo(this.actionType))
            return true;
        else {
            this.response = ILLEGAL_ACTION;
            return false;
        }
    }

    /**
     * Computes the request sent by the client using Model's methods.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A String to be used later to create a message to send to the Client.
     */
    public String doAction(ActionController actionController) throws Exception {
        return null;
    }

    /**
     * Prepares a message to be sent to the Client after a certain request. Each
     * Action SubClass creates a different MessageToClient-Object to be later serialized and sent.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A MessageToClient subclass containing all information needed to update the client's Reduced Model.
     */
    public MessageToClient messagePrepare(ActionController actionController) {
        return null;
    }

    /**
     * Edits a MessageToClient in case the player tried to perform an Action he could not have performed at a
     * certain time.
     * @param message MessageToClient to edit.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return The edited MessageToClient object.
     */
    public MessageToClient illegalAction(MessageToClient message, ActionController actionController) {
        if(message.getPossibleActions().size() > 0)
            message.getPossibleActions().clear();

        for(ActionType type : actionController.getGame().getCurrentPlayer().getPossibleActions())
            message.addPossibleAction(type);

        message.setError(this.response);
        return message;
    }

    /**
     * Common method used by Cheat Actions to generate a new CheatMessage.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A new CheatMessage.
     */
    protected CheatMessage cheatMessage(ActionController actionController) {
        CheatMessage message = new CheatMessage(actionController);
        message.setError(this.response);
        return message;
    }
}
