package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PopeSpace;
import org.junit.Test;

/**
 * Unit test for PopeSpace enum-type Class.
 */

public class PopeSpaceTest {

    /**
     * getter test for PopeSpace enum-type Class.
     */

    @Test
    public void getterTest() {

        assertSame(PopeSpace.No, PopeSpace.getPopeSpece(0));
        assertSame(PopeSpace.ONE, PopeSpace.getPopeSpece(1));
        assertSame(PopeSpace.TWO, PopeSpace.getPopeSpece(2));
        assertSame(PopeSpace.THREE, PopeSpace.getPopeSpece(3));

    }
}