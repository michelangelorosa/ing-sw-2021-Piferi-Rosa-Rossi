package it.polimi.ingsw.Model;

/**
 * ResourceStack Class contains four integer private attributes indicating the amount of each
 * different type of resource: shields, servants, coins, stones.
 */
public class ResourceStack {
    private int shields;
    private int servants;
    private int coins;
    private int stones;

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
     * Getter for "shields" attribute in ResourceStack Class.
     */
    public int getShields() {
        return shields;
    }

    /**
     * Setter for "shields" attribute in ResourceStack Class.
     */
    public void setShields(int shields) {
        this.shields = shields;
    }

    /**
     * Getter for "servants" attribute in ResourceStack Class.
     */
    public int getServants() {
        return servants;
    }

    /**
     * Setter for "servants" attribute in ResourceStack Class.
     */
    public void setServants(int servants) {
        this.servants = servants;
    }

    /**
     * Getter for "coins" attribute in ResourceStack Class.
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Setter for "coins" attribute in ResourceStack Class.
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Getter for "stones" attribute in ResourceStack Class.
     */
    public int getStones() {
        return stones;
    }

    /**
     * Setter for "stones" attribute in ResourceStack Class.
     */
    public void setStones(int stones) {
        this.stones = stones;
    }

    /**
     * Getter for a specified type of resource inside the stack.
     * @param resourceType is the specified resource type.
     * @return the number of resources of the specified type.
     */
    public int getResource(ResourceType resourceType) {
        if(resourceType == ResourceType.SHIELDS)
            return shields;
        else if (resourceType == ResourceType.SERVANTS)
            return servants;
        else if (resourceType == ResourceType.COINS)
            return coins;
        else if (resourceType == ResourceType.STONES)
            return stones;
        else
            return -1;
    }

    /**
     * Setter for a specified type of resource inside the stack.
     * @param resourceType is the specified resource type.
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
     * This method is used to add resources of a specified type to the stack.
     * @param resourcesToAdd is the number of resources to add.
     * @param resourceType is the specified resource type.
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
     * This method is used to remove resources of a specified type from the stack.
     * @param resourcesToRemove is the number of resources to remove.
     * @param resourceType is the specified resource type.
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
     * toString override method for ResourceStack Class.
     */
    public String toString() {
        return shields+" "+servants+" "+coins+" "+stones;
    }
}
