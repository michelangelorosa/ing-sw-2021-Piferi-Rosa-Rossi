package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.RedCardSlot;

public class CardSlot extends RedCardSlot {
    private static final long serialVersionUID = 0x1;

    /**
     * constructor for CardSlot
     */
    public CardSlot(){
        super();
        this.levelOccupied = 0;
    }

    /**
     * Setter for levelOccupied
     */
    public void setLevelOccupied(int levelOccupied) {
        this.levelOccupied = levelOccupied;
    }

    /**
     * Getter for levelOccupied
     */
    public int getLevelOccupied(){
        return levelOccupied;
    }

    /**
     *In this method firstly I check if the slot is full or if I can't add the card in this slot. If I can add the card
     * I memorize it in the array cards.
     * @param card is the card to add
     */

    public void addCard(DevelopmentCard card){
        if(isFull()) System.out.println("Slot is full, select another slot!");
        else if(!canAdd(card)) System.out.println("Can't add a level " + card.getLevel() + " card in this slot!");
        else cards[levelOccupied++] = card;
    }

    /** This method is use to calculate the points relative at the Card Slots
     * @return an int which is the points relative at the cards on the slots
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
     * This method is use to get the first card of the slot.
     * @return the card on the top of the slot
     */
    public DevelopmentCard getFirstCard(){
        if(levelOccupied == 0) return null;
        else return (DevelopmentCard) cards[levelOccupied - 1];
    }

    /**
     * Counts the number of cards of a certain color and level.
     * @param color the color of the card I want to count
     * @param level the level of the card I want to count
     * @return the result of the counter
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
     * Counts the number of card of a certain color.
     * @param color the color of the card I want to count
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

    public DevelopmentCard[] getCards() {
        return (DevelopmentCard[]) cards;
    }

    /**Method for converting model classes to view classes*/
    public RedCardSlot toView() {
        return (RedCardSlot) this;
    }
}
