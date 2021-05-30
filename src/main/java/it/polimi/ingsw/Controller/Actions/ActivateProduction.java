package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardSlots;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

import java.util.ArrayList;

/**
 * ActivateProduction Class contains data and methods to resolve a Client request regarding Production
 * activation.
 * <p>
 * Each boolean attribute represents a different production power the player wants to activate.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li> boolean "firstSlot", "secondSlot", "thirdSlot": they indicate the player's Board Slots. </li>
 *     <li> boolean "firstLeaderCard", "secondLeaderCard": they indicate the player's Leader Cards. </li>
 *     <li> boolean "basicProduction": indicates the player's Basic Production. </li>
 *     <li> ArrayList(ResourceType) "basicProductionInputs": contains Basic Production inputs. </li>
 * </ul>
 * @author redrick99
 */
public class ActivateProduction extends Action {

    private final boolean firstSlot;
    private final boolean secondSlot;
    private final boolean thirdSlot;

    private final boolean firstLeaderCard;
    private final boolean secondLeaderCard;

    private final boolean basicProduction;
    private final ArrayList<ResourceType> basicProductionInputs;

    /**
     * Constructor for ActivateProduction Class.
     */
    public ActivateProduction(boolean firstSlot, boolean secondSlot, boolean thirdSlot1, boolean firstLeaderCard, boolean secondLeaderCard, boolean basicProduction, ArrayList<ResourceType> basicProductionInputs) {
        this.actionType = ActionType.ACTIVATE_PRODUCTION;
        this.firstSlot = firstSlot;
        this.secondSlot = secondSlot;
        this.thirdSlot = thirdSlot1;
        this.firstLeaderCard = firstLeaderCard;
        this.secondLeaderCard = secondLeaderCard;
        this.basicProduction = basicProduction;
        this.basicProductionInputs = basicProductionInputs;
    }

    /**
     * Getter for "firsSlot" attribute.
     */
    public boolean isFirstSlot() {
        return firstSlot;
    }

    /**
     * Getter for "secondSlot" attribute.
     */
    public boolean isSecondSlot() {
        return secondSlot;
    }

    /**
     * Getter for "thirdSlot" attribute.
     */
    public boolean isThirdSlot() {
        return thirdSlot;
    }

    /**
     * Getter for "firstLeaderCard" attribute.
     */
    public boolean isFirstLeaderCard() {
        return firstLeaderCard;
    }

    /**
     * Getter for "secondLeaderCard" attribute.
     */
    public boolean isSecondLeaderCard() {
        return secondLeaderCard;
    }

    /**
     * Getter for "basicProduction" attribute.
     */
    public boolean isBasicProduction() {
        return basicProduction;
    }

    /**
     * Getter for "basicProductionInputs" attribute.
     */
    public ArrayList<ResourceType> getBasicProductionInputs() {
        return basicProductionInputs;
    }

