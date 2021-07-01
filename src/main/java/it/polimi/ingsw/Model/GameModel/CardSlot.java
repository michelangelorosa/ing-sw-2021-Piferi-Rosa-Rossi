package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.RedCardSlot;

/**
 * CardSlot Class defines methods of a RedCardSlot object to be used only on the Model.
 * @author francescopiferi99
 */
public class CardSlot extends RedCardSlot {

    /**
     * Constructor for CardSlot.
     */
    public CardSlot(){
        super();
        this.levelOccupied = 0;
    }

    /**
     * Setter for CardSlot.
     */
    public void setLevelOccupied(int levelOccupied){this.levelOccupied = levelOccupied;}

    /**
     * Getter for levelOccupied.
     */
    public int getLevelOccupied(){
        return levelOccupied;
    }

    /**
     * Check if the slot is full or if a card cannot be added to this slot. If the card can be added, it is stored in the cards array.
     * @param card Card to add.
     */
    public void addCard(DevelopmentCard card){
        if(isFull()) System.out.println("Slot is full, select another slot!");
        else if(!canAdd(card)) System.out.println("Can't add a level " + card.getLevel() + " card in this slot!");
        else cards[levelOccupied++] = card;
    }

    /**
     * Calculates the points relative to the Card Slots.
     * @return an int which is the total points relative to the cards in the slots.
     */
    public int finalPoints(){
        int points = 0;
        for(int i = 0; i < 3; i++){
            if(cards[i] != null) points += cards[i].getVictoryPoints();
            else return points;
        }
        return points;
    }

    /**
     * Gets the first card of the slot.
     * @return the card on the top of the slot.
     */
    public DevelopmentCard getFirstCard(){
        if(levelOccupied == 0) return null;
        else return (DevelopmentCard) cards[levelOccupied - 1];
    }

    /**
     * Counts the number of cards of a certain color and level.
     * @param color the color of the card to count.
     * @param level the level of the card to count.
     * @return the result of the counter.
     */
    public int sumType(Color color, Level level){
        int count = 0;
        for(int i = 0; i < 3; i++) {
            if(cards[i] != null) {
                if (cards[i].getColor() == color && cards[i].getLevel() == level) count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of cards of a certain color.
     * @param color Color of the card to count
     * @return the result of the counter
     */
    public int sumColors(Color color){
        int count = 0;
        for(int i = 0; i < 3; i++){
            if(cards[i] != null){
                if(cards[i].getColor() == color) count ++;
            }
        }
        return count;
    }

    /**
     * Getter for cards attribute.
     */
    public DevelopmentCard[] getCards() {
        return (DevelopmentCard[]) cards;
    }

    /**Converts model classes to view classes*/
    public RedCardSlot toView() {
        return  this;
    }
}
