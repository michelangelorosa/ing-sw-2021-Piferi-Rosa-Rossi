package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Board {
    private final ResourceManager resourceManager;
    private final DevelopmentCardSlots developmentCardSlots;
    private final LeaderCard[] leaderCards;
    private BasicProduction basicProduction;


    public Board() {
        this.resourceManager = new ResourceManager();
        this.developmentCardSlots = new DevelopmentCardSlots();
        this.leaderCards = new LeaderCard[2];
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public DevelopmentCardSlots getDevelopmentCardSlots() {
        return developmentCardSlots;
    }

    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    public BasicProduction getBasicProduction() {
        return basicProduction;
    }

    public void setBasicProduction(BasicProduction basicProduction) {
        this.basicProduction = basicProduction;
    }

    public boolean canStartProduction(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ArrayList<ResourceType> inputs) {
        ResourceStack totalInput = new ResourceStack(0, 0, 0, 0);
        ResourceType[] resourceTypes = ResourceType.values();

        if(developmentCards != null && developmentCards.size() > 0)
            for(DevelopmentCard card : developmentCards)
                totalInput.addToAllTypes(card.getInput());

        if(leaderCards != null && leaderCards.size() > 0)
            for(LeaderCard leaderCard : leaderCards)
                totalInput.addToAllTypes(leaderCard.getInput());

       if(wantsBasicProduction) {
           for (ResourceType type : inputs)
               totalInput.addResource(1, type);
           totalInput.addToAllTypes(this.basicProduction.getFixedInputs());
       }

       for(int i = 1; i <= 4; i++)
           if(resourceManager.countAllResourcesByType(resourceTypes[i]) < totalInput.getResource(resourceTypes[i]))
                return false;

       return true;
    }

    public void getProductionCost(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ArrayList<ResourceType> inputs) {
        ResourceStack totalInput = new ResourceStack(0,0,0,0);

        if(developmentCards != null && developmentCards.size() > 0)
            for (DevelopmentCard card : developmentCards)
                totalInput.addToAllTypes(card.getInput());

        if(leaderCards != null && leaderCards.size() > 0)
            for (LeaderCard leaderCard : leaderCards)
                totalInput.addToAllTypes(leaderCard.getInput());

        if(wantsBasicProduction) {
            for (ResourceType input : inputs)
                totalInput.addResource(1, input);
            totalInput.addToAllTypes(this.basicProduction.getFixedInputs());
        }

        this.resourceManager.setTemporaryResourcesToPay(totalInput);
    }

    public ResourceStack getFixedProductionOutput(Player player, ArrayList<DevelopmentCard> developmentCards, boolean wantsBasicProduction) {
        ResourceStack totalOutput = new ResourceStack(0,0,0,0);
        int outputFaithPoints = 0;

        if(developmentCards != null && developmentCards.size() > 0) {
            for (DevelopmentCard card : developmentCards) {
                totalOutput.addToAllTypes(card.getOutput());
                outputFaithPoints += card.getOutputFaith();
            }
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

    public boolean canActivateLeaderCard(LeaderCard leaderCard) {
        ResourceStack leaderCardCost = leaderCard.getResourcesRequired();
        LeaderRequirements leaderCardNeededCards = leaderCard.getCardsRequired();
        ResourceType[] resourceTypes = ResourceType.values();

        if(!leaderCardNeededCards.hasRequirements(this.developmentCardSlots.sumResources()))
            return false;

        else
            for(int i = 1; i <= 4; i++)
                if(this.resourceManager.countAllResourcesByType(resourceTypes[i]) < leaderCardCost.getResource(resourceTypes[i]))
                    return false;

        return true;
    }

    public void activateLeaderCard(LeaderCard leaderCard) {
        leaderCard.setActive(true);
        if(leaderCard.getAction() == LeaderCardAction.EXTRADEPOT)
            this.resourceManager.activateLeaderDepot(leaderCard);
    }
}
