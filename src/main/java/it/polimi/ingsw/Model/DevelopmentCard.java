package it.polimi.ingsw.Model;

/**
 * DevelopmentCard Class describes the different attributes and methods
 * needed for the creation of a Development Card.
 */
public class DevelopmentCard {

    /**
     * Color and Level are enum type classes containing all possible colors and levels
     * a Development Card could have.
     * Each instantiated Development Card has two attributes (color and level) indicating the card's
     * color and level, which are final, has they shall not be changed.
     */
    private final Color color;
    private final Level level;

    /**
     * Each Development Card has its own unique cardId (hence the "final" declaration), used for
     * recognition, and a finite number of victory points.
     */
    private final int cardId;
    private int victoryPoints;

    /**
     * A Development Card has three ResourceStack Class - type attributes containing namely the cost,
     * production input and production output of the card. As some cards can have faith points as a
     * production outcome, an integer attribute called outputFaith is introduced.
     */
    private ResourceStack cost;
    private ResourceStack input;
    private ResourceStack output;
    private int outputFaith;

    /**
     * Constructor for DevelopmentCard Class.
     */
    public DevelopmentCard(Color color, Level level, int cardId, int victoryPoints, ResourceStack cost, ResourceStack input, ResourceStack output, int outputFaith) {
        this.color = color;
        this.level = level;
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.cost = cost;
        this.input = input;
        this.output = output;
        this.outputFaith = outputFaith;
    }

    /**
     * Getter for "color" attribute in DevelopmentCard Class.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Getter for "level" attribute in DevelopmentCard Class.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Getter for "cardId" attribute in DevelopmentCard Class.
     */

    public int getCardId() {
        return cardId;
    }

    /**
     * Getter for "victoryPoints" attribute in DevelopmentCard Class.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Setter for "victoryPoints" attribute in DevelopmentCard Class.
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Getter for "outputFaith" attribute in DevelopmentCard Class.
     */
    public int getOutputFaith() {
        return outputFaith;
    }

    /**
     * Setter for "outputFaith" attribute in DevelopmentCard Class.
     */
    public void setOutputFaith(int outputFaith) {
        this.outputFaith = outputFaith;
    }

    /**
     * Getter for "cost" attribute in DevelopmentCard Class.
     */
    public ResourceStack getCost() {
        return cost;
    }

    /**
     * Setter for "cost" attribute in DevelopmentCard Class.
     */
    public void setCost(ResourceStack cost) {
        this.cost = cost;
    }

    /**
     * Getter for "input" attribute in DevelopmentCard Class.
     */
    public ResourceStack getInput() {
        return input;
    }

    /**
     * Setter for "input" attribute in DevelopmentCard Class.
     */
    public void setInput(ResourceStack input) {
        this.input = input;
    }

    /**
     * Getter for "output" attribute in DevelopmentCard Class.
     */
    public ResourceStack getOutput() {
        return output;
    }

    /**
     * Setter for "output" attribute in DevelopmentCard Class.
     */
    public void setOutput(ResourceStack output) {
        this.output = output;
    }

    /**
     * toString override method for DevelopmentCard Class.
     */
    public String toString() {
        return cardId+" "+victoryPoints+" "+color+" "+level+" "+cost+" "+input+" "+output+" "+outputFaith;
    }

}
