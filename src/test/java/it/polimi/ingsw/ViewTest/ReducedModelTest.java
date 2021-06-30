package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.BasicProduction;
import it.polimi.ingsw.Model.GameModel.CardSlot;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Strongbox;
import it.polimi.ingsw.View.ReducedModel.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReducedModelTest {
    Game game = new Game();
    Player player = new Player("Gatto", 1, true);
    RedBasicProduction production = new RedBasicProduction();
    RedCardSlot cardSlot = new CardSlot();
    RedStrongbox strongbox = new RedStrongbox();


    @Test
    public void GameTest(){
        game.getPlayers().add(player);
        assertEquals("Gatto", game.getCurrentPlayerNickname());
        ArrayList<String> lorenzo;
        lorenzo = game.lorenzoToCli();
        assertSame("HELLO! IM LORENZO'S BOARD!", lorenzo.get(0));
    }


    @Test
    public void RedDevelopmentCardTest(){
        assertEquals(0, cardSlot.getLevelOccupied());
    }

    @Test
    public void RedStrongboxTest(){
        assertEquals("0 0 0 0", strongbox.toString());
        assertEquals(0, strongbox.getStoredResources().getResource(ResourceType.SHIELDS));
    }
}
