package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.ResourceType;
import it.polimi.ingsw.Model.Strongbox;
import org.junit.Test;

/**
 * Unit test for Strongbox Class.
 */
public class StrongboxTest {
    Strongbox strongbox = new Strongbox();

    /**
     * Constructor and getter test for Strongbox Class
     */
    @Test
    public void constructorTest() {
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.STONES));
    }

    /**
     * Test for "reset" method in StrongboxClass;
     */
    @Test
    public void resetTest() {
        ResourceStack resourceStack = new ResourceStack(1,1,2,3);
        strongbox.addToAllTypes(resourceStack);
        strongbox.reset();

        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.STONES));
    }

    /**
     * Test for "addToAllTypesTest" method in Strongbox Class
     */
    @Test
    public void addToAllTypesTest() {
        ResourceStack resourceStack = new ResourceStack(1,1,2,3);
        strongbox.addToAllTypes(resourceStack);

        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(2,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(3,strongbox.getStoredResources().getResource(ResourceType.STONES));
    }

    /**
     * Test for "removeFromAllTypesTest" method in Strongbox Class
     */
    @Test
    public void removeFromAllTypesTest() {
        ResourceStack resourceStack = new ResourceStack(2,1,2,3);

        strongbox.addResourcesByType(1, ResourceType.SHIELDS);
        strongbox.addResourcesByType(2, ResourceType.SERVANTS);
        strongbox.addResourcesByType(3, ResourceType.COINS);
        strongbox.addResourcesByType(4, ResourceType.STONES);

        strongbox.removeFromAllTypes(resourceStack);

        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(2,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(3,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(4,strongbox.getStoredResources().getResource(ResourceType.STONES));

        ResourceStack resourceStack2 = new ResourceStack(1,1,2,3);

        strongbox.removeFromAllTypes(resourceStack2);

        assertEquals(0,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.STONES));
    }

    /**
     * Test for "addResourcesByType" method in Strongbox Class.
     */
    @Test
    public void addResourcesByTypeTest() {
        strongbox.addResourcesByType(1, ResourceType.SHIELDS);
        strongbox.addResourcesByType(2, ResourceType.SERVANTS);
        strongbox.addResourcesByType(3, ResourceType.COINS);
        strongbox.addResourcesByType(4, ResourceType.STONES);

        assertEquals(1,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(2,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(3,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(4,strongbox.getStoredResources().getResource(ResourceType.STONES));

        strongbox.addResourcesByType(1, ResourceType.SHIELDS);
        strongbox.addResourcesByType(2, ResourceType.SERVANTS);
        strongbox.addResourcesByType(3, ResourceType.COINS);
        strongbox.addResourcesByType(4, ResourceType.STONES);

        assertEquals(2,strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(4,strongbox.getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(6,strongbox.getStoredResources().getResource(ResourceType.COINS));
        assertEquals(8,strongbox.getStoredResources().getResource(ResourceType.STONES));
    }

    /**
     * Test for "countResourceByType" method in Strongbox Class.
     */
    @Test
    public void countResourceByTypeTest() {
        strongbox.addResourcesByType(1, ResourceType.SHIELDS);
        strongbox.addResourcesByType(2, ResourceType.SERVANTS);
        strongbox.addResourcesByType(3, ResourceType.COINS);
        strongbox.addResourcesByType(4, ResourceType.STONES);

        assertEquals(1, strongbox.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(2, strongbox.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(3, strongbox.countResourcesByType(ResourceType.COINS));
        assertEquals(4, strongbox.countResourcesByType(ResourceType.STONES));

        strongbox.addResourcesByType(3, ResourceType.SHIELDS);
        strongbox.addResourcesByType(5, ResourceType.SERVANTS);
        strongbox.addResourcesByType(11, ResourceType.COINS);
        strongbox.addResourcesByType(0, ResourceType.STONES);

        assertEquals(4, strongbox.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(7, strongbox.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(14, strongbox.countResourcesByType(ResourceType.COINS));
        assertEquals(4, strongbox.countResourcesByType(ResourceType.STONES));
    }

    /**
     * Test for "removeResourcesByType" method in Strongbox Class.
     */
    @Test
    public void removeResourcesByTypeTest() {
        strongbox.addResourcesByType(1, ResourceType.SHIELDS);
        strongbox.addResourcesByType(2, ResourceType.SERVANTS);
        strongbox.addResourcesByType(3, ResourceType.COINS);
        strongbox.addResourcesByType(4, ResourceType.STONES);

        strongbox.addResourcesByType(3, ResourceType.SHIELDS);
        strongbox.addResourcesByType(5, ResourceType.SERVANTS);
        strongbox.addResourcesByType(11, ResourceType.COINS);
        strongbox.addResourcesByType(0, ResourceType.STONES);

        strongbox.removeResourcesByType(6, ResourceType.SHIELDS);
        strongbox.removeResourcesByType(7, ResourceType.SERVANTS);
        strongbox.removeResourcesByType(6, ResourceType.COINS);
        strongbox.removeResourcesByType(1, ResourceType.STONES);

        assertEquals(4, strongbox.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, strongbox.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(8, strongbox.countResourcesByType(ResourceType.COINS));
        assertEquals(3, strongbox.countResourcesByType(ResourceType.STONES));
    }

    /**
     * Test for "toString" method in Strongbox Class.
     */
    @Test
    public void toStringTest() {
        String string = "0 0 0 0";
        assertEquals(string, strongbox.toString());
    }

}
