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
            blueCardLv1 += slots[i].sumType(Color.BLUE, Level.ONE);
            blueCardLv2 += slots[i].sumType(Color.BLUE, Level.TWO);
            blueCardLv3 += slots[i].sumType(Color.BLUE, Level.THREE);
            purpleCardLv1 += slots[i].sumType(Color.PURPLE, Level.ONE);
            purpleCardLv2 += slots[i].sumType(Color.PURPLE, Level.TWO);
            purpleCardLv3 += slots[i].sumType(Color.PURPLE, Level.THREE);
            yellowCardLv1 += slots[i].sumType(Color.YELLOW, Level.ONE);
            yellowCardLv2 += slots[i].sumType(Color.YELLOW, Level.TWO);
            yellowCardLv3 += slots[i].sumType(Color.YELLOW, Level.THREE);
            greenCardLv1 += slots[i].sumType(Color.GREEN, Level.ONE);
            greenCardLv2 += slots[i].sumType(Color.GREEN, Level.TWO);
            greenCardLv3 += slots[i].sumType(Color.GREEN, Level.THREE);
        }

        sum = new LeaderRequirements(blueCardLv1, purpleCardLv1, yellowCardLv1, greenCardLv1, blueCardLv2, purpleCardLv2, yellowCardLv2, greenCardLv2, blueCardLv3, purpleCardLv3, yellowCardLv3, greenCardLv3);
        return sum;
    }

    public void addCard(int i, DevelopmentCard card){
        if(i == 0 || i == 1 || i == 2) slots[i].addCard(card);
        else System.err.println("ERROR");
    }

}
