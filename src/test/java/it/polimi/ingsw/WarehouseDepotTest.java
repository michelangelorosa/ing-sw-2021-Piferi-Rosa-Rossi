package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.View.ReducedModel.RedWarehouseDepot;
import org.junit.Test;

/**
 * Unit test for WarehouseDepot Class.
 */
public class WarehouseDepotTest {
    WarehouseDepot depot = new WarehouseDepot(3,false);
    WarehouseDepot leaderDepot = new WarehouseDepot(3,true);

    /**
     * Constructor test WarehouseDepot Class.
     */
    @Test
    public void constructorTest() {
        assertSame(ResourceType.NONE, depot.getResourceType());
        assertEquals(3,depot.getMaxResources());
        assertFalse(depot.isFromLeaderCardAbility());
    }

    /**
     * Test for "isEmpty" method in WarehouseDepot Class.
     */
    @Test
    public void isEmptyTest() {
        assertTrue(depot.isEmpty());
        depot.addResources(1);
        assertFalse(depot.isEmpty());
    }

    /**
     * Test for "isFull" method in WarehouseDepot Class.
     */
    @Test
    public void isFullTest() {
        assertFalse(depot.isFull());
        depot.addResources(3);
        assertTrue(depot.isFull());
    }

    /**
     * Test for "addResources" method in WarehouseDepot Class.
     */
    @Test
    public void addResourcesTest() {
        depot.addResources(1);
        assertEquals(1,depot.getStoredResources());
        depot.addResources(2);
        assertEquals(3,depot.getStoredResources());
        depot.addResources(3);
        assertEquals(3,depot.getStoredResources());
    }

    /**
     * Test for "removeResources" method in WarehouseDepot Class.
     */

    @Test
    public void removeResourcesTest() {
        depot.addResources(3);
        depot.setResourceType(ResourceType.SHIELDS);
        leaderDepot.addResources(3);
        leaderDepot.setResourceType(ResourceType.SHIELDS);

        depot.removeResources(1);
        leaderDepot.removeResources(1);

        assertEquals(2, depot.getStoredResources());
        assertEquals(2,leaderDepot.getStoredResources());
        assertSame(ResourceType.SHIELDS, depot.getResourceType());
        assertSame(ResourceType.SHIELDS, leaderDepot.getResourceType());

        depot.removeResources(1);
        leaderDepot.removeResources(1);

        assertEquals(1, depot.getStoredResources());
        assertEquals(1,leaderDepot.getStoredResources());
        assertSame(ResourceType.SHIELDS, depot.getResourceType());
        assertSame(ResourceType.SHIELDS, leaderDepot.getResourceType());

        depot.removeResources(1);
        leaderDepot.removeResources(1);

        assertEquals(0, depot.getStoredResources());
        assertEquals(0,leaderDepot.getStoredResources());
        assertSame(ResourceType.NONE, depot.getResourceType());
        assertSame(ResourceType.SHIELDS, leaderDepot.getResourceType());
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){

        RedWarehouseDepot depotView;
        RedWarehouseDepot depotViewLeader;

        depotView = depot.toView();
        depotViewLeader = leaderDepot.toView();

        assertSame(ResourceType.NONE, depotView.getResourceType());
        assertEquals(3,depotView.getMaxResources());
        assertFalse(depotView.isFromLeaderCardAbility());
        assertTrue(depotViewLeader.isFromLeaderCardAbility());

        depot.addResources(3);
        depot.setResourceType(ResourceType.SHIELDS);

        depotView = depot.toView();

        assertEquals(3, depotView.getStoredResources());
        assertSame(ResourceType.SHIELDS, depotView.getResourceType());
        assertEquals(3, depotView.getStoredResources());

        depot.removeResources(1);

        depotView = depot.toView();

        assertEquals(2, depotView.getStoredResources());
        assertSame(ResourceType.SHIELDS, depotView.getResourceType());
        assertEquals(2, depotView.getStoredResources());
    }
}
