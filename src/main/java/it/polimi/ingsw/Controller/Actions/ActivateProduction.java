package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

import java.util.ArrayList;

/**
 * ActivateProduction Class contains data and methods to resolve a Client request regarding Production
 * activation.
 * <p>
 * Each boolean attribute represents a different production power the player wants to activate.
 */
public class ActivateProduction extends Action implements ActionInterface {

    /** Three booleans indicating the player's Board Slots. */
    private final boolean firstSlot;
    private final boolean secondSlot;
    private final boolean thirdSlot;

    /** Two booleans indicating the player's Leader Cards. */
    private final boolean firstLeaderCard;
    private final boolean secondLeaderCard;

    /** One boolean indicating the player's Basic Production and a array indicating the Basic Production inputs. */
    private final boolean basicProduction;
    private final ArrayList<ResourceType> basicProductionInputs;

    public ActivateProduction(boolean firstSlot, boolean secondSlot, boolean thirdSlot1, boolean firstLeaderCardActive, boolean secondLeaderCardActive, boolean basicProduction, ArrayList<ResourceType> basicProductionInputs) {
        this.actionType = ActionType.ACTIVATE_PRODUCTION;
        this.firstSlot = firstSlot;
        this.secondSlot = secondSlot;
        this.thirdSlot = thirdSlot1;
        this.firstLeaderCard = firstLeaderCardActive;
        this.secondLeaderCard = secondLeaderCardActive;
        this.basicProduction = basicProduction;
        this.basicProductionInputs = basicProductionInputs;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean isFirstSlot() {
        return firstSlot;
    }

    public boolean isSecondSlot() {
        return secondSlot;
    }

    public boolean isThirdSlot() {
        return thirdSlot;
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

    public ArrayList<ResourceType> getBasicProductionInputs() {
        return basicProductionInputs;
    }

    /**
     * This method checks if the input sent to the server is correct by assuring that the Basic Production
     * inputs are not of type NONE.
     * @return true if the message is correct.
     * @throws IllegalArgumentException if the specified type is NONE.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException{
        if(basicProduction && (basicProductionInputs.get(0) == ResourceType.NONE || basicProductionInputs.get(1) == ResourceType.NONE))
            throw new IllegalArgumentException("Illegal Type NONE in active basic production.");
        return true;
    }

    /**
     * This method checks if the input sent to the server is logically applicable.
     * @param game Is the game instance being played.
     * @return false if the Leader Card was already active, true if not.
     */
    @Override
    public boolean canBeApplied(Game game) {
        Player player = game.getCurrentPlayer();
        if(firstLeaderCard && (!player.getBoard().getLeaderCards()[0].isActive() || player.getBoard().getLeaderCards()[0].getAction() != LeaderCardAction.PRODUCTIONPOWER))
            return false;
        if(secondLeaderCard && (!player.getBoard().getLeaderCards()[1].isActive() || player.getBoard().getLeaderCards()[1].getAction() != LeaderCardAction.PRODUCTIONPOWER))
            return false;
        if(basicProduction && basicProductionInputs.size() != game.getCurrentPlayer().getBoard().getBasicProduction().getJollyIn())
            return false;

        return true;
    }

    /**
     * This method is used to actually activate production if the player has the correct requirements.
     * @param game Instance of the game which is being played.
     * @param chooseProductionOutput Object used to save the production's output for later use.
     * @return a String containing an error message or a SUCCESS statement.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();

        if(!this.canBeApplied(game))
            return "Leader Card not active or not required Type/Error in reading basic production inputs.";


        ArrayList<DevelopmentCard> devCards = new ArrayList<>();
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        ArrayList<ResourceType> inputs;

        DevelopmentCardSlots slots = game.getCurrentPlayer().getBoard().getDevelopmentCardSlots();


        if(this.firstSlot) {
            if(slots.getSlots()[0].isEmpty())
                return "Can't pick empty slot (1st)";
            else
                devCards.add(slots.getSlots()[0].getFirstCard());
        }
        if(this.secondSlot) {
            if(slots.getSlots()[1].isEmpty())
                return "Can't pick empty slot (2nd)";
            else
                devCards.add(slots.getSlots()[1].getFirstCard());
        }
        if(this.thirdSlot) {
            if(slots.getSlots()[2].isEmpty())
                return "Can't pick empty slot (3rd)";
            else
                devCards.add(slots.getSlots()[2].getFirstCard());
        }
        if(this.firstLeaderCard)
            leaderCards.add(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);

        if(this.secondLeaderCard)
            leaderCards.add(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);

        if(this.basicProduction)
            inputs = this.basicProductionInputs;
        else
            inputs = null;

        if(!game.getCurrentPlayer().getBoard().canStartProduction(devCards, leaderCards, this.basicProduction, inputs))
            return "Not enough Resources to start Production";

        else {
            game.getCurrentPlayer().getBoard().getProductionCost(devCards, leaderCards, this.basicProduction, inputs);

            chooseProductionOutput.getOutput().addToAllTypes(game.getCurrentPlayer().getBoard().getFixedProductionOutput(game.getCurrentPlayer(), devCards, leaderCards, this.basicProduction));

            if(this.firstLeaderCard)
                chooseProductionOutput.setFirstLeaderCard(true);

            if(this.secondLeaderCard)
                chooseProductionOutput.setSecondLeaderCard(true);

            if(this.basicProduction)
                chooseProductionOutput.setBasicProduction(true);

            return "SUCCESS";
        }
    }
}
