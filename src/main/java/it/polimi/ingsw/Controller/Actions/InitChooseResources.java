package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.MessagesToClient.InitChoseResourcesMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;

import java.util.ArrayList;
import java.util.HashMap;

public class InitChooseResources extends Action{
    private final HashMap<Integer, ArrayList<ResourceType>> depotResource;

    public InitChooseResources(HashMap<Integer, ArrayList<ResourceType>> depotResource) {
        this.depotResource = depotResource;
    }

    public HashMap<Integer, ArrayList<ResourceType>> getDepotResource() {
        return depotResource;
    }

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

    @Override
    public boolean canBeApplied(ActionController actionController) throws IllegalArgumentException {
        if(depotResource.get(0).isEmpty() && depotResource.get(1).isEmpty() && depotResource.get(2).isEmpty())
            return true;

        ResourceType temp0 = ResourceType.NONE;
        ResourceType temp1 = ResourceType.NONE;
        ResourceType temp2 = ResourceType.NONE;

        if(!depotResource.get(0).isEmpty()) {
            ArrayList<ResourceType> depot0 = depotResource.get(0);

            if(depot0.size() > 1) {
                temp0 = depot0.get(0);
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

            if(depot1.size() > 1) {
                temp1 = depot1.get(0);
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

        if(temp0 == temp1 || temp0 == temp2 || temp1 == temp2) {
            this.response = "Cannot put the same type of resources in two different depots!";
            return false;
        }

        return true;
    }

    @Override
    public String doAction(ActionController actionController) {

        if(!this.isCorrect())
            return null;

        if(!this.canBeApplied(actionController))
            return this.response;

        if(depotResource.get(0).isEmpty() && depotResource.get(1).isEmpty() && depotResource.get(2).isEmpty()) {
            this.response = "SUCCESS";
            return "SUCCESS";
        }

        if(!depotResource.get(0).isEmpty()) {
            for(ResourceType type : depotResource.get(0))
                actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(type, actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0]);
        }

        if(!depotResource.get(1).isEmpty()) {
            for(ResourceType type : depotResource.get(1))
                actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(type, actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1]);
        }

        if(!depotResource.get(2).isEmpty()) {
            for(ResourceType type : depotResource.get(2))
                actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(type, actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2]);
        }

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        InitChoseResourcesMessage message = new InitChoseResourcesMessage(actionController.getGame().getCurrentPlayerNickname());
        message.setError(this.response);

        if(this.response.equals("SUCCESS")) {
            message.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
            message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
            message.addPossibleAction(ActionType.BUY_CARD);
            message.addPossibleAction(ActionType.BUY_FROM_MARKET);
        }
        else {
            message.addPossibleAction(ActionType.INIT_CHOOSE_RESOURCES);
        }

        return message;
    }
}
