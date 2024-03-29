package it.polimi.ingsw.Model.GameModel;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.Model.Enums.*;

/**
 * Board Class contains all parts defining the playing board for each Player.
 * <p>Attributes:</p>
 * <ul>
 *     <li>ResourceManager "resourceManager" contains both the Warehouse and the Strongbox</li>
 *     <li>DevelopmentCardSlots "developmentCardSlots" contains all three Development Card slots on the player's board</li>
 *     <li>LeaderCard[] "leaderCards": contains both leader cards owned by the player</li>
 *     <li>BasicProduction "basicProduction": defines the basic production power of the player's board</li>
 * </ul>
 * @author everyone
 */
public class Board implements Serializable {
    private final ResourceManager resourceManager;
    private final DevelopmentCardSlots developmentCardSlots;
    private final LeaderCard[] leaderCards;
    private BasicProduction basicProduction;

    /**
     * Constructor for Board Class.
     */
    public Board() {
        this.resourceManager = new ResourceManager();
        this.developmentCardSlots = new DevelopmentCardSlots();
        this.leaderCards = new LeaderCard[2];
        this.basicProduction = new BasicProduction(2, 1);
    }

    /**
     * Getter for "resourceManager" attribute.
     */
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    /**
     * Getter for "developmentCardSlots" attribute.
     */
    public DevelopmentCardSlots getDevelopmentCardSlots() {
        return developmentCardSlots;
    }

    /**
     * Getter for "leaderCards" attribute.
     */
    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    /**
     * Getter for "basicProduction" attribute.
     */
    public BasicProduction getBasicProduction() {
        return basicProduction;
    }

    /**
     * Setter for "basicProduction" attribute.
     */
    public void setBasicProduction(BasicProduction basicProduction) {
        this.basicProduction = basicProduction;
    }

    /**
     * Counts input resources of the Production.
     * @param developmentCards Cards chosen to activate production.
     * @param leaderCards Leader Cards chosen to activate production.
     * @param wantsBasicProduction True if the player wants to activate the basic production.
     * @param inputs All inputs of the basic production.
     * @return A stack containing all inputs.
     */
    public ResourceStack productionInputAdder(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ArrayList<ResourceType> inputs) throws IllegalArgumentException {
        ResourceStack totalInput = new ResourceStack(0, 0, 0, 0);

        if(developmentCards != null && developmentCards.size() > 0)
            for(DevelopmentCard card : developmentCards)
                totalInput.addToAllTypes(card.getInput());

        if(leaderCards != null && leaderCards.size() > 0)
            for(LeaderCard leaderCard : leaderCards) {
                if(!leaderCard.isActive())
                    throw new IllegalArgumentException("Model.Board: Leader Card was not active.");
                else if(leaderCard.getAction() != LeaderCardAction.PRODUCTIONPOWER)
                    throw new IllegalArgumentException("Model.Board: Leader Card was not of type PRODUCTION POWER.");
                else
                    totalInput.addToAllTypes(leaderCard.getInput());
            }

        if(wantsBasicProduction) {
            for (ResourceType type : inputs)
                totalInput.addResource(1, type);
            totalInput.addToAllTypes(this.basicProduction.getFixedInputs());
        }
        return totalInput;
    }

    /**
     * Checks if the player has enough resources to start a production.
     * @param developmentCards Cards chosen to activate production.
     * @param leaderCards Leader Cards chosen to activate production.
     * @param wantsBasicProduction True if the player wants to activate the basic production.
     * @param inputs All inputs of the basic production.
     * @return True if the player can start the Production.
     */
    public boolean canStartProduction(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ArrayList<ResourceType> inputs) {
        ResourceStack totalInput;
        ResourceType[] resourceTypes = ResourceType.values();

        totalInput = this.productionInputAdder(developmentCards, leaderCards, wantsBasicProduction, inputs);

        for(int i = 1; i <= 4; i++)
            if(resourceManager.countAllResourcesByType(resourceTypes[i]) < totalInput.getResource(resourceTypes[i]))
                return false;

        return true;
    }

