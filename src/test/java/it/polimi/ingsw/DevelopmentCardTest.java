package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.ResourceType;
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

        assertEquals(0, card.getCost().getResource(ResourceType.SHIELDS));
        assertEquals(1, card.getCost().getResource(ResourceType.SERVANTS));
        assertEquals(2, card.getCost().getResource(ResourceType.COINS));
        assertEquals(3, card.getCost().getResource(ResourceType.STONES));

        assertEquals(2, card.getInput().getResource(ResourceType.SHIELDS));
        assertEquals(6, card.getInput().getResource(ResourceType.SERVANTS));
        assertEquals(13, card.getInput().getResource(ResourceType.COINS));
        assertEquals(16, card.getInput().getResource(ResourceType.STONES));

        assertEquals(31, card.getOutput().getResource(ResourceType.SHIELDS));
        assertEquals(0, card.getOutput().getResource(ResourceType.SERVANTS));
        assertEquals(0, card.getOutput().getResource(ResourceType.COINS));
        assertEquals(0, card.getOutput().getResource(ResourceType.STONES));

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
        assertEquals(99, card.getCost().getResource(ResourceType.SHIELDS));
        assertEquals(99, card.getCost().getResource(ResourceType.SERVANTS));
        assertEquals(99, card.getCost().getResource(ResourceType.COINS));
        assertEquals(99, card.getCost().getResource(ResourceType.STONES));

        assertEquals(88, card.getInput().getResource(ResourceType.SHIELDS));
        assertEquals(88, card.getInput().getResource(ResourceType.SERVANTS));
        assertEquals(88, card.getInput().getResource(ResourceType.COINS));
        assertEquals(88, card.getInput().getResource(ResourceType.STONES));

        assertEquals(77, card.getOutput().getResource(ResourceType.SHIELDS));
        assertEquals(77, card.getOutput().getResource(ResourceType.SERVANTS));
        assertEquals(77, card.getOutput().getResource(ResourceType.COINS));
        assertEquals(77, card.getOutput().getResource(ResourceType.STONES));

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

        assertEquals(0, cost3.getResource(ResourceType.SHIELDS));
        assertEquals(1, cost3.getResource(ResourceType.SERVANTS));
        assertEquals(2, cost3.getResource(ResourceType.COINS));
        assertEquals(3, cost3.getResource(ResourceType.STONES));

        assertEquals(2, input3.getResource(ResourceType.SHIELDS));
        assertEquals(6, input3.getResource(ResourceType.SERVANTS));
        assertEquals(13, input3.getResource(ResourceType.COINS));
        assertEquals(16, input3.getResource(ResourceType.STONES));

        assertEquals(31, output3.getResource(ResourceType.SHIELDS));
        assertEquals(0, output3.getResource(ResourceType.SERVANTS));
        assertEquals(0, output3.getResource(ResourceType.COINS));
        assertEquals(0, output3.getResource(ResourceType.STONES));

        assertEquals(12, outputFaith3);
    }

    /**
     * Test for "toString" method in DevelopmentCard Class.
     */
    @Test
    public void toStringTest() {

        String testString = "1 100 BLUE ONE 0 1 2 3 2 6 13 16 31 0 0 0 12";

        assertArrayEquals(testString.toCharArray(), card.toString().toCharArray());
    }
}
