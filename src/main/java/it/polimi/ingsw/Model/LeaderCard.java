package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;

import java.io.Serializable;

/**
 * There are 4 types of Leader Card, depending on the type of special effect they apply on the Game.
 * All of them have the following attributes: a CardId for identification, victoryPoints for the score.
 * Type is a byte identifying the type
 *  */
public class LeaderCard implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final int cardId;
    private final int victoryPoints;
    private final LeaderCardAction action;
    private boolean active;
    private final ResourceStack resourcesRequired;
    private final LeaderRequirements cardsRequired;
    //ABILITY ONE: Discount on Development Card
    private ResourceStack discount;
    //ABILITY TWO: White Marble to Resource
    private Marble marble;
    //ABILITY THREE: New Dev w/ faith
    private ResourceStack input;
    private int faith;
    private int jollyOut;
    //ABILITY FOUR: Xtra Depot
    private ResourceType resource;

    /**
     * Constructor for LeaderCard Class. The override of ability is implicit in the parameters
     */
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack discount) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.DISCOUNT;
        this.active = false;
        this.discount=discount;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, Marble marble) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.WHITEMARBLE;
        this.active = false;
        this.marble = marble;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack input,int jollyOut, int faith) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.PRODUCTIONPOWER;
        this.active = false;
        this.input = input;
        this.faith = faith;
        this.jollyOut = jollyOut;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceType resource) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.EXTRADEPOT;
        this.active = false;
        this.resource = resource;
    }

    /**Setter for Active*/
    public void setActive(boolean active) {
        this.active = active;
    }

    /**Getter for cardId*/
    public int getCardId() {
        return cardId;
    }

    /**Getter for victoryPoints*/
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**Getter for Action*/
    public LeaderCardAction getAction() {
        return action;
    }

    /**Getter for Active*/
    public boolean isActive() {
        return active;
    }

    /**Getter for ResourceRequired*/
    public ResourceStack getResourcesRequired() {
        return resourcesRequired;
    }

    /**Getter for cardsRequired*/
    public LeaderRequirements getCardsRequired() {
        return cardsRequired;
    }

    /**Getter for discount*/
    public ResourceStack getDiscount() {
        return discount;
    }

    /**Getter for marble*/
    public Marble getMarble() {
        return marble;
    }

    /**Getter for input*/
    public ResourceStack getInput() {
        return input;
    }

    /**Getter for faith*/
    public int getFaith() {
        return faith;
    }

    /**Getter for jollyOut*/
    public int getJollyOut() {
        return jollyOut;
    }

    /**Getter for Resource*/
    public ResourceType getResource() {
        return resource;
    }

    public it.polimi.ingsw.View.ReducedModel.LeaderCard toView(){
        it.polimi.ingsw.View.ReducedModel.LeaderCard leaderCard;
        if(this.action == LeaderCardAction.DISCOUNT){
            leaderCard = new it.polimi.ingsw.View.ReducedModel.LeaderCard(this.cardId, this.victoryPoints, this.resourcesRequired.toView(), this.cardsRequired.toView(), this.discount.toView());
        }
        else if(this.action == LeaderCardAction.PRODUCTIONPOWER){
            leaderCard = new it.polimi.ingsw.View.ReducedModel.LeaderCard(this.cardId, this.victoryPoints, this.resourcesRequired.toView(), this.cardsRequired.toView(), this.input.toView(), this.jollyOut, this.faith);
        }
        else {//(this.action == LeaderCardAction.EXTRADEPOT) {
            leaderCard = new it.polimi.ingsw.View.ReducedModel.LeaderCard(this.cardId, this.victoryPoints, this.resourcesRequired.toView(), this.cardsRequired.toView(), this.resource);
        }
        //else leaderCard = new it.polimi.ingsw.View.ReducedModel.LeaderCard(this.cardId, this.victoryPoints, this.resourcesRequired.toView(), this.cardsRequired.toView(), this.marble);
        return leaderCard;
    }
}
