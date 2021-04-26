package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

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
     * This method is use to see if the player can or cannot add a card in a particular slot.
     * @param i is the number of the slot that the player wants.
     * @param card is the Development card that the player wants to add
     * @return true if the player can add the card in that slot, false if not
     */
    public boolean canAddInThisSlot(int i, DevelopmentCard card){
        if(slots[i].canAdd(card)) return true;
        else return false;
    }
}