package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.Random;

/**
 * Market Class defines the contents and behaviour of the game's market. A Market Class-type
 * object contains a 2D-array final attribute containing marbles and an extra marble to
 * push inside the market whenever a player buys resources.
 */
public class Market implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final Marble[][] marbles;
    private Marble extraMarble;

    /**
     * Constructor for Market Class.
     */
    public Market() {
        marbles = new Marble[3][4];
        this.reset();
    }

    /**
     * Getter for "marbles" attribute.
     */
    public Marble[][] getMarbles() {
        return marbles;
    }

    /**
     * Getter for "extraMarble" attribute.
     */
    public Marble getExtraMarble() {
        return extraMarble;
    }

    /**
     * This method is used to reset the market to initial conditions, with randomized marbles
     * inside the 2D-array.
     */
    public void reset() {
        int upperBound = 6;
        int randomNumber;
        Random rand = new Random();
        Marble[] marbles = Marble.values();

        for(int j = 0; j <= 3; j++)
            for(int i = 0; i <= 2; i++) {
                randomNumber = rand.nextInt(upperBound);
                this.marbles[i][j] = marbles[randomNumber];
            }
        randomNumber = rand.nextInt(upperBound);
        this.extraMarble = marbles[randomNumber];
    }

    /**
     * This method is used when a player chooses a row he wants to buy from the market.
     * <p>
     * It transforms the selected row of marbles into a ResourceStack of resources which
     * will then be processed by the Resource Manager.
     * The player's faith is also updated if the row contains red marbles and the method transforms
     * white marbles if it is necessary.
     * @param row is the number indicating the row.
     * @param player is the player corresponding to the action (needed to update faith).
     */
    public void rowToResources(int row, Player player) throws IllegalArgumentException {
        player.getBoard().getResourceManager().setTemporaryWhiteMarbles(0);

        ResourceStack resourceStack = new ResourceStack(0,0,0,0);
        if(row < 0 || row > 2)
            throw new IllegalArgumentException("Model: Row index out of bounds.");

        for(int j = 0; j <= 3; j++)
            resourceStack.addResource(1, this.marbles[row][j].marbleToResource(player));


        Marble[] newMarbles = new Marble[4];
        newMarbles[0] = this.marbles[row][1];
        newMarbles[1] = this.marbles[row][2];
        newMarbles[2] = this.marbles[row][3];
        newMarbles[3] = extraMarble;

        extraMarble = this.marbles[row][0];
        this.marbles[row][0] = newMarbles[0];
        this.marbles[row][1] = newMarbles[1];
        this.marbles[row][2] = newMarbles[2];
        this.marbles[row][3] = newMarbles[3];

        player.getBoard().getResourceManager().setTemporaryResourcesToPay(resourceStack);
    }

    /**
     * This method is used when a player chooses a column he wants to buy from the market.
     * <p>
     * It transforms the selected column of marbles into a ResourceStack of resources which
     * will then be processed by the Resource Manager.
     * The player's faith is also updated if the column contains red marbles and the method transforms
     * white marbles if it is necessary.
     * @param column is the number indicating the column.
     * @param player is the player corresponding to the action (needed to update faith).
     * not have such card, the parameter should be null.
     */
    public void columnToResources(int column, Player player) throws IllegalArgumentException{
        player.getBoard().getResourceManager().setTemporaryWhiteMarbles(0);

        ResourceStack resourceStack = new ResourceStack(0,0,0,0);
        if(column < 0 || column > 3)
            throw new IllegalArgumentException("Model: Column index out of bounds.");

        for(int i = 0; i <= 2; i++)
            resourceStack.addResource(1, this.marbles[i][column].marbleToResource(player));

        Marble[] newMarbles = new Marble[3];
        newMarbles[0] = this.marbles[1][column];
        newMarbles[1] = this.marbles[2][column];
        newMarbles[2] = extraMarble;

        extraMarble = this.marbles[0][column];
        this.marbles[0][column] = newMarbles[0];
        this.marbles[1][column] = newMarbles[1];
        this.marbles[2][column] = newMarbles[2];

        player.getBoard().getResourceManager().setTemporaryResourcesToPay(resourceStack);
    }

    public void whiteMarbleToResource(Player player, LeaderCard leaderCard) throws IllegalArgumentException {
        if(player.getBoard().getResourceManager().getTemporaryWhiteMarbles() <= 0)
            throw new IllegalArgumentException("No White Marbles to convert.");
        else if(!leaderCard.isActive())
            throw new IllegalArgumentException("Card has to be active to convert White Marble.");
        else if(leaderCard.getAction() != LeaderCardAction.WHITEMARBLE)
            throw new IllegalArgumentException("Card has to be of type WHITEMARBLE to convert White Marble.");

        player.getBoard().getResourceManager().getTemporaryResourcesToPay().addResource(1, leaderCard.getMarble().marbleToResource(player));
        player.getBoard().getResourceManager().removeWhiteMarble();
    }

    /**
     * toString override method for Market Class.
     */
    @Override
    public String toString() {
        return marbles[0][0]+" "+marbles[0][1]+" "+marbles[0][2]+" "+marbles[0][3]+System.lineSeparator()+
                marbles[1][0]+" "+marbles[1][1]+" "+marbles[1][2]+" "+marbles[1][3]+System.lineSeparator()+
                marbles[2][0]+" "+marbles[2][1]+" "+marbles[2][2]+" "+marbles[2][3];
    }

    /**
     * This method is used for test purposes only.
     */
    public void testMethod() {
        this.marbles[0][0] = Marble.BLUE; this.marbles[0][1] = Marble.YELLOW; this.marbles[0][2] = Marble.RED; this.marbles[0][3] = Marble.BLUE;
        this.marbles[1][0] = Marble.GREY; this.marbles[1][1] = Marble.GREY; this.marbles[1][2] = Marble.WHITE; this.marbles[1][3] = Marble.WHITE;
        this.marbles[2][0] = Marble.PURPLE; this.marbles[2][1] = Marble.RED; this.marbles[2][2] = Marble.YELLOW; this.marbles[2][3] = Marble.RED;
        this.extraMarble = Marble.WHITE;
    }
}
