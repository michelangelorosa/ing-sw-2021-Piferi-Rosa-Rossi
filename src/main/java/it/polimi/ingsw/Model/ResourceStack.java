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
     * toString override method for ResourceStack Class.
     */
    public String toString() {
        return shields+" "+servants+" "+coins+" "+stones;
    }
}
