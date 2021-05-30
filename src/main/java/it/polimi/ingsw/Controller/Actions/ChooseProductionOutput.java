package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.*;

import java.util.ArrayList;

/**
 * ChooseProductionOutput Class contains data and methods to be used when the player
 * concluded payment for a Production to choose the Production output.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>boolean "firstLeaderCard", "secondLeaderCard", "basicProduction": they are set when activating
 *     a production and indicate which different Productions the player chose to activate.</li>
 *     <li>ArrayList(ResourceType) "firstLeaderCardOutput", "secondLeaderCardOutput", "basicProductionOutput":
 *     they contain the outputs chosen by the player.</li>
 * </ul>
 * @author redrick99
 */
public class ChooseProductionOutput extends Action {
    private boolean firstLeaderCard;
    private boolean secondLeaderCard;
    private boolean basicProduction;
    private ArrayList<ResourceType> firstLeaderCardOutput;
    private ArrayList<ResourceType> secondLeaderCardOutput;
    private ArrayList<ResourceType> basicProductionOutput;

    private ResourceStack output;

    /**
     * Constructor for ChooseProductionOutput Class.
     */
    public ChooseProductionOutput() {
        this.actionType = ActionType.CHOOSE_PRODUCTION_OUTPUT;
        this.firstLeaderCard = false;
        this.secondLeaderCard = false;
        this.basicProduction = false;
        this.firstLeaderCardOutput = null;
        this.secondLeaderCardOutput = null;
        this.basicProductionOutput = null;
        this.output = new ResourceStack(0,0,0,0);
    }

    /**
     * Getter for "firstLeaderCard" attribute.
     */
    public boolean isFirstLeaderCard() {
        return firstLeaderCard;
    }

    /**
     * Getter for "secondLeaderCard" attribute.
     */
    public boolean isSecondLeaderCard() {
        return secondLeaderCard;
    }

    /**
     * Getter for "basicProduction" attribute.
     */
    public boolean isBasicProduction() {
        return basicProduction;
    }

    /**
     * Getter for "firstLeaderCardOutput" attribute.
     */
    public ArrayList<ResourceType> getFirstLeaderCardOutput() {
        return firstLeaderCardOutput;
    }

    /**
     * Getter for "secondLeaderCardOutput" attribute.
     */
    public ArrayList<ResourceType> getSecondLeaderCardOutput() {
        return secondLeaderCardOutput;
    }

    /**
     * Getter for "basicProductionOutput" attribute.
     */
    public ArrayList<ResourceType> getBasicProductionOutput() {
        return basicProductionOutput;
    }

    /**
     * Getter for "output" attribute.
     */
    public ResourceStack getOutput() {
        return output;
    }

    /**
     * Setter for firstLeaderCard attribute.
     */
    public void setFirstLeaderCard(boolean firstLeaderCard) {
        this.firstLeaderCard = firstLeaderCard;
    }

    /**
     * Setter for secondLeaderCard attribute.
     */
    public void setSecondLeaderCard(boolean secondLeaderCard) {
        this.secondLeaderCard = secondLeaderCard;
    }

    /**
     * Setter for basicProduction attribute.
     */
    public void setBasicProduction(boolean basicProduction) {
        this.basicProduction = basicProduction;
    }

    /**
     * Setter for firstLeaderCardOutput attribute.
     */
    public void setFirstLeaderCardOutput(ArrayList<ResourceType> firstLeaderCardOutput) {
        this.firstLeaderCardOutput = firstLeaderCardOutput;
    }

    /**
     * Setter for secondLeaderCardOutput attribute.
     */
    public void setSecondLeaderCardOutput(ArrayList<ResourceType> secondLeaderCardOutput) {
        this.secondLeaderCardOutput = secondLeaderCardOutput;
    }

    /**
     * Setter for basicProductionOutput attribute.
     */
    public void setBasicProductionOutput(ArrayList<ResourceType> basicProductionOutput) {
        this.basicProductionOutput = basicProductionOutput;
    }

    /**
     * Checks if the input sent to the server is correct by assuring that the ArrayLists
     * do not contain ResourceTypes equal to NONE.
     * @throws IllegalArgumentException if at least one of the ResourceTypes is NONE.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(firstLeaderCard)
            for(ResourceType type : firstLeaderCardOutput)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("1- One resource was of type NONE.");

        if(secondLeaderCard)
            for(ResourceType type : secondLeaderCardOutput)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("2- One resource was of type NONE.");

        if(basicProduction)
            for(ResourceType type : basicProductionOutput)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("3- One resource was of type NONE.");

        return true;
    }

    /**
     * Checks if the action is logically applicable.
     * @param actionController Class used to compute Action messages coming from the Client.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        if(firstLeaderCard)
            if(!(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].isActive()) || firstLeaderCardOutput.size() != actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].getJollyOut() || actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].getAction() != LeaderCardAction.PRODUCTIONPOWER)
                return false;


        if(secondLeaderCard)
            if(!(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].isActive()) || secondLeaderCardOutput.size() != actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].getJollyOut() || actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].getAction() != LeaderCardAction.PRODUCTIONPOWER)
                return false;

        if(basicProduction)
            if(basicProductionOutput.size() != actionController.getGame().getCurrentPlayer().getBoard().getBasicProduction().getJollyOut())
                return false;

        return true;
    }

    /**
     * Controls and executes the action on the Model.
     * <p>After checking all outputs specified by the player, proceeds to add them to the
     * player's strongbox along with all not chosen resources created by the production.</p>
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();
        if(!this.canBeApplied(actionController)) {
            this.response = "Tried to use not valid Leader Cards or tried to get more resources than possible.";
            return "Tried to use not valid Leader Cards or tried to get more resources than possible.";
        }

        if(firstLeaderCard)
            for(ResourceType type : this.firstLeaderCardOutput)
                output.addResource(1, type);
        if(secondLeaderCard)
            for(ResourceType type : this.secondLeaderCardOutput)
                output.addResource(1, type);
        if(basicProduction)
            for(ResourceType type : this.basicProductionOutput)
                output.addResource(1, type);

        if(!output.isEmpty())
            actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addProductionResources(output);

        output = new ResourceStack(0,0,0,0);

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    /**
     * Prepares a ChoseProductionMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ChoseProductionOutputMessage message = new ChoseProductionOutputMessage(actionController.getGame().getCurrentPlayerNickname());
        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.setStrongbox(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
            message.addPossibleAction(ActionType.END_TURN);
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);

        return message;
    }
}