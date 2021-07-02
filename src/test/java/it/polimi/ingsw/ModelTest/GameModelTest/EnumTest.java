package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.View.Utility.ANSIColors;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for Enumerations Class.
 */
public class EnumTest {

    /**
     * Test to check if GameStatus enumeration works properly
     */
    @Test
    public void GameStatusTest() {
        GameStatus status = GameStatus.END;
        assertEquals(GameStatus.END, status);
    }

    /**
     * Test to check if ResourceType enumeration works properly
     */
    @Test
    public void ResourceTypeTest() {
        ResourceType type = ResourceType.COINS;
        assertEquals(ANSIColors.FRONT_YELLOW + ANSIColors.COIN + ANSIColors.RESET, type.toCliSymbol());

        ResourceType[] types = ResourceType.values();
        for(ResourceType t : types) {
            System.out.println(t.toCli());
            System.out.println(t.toCliSymbol());
            System.out.println(t.toCliNoColor());
        }
    }

    /**
     * Test to check if leaderCardAction enumeration works properly
     */
    @Test
    public void leaderCardActionTest(){
        LeaderCardAction discount = LeaderCardAction.DISCOUNT;
        assertEquals(LeaderCardAction.DISCOUNT, discount);
    }

    /**
     * Test to check if soloActionToken enumeration works properly
     */
    @Test
    public void soloActionTokensTest(){
        SoloActionToken black = SoloActionToken.BLACKCROSSPLUS2;
        assertEquals(SoloActionToken.BLACKCROSSPLUS2, black);
        black.toCli();
    }


}

