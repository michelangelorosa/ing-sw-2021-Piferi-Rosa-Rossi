package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.ResourceType;
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
        assertEquals(1, stack.getResource(ResourceType.SHIELDS));
        assertEquals(2, stack.getResource(ResourceType.SERVANTS));
        assertEquals(3, stack.getResource(ResourceType.COINS));
        assertEquals(4, stack.getResource(ResourceType.STONES));
    }

    /**
     * Setter test for ResourceStack Class.
     */
    @Test
    public void setterTest() {
        stack.setResource(10, ResourceType.SHIELDS);
        stack.setResource(20, ResourceType.SERVANTS);
        stack.setResource(30, ResourceType.COINS);
        stack.setResource(40, ResourceType.STONES);

        assertEquals(10, stack.getResource(ResourceType.SHIELDS));
        assertEquals(20, stack.getResource(ResourceType.SERVANTS));
        assertEquals(30, stack.getResource(ResourceType.COINS));
        assertEquals(40, stack.getResource(ResourceType.STONES));
    }

    /**
     * Getter test for ResourceStack Class.
     */
    @Test
    public void getterTest() {
        int shields2 = stack.getResource(ResourceType.SHIELDS);
        int servants2 = stack.getResource(ResourceType.SERVANTS);
        int coins2 = stack.getResource(ResourceType.COINS);
        int stones2 = stack.getResource(ResourceType.STONES);

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
