package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.Enums.ResourceType;
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
}
