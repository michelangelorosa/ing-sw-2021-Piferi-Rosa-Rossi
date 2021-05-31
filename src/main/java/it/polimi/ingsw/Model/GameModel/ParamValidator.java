package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.View.ReducedModel.RedLeaderRequirements;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

/**
 * ParamValidator checks if a given input from the param modifier is valid. If valid the functions of this class return true, if there's anything wrong false
 */
public class ParamValidator {

    /**
     * Used to validate a LeaderCard, every possible field gets checked
     * @param leaderCard    The card to check
     * @return              true if valid, false otherwise
     */
    public static boolean validateCard(LeaderCard leaderCard){
        if(leaderCard.getCardId()<48)
            return false;
        if(leaderCard.getVictoryPoints()<0||leaderCard.getVictoryPoints()>99)
            return false;
        if(leaderCard.isActive()==true)
            return false;
        if(!leaderCard.getResourcesRequired().isValid())
            return false;
        if(leaderCard.getAction().equals(LeaderCardAction.DISCOUNT))
        {
            if(!leaderCard.getDiscount().isValid())
                return false;
        }
        if(leaderCard.getAction().equals(LeaderCardAction.WHITEMARBLE))
        {
            if(leaderCard.getMarble().equals(Marble.WHITE))
                return false;
        }
        if(leaderCard.getAction().equals(LeaderCardAction.PRODUCTIONPOWER))
        {
            if(!leaderCard.getInput().isValid())
                return false;
            if(leaderCard.getFaith()<0||leaderCard.getFaith()>20)
                return false;
            if(leaderCard.getJollyOut()<0||leaderCard.getJollyOut()>20)
                return false;
        }
        if(leaderCard.getAction().equals(LeaderCardAction.EXTRADEPOT))
        {
            if(leaderCard.getResource().equals(ResourceType.NONE))
                return false;
        }
        return leaderCard.getCardsRequired().isValid(leaderCard.getCardsRequired());
    }
    /**
     * Used to validate a DevelopmentCard, every possible field gets checked
     * @param developmentCard    The card to check
     * @return              true if valid, false otherwise
     */
    public static boolean validateCard(DevelopmentCard developmentCard){
        if(developmentCard.getColor().equals(null)||developmentCard.getLevel().equals(null))
            return false;
        if(developmentCard.getCardId()<1||developmentCard.getCardId()>48)
            return false;
        if(developmentCard.getVictoryPoints()<0||developmentCard.getVictoryPoints()>99)
            return false;
        if(!developmentCard.getCost().isValid())
            return false;
        if(!developmentCard.getInput().isValid())
            return false;
        if(!developmentCard.getOutput().isValid())
            return false;
        if(developmentCard.getOutputFaith()<0||developmentCard.getOutputFaith()>20)
            return false;
        return true;
    }
}
