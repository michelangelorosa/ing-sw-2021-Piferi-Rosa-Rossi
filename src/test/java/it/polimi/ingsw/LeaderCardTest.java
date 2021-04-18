package it.polimi.ingsw;
import it.polimi.ingsw.Model.*;
import org.junit.Test;
import static org.junit.Assert.*;



public class LeaderCardTest {
/*
* Creating 4 different Leader Cards
* */
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

    //Testing DISCOUNT type
    @Test
    public void leaderDepotTest(){
        assertEquals(cardId1,leaderCard1.getCardId());
        assertEquals(victoryPoints1,leaderCard1.getVictoryPoints());
        assertEquals(leaderCard1.getAction(),LeaderCardAction.DISCOUNT);
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
    //Testing WHITEMARBLE type
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
    //Testing PRODUCTIONPOWER type
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
    //Testing EXTRADEPOT
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
}