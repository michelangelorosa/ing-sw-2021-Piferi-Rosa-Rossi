package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCard;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

/**
 * DevelopmentCard Class describes the different methods needed for the creation and usage of a Development Card inside the Model.
 * @author everyone
 */
public class DevelopmentCard extends RedDevelopmentCard {

    /**
     * Constructor for DevelopmentCard Class.
     */
    public DevelopmentCard(Color color, Level level, int cardId, int victoryPoints, RedResourceStack cost, RedResourceStack input, RedResourceStack output, int outputFaith) {
        super(cardId, color, level);
        this.victoryPoints = victoryPoints;
        this.cost = cost;
        this.input = input;
        this.output = output;
        this.outputFaith = outputFaith;
    }

    /**
     * Setter for "victoryPoints" attribute.
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Setter for "outputFaith" attribute.
     */
    public void setOutputFaith(int outputFaith) {
        this.outputFaith = outputFaith;
    }

    /**
     * Getter for "cost" attribute.
     */
    public ResourceStack getCost() {
        return (ResourceStack) cost;
    }

    /**
     * Setter for "cost" attribute.
     */
    public void setCost(RedResourceStack cost) {
        this.cost = cost;
    }

    /**
     * Getter for "input" attribute.
     */
    public ResourceStack getInput() {
        return (ResourceStack)input;
    }

    /**
     * Setter for "input" attribute.
     */
    public void setInput(RedResourceStack input) {
        this.input = input;
    }

    /**
     * Getter for "output" attribute.
     */
    public ResourceStack getOutput() {
        return (ResourceStack)output;
    }

    /**
     * Setter for "output" attribute.
     */
    public void setOutput(RedResourceStack output) {
        this.output = output;
    }

    /**Converts model classes to view classes*/
    public RedDevelopmentCard toView() {
        return this;
    }
}
