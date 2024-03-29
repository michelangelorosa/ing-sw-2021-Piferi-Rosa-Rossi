package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for ResourceManager Class.
 */
public class ResourceManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
     * Setter and Getter test for ResourceManager Class.
     */
    @Test
    public void setterGetterTest() {
        ResourceStack resourceStack = new ResourceStack(1,2,3,4);
        resourceManager.setTemporaryResourcesToPay(resourceStack);
        resourceManager.setTemporaryWhiteMarbles(4);

        assertEquals(1, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(2, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(3, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(4, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(4, resourceManager.getTemporaryWhiteMarbles());

        for(int i = 0; i < 3; i ++) {
            assertEquals(ResourceType.NONE, resourceManager.getWarehouseDepots()[i].getResourceType());
            assertEquals(0, resourceManager.getWarehouseDepots()[i].getStoredResources());
        }

        assertEquals(ResourceType.NONE, resourceManager.getExtraWarehouseDepotOne().getResourceType());
        assertEquals(0, resourceManager.getExtraWarehouseDepotOne().getStoredResources());
        assertFalse(resourceManager.isExtraDepotOneActive());

        assertEquals(ResourceType.NONE, resourceManager.getExtraWarehouseDepotTwo().getResourceType());
        assertEquals(0, resourceManager.getExtraWarehouseDepotTwo().getStoredResources());
        assertFalse(resourceManager.isExtraDepotTwoActive());

    }

    /**
     * Test for "addWhiteMarble" and "removeWhiteMarble" method in ResourceManager Class.
     */
    @Test
    public void addRemoveWhiteMarbleTest() {
        assertEquals(0, resourceManager.getTemporaryWhiteMarbles());
        resourceManager.addWhiteMarble();
        assertEquals(1, resourceManager.getTemporaryWhiteMarbles());
        resourceManager.addWhiteMarble();
        assertEquals(2, resourceManager.getTemporaryWhiteMarbles());
        resourceManager.addWhiteMarble();
        assertEquals(3, resourceManager.getTemporaryWhiteMarbles());
        resourceManager.removeWhiteMarble();
        assertEquals(2, resourceManager.getTemporaryWhiteMarbles());
        resourceManager.removeWhiteMarble();
        assertEquals(1, resourceManager.getTemporaryWhiteMarbles());
        resourceManager.removeWhiteMarble();
        assertEquals(0, resourceManager.getTemporaryWhiteMarbles());
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("TemporaryWhiteMarbles number is already 0.");
        resourceManager.removeWhiteMarble();
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
     * Test to check if the method "canAddMarket()" works properly in ResourceManager Class
     */
    @Test
    public void canAddMarketResources() {
        resourceManager.reset();
        assertTrue(resourceManager.canAddMarketResources());
        resourceManager.addMarketResourcesByType(3, ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.COINS, resourceManager.getWarehouseDepots()[1]);
        resourceManager.addMarketResourcesByType(1, ResourceType.SERVANTS, resourceManager.getWarehouseDepots()[2]);
        assertFalse(resourceManager.canAddMarketResources());

        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.COINS);
        assertTrue(resourceManager.canAddMarketResources());
    }

    /**
     * Test to check if the method "canAddDepot()" works properly in ResourceManager Class
     */
    @Test
    public void canAddToDepot() {
        resourceManager.addMarketResourcesByType(3, ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0]);
        assertFalse(resourceManager.canAddToDepot(ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0]));
        assertFalse(resourceManager.canAddToDepot(ResourceType.COINS, resourceManager.getWarehouseDepots()[0]));
        assertFalse(resourceManager.canAddToDepot(ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[1]));
        assertFalse(resourceManager.canAddToDepot(ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[2]));

        assertTrue(resourceManager.canAddToDepot(ResourceType.COINS, resourceManager.getWarehouseDepots()[1]));
    }

    /**
     * Test to check if the method "addOneResourceToDepot()" works properly in ResourceManager Class
     */
    @Test
    public void addOneResourceToDepotTest() {
        resourceManager.setTemporaryResourcesToPay(new ResourceStack(2, 1, 0, 0));
        resourceManager.addOneResourceToDepot(ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0]);
        assertEquals(1, resourceManager.getWarehouseDepots()[0].getStoredResources());
        assertEquals(ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0].getResourceType());
        assertEquals(1, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
    }

    /**
     * Test to check if the method "hasPayed()" works properly in ResourceManager Class
     */
    @Test
    public void hasPayedTest() {
        assertTrue(resourceManager.hasPayed());
        ResourceStack resource = new ResourceStack(0,0,0,1);
        resourceManager.setTemporaryResourcesToPay(resource);
        assertFalse(resourceManager.hasPayed());
    }

    /**
     * Test to check if the method "resourceIsNeededToPay()" works properly in ResourceManager Class
     */
    @Test
    public void resourceIsNeededToPayTest() {
        ResourceStack resource = new ResourceStack(0,0,1,1);
        resourceManager.setTemporaryResourcesToPay(resource);

        assertFalse(resourceManager.resourceIsNeededToPay(ResourceType.SHIELDS));
        assertFalse(resourceManager.resourceIsNeededToPay(ResourceType.SERVANTS));
        assertTrue(resourceManager.resourceIsNeededToPay(ResourceType.COINS));
        assertTrue(resourceManager.resourceIsNeededToPay(ResourceType.STONES));
    }

    /**
     * Test to check if the method "payOneResource()" works properly in ResourceManager Class
     */
    @Test
    public void payOneResourceTest() {
        ResourceStack resource = new ResourceStack(1,0,1,1);
        resourceManager.setTemporaryResourcesToPay(resource);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0]);
        resourceManager.addProductionResources(resource);

        resourceManager.payOneResourceWarehouse(resourceManager.getWarehouseDepots()[0]);

        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(1, resourceManager.getWarehouseDepots()[0].getStoredResources());

        resourceManager.payOneResourceStrongbox(ResourceType.COINS);
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getStrongbox().getStoredResources().getResource(ResourceType.COINS));

    }

    /**
     * Test for "cardIsBuyable" method in ResourceManager Class.
     */
    @Test
    public void cardIsBuyableTest() {
        // ---------- CREATION OF LEADER CARDS AND DEVELOPMENT CARDS TO TEST ---------- //
        ResourceStack discount1 = new ResourceStack(1,1,0,0);
        ResourceStack discount2 = new ResourceStack(0,0,1,1);

        LeaderRequirements leaderRequirements;

        LeaderCard discountCardOne;
        LeaderCard discountCardTwo;
        LeaderCard extraDepotOne;
        LeaderCard whiteMarbleOne;

        LeaderCard[] leaderCards;

        leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        discountCardOne = new LeaderCard(1, 1, discount1, leaderRequirements, discount1);
        discountCardTwo = new LeaderCard(2, 1, discount2, leaderRequirements, discount2);
        extraDepotOne = new LeaderCard(3, 3, discount1, leaderRequirements, ResourceType.COINS);
        whiteMarbleOne = new LeaderCard(4, 4, discount1, leaderRequirements, Marble.BLUE);

        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 1, discount1, discount1, discount1, 1);
        DevelopmentCard card2 = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 1, discount2, discount2, discount2, 1);

        ResourceStack cost = new ResourceStack(0, 0, 0, 3);
        DevelopmentCard card3 = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 1, cost, discount1, discount1, 1);


        // ---------- SIMPLE TEST WITH NO DISCOUNT ABILITY LEADER CARDS ---------- //
        leaderCards = new LeaderCard[2];
        leaderCards[0] = extraDepotOne;
        leaderCards[1] = whiteMarbleOne;

        ResourceStack resourceStack = new ResourceStack(5, 2, 1, 3);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(3, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);

        resourceManager.addProductionResources(resourceStack);

        assertTrue(resourceManager.cardIsBuyable(card1, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card2, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card3, leaderCards));

        assertTrue(resourceManager.cardIsBuyable(card1, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card2, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card3, leaderCards));

        resourceStack = new ResourceStack(0, 0, 0, 0);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.STONES, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.addMarketResourcesByType(1, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[2]);

        resourceManager.addProductionResources(resourceStack);

        leaderCards[0].setActive(true);
        leaderCards[1].setActive(true);

        assertFalse(resourceManager.cardIsBuyable(card3, leaderCards));

        leaderCards = new LeaderCard[2];
        leaderCards[0] = discountCardTwo;
        leaderCards[1] = whiteMarbleOne;

        assertFalse(resourceManager.cardIsBuyable(card3, leaderCards));

        // ---------- TEST WITH ONE ACTIVE DISCOUNT ABILITY LEADER CARD ---------- //
        leaderCards[0].setActive(true);

        assertTrue(leaderCards[0].isActive());

        assertTrue(resourceManager.cardIsBuyable(card3, leaderCards));

        leaderCards = new LeaderCard[2];
        leaderCards[0] = discountCardTwo;
        leaderCards[1] = discountCardOne;

        leaderCards[0].setActive(false);
        leaderCards[1].setActive(false);

        assertFalse(resourceManager.cardIsBuyable(card1, leaderCards));
        assertFalse(resourceManager.cardIsBuyable(card3, leaderCards));

        leaderCards[0].setActive(true);

        assertFalse(resourceManager.cardIsBuyable(card1, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card3, leaderCards));

        // ---------- TEST WITH ONE ACTIVE DISCOUNT ABILITY LEADER CARD ---------- //
        leaderCards[1].setActive(true);

        assertTrue(resourceManager.cardIsBuyable(card1, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card3, leaderCards));

        // ---------- TEST WITH DEVELOPMENT CARDS DISCOUNTED DOWN TO ZERO COST ---------- //
        resourceStack = new ResourceStack(0, 0, 0, 0);
        resourceManager.reset();

        resourceManager.addProductionResources(resourceStack);

        assertTrue(resourceManager.cardIsBuyable(card1, leaderCards));
        assertTrue(resourceManager.cardIsBuyable(card2, leaderCards));
    }

    /**
     * Test to check if the method "hasResourceToActivateCard()" works properly in ResourceManager Class
     */
    @Test
    public void hasResourcesToActivateLeaderCardTest() {
        ResourceStack cost = new ResourceStack(1,1,1,1);
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);

        LeaderCard leaderCard = new LeaderCard(1, 1, cost, leaderRequirements, cost);
        ResourceStack resourceStack = new ResourceStack(5, 2, 1, 3);
        resourceManager.reset();
        assertFalse(resourceManager.hasResourcesToActivateLeaderCard(leaderCard));

        resourceManager.addMarketResourcesByType(3, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);

        assertFalse(resourceManager.hasResourcesToActivateLeaderCard(leaderCard));

        resourceManager.addProductionResources(resourceStack);

        assertTrue(resourceManager.hasResourcesToActivateLeaderCard(leaderCard));
    }

    /**
     * Test for "activateLeaderDepot" method in ResourceManager Class.
     */
    @Test
    public void activateLeaderDepotTest() {
        ResourceStack stack = new ResourceStack(0,0,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        LeaderCard extraDepot1 = new LeaderCard(1, 1, stack, leaderRequirements, ResourceType.COINS);
        LeaderCard extraDepot2 = new LeaderCard(1, 1, stack, leaderRequirements, ResourceType.SHIELDS);
        LeaderCard notExtraDepot = new LeaderCard(1, 1, stack, leaderRequirements, Marble.RED);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {resourceManager.activateLeaderDepot(extraDepot1);});
        assertEquals("EXTRADEPOT LeaderAbility has to be active to activate extra depot", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {resourceManager.activateLeaderDepot(extraDepot2);});
        assertEquals("EXTRADEPOT LeaderAbility has to be active to activate extra depot", e.getMessage());

        assertFalse(resourceManager.getWarehouse().isExtraWarehouseDepot1IsActive());
        assertFalse(resourceManager.getWarehouse().isExtraWarehouseDepot2IsActive());

        extraDepot1.setActive(true);
        extraDepot2.setActive(true);

        resourceManager.activateLeaderDepot(extraDepot1);
        resourceManager.activateLeaderDepot(extraDepot2);

        assertTrue(resourceManager.getWarehouse().isExtraWarehouseDepot1IsActive());
        assertTrue(resourceManager.getWarehouse().isExtraWarehouseDepot2IsActive());

        assertEquals(ResourceType.COINS ,resourceManager.getExtraWarehouseDepotOne().getResourceType());
        assertEquals(ResourceType.SHIELDS ,resourceManager.getExtraWarehouseDepotTwo().getResourceType());

        e = assertThrows(IllegalArgumentException.class, () -> {resourceManager.activateLeaderDepot(notExtraDepot);});
        assertEquals("Needed EXTRADEPOT LeaderAbility to activate extra depot (was WHITEMARBLE instead)", e.getMessage());
    }

}
