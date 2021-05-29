package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import org.junit.Test;

/**
 * Unit test for Warehouse Class.
 */
public class WarehouseTest {

    Warehouse warehouse = new Warehouse();

    /**
     * Constructor and getter test for Warehouse Class.
     */
    @Test
    public void constructorTest() {
        assertSame(warehouse.getWarehouseDepots()[0].getResourceType(), ResourceType.NONE);
        assertSame(warehouse.getWarehouseDepots()[1].getResourceType(), ResourceType.NONE);
        assertSame(warehouse.getWarehouseDepots()[2].getResourceType(), ResourceType.NONE);
        assertSame(warehouse.getExtraWarehouseDepot1().getResourceType(), ResourceType.NONE);
        assertSame(warehouse.getExtraWarehouseDepot2().getResourceType(), ResourceType.NONE);

        assertEquals(3, warehouse.getWarehouseDepots()[0].getMaxResources());
        assertEquals(2, warehouse.getWarehouseDepots()[1].getMaxResources());
        assertEquals(1, warehouse.getWarehouseDepots()[2].getMaxResources());
        assertEquals(2, warehouse.getExtraWarehouseDepot1().getMaxResources());
        assertEquals(2, warehouse.getExtraWarehouseDepot2().getMaxResources());

        assertFalse(warehouse.getWarehouseDepots()[0].isFromLeaderCardAbility());
        assertFalse(warehouse.getWarehouseDepots()[1].isFromLeaderCardAbility());
        assertFalse(warehouse.getWarehouseDepots()[2].isFromLeaderCardAbility());
        assertTrue(warehouse.getExtraWarehouseDepot1().isFromLeaderCardAbility());
        assertTrue(warehouse.getExtraWarehouseDepot2().isFromLeaderCardAbility());

        assertFalse(warehouse.isExtraWarehouseDepot1IsActive());
        assertFalse(warehouse.isExtraWarehouseDepot2IsActive());

    }

    /**
     * Test for "activateLeaderDepot" method in Warehouse Class.
     */
    @Test
    public void activateLeaderDepotTest() {
        warehouse.activateLeaderDepot(ResourceType.SHIELDS);

        assertTrue(warehouse.isExtraWarehouseDepot1IsActive());
        assertFalse(warehouse.isExtraWarehouseDepot2IsActive());
        assertSame(ResourceType.SHIELDS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.NONE, warehouse.getExtraWarehouseDepot2().getResourceType());

        warehouse.activateLeaderDepot(ResourceType.COINS);

        assertTrue(warehouse.isExtraWarehouseDepot1IsActive());
        assertTrue(warehouse.isExtraWarehouseDepot2IsActive());
        assertSame(ResourceType.SHIELDS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot2().getResourceType());

        warehouse.activateLeaderDepot(ResourceType.COINS);

        assertTrue(warehouse.isExtraWarehouseDepot1IsActive());
        assertTrue(warehouse.isExtraWarehouseDepot2IsActive());
        assertSame(ResourceType.SHIELDS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot2().getResourceType());

    }

