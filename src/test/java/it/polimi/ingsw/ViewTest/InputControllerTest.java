package it.polimi.ingsw.ViewTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.View.InputController;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.Model.ResourceType;


import it.polimi.ingsw.View.ReducedModel.Enums.Marble;

import org.junit.Test;

import java.util.ArrayList;

/**
 * JUnit Test for InputController Class.
 */
public class InputControllerTest {

    /**
     * Test for checkActivateLeaderCard Method in InputController Class.
     */
    @Test
    public void checkActivateLeaderCardTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(0);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(1);

        assertTrue(InputController.checkActivateLeaderCard(0, game));
        assertTrue(InputController.checkActivateLeaderCard(1, game));

        game.getPlayers().get(0).getLeaderCards()[0].setActive(true);
        game.getPlayers().get(0).getLeaderCards()[1].setActive(true);

        assertFalse(InputController.checkActivateLeaderCard(0, game));
        assertEquals("Leader Card already active!", InputController.getError());
        assertFalse(InputController.checkActivateLeaderCard(1, game));
        assertEquals("Leader Card already active!", InputController.getError());

        assertFalse(InputController.checkActivateLeaderCard(-1, game));
        assertEquals("Please insert a valid number (1, 2 or 0 to exit)", InputController.getError());
        assertFalse(InputController.checkActivateLeaderCard(-1, game));
        assertEquals("Please insert a valid number (1, 2 or 0 to exit)", InputController.getError());

