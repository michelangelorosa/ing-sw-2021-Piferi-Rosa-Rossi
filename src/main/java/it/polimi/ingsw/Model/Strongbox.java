package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.ResourceType;

import java.io.Serializable;

/**
 * Strongbox Class defines the contents and the behaviour of the strongbox inside the player's board.
 * Each strongbox contains a ResourceStack-type object which contains the numbers all the four
 * different resource types.
 */
public class Strongbox implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final ResourceStack storedResources;

    /**
     * Constructor for Strongbox Class. It initializes all resources to 0.
     */
    public Strongbox() {
        this.storedResources = new ResourceStack(0,0,0,0);
    }

    /**
     * Getter for "storedResources" attribute in Strongbox Class.
     */
    public ResourceStack getStoredResources() {
        return storedResources;
    }

    /**
     * This method resets the Strongbox to its initial state.
     */
    public void reset() {
        ResourceType[] resourceTypes = ResourceType.values();
        for(int i = 1; i <= 4; i++)
            this.storedResources.setResource(0, resourceTypes[i]);
    }

    /**
     * This method adds a set of resources to the strongbox.
     * @param resourceStack is the set of resources to be added.
     */
    public void addToAllTypes(ResourceStack resourceStack) {
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++)
            this.storedResources.addResource(resourceStack.getResource(resourceTypes[i]), resourceTypes[i]);
    }

    /**
     * This method removes a set of resources from the strongbox.
     * @param resourceStack is the set of resources to be removed.
     */
    public void removeFromAllTypes(ResourceStack resourceStack) {
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++) {
            if(this.storedResources.getResource(resourceTypes[i]) < resourceStack.getResource(resourceTypes[i])) {
                System.err.println("Error: Not enough "+resourceTypes[i]+" in strongbox for removal");
                return;
            }
            else
                this.storedResources.removeResource(resourceStack.getResource(resourceTypes[i]), resourceTypes[i]);
        }
    }

    /**
     * This method is used to count all resources of a certain type
     * inside the strongbox.
     * @param resourceType specifies the type of resource searched.
     * @return the number of resources counted.
     */
    public int countResourcesByType(ResourceType resourceType) {
        return this.storedResources.getResource(resourceType);
    }

    /**
     * This method is used to add resources of a specified type to the strongbox.
     * @param resourcesToAdd is the number of resources to add.
     * @param resourceType is the type of the resources which are to be added.
     */
    public void addResourcesByType(int resourcesToAdd, ResourceType resourceType) {
        this.storedResources.addResource(resourcesToAdd, resourceType);
    }

    /**
     * This method is used to remove one resource of a specified type to the strongbox.
     * If the strongbox's number of resources of the specified type is equal or less than 0,
     * an exception is thrown.
     * @param resourceType is the type of the resources which are to be added.
     * @exception IllegalArgumentException Thrown if the number of specified Type resource is 0.
     */
    public void removeOneResourcesByType(ResourceType resourceType) throws IllegalArgumentException {
        if(this.countResourcesByType(resourceType) <= 0)
            throw new IllegalArgumentException("Model: Strongbox does not have any "+resourceType+" left.");

        this.storedResources.removeResource(1, resourceType);
    }

    /**
     * Override method used for printing the contents of a Strongbox-type object.
     * @return a String containing the information.
     */
    public String toString() {
        return storedResources.getShields()+" "+storedResources.getServants()+" "+storedResources.getCoins()+" "+storedResources.getStones();
    }
}
