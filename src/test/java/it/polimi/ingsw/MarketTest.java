package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

/**
 * Unit test for Market Class.
 */
public class MarketTest {
    Market market = new Market();
    FaithTrack faithTrack = new FaithTrack();
    Player player = new Player("antonio", 1, false);

    /**
     * Constructor and "toString" method tests.
     */
    @Test
    public void constructorTest() {
        System.out.println(market.toString());
    }

    /**
     * Test for "reset" method in Market Class.
     */
    @Test
    public void resetTest() {
        System.out.println(market.toString());
        market.reset();
        System.out.println(market.toString());
    }

    /**
     * Test for "rowToResources" method in Market Class.
     */
    @Test
    public void rowToResourcesTest() {
        market.testMethod();

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        ResourceStack stack = market.rowToResources(1, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(2, stack.getResource(ResourceType.STONES));

        assertEquals(0, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.rowToResources(0, player, null);

        assertEquals(2, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(1, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.rowToResources(2, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(1, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(3, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());



        stack = market.rowToResources(1, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));

        assertEquals(3, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.rowToResources(0, player, null);
        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        assertEquals(1, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        stack = market.rowToResources(2, player, null);

        assertEquals(1, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(6, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        Market market2 = new Market();
        market2.testMethod();

        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        LeaderCard whiteMarbleOne = new LeaderCard(1, 1, stack, leaderRequirements, Marble.YELLOW);
        LeaderCard whiteMarbleTwo = new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE);

        whiteMarbleOne.setActive(true);

        stack = market2.rowToResources(1, player, whiteMarbleOne);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(2, stack.getResource(ResourceType.COINS));
        assertEquals(2, stack.getResource(ResourceType.STONES));

        whiteMarbleTwo.setActive(true);

        stack = market2.rowToResources(1, player, whiteMarbleTwo);

        assertEquals(3, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));
    }

    /**
     * Test for "columnToResources" method in Market Class.
     */
    @Test
    public void columnToResourcesTest() {
        market.testMethod();

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        ResourceStack stack = market.columnToResources(1, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));

        assertEquals(1, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.columnToResources(0, player, null);

        assertEquals(1, stack.getResource(ResourceType.SHIELDS));
        assertEquals(1, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));

        assertEquals(1, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.columnToResources(2, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(2, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.columnToResources(3, player, null);

        assertEquals(1, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(3, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());



        stack = market.columnToResources(1, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.columnToResources(0, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(1, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(1, stack.getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.columnToResources(2, player, null);

        assertEquals(1, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(1, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        stack = market.columnToResources(3, player, null);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        assertEquals(6, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());


        // ---------- TEST WITH ACTIVE LEADER CARD ---------- //
        Market market2 = new Market();
        market2.testMethod();

        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        LeaderCard whiteMarbleOne = new LeaderCard(1, 1, stack, leaderRequirements, Marble.YELLOW);
        LeaderCard whiteMarbleTwo = new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE);

        whiteMarbleOne.setActive(true);

        stack = market2.columnToResources(2, player, whiteMarbleOne);

        assertEquals(0, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(2, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

        whiteMarbleTwo.setActive(true);

        stack = market2.columnToResources(3, player, whiteMarbleTwo);

        assertEquals(2, stack.getResource(ResourceType.SHIELDS));
        assertEquals(0, stack.getResource(ResourceType.SERVANTS));
        assertEquals(0, stack.getResource(ResourceType.COINS));
        assertEquals(0, stack.getResource(ResourceType.STONES));

    }
}
