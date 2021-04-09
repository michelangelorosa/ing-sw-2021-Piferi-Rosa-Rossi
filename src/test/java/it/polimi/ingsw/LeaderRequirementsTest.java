package it.polimi.ingsw;

import it.polimi.ingsw.Model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeaderRequirementsTest {
    int blueCardLv1=1,purpleCardLv1=2,yellowCardLv1=1,greenCardLv1=0,blueCardLv2=0,purpleCardLv2=0,yellowCardLv2=0,greenCardLv2=4,blueCardLv3=0,purpleCardLv3=0,yellowCardLv3=2,greenCardLv3=0;
    LeaderRequirements leaderRequirements = new LeaderRequirements(blueCardLv1,purpleCardLv1,yellowCardLv1,greenCardLv1,blueCardLv2,purpleCardLv2,yellowCardLv2,greenCardLv2,blueCardLv3,purpleCardLv3,yellowCardLv3,greenCardLv3);
    ResourceStack resourceStack = new ResourceStack(1,4,6,7);
    LeaderCard leaderCard = new LeaderCard(0,0,resourceStack,leaderRequirements,Marble.WHITE);
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
}