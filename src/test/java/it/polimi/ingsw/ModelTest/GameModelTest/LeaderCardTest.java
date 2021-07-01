package it.polimi.ingsw.ModelTest.GameModelTest;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tester for LeaderCard class
 */

public class LeaderCardTest {

    /**
     * Code required for the creation of 4 LeaderCards with the LeaderCard Constructor.
     * Each Card is tested differently depending on its abilities
     */
    int cardId1=43,cardId2=423,cardId3=41,cardId4=2,victoryPoints1=32,victoryPoints2=42,victoryPoints3=49,victoryPoints4=30,faith=3,jollyOut=9
        ,blueCardLv1=1,purpleCardLv1=2,yellowCardLv1=1,greenCardLv1=0,blueCardLv2=0,purpleCardLv2=0,yellowCardLv2=0,greenCardLv2=4,blueCardLv3=0,purpleCardLv3=0,yellowCardLv3=2,greenCardLv3=0;
    Marble marble = Marble.RED;
    ResourceType resourceType = ResourceType.COINS;
    ResourceStack resourceStack1 = new ResourceStack(1,4,6,7);
    ResourceStack resourceStack2 = new ResourceStack(6,5,2,4);
    ResourceStack resourceStack3 = new ResourceStack(0,0,0,0);
    ResourceStack resourceStack4 = new ResourceStack(6,4,1,8);
    ResourceStack resourceStackDiscount = new ResourceStack(5,7,0,0);
    ResourceStack resourceStackInput = new ResourceStack(4,3,0,0);
    LeaderRequirements leaderRequirements = new LeaderRequirements(blueCardLv1,purpleCardLv1,yellowCardLv1,greenCardLv1,blueCardLv2,purpleCardLv2,yellowCardLv2,greenCardLv2,blueCardLv3,purpleCardLv3,yellowCardLv3,greenCardLv3);

    LeaderCard leaderCard1 = new LeaderCard(cardId1,victoryPoints1,resourceStack1,leaderRequirements,resourceStackDiscount);

    /**
     * Testing LeaderCards with Depot type
      */
    @Test
    public void leaderDepotTest(){
        System.out.println(leaderCard1.getCardId());
        assertEquals(victoryPoints1,leaderCard1.getVictoryPoints());
        assertEquals(leaderCard1.getAction(), LeaderCardAction.DISCOUNT);
        assertFalse(leaderCard1.isActive());
        assertSame(leaderCard1.getResourcesRequired(),resourceStack1);
        assertSame(leaderCard1.getCardsRequired(),leaderRequirements);
        assertSame(leaderCard1.getDiscount(),resourceStackDiscount);
        assertNull(leaderCard1.getMarble());
        assertNull(leaderCard1.getInput());
        assertSame(leaderCard1.getFaith(),0);
        assertSame(leaderCard1.getJollyOut(),0);
        assertNull(leaderCard1.getResource());
        leaderCard1.setActive(true);
        assertTrue(leaderCard1.isActive());
    }

    LeaderCard leaderCard2 = new LeaderCard(cardId2,victoryPoints2,resourceStack2,leaderRequirements,marble);

    /**
     * Testing LeaderCards with WhiteMarble->Resource ability
     */
    @Test
    public void whiteMarbleTest(){
        assertEquals(cardId2,leaderCard2.getCardId());
        assertEquals(victoryPoints2,leaderCard2.getVictoryPoints());
        assertEquals(LeaderCardAction.WHITEMARBLE,leaderCard2.getAction());
        assertFalse(leaderCard2.isActive());
        assertSame(leaderCard2.getResourcesRequired(),resourceStack2);
        assertSame(leaderCard2.getCardsRequired(),leaderRequirements);
        assertSame(leaderCard2.getMarble(),marble);
        assertNull(leaderCard2.getDiscount());
        assertNull(leaderCard2.getInput());
        assertEquals(leaderCard2.getFaith(),0);
        assertEquals(leaderCard2.getJollyOut(),0);
        assertNull(leaderCard2.getResource());
        leaderCard2.setActive(true);
        assertTrue(leaderCard2.isActive());
    }

