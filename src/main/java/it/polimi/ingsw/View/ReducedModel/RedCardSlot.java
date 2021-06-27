package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.Enums.Level;

import java.io.Serializable;
import java.util.ArrayList;

public class RedCardSlot implements Serializable {
    protected int levelOccupied;
    protected final RedDevelopmentCard[] cards;

    protected RedCardSlot () {
        this.cards = new DevelopmentCard[3];
    }

    public RedDevelopmentCard[] getDevelopmentCards() {
        return cards;
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

    /**
     * This method returns true if he can add the card i the slot
     * @param card which is  the card that the player wants to add
     * @return true if he can, false if he cannot
     */
    public boolean canAdd(RedDevelopmentCard card){
        if(levelOccupied == 0 && card.getLevel() == Level.ONE) return true;
        if(levelOccupied == 1 && card.getLevel() == Level.TWO) return true;
        if(levelOccupied == 2 && card.getLevel() == Level.THREE) return true;
        else return false;
    }

    /**
     * This method is use to get the first card of the slot.
     * @return the card on the top of the slot
     */
    public RedDevelopmentCard getFirstCard(){
        if(levelOccupied == 0) return null;
        else return cards[levelOccupied - 1];
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

        RedDevelopmentCard card;
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
