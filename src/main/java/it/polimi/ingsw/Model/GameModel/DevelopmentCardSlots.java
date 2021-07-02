package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardSlots;

import java.util.ArrayList;

/**
 * DevelopmentCardSlots Class defines methods used on the Model to modify the player's Development Card Slots.
 * @author francescopiferi99
 */
public class DevelopmentCardSlots extends RedDevelopmentCardSlots {

    /**
     * DevelopmentCardSlot constructor.
     */
    public DevelopmentCardSlots(){
        super();
        this.slots[0] = new CardSlot();
        this.slots[1] = new CardSlot();
        this.slots[2] = new CardSlot();
    }

    /**
     * Getter for "slots" attribute.
     */
    public CardSlot[] getSlots() {
        return (CardSlot[]) slots;
    }


    /**
     * Checks if the player bought his 7th Development Card.
     */
    public boolean victory(){
        return (slots[0].getLevelOccupied() + slots[1].getLevelOccupied() + slots[2].getLevelOccupied()) == 7;
    }

    /**
     * Counts the final points.
     * @return points which is an int that contains the sum of the points of the player.
     */
    public int totalPoints(){
        int points = 0;
        points += ((CardSlot)slots[0]).finalPoints();
        points += ((CardSlot)slots[1]).finalPoints();
        points += ((CardSlot)slots[2]).finalPoints();

        return points;
    }

    /**
     * Counts the number of cards, divided by color and level, on the player's board.
     * @return sum
     */
    public LeaderRequirements sumResources(){
        LeaderRequirements sum;
        int blueCardLv1 = 0;
        int purpleCardLv1 = 0;
        int yellowCardLv1 = 0;
        int greenCardLv1 = 0;
        int blueCardLv2 = 0;
        int purpleCardLv2 = 0;
        int yellowCardLv2 = 0;
        int greenCardLv2 = 0;
        int blueCardLv3 = 0;
        int purpleCardLv3 = 0;
        int yellowCardLv3 = 0;
        int greenCardLv3 = 0;
        for(int i = 0; i < 3; i++){
            blueCardLv1 += ((CardSlot)slots[i]).sumType(Color.BLUE, Level.ONE);
            blueCardLv2 += ((CardSlot)slots[i]).sumType(Color.BLUE, Level.TWO);
            blueCardLv3 += ((CardSlot)slots[i]).sumType(Color.BLUE, Level.THREE);
            purpleCardLv1 += ((CardSlot)slots[i]).sumType(Color.PURPLE, Level.ONE);
            purpleCardLv2 += ((CardSlot)slots[i]).sumType(Color.PURPLE, Level.TWO);
            purpleCardLv3 += ((CardSlot)slots[i]).sumType(Color.PURPLE, Level.THREE);
            yellowCardLv1 += ((CardSlot)slots[i]).sumType(Color.YELLOW, Level.ONE);
            yellowCardLv2 += ((CardSlot)slots[i]).sumType(Color.YELLOW, Level.TWO);
            yellowCardLv3 += ((CardSlot)slots[i]).sumType(Color.YELLOW, Level.THREE);
            greenCardLv1 += ((CardSlot)slots[i]).sumType(Color.GREEN, Level.ONE);
            greenCardLv2 += ((CardSlot)slots[i]).sumType(Color.GREEN, Level.TWO);
            greenCardLv3 += ((CardSlot)slots[i]).sumType(Color.GREEN, Level.THREE);
        }

        sum = new LeaderRequirements(blueCardLv1, purpleCardLv1, yellowCardLv1, greenCardLv1, blueCardLv2, purpleCardLv2, yellowCardLv2, greenCardLv2, blueCardLv3, purpleCardLv3, yellowCardLv3, greenCardLv3);
        return sum;
    }

    /**
     * Counts the number of cards, divided only by color, on the player's board
     * @return sum
     */
    public LeaderRequirements sumColors(){
        LeaderRequirements sum;
        int blueCards = 0;
        int purpleCards = 0;
        int yellowCards = 0;
        int greenCards = 0;

        for(int i = 0; i < 3; i++){
            blueCards += ((CardSlot)slots[i]).sumColors(Color.BLUE);
            purpleCards += ((CardSlot)slots[i]).sumColors(Color.PURPLE);
            yellowCards += ((CardSlot)slots[i]).sumColors(Color.YELLOW);
            greenCards += ((CardSlot)slots[i]).sumColors(Color.GREEN);
        }
        sum = new LeaderRequirements(blueCards, purpleCards,yellowCards,greenCards);
        return sum;
    }

    /**
     * Adds a card in a particular slot.
     * @param i is the number of the slot I want to use.
     * @param card Is the card I want to add.
     */
    public void addCard(int i, DevelopmentCard card) throws IllegalArgumentException{
        if(i == 0 || i == 1 || i == 2) ((CardSlot)slots[i]).addCard(card);
        else throw new IllegalArgumentException("ERROR, the slot does not exists");
    }

    public void addCardInFirstFreeSpace(DevelopmentCard card) throws ModelException {
        if(canAddInThisSlot(0, card))
            addCard(0, card);
        else if(canAddInThisSlot(1, card))
            addCard(1, card);
        else if(canAddInThisSlot(2, card))
            addCard(2, card);
        else
            throw new ModelException("DISCONNECTION: Cannot add card to any depot");
    }

    /**
     * Counts the total number of Development Cards on the Board
     * @return the number of cards
     */
    public int countAllCards() {
        return this.getSlots()[0].getLevelOccupied() + this.getSlots()[1].getLevelOccupied() + this.getSlots()[2].getLevelOccupied();
    }

    /**
     * Converts this to View.
     */
    public RedDevelopmentCardSlots toView(){

        return this;
    }
}