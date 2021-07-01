package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;

/**
 * Strongbox Class defines the contents and the behaviour of the strongbox inside the player's board.
 * Each strongbox contains a ResourceStack-type object which contains the numbers all the four
 * different resource types.
 * @author redrick99
 */
public class Strongbox extends RedStrongbox {

    /**
     * Constructor for Strongbox Class. It initializes all resources to 0.
     */
    public Strongbox() {
        this.storedResources = new ResourceStack(0,0,0,0);
    }

    /**
     * Getter for "storedResources" attribute.
     */
    public ResourceStack getStoredResources() {
        return (ResourceStack) storedResources;
    }

    /**
     * Resets the Strongbox.
     */
    public void reset() {
        ResourceType[] resourceTypes = ResourceType.values();
        for(int i = 1; i <= 4; i++)
            this.storedResources.setResource(0, resourceTypes[i]);
    }

    /**
     * Adds a set of resources to the strongbox.
     * @param resourceStack Set of resources to be added.
     */
    public void addToAllTypes(ResourceStack resourceStack) {
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++)
            ((ResourceStack)this.storedResources).addResource(resourceStack.getResource(resourceTypes[i]), resourceTypes[i]);
    }

    /**
     * Removes a set of resources from the strongbox.
     * @param resourceStack Set of resources to be removed.
     */
    public void removeFromAllTypes(ResourceStack resourceStack) {
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++) {
            if(this.storedResources.getResource(resourceTypes[i]) < resourceStack.getResource(resourceTypes[i])) {
                System.err.println("Error: Not enough "+resourceTypes[i]+" in strongbox for removal");
                return;
            }
            else
                ((ResourceStack)this.storedResources).removeResource(resourceStack.getResource(resourceTypes[i]), resourceTypes[i]);
        }
    }

    /**
     * Counts all resources of a certain type inside the strongbox.
     * @param resourceType specifies the type of resource searched.
     * @return the number of resources counted.
     */
    public int countResourcesByType(ResourceType resourceType) {
        return this.storedResources.getResource(resourceType);
    }

    /**
     * Adds resources of a specified type to the strongbox.
     * @param resourcesToAdd is the number of resources to add.
     * @param resourceType is the type of the resources which are to be added.
     */
    public void addResourcesByType(int resourcesToAdd, ResourceType resourceType) {
        ((ResourceStack)this.storedResources).addResource(resourcesToAdd, resourceType);
    }

    /**
     * Removes one resource of a specified type from the strongbox.
     * If the strongbox's number of resources of the specified type is equal or less than 0,
     * an exception is thrown.
     * @param resourceType Type of the resources which are to be added.
     * @exception IllegalArgumentException Thrown if the number of specified Type resource is 0.
     */
    public void removeOneResourcesByType(ResourceType resourceType) throws IllegalArgumentException {
        if(this.countResourcesByType(resourceType) <= 0)
            throw new IllegalArgumentException("Model: Strongbox does not have any "+resourceType+" left.");

        ((ResourceStack)this.storedResources).removeResource(1, resourceType);
    }

    /**
     * Overridden method used for printing the contents of a Strongbox-type object.
     * @return a String containing the information.
     */
    public String toString() {
        return storedResources.getResource(ResourceType.SHIELDS)+" "+storedResources.getResource(ResourceType.SERVANTS)+" "+storedResources.getResource(ResourceType.COINS)+" "+storedResources.getResource(ResourceType.STONES);
    }

    /**Method for converting model classes to view classes*/
    public RedStrongbox toView() {
        return this;
    }

}
