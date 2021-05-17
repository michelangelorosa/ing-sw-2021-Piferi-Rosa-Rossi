package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.UserInterface;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class CliTest {

    InputStream sysInBackup = System.in;
    UserInterface ui = new Cli();
    Game game = CommonViewTestMethods.gameCreator();

    private static void changeSystemIn(String s) {
        ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
        System.setIn(in);
    }

    @Test
    public void initTest() {
        changeSystemIn("123123\n22222\n127.0.0.1\n1001010\n127.0.0.1\n22222");
        ArrayList<Object> objects = ui.init();
        assertEquals("127.0.0.1", objects.get(0));
        assertEquals(22222, objects.get(1));
        System.setIn(sysInBackup);
    }

    @Test
    public void initialChooseLeaderCardsTest() {
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        ResourceStack stack = new ResourceStack(1,2,3,4);
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(0, 1, stack, leaderRequirements, Marble.PURPLE));
        leaderCards.add(new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE));
        leaderCards.add(new LeaderCard(2, 1, stack, leaderRequirements, Marble.GREY));
        leaderCards.add(new LeaderCard(3, 1, stack, leaderRequirements, Marble.YELLOW));
        assertEquals(4, leaderCards.size());

        changeSystemIn("0\n1\n0\n1\n2");
        ui = new Cli();
        Action action = ui.initialChooseLeaderCards(leaderCards);

        assertTrue(action instanceof InitChooseLeaderCards);

        assertEquals(leaderCards.get(0), ((InitChooseLeaderCards)action).getLeaderCard1());
        assertEquals(leaderCards.get(1), ((InitChooseLeaderCards)action).getLeaderCard2());
    }

    @Test
    public void actionPickerTest() {
        game.getPlayers().get(0).getPossibleActions().add(ActionType.BUY_CARD);
        game.getPlayers().get(0).getPossibleActions().add(ActionType.ACTIVATE_PRODUCTION);
        game.getPlayers().get(0).getPossibleActions().add(ActionType.ACTIVATE_LEADERCARD);
        game.getPlayers().get(0).getPossibleActions().add(ActionType.MARKET_CHOOSE_ROW);


    }

    @Test
    public void activateLeaderCardTest() {
        changeSystemIn("0");
        ui = new Cli();
        assertNull(ui.activateLeaderCard(game));

        changeSystemIn("4\n5\n2");
        ui = new Cli();
        Action action = ui.activateLeaderCard(game);

        assertTrue(action instanceof ActivateLeaderCard);
        assertEquals(1, ((ActivateLeaderCard)action).getLeaderCard());
    }

    @Test
    public void activateProductionTest() {
        game.getMyPlayer().getLeaderCards()[1].setActive(true);
        changeSystemIn("0");
        ui = new Cli();
        assertNull(ui.activateProduction(game));

        changeSystemIn("y\n0");
        ui = new Cli();
        assertNull(ui.activateProduction(game));

        changeSystemIn("y\ny\n0");
        ui = new Cli();
        assertNull(ui.activateProduction(game));

        changeSystemIn("y\ny\ny\nn\ny\nn\ny\ny\n1\n6\n2");
        ui = new Cli();
        Action action = ui.activateProduction(game);
        assertTrue(action instanceof ActivateProduction);
        assertTrue(((ActivateProduction)action).isFirstSlot());
        assertTrue(((ActivateProduction)action).isSecondSlot());
        assertFalse(((ActivateProduction)action).isThirdSlot());
        assertFalse(((ActivateProduction)action).isFirstLeaderCard());
        assertTrue(((ActivateProduction)action).isSecondLeaderCard());
        assertTrue(((ActivateProduction)action).isBasicProduction());
        assertEquals(ResourceType.SHIELDS, ((ActivateProduction)action).getBasicProductionInputs().get(0));
        assertEquals(ResourceType.SERVANTS, ((ActivateProduction)action).getBasicProductionInputs().get(1));
    }

    @Test
    public void addResourceTest() {
        game.getMyPlayer().setTemporaryResources(new ResourceStack(0,1,0,0));

        changeSystemIn("5\n2\n3\n2");
        ui = new Cli();
        Action action = ui.addResource(game);
        assertTrue(action instanceof AddResource);
        assertEquals(2, ((AddResource)action).getDepot());
        assertEquals(ResourceType.SERVANTS, ((AddResource)action).getResourceType());
    }

    @Test
    public void buyCardTest() {
        changeSystemIn("0");
        ui = new Cli();
        assertNull(ui.buyCard(game));

        changeSystemIn("-1\n1\n1\n-1\n4\n1\n1\n5\n1\n1");
        ui = new Cli();
        Action action = ui.buyCard(game);
        assertTrue(action instanceof BuyCard);
        assertEquals(0, ((BuyCard)action).getRow());
        assertEquals(0, ((BuyCard)action).getColumn());
    }

    @Test
    public void chooseCardSlotTest() {
        changeSystemIn("0\n1");
        ui = new Cli();
        Action action = ui.chooseCardSlot(game);
        assertTrue(action instanceof ChooseCardSlot);
        assertEquals(0, ((ChooseCardSlot)action).getCardSlot());
    }

    @Test
    public void chooseLeaderCardTest() {
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        ResourceStack stack = new ResourceStack(1,1,1,1);
        LeaderCard whiteMarble = new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE);
        game.getMyPlayer().getLeaderCards()[0] = whiteMarble;
        game.getMyPlayer().getLeaderCards()[0].setActive(true);

        changeSystemIn("0\n2\n1");
        ui = new Cli();
        Action action = ui.chooseLeaderCard(game);
        assertTrue(action instanceof  ChooseLeaderCard);
        assertEquals(0, ((ChooseLeaderCard)action).getLeaderCard());
    }

    @Test
    public void chooseProductionOutput() {
        changeSystemIn("2\n1\n");
        ui = new Cli();
        Action action = ui.chooseProductionOutput(game);
        assertTrue(action instanceof ChooseProductionOutput);
        assertFalse(((ChooseProductionOutput)action).isFirstLeaderCard());
        assertFalse(((ChooseProductionOutput)action).isSecondLeaderCard());
        assertFalse(((ChooseProductionOutput)action).isBasicProduction());
    }

    @Test
    public void marketChooseRowTest() {
        changeSystemIn("-1\n3\n1\n-1\n4\n1");
        ui = new Cli();
        Action action = ui.marketChooseRow(game);
        assertTrue(action instanceof MarketChooseRow);
        assertTrue(((MarketChooseRow)action).isRow());
        assertEquals(0, ((MarketChooseRow)action).getRowOrColumnNumber());

        changeSystemIn("-1\n3\n2\n-1\n5\n4");
        ui = new Cli();
        action = ui.marketChooseRow(game);
        assertTrue(action instanceof MarketChooseRow);
        assertFalse(((MarketChooseRow)action).isRow());
        assertEquals(3, ((MarketChooseRow)action).getRowOrColumnNumber());
    }

    @Test
    public void payResourceTest() {
        game.getMyPlayer().setTemporaryResources(new ResourceStack(1,0,0,0));

        changeSystemIn("-1\n3\n1\n2");
        ui = new Cli();
        PayResourceBuyCard action = ui.payResourceBuyCard(game);
        assertNotNull(action);
        assertEquals(1, action.getDepot());
        assertNull(action.getResourceType());

        changeSystemIn("-1\n3\n2\n2");
        ui = new Cli();
        PayResourceProduction action2 = ui.payResourceProduction(game);
        assertNotNull(action2);
        assertEquals(0, action2.getDepot());
        assertEquals(ResourceType.SERVANTS, action2.getResourceType());
    }

    @Test
    public void resetWarehouseTest() {
        Action action = ui.resetWarehouse();
        assertTrue(action instanceof ResetWarehouse);
    }

    @Test
    public void switchDepot() {
        changeSystemIn("-1\n6\n2\n2\n1\n2");
        ui = new Cli();
        Action action = ui.switchDepot(game);
        assertTrue(action instanceof SwitchDepot);
        assertEquals(0, ((SwitchDepot)action).getFirstDepot());
        assertEquals(1, ((SwitchDepot)action).getSecondDepot());
    }



}
