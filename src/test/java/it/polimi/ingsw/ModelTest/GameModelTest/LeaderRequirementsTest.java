package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.View.ReducedModel.RedLeaderRequirements;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderRequirementsTest {
    int blueCardLv1=1,purpleCardLv1=2,yellowCardLv1=1,greenCardLv1=0,blueCardLv2=0,purpleCardLv2=0,yellowCardLv2=0,greenCardLv2=4,blueCardLv3=0,purpleCardLv3=0,yellowCardLv3=2,greenCardLv3=0;
    LeaderRequirements leaderRequirements = new LeaderRequirements(blueCardLv1,purpleCardLv1,yellowCardLv1,greenCardLv1,blueCardLv2,purpleCardLv2,yellowCardLv2,greenCardLv2,blueCardLv3,purpleCardLv3,yellowCardLv3,greenCardLv3);
    ResourceStack resourceStack = new ResourceStack(1,4,6,7);
    LeaderCard leaderCard = new LeaderCard(0,0,resourceStack,leaderRequirements, Marble.WHITE);
    //Testing from LeaderCard and single inputs
    @Test
    public void leaderRequirementsTest(){
        assertSame(leaderRequirements,leaderCard.getCardsRequired());
        assertEquals(leaderRequirements.getBlueCardLv1(),blueCardLv1);
        assertEquals(leaderRequirements.getBlueCardLv2(),blueCardLv2);
        assertEquals(leaderRequirements.getBlueCardLv3(),blueCardLv3);
        assertEquals(leaderRequirements.getPurpleCardLv1(),purpleCardLv1);
        assertEquals(leaderRequirements.getPurpleCardLv2(),purpleCardLv2);
        assertEquals(leaderRequirements.getPurpleCardLv3(),purpleCardLv3);
        assertEquals(leaderRequirements.getYellowCardLv1(),yellowCardLv1);
        assertEquals(leaderRequirements.getYellowCardLv2(),yellowCardLv2);
        assertEquals(leaderRequirements.getYellowCardLv3(),yellowCardLv3);
        assertEquals(leaderRequirements.getGreenCardLv1(),greenCardLv1);
        assertEquals(leaderRequirements.getGreenCardLv2(),greenCardLv2);
        assertEquals(leaderRequirements.getGreenCardLv3(),greenCardLv3);
    }

    @Test
    public void hasRequirementTest(){
        LeaderCard[] cardsToTest = new LeaderCard[4];

        ResourceStack resourcesRequired = new ResourceStack(0,0,0,0);
        LeaderRequirements cards = new LeaderRequirements(0,0,1,1,0,0,0,0,0,0,0,0);
        ResourceStack discount = new ResourceStack(0,1,0,0);
        cardsToTest[0] = new LeaderCard(49, 2, resourcesRequired, cards, discount);

        resourcesRequired = new ResourceStack(0,0,5,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        ResourceType type = ResourceType.STONES;
        cardsToTest[1] = new LeaderCard(53, 3, resourcesRequired, cards, type);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(1,0,2,0,0,0,0,0,0,0,0,0);
        Marble marble = Marble.PURPLE;
        cardsToTest[2] = new LeaderCard(57, 5, resourcesRequired, cards, marble);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,1,0,0,0,0,0);
        ResourceStack input = new ResourceStack(1,0,0,0);
        int jollyOut = 1;
        int faithOut = 1;
        cardsToTest[3] = new LeaderCard(61, 4, resourcesRequired, cards, input, jollyOut, faithOut);


        ResourceStack cost = new ResourceStack(0, 1, 2, 3);
        ResourceStack input4 = new ResourceStack(2, 6, 13, 16);
        ResourceStack output = new ResourceStack(31, 0, 0, 0);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input4, output, 0);
        DevelopmentCard cardTest = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input4, output, 0);

        ResourceStack cost1 = new ResourceStack(10, 20, 30, 40);
        ResourceStack input1 = new ResourceStack(20, 60, 130, 160);
        ResourceStack output1 = new ResourceStack(1, 1, 1, 1);
        DevelopmentCard card1 = new DevelopmentCard(Color.PURPLE, Level.ONE, 2, 10, cost1, input1, output1, 1);
        DevelopmentCard card1Test = new DevelopmentCard(Color.BLUE, Level.ONE, 2, 10, cost1, input1, output1, 1);

        ResourceStack cost2 = new ResourceStack(4, 4, 4, 4);
        ResourceStack input2 = new ResourceStack(4, 4, 4, 4);
        ResourceStack output2 = new ResourceStack(4, 4, 4, 4);
        DevelopmentCard card2 = new DevelopmentCard(Color.YELLOW, Level.TWO, 3, 11, cost2, input2, output2, 2);
        DevelopmentCard card2Test = new DevelopmentCard(Color.BLUE, Level.ONE, 3, 11, cost2, input2, output2, 2);

        ResourceStack cost3 = new ResourceStack(5, 5, 5, 5);
        ResourceStack input3 = new ResourceStack(5, 5, 5, 5);
        ResourceStack output3 = new ResourceStack(5, 5, 5, 5);
        DevelopmentCard card3 = new DevelopmentCard(Color.GREEN, Level.THREE, 4, 12, cost3, input3, output3, 3);
        DevelopmentCard card3Test = new DevelopmentCard(Color.BLUE, Level.ONE, 4, 12, cost3, input3, output3, 3);

        DevelopmentCardSlots slots = new DevelopmentCardSlots();

        slots.addCard(1,card1);
        slots.addCard(2,card1);
        slots.addCard(1,card2);
        /*
        slots.addCard(1,card3);
        slots.addCard(0,card);
        slots.addCard(2,card2);
        slots.addCard(2,card3);

         */



        LeaderRequirements resources = slots.sumResources();
        LeaderRequirements leaderCardNeededCards = leaderCard.getCardsRequired();

        System.out.println(leaderCardNeededCards.hasRequirements(resources));
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        RedLeaderRequirements leaderView;

        leaderView = leaderRequirements.toView();

        assertEquals(blueCardLv1, leaderView.getBlueCardLv1());
        assertEquals(blueCardLv2, leaderView.getBlueCardLv2());
        assertEquals(blueCardLv3, leaderView.getBlueCardLv3());
        assertEquals(purpleCardLv1, leaderView.getPurpleCardLv1());
        assertEquals(purpleCardLv2, leaderView.getPurpleCardLv2());
        assertEquals(purpleCardLv3, leaderView.getPurpleCardLv3());
        assertEquals(yellowCardLv1, leaderView.getYellowCardLv1());
        assertEquals(yellowCardLv2, leaderView.getYellowCardLv2());
        assertEquals(yellowCardLv3, leaderView.getYellowCardLv3());
        assertEquals(greenCardLv1, leaderView.getGreenCardLv1());
        assertEquals(greenCardLv2, leaderView.getGreenCardLv2());
        assertEquals(greenCardLv3, leaderView.getGreenCardLv3());

        leaderRequirements = new LeaderRequirements(1,15,19,99);
        leaderView = leaderRequirements.toView();

        assertEquals(leaderRequirements.getNeedBlueCard(), leaderView.getNeedBlueCard());
        assertEquals(leaderRequirements.getNeedPurpleCard(), leaderView.getNeedPurpleCard());
        assertEquals(leaderRequirements.getNeedYellowCard(), leaderView.getNeedYellowCard());
        assertEquals(leaderRequirements.getNeedGreenCard(), leaderView.getNeedGreenCard());
    }
}