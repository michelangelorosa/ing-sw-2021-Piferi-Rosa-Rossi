package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

/**
 * ResourceStack Class contains four integer private attributes indicating the amount of each
 * different type of resource: shields, servants, coins, stones.
 * @author redrick99
 */
public class ResourceStack extends RedResourceStack{

    /**
     * Constructor for ResourceStack Class.
     */
    public ResourceStack(int shields, int servants, int coins, int stones) {
        this.shields = shields;
        this.servants = servants;
        this.coins = coins;
        this.stones = stones;
    }


    /**
     * Setter for a specified type of resource inside the stack.
     * @param resourceType Specified resource type.
     */
    public void setResource(int resources, ResourceType resourceType) {
        if(resourceType == ResourceType.SHIELDS)
            this.shields = resources;
        else if (resourceType == ResourceType.SERVANTS)
            this.servants = resources;
        else if (resourceType == ResourceType.COINS)
            this.coins = resources;
        else if (resourceType == ResourceType.STONES)
            this.stones = resources;
    }

    /**
     * Adds resources of a specified type to the stack.
     * @param resourcesToAdd Number of resources to add.
     * @param resourceType Specified resource type.
     */
    public void addResource(int resourcesToAdd, ResourceType resourceType) {
        if(resourceType == ResourceType.SHIELDS)
            this.shields += resourcesToAdd;
        else if (resourceType == ResourceType.SERVANTS)
            this.servants += resourcesToAdd;
        else if (resourceType == ResourceType.COINS)
            this.coins += resourcesToAdd;
        else if (resourceType == ResourceType.STONES)
            this.stones += resourcesToAdd;
    }

    /**
     * Adds the resources of a different ResourceStack.
     * @param resourceStack Stack containing the resources to add.
     */
    public void addToAllTypes(ResourceStack resourceStack){
        ResourceType[] types = ResourceType.values();
        for(int i = 1; i < 5; i++){
            this.addResource(resourceStack.getResource(types[i]), types[i]);
        }
    }

    /**
     * Removes resources of a specified type from the stack.
     * @param resourcesToRemove is the number of resources to remove.
     * @param resourceType Specified resource type.
     */
    public void removeResource(int resourcesToRemove, ResourceType resourceType) {
        if(resourceType == ResourceType.SHIELDS)
            this.shields -= resourcesToRemove;
        else if (resourceType == ResourceType.SERVANTS)
            this.servants -= resourcesToRemove;
        else if (resourceType == ResourceType.COINS)
            this.coins -= resourcesToRemove;
        else if (resourceType == ResourceType.STONES)
            this.stones -= resourcesToRemove;
    }

    /**
     * Removes the resources of different ResourceStack.
     * @param resourceStack Stack containing the resources to remove.
     */
    public void removeFromAllTypes(ResourceStack resourceStack) {
        ResourceType[] types = ResourceType.values();
        for(int i = 1; i < 5; i++){
            if(this.getResource(types[i]) < resourceStack.getResource(types[i]))
                this.setResource(0, types[i]);
            else
                this.removeResource(resourceStack.getResource(types[i]), types[i]);
        }
    }

    /**
     * Creates a copy of the ResourceStack-type object.
     * @return a copy of the stack.
     */
    public ResourceStack copyStack() {
        ResourceStack newStack = new ResourceStack(0, 0, 0, 0);
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++) {
            newStack.setResource(this.getResource(resourceTypes[i]), resourceTypes[i]);
        }
        return  newStack;
    }

    /**
     * This method is used when Resetting a Resource Manager. It reverts the ResourceStack to
     * another given in input.
     * @param resourceStack Stack to revert to.
     */
    public void revertBack(ResourceStack resourceStack) {
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++) {
            this.setResource(resourceStack.getResource(resourceTypes[i]), resourceTypes[i]);
        }
    }

    /**
     * Counts the total number of all Types of resources.
     * @return the number of resources.
     */
    public int totalResourcesToInt() {
        return this.shields + this.servants + this.coins + this.stones;
    }
}
