package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.View.ReducedModel.RedMarket;

import java.util.ArrayList;
import java.util.Random;

/**
 * Market Class defines the contents and behaviour of the game's market. A Market Class-type
 * object contains a 2D-array final attribute containing marbles and an extra marble to
 * push inside the market whenever a player buys resources.
 * @author redrick99
 */
public class Market extends RedMarket {
    /**
     * Constructor for Market Class.
     */
    public Market() {
        super();
        this.reset();
    }

    /**
     * Alternative constructor for Market Class.
     */
    public Market(Marble[][] marbles) {
        super(marbles);
    }

    /**
     * This method is used to reset the market to initial conditions, with randomized marbles
     * inside the 2D-array.
     */
    public void reset() {
        Random rand = new Random();
        int randomNumber;
        ArrayList<Marble> marbles = new ArrayList<>();

        for(int i = 0; i < 4; i++)
            marbles.add(Marble.WHITE);

        for(int i = 0; i < 2; i++)
            marbles.add(Marble.BLUE);

        for(int i = 0; i < 2; i++)
            marbles.add(Marble.GREY);

        for(int i = 0; i < 2; i++)
            marbles.add(Marble.YELLOW);

        for(int i = 0; i < 2; i++)
            marbles.add(Marble.PURPLE);

        marbles.add(Marble.RED);

        for(int j = 0; j <= 3; j++)
            for(int i = 0; i <= 2; i++) {
                randomNumber = rand.nextInt(marbles.size());
                this.marbles[i][j] = marbles.remove(randomNumber);
            }
        this.extraMarble = marbles.remove(0);
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


        this.rowChange(row);

        player.getBoard().getResourceManager().setTemporaryResourcesToPay(resourceStack);
    }

    /**
     * Changes market after a row purchase.
     * @param row row to be changes.
     */
    public void rowChange(int row) {
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

        this.columnChange(column);

        player.getBoard().getResourceManager().setTemporaryResourcesToPay(resourceStack);
    }

    /**
     * Realigns Market after a column choice.
     * @param column index of the column to change.
     */
    public void columnChange(int column) {
        Marble[] newMarbles = new Marble[3];
        newMarbles[0] = this.marbles[1][column];
        newMarbles[1] = this.marbles[2][column];
        newMarbles[2] = extraMarble;

        extraMarble = this.marbles[0][column];
        this.marbles[0][column] = newMarbles[0];
        this.marbles[1][column] = newMarbles[1];
        this.marbles[2][column] = newMarbles[2];
    }

    /**
     * Converts a white marble to a resource based on the Leader Card given.
     * @param player player who bought from the market.
     * @param leaderCard Leader Card from which to get the white marble.
     * @throws IllegalArgumentException if there are no white marbles to convert or if the Leader Card is not active or not
     * of type WHITEMARBLE.
     */
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
     * Setter for "extraMarble" attribute.
     */
    public void setExtraMarble(Marble extraMarble) {
        this.extraMarble = extraMarble;
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
