package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.MessagesToClient.InitChoseResourcesMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * InitChooseResources Class defines data and methods used to give to the player the initial resources
 * he chooses at the beginning of the game.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>HashMap&lt;Integer, ArrayList&lt;ResourceType&gt;&gt; depotResource: Maps an ArrayList of Resources
 *     to an Integer key which indicates the depot chose by the player to put said resources.</li>
 * </ul>
 * @author redrick99
 */
public class InitChooseResources extends Action{
    private final HashMap<Integer, ArrayList<ResourceType>> depotResource;
    private String nickname;

    /**
     * Constructor for InitChooseResources Class.
     */
    public InitChooseResources(HashMap<Integer, ArrayList<ResourceType>> depotResource) {
        this.depotResource = depotResource;
    }

    /**
     * Getter for "depotResource" attribute.
     */
    public HashMap<Integer, ArrayList<ResourceType>> getDepotResource() {
        return depotResource;
    }

    /**
     * Checks if the object parameters are correct by assuring that the player hasn't specified more
     * resources per depot than he actually could and by making sure that there are no Resources of type NONE.
     * @return true if every ArrayList inside the HashMap is correct.
     * @throws IllegalArgumentException if any of the ArrayLists inside the HasMap do not follow given requirements
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException{
        if(!depotResource.get(0).isEmpty())
            if(depotResource.get(0).size() > 3)
                throw new IllegalArgumentException("Error: InitResourcesArray for Depot 0 bigger than 3");
            else
                for(ResourceType type : depotResource.get(0))
                    if(type == ResourceType.NONE)
                        throw new IllegalArgumentException("Error: InitResourcesArray for Depot 0 has a type NONE resource");

        if(!depotResource.get(1).isEmpty())
            if(depotResource.get(1).size() > 2)
                throw new IllegalArgumentException("Error: InitResourcesArray for Depot 1 bigger than 2");
            else
                for(ResourceType type : depotResource.get(1))
                    if(type == ResourceType.NONE)
                        throw new IllegalArgumentException("Error: InitResourcesArray for Depot 1 has a type NONE resource");

        if(!depotResource.get(2).isEmpty())
            if(depotResource.get(2).size() > 1)
                throw new IllegalArgumentException("Error: InitResourcesArray for Depot 2 bigger than 1");
            else
                for(ResourceType type : depotResource.get(2))
                    if(type == ResourceType.NONE)
                        throw new IllegalArgumentException("Error: InitResourcesArray for Depot 2 has a type NONE resource");

        return true;
    }

    /**
     * Checks if the action is applicable with given parameters by assuring that the player didn't pick
     * different types of resources to be put in the same Depot or that the player didn't put two equal
     * resources in two different depots.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the action is applicable.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        if(depotResource.get(0).isEmpty() && depotResource.get(1).isEmpty() && depotResource.get(2).isEmpty())
            return true;

        ResourceType temp0 = ResourceType.NONE;
        ResourceType temp1 = ResourceType.NONE;
        ResourceType temp2 = ResourceType.NONE;

        if(!depotResource.get(0).isEmpty()) {
            ArrayList<ResourceType> depot0 = depotResource.get(0);
            temp0 = depot0.get(0);

            if(depot0.size() > 1) {
                for(int i = 1; i < depot0.size(); i++) {
                    if(depot0.get(i) != temp0) {
                        this.response = "Cannot put two different resources in the same depot! (Depot 0)";
                        return false;
                    }
                }
            }
        }

        if(!depotResource.get(1).isEmpty()) {
            ArrayList<ResourceType> depot1 = depotResource.get(1);
            temp1 = depot1.get(0);

            if(depot1.size() > 1) {
                for(int i = 1; i < depot1.size(); i++) {
                    if(depot1.get(i) != temp1) {
                        this.response = "Cannot put two different resources in the same depot! (Depot 1)";
                        return false;
                    }
                }
            }
        }

        if(!depotResource.get(2).isEmpty()) {
            temp2 = depotResource.get(2).get(0);
        }

        if(temp0 != ResourceType.NONE && temp0 == temp1 || temp0 != ResourceType.NONE && temp0 == temp2 || temp1 != ResourceType.NONE && temp1 == temp2) {
            this.response = "Cannot put the same type of resources in two different depots!";
            return false;
        }

        return true;
    }

    /**
     * Controls and Executes the action by adding the specified resources to the player's Warehouse.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action is correctly applied to the Model, another String if otherwise.
     */
    @Override
    public String doAction(ActionController actionController) {

        if(!this.isCorrect())
            return null;

        if(!this.canBeApplied(actionController))
            return this.response;

        if(depotResource.get(0).isEmpty() && depotResource.get(1).isEmpty() && depotResource.get(2).isEmpty()) {
            this.response = "SUCCESS";
        }
        else {
            if (!depotResource.get(0).isEmpty()) {
                for (ResourceType type : depotResource.get(0))
                    actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(type, actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0]);
            }

            if (!depotResource.get(1).isEmpty()) {
                for (ResourceType type : depotResource.get(1))
                    actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(type, actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1]);
            }

            if (!depotResource.get(2).isEmpty()) {
                for (ResourceType type : depotResource.get(2))
                    actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(type, actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2]);
            }
        }

        this.response = "SUCCESS";
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.BUY_CARD);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
        this.nickname = actionController.getGame().getCurrentPlayerNickname();
        actionController.getGame().nextPlayer();

        return "SUCCESS";
    }

    /**
     * Prepares a InitChoseResourcesMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        InitChoseResourcesMessage message = new InitChoseResourcesMessage(this.nickname);
        message.setError(this.response);

        return message;
    }
}
