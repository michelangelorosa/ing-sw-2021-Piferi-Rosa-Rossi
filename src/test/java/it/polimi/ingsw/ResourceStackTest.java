package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.ResourceStack;
import org.junit.Test;

/**
 * Unit test for ResourceStack Class.
 */
public class ResourceStackTest {

    ResourceStack stack = new ResourceStack(1, 2, 3, 4);

    /**
     * Constructor test for ResourceStack Class.
     */
    @Test
    public void constructorTest() {
        assertEquals(1, stack.getShields());
        assertEquals(2, stack.getServants());
        assertEquals(3, stack.getCoins());
        assertEquals(4, stack.getStones());
    }

    /**
     * Setter test for ResourceStack Class.
     */
    @Test
    public void setterTest() {
        stack.setShields(10);
        stack.setServants(20);
        stack.setCoins(30);
        stack.setStones(40);

        assertEquals(10, stack.getShields());
        assertEquals(20, stack.getServants());
        assertEquals(30, stack.getCoins());
        assertEquals(40, stack.getStones());
    }

    /**
     * Getter test for ResourceStack Class.
     */
    @Test
    public void getterTest() {
        int shields2 = stack.getShields();
        int servants2 = stack.getServants();
        int coins2 = stack.getCoins();
        int stones2 = stack.getStones();

        assertEquals(1, shields2);
        assertEquals(2, servants2);
        assertEquals(3, coins2);
        assertEquals(4, stones2);
    }

    /**
     * Test for "toString" method in ResourceStack Class.
     */
    @Test
    public void toStringTest() {

        String testString = "1 2 3 4";

        assertArrayEquals(testString.toCharArray(), stack.toString().toCharArray());
    }
}
