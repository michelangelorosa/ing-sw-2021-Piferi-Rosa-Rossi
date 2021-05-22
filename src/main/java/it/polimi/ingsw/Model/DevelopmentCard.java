package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCard;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

import java.io.Serializable;

/**
 * DevelopmentCard Class describes the different attributes and methods
 * needed for the creation of a Development Card.
 */
public class DevelopmentCard extends RedDevelopmentCard {

    /**
     * Constructor for DevelopmentCard Class.
     */
    public DevelopmentCard(Color color, Level level, int cardId, int victoryPoints, RedResourceStack cost, RedResourceStack input, RedResourceStack output, int outputFaith) {
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
     * Setter for "victoryPoints" attribute in DevelopmentCard Class.
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
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
        return (ResourceStack) cost;
    }

    /**
     * Setter for "cost" attribute in DevelopmentCard Class.
     */
    public void setCost(RedResourceStack cost) {
        this.cost = cost;
    }

    /**
     * Getter for "input" attribute in DevelopmentCard Class.
     */
    public ResourceStack getInput() {
        return (ResourceStack)input;
    }

    /**
     * Setter for "input" attribute in DevelopmentCard Class.
     */
    public void setInput(RedResourceStack input) {
        this.input = input;
    }

    /**
     * Getter for "output" attribute in DevelopmentCard Class.
     */
    public ResourceStack getOutput() {
        return (ResourceStack)output;
    }

    /**
     * Setter for "output" attribute in DevelopmentCard Class.
     */
    public void setOutput(RedResourceStack output) {
        this.output = output;
    }

    /**Method for converting model classes to view classes*/
    public RedDevelopmentCard toView() {
        return (RedDevelopmentCard)this;
    }
}
