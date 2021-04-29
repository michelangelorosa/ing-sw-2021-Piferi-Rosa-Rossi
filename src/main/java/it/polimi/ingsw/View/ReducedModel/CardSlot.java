package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

import java.io.Serializable;
import java.util.ArrayList;

public class CardSlot implements Serializable {
    private static final long serialVersionUID = 0x1;

    private int levelOccupied;
    private final DevelopmentCard[] cards;

    /**
     * constructor for CardSlot
     */
    public CardSlot(){
        this.levelOccupied = 0;
        this.cards = new DevelopmentCard[3];
    }



    /**
     * Getter for levelOccupied
     */
    public int getLevelOccupied(){
        return levelOccupied;
    }

    /**
     * This method returns true if a slot has 3 cards.
     * @return true/false
     */
    public boolean isFull(){
        return levelOccupied == 3;
    }

    /**
     * This method returns true if a slot is empty.
     * @return true/false
     */
    public boolean isEmpty() {
        return levelOccupied == 0;
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
     * This method returns true if he can add the card i the slot
     * @param card which is  the card that the player wants to add
     * @return true if he can, false if he cannot
     */
    public boolean canAdd(DevelopmentCard card){
        if(levelOccupied == 0 && card.getLevel() == Level.ONE) return true;
        if(levelOccupied == 1 && card.getLevel() == Level.TWO) return true;
        if(levelOccupied == 2 && card.getLevel() == Level.THREE) return true;
        else return false;
    }

    public void addCard(DevelopmentCard card){
        if(isFull()) System.out.println("Slot is full, select another slot!");
        else if(!canAdd(card)) System.out.println("Can't add a level " + card.getLevel() + " card in this slot!");
        else cards[levelOccupied++] = card;
    }

    /**
     * This method is use to get the first card of the slot.
     * @return the card on the top of the slot
     */
    public DevelopmentCard getFirstCard(){
        if(levelOccupied == 0) return null;
        else return cards[levelOccupied - 1];
    }

    /**
     * Counts the number of card of a certain color and level.
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

    public ArrayList<String> toCli() {
        ArrayList<String> cardSlot = new ArrayList<>();
        ArrayList<String> cardCli;
        for(int j = 0; j < 4; j++)
            cardSlot.add("                              ");

        if(this.isEmpty()) {
            for(int i = 0; i < 15; i++)
                cardSlot.add("                              ");
            return cardSlot;
        }

        DevelopmentCard card;
        for(int i = 0; i < this.levelOccupied; i++) {
            card = this.cards[i];
            if(i == 0)
                cardSlot.addAll(card.toCli());

            else if(i == 1) {
                cardCli = card.toCli();
                for(int k = 2; k < cardSlot.size() - 2; k++)
                    cardSlot.set(k, cardCli.get(k-2));
            }
            else if(i == 2) {
                cardCli = card.toCli();
                for(int k = 0; k < cardSlot.size() - 4; k++)
                    cardSlot.set(k, cardCli.get(k));
            }
        }

        return cardSlot;
    }
}