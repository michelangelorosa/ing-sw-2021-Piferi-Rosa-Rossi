package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import org.junit.Test;
public class CardSlotTest {

    CardSlot test = new CardSlot();
    ResourceStack cost = new ResourceStack(0, 1, 2, 3);
    ResourceStack input = new ResourceStack(2, 6, 13, 16);
    ResourceStack output = new ResourceStack(31, 0, 0, 0);
    DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, 0);
    DevelopmentCard cardTest = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 100, cost, input, output, 0);
    DevelopmentCard card2Test = new DevelopmentCard(Color.BLUE, Level.ONE, 2, 100, cost, input, output, 0);
    DevelopmentCard card3Test = new DevelopmentCard(Color.BLUE, Level.THREE, 3, 100, cost, input, output, 0);
    DevelopmentCard card4Test = new DevelopmentCard(Color.PURPLE, Level.THREE, 4, 100, cost, input, output, 0);

    /**
     * Test to check if the constructor works properly
     */

    @Test
    public void constructorTest(){
        assertEquals(0, test.getLevelOccupied());
    }

    /**
     * Test to check if the method addCard works properly by adding five cards. The method recognize what card can and
     * can not add to the slot.
     */

    @Test
    public void addCardTest(){
        test.addCard(card);
        assertEquals(1, test.getLevelOccupied());

        test.addCard(cardTest);
        assertEquals(2, test.getLevelOccupied());

        test.addCard(card2Test);
        assertEquals(2, test.getLevelOccupied());

        test.addCard(card3Test);
        assertEquals(3, test.getLevelOccupied());

        test.addCard(card4Test);
        assertEquals(3, test.getLevelOccupied());
    }

    /**
     * Test to watch if the methods isFull and isEmpty work properly by trying to add a card in a full slot.
     */

    @Test
    public void isFullAndIsEmptyTest(){
        test.addCard(cardTest);
        assertTrue(test.isEmpty());
        assertFalse(test.isFull());

        test.addCard(card);
        assertEquals(1, test.getLevelOccupied());

        test.addCard(cardTest);
        assertEquals(2, test.getLevelOccupied());

        test.addCard(card3Test);
        assertEquals(3, test.getLevelOccupied());

        test.addCard(card4Test);
        assertEquals(3, test.getLevelOccupied());

        assertTrue(test.isFull());
        assertFalse(test.isEmpty());
    }


    /**
     * Test to check if the method finalPoints works properly by adding 3 cards and watching the final points.
     */

    @Test
    public void finalPointsTest(){
        test.addCard(card);
        assertEquals(1, test.getLevelOccupied());

        test.addCard(cardTest);
        assertEquals(2, test.getLevelOccupied());

        test.addCard(card3Test);
        assertEquals(3, test.getLevelOccupied());

        int points = test.finalPoints();

        assertEquals(300, points);
    }

    /**
     * Test to watch if the method canAdd works properly by a series of tests.
     */

    @Test
    public void canAddTest(){
        test.canAdd(cardTest);
        assertFalse(test.canAdd(cardTest));

        test.canAdd(card4Test);
        assertFalse(test.canAdd(card4Test));

        test.canAdd(card);
        assertTrue(test.canAdd(card));

        test.canAdd(card3Test);
        assertFalse(test.canAdd(card3Test));

        test.canAdd(card2Test);
        assertTrue(test.canAdd(card2Test));
    }

    /**
     * Test to watch if the method "getFirstCard" works properly.
     */

    @Test
    public void getFirstCardTest(){
        assertNull(test.getFirstCard());

        test.addCard(card);
        assertEquals(card, test.getFirstCard());

        test.addCard(cardTest);
        assertEquals(cardTest, test.getFirstCard());

        test.addCard(card3Test);
        assertEquals(card3Test, test.getFirstCard());

    }

    /**
     * Test to watch if the method "sumType" works properly by adding three cards to the slot.
     */

    @Test
    public void sumTypeTest(){
        test.addCard(card);
        test.addCard(cardTest);
        test.addCard(card3Test);
        int blueLv1 = test.sumType(Color.BLUE, Level.ONE);
        int blueLv2 = test.sumType(Color.BLUE, Level.TWO);
        int blueLv3 = test.sumType(Color.BLUE, Level.THREE);

        assertEquals(1, blueLv1);
        assertEquals(1, blueLv2);
        assertEquals(1, blueLv3);

    }
    /**
     * Test to watch if the method "sumColors" works properly by adding three cards to the slot.
     */
    @Test
    public void sumColorsTest(){
        test.addCard(card);
        test.addCard(cardTest);
        test.addCard(card4Test);
        int blueCards = test.sumColors(Color.BLUE);

        assertEquals(2, blueCards);
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        it.polimi.ingsw.View.ReducedModel.CardSlot slotView;
        slotView = test.toView();
        assertEquals(0, slotView.getLevelOccupied());

        test.addCard(card);
        test.addCard(cardTest);
        test.addCard(card2Test);
        test.addCard(card3Test);
        slotView = test.toView();
        assertEquals(3, slotView.getLevelOccupied());
    }
}
