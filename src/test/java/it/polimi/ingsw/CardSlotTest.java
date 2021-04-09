package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
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
    DevelopmentCard card4Test = new DevelopmentCard(Color.BLUE, Level.THREE, 4, 100, cost, input, output, 0);

    @Test
    public void costructorTest(){
        assertEquals(0, test.getLevelOccupied());
    }

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

    @Test
    public void isFullTest(){
        test.addCard(card);
        assertEquals(1, test.getLevelOccupied());

        test.addCard(cardTest);
        assertEquals(2, test.getLevelOccupied());

        test.addCard(card3Test);
        assertEquals(3, test.getLevelOccupied());

        test.addCard(card4Test);
        assertEquals(3, test.getLevelOccupied());
    }

    @Test
    public void finalPointsTest(){
        test.addCard(card);
        assertEquals(1, test.getLevelOccupied());

        test.addCard(cardTest);
        assertEquals(2, test.getLevelOccupied());

        test.addCard(card3Test);
        assertEquals(3, test.getLevelOccupied());

        int points = 0;

        points = test.finalPoints();

        assertEquals(300, points);
    }

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

    @Test
    public void getFirstCardTest(){
        test.addCard(card);
        test.addCard(cardTest);
        test.addCard(card3Test);

        assertEquals(card3Test, test.getFirstCard());

    }




}
