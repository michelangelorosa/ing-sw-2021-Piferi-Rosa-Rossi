package it.polimi.ingsw;

import it.polimi.ingsw.Model.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;

public class GameTest {
    Game game = new Game();

    @Test
    public void  joinTest() throws FileNotFoundException {
        //joiner(game);

        assertEquals("Giacomo", game.getPlayers().get(0).getNickname());
        assertEquals("Valentino", game.getPlayers().get(1).getNickname());
        assertEquals("Andrea", game.getPlayers().get(2).getNickname());
        assertEquals("Lorenzo", game.getPlayers().get(3).getNickname());
    }

    @Test
    public void getCurrentPlayerTest() throws FileNotFoundException {
        //joiner(game);
        System.out.println(game.getCurrentPlayer().getNickname());
    }

//TODO
    /*
    private void joiner(Game game) throws FileNotFoundException{
        game.join("Giacomo");
        game.join("Valentino");
        game.join("Andrea");
        game.join("Lorenzo");
    }
    */
}
