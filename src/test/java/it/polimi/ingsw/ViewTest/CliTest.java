package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.ReducedModel.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class CliTest {

    InputStream sysInBackup = System.in;
    CliController ui = new CliController();
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
    public void numberOfPlayersTest() {
        changeSystemIn("5\n16\n3");
        ui = new CliController();
        assertEquals(3, ui.numberOfPlayers());
    }

    @Test
    public void initialInsertNameTest() {
        changeSystemIn("anthonyTheFirstAndPowerfulHumanBeing\n\n  \npippo");
        ui = new CliController();
        assertEquals("pippo", ui.initialInsertName());
    }

    @Test
    public void waitingForOtherPlayersTest() {
        ui.waitingForPlayers();
    }

    @Test
    public void initialLobbyTest() {
        changeSystemIn("n\n2\n123123123123\n1");
        ui = new CliController();
        assertEquals(4, ui.initialLobby());
    }

    @Test
    public void initialChooseResourcesTest() {
        changeSystemIn("-5\n3\n1\n4\n-1\n3\n2\n1");
        ui = new CliController();
        Action action = ui.initialChooseResources(3);
        assertTrue(action instanceof InitChooseResources);
        HashMap<Integer, ArrayList<ResourceType>> result = ((InitChooseResources)action).getDepotResource();
        assertEquals(ResourceType.COINS, result.get(0).remove(0));
        assertEquals(ResourceType.SERVANTS, result.get(0).remove(0));
        assertEquals(ResourceType.STONES, result.get(2).remove(0));
        assertEquals(0, result.get(1).size());
    }

    @Test
    public void initialChooseLeaderCardsTest() {
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        ResourceStack stack = new ResourceStack(1,2,3,4);
        ArrayList<RedLeaderCard> leaderCards = new ArrayList<>();
        leaderCards.add(new LeaderCard(0, 1, stack, leaderRequirements, Marble.PURPLE));
        leaderCards.add(new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE));
        leaderCards.add(new LeaderCard(2, 1, stack, leaderRequirements, Marble.GREY));
        leaderCards.add(new LeaderCard(3, 1, stack, leaderRequirements, Marble.YELLOW));
        assertEquals(4, leaderCards.size());

        changeSystemIn("0\n1\n0\n1\n2");
        ui = new CliController();
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

        changeSystemIn("a\nb\n1\nc\nd\n4\n1\n3");
        ui = new CliController();

        Action action = ui.actionPicker(game);
        assertTrue(action instanceof MarketChooseRow);
    }

    @Test
    public void activateLeaderCardTest() {
        changeSystemIn("0");
        ui = new CliController();
        assertNull(ui.activateLeaderCard(game));

        changeSystemIn("4\n5\n2");
        ui = new CliController();
        Action action = ui.activateLeaderCard(game);

        assertTrue(action instanceof ActivateLeaderCard);
        assertEquals(1, ((ActivateLeaderCard)action).getLeaderCard());
    }

    @Test
    public void activateProductionTest() {
        ((LeaderCard)game.getMyPlayer().getLeaderCards()[1]).setActive(true);
        changeSystemIn("0");
        ui = new CliController();
        assertNull(ui.activateProduction(game));

        changeSystemIn("y\n0");
        ui = new CliController();
        assertNull(ui.activateProduction(game));

        changeSystemIn("y\ny\n0");
        ui = new CliController();
        assertNull(ui.activateProduction(game));

        changeSystemIn("y\ny\ny\nn\ny\nn\ny\ny\n1\n6\n2");
        ui = new CliController();
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
        ui = new CliController();
        Action action = ui.addResource(game);
        assertTrue(action instanceof AddResource);
        assertEquals(2, ((AddResource)action).getDepot());
        assertEquals(ResourceType.SERVANTS, ((AddResource)action).getResourceType());
    }

    @Test
    public void buyCardTest() {
        changeSystemIn("0");
        ui = new CliController();
        assertNull(ui.buyCard(game));

        changeSystemIn("-1\n1\n1\n-1\n4\n1\n1\n5\n1\n1");
        ui = new CliController();
        Action action = ui.buyCard(game);
        assertTrue(action instanceof BuyCard);
        assertEquals(0, ((BuyCard)action).getRow());
        assertEquals(0, ((BuyCard)action).getColumn());
    }

    @Test
    public void chooseCardSlotTest() {
        changeSystemIn("0\n1");
        ui = new CliController();
        Action action = ui.chooseCardSlot(game);
        assertTrue(action instanceof ChooseCardSlot);
        assertEquals(0, ((ChooseCardSlot)action).getCardSlot());
    }

    @Test
    public void chooseLeaderCardTest() {
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        ResourceStack stack = new ResourceStack(1,1,1,1);
        RedLeaderCard whiteMarble = new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE);
        game.getMyPlayer().getLeaderCards()[0] = whiteMarble;
        ((LeaderCard)game.getMyPlayer().getLeaderCards()[0]).setActive(true);

        changeSystemIn("0\n2\n1");
        ui = new CliController();
        Action action = ui.chooseLeaderCard(game);
        assertTrue(action instanceof  ChooseLeaderCard);
        assertEquals(0, ((ChooseLeaderCard)action).getLeaderCard());
    }

    @Test
    public void chooseProductionOutput() {
        changeSystemIn("2\n1\n");
        ui = new CliController();
        Action action = ui.chooseProductionOutput(game);
        assertTrue(action instanceof ChooseProductionOutput);
        assertFalse(((ChooseProductionOutput)action).isFirstLeaderCard());
        assertFalse(((ChooseProductionOutput)action).isSecondLeaderCard());
        assertFalse(((ChooseProductionOutput)action).isBasicProduction());

        ResourceStack resourceStack = new ResourceStack(0,0,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0);

        LeaderCard leaderCard1 = new LeaderCard(1, 23, resourceStack, leaderRequirements, resourceStack, 3, 2);
        LeaderCard leaderCard2 = new LeaderCard(2, 23, resourceStack, leaderRequirements, resourceStack, 1, 2);

        CommonViewTestMethods.giveLeaderCards(game.getCurrentPlayer(), leaderCard1, leaderCard2);

        class CliExtender extends CliController {
            CliExtender() {
                super();
            }

            public void boolSetter(boolean first, boolean second, boolean basic) {
                this.testSetter(first, second, basic);
            }
        }

        changeSystemIn("0\n1\n2\n3\n0\n1\n0\n4");
        ui = new CliExtender();
        ((CliExtender)ui).boolSetter(true, true, true);
        action = ui.chooseProductionOutput(game);

        assertTrue(action instanceof ChooseProductionOutput);
        assertTrue(((ChooseProductionOutput)action).isFirstLeaderCard());
        assertTrue(((ChooseProductionOutput)action).isSecondLeaderCard());
        assertTrue(((ChooseProductionOutput)action).isBasicProduction());
        assertEquals(ResourceType.SHIELDS, ((ChooseProductionOutput)action).getFirstLeaderCardOutput().get(0));
        assertEquals(ResourceType.SERVANTS, ((ChooseProductionOutput)action).getFirstLeaderCardOutput().get(1));
        assertEquals(ResourceType.COINS, ((ChooseProductionOutput)action).getFirstLeaderCardOutput().get(2));

        assertEquals(ResourceType.SHIELDS, ((ChooseProductionOutput)action).getSecondLeaderCardOutput().get(0));

        assertEquals(ResourceType.STONES, ((ChooseProductionOutput)action).getBasicProductionOutput().get(0));
    }

    @Test
    public void marketChooseRowTest() {
        changeSystemIn("-1\n3\n1\n-1\n4\n1");
        ui = new CliController();
        Action action = ui.marketChooseRow(game);
        assertTrue(action instanceof MarketChooseRow);
        assertTrue(((MarketChooseRow)action).isRow());
        assertEquals(0, ((MarketChooseRow)action).getRowOrColumnNumber());

        changeSystemIn("-1\n3\n2\n-1\n5\n4");
        ui = new CliController();
        action = ui.marketChooseRow(game);
        assertTrue(action instanceof MarketChooseRow);
        assertFalse(((MarketChooseRow)action).isRow());
        assertEquals(3, ((MarketChooseRow)action).getRowOrColumnNumber());
    }

    @Test
    public void payResourceTest() {
        game.getMyPlayer().setTemporaryResources(new ResourceStack(1,0,0,0));

        changeSystemIn("-1\n3\n1\n2");
        ui = new CliController();
        PayResourceBuyCard action = ui.payResourceBuyCard(game);
        assertNotNull(action);
        assertEquals(1, action.getDepot());
        assertNull(action.getResourceType());

        changeSystemIn("-1\n3\n2\n2");
        ui = new CliController();
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
        ui = new CliController();
        Action action = ui.switchDepot(game);
        assertTrue(action instanceof SwitchDepot);
        assertEquals(0, ((SwitchDepot)action).getFirstDepot());
        assertEquals(1, ((SwitchDepot)action).getSecondDepot());
    }



}
