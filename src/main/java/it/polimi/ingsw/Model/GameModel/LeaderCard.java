package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

/**
 * There are 4 types of Leader Card, depending on the type of special effect they apply on the Game.
 * All of them have the following attributes: a CardId for identification, victoryPoints for the score.
 * Type is a byte identifying the type
 *  */
public class LeaderCard extends RedLeaderCard {

    /**
     * Constructor for LeaderCard Class. The override of ability is implicit in the parameters
     */
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack discount) {
        super(cardId, victoryPoints, LeaderCardAction.DISCOUNT, resourcesRequired, cards);
        this.active = false;
        this.discount=discount;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, Marble marble) {
        super(cardId, victoryPoints, LeaderCardAction.WHITEMARBLE, resourcesRequired, cards);
        this.active = false;
        this.marble = marble;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack input,int jollyOut, int faith) {
        super(cardId, victoryPoints, LeaderCardAction.PRODUCTIONPOWER, resourcesRequired, cards);
        this.active = false;
        this.input = input;
        this.faith = faith;
        this.jollyOut = jollyOut;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceType resource) {
        super(cardId, victoryPoints, LeaderCardAction.EXTRADEPOT, resourcesRequired, cards);
        this.active = false;
        this.resource = resource;
    }

    /**Setter for Active*/
    public void setActive(boolean active) {
        this.active = active;
    }

    /**Getter for ResourceRequired*/
    public ResourceStack getResourcesRequired() {
        return (ResourceStack) resourcesRequired;
    }

    /**Getter for cardsRequired*/
    public LeaderRequirements getCardsRequired() {
        return (LeaderRequirements) cardsRequired;
    }

    /**Getter for discount*/
    public ResourceStack getDiscount() {
        return (ResourceStack) discount;
    }

    /**Getter for marble*/
    public Marble getMarble() {
        return marble;
    }

    /**Getter for input*/
    public ResourceStack getInput() {
        return (ResourceStack) input;
    }

    public RedLeaderCard toView(){
        return (RedLeaderCard) this;
    }

    public void discard() throws IllegalArgumentException{
        if(!this.discarded && !this.active) discarded = true;
        else throw new IllegalArgumentException("You cannot discard this Leader Card");
    }

}
