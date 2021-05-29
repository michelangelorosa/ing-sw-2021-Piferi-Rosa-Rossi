package it.polimi.ingsw;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderCardShuffle;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Testing the Card Shuffler
 */
public class LeaderCardShuffleTest {

    @Test
    public void LeaderCardShuffleTests(){
        LeaderCard[] cardsPicked1;
        LeaderCard[] cardsPicked2;
        LeaderCard[] cardsPicked3;
        LeaderCard[] cardsPicked4;
        //Testing the number of cards, each has to be different
        cardsPicked1 = LeaderCardShuffle.getLeaderShuffled();
        cardsPicked2 = LeaderCardShuffle.getLeaderShuffled();
        cardsPicked3 = LeaderCardShuffle.getLeaderShuffled();
        cardsPicked4 = LeaderCardShuffle.getLeaderShuffled();
        assertEquals(cardsPicked1.length+cardsPicked2.length+cardsPicked3.length+cardsPicked4.length,16);
        //Testing each card picked has a different id
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertNotEquals(cardsPicked1[i].getCardId(), cardsPicked2[j].getCardId());
                assertNotEquals(cardsPicked1[i].getCardId(), cardsPicked3[j].getCardId());
                assertNotEquals(cardsPicked1[i].getCardId(), cardsPicked4[j].getCardId());
            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertNotEquals(cardsPicked2[i].getCardId(), cardsPicked3[j].getCardId());
                assertNotEquals(cardsPicked2[i].getCardId(), cardsPicked4[j].getCardId());
            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                assertNotEquals(cardsPicked3[i].getCardId(), cardsPicked4[j].getCardId());
            }
        }
        //Testing that too many calls result in Error
        assertNull(LeaderCardShuffle.getLeaderShuffled());
    }

}
