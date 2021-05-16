package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;

import java.io.Serializable;
import java.util.ArrayList;

public class DevelopmentCardSlots implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final CardSlot[] slots;
    private int cardsInSlot;
    private Level level;


    /**
     * DevelopmentCardSlot constructor.
     */
    public DevelopmentCardSlots(){
        this.slots = new CardSlot[3];
        this.slots[0] = new CardSlot();
        this.slots[1] = new CardSlot();
        this.slots[2] = new CardSlot();
    }

    /**
     * Getter
     * @return an array of CardSlot
     */
    public CardSlot[] getSlots() {
        return slots;
    }

    /**
     * This method is used to sum all the production input by receiving an ArrayList of all the Cards that the player
     * wants to use and create a new ResourceStack that contains the sum of the resources.
     * @param inputs is the ArrayList of DevelopmentCards
     * @return productionInput which is a ResourceStack that contains the sum of the resources needed
     */
    private ResourceStack getProductionInput(ArrayList<DevelopmentCard> inputs){
        ResourceStack productionInput = new ResourceStack(0, 0, 0,0);
        for(DevelopmentCard card : inputs){
            productionInput.addToAllTypes(card.getInput());
        }
        return productionInput;
    }

    /**
     * This method is used to sum all the production outputs by receiving an ArrayList of all the Cards that the player
     * wants to use and create a new ResourceStack that contains the sum of the resources.
     * @param outputs is the ArrayList of DevelopmentCards
     * @return productionOutput which is a ResourceStack that contains the sum of the resources needed
     */
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

    /**
     * This method is use to count the final point
     * @return points which is an int that contains the sum of the points of the player.
     */
    public int totalPoints(){
        int points = 0;
        points += slots[0].finalPoints();
        points += slots[1].finalPoints();
        points += slots[2].finalPoints();

        return points;
    }


    /**
     * This method is use to see if the player can or cannot add a card in the slot
     * @param card is the Development card that the player wants to add
     * @return true if there is a slot available, false if not
     */
    public boolean canAdd(DevelopmentCard card){
        for(int i = 0; i < 3; i++){
            if(slots[i].canAdd(card)) return true;
        }
        return false;
    }

    /**
     * This method is used to count the number of cards, divided by color and level, on the player's board
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

    /**
     * This method is use to see if the player can or cannot add a card in a particular slot.
     * @param i is the number of the slot that the player wants.
     * @param card is the Development card that the player wants to add
     * @return true if the player can add the card in that slot, false if not
     */
    public boolean canAddInThisSlot(int i, DevelopmentCard card){
        return slots[i].canAdd(card);
    }

    /**
     * This method is used to count the number of cards, divided only by color, on the player's board
     * @return sum
     */
    public LeaderRequirements sumColors(){
        LeaderRequirements sum;
        int blueCards = 0;
        int purpleCards = 0;
        int yellowCards = 0;
        int greenCards = 0;

        for(int i = 0; i < 3; i++){
            blueCards += slots[i].sumColors(Color.BLUE);
            purpleCards += slots[i].sumColors(Color.PURPLE);
            yellowCards += slots[i].sumColors(Color.YELLOW);
            greenCards += slots[i].sumColors(Color.GREEN);
        }
        sum = new LeaderRequirements(blueCards, purpleCards,yellowCards,greenCards);
        return sum;
    }

    /**
     * This method use to add a card in a particular slot.
     * @param i is the number of the slot I want to use.
     * @param card Is the card I want to add.
     */
    public void addCard(int i, DevelopmentCard card) throws IllegalArgumentException{
        if(i == 0 || i == 1 || i == 2) slots[i].addCard(card);
        else throw new IllegalArgumentException("ERROR, the slot does not exists");
    }

    /**
     * This method is used to count the total number of Development Cards on the Board
     * @return the number of cards
     */
    public int countAllCards() {
        return this.getSlots()[0].getLevelOccupied() + this.getSlots()[1].getLevelOccupied() + this.getSlots()[2].getLevelOccupied();
    }

    public it.polimi.ingsw.View.ReducedModel.DevelopmentCardSlots toView(){

        it.polimi.ingsw.View.ReducedModel.DevelopmentCardSlots slotsView = new it.polimi.ingsw.View.ReducedModel.DevelopmentCardSlots();

        for(int i = 0; i < 3; i++) {
            slotsView.getSlots()[i] = new it.polimi.ingsw.View.ReducedModel.CardSlot();
            slotsView.getSlots()[i] = this.slots[i].toView();
        }

        return slotsView;
    }
}