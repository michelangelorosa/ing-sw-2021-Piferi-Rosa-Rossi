package it.polimi.ingsw.Model;

public class CardSlot {
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
     *In this method firstly I check if the slot is full or if I can't add the card in this slot. If I can add the card
     * I memorize it in the array cards.
     * @param card
     */

    public void addCard(DevelopmentCard card){
        if(isFull()) System.out.println("Slot is full, select another slot!");
        else if(!canAdd(card)) System.out.println("Can't add a level " + card.getLevel() + " card in this slot!");
        else cards[levelOccupied++] = card;
    }

    /**
     * This method turns true if a slot has 3 cards
     * @return true/false
     */
    public boolean isFull(){
        return levelOccupied == 3;
    }


    public int finalPoints(){
        int points = 0;
        for(int i = 0; i < 3; i++){
            if(cards[i] != null) points += cards[i].getVictoryPoints();
            else return points;
        }
        return points;
    }

    public boolean canAdd(DevelopmentCard card){
        if(levelOccupied == 0 && card.getLevel() == Level.ONE) return true;
        if(levelOccupied == 1 && card.getLevel() == Level.TWO) return true;
        if(levelOccupied == 2 && card.getLevel() == Level.THREE) return true;
        else return false;
    }

    public DevelopmentCard getFirstCard(){
        if(levelOccupied == 0) return null;
        else return cards[levelOccupied - 1];
    }

    public int sumType(Color color, Level level){
        int count = 0;
        for(int i = 0; i < 3; i++) {
            if(cards[i] != null) {
                if (cards[i].getColor() == color && cards[i].getLevel() == level) count++;
            }
        }
        return count;
    }
}