    /**
     * Checks if the input sent to the server is correct by assuring that there are no elements inside
     * the basicProductionInputs ArrayList which are equal to ResourceType.NONE.
     * @return true if there are no elements of ResourceType.NONE.
     * @throws IllegalArgumentException if at least one element is of ResourceType.NONE.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(basicProduction)
            for(ResourceType type : basicProductionInputs)
                if(type == ResourceType.NONE)
                    throw new IllegalArgumentException("Illegal Type NONE in active basic production.");
        return true;
    }

    /**
     * Checks if the input sent to the server is logically applicable buy running the following controls:
     * <ul>
     *     <li>If any of the booleans indicating a Leader Card is set to true, checks if the Leader Card is
     *     of type "PRODUCTION POWER" and active</li>
     *     <li>If "basicProduction" boolean is set to true, checks if the size of the basicProductionInputs
     *     ArrayList corresponds to the number of input resources needed by the Basic Production inside the
     *     Game being played.</li>
     * </ul>
     * @return false if at least one of the two controls is not respected.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        Player player = actionController.getGame().getCurrentPlayer();
        if(firstLeaderCard && (!player.getBoard().getLeaderCards()[0].isActive() || player.getBoard().getLeaderCards()[0].getAction() != LeaderCardAction.PRODUCTIONPOWER))
            return false;

        if(secondLeaderCard && (!player.getBoard().getLeaderCards()[1].isActive() || player.getBoard().getLeaderCards()[1].getAction() != LeaderCardAction.PRODUCTIONPOWER))
            return false;
        return !basicProduction || basicProductionInputs.size() == actionController.getGame().getCurrentPlayer().getBoard().getBasicProduction().getJollyIn();
    }

    /**
     * Activates production if the player has all the needed requirements.
     * <p>Firstly checks if the player has all the required resources to start a production, then saves data
     * to be used later when the player has to choose Production Output.</p>
     * At last, the Production is activated and a new Payment Cycle starts.
     * @return a String containing an error message if the player performing the action doesn't meet Production
     * requirements or if the player indicated empty card slots to be used for production, otherwise a SUCCESS statement.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();

        if(!this.canBeApplied(actionController)) {
            this.response = "Leader Card not active or not required Type/Error in reading basic production inputs.";
            return "Leader Card not active or not required Type/Error in reading basic production inputs.";
        }


        ArrayList<DevelopmentCard> devCards = new ArrayList<>();
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        ArrayList<ResourceType> inputs;

        DevelopmentCardSlots slots = actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots();


        if(this.firstSlot) {
            if(slots.getSlots()[0].isEmpty()) {
                this.response = "Can't pick empty slot (1st)";
                return "Can't pick empty slot (1st)";
            }
            else
                devCards.add(slots.getSlots()[0].getFirstCard());
        }
        if(this.secondSlot) {
            if(slots.getSlots()[1].isEmpty()) {
                this.response = "Can't pick empty slot (2nd)";
                return "Can't pick empty slot (2nd)";
            }
            else
                devCards.add(slots.getSlots()[1].getFirstCard());
        }
        if(this.thirdSlot) {
            if(slots.getSlots()[2].isEmpty()) {
                this.response = "Can't pick empty slot (3rd)";
                return "Can't pick empty slot (3rd)";
            }
            else
                devCards.add(slots.getSlots()[2].getFirstCard());
        }
        if(this.firstLeaderCard)
            leaderCards.add(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0]);

        if(this.secondLeaderCard)
            leaderCards.add(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1]);

        if(this.basicProduction)
            inputs = this.basicProductionInputs;
        else
            inputs = null;

        if(!actionController.getGame().getCurrentPlayer().getBoard().canStartProduction(devCards, leaderCards, this.basicProduction, inputs)) {
            this.response = "Not enough Resources to start Production";
            return "Not enough Resources to start Production";
        }

        else {
            actionController.getGame().getCurrentPlayer().getBoard().getProductionCost(devCards, leaderCards, this.basicProduction, inputs);

            actionController.getChooseProductionOutput().getOutput().addToAllTypes(actionController.getGame().getCurrentPlayer().getBoard().getFixedProductionOutput(actionController.getGame().getCurrentPlayer(), devCards, leaderCards, this.basicProduction));

            if(this.firstLeaderCard)
                actionController.getChooseProductionOutput().setFirstLeaderCard(true);

            if(this.secondLeaderCard)
                actionController.getChooseProductionOutput().setSecondLeaderCard(true);

            if(this.basicProduction)
                actionController.getChooseProductionOutput().setBasicProduction(true);

            if(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().isEmpty())
                this.response = "No Payment";
            else
                this.response = "SUCCESS";
            return "SUCCESS";
        }
    }

    /**
     * Prepares a ActivateProductionMessage type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ActivateProductionMessage message = new ActivateProductionMessage(actionController.getGame().getCurrentPlayerNickname());
        message.setError(this.response);

        if(this.response.equals("SUCCESS")) {
            message.addPossibleAction(ActionType.PAY_RESOURCE_PRODUCTION);
        }
        else if(this.response.equals("No Payment")) {
            message.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
        }
        else {
            message.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
            message.addPossibleAction(ActionType.BUY_CARD);
            message.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        }

        return message;
    }
}