    LeaderCard leaderCard3 = new LeaderCard(cardId3,victoryPoints3,resourceStack3,leaderRequirements,resourceStackInput,jollyOut,faith);

    /**
     * Testing LeaderCards that add a ProductionPower to the Player's board
     */
    @Test
    public void productionPowerTest(){
        assertEquals(leaderCard3.getCardId(),cardId3);
        assertEquals(leaderCard3.getVictoryPoints(),victoryPoints3);
        assertEquals(leaderCard3.getAction(),LeaderCardAction.PRODUCTIONPOWER);
        assertFalse(leaderCard3.isActive());
        assertSame(leaderCard3.getResourcesRequired(),resourceStack3);
        assertSame(leaderCard3.getCardsRequired(),leaderRequirements);
        assertNull(leaderCard3.getDiscount());
        assertNull(leaderCard3.getMarble());
        assertSame(leaderCard3.getInput(),resourceStackInput);
        assertEquals(leaderCard3.getFaith(),faith);
        assertEquals(leaderCard3.getJollyOut(),jollyOut);
        assertNull(leaderCard3.getResource());
        leaderCard3.setActive(true);
        assertTrue(leaderCard3.isActive());
    }

    LeaderCard leaderCard4 = new LeaderCard(cardId4,victoryPoints4,resourceStack4,leaderRequirements,resourceType);
    /**
     * Testing the LeaderCards that add a depot to the Player's board
     */
    @Test
    public void extraDepotTest(){
        assertEquals(leaderCard4.getCardId(),cardId4);
        assertEquals(leaderCard4.getVictoryPoints(),victoryPoints4);
        assertEquals(leaderCard4.getAction(),LeaderCardAction.EXTRADEPOT);
        assertFalse(leaderCard4.isActive());
        assertSame(leaderCard4.getResourcesRequired(),resourceStack4);
        assertSame(leaderCard4.getCardsRequired(),leaderRequirements);
        assertNull(leaderCard4.getDiscount());
        assertNull(leaderCard4.getMarble());
        assertNull(leaderCard4.getInput());
        assertEquals(leaderCard4.getFaith(),0);
        assertEquals(leaderCard4.getJollyOut(),0);
        assertSame(leaderCard4.getResource(),resourceType);
        leaderCard4.setActive(true);
        assertTrue(leaderCard4.isActive());
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){

        RedLeaderCard leaderView;
        leaderView = leaderCard1.toView();

            assertEquals(cardId1, leaderView.getCardId());
            assertEquals(victoryPoints1, leaderView.getVictoryPoints());
            assertEquals(LeaderCardAction.DISCOUNT, leaderView.getAction());
            assertFalse(leaderView.isActive());
            assertSame(resourceStack1.getResource(ResourceType.SHIELDS), leaderView.getResourcesRequired().getResource(ResourceType.SHIELDS));
            assertSame(resourceStack1.getResource(ResourceType.SERVANTS), leaderView.getResourcesRequired().getResource(ResourceType.SERVANTS));
            assertSame(resourceStack1.getResource(ResourceType.COINS), leaderView.getResourcesRequired().getResource(ResourceType.COINS));
            assertSame(resourceStack1.getResource(ResourceType.STONES), leaderView.getResourcesRequired().getResource(ResourceType.STONES));
            assertSame(leaderRequirements.getBlueCardLv1(), leaderView.getCardsRequired().getBlueCardLv1());
            assertSame(leaderRequirements.getBlueCardLv2(), leaderView.getCardsRequired().getBlueCardLv2());
            assertSame(leaderRequirements.getBlueCardLv3(), leaderView.getCardsRequired().getBlueCardLv3());
            assertSame(leaderRequirements.getPurpleCardLv1(), leaderView.getCardsRequired().getPurpleCardLv1());
            assertSame(leaderRequirements.getPurpleCardLv2(), leaderView.getCardsRequired().getPurpleCardLv2());
            assertSame(leaderRequirements.getPurpleCardLv3(), leaderView.getCardsRequired().getPurpleCardLv3());
            assertSame(leaderRequirements.getYellowCardLv1(), leaderView.getCardsRequired().getYellowCardLv1());
            assertSame(leaderRequirements.getYellowCardLv2(), leaderView.getCardsRequired().getYellowCardLv2());
            assertSame(leaderRequirements.getYellowCardLv3(), leaderView.getCardsRequired().getYellowCardLv3());
            assertSame(leaderRequirements.getGreenCardLv1(), leaderView.getCardsRequired().getGreenCardLv1());
            assertSame(leaderRequirements.getGreenCardLv2(), leaderView.getCardsRequired().getGreenCardLv2());
            assertSame(leaderRequirements.getGreenCardLv3(), leaderView.getCardsRequired().getGreenCardLv3());
            assertSame(leaderRequirements.getNeedBlueCard(), leaderView.getCardsRequired().getNeedBlueCard());
            assertSame(leaderRequirements.getNeedPurpleCard(), leaderView.getCardsRequired().getNeedPurpleCard());
            assertSame(leaderRequirements.getNeedYellowCard(), leaderView.getCardsRequired().getNeedYellowCard());
            assertSame(leaderRequirements.getNeedGreenCard(), leaderView.getCardsRequired().getNeedGreenCard());
            assertSame(resourceStackDiscount.getResource(ResourceType.SHIELDS), leaderView.getDiscount().getResource(ResourceType.SHIELDS));
            assertSame(resourceStackDiscount.getResource(ResourceType.SERVANTS), leaderView.getDiscount().getResource(ResourceType.SERVANTS));
            assertSame(resourceStackDiscount.getResource(ResourceType.COINS), leaderView.getDiscount().getResource(ResourceType.COINS));
            assertSame(resourceStackDiscount.getResource(ResourceType.STONES), leaderView.getDiscount().getResource(ResourceType.STONES));
            assertNull(leaderView.getMarble());
            assertNull(leaderView.getInput());
            assertEquals(0, leaderView.getJollyOut());
            assertEquals(0, leaderView.getFaith());
            assertNull(leaderView.getResource());



        leaderView = leaderCard2.toView();

            assertEquals(cardId2, leaderView.getCardId());
            assertEquals(victoryPoints2, leaderView.getVictoryPoints());
            assertEquals(LeaderCardAction.WHITEMARBLE, leaderView.getAction());
            assertFalse(leaderView.isActive());
            assertSame(resourceStack2.getResource(ResourceType.SHIELDS), leaderView.getResourcesRequired().getResource(ResourceType.SHIELDS));
            assertSame(resourceStack2.getResource(ResourceType.SERVANTS), leaderView.getResourcesRequired().getResource(ResourceType.SERVANTS));
            assertSame(resourceStack2.getResource(ResourceType.COINS), leaderView.getResourcesRequired().getResource(ResourceType.COINS));
            assertSame(resourceStack2.getResource(ResourceType.STONES), leaderView.getResourcesRequired().getResource(ResourceType.STONES));
            assertSame(leaderRequirements.getBlueCardLv1(), leaderView.getCardsRequired().getBlueCardLv1());
            assertSame(leaderRequirements.getBlueCardLv2(), leaderView.getCardsRequired().getBlueCardLv2());
            assertSame(leaderRequirements.getBlueCardLv3(), leaderView.getCardsRequired().getBlueCardLv3());
            assertSame(leaderRequirements.getPurpleCardLv1(), leaderView.getCardsRequired().getPurpleCardLv1());
            assertSame(leaderRequirements.getPurpleCardLv2(), leaderView.getCardsRequired().getPurpleCardLv2());
            assertSame(leaderRequirements.getPurpleCardLv3(), leaderView.getCardsRequired().getPurpleCardLv3());
            assertSame(leaderRequirements.getYellowCardLv1(), leaderView.getCardsRequired().getYellowCardLv1());
            assertSame(leaderRequirements.getYellowCardLv2(), leaderView.getCardsRequired().getYellowCardLv2());
            assertSame(leaderRequirements.getYellowCardLv3(), leaderView.getCardsRequired().getYellowCardLv3());
            assertSame(leaderRequirements.getGreenCardLv1(), leaderView.getCardsRequired().getGreenCardLv1());
            assertSame(leaderRequirements.getGreenCardLv2(), leaderView.getCardsRequired().getGreenCardLv2());
            assertSame(leaderRequirements.getGreenCardLv3(), leaderView.getCardsRequired().getGreenCardLv3());
            assertSame(leaderRequirements.getNeedBlueCard(), leaderView.getCardsRequired().getNeedBlueCard());
            assertSame(leaderRequirements.getNeedPurpleCard(), leaderView.getCardsRequired().getNeedPurpleCard());
            assertSame(leaderRequirements.getNeedYellowCard(), leaderView.getCardsRequired().getNeedYellowCard());
            assertSame(leaderRequirements.getNeedGreenCard(), leaderView.getCardsRequired().getNeedGreenCard());
            assertNull(leaderView.getDiscount());
            assertSame(marble, leaderView.getMarble());
            assertNull(leaderView.getInput());
            assertEquals(0, leaderView.getJollyOut());
            assertEquals(0, leaderView.getFaith());
            assertNull(leaderView.getResource());


        leaderView = leaderCard3.toView();

            assertEquals(cardId3, leaderView.getCardId());
            assertEquals(victoryPoints3, leaderView.getVictoryPoints());
            assertEquals(LeaderCardAction.PRODUCTIONPOWER, leaderView.getAction());
            assertFalse(leaderView.isActive());
            assertSame(resourceStack3.getResource(ResourceType.SHIELDS), leaderView.getResourcesRequired().getResource(ResourceType.SHIELDS));
            assertSame(resourceStack3.getResource(ResourceType.SERVANTS), leaderView.getResourcesRequired().getResource(ResourceType.SERVANTS));
            assertSame(resourceStack3.getResource(ResourceType.COINS), leaderView.getResourcesRequired().getResource(ResourceType.COINS));
            assertSame(resourceStack3.getResource(ResourceType.STONES), leaderView.getResourcesRequired().getResource(ResourceType.STONES));
            assertSame(leaderRequirements.getBlueCardLv1(), leaderView.getCardsRequired().getBlueCardLv1());
            assertSame(leaderRequirements.getBlueCardLv2(), leaderView.getCardsRequired().getBlueCardLv2());
            assertSame(leaderRequirements.getBlueCardLv3(), leaderView.getCardsRequired().getBlueCardLv3());
            assertSame(leaderRequirements.getPurpleCardLv1(), leaderView.getCardsRequired().getPurpleCardLv1());
            assertSame(leaderRequirements.getPurpleCardLv2(), leaderView.getCardsRequired().getPurpleCardLv2());
            assertSame(leaderRequirements.getPurpleCardLv3(), leaderView.getCardsRequired().getPurpleCardLv3());
            assertSame(leaderRequirements.getYellowCardLv1(), leaderView.getCardsRequired().getYellowCardLv1());
            assertSame(leaderRequirements.getYellowCardLv2(), leaderView.getCardsRequired().getYellowCardLv2());
            assertSame(leaderRequirements.getYellowCardLv3(), leaderView.getCardsRequired().getYellowCardLv3());
            assertSame(leaderRequirements.getGreenCardLv1(), leaderView.getCardsRequired().getGreenCardLv1());
            assertSame(leaderRequirements.getGreenCardLv2(), leaderView.getCardsRequired().getGreenCardLv2());
            assertSame(leaderRequirements.getGreenCardLv3(), leaderView.getCardsRequired().getGreenCardLv3());
            assertSame(leaderRequirements.getNeedBlueCard(), leaderView.getCardsRequired().getNeedBlueCard());
            assertSame(leaderRequirements.getNeedPurpleCard(), leaderView.getCardsRequired().getNeedPurpleCard());
            assertSame(leaderRequirements.getNeedYellowCard(), leaderView.getCardsRequired().getNeedYellowCard());
            assertSame(leaderRequirements.getNeedGreenCard(), leaderView.getCardsRequired().getNeedGreenCard());
            assertNull(leaderView.getDiscount());
            assertNull(leaderView.getMarble());
            assertSame(resourceStackInput.getResource(ResourceType.SHIELDS), leaderView.getInput().getResource(ResourceType.SHIELDS));
            assertSame(resourceStackInput.getResource(ResourceType.SERVANTS), leaderView.getInput().getResource(ResourceType.SERVANTS));
            assertSame(resourceStackInput.getResource(ResourceType.COINS), leaderView.getInput().getResource(ResourceType.COINS));
            assertSame(resourceStackInput.getResource(ResourceType.STONES), leaderView.getInput().getResource(ResourceType.STONES));
            assertSame(jollyOut, leaderView.getJollyOut());
            assertSame(faith, leaderView.getFaith());
            assertNull(leaderView.getResource());

        leaderView = leaderCard4.toView();

            assertEquals(cardId4, leaderView.getCardId());
            assertEquals(victoryPoints4, leaderView.getVictoryPoints());
            assertEquals(LeaderCardAction.EXTRADEPOT, leaderView.getAction());
            assertFalse(leaderView.isActive());
            assertSame(resourceStack4.getResource(ResourceType.SHIELDS), leaderView.getResourcesRequired().getResource(ResourceType.SHIELDS));
            assertSame(resourceStack4.getResource(ResourceType.SERVANTS), leaderView.getResourcesRequired().getResource(ResourceType.SERVANTS));
            assertSame(resourceStack4.getResource(ResourceType.COINS), leaderView.getResourcesRequired().getResource(ResourceType.COINS));
            assertSame(resourceStack4.getResource(ResourceType.STONES), leaderView.getResourcesRequired().getResource(ResourceType.STONES));
            assertSame(leaderRequirements.getBlueCardLv1(), leaderView.getCardsRequired().getBlueCardLv1());
            assertSame(leaderRequirements.getBlueCardLv2(), leaderView.getCardsRequired().getBlueCardLv2());
            assertSame(leaderRequirements.getBlueCardLv3(), leaderView.getCardsRequired().getBlueCardLv3());
            assertSame(leaderRequirements.getPurpleCardLv1(), leaderView.getCardsRequired().getPurpleCardLv1());
            assertSame(leaderRequirements.getPurpleCardLv2(), leaderView.getCardsRequired().getPurpleCardLv2());
            assertSame(leaderRequirements.getPurpleCardLv3(), leaderView.getCardsRequired().getPurpleCardLv3());
            assertSame(leaderRequirements.getYellowCardLv1(), leaderView.getCardsRequired().getYellowCardLv1());
            assertSame(leaderRequirements.getYellowCardLv2(), leaderView.getCardsRequired().getYellowCardLv2());
            assertSame(leaderRequirements.getYellowCardLv3(), leaderView.getCardsRequired().getYellowCardLv3());
            assertSame(leaderRequirements.getGreenCardLv1(), leaderView.getCardsRequired().getGreenCardLv1());
            assertSame(leaderRequirements.getGreenCardLv2(), leaderView.getCardsRequired().getGreenCardLv2());
            assertSame(leaderRequirements.getGreenCardLv3(), leaderView.getCardsRequired().getGreenCardLv3());
            assertSame(leaderRequirements.getNeedBlueCard(), leaderView.getCardsRequired().getNeedBlueCard());
            assertSame(leaderRequirements.getNeedPurpleCard(), leaderView.getCardsRequired().getNeedPurpleCard());
            assertSame(leaderRequirements.getNeedYellowCard(), leaderView.getCardsRequired().getNeedYellowCard());
            assertSame(leaderRequirements.getNeedGreenCard(), leaderView.getCardsRequired().getNeedGreenCard());
            assertNull(leaderView.getDiscount());
            assertNull(leaderView.getMarble());
            assertNull(leaderView.getInput());
            assertEquals(0, leaderView.getJollyOut());
            assertEquals(0, leaderView.getFaith());
            assertSame(resourceType, leaderView.getResource());

    }

    @Test
    public void discardLeaderCardTest() {
        leaderCard1.setActive(true);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, leaderCard1 :: discard);
        assertEquals(e.getMessage(), "You cannot discard this Leader Card");
    }
}