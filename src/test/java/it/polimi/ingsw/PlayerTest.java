package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.Player;
import org.junit.Test;

/**
 * Unit test for Player enum-type Class.
 */

public class PlayerTest {

    /**
     * getter test for Player enum-type Class.
     */

    @Test
    public void getterTest() {

        assertSame(Player.PLAYER_ONE, Player.getPlayer(0));
        assertSame(Player.PLAYER_TWO, Player.getPlayer(1));
        assertSame(Player.PLAYER_THREE, Player.getPlayer(2));
        assertSame(Player.PLAYER_FOUR, Player.getPlayer(3));
    }
}