    /**
     * Test for "activateLeaderDepot" method in Warehouse Class.
     */
    @Test
    public void countResourcesByTypeTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        assertEquals(3,warehouse.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(2,warehouse.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(1,warehouse.countResourcesByType(ResourceType.STONES));

        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SHIELDS);

        assertEquals(5,warehouse.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(0,warehouse.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(1,warehouse.countResourcesByType(ResourceType.STONES));

        warehouse.activateLeaderDepot(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot1().addResources(2);

        assertEquals(5,warehouse.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(0,warehouse.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(1,warehouse.countResourcesByType(ResourceType.STONES));
        assertEquals(2,warehouse.countResourcesByType(ResourceType.COINS));

        warehouse.activateLeaderDepot(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot2().addResources(1);

        assertEquals(5,warehouse.countResourcesByType(ResourceType.SHIELDS));
        assertEquals(0,warehouse.countResourcesByType(ResourceType.SERVANTS));
        assertEquals(1,warehouse.countResourcesByType(ResourceType.STONES));
        assertEquals(3,warehouse.countResourcesByType(ResourceType.COINS));

    }

    /**
     * Test for "addResourcesTest" method in Warehouse Class.
     */
    @Test
    public void addResourcesTest() {
        int extraResources;

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(1);
        warehouse.getWarehouseDepots()[2].addResources(0);

        extraResources = warehouse.addResources(1,ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]);
        assertEquals(1,extraResources);
        assertEquals(3,warehouse.getWarehouseDepots()[0].getStoredResources());

        extraResources = warehouse.addResources(1,ResourceType.COINS, warehouse.getWarehouseDepots()[2]);
        assertEquals(1,extraResources);
        assertEquals(0,warehouse.getWarehouseDepots()[2].getStoredResources());

        extraResources = warehouse.addResources(1, ResourceType.SERVANTS, warehouse.getWarehouseDepots()[1]);
        assertEquals(2,warehouse.getWarehouseDepots()[1].getStoredResources());
        assertEquals(ResourceType.SERVANTS,warehouse.getWarehouseDepots()[1].getResourceType());
        assertEquals(0,extraResources);

        warehouse.getWarehouseDepots()[0].setStoredResources(0);
        warehouse.getWarehouseDepots()[1].setStoredResources(0);
        warehouse.getWarehouseDepots()[2].setStoredResources(0);

        extraResources = warehouse.addResources(2,ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]);
        assertEquals(0,extraResources);
        assertEquals(2,warehouse.getWarehouseDepots()[0].getStoredResources());
        assertEquals(ResourceType.SHIELDS,warehouse.getWarehouseDepots()[0].getResourceType());

        extraResources = warehouse.addResources(1, ResourceType.SERVANTS, warehouse.getWarehouseDepots()[1]);
        assertEquals(0,extraResources);
        assertEquals(1,warehouse.getWarehouseDepots()[1].getStoredResources());
        assertEquals(ResourceType.SERVANTS,warehouse.getWarehouseDepots()[1].getResourceType());

        extraResources = warehouse.addResources(1,ResourceType.COINS, warehouse.getWarehouseDepots()[2]);
        assertEquals(0,extraResources);
        assertEquals(1,warehouse.getWarehouseDepots()[2].getStoredResources());
        assertEquals(ResourceType.COINS,warehouse.getWarehouseDepots()[2].getResourceType());

        warehouse.getWarehouseDepots()[0].setStoredResources(0);
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.NONE);

        extraResources = warehouse.addResources(3,ResourceType.STONES, warehouse.getWarehouseDepots()[0]);
        assertEquals(0,extraResources);
        assertEquals(3,warehouse.getWarehouseDepots()[0].getStoredResources());
        assertSame(ResourceType.STONES,warehouse.getWarehouseDepots()[0].getResourceType());
    }

    @Test
    public void canAddToDepotTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(1);
        warehouse.getWarehouseDepots()[2].addResources(0);

        assertFalse(warehouse.canAddToDepot(ResourceType.COINS, warehouse.getWarehouseDepots()[0]));
        assertFalse(warehouse.canAddToDepot(ResourceType.COINS, warehouse.getWarehouseDepots()[1]));
        assertFalse(warehouse.canAddToDepot(ResourceType.COINS, warehouse.getWarehouseDepots()[2]));

        assertTrue(warehouse.canAddToDepot(ResourceType.SERVANTS, warehouse.getWarehouseDepots()[1]));
        assertTrue(warehouse.canAddToDepot(ResourceType.STONES, warehouse.getWarehouseDepots()[2]));
        assertFalse(warehouse.canAddToDepot(ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]));

        warehouse.activateLeaderDepot(ResourceType.COINS);

        assertTrue(warehouse.canAddToDepot(ResourceType.COINS, warehouse.getExtraWarehouseDepot1()));
        assertFalse(warehouse.canAddToDepot(ResourceType.SHIELDS, warehouse.getExtraWarehouseDepot1()));

        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.NONE);
        assertTrue(warehouse.canAddToDepot(ResourceType.COINS, warehouse.getWarehouseDepots()[2]));
        assertFalse(warehouse.canAddToDepot(ResourceType.SHIELDS, warehouse.getWarehouseDepots()[2]));
    }

