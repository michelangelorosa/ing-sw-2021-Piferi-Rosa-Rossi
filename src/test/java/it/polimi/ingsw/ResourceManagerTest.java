package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

/**
 * Unit test for ResourceManager Class.
 */
public class ResourceManagerTest {
    ResourceManager resourceManager = new ResourceManager();
    ResourceType[] resourceTypes = ResourceType.values();

    /**
     * Constructor and getter test for ResourceManager Class.
     */
    @Test
    public void constructorTest() {
        for(int i = 1; i <= 4; i++) {
            assertEquals(0, resourceManager.getWarehouse().countResourcesByType(resourceTypes[i]));
            assertEquals(0, resourceManager.getStrongbox().countResourcesByType(resourceTypes[i]));
        }
    }

    /**
     * Test for "reset" method in ResourceManager Class.
     */
    @Test
    public void resetTest() {
        ResourceStack resourceStack = new ResourceStack(5, 2, 1, 3);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(3, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);
        resourceManager.addProductionResources(resourceStack);

        resourceManager.reset();
        resourceStack = resourceManager.countAllResources();
        assertEquals(0, resourceStack.getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceStack.getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceStack.getResource(ResourceType.COINS));
        assertEquals(0, resourceStack.getResource(ResourceType.STONES));
    }

    /**
     * Test for "countAllResources" method in ResourceManager Class.
     */
    @Test
    public void countAllResourcesTest() {
        ResourceStack resourceStack = resourceManager.countAllResources();
        assertEquals(0, resourceStack.getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceStack.getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceStack.getResource(ResourceType.COINS));
        assertEquals(0, resourceStack.getResource(ResourceType.STONES));

        resourceManager.getWarehouse().addResources(3, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.getWarehouse().addResources(1, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2]);
        resourceManager.getStrongbox().addResourcesByType(10, ResourceType.SHIELDS);
        resourceManager.getStrongbox().addResourcesByType(5, ResourceType.SERVANTS);
        resourceManager.getStrongbox().addResourcesByType(3, ResourceType.COINS);

        resourceStack = resourceManager.countAllResources();
        assertEquals(13, resourceStack.getResource(ResourceType.SHIELDS));
        assertEquals(6, resourceStack.getResource(ResourceType.SERVANTS));
        assertEquals(3, resourceStack.getResource(ResourceType.COINS));
        assertEquals(0, resourceStack.getResource(ResourceType.STONES));
    }

