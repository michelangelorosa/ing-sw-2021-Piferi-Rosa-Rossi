package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.DevelopmentCard;
import it.polimi.ingsw.Model.Level;
import it.polimi.ingsw.Model.ResourceStack;
import org.junit.Test;

/**
 * Unit test for DevelopmentCard Class.
 */

public class DevelopmentCardTest {

    ResourceStack cost = new ResourceStack(0, 1, 2, 3);
    ResourceStack input = new ResourceStack(2, 6, 13, 16);
    ResourceStack output = new ResourceStack(31, 0, 0, 0);

    int outputFaith = 12;

    DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, outputFaith);

    /**
     * Constructor test for DevelopmentCard Class.
     */

    @Test
    public void constructorTest() {

        assertSame(card.getColor(), Color.BLUE);
        assertSame(card.getLevel(), Level.ONE);
        assertEquals(1, card.getCardId());
        assertEquals(100, card.getVictoryPoints());

        assertEquals(0, card.getCost().getShields());
        assertEquals(1, card.getCost().getServants());
        assertEquals(2, card.getCost().getCoins());
        assertEquals(3, card.getCost().getStones());

        assertEquals(2, card.getInput().getShields());
        assertEquals(6, card.getInput().getServants());
        assertEquals(13, card.getInput().getCoins());
        assertEquals(16, card.getInput().getStones());

        assertEquals(31, card.getOutput().getShields());
        assertEquals(0, card.getOutput().getServants());
        assertEquals(0, card.getOutput().getCoins());
        assertEquals(0, card.getOutput().getStones());

        assertEquals(12, card.getOutputFaith());

    }

    /**
     * Setter test for DevelopmentCard Class.
     */

    @Test
    public void setterTest() {
        int victoryPoints2 = 21;

        ResourceStack cost2 = new ResourceStack(99, 99, 99, 99);
        ResourceStack input2 = new ResourceStack(88, 88, 88, 88);
        ResourceStack output2 = new ResourceStack(77, 77, 77, 77);

        int outputFaith2 = 0;

        card.setVictoryPoints(victoryPoints2);

        card.setCost(cost2);
        card.setInput(input2);
        card.setOutput(output2);
        card.setOutputFaith(outputFaith2);

        assertEquals(21, card.getVictoryPoints());
        assertEquals(99, card.getCost().getShields());
        assertEquals(99, card.getCost().getServants());
        assertEquals(99, card.getCost().getCoins());
        assertEquals(99, card.getCost().getStones());

        assertEquals(88, card.getInput().getShields());
        assertEquals(88, card.getInput().getServants());
        assertEquals(88, card.getInput().getCoins());
        assertEquals(88, card.getInput().getStones());

        assertEquals(77, card.getOutput().getShields());
        assertEquals(77, card.getOutput().getServants());
        assertEquals(77, card.getOutput().getCoins());
        assertEquals(77, card.getOutput().getStones());

        assertEquals(0, card.getOutputFaith());

    }

    /**
     * Getter test for DevelopmentCard Class.
     */

    @Test
    public void getterTest() {
        Color color3 = card.getColor();
        Level level3 = card.getLevel();

        ResourceStack cost3 = card.getCost();
        ResourceStack input3 = card.getInput();
        ResourceStack output3 = card.getOutput();

        int outputFaith3 = card.getOutputFaith();

        assertSame(color3, Color.BLUE);
        assertSame(level3, Level.ONE);
        assertEquals(1, card.getCardId());
        assertEquals(100, card.getVictoryPoints());

        assertEquals(0, cost3.getShields());
        assertEquals(1, cost3.getServants());
        assertEquals(2, cost3.getCoins());
        assertEquals(3, cost3.getStones());

        assertEquals(2, input3.getShields());
        assertEquals(6, input3.getServants());
        assertEquals(13, input3.getCoins());
        assertEquals(16, input3.getStones());

        assertEquals(31, output3.getShields());
        assertEquals(0, output3.getServants());
        assertEquals(0, output3.getCoins());
        assertEquals(0, output3.getStones());

        assertEquals(12, outputFaith3);
    }
}
