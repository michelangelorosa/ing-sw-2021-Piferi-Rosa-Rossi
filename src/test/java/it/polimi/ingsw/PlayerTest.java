package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import org.junit.Test;

/**
 * Unit test for Player Class.
 */
public class PlayerTest {

    Player playerOne = new Player("alberto", 0, true);
    Player playerTwo = new Player("nikola", 1, false);
    Player playerThree = new Player("roberto", 2, false);
    Player playerFour = new Player("giacomo", 3, false);

    FaithTrack test = new FaithTrack();
    FaithCell[] cells = JSONReader.ReadFaithCells();

    /**
     * Constructor and Getter test for Player Class.
     */
    @Test
    public void constructorTest() {
        assertEquals("alberto", playerOne.getNickname());
        assertEquals("nikola", playerTwo.getNickname());
        assertEquals("roberto", playerThree.getNickname());
        assertEquals("giacomo", playerFour.getNickname());

        assertEquals(0, playerOne.getTurnPosition());
        assertEquals(1, playerTwo.getTurnPosition());
        assertEquals(2, playerThree.getTurnPosition());
        assertEquals(3, playerFour.getTurnPosition());

        assertTrue(playerOne.hasInkwell());
        assertFalse(playerTwo.hasInkwell());
        assertFalse(playerThree.hasInkwell());
        assertFalse(playerFour.hasInkwell());

        assertEquals(PlayerStatus.IN_GAME, playerOne.getStatus());
        assertEquals(PlayerStatus.IN_GAME, playerTwo.getStatus());
        assertEquals(PlayerStatus.IN_GAME, playerThree.getStatus());
        assertEquals(PlayerStatus.IN_GAME, playerFour.getStatus());

        assertEquals(0, playerOne.getVictoryPoints());
        assertEquals(0, playerTwo.getVictoryPoints());
        assertEquals(0, playerThree.getVictoryPoints());
        assertEquals(0, playerFour.getVictoryPoints());
    }

    /**
     * Setter test for Player Class.
     */
    @Test
    public void setterTest() {
        assertEquals(PlayerStatus.IN_GAME, playerOne.getStatus());
        playerOne.setStatus(PlayerStatus.IDLE);
        assertEquals(PlayerStatus.IDLE, playerOne.getStatus());
        playerOne.setStatus(PlayerStatus.WON);
        assertEquals(PlayerStatus.WON, playerOne.getStatus());
        playerOne.setStatus(PlayerStatus.LOST);
        assertEquals(PlayerStatus.LOST, playerOne.getStatus());
        playerOne.setStatus(PlayerStatus.IN_GAME);
        assertEquals(PlayerStatus.IN_GAME, playerOne.getStatus());
    }


    /**
     * Test for the method "stepAhead" for Player Class.
     */
    @Test
    public void stepAheadTest(){
        playerOne.stepAhead(50);
        assertEquals(24, playerOne.getFaithTrackPosition());
    }

    /**
     * Test for method "victory" for Player Class.
     */
    @Test
    public void victoryTest(){
        playerOne.stepAhead(26);
        playerOne.endTrack();
        assertEquals(24, playerOne.getFaithTrackPosition());
        assertTrue(playerOne.endTrack());
    }

    /**
     * Test for "addVictoryPoints" method in Player Class.
     */
    @Test
    public void addVictoryPointsTest() {
        playerOne.addVictoryPoints(21);
        assertEquals(21, playerOne.getVictoryPoints());
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        it.polimi.ingsw.View.ReducedModel.Player playerView;

        playerView = playerOne.toView();

        assertEquals("alberto", playerView.getNickname());
        assertEquals(0, playerView.getTurnPosition());
        assertTrue(playerView.hasInkwell());
        assertEquals(PlayerStatus.IN_GAME, playerView.getStatus());
        assertEquals(0, playerView.getVictoryPoints());
        assertEquals(3, playerView.getPopeTiles()[1].getVictoryPoints());

    }
}
