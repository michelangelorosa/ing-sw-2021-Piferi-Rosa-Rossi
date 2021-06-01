package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.SinglePlayer;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for SinglePlayer class
 */

public class SinglePlayerTest {

    SinglePlayer singlePlayer = new SinglePlayer();

    /**
     * Testing the initial conditions of the SinglePlayer game
     * The token types and number has to be correct.
     * Tests also getTokens and tokenShuffle
     */
    @Test
    public void constructorTest(){
        SoloActionToken[] token = singlePlayer.getTokens(singlePlayer);
        int BLACKCROSSPLUS2=0, BLACKCROSSSHUFFLE=0, DELETE2BLUE=0, DELETE2PURPLE=0, DELETE2YELLOW=0, DELETE2GREEN=0;
        for(int i=0;i<7;i++){
            if(token[i]==SoloActionToken.BLACKCROSSPLUS2)
                BLACKCROSSPLUS2++;
            else
                if(token[i]==SoloActionToken.BLACKCROSSSHUFFLE)
                    BLACKCROSSSHUFFLE++;
                else
                    if(token[i]==SoloActionToken.DELETE2BLUE)
                        DELETE2BLUE++;
                    else
                        if (token[i]==SoloActionToken.DELETE2GREEN)
                            DELETE2GREEN++;
                        else
                            if(token[i]==SoloActionToken.DELETE2PURPLE)
                                DELETE2PURPLE++;
                            else
                                if(token[i]==SoloActionToken.DELETE2YELLOW)
                                    DELETE2YELLOW++;
        }
        assertEquals(2,BLACKCROSSPLUS2);
        assertEquals(1,BLACKCROSSSHUFFLE);
        assertEquals(1,DELETE2BLUE);
        assertEquals(1,DELETE2GREEN);
        assertEquals(1,DELETE2YELLOW);
        assertEquals(1,DELETE2PURPLE);
    }

    /**
     * Tests the actionTokenHandler method for the actions due in SinglePlayer Game
     */
    @Test
    public void actionTokenHandlerTest(){
        SoloActionToken[] soloActionTokens=singlePlayer.getTokens(singlePlayer);
    Player lorenzo,player;
    lorenzo=new Player("Lorenzo il Magnifico",-1,false);
    player=new Player("player",0,false);
    ArrayList<Player> players = new ArrayList<>();
    players.add(lorenzo);
    players.add(player);
    SoloActionToken testToken;
    //(SoloActionToken actionToken,SoloActionToken[] soloActionToken,ArrayList<Player> players){
        /*
          BlackCross+2 test: expected behaviour:
          Lorenzo's faith position has to increase by 2
         */
        singlePlayer.actionTokenHandler(SoloActionToken.BLACKCROSSPLUS2,soloActionTokens,players);
        assertEquals(2,lorenzo.getFaithTrackPosition());
        /*
          BlackCrossShuffle test: expected behaviour:
          Lorenzo's faith position has to increase by 1
          Token deck has to be shuffled
         */
        singlePlayer.actionTokenHandler(SoloActionToken.BLACKCROSSSHUFFLE,soloActionTokens,players);
        assertEquals(3,lorenzo.getFaithTrackPosition());
    }
}
