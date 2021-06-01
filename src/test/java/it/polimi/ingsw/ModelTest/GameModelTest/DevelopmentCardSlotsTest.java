package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardSlots;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardSlots;
import org.junit.Test;

public class DevelopmentCardSlotsTest {

    /**
     * Here I defined the cards and the slot I'll use in my tests
     */

    ResourceStack cost = new ResourceStack(0, 1, 2, 3);
    ResourceStack input = new ResourceStack(2, 6, 13, 16);
    ResourceStack output = new ResourceStack(31, 0, 0, 0);
    DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, 0);

    ResourceStack cost1 = new ResourceStack(10, 20, 30, 40);
    ResourceStack input1 = new ResourceStack(20, 60, 130, 160);
    ResourceStack output1 = new ResourceStack(1, 1, 1, 1);
    DevelopmentCard card1 = new DevelopmentCard(Color.PURPLE, Level.ONE, 2, 10, cost1, input1, output1, 1);

    ResourceStack cost2 = new ResourceStack(4, 4, 4, 4);
    ResourceStack input2 = new ResourceStack(4, 4, 4, 4);
    ResourceStack output2 = new ResourceStack(4, 4, 4, 4);
    DevelopmentCard card2 = new DevelopmentCard(Color.YELLOW, Level.TWO, 3, 11, cost2, input2, output2, 2);

    ResourceStack cost3 = new ResourceStack(5, 5, 5, 5);
    ResourceStack input3 = new ResourceStack(5, 5, 5, 5);
    ResourceStack output3 = new ResourceStack(5, 5, 5, 5);
    DevelopmentCard card3 = new DevelopmentCard(Color.GREEN, Level.THREE, 4, 12, cost3, input3, output3, 3);

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

    /**
     * Test to check if the method sumResources works properly by adding cards and look at the result.
     */
    @Test
    public void sumResourcesTest(){
        LeaderRequirements total;

        slots.addCard(1,card1);
        slots.addCard(2,card1);
        slots.addCard(1,card2);
        slots.addCard(1,card3);
        slots.addCard(0,card);
        slots.addCard(2,card2);

        total = slots.sumResources();

        assertEquals(1, total.getBlueCardLv1());
        assertEquals(0, total.getBlueCardLv2());
        assertEquals(0, total.getBlueCardLv3());

        assertEquals(2, total.getPurpleCardLv1());
        assertEquals(0, total.getPurpleCardLv2());
        assertEquals(0, total.getPurpleCardLv3());

        assertEquals(0, total.getYellowCardLv1());
        assertEquals(2, total.getYellowCardLv2());
        assertEquals(0, total.getYellowCardLv3());

        assertEquals(0, total.getGreenCardLv1());
        assertEquals(0, total.getGreenCardLv2());
        assertEquals(1, total.getGreenCardLv3());
    }


    /**
     * Test to watch is the method canAddInThisSlot works properly by looking at the first card in the slot.
     */
    @Test
    public void canAddInThisSlotTest(){
        if(slots.canAddInThisSlot(1, card1))slots.addCard(1,card1);
        if(slots.canAddInThisSlot(2, card1)) slots.addCard(2,card1);
        if(slots.canAddInThisSlot(1, card2)) slots.addCard(1,card2);
        if(slots.canAddInThisSlot(1, card3))slots.addCard(1,card3);
        if(slots.canAddInThisSlot(0, card3)) slots.addCard(0, card3);

        assertEquals(card3, slots.getSlots()[1].getFirstCard());
        assertEquals(card1, slots.getSlots()[2].getFirstCard());
        assertNull(slots.getSlots()[0].getFirstCard());
    }

    /**
     * Test to watch if the method sumColor works properly by adding 6 cards in the DevelopmentCardSlots and looking
     * at he result.
     */
    @Test
    public void sumColorTest(){
        LeaderRequirements total;

        slots.addCard(1,card1);
        slots.addCard(2,card1);
        slots.addCard(1,card2);
        slots.addCard(1,card3);
        slots.addCard(0,card);
        slots.addCard(2,card2);

        total = slots.sumColors();

        assertEquals(1, total.getNeedBlueCard());
        assertEquals(2, total.getNeedPurpleCard());
        assertEquals(2, total.getNeedYellowCard());
        assertEquals(1, total.getNeedGreenCard());
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        RedDevelopmentCardSlots slotsView;

        slots.addCard(1,card1);
        slots.addCard(2,card1);
        slots.addCard(1,card2);
        slots.addCard(1,card3);
        slots.addCard(0,card);
        slots.addCard(2,card2);

        slotsView = slots.toView();

        assertEquals(slots.getSlots()[0].getFirstCard().getCardId(), slotsView.getSlots()[0].getFirstCard().getCardId());
        assertEquals(slots.getSlots()[1].getFirstCard().getCardId(), slotsView.getSlots()[1].getFirstCard().getCardId());
        assertEquals(slots.getSlots()[2].getFirstCard().getCardId(), slotsView.getSlots()[2].getFirstCard().getCardId());

        assertEquals(slots.getSlots()[0].getFirstCard().getInput().getResource(ResourceType.SHIELDS), slotsView.getSlots()[0].getFirstCard().getInput().getResource(ResourceType.SHIELDS));
        assertEquals(slots.getSlots()[1].getFirstCard().getInput().getResource(ResourceType.COINS), slotsView.getSlots()[1].getFirstCard().getInput().getResource(ResourceType.COINS));
        assertEquals(slots.getSlots()[2].getFirstCard().getInput().getResource(ResourceType.SERVANTS), slotsView.getSlots()[2].getFirstCard().getInput().getResource(ResourceType.SERVANTS));
        assertEquals(slots.getSlots()[0].getFirstCard().getInput().getResource(ResourceType.STONES), slotsView.getSlots()[0].getFirstCard().getInput().getResource(ResourceType.STONES));
    }
}
