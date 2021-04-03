package it.polimi.ingsw.Model;

/**
 * Strongbox Class defines the contents and the behaviour of the strongbox inside the player's board.
 * Each strongbox contains a ResourceStack-type object which contains the numbers all the four
 * different resource types.
 */
public class Strongbox {
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
     * This method is used to count all resources of a certain type
     * inside the strongbox.
     * @param resourceType specifies the type of resource searched.
     * @return the number of resources counted.
     */
    public int countResourcesByType(ResourceType resourceType) {
        int countedInt = 0;
        switch (resourceType) {
            case SHIELDS:
                countedInt = this.storedResources.getShields();
                break;
            case SERVANTS:
                countedInt = this.storedResources.getServants();
                break;
            case COINS:
                countedInt = this.storedResources.getCoins();
                break;
            case STONES:
                countedInt = this.storedResources.getStones();
                break;
        }
        return countedInt;
    }

    /**
     * This method is used to add resources of a specified type to the strongbox.
     * @param resourcesToAdd is the number of resources to add.
     * @param resourceType is the type of the resources which are to be added.
     */
    public void addResourcesByType(int resourcesToAdd, ResourceType resourceType) {
        switch (resourceType) {
            case SHIELDS:
                this.storedResources.setShields(this.storedResources.getShields() + resourcesToAdd);
                break;
            case SERVANTS:
                this.storedResources.setServants(this.storedResources.getServants() + resourcesToAdd);
                break;
            case COINS:
                this.storedResources.setCoins(this.storedResources.getCoins() + resourcesToAdd);
                break;
            case STONES:
                this.storedResources.setStones(this.storedResources.getStones() + resourcesToAdd);
                break;
        }
    }

    /**
     * This method is used to remove resources of a specified type to the strongbox.
     * If the strongbox's number of resources of the specified type is lower than the number of resources that
     * have to be removed, an error message is displayed and the method returns the number of resources that have
     * yet to be removed (all of them).
     * @param resourcesToRemove is the number of resources to remove.
     * @param resourceType is the type of the resources which are to be added.
     * @return 0 if resources have been removed successfully, else the number of resources which had to be
     * removed.
     */
    public int removeResourcesByType(int resourcesToRemove, ResourceType resourceType) {
        if(this.countResourcesByType(resourceType) >= resourcesToRemove) {
            switch (resourceType) {
                case SHIELDS:
                    this.storedResources.setShields(this.storedResources.getShields() - resourcesToRemove);
                    break;
                case SERVANTS:
                    this.storedResources.setServants(this.storedResources.getServants() - resourcesToRemove);
                    break;
                case COINS:
                    this.storedResources.setCoins(this.storedResources.getCoins() - resourcesToRemove);
                    break;
                case STONES:
                    this.storedResources.setStones(this.storedResources.getStones() - resourcesToRemove);
                    break;
            }
            return 0;
        }
        else {
            System.err.println("Error: Strongbox doesn't have that many " + resourceType);
            return resourcesToRemove;
        }
    }

    /**
     * Override method used for printing the contents of a Strongbox-type object.
     * @return a String containing the information.
     */
    public String toString() {
        return storedResources.getShields()+" "+storedResources.getServants()+" "+storedResources.getCoins()+" "+storedResources.getStones();
    }
}