    @Test
    public void removeResourcesFromDepotTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].addResources(3);

        assertTrue(warehouse.canRemoveFromDepot(warehouse.getWarehouseDepots()[0]));
        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);
        assertEquals(2, warehouse.getWarehouseDepots()[0].getStoredResources());
        assertTrue(warehouse.canRemoveFromDepot(warehouse.getWarehouseDepots()[0]));
        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);
        assertEquals(1, warehouse.getWarehouseDepots()[0].getStoredResources());
        assertTrue(warehouse.canRemoveFromDepot(warehouse.getWarehouseDepots()[0]));
        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);
        assertEquals(0, warehouse.getWarehouseDepots()[0].getStoredResources());

        assertFalse(warehouse.canRemoveFromDepot(warehouse.getWarehouseDepots()[0]));
    }

    /**
     * Test for "removeResourcesByType" method in Warehouse Class.
     */
    @Test
    public void removeResourcesByTypeTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        // ---------- GENERIC USE TEST (without extra depots) ---------- //
        warehouse.removeResourcesByType(2,ResourceType.SHIELDS);
        assertSame(ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0].getResourceType());
        assertEquals(1,warehouse.getWarehouseDepots()[0].getStoredResources());

        warehouse.removeResourcesByType(2,ResourceType.SERVANTS);
        assertSame(ResourceType.NONE, warehouse.getWarehouseDepots()[1].getResourceType());
        assertEquals(0,warehouse.getWarehouseDepots()[1].getStoredResources());

        warehouse.removeResourcesByType(1,ResourceType.SHIELDS);
        assertSame(ResourceType.NONE, warehouse.getWarehouseDepots()[0].getResourceType());
        assertEquals(0,warehouse.getWarehouseDepots()[0].getStoredResources());

        warehouse.removeResourcesByType(1,ResourceType.STONES);
        assertSame(ResourceType.NONE, warehouse.getWarehouseDepots()[2].getResourceType());
        assertEquals(0,warehouse.getWarehouseDepots()[2].getStoredResources());

        // ---------- RETURN TO INITIAL CONDITIONS ---------- //
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        warehouse.activateLeaderDepot(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot1().addResources(2);

        warehouse.activateLeaderDepot(ResourceType.SERVANTS);
        warehouse.getExtraWarehouseDepot2().addResources(2);

        // ---------- EXTRA DEPOT 1 TEST ---------- //
        warehouse.removeResourcesByType(2, ResourceType.COINS);
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertEquals(0,warehouse.getExtraWarehouseDepot1().getStoredResources());

        warehouse.getExtraWarehouseDepot1().addResources(2);

        // ---------- EXTRA DEPOT 2 EMPTYING TEST ---------- //
        warehouse.removeResourcesByType(4, ResourceType.SERVANTS);
        assertSame(ResourceType.NONE, warehouse.getWarehouseDepots()[1].getResourceType());
        assertSame(ResourceType.SERVANTS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(0,warehouse.getWarehouseDepots()[1].getStoredResources());
        assertEquals(0,warehouse.getExtraWarehouseDepot2().getStoredResources());

        // ---------- RETURN TO INITIAL CONDITIONS ---------- //
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getExtraWarehouseDepot2().addResources(2);

        // ---------- NORMAL DEPOT + EXTRA DEPOT 2 TEST AND EMPTYING TEST ---------- //
        warehouse.removeResourcesByType(3, ResourceType.SERVANTS);
        assertSame(ResourceType.NONE, warehouse.getWarehouseDepots()[1].getResourceType());
        assertSame(ResourceType.SERVANTS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(0,warehouse.getWarehouseDepots()[1].getStoredResources());
        assertEquals(1,warehouse.getExtraWarehouseDepot2().getStoredResources());

        warehouse.removeResourcesByType(1, ResourceType.SERVANTS);
        assertSame(ResourceType.NONE, warehouse.getWarehouseDepots()[1].getResourceType());
        assertSame(ResourceType.SERVANTS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(0,warehouse.getWarehouseDepots()[1].getStoredResources());
        assertEquals(0,warehouse.getExtraWarehouseDepot2().getStoredResources());

        // ---------- BOTH EXTRA DEPOTS ONLY TEST ---------- //
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);
        warehouse.getExtraWarehouseDepot1().setStoredResources(0);
        warehouse.getExtraWarehouseDepot2().setStoredResources(0);

        warehouse.getExtraWarehouseDepot1().setResourceType(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot2().setResourceType(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot1().addResources(2);
        warehouse.getExtraWarehouseDepot2().addResources(2);

        // simple test //
        warehouse.removeResourcesByType(1, ResourceType.COINS);
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(1,warehouse.getExtraWarehouseDepot1().getStoredResources());
        assertEquals(2,warehouse.getExtraWarehouseDepot2().getStoredResources());

        warehouse.getExtraWarehouseDepot1().addResources(1);

        // emptying extra depot 1 only //
        warehouse.removeResourcesByType(2, ResourceType.COINS);
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(0,warehouse.getExtraWarehouseDepot1().getStoredResources());
        assertEquals(2,warehouse.getExtraWarehouseDepot2().getStoredResources());

        warehouse.getExtraWarehouseDepot1().addResources(2);

        // emptying extra depot 1 and simple test for extra depot 2 //
        warehouse.removeResourcesByType(3, ResourceType.COINS);
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(0,warehouse.getExtraWarehouseDepot1().getStoredResources());
        assertEquals(1,warehouse.getExtraWarehouseDepot2().getStoredResources());

        warehouse.getExtraWarehouseDepot1().addResources(2);
        warehouse.getExtraWarehouseDepot2().addResources(1);

        // emptying extra depot 1 and extra depot 2 //
        warehouse.removeResourcesByType(4, ResourceType.COINS);
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot1().getResourceType());
        assertSame(ResourceType.COINS, warehouse.getExtraWarehouseDepot2().getResourceType());
        assertEquals(0,warehouse.getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0,warehouse.getExtraWarehouseDepot2().getStoredResources());

    }

    /**
     * Test for "switchDepotResources" method in Warehouse Class.
     */
    @Test
    public void switchDepotResourcesTest() {
        boolean isSwitchable;

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        // ---------- UNSWITCHABLE DEPOTS SWITCH TRY ---------- //
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[1]);
        assertFalse(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[1], warehouse.getWarehouseDepots()[0]);
        assertFalse(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[2]);
        assertFalse(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[2], warehouse.getWarehouseDepots()[0]);
        assertFalse(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[1], warehouse.getWarehouseDepots()[2]);
        assertFalse(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[2], warehouse.getWarehouseDepots()[1]);
        assertFalse(isSwitchable);

        warehouse.getWarehouseDepots()[0].setStoredResources(1);
        warehouse.getWarehouseDepots()[1].setStoredResources(1);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        // ---------- ALL POSSIBLE SWITCHES, SAME NUMBER OF STORED RESOURCES DEPOTS --------- //
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[1]);
        assertTrue(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[1], warehouse.getWarehouseDepots()[0]);
        assertTrue(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[2]);
        assertTrue(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[2], warehouse.getWarehouseDepots()[0]);
        assertTrue(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[1], warehouse.getWarehouseDepots()[2]);
        assertTrue(isSwitchable);
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[2], warehouse.getWarehouseDepots()[1]);
        assertTrue(isSwitchable);

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].setStoredResources(1);
        warehouse.getWarehouseDepots()[1].setStoredResources(1);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        // ---------- RESOURCE TYPE TEST AFTER SWITCH --------- //
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[1]);
        assertTrue(isSwitchable);
        assertSame(ResourceType.SERVANTS, warehouse.getWarehouseDepots()[0].getResourceType());
        assertSame(ResourceType.SHIELDS, warehouse.getWarehouseDepots()[1].getResourceType());

        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[2]);
        assertTrue(isSwitchable);
        assertSame(ResourceType.STONES, warehouse.getWarehouseDepots()[0].getResourceType());
        assertSame(ResourceType.SERVANTS, warehouse.getWarehouseDepots()[2].getResourceType());

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].setStoredResources(1);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        // ---------- RESOURCE TYPE AND STORED RESOURCES NUMBER TEST AFTER SWITCH, DEPOTS WITH DIFFERENT NUMBERS OF RESOURCES --------- //
        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[1]);
        assertTrue(isSwitchable);
        assertSame(ResourceType.SERVANTS, warehouse.getWarehouseDepots()[0].getResourceType());
        assertSame(ResourceType.SHIELDS, warehouse.getWarehouseDepots()[1].getResourceType());
        assertEquals(2, warehouse.getWarehouseDepots()[0].getStoredResources());
        assertEquals(1, warehouse.getWarehouseDepots()[1].getStoredResources());

        isSwitchable = warehouse.switchDepotResources(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[2]);
        assertFalse(isSwitchable);
    }

    /**
     * Test for "isFull" method in Warehouse Class.
     */
    @Test
    public void isFullTest() {
        assertFalse(warehouse.isFull());

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);
        assertTrue(warehouse.isFull());

        warehouse.activateLeaderDepot(ResourceType.COINS);
        assertFalse(warehouse.isFull());

        warehouse.getExtraWarehouseDepot1().addResources(2);
        assertTrue(warehouse.isFull());

        warehouse.activateLeaderDepot(ResourceType.COINS);
        assertFalse(warehouse.isFull());

        warehouse.getExtraWarehouseDepot2().addResources(2);
        assertTrue(warehouse.isFull());
    }

    /**
     * Test for "resourceTypeFits" in Warehouse Class.
     */
    @Test
    public void resourceTypeFitsTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);
        assertFalse(warehouse.resourceTypeFits(ResourceType.SHIELDS));
        assertFalse(warehouse.resourceTypeFits(ResourceType.SERVANTS));
        assertFalse(warehouse.resourceTypeFits(ResourceType.STONES));

        warehouse.removeResourcesByType(1, ResourceType.SHIELDS);
        assertTrue(warehouse.resourceTypeFits(ResourceType.SHIELDS));

        warehouse.getWarehouseDepots()[0].addResources(1);
        assertFalse(warehouse.resourceTypeFits(ResourceType.SHIELDS));

        warehouse.activateLeaderDepot(ResourceType.COINS);
        assertTrue(warehouse.resourceTypeFits(ResourceType.COINS));

        warehouse.getExtraWarehouseDepot1().addResources(2);
        assertFalse(warehouse.resourceTypeFits(ResourceType.COINS));

        warehouse.activateLeaderDepot(ResourceType.SHIELDS);
        assertTrue(warehouse.resourceTypeFits(ResourceType.SHIELDS));
        assertFalse(warehouse.resourceTypeFits(ResourceType.COINS));

        warehouse.getExtraWarehouseDepot2().addResources(2);
        assertFalse(warehouse.resourceTypeFits(ResourceType.SHIELDS));
        assertFalse(warehouse.resourceTypeFits(ResourceType.COINS));
    }

    /**
     * Test for "emptyDepotExists" in Warehouse Class.
     */
    @Test
    public void emptyDepotExistsTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);
        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        assertFalse(warehouse.emptyDepotExists());

        warehouse.removeResourcesByType(1, ResourceType.STONES);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.removeResourcesByType(2, ResourceType.SERVANTS);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.removeResourcesByType(3, ResourceType.SHIELDS);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.addResources(1, ResourceType.STONES, warehouse.getWarehouseDepots()[2]);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.addResources(2, ResourceType.SERVANTS, warehouse.getWarehouseDepots()[1]);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.addResources(3, ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]);

        assertFalse(warehouse.emptyDepotExists());
    }

    /**
     * Test for "areEmptyDepotsFillableByType" in Warehouse Class.
     */

    @Test
    public void areEmptyDepotsFillableByTypeTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);
        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[2].addResources(1);

        assertFalse(warehouse.areEmptyDepotsFillableByType(ResourceType.SHIELDS));
        assertFalse(warehouse.areEmptyDepotsFillableByType(ResourceType.STONES));
        assertTrue(warehouse.areEmptyDepotsFillableByType(ResourceType.SERVANTS));
        assertTrue(warehouse.areEmptyDepotsFillableByType(ResourceType.COINS));
    }

    @Test
    public void canAddResourceTest() {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        warehouse.activateLeaderDepot(ResourceType.COINS);
        warehouse.addResources(2, ResourceType.COINS, warehouse.getExtraWarehouseDepot1());

        assertFalse(warehouse.canAddResource(ResourceType.SHIELDS));
        assertFalse(warehouse.canAddResource(ResourceType.SERVANTS));
        assertFalse(warehouse.canAddResource(ResourceType.STONES));
        assertFalse(warehouse.canAddResource(ResourceType.COINS));

        warehouse.activateLeaderDepot(ResourceType.COINS);
        assertTrue(warehouse.canAddResource(ResourceType.COINS));

        warehouse.reset();

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(2);
        warehouse.getWarehouseDepots()[1].addResources(1);
        warehouse.getWarehouseDepots()[2].addResources(0);

        assertTrue(warehouse.canAddResource(ResourceType.SHIELDS));
        assertTrue(warehouse.canAddResource(ResourceType.SERVANTS));
        assertTrue(warehouse.canAddResource(ResourceType.STONES));
        assertFalse(warehouse.canAddResource(ResourceType.COINS));

        warehouse.getWarehouseDepots()[1].addResources(1);

        warehouse.activateLeaderDepot(ResourceType.SERVANTS);

        assertTrue(warehouse.canAddResource(ResourceType.SERVANTS));

        warehouse.getExtraWarehouseDepot1().addResources(1);

        assertTrue(warehouse.canAddResource(ResourceType.SERVANTS));
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        RedWarehouse warehouseView;

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);
        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[2].addResources(1);

        warehouseView = warehouse.toView();

        assertFalse(warehouseView.areEmptyDepotsFillableByType(ResourceType.SHIELDS));
        assertFalse(warehouseView.areEmptyDepotsFillableByType(ResourceType.STONES));
        assertTrue(warehouseView.areEmptyDepotsFillableByType(ResourceType.SERVANTS));
        assertTrue(warehouseView.areEmptyDepotsFillableByType(ResourceType.COINS));
    }
}
