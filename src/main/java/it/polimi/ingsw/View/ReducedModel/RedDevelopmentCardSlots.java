package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.GameModel.CardSlot;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RedDevelopmentCardSlots Class defines data and methods of the Development Card Slots of the user's reduced game.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedCardSlot[] "slots": Card Slots contained inside the DevelopmentCardSlots</li>
 * </ul>
 */
public class RedDevelopmentCardSlots implements Serializable {
    protected final RedCardSlot[] slots;

    protected RedDevelopmentCardSlots() {
        this.slots = new CardSlot[3];
    }

    /**
     * Getter
     * @return an array of CardSlot
     */
    public RedCardSlot[] getSlots() {
        return slots;
    }

    /**
     * This method is use to see if the player can or cannot add a card in the slot
     * @param card is the Development card that the player wants to add
     * @return true if there is a slot available, false if not
     */
    public boolean canAdd(RedDevelopmentCard card){
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
    public boolean canAddInThisSlot(int i, RedDevelopmentCard card){
        return slots[i].canAdd(card);
    }

    public ArrayList<String> toCli() {
        ArrayList<String> devSlots = this.slots[0].toCli();
        devSlots.add(0, "╔════════════════════════════════════════════════════════════════════════════════════════════╗");
        ArrayList<String> cardSlot2 = this.slots[1].toCli();
        ArrayList<String> cardSlot3 = this.slots[2].toCli();

        for(int i = 1; i < devSlots.size(); i++) {
            devSlots.set(i, ("║" + devSlots.get(i) + " " + cardSlot2.get(i-1) + " " + cardSlot3.get(i-1) + "║"));
        }
        devSlots.add(devSlots.size(), "╚════════════════════════════════════════════════════════════════════════════════════════════╝");
        return devSlots;
    }
}