package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Color;
import org.junit.Test;

/**
 * Unit test for Color enum-type Class.
 */

public class ColorTest {

    /**
     * getter test for Color enum-type Class.
     */

    @Test
    public void getterTest() {

        assertSame(Color.BLUE, Color.getColor(0));
        assertSame(Color.PURPLE, Color.getColor(1));
        assertSame(Color.YELLOW, Color.getColor(2));
        assertSame(Color.GREEN, Color.getColor(3));

    }
}
