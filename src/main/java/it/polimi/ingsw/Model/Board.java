package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Board {
    private final ResourceManager resourceManager;
    private final DevelopmentCardSlots developmentCardSlots;
    private final LeaderCard[] leaderCards;


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

    public boolean canStartProduction(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ResourceType[] inputs) {
        ResourceStack totalInput = new ResourceStack(0, 0, 0, 0);
        ResourceType[] resourceTypes = ResourceType.values();

        if(developmentCards != null && developmentCards.size() > 0)
            for(DevelopmentCard card : developmentCards)
                totalInput.addToAllTypes(card.getInput());

        if(leaderCards != null && leaderCards.size() > 0)
            for(LeaderCard leaderCard : leaderCards)
                totalInput.addToAllTypes(leaderCard.getInput());

       if(wantsBasicProduction)
           for(ResourceType type : inputs)
               totalInput.addResource(1, type);

       for(int i = 1; i <= 4; i++)
           if(resourceManager.countAllResourcesByType(resourceTypes[i]) < totalInput.getResource(resourceTypes[i]))
                return false;

           this.resourceManager.setTemporaryResourcesToPay(totalInput);
       return true;
    }

    public void getProductionCost(ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, boolean wantsBasicProduction, ResourceType[] inputs) {
        ResourceStack totalInput = new ResourceStack(0,0,0,0);

        if(developmentCards != null && developmentCards.size() > 0)
            for (DevelopmentCard card : developmentCards)
                totalInput.addToAllTypes(card.getInput());

        if(leaderCards != null && leaderCards.size() > 0)
            for (LeaderCard leaderCard : leaderCards)
                totalInput.addToAllTypes(leaderCard.getInput());

        if(wantsBasicProduction)
            for (ResourceType input : inputs)
                totalInput.addResource(1, input);

        this.resourceManager.setTemporaryResourcesToPay(totalInput);
    }

    public void getProductionOutput(Player player, ArrayList<DevelopmentCard> developmentCards, ArrayList<LeaderCard> leaderCards, ArrayList<ResourceType> leaderCardsJollies, boolean wantsBasicProduction, ResourceType output) {
        ResourceStack totalOutput = new ResourceStack(0,0,0,0);
        int outputFaithPoints = 0;

        if(developmentCards != null && developmentCards.size() > 0) {
            for (DevelopmentCard card : developmentCards) {
                totalOutput.addToAllTypes(card.getOutput());
                outputFaithPoints += card.getOutputFaith();
            }
        }

        if(leaderCards != null && leaderCards.size() > 0) {
            for(int i = 0; i < leaderCards.size(); i++) {
                totalOutput.addResource(leaderCards.get(i).getJollyOut(), leaderCardsJollies.get(i));
                outputFaithPoints += leaderCards.get(i).getFaith();
            }
        }

        if(wantsBasicProduction) {
            totalOutput.addResource(1, output);
        }

        if(outputFaithPoints > 0)
            player.stepAhead(outputFaithPoints);
        if(!totalOutput.isEmpty())
            this.resourceManager.addProductionResources(totalOutput);
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
