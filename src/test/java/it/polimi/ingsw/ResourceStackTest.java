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
    ResourceStack stack2 = new ResourceStack(2, 3, 4, 5);


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
     * Test for "addResource" method in ResourceStack Class.
     */
    @Test
    public  void addResourceTest() {
        ResourceStack stack5 = new ResourceStack(0, 0, 0, 0);
        stack5.addResource(10, ResourceType.SHIELDS);
        stack5.addResource(10, ResourceType.SERVANTS);
        stack5.addResource(10, ResourceType.COINS);
        stack5.addResource(10, ResourceType.STONES);

        assertEquals(10, stack5.getResource(ResourceType.SHIELDS));
        assertEquals(10, stack5.getResource(ResourceType.SERVANTS));
        assertEquals(10, stack5.getResource(ResourceType.COINS));
        assertEquals(10, stack5.getResource(ResourceType.STONES));

        stack5.addResource(57, ResourceType.SHIELDS);

        assertEquals(67, stack5.getResource(ResourceType.SHIELDS));
    }

    /**
     * Test for addAllTypes. I created a new ResourceStack called stack3 that is the sum of the resources of stack
     * and stack2. After calling the method stack and stack3 has the same number of resources.
     */
    @Test
    public void addAllTypesTest(){
        stack.addToAllTypes(stack2);
        ResourceStack stack3 = new ResourceStack(3, 5, 7, 9);

        assertArrayEquals(stack3.toString().toCharArray(), stack.toString().toCharArray());
    }

    /**
     * Test for "removeResource" method in ResourceStack Class.
     */
    @Test
    public void removeResourceTest(){
        ResourceStack stack5 = new ResourceStack(50, 50, 50, 50);
        stack5.removeResource(10, ResourceType.SHIELDS);
        stack5.removeResource(15, ResourceType.SERVANTS);
        stack5.removeResource(20, ResourceType.COINS);
        stack5.removeResource(25, ResourceType.STONES);

        assertEquals(40, stack5.getResource(ResourceType.SHIELDS));
        assertEquals(35, stack5.getResource(ResourceType.SERVANTS));
        assertEquals(30, stack5.getResource(ResourceType.COINS));
        assertEquals(25, stack5.getResource(ResourceType.STONES));

        stack5.removeResource(40, ResourceType.SHIELDS);

        assertEquals(0, stack5.getResource(ResourceType.SHIELDS));
    }

    /**
     * Test for "removeFromAllType" method in ResourceStack Class.
     */
    @Test
    public void removeFromAllTypesTest(){
        ResourceStack stack5 = new ResourceStack(50, 50, 50, 50);
        ResourceStack stack6 = new ResourceStack(10, 15, 20, 25);
        stack5.removeFromAllTypes(stack6);

        assertEquals(40, stack5.getResource(ResourceType.SHIELDS));
        assertEquals(35, stack5.getResource(ResourceType.SERVANTS));
        assertEquals(30, stack5.getResource(ResourceType.COINS));
        assertEquals(25, stack5.getResource(ResourceType.STONES));

        stack6 = new ResourceStack(123, 432235, 55, 1);
        stack5.removeFromAllTypes(stack6);

        assertEquals(0, stack5.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack5.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack5.getResource(ResourceType.COINS));
        assertEquals(24, stack5.getResource(ResourceType.STONES));
    }

    /**
     * Test for "isEmptyStack" method in ResourceStack Class.
     */
    @Test
    public void isEmptyTest() {
        ResourceStack stack5 = new ResourceStack(1,0,0,0);
        assertFalse(stack5.isEmpty());
        stack5 = new ResourceStack(0,1,0,0);
        assertFalse(stack5.isEmpty());
        stack5 = new ResourceStack(0,0,1,0);
        assertFalse(stack5.isEmpty());
        stack5 = new ResourceStack(0,0,0,1);
        assertFalse(stack5.isEmpty());

        stack5 = new ResourceStack(0,0,0,0);
        assertTrue(stack5.isEmpty());
    }

    /**
     * Test for "copyStack" method in ResourceStack Class.
     */
    @Test
    public void copyStackTest() {
        ResourceStack copiedStack = stack.copyStack();
        assertEquals(copiedStack.toString(), stack.toString());
    }

    /**
     * Test for "toString" method in ResourceStack Class.
     */
    @Test
    public void toStringTest() {
        assertEquals("1 2 3 4", stack.toString());
    }

}
