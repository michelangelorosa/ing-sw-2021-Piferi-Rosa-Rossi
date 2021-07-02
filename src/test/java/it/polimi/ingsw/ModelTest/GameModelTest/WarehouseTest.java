package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Exceptions.ModelException;
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

    /**
     * Test to check if the method "canAddToDepot()" works properly in Warehouse Class
     */
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

    /**
     * Test to check if the method "removeResourceFromDepot()" works properly in Warehouse Class
     */
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
    public void resourceTypeFitsTest() throws ModelException {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);
        assertFalse(warehouse.resourceTypeFits(ResourceType.SHIELDS));
        assertFalse(warehouse.resourceTypeFits(ResourceType.SERVANTS));
        assertFalse(warehouse.resourceTypeFits(ResourceType.STONES));

        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);
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
    public void emptyDepotExistsTest() throws ModelException {
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);
        warehouse.getWarehouseDepots()[0].addResources(3);
        warehouse.getWarehouseDepots()[1].addResources(2);
        warehouse.getWarehouseDepots()[2].addResources(1);

        assertFalse(warehouse.emptyDepotExists());

        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[2]);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[1]);
        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[1]);

        assertTrue(warehouse.emptyDepotExists());

        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);
        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);
        warehouse.removeResourceFromDepot(warehouse.getWarehouseDepots()[0]);

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

    /**
     * Test to check if the method "canAddResource()" works properly in Warehouse Class
     */
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

    /**
     * Test to check if methods work properly in Warehouse Class
     */
    @Test
    public void testFiller() {
        warehouse.addResources(3, ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]);
        warehouse.addResources(2, ResourceType.COINS, warehouse.getWarehouseDepots()[1]);
        warehouse.addResources(1, ResourceType.STONES, warehouse.getWarehouseDepots()[2]);
        warehouse.addResources(1, ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]);

        warehouse.activateLeaderDepot(ResourceType.SHIELDS);
        warehouse.activateLeaderDepot(ResourceType.SHIELDS);

        assertFalse(warehouse.canSwitchDepots(warehouse.getWarehouseDepots()[0], warehouse.getWarehouseDepots()[1]));
        assertFalse(warehouse.canSwitchDepots(warehouse.getWarehouseDepots()[1], warehouse.getWarehouseDepots()[0]));

        assertTrue(warehouse.canSwitchDepots(warehouse.getExtraWarehouseDepot1(), warehouse.getExtraWarehouseDepot2()));

        warehouse.getExtraWarehouseDepot2().setResourceType(ResourceType.COINS);
        assertFalse(warehouse.canSwitchDepots(warehouse.getExtraWarehouseDepot1(), warehouse.getExtraWarehouseDepot2()));

        assertTrue(warehouse.canSwitchDepots(warehouse.getExtraWarehouseDepot2(), warehouse.getWarehouseDepots()[1]));
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.NONE);
        warehouse.getWarehouseDepots()[0].setStoredResources(0);
        assertTrue(warehouse.canSwitchDepots(warehouse.getWarehouseDepots()[0], warehouse.getExtraWarehouseDepot1()));

        Warehouse warehouse = this.warehouse.copyWarehouse();
        assertTrue(warehouse.isExtraWarehouseDepot1IsActive());
    }
}
