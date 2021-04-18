package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

import java.util.ArrayList;

public class ChooseProductionOutput extends Action implements ActionInterface {
    private boolean firstLeaderCard;
    private boolean secondLeaderCard;
    private boolean basicProduction;
    private ArrayList<ResourceType> firstLeaderCardOutput;
    private ArrayList<ResourceType> secondLeaderCardOutput;
    private ArrayList<ResourceType> basicProductionOutput;

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

    public ArrayList<ResourceType> getFirstLeaderCardOutput() {
        return firstLeaderCardOutput;
    }

    public ArrayList<ResourceType> getSecondLeaderCardOutput() {
        return secondLeaderCardOutput;
    }

    public ArrayList<ResourceType> getBasicProductionOutput() {
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

    public void setFirstLeaderCardOutput(ArrayList<ResourceType> firstLeaderCardOutput) {
        this.firstLeaderCardOutput = firstLeaderCardOutput;
    }

    public void setSecondLeaderCardOutput(ArrayList<ResourceType> secondLeaderCardOutput) {
        this.secondLeaderCardOutput = secondLeaderCardOutput;
    }

    public void setBasicProductionOutput(ArrayList<ResourceType> basicProductionOutput) {
        this.basicProductionOutput = basicProductionOutput;
    }

    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(firstLeaderCard)
            for(ResourceType type : firstLeaderCardOutput)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("1- One resource was of type NONE.");

        if(secondLeaderCard)
            for(ResourceType type : secondLeaderCardOutput)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("2- One resource was of type NONE.");

        if(basicProduction)
            for(ResourceType type : basicProductionOutput)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("3- One resource was of type NONE.");

        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        if(firstLeaderCard)
            if(!(game.getCurrentPlayer().getBoard().getLeaderCards()[0].isActive()) || firstLeaderCardOutput.size() != game.getCurrentPlayer().getBoard().getLeaderCards()[0].getJollyOut() || game.getCurrentPlayer().getBoard().getLeaderCards()[0].getAction() != LeaderCardAction.PRODUCTIONPOWER)
                return false;

        if(secondLeaderCard)
            if(!(game.getCurrentPlayer().getBoard().getLeaderCards()[1].isActive()) || secondLeaderCardOutput.size() != game.getCurrentPlayer().getBoard().getLeaderCards()[1].getJollyOut() || game.getCurrentPlayer().getBoard().getLeaderCards()[1].getAction() != LeaderCardAction.PRODUCTIONPOWER)
                return false;

        if(basicProduction)
            if(basicProductionOutput.size() != game.getCurrentPlayer().getBoard().getBasicProduction().getJollyOut())
                return false;

        return true;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();
        if(!this.canBeApplied(game))
            return "Tried to use not valid Leader Cards or tried to get more resources than possible.";

        if(firstLeaderCard)
            for(ResourceType type : this.basicProductionOutput)
                output.addResource(1, type);
        if(secondLeaderCard)
            for(ResourceType type : this.basicProductionOutput)
                output.addResource(1, type);
        if(basicProduction)
            for(ResourceType type : this.basicProductionOutput)
                output.addResource(1, type);

        game.getCurrentPlayer().getBoard().getResourceManager().addProductionResources(output);

        return "SUCCESS";
    }
}
