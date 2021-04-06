package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerStatus;
import org.junit.Test;

/**
 * Unit test for Player enum-type Class.
 */

public class PlayerTest {


    @Test
    public void constructorTest(){
        Player players = new Player("zero", 0, true);

        assertEquals("zero", players.getNickname());
        assertEquals(0, players.getTurnPosition());
        assertEquals(true, players.hasInkwell());
    }

    @Test
    public void setterTest(){
        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        players[0].setStatus(PlayerStatus.IDLE);
        players[1].setStatus(PlayerStatus.IN_GAME);
        players[2].setStatus(PlayerStatus.IDLE);
        players[3].setStatus(PlayerStatus.IN_GAME);

        assertEquals(PlayerStatus.IDLE, players[0].getStatus());
        assertEquals(PlayerStatus.IN_GAME, players[1].getStatus());
        assertEquals(PlayerStatus.IDLE, players[2].getStatus());
        assertEquals(PlayerStatus.IN_GAME, players[3].getStatus());
    }

    /**
     * getter test for Player enum-type Class.
     */

    @Test
    public void getterTest() {

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        players[0].setStatus(PlayerStatus.IDLE);
        players[1].setStatus(PlayerStatus.IN_GAME);
        players[2].setStatus(PlayerStatus.IDLE);
        players[3].setStatus(PlayerStatus.IN_GAME);

        assertEquals("zero", players[0].getNickname());
        assertEquals("one", players[1].getNickname());
        assertEquals("two", players[2].getNickname());
        assertEquals("three", players[3].getNickname());


        assertEquals(0, players[0].getTurnPosition());
        assertEquals(1, players[1].getTurnPosition());
        assertEquals(2, players[2].getTurnPosition());
        assertEquals(3, players[3].getTurnPosition());

        assertEquals(true, players[0].hasInkwell());
        assertEquals(false, players[1].hasInkwell());
        assertEquals(false, players[2].hasInkwell());
        assertEquals(false, players[3].hasInkwell());

        assertEquals(PlayerStatus.IDLE, players[0].getStatus());
        assertEquals(PlayerStatus.IN_GAME, players[1].getStatus());
        assertEquals(PlayerStatus.IDLE, players[2].getStatus());
        assertEquals(PlayerStatus.IN_GAME, players[3].getStatus());
    }
}