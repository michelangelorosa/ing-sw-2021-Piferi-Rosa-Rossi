package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

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
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack input, int jollyOut, int faith) {
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCardId() {
        return cardId;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public LeaderCardAction getAction() {
        return action;
    }

    public boolean isActive() {
        return active;
    }

    public ResourceStack getResourcesRequired() {
        return resourcesRequired;
    }

    public LeaderRequirements getCardsRequired() {
        return cardsRequired;
    }

    public ResourceStack getDiscount() {
        return discount;
    }

    public Marble getMarble() {
        return marble;
    }

    public ResourceStack getInput() {
        return input;
    }

    public int getFaith() {
        return faith;
    }

    public int getJollyOut() {
        return jollyOut;
    }

    public ResourceType getResource() {
        return resource;
    }
}