    /**
     * Test for "countAllResourcesByType" method in ResourceManager Class.
     */
    @Test
    public void countAllResourcesByTypeTest() {
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.STONES));

        resourceManager.getWarehouse().addResources(3, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.getWarehouse().addResources(1, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2]);
        resourceManager.getStrongbox().addResourcesByType(10, ResourceType.SHIELDS);
        resourceManager.getStrongbox().addResourcesByType(5, ResourceType.SERVANTS);
        resourceManager.getStrongbox().addResourcesByType(3, ResourceType.COINS);

        assertEquals(13, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(6, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(3, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.STONES));
    }

    /**
     * Test for "countResourcesToVictoryPoints" method in ResourceManager Class.
     */
    @Test
    public void countResourcesToVictoryPointsTest() {
        assertEquals(0, resourceManager.countResourcesToVictoryPoints());

        resourceManager.getWarehouse().addResources(3, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.getWarehouse().addResources(1, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2]);
        resourceManager.getStrongbox().addResourcesByType(10, ResourceType.SHIELDS);
        resourceManager.getStrongbox().addResourcesByType(5, ResourceType.SERVANTS);
        resourceManager.getStrongbox().addResourcesByType(3, ResourceType.COINS);

        assertEquals(4, resourceManager.countResourcesToVictoryPoints());
    }

    /**
     * Test for "addProductionResources" method in ResourceManager Class.
     */
    @Test
    public void addProductionResourcesTest() {
        resourceManager.getWarehouse().addResources(3, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.getWarehouse().addResources(1, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2]);
        resourceManager.getStrongbox().addResourcesByType(10, ResourceType.SHIELDS);
        resourceManager.getStrongbox().addResourcesByType(5, ResourceType.SERVANTS);
        resourceManager.getStrongbox().addResourcesByType(3, ResourceType.COINS);

        assertEquals(4, resourceManager.countResourcesToVictoryPoints());
    }

    /**
     * Test for "addMarketResourcesByType" method in ResourceManager Class.
     * <p>
     * Testing includes all possible errors.
     */
    @Test
    public void addMarketResourcesByTypeTest() {
        assertEquals(1, resourceManager.addMarketResourcesByType(4, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]));
        assertEquals(3, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertSame(ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());

        assertEquals(2, resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]));
        assertEquals(3, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertSame(ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());

        assertEquals(0, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[1]));
        assertEquals(1, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertSame(ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());

        assertEquals(0, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[1]));
        assertEquals(2, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertSame(ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());

        assertEquals(1, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[1]));
        assertEquals(2, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertSame(ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());

        assertEquals(1, resourceManager.addMarketResourcesByType(2, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2]));
        assertEquals(1, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertSame(ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());

        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.SHIELDS);
        assertEquals(1, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));

        assertEquals(0, resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));
        assertEquals(2, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertSame(ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());

        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);
        assertEquals(1, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));
        assertEquals(2, resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertSame(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot2().getResourceType());

        assertEquals(1, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getExtraWarehouseDepot2()));

        resourceManager.getWarehouse().getWarehouseDepots()[0].setStoredResources(0);
        resourceManager.getWarehouse().getWarehouseDepots()[1].setStoredResources(0);
        resourceManager.getWarehouse().getWarehouseDepots()[2].setStoredResources(0);
        resourceManager.getWarehouse().getExtraWarehouseDepot1().setStoredResources(0);
        resourceManager.getWarehouse().getExtraWarehouseDepot2().setStoredResources(0);

        assertEquals(1, resourceManager.addMarketResourcesByType(4, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]));

        assertEquals(1, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));

        assertEquals(4, resourceManager.addMarketResourcesByType(4, ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot2()));

        assertEquals(2, resourceManager.addMarketResourcesByType(4, ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));

        this.resourceManager.reset();

        this.resourceManager.getWarehouse().activateLeaderDepot(ResourceType.SERVANTS);
        assertEquals(2, resourceManager.addMarketResourcesByType(4, ResourceType.SERVANTS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));
        assertEquals(2, resourceManager.addMarketResourcesByType(2, ResourceType.SERVANTS, resourceManager.getWarehouse().getExtraWarehouseDepot1()));

        this.resourceManager.reset();
        assertEquals(0, resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]));
        assertEquals(2, resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]));

        assertEquals(0, resourceManager.addMarketResourcesByType(1, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[0]));

        assertEquals(0, resourceManager.addMarketResourcesByType(2, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[1]));

        assertEquals(0, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[2]));

        assertEquals(1, resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[2]));

    }

    /**
     * Test for "addMarketResourcesByType" method in ResourceManager Class.
     * <p>
     * Testing includes all possible errors.
     */
    @Test
    public void payProductionPriceTest() {
        ResourceStack resourceStack = new ResourceStack(5, 2, 1, 3);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(3, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);

        resourceManager.addProductionResources(resourceStack);

        // ---------- ERROR DISPLAY TEST ---------- //
        resourceStack = new ResourceStack(5, 2, 4, 4);
        resourceManager.payProductionOrCardPrice(resourceStack);

        assertEquals(3, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(2, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());

        // ---------- WAREHOUSE EMPTYING TEST WITH ONE EXTRA DEPOT ---------- //
        resourceStack = new ResourceStack(5, 2, 4, 0);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());

        assertEquals(2, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(3, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        resourceManager.reset();

        resourceStack = new ResourceStack(1, 1, 1, 0);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(2, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.addMarketResourcesByType(1, ResourceType.STONES, resourceManager.getWarehouse().getWarehouseDepots()[2]);

        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);
        resourceManager.addMarketResourcesByType(1, ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1());

        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.SHIELDS);
        resourceManager.addMarketResourcesByType(1, ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot2());

        resourceManager.addProductionResources(resourceStack);

        // ---------- REMOVE ZERO RESOURCES TEST ---------- //
        resourceStack = new ResourceStack(0,0,0,0);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(2, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(2, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());

        assertEquals(1, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(1, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(1, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- GENERIC TEST WITH TWO EXTRA DEPOTS ---------- //
        resourceStack = new ResourceStack(2,0,2,2);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());

        assertEquals(1, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(1, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(1, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- ALL ERROR DISPLAY TEST ---------- //
        resourceStack = new ResourceStack(3,12,2,2);
        resourceManager.payProductionOrCardPrice(resourceStack);

        // ---------- EMPTYING WAREHOUSE AND STRONGBOX TEST ---------- //
        resourceStack = new ResourceStack(2,1,1,0);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());
        assertEquals(ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot2().getResourceType());

        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- EMPTY WAREHOUSE AND STRONGBOX TEST ---------- //
        resourceStack = new ResourceStack(2,1,1,0);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());
        assertEquals(ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot2().getResourceType());

        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- REMOVE NOTHING WITH EMPTY MANAGER TEST ---------- //
        resourceStack = new ResourceStack(0,0,0,0);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());
        assertEquals(ResourceType.SHIELDS, resourceManager.getWarehouse().getExtraWarehouseDepot2().getResourceType());

        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        resourceManager.reset();

        resourceStack = new ResourceStack(20, 20, 20, 20);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(3, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);
        resourceManager.addMarketResourcesByType(1, ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1());

        resourceManager.addProductionResources(resourceStack);

        // ---------- SINGLE RESOURCE TYPE TEST ---------- //
        resourceStack = new ResourceStack(5, 0, 0, 0);
        resourceManager.payProductionOrCardPrice(resourceStack);
        assertEquals(3, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());

        assertEquals(17, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(20, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(20, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(20, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- SINGLE RESOURCE TYPE TEST ---------- //
        resourceStack = new ResourceStack(0, 5, 0, 0);
        resourceManager.payProductionOrCardPrice(resourceStack);

        assertEquals(3, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());

        assertEquals(17, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(15, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(20, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(20, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- SINGLE RESOURCE TYPE TEST ---------- //
        resourceStack = new ResourceStack(0, 0, 5, 0);
        resourceManager.payProductionOrCardPrice(resourceStack);

        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(1, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());

        assertEquals(17, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(15, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(18, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(20, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));

        // ---------- SINGLE RESOURCE TYPE TEST ---------- //
        resourceStack = new ResourceStack(0, 0, 0, 5);
        resourceManager.payProductionOrCardPrice(resourceStack);

        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, resourceManager.getWarehouse().getExtraWarehouseDepot2().getStoredResources());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[0].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(ResourceType.NONE, resourceManager.getWarehouse().getWarehouseDepots()[2].getResourceType());
        assertEquals(ResourceType.STONES, resourceManager.getWarehouse().getExtraWarehouseDepot1().getResourceType());

        assertEquals(17, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SHIELDS));
        assertEquals(15, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.SERVANTS));
        assertEquals(18, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));
        assertEquals(16, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.STONES));
    }
}
