package it.polimi.ingsw;

import static org.junit.Assert.*;

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
        assertEquals(0,strongbox.getStoredResources().getShields());
        assertEquals(0,strongbox.getStoredResources().getServants());
        assertEquals(0,strongbox.getStoredResources().getCoins());
        assertEquals(0,strongbox.getStoredResources().getStones());
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

        assertEquals(1,strongbox.getStoredResources().getShields());
        assertEquals(2,strongbox.getStoredResources().getServants());
        assertEquals(3,strongbox.getStoredResources().getCoins());
        assertEquals(4,strongbox.getStoredResources().getStones());

        strongbox.addResourcesByType(1, ResourceType.SHIELDS);
        strongbox.addResourcesByType(2, ResourceType.SERVANTS);
        strongbox.addResourcesByType(3, ResourceType.COINS);
        strongbox.addResourcesByType(4, ResourceType.STONES);

        assertEquals(2,strongbox.getStoredResources().getShields());
        assertEquals(4,strongbox.getStoredResources().getServants());
        assertEquals(6,strongbox.getStoredResources().getCoins());
        assertEquals(8,strongbox.getStoredResources().getStones());
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
