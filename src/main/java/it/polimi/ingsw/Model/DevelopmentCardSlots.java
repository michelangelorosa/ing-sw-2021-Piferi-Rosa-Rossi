package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class DevelopmentCardSlots {

    private final CardSlot[] slots;
    private int cardsInSlot;
    private Level level;

    public DevelopmentCardSlots(){
        this.slots = new CardSlot[3];
        this.slots[0] = new CardSlot();
        this.slots[1] = new CardSlot();
        this.slots[2] = new CardSlot();
    }

    public CardSlot[] getSlots() {
        return slots;
    }

    private ResourceStack getProductionInput(ArrayList<DevelopmentCard> inputs){
        ResourceStack productionInput = new ResourceStack(0, 0, 0,0);
        for(DevelopmentCard card : inputs){
            productionInput.addToAllTypes(card.getInput());
        }
        return productionInput;
    }

    private ResourceStack getProductionOutput(ArrayList<DevelopmentCard> outputs){
        ResourceStack productionOutput = new ResourceStack(0, 0, 0,0);
        for(DevelopmentCard card : outputs){
            productionOutput.addToAllTypes(card.getInput());
        }
        return productionOutput;
    }

    /**
     * If the player buys the seventh card he wins
     */
    public boolean victory(){
        return (slots[0].getLevelOccupied() + slots[1].getLevelOccupied() + slots[2].getLevelOccupied()) == 7;
    }

    public int totalPoints(){
        int points = 0;
        points += slots[0].finalPoints();
        points += slots[1].finalPoints();
        points += slots[2].finalPoints();

        return points;
    }

    public boolean canAdd(DevelopmentCard card){
        for(int i = 0; i < 3; i++){
            if(slots[i].canAdd(card)) return true;
        }
        return false;
    }

    public void addCard(int i, DevelopmentCard card){
        if(i == 0 || i == 1 || i == 2) slots[i].addCard(card);
        else System.err.println("ERROR");
    }

}
