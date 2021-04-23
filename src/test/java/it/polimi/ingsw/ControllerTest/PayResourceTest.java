package it.polimi.ingsw.ControllerTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.BUY_CARD;
import static it.polimi.ingsw.Controller.Actions.ActionType.CHOOSE_CARD_SLOT;
import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class PayResourceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    PayResource pay = new PayResource(false, 0, ResourceType.SHIELDS);
    PayResource pay2 = new PayResource(true, 0, ResourceType.NONE);
    PayResource pay3 = new PayResource(false, 0, ResourceType.STONES);
    PayResource pay4 = new PayResource(false, 0, ResourceType.COINS);
    PayResource pay5 = new PayResource(true, 3, ResourceType.NONE);

    /**getter test*/
    @Test
    public void getActionTypeTest(){

    }

    /**Test to check if isFromWarehouse method works properly*/
    @Test
    public void isFromWarehouseTest(){
        assertFalse(pay.isFromWarehouse());
    }

    /**Getter test*/
    @Test
    public void getDepotTest(){
        assertEquals(0, pay2.getDepot());
    }

    /**Getter test*/
    @Test
    public void getResourceTypeTest(){
        assertEquals(ResourceType.SHIELDS, pay.getResourceType());
    }

    /**Test to check if isCorrect method works properly when the index of the depot is out of bound*/
    @Test
    public void isCorrect1Test(){
        PayResource payErr = new PayResource(true, 5, ResourceType.STONES);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Depot index out of bounds.");
        payErr.isCorrect();
    }

    /**Test to check if isCorrect method works properly when it's specify the type of a depot*/
    @Test
    public void isCorrect2Test(){
        PayResource payErr = new PayResource(true, 1, ResourceType.STONES);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Specified type when choosing depot.");
        payErr.isCorrect();
    }

    /**Test to check if isCorrect method works properly when is not specify the type of resource in the strongbox*/
    @Test
    public void isCorrect3Test(){
        PayResource payErr = new PayResource(false, 1, ResourceType.NONE);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource coming from strongbox was of type NONE.");
        payErr.isCorrect();
    }

    /**Test to check if canBeApplied works properly*/
    @Test
    public void canBeAppliedTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);
        PayResource payTest = new PayResource(false, 1, ResourceType.SHIELDS);
    //When the resources comes from the strongbox, return true.
        assertEquals(true, pay.canBeApplied(game));
        WarehouseDepot warehouseDepot = new WarehouseDepot(2, true);
        game.getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(ResourceType.SHIELDS, warehouseDepot);
    //If the resource comes from an extraDepot but it is not active, return false.
        assertEquals(false, pay5.canBeApplied(game));
    //If the resource comes from an extraDepot but it is active, return true.
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getLeaderCards().get(7));
        assertEquals(true, pay5.canBeApplied(game));
    }

    /**
     * I created a game, gave to the current player some resources and tried to pay the cards which costs 2 shields.
     * I put the cost of the card in a temporary stack and start to pay.
     * <ul>
     *     <li>The first time I pay a shield from the strongbox, I have still a shield to pay, so it returns "HasToPay"</li>
     *     <li>The second time I pay with a stone from the strongbox, but it is not necessary for the card, so it returns
     *     "This type of resource is not needed"</li>
     *     <li>The third time I try to pay with a coin from the strongbox, but there are no coins in the strongbox,
     *     so it returns "No COINS left in Strongbox"</li>
     *     <li>The fourth time I pay with a shield from the warehouse. There are no other resources to pay so it returns
     *     "SUCCESS"</li>
     * </ul>
     */
    @Test
    public void doActionTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);

        ChooseProductionOutput output = new ChooseProductionOutput();
        ChooseCardSlot cardSlot = new ChooseCardSlot(1);
        ResetWarehouse resetWarehouse = new ResetWarehouse();
        ResourceStack stack = new ResourceStack(5,3,0,2);
        ResourceStack temporary = new ResourceStack(2,0,0,0);
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(temporary);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 1,2,0,ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);
        assertEquals("2 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());

        assertEquals("HasToPay", pay.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("1 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("This type of resource is not needed", pay3.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("1 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("No COINS left in Strongbox", pay4.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("Can't take resource from a non active depot", pay5.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("SUCCESS", pay2.doAction(game, output, cardSlot, resetWarehouse));

        assertEquals(ResourceType.NONE, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getResourceType());
    }
}
