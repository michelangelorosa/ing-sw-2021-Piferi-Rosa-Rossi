package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.View.ReducedModel.RedMarket;
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

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {market2.rowToResources(10, player);});
        assertEquals("Model: Row index out of bounds.", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {market2.whiteMarbleToResource(player, whiteMarbleOne);});
        assertEquals("No White Marbles to convert.", e.getMessage());

        whiteMarbleTwo.setActive(false);

        player.getBoard().getResourceManager().setTemporaryWhiteMarbles(1);
        e = assertThrows(IllegalArgumentException.class, () -> {market2.whiteMarbleToResource(player, whiteMarbleTwo);});
        assertEquals("Card has to be active to convert White Marble.", e.getMessage());

        LeaderCard leaderCard = new LeaderCard(1, 1, player.getBoard().getResourceManager().getTemporaryResourcesToPay(), leaderRequirements, ResourceType.COINS);
        leaderCard.setActive(true);
        e = assertThrows(IllegalArgumentException.class, () -> {market2.whiteMarbleToResource(player, leaderCard);});
        assertEquals("Card has to be of type WHITEMARBLE to convert White Marble.", e.getMessage());
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

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {market2.columnToResources(10, player);});
        assertEquals("Model: Column index out of bounds.", e.getMessage());

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("No White Marbles to convert.");
        market2.whiteMarbleToResource(player, whiteMarbleTwo);
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        RedMarket marketView;

        marketView = market.toView();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                assertEquals(market.getMarbles()[i][j], marketView.getMarbles()[i][j]);
            }
        }

    }
}
