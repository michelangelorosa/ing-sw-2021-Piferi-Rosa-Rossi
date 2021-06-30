package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Test for Board Class.
 */
public class BoardTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    Board board = new Board();

    /**
     * Constructor and getter test for Board Class.
     */
    @Test
    public void constructorTest() {
        for(int i = 0; i < 3; i++) {
            assertEquals(ResourceType.NONE, board.getResourceManager().getWarehouseDepots()[i].getResourceType());
            assertEquals(0, board.getResourceManager().getWarehouseDepots()[i].getStoredResources());
        }
        assertEquals(ResourceType.NONE, board.getResourceManager().getExtraWarehouseDepotOne().getResourceType());
        assertEquals(ResourceType.NONE, board.getResourceManager().getExtraWarehouseDepotTwo().getResourceType());

        assertEquals(0, board.getResourceManager().getExtraWarehouseDepotOne().getStoredResources());
        assertEquals(0, board.getResourceManager().getExtraWarehouseDepotTwo().getStoredResources());

        assertEquals(2, board.getBasicProduction().getJollyIn());
        assertEquals(1, board.getBasicProduction().getJollyOut());
        assertEquals(0, board.getBasicProduction().getOutputFaith());
        assertEquals("0 0 0 0", board.getBasicProduction().getFixedInputs().toString());
        assertEquals("0 0 0 0", board.getBasicProduction().getFixedOutputs().toString());
    }

    /**
     * Setter test for Board Class.
     */
    @Test
    public void setterTest() {
        ResourceStack stack = new ResourceStack(1,1,1,1);
        ResourceStack stack2 = new ResourceStack(2,1,1,1);
        BasicProduction basicProduction = new BasicProduction(stack, stack2, 2, 2, 2);
        board.setBasicProduction(basicProduction);

        assertEquals(2, board.getBasicProduction().getJollyIn());
        assertEquals(2, board.getBasicProduction().getJollyOut());
        assertEquals(2, board.getBasicProduction().getOutputFaith());
        assertEquals("1 1 1 1", board.getBasicProduction().getFixedInputs().toString());
        assertEquals("2 1 1 1", board.getBasicProduction().getFixedOutputs().toString());
    }

    /**
     * Test for "productionAdder" and "getProductionCost" methods in Board Class.
     */
    @Test
    public void productionInputAdderAndProductionCostTest() {
        ArrayList<DevelopmentCard> cards = generateDevCards();
        ArrayList<LeaderCard> leaderCards = generateLeaderCards();
        ArrayList<ResourceType> basicProductionInput = new ArrayList<>();

        activateLeaderCards(leaderCards);

        ResourceStack input = board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
        assertEquals("4 3 2 1", input.toString());

        board.getProductionCost(cards, leaderCards, false, basicProductionInput);
        assertEquals("4 3 2 1", board.getResourceManager().getTemporaryResourcesToPay().toString());


        leaderCards.remove(1);
        input = board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
        assertEquals("4 3 1 0", input.toString());
        board.getProductionCost(cards, leaderCards, false, basicProductionInput);
        assertEquals("4 3 1 0", board.getResourceManager().getTemporaryResourcesToPay().toString());

        leaderCards.remove(0);
        input = board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
        assertEquals("3 2 1 0", input.toString());
        board.getProductionCost(cards, leaderCards, false, basicProductionInput);
        assertEquals("3 2 1 0", board.getResourceManager().getTemporaryResourcesToPay().toString());

        basicProductionInput.add(ResourceType.SHIELDS);
        basicProductionInput.add(ResourceType.SHIELDS);
        basicProductionInput.add(ResourceType.STONES);
        input = board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
        assertEquals("3 2 1 0", input.toString());
        board.getProductionCost(cards, leaderCards, false, basicProductionInput);
        assertEquals("3 2 1 0", board.getResourceManager().getTemporaryResourcesToPay().toString());


        input = board.productionInputAdder(cards, leaderCards, true, basicProductionInput);
        assertEquals("5 2 1 1", input.toString());
        board.getProductionCost(cards, leaderCards, true, basicProductionInput);
        assertEquals("5 2 1 1", board.getResourceManager().getTemporaryResourcesToPay().toString());

        cards.remove(2);
        input = board.productionInputAdder(cards, leaderCards, true, basicProductionInput);
        assertEquals("3 1 1 1", input.toString());
        board.getProductionCost(cards, leaderCards, true, basicProductionInput);
        assertEquals("3 1 1 1", board.getResourceManager().getTemporaryResourcesToPay().toString());

        cards.remove(1);
        input = board.productionInputAdder(cards, leaderCards, true, basicProductionInput);
        assertEquals("2 0 1 1", input.toString());
        board.getProductionCost(cards, leaderCards, true, basicProductionInput);
        assertEquals("2 0 1 1", board.getResourceManager().getTemporaryResourcesToPay().toString());

        cards.remove(0);
        input = board.productionInputAdder(cards, leaderCards, true, basicProductionInput);
        assertEquals("2 0 0 1", input.toString());
        board.getProductionCost(cards, leaderCards, true, basicProductionInput);
        assertEquals("2 0 0 1", board.getResourceManager().getTemporaryResourcesToPay().toString());


        leaderCards = generateLeaderCards();
        activateLeaderCards(leaderCards);
        input = board.productionInputAdder(cards, leaderCards, true, basicProductionInput);
        assertEquals("3 1 1 2", input.toString());
        board.getProductionCost(cards, leaderCards, true, basicProductionInput);
        assertEquals("3 1 1 2", board.getResourceManager().getTemporaryResourcesToPay().toString());

        leaderCards.remove(1);
        input = board.productionInputAdder(cards, leaderCards, true, basicProductionInput);
        assertEquals("3 1 0 1", input.toString());
        board.getProductionCost(cards, leaderCards, true, basicProductionInput);
        assertEquals("3 1 0 1", board.getResourceManager().getTemporaryResourcesToPay().toString());

        input = board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
        assertEquals("1 1 0 0", input.toString());
        board.getProductionCost(cards, leaderCards, false, basicProductionInput);
        assertEquals("1 1 0 0", board.getResourceManager().getTemporaryResourcesToPay().toString());

        leaderCards.remove(0);
        input = board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
        assertEquals("0 0 0 0", input.toString());
        board.getProductionCost(cards, leaderCards, false, basicProductionInput);
        assertEquals("0 0 0 0", board.getResourceManager().getTemporaryResourcesToPay().toString());

        leaderCards = generateLeaderCards();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Model.Board: Leader Card was not active.");
        board.productionInputAdder(cards, leaderCards, false, basicProductionInput);
    }

    /**
     * Test for "canStartProduction" method in Board Class.
     */
    @Test
    public void canStartProductionTest() {
        ArrayList<DevelopmentCard> cards = generateDevCards();
        ArrayList<LeaderCard> leaderCards = generateLeaderCards();
        ArrayList<ResourceType> basicProductionInput = new ArrayList<>();

        activateLeaderCards(leaderCards);

        assertFalse(board.canStartProduction(cards, leaderCards, false, basicProductionInput));

        setResourceManagerWarehouseOnly(board.getResourceManager());
        assertFalse(board.canStartProduction(cards, leaderCards, false, basicProductionInput));

        board.getResourceManager().reset();
        setResourceManagerStrongboxOnly(board.getResourceManager());
        assertTrue(board.canStartProduction(cards, leaderCards, false, basicProductionInput));

        board.getResourceManager().reset();
        setResourceManagerBoth(board.getResourceManager());
        assertTrue(board.canStartProduction(cards, leaderCards, true, basicProductionInput));

        board.getResourceManager().reset();
        cards.remove(2);
        cards.remove(1);
        cards.remove(0);
        leaderCards.remove(1);
        leaderCards.remove(0);

        setResourceManagerWarehouseOnly(board.getResourceManager());
        assertTrue(board.canStartProduction(cards, leaderCards, true, basicProductionInput));

        leaderCards = generateLeaderCards();
        activateLeaderCards(leaderCards);
        basicProductionInput.add(ResourceType.SHIELDS);
        basicProductionInput.add(ResourceType.STONES);
        basicProductionInput.add(ResourceType.SHIELDS);

        assertFalse(board.canStartProduction(cards, leaderCards, true, basicProductionInput));

        ResourceStack stack = new ResourceStack(0,0,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        LeaderCard leaderCard = new LeaderCard(0, 0, stack, leaderRequirements, Marble.PURPLE);
        leaderCard.setActive(true);
        leaderCards.add(leaderCard);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Model.Board: Leader Card was not of type PRODUCTION POWER.");
        board.canStartProduction(cards, leaderCards, true, basicProductionInput);
    }

    /**
     * Test for "getFixedProductionOutput" method in Board Class.
     */
    @Test
    public void getFixedProductionOutputTest() {
        Player player = new Player("Antonio", 0, true);
        ArrayList<DevelopmentCard> cards = generateDevCards();
        ArrayList<LeaderCard> leaderCards = generateLeaderCards();

        activateLeaderCards(leaderCards);

        ResourceStack output = board.getFixedProductionOutput(player, cards, leaderCards, true);
        assertEquals("5 3 0 0", output.toString());
        assertEquals(4, player.getFaithTrackPosition());

        cards.remove(2);
        output = board.getFixedProductionOutput(player, cards, leaderCards, true);
        assertEquals("3 0 0 0", output.toString());
        assertEquals(8, player.getFaithTrackPosition());

        ResourceStack stack = new ResourceStack(1, 1, 1, 1);
        BasicProduction production = new BasicProduction(stack, stack, 1, 2, 3);
        board.setBasicProduction(production);

        output = board.getFixedProductionOutput(player, cards, leaderCards, false);
        assertEquals("3 0 0 0", output.toString());
        assertEquals(12, player.getFaithTrackPosition());

        output = board.getFixedProductionOutput(player, cards, leaderCards, true);
        assertEquals("4 1 1 1", output.toString());
        assertEquals(19, player.getFaithTrackPosition());

        cards.remove(0);
        cards.remove(0);

        leaderCards = new ArrayList<>();

        output = board.getFixedProductionOutput(player, cards, leaderCards, true);
        assertEquals("1 1 1 1", output.toString());
        assertEquals(22, player.getFaithTrackPosition());

        output = board.getFixedProductionOutput(player, cards, leaderCards, false);
        assertEquals("0 0 0 0", output.toString());
        assertEquals(22, player.getFaithTrackPosition());
    }

    /**
     * Test for "canActivateLeaderCard" method in Board Class.
     */
    @Test
    public void canActivateLeaderCardTest() {
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,1,1,0,0,0,0,1,0,0);
        ResourceStack stack = new ResourceStack(3,2,1,0);
        LeaderCard leaderCard = new LeaderCard(1, 1, stack, leaderRequirements, Marble.PURPLE);
        LeaderRequirements leaderRequirements2 = new LeaderRequirements(0,0,1,0);
        ResourceStack stack2 = new ResourceStack(3,2,1,0);
        LeaderCard leaderCard2 = new LeaderCard(1, 1, stack2, leaderRequirements2, Marble.BLUE);

        Player player = new Player("Antonio", 0, true);

        assertFalse(player.getBoard().canActivateLeaderCard(leaderCard));
        assertFalse(player.getBoard().canActivateLeaderCard(leaderCard2));

        setResourceManagerWarehouseOnly(player.getBoard().getResourceManager());
        assertFalse(player.getBoard().canActivateLeaderCard(leaderCard));

        player.getBoard().getResourceManager().reset();
        setResourceManagerBoth(player.getBoard().getResourceManager());
        assertFalse(player.getBoard().canActivateLeaderCard(leaderCard));

        player.getBoard().getResourceManager().reset();
        CommonTestMethods.addCardsToPlayersBoard(player);
        assertFalse(player.getBoard().canActivateLeaderCard(leaderCard));

        setResourceManagerBoth(player.getBoard().getResourceManager());
        assertTrue(player.getBoard().canActivateLeaderCard(leaderCard));
        assertTrue(player.getBoard().canActivateLeaderCard(leaderCard2));

    }

    /**
     * Test for "activateLeaderCard" method in Board Class.
     */
    @Test
    public void activateLeaderCardTest() {
        ArrayList<LeaderCard> leaderCards = generateLeaderCards();

        assertFalse(leaderCards.get(0).isActive());
        assertFalse(leaderCards.get(1).isActive());

        board.activateLeaderCard(leaderCards.get(0));
        assertTrue(leaderCards.get(0).isActive());

        board.activateLeaderCard(leaderCards.get(1));
        assertTrue(leaderCards.get(1).isActive());

        LeaderCard leaderCard = new LeaderCard(3, 4, new ResourceStack(0,0,0,0), new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0), ResourceType.SHIELDS);
        board.activateLeaderCard(leaderCard);
        assertTrue(leaderCard.isActive());
        assertTrue(board.getResourceManager().isExtraDepotOneActive());
        assertEquals(ResourceType.SHIELDS, board.getResourceManager().getExtraWarehouseDepotOne().getResourceType());
    }


    /**
     * FOR TESTING.
     * Method used to generate Development Cards.
     * @return An ArrayList containing three Development Cards.
     */
    private ArrayList<DevelopmentCard> generateDevCards() {
        ArrayList<DevelopmentCard> cards = new ArrayList<>();

        ResourceStack cost = new ResourceStack(2,0,0,0);
        ResourceStack input = new ResourceStack(0,0,1,0);
        ResourceStack output = new ResourceStack(0,0,0,0);
        int outputFaith = 1;
        DevelopmentCard card1 = new DevelopmentCard(Color.GREEN, Level.ONE, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,1,1);
        input = new ResourceStack(1,1,0,0);
        output = new ResourceStack(3,0,0,0);
        outputFaith = 2;
        DevelopmentCard card2 = new DevelopmentCard(Color.YELLOW, Level.ONE, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,1,1);
        input = new ResourceStack(2,1,0,0);
        output = new ResourceStack(2,3,0,0);
        outputFaith = 0;
        DevelopmentCard card3 = new DevelopmentCard(Color.PURPLE, Level.TWO, 1, 1, cost, input, output, outputFaith);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        return cards;
    }

    /**
     * FOR TESTING.
     * Method used to generate Leader Cards.
     * @return An ArrayList containing two Leader Cards.
     */
    private ArrayList<LeaderCard> generateLeaderCards() {
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();

        ResourceStack stack1 = new ResourceStack(1,1,0,0);
        ResourceStack stack2 = new ResourceStack(0,0,1,1);
        LeaderRequirements requirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);

        LeaderCard productionOne = new LeaderCard(4, 3, stack2, requirements, stack1, 1, 1);
        LeaderCard productionTwo = new LeaderCard(5, 1, stack2, requirements, stack2, 2, 0);

        leaderCards.add(productionOne);
        leaderCards.add(productionTwo);

        return leaderCards;
    }

    /**
     * FOR TESTING.
     * Method used to activate all Leader Cards inside an ArrayList.
     */
    private void activateLeaderCards(ArrayList<LeaderCard> leaderCards) {
        for(LeaderCard leaderCard : leaderCards)
            leaderCard.setActive(true);
    }

    /**
     * FOR TESTING.
     * Method used to fill the Warehouse inside a Resource Manager.
     */
    private void setResourceManagerWarehouseOnly(ResourceManager resourceManager) {
        resourceManager.addMarketResourcesByType(2, ResourceType.SHIELDS, resourceManager.getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.COINS, resourceManager.getWarehouseDepots()[1]);
        resourceManager.addMarketResourcesByType(1, ResourceType.SERVANTS, resourceManager.getWarehouseDepots()[2]);
        resourceManager.getWarehouse().activateLeaderDepot(ResourceType.STONES);
        resourceManager.addOneResourceToDepot(ResourceType.STONES, resourceManager.getExtraWarehouseDepotOne());
    }

    /**
     * FOR TESTING.
     * Method used to fill the Strongbox inside a Resource Manager.
     */
    private void setResourceManagerStrongboxOnly(ResourceManager resourceManager) {
        ResourceStack stack = new ResourceStack(5, 4, 3, 2);
        resourceManager.addProductionResources(stack);
    }

    /**
     * FOR TESTING.
     * Method used to fill both the Warehouse and the Strongbox inside a Resource Manager.
     */
    private void setResourceManagerBoth(ResourceManager resourceManager) {
        setResourceManagerWarehouseOnly(resourceManager);
        setResourceManagerStrongboxOnly(resourceManager);
    }

}