        assertFalse(InputController.checkActivateLeaderCard(2, game));
        assertEquals("Please insert a valid number (1, 2 or 0 to exit)", InputController.getError());
        assertFalse(InputController.checkActivateLeaderCard(2, game));
        assertEquals("Please insert a valid number (1, 2 or 0 to exit)", InputController.getError());
    }

    /**
     * Test for checkActivateProduction Method in InputController Class.
     */
    @Test
    public void checkActivateProductionCheckTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(0);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(1);

        ArrayList<ResourceType> inputs = new ArrayList<>();
        inputs.add(ResourceType.SHIELDS);
        inputs.add(ResourceType.SHIELDS);
        inputs.add(ResourceType.NONE);

        assertFalse(InputController.checkActivateProduction(true, inputs, false, false, game));
        assertEquals("At least one resources was of type NONE!", InputController.getError());

        inputs.set(2, ResourceType.SHIELDS);
        assertFalse(InputController.checkActivateProduction(true, inputs, false, false, game));
        assertEquals("Number of Basic Production inputs should be 2", InputController.getError());

        assertFalse(InputController.checkActivateProduction(false, inputs, true, false, game));
        assertEquals("(First) Leader Card needs to be of \"PRODUCTION POWER\" type to be used in Production!", InputController.getError());

        assertFalse(InputController.checkActivateProduction(false, inputs, false, true, game));
        assertEquals("(Second) Leader Card needs to be of \"PRODUCTION POWER\" type to be used in Production!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(4);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(5);

        assertFalse(InputController.checkActivateProduction(false, inputs, true, false, game));
        assertEquals("(First) Leader Card is not active!", InputController.getError());

        assertFalse(InputController.checkActivateProduction(false, inputs, false, true, game));
        assertEquals("(Second) Leader Card is not active!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[0].setActive(true);

        assertFalse(InputController.checkActivateProduction(false, inputs, true, true, game));
        assertEquals("(Second) Leader Card is not active!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[1].setActive(true);

        assertTrue(InputController.checkActivateProduction(false, inputs, true, true, game));
    }

    /**
     * Test for checkAddResource Method in InputController Class.
     */
    @Test
    public void checkAddResourceTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");
        game.getMyPlayer().setTemporaryResources(new ResourceStack(5, 5, 5, 5));

        assertFalse(InputController.checkAddResource(-1, ResourceType.COINS, game));
        assertEquals("Pick a number from 0 to 4", InputController.getError());

        assertFalse(InputController.checkAddResource(5, ResourceType.COINS, game));
        assertEquals("Pick a number from 0 to 4", InputController.getError());

        assertFalse(InputController.checkAddResource(0, ResourceType.NONE, game));
        assertEquals("Can't pick resource of type NONE!", InputController.getError());

        assertTrue(InputController.checkAddResource(0, ResourceType.SHIELDS, game));

        assertFalse(InputController.checkAddResource(3, ResourceType.SHIELDS, game));
        assertEquals("Extra depots are not active!", InputController.getError());

        assertFalse(InputController.checkAddResource(4, ResourceType.SHIELDS, game));
        assertEquals("Extra depot 2 is not active!", InputController.getError());

        game.getPlayers().get(0).getWarehouse().setExtraWarehouseDepot1IsActive(true);
        assertTrue(InputController.checkAddResource(3, ResourceType.STONES, game));
        assertFalse(InputController.checkAddResource(4, ResourceType.SHIELDS, game));
        assertEquals("Extra depot 2 is not active!", InputController.getError());

        game.getPlayers().get(0).getWarehouse().setExtraWarehouseDepot2IsActive(true);

        assertTrue(InputController.checkAddResource(4, ResourceType.SHIELDS, game));
    }

    /**
     * Test for checkBuyCard Method in InputController Class.
     */
    @Test
    public void checkBuyCardTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        assertFalse(InputController.checkBuyCard(-1, 0, game));
        assertEquals("Row index should be between 1 and 3!", InputController.getError());

        assertFalse(InputController.checkBuyCard(3, 0, game));
        assertEquals("Row index should be between 1 and 3!", InputController.getError());

        assertFalse(InputController.checkBuyCard(0, -1, game));
        assertEquals("Column index should be between 1 and 4!", InputController.getError());

        assertFalse(InputController.checkBuyCard(0, 4, game));
        assertEquals("Column index should be between 1 and 4!", InputController.getError());

        while(!game.getDevelopmentCardTable().getDeck(0,0).isEmpty())
            game.getDevelopmentCardTable().drawCardFromDeck(0, 0);

        assertFalse(InputController.checkBuyCard(0, 0, game));
        assertEquals("Cannot buy Card from and empty Deck!", InputController.getError());

        assertTrue(InputController.checkBuyCard(1, 0, game));
    }

    /**
     * Test for checkChooseCardSlot Method in InputController Class.
     */
    @Test
    public void checkChooseCardSlotTest() {
        assertFalse(InputController.checkChooseCardSlot(-1));
        assertEquals("Card Slot index should be between 1 and 3", InputController.getError());

        assertFalse(InputController.checkChooseCardSlot(3));
        assertEquals("Card Slot index should be between 1 and 3", InputController.getError());

        assertTrue(InputController.checkChooseCardSlot(0));
        assertTrue(InputController.checkChooseCardSlot(1));
        assertTrue(InputController.checkChooseCardSlot(2));
    }

    /**
     * Test for checkChooseLeaderCard Method in InputController Class.
     */
    @Test
    public void checkChooseLeaderCardTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(0);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(1);

        assertFalse(InputController.checkChooseLeaderCard(-1, game));
        assertEquals("Leader Card Index should be either 0 or 1!", InputController.getError());

        assertFalse(InputController.checkChooseLeaderCard(2, game));
        assertEquals("Leader Card Index should be either 0 or 1!", InputController.getError());

        assertFalse(InputController.checkChooseLeaderCard(0, game));
        assertEquals("Leader Card must be of type \"WHITEMARBLE\"!", InputController.getError());

        assertFalse(InputController.checkChooseLeaderCard(1, game));
        assertEquals("Leader Card must be of type \"WHITEMARBLE\"!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(2);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(3);

        assertFalse(InputController.checkChooseLeaderCard(0, game));
        assertEquals("Leader Card must be active!", InputController.getError());

        assertFalse(InputController.checkChooseLeaderCard(1, game));
        assertEquals("Leader Card must be active!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[0].setActive(true);

        assertTrue(InputController.checkChooseLeaderCard(0, game));
        assertFalse(InputController.checkChooseLeaderCard(1, game));
        assertEquals("Leader Card must be active!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[1].setActive(true);

        assertTrue(InputController.checkChooseLeaderCard(0, game));
        assertTrue(InputController.checkChooseLeaderCard(1, game));
    }

    /**
     * Test for checkChooseProductionOutput Method in InputController Class.
     */
    @Test
    public void checkChooseProductionOutputTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(0);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(1);

        ArrayList<ResourceType> basicInputs = new ArrayList<>();
        basicInputs.add(ResourceType.SHIELDS);
        basicInputs.add(ResourceType.COINS);
        basicInputs.add(ResourceType.NONE);

        ArrayList<ResourceType> lead1 = new ArrayList<>();
        lead1.add(ResourceType.COINS);
        lead1.add(ResourceType.NONE);

        ArrayList<ResourceType> lead2 = new ArrayList<>();
        lead2.add(ResourceType.COINS);
        lead2.add(ResourceType.NONE);

        assertFalse(InputController.checkChooseProductionOutput(true, false, false, basicInputs, lead1, lead2, game));
        assertEquals("One or more resources were of type NONE!", InputController.getError());

        basicInputs.set(2, ResourceType.COINS);

        assertFalse(InputController.checkChooseProductionOutput(true, false, false, basicInputs, lead1, lead2, game));
        assertEquals("1 resources have to be chosen! (You picked 3)", InputController.getError());

        assertFalse(InputController.checkChooseProductionOutput(false, true, false, basicInputs, lead1, lead2, game));
        assertEquals("(First) Leader Card needs to be of \"PRODUCTION POWER\" type to be used in Production!", InputController.getError());

        assertFalse(InputController.checkChooseProductionOutput(false, false, true, basicInputs, lead1, lead2, game));
        assertEquals("(Second) Leader Card needs to be of \"PRODUCTION POWER\" type to be used in Production!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(4);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(5);

        assertFalse(InputController.checkChooseProductionOutput(false, true, false, basicInputs, lead1, lead2, game));
        assertEquals("(First) Leader Card is not active!", InputController.getError());

        assertFalse(InputController.checkChooseProductionOutput(false, false, true, basicInputs, lead1, lead2, game));
        assertEquals("(Second) Leader Card is not active!", InputController.getError());

        game.getPlayers().get(0).getLeaderCards()[0].setActive(true);
        game.getPlayers().get(0).getLeaderCards()[1].setActive(true);

        assertFalse(InputController.checkChooseProductionOutput(false, true, false, basicInputs, lead1, lead2, game));
        assertEquals("One or more resources were of type NONE!", InputController.getError());

        assertFalse(InputController.checkChooseProductionOutput(false, false, true, basicInputs, lead1, lead2, game));
        assertEquals("One or more resources were of type NONE!", InputController.getError());

        lead1.set(1, ResourceType.COINS);
        lead2.set(1, ResourceType.COINS);

        assertFalse(InputController.checkChooseProductionOutput(false, true, false, basicInputs, lead1, lead2, game));
        assertEquals("3 resources have to be chosen! (You picked 2)", InputController.getError());

        assertFalse(InputController.checkChooseProductionOutput(false, false, true, basicInputs, lead1, lead2, game));
        assertEquals("3 resources have to be chosen! (You picked 2)", InputController.getError());

        lead1.add(ResourceType.COINS);
        lead2.add(ResourceType.COINS);

        basicInputs.remove(0);
        basicInputs.remove(0);

        assertTrue(InputController.checkChooseProductionOutput(true, true, true, basicInputs, lead1, lead2, game));
    }

    /**
     * Test for checkMarketChooseRow Method in InputController Class.
     */
    @Test
    public void checkMarketChooseRowTest() {
        assertFalse(InputController.checkMarketChooseRow(true, -1));
        assertEquals("Row index should be between 0 and 2!", InputController.getError());

        assertFalse(InputController.checkMarketChooseRow(true, 3));
        assertEquals("Row index should be between 0 and 2!", InputController.getError());

        assertFalse(InputController.checkMarketChooseRow(false, -1));
        assertEquals("Column index should be between 0 and 3!", InputController.getError());

        assertFalse(InputController.checkMarketChooseRow(false, 4));
        assertEquals("Column index should be between 0 and 3!", InputController.getError());

        assertTrue(InputController.checkMarketChooseRow(true, 2));
        assertTrue(InputController.checkMarketChooseRow(false, 3));
    }

    /**
     * Test for checkPayResource Method in InputController Class.
     */
    @Test
    public void checkPayResourceTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        game.getPlayers().get(0).getLeaderCards()[0] = game.getLeaderCards().get(0);
        game.getPlayers().get(0).getLeaderCards()[1] = game.getLeaderCards().get(1);

        assertFalse(InputController.checkPayResource(true, -1, ResourceType.NONE, game));
        assertEquals("Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkPayResource(true, 5, ResourceType.NONE, game));
        assertEquals("Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkPayResource(false, -1, ResourceType.NONE, game));
        assertEquals("Resource was of type NONE!", InputController.getError());

        assertFalse(InputController.checkPayResource(true, 3, ResourceType.NONE, game));
        assertEquals("Extra depots are not active!", InputController.getError());

        assertFalse(InputController.checkPayResource(true, 4, ResourceType.NONE, game));
        assertEquals("Extra depot 2 is not active!", InputController.getError());

        game.getPlayers().get(0).getWarehouse().setExtraWarehouseDepot1IsActive(true);
        game.getPlayers().get(0).getWarehouse().setExtraWarehouseDepot2IsActive(true);

        assertTrue(InputController.checkPayResource(true, 3, ResourceType.NONE, game));
        assertTrue(InputController.checkPayResource(true, 4, ResourceType.NONE, game));
    }

    /**
     * Test for checkSwitchDepot Method in InputController Class.
     */
    @Test
    public void checkSwitchDepotTest() {
        Game game = gameCreator();
        game.setMyNickname("marco");

        assertFalse(InputController.checkSwitchDepot(-1, -1, game));
        assertEquals("First Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(5, -1, game));
        assertEquals("First Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(0, -1, game));
        assertEquals("Second Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(0, -1, game));
        assertEquals("Second Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(0, 5, game));
        assertEquals("Second Depot index should be between 1 and 5", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(0, 0, game));
        assertEquals("Cannot switch Depot with itself!", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(3, 0, game));
        assertEquals("Cannot switch non active Depots", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(4, 0, game));
        assertEquals("Cannot switch non active Depots", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(0, 3, game));
        assertEquals("Cannot switch non active Depots", InputController.getError());

        assertFalse(InputController.checkSwitchDepot(0, 4, game));
        assertEquals("Cannot switch non active Depots", InputController.getError());

        assertTrue(InputController.checkSwitchDepot(0, 1, game));
    }


    /**
     * Method used to create a Game for test purposes.
     */
    private static Game gameCreator() {
        Game game = new Game();
        game.getPlayers().add(new Player("marco", 0, true));
        game.getPlayers().add(new Player("antonio", 1, false));
        game.getPlayers().add(new Player("lello", 2, false));
        game.getPlayers().add(new Player("anastasia", 3, false));

        DevelopmentCardDeck[][] decks;
        decks = ToCliTest.tableCreator();

        leaderCardInit(game);

        DevelopmentCardTable table = new DevelopmentCardTable(decks);

        game.setDevelopmentCardTable(table);

        return game;
    }

    /**
     * Method used to create a Game for test purposes.
     */
    public static void leaderCardInit(Game game) {
        game.setLeaderCards(new ArrayList<>());

        ResourceStack stack1 = new ResourceStack(1,1,0,0);
        ResourceStack stack2 = new ResourceStack(0,0,1,1);
        ResourceStack stack3 = new ResourceStack(1,1,1,1);
        ResourceStack stack4 = new ResourceStack(2,0,0,0);

        LeaderRequirements requirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);

        LeaderCard discountOne = new LeaderCard(0, 1, stack1, requirements, stack1);
        LeaderCard discountTwo = new LeaderCard(1, 1, stack1, requirements, stack2);

        LeaderCard whiteMarbleOne = new LeaderCard(2, 1, stack1, requirements, Marble.YELLOW);
        LeaderCard whiteMarbleTwo = new LeaderCard(3, 2, stack1, requirements, Marble.PURPLE);

        LeaderCard productionOne = new LeaderCard(4, 3, stack2, requirements, stack1, 3, 1);
        LeaderCard productionTwo = new LeaderCard(5, 1, stack2, requirements, stack2, 3, 0);

        LeaderCard extraDepotOne = new LeaderCard(1, 2, stack3, requirements, ResourceType.SHIELDS);
        LeaderCard extraDepotTwo = new LeaderCard(1, 2, stack4, requirements, ResourceType.SERVANTS);

        game.getLeaderCards().add(discountOne);
        game.getLeaderCards().add(discountTwo);
        game.getLeaderCards().add(whiteMarbleOne);
        game.getLeaderCards().add(whiteMarbleTwo);
        game.getLeaderCards().add(productionOne);
        game.getLeaderCards().add(productionTwo);
        game.getLeaderCards().add(extraDepotOne);
        game.getLeaderCards().add(extraDepotTwo);
    }
}
