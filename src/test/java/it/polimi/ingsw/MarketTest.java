package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for Market Class.
 */
public class MarketTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        System.out.println(market.getExtraMarble());
        market.reset();
        System.out.println(market.toString());
        System.out.println(market.getExtraMarble());
    }

    /**
     * Test for "rowToResources" and "whiteMarbleToResource" methods in Market Class.
     */
    @Test
    public void rowToResourcesTest() {
        market.testMethod();

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.rowToResources(1, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(2, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(0, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.rowToResources(0, player);

        assertEquals(2, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(1, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.rowToResources(2, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(3, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());



        market.rowToResources(1, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(3, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.rowToResources(0, player);
        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        market.rowToResources(2, player);

        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(6, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        Market market2 = new Market();
        market2.testMethod();

        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        LeaderCard whiteMarbleOne = new LeaderCard(1, 1, player.getBoard().getResourceManager().getTemporaryResourcesToPay(), leaderRequirements, Marble.YELLOW);
        LeaderCard whiteMarbleTwo = new LeaderCard(1, 1, player.getBoard().getResourceManager().getTemporaryResourcesToPay(), leaderRequirements, Marble.BLUE);

        whiteMarbleOne.setActive(true);

        market2.rowToResources(1, player);

        while(player.getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
            market2.whiteMarbleToResource(player, whiteMarbleOne);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(2, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(2, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        whiteMarbleTwo.setActive(true);

        market2.rowToResources(1, player);
        while(player.getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
            market2.whiteMarbleToResource(player, whiteMarbleTwo);

        assertEquals(3, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("No White Marbles to convert.");
        market2.whiteMarbleToResource(player, whiteMarbleTwo);
    }

    /**
     * Test for "columnToResources" and "whiteMarbleToResource" methods in Market Class.
     */
    @Test
    public void columnToResourcesTest() {
        market.testMethod();

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(1, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(1, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(0, player);

        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(1, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(2, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(2, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(3, player);

        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(3, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());



        market.columnToResources(1, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(0, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(2, player);

        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(1, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(4, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());

        market.columnToResources(3, player);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        assertEquals(6, player.getFaithTrackPosition());

        System.out.println(market.toString()+"   "+market.getExtraMarble()+System.lineSeparator());


        // ---------- TEST WITH ACTIVE LEADER CARD ---------- //
        Market market2 = new Market();
        market2.testMethod();

        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        LeaderCard whiteMarbleOne = new LeaderCard(1, 1, player.getBoard().getResourceManager().getTemporaryResourcesToPay(), leaderRequirements, Marble.YELLOW);
        LeaderCard whiteMarbleTwo = new LeaderCard(1, 1, player.getBoard().getResourceManager().getTemporaryResourcesToPay(), leaderRequirements, Marble.BLUE);

        whiteMarbleOne.setActive(true);

        market2.columnToResources(2, player);

        while(player.getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
            market2.whiteMarbleToResource(player, whiteMarbleOne);

        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(2, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        whiteMarbleTwo.setActive(true);

        market2.columnToResources(3, player);

        while(player.getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
            market2.whiteMarbleToResource(player, whiteMarbleTwo);

        assertEquals(2, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, player.getBoard().getResourceManager().getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("No White Marbles to convert.");
        market2.whiteMarbleToResource(player, whiteMarbleTwo);
    }
}
