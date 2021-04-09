package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

public class DevelopmentCardSlotsTest {

    /**
     * Here I defined the cards and the slot I'll use in my tests
     */

    ResourceStack cost = new ResourceStack(0, 1, 2, 3);
    ResourceStack input = new ResourceStack(2, 6, 13, 16);
    ResourceStack output = new ResourceStack(31, 0, 0, 0);
    DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, 0);
    DevelopmentCard cardTest = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, 0);

    ResourceStack cost1 = new ResourceStack(10, 20, 30, 40);
    ResourceStack input1 = new ResourceStack(20, 60, 130, 160);
    ResourceStack output1 = new ResourceStack(1, 1, 1, 1);
    DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.ONE, 2, 10, cost1, input1, output1, 1);
    DevelopmentCard card1Test = new DevelopmentCard(Color.BLUE, Level.ONE, 2, 10, cost1, input1, output1, 1);

    ResourceStack cost2 = new ResourceStack(4, 4, 4, 4);
    ResourceStack input2 = new ResourceStack(4, 4, 4, 4);
    ResourceStack output2 = new ResourceStack(4, 4, 4, 4);
    DevelopmentCard card2 = new DevelopmentCard(Color.BLUE, Level.TWO, 3, 11, cost2, input2, output2, 2);
    DevelopmentCard card2Test = new DevelopmentCard(Color.BLUE, Level.ONE, 3, 11, cost2, input2, output2, 2);

    ResourceStack cost3 = new ResourceStack(5, 5, 5, 5);
    ResourceStack input3 = new ResourceStack(5, 5, 5, 5);
    ResourceStack output3 = new ResourceStack(5, 5, 5, 5);
    DevelopmentCard card3 = new DevelopmentCard(Color.BLUE, Level.THREE, 4, 12, cost3, input3, output3, 3);
    DevelopmentCard card3Test = new DevelopmentCard(Color.BLUE, Level.ONE, 4, 12, cost3, input3, output3, 3);

    DevelopmentCardSlots slots = new DevelopmentCardSlots();

    /**
     * Test to check if the method victory works properly. It counts the cards in the slot and becomes TRUE when the
     * player gains seven cards.
     */

    @Test
    public void victoryTest(){
        slots.addCard(1,card1);
        slots.addCard(2,card1);
        assertFalse(slots.victory());
        slots.addCard(1,card2);
        slots.addCard(1,card3);
        assertFalse(slots.victory());
        slots.addCard(0,card);
        slots.addCard(2,card2);
        assertFalse(slots.victory());
        slots.addCard(2,card3);
        assertTrue(slots.victory());
    }

    /**
     * Test to check if the method totalPoints works properly. It sums all the victory points of all the cards in the
     * slots.
     */

    @Test
    public void totalPointsTest(){
        slots.addCard(1,card1);
        slots.addCard(2,card1);
        slots.addCard(1,card2);
        slots.addCard(1,card3);
        slots.addCard(0,card);
        slots.addCard(2,card2);
        slots.addCard(2,card3);

        assertEquals(166, slots.totalPoints());
    }

    /**
     * Test to check if the method canAdd works properly. In this exemple the only card I can add is card2 because it is
     * the only level 2 card I have. I can't add a level 1 card because I don't have free slots and I can't add a level 3
     * card because I don't have any level 2 card in any slot.
     */

    @Test
    public void canAddTest(){
        slots.addCard(1,card1);
        slots.addCard(2,card1);
        slots.addCard(0,card1);
        assertFalse(slots.canAdd(card1));
        assertTrue(slots.canAdd(card2));
        assertFalse(slots.canAdd(card3));
    }

    /**
     * Test to check if the method addCard works properly by looking at the first card of the slot.
     */

    @Test
    public void addCardTest(){
        slots.addCard(0,card1);
        slots.addCard(1,card1);
        slots.addCard(2,card1);

        assertEquals(card1, slots.getSlots()[0].getFirstCard());
        assertEquals(card1, slots.getSlots()[1].getFirstCard());
        assertEquals(card1, slots.getSlots()[2].getFirstCard());


    }
}