    /**
     * Gets the total cost needed to start the Production.
     * @param developmentCards Cards chosen to activate production.
     * @param leaderCards Leader Cards chosen to activate production.
     * @param wantsBasicProduction True if the player wants to activate the basic production.
     * @param inputs All inputs of the basic production.
     */
    public void getProductionCost(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ArrayList<ResourceType> inputs) {
        ResourceStack totalInput;

        totalInput = this.productionInputAdder(developmentCards, leaderCards, wantsBasicProduction, inputs);

        this.resourceManager.setTemporaryResourcesToPay(totalInput);
    }

    /**
     * Gets the total output of non-choosable outputs of the Production.
     * @param player The player who started a Production (to get faith points).
     * @param developmentCards Cards chosen to activate production.
     * @param leaderCards Leader Cards chosen to activate production.
     * @param wantsBasicProduction True if the player wants to activate the basic production.
     * @return ResourceStack containing total non-choosable output of the Production.
     */
    public ResourceStack getFixedProductionOutput(Player player, ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction) throws IllegalArgumentException {
        ResourceStack totalOutput = new ResourceStack(0,0,0,0);
        int outputFaithPoints = 0;

        if(developmentCards != null && developmentCards.size() > 0) {
            for (DevelopmentCard card : developmentCards) {
                totalOutput.addToAllTypes(card.getOutput());
                outputFaithPoints += card.getOutputFaith();
            }
        }

        if(leaderCards != null && leaderCards.size() > 0)
            for(LeaderCard leaderCard : leaderCards) {
                if(!leaderCard.isActive())
                    throw new IllegalArgumentException("Model.Board: Leader Card was not active.");
                else if(leaderCard.getAction() != LeaderCardAction.PRODUCTIONPOWER)
                    throw new IllegalArgumentException("Model.Board: Leader Card was not of type PRODUCTIONPOWER.");
                else
                    outputFaithPoints += leaderCard.getFaith();
            }


        if(wantsBasicProduction) {
            totalOutput.addToAllTypes(this.basicProduction.getFixedOutputs());
            outputFaithPoints += this.basicProduction.getOutputFaith();
        }

        if(outputFaithPoints > 0)
            player.stepAhead(outputFaithPoints);
        if(!totalOutput.isEmpty())
            return totalOutput;
        else
            return new ResourceStack(0,0,0,0);
    }

    /**
     * Checks if the player can activate a given leaderCard (checks both Resources and Cards).
     * @param leaderCard Leader Card to be activated.
     * @return True if the card can be activated.
     */
    public boolean canActivateLeaderCard(LeaderCard leaderCard) {
        ResourceStack leaderCardCost = leaderCard.getResourcesRequired();
        LeaderRequirements leaderCardNeededCards = leaderCard.getCardsRequired();
        ResourceType[] resourceTypes = ResourceType.values();

        if(leaderCardNeededCards.getGeneric()){
            if(!leaderCardNeededCards.hasRequirements(this.developmentCardSlots.sumColors()) || leaderCard.isDiscarded())
                return false;
        }
        else if(!leaderCardNeededCards.hasRequirements(this.developmentCardSlots.sumResources()) || leaderCard.isDiscarded())
            return false;

        for(int i = 1; i <= 4; i++)
            if(this.resourceManager.countAllResourcesByType(resourceTypes[i]) < leaderCardCost.getResource(resourceTypes[i]))
                return false;

        return true;
    }

    /**
     * Activates a given Leader Card.
     * @param leaderCard Leader Card to be activated.
     */
    public void activateLeaderCard(LeaderCard leaderCard) {
        leaderCard.setActive(true);
        if(leaderCard.getAction() == LeaderCardAction.EXTRADEPOT)
            this.resourceManager.activateLeaderDepot(leaderCard);
    }
}