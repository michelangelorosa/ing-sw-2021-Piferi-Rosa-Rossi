package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class ChooseProductionOutput {
    private final ActionType actionType;
    private boolean firstLeaderCard;
    private boolean secondLeaderCard;
    private boolean basicProduction;
    private ResourceType firstLeaderCardOutput;
    private ResourceType secondLeaderCardOutput;
    private ResourceType basicProductionOutput;

    private ResourceStack output;

    public ChooseProductionOutput() {
        this.actionType = ActionType.CHOOSE_PRODUCTION_OUTPUT;
        this.firstLeaderCard = false;
        this.secondLeaderCard = false;
        this.basicProduction = false;
        this.firstLeaderCardOutput = null;
        this.secondLeaderCardOutput = null;
        this.basicProductionOutput = null;
        this.output = new ResourceStack(0,0,0,0);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean isFirstLeaderCard() {
        return firstLeaderCard;
    }

    public boolean isSecondLeaderCard() {
        return secondLeaderCard;
    }

    public boolean isBasicProduction() {
        return basicProduction;
    }

    public ResourceType getFirstLeaderCardOutput() {
        return firstLeaderCardOutput;
    }

    public ResourceType getSecondLeaderCardOutput() {
        return secondLeaderCardOutput;
    }

    public ResourceType getBasicProductionOutput() {
        return basicProductionOutput;
    }

    public ResourceStack getOutput() {
        return output;
    }

    public void setFirstLeaderCard(boolean firstLeaderCard) {
        this.firstLeaderCard = firstLeaderCard;
    }

    public void setSecondLeaderCard(boolean secondLeaderCard) {
        this.secondLeaderCard = secondLeaderCard;
    }

    public void setBasicProduction(boolean basicProduction) {
        this.basicProduction = basicProduction;
    }

    public void setFirstLeaderCardOutput(ResourceType firstLeaderCardOutput) {
        this.firstLeaderCardOutput = firstLeaderCardOutput;
    }

    public void setSecondLeaderCardOutput(ResourceType secondLeaderCardOutput) {
        this.secondLeaderCardOutput = secondLeaderCardOutput;
    }

    public void setBasicProductionOutput(ResourceType basicProductionOutput) {
        this.basicProductionOutput = basicProductionOutput;
    }

    public boolean isCorrect() throws IllegalArgumentException {
        if((firstLeaderCard && firstLeaderCardOutput == ResourceType.NONE) || (!firstLeaderCard && firstLeaderCardOutput != ResourceType.NONE))
            throw new IllegalArgumentException("First Leader Card output Type out of bounds.");
        if((secondLeaderCard && secondLeaderCardOutput == ResourceType.NONE) || (!secondLeaderCard && secondLeaderCardOutput != ResourceType.NONE))
            throw new IllegalArgumentException("Second Leader output Card Type out of bounds.");
        if((basicProduction && basicProductionOutput == ResourceType.NONE) || (!basicProduction && basicProductionOutput != ResourceType.NONE))
            throw new IllegalArgumentException("Basic Production output Type out of bounds.");

        return true;
    }

    public String doChooseProductionOutput(Game game) {
        this.isCorrect();

        if(firstLeaderCard)
            output.addResource(1, firstLeaderCardOutput);
        if(secondLeaderCard)
            output.addResource(1, secondLeaderCardOutput);
        if(basicProduction)
            output.addResource(1, basicProductionOutput);

        game.getCurrentPlayer().getBoard().getResourceManager().addProductionResources(output);

        return "SUCCESS";
    }
}
