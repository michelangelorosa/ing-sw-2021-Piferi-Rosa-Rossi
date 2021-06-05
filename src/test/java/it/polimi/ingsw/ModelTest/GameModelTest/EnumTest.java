package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.View.Utility.ANSIColors;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnumTest {

    @Test
    public void GameStatusTest() {
        GameStatus status = GameStatus.END;
        assertEquals(GameStatus.END, status);
    }

    @Test
    public void ResourceTypeTest() {
        ResourceType type = ResourceType.COINS;
        assertEquals(ANSIColors.FRONT_YELLOW + ANSIColors.COIN + ANSIColors.RESET, type.toCliSymbol());
    }

    @Test
    public void leaderCardActionTest(){
        LeaderCardAction discount = LeaderCardAction.DISCOUNT;
        assertEquals(LeaderCardAction.DISCOUNT, discount);
    }

    @Test
    public void soloActionTokensTest(){
        SoloActionToken black = SoloActionToken.BLACKCROSSPLUS2;
        assertEquals(SoloActionToken.BLACKCROSSPLUS2, black);
        black.toCli();
    }


}

