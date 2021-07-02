package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.FaithCell;
import it.polimi.ingsw.Model.GameModel.FaithTrack;
import it.polimi.ingsw.Model.GameModel.Player;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit test for FaithTrack Class.
 */
public class FaithTrackTest {

    FaithTrack test = new FaithTrack();

    /**
     * Getter test for Faith Track
     */
    @Test
    public void getterTest() {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0);//, VaticanReportSectionEnum.No, PopeSpace.No);

        assertEquals(0, players.get(0).getFaithTrackPosition());
        assertEquals(0, players.get(1).getFaithTrackPosition());
        assertEquals(0, players.get(2).getFaithTrackPosition());
        assertEquals(0, players.get(3).getFaithTrackPosition());

        test.getONE();
        test.getTWO();
        test.getTHREE();

        assertEquals("0 0", test.getCells()[0].toString());

        players.get(0).setStatus(PlayerStatus.IN_GAME);
        players.get(1).setStatus(PlayerStatus.IDLE);
        players.get(2).setStatus(PlayerStatus.IDLE);
        players.get(3).setStatus(PlayerStatus.IN_GAME);


        assertEquals(PlayerStatus.IN_GAME, players.get(0).getStatus());
        assertEquals(PlayerStatus.IDLE, players.get(1).getStatus());
        assertEquals(PlayerStatus.IDLE, players.get(2).getStatus());
        assertEquals(PlayerStatus.IN_GAME, players.get(3).getStatus());
    }

    /**
     * Setter test for Faith Track
     */

    @Test
    public void setterTest() {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        players.get(0).stepAhead(12);
        players.get(1).stepAhead(15);
        players.get(2).stepAhead(1);
        players.get(3).stepAhead(8);

        assertEquals(12, players.get(0).getFaithTrackPosition());
        assertEquals(15, players.get(1).getFaithTrackPosition());
        assertEquals(1, players.get(2).getFaithTrackPosition());
        assertEquals(8, players.get(3).getFaithTrackPosition());

        players.get(0).setStatus(PlayerStatus.IN_GAME);
        players.get(1).setStatus(PlayerStatus.IDLE);
        players.get(2).setStatus(PlayerStatus.IDLE);
        players.get(3).setStatus(PlayerStatus.IN_GAME);

        assertEquals(PlayerStatus.IN_GAME, players.get(0).getStatus());
        assertEquals(PlayerStatus.IDLE, players.get(1).getStatus());
        assertEquals(PlayerStatus.IDLE, players.get(2).getStatus());
        assertEquals(PlayerStatus.IN_GAME, players.get(3).getStatus());


    }


    /**
     * Here I check that the method "stepAhead()" works properly by passing in input the advancement of the third
     * player by 2 cells. We will use this method every time a player needs to go ahead in the Faith Track.
     */

    @Test
    public void stepAheadTest() {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0);
        test.getCells()[1] = new FaithCell(1, 0);
        test.getCells()[2] = new FaithCell(2, 0);
        test.getCells()[3] = new FaithCell(3, 1);
        test.getCells()[4] = new FaithCell(4, 1);
        test.getCells()[5] = new FaithCell(5, 1);
        test.getCells()[6] = new FaithCell(6, 2);
        test.getCells()[7] = new FaithCell(7, 2);
        test.getCells()[8] = new FaithCell(8, 2);
        test.getCells()[9] = new FaithCell(9, 4);
        test.getCells()[10] = new FaithCell(10, 4);
        test.getCells()[11] = new FaithCell(11, 4);
        test.getCells()[12] = new FaithCell(12, 6);
        test.getCells()[13] = new FaithCell(13, 6);
        test.getCells()[14] = new FaithCell(14, 6);
        test.getCells()[15] = new FaithCell(15, 9);
        test.getCells()[16] = new FaithCell(16, 9);
        test.getCells()[17] = new FaithCell(17, 9);
        test.getCells()[18] = new FaithCell(18, 12);
        test.getCells()[19] = new FaithCell(19, 12);
        test.getCells()[20] = new FaithCell(20, 12);
        test.getCells()[21] = new FaithCell(21, 16);
        test.getCells()[22] = new FaithCell(22, 16);
        test.getCells()[23] = new FaithCell(23, 16);
        test.getCells()[24] = new FaithCell(24, 20);

        players.get(2).stepAhead(2);

        System.out.println("1: " + players.get(0).getFaithTrackPosition());
        System.out.println("2: " + players.get(1).getFaithTrackPosition());
        System.out.println("3: " + players.get(2).getFaithTrackPosition());
        System.out.println("4: " + players.get(3).getFaithTrackPosition());

        assertEquals(0, players.get(0).getFaithTrackPosition());
        assertEquals(0, players.get(1).getFaithTrackPosition());
        assertEquals(2, players.get(2).getFaithTrackPosition());
        assertEquals(0, players.get(3).getFaithTrackPosition());
    }


    /**
     * Here I check that the method "allAhead()" works properly by passing in input the player that has to stay in his
     * cell, and the advancement of all the other players that are still on the game.
     */

    @Test
    public void allAheadTest() throws ModelException {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.allAhead(players.get(2), players, 3);

        assertEquals(3, players.get(0).getFaithTrackPosition());
        assertEquals(3, players.get(1).getFaithTrackPosition());
        assertEquals(0, players.get(2).getFaithTrackPosition());
        assertEquals(3, players.get(3).getFaithTrackPosition());

        players.add(new Player("FOUR", 10, false));
        ModelException e = assertThrows(ModelException.class, () -> test.allAhead(players.get(4), players, 10));
        assertEquals("Error: No player corresponds to position 10", e.getMessage());

    }


    /**
     * Here I check if the method "checkFinished()" works properly by testing setting playerONE to 21 and then making
     * him advance 3 cells ahead. We can see that the first time the method returns FALSE and the second time it
     * returns TRUE.
     */

    @Test
    public void checkFinishedTest() {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        players.get(0).stepAhead(21);
        assertFalse(test.checkFinishedTrack(players));

        players.get(0).stepAhead(3);
        assertTrue(test.checkFinishedTrack(players));
    }


    /**
     * In this test I check if the method "getFinalPoints()" works properly by giving to all the players a number,
     * which represents the cell, and sum the victory points of their cell with the points they have gained during the
     * game.
     */

    @Test
    public void getFinalPointsTest() {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        players.get(0).stepAhead(23);
        players.get(0).stepAhead(1);
        players.get(1).stepAhead(12);
        players.get(2).stepAhead(22);
        players.get(3).stepAhead(0);

        test.addFinalPoints(players);

        assertEquals(20, players.get(0).getVictoryPoints());
        assertEquals(6, players.get(1).getVictoryPoints());
        assertEquals(16, players.get(2).getVictoryPoints());
        assertEquals(0, players.get(3).getVictoryPoints());

        /*
         * In this example we can see that every time the player goes too far in the Faith Track the counter stays on
         * cell #24 (the last of the track
         */

        players.get(0).stepAhead(23);

        players.get(0).stepAhead(15);
       // assertEquals(20, test.getFinalPoints(players.get(0), players, 0));
    }

    /**
     * In this test It is checked if the method "popeSpaceSector()" works properly by a simulation of the Faith Track.
     */
    @Test
    public void popeSpaceSectorTest(){
        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        players.get(1).stepAhead(6);
        players.get(0).stepAhead(8);
        test.popeSpaceSector(players);


        players.get(2).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(1).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(2).stepAhead(14);
        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);
        players.get(3).endTrack();
        assertEquals(2, players.get(0).getVictoryPoints());
        assertEquals(5, players.get(1).getVictoryPoints());
        assertEquals(4, players.get(2).getVictoryPoints());
        assertEquals(7, players.get(3).getVictoryPoints());

        System.out.println(players.get(0).getPopeTiles()[0].getPopeTile());
        System.out.println(players.get(1).getPopeTiles()[0].getPopeTile());
        System.out.println(players.get(2).getPopeTiles()[0].getPopeTile());
        System.out.println(players.get(3).getPopeTiles()[0].getPopeTile());
        System.out.println(" ");
        System.out.println(players.get(0).getPopeTiles()[1].getPopeTile());
        System.out.println(players.get(1).getPopeTiles()[1].getPopeTile());
        System.out.println(players.get(2).getPopeTiles()[1].getPopeTile());
        System.out.println(players.get(3).getPopeTiles()[1].getPopeTile());
        System.out.println(" ");
        System.out.println(players.get(0).getPopeTiles()[2].getPopeTile());
        System.out.println(players.get(1).getPopeTiles()[2].getPopeTile());
        System.out.println(players.get(2).getPopeTiles()[2].getPopeTile());
        System.out.println(players.get(3).getPopeTiles()[2].getPopeTile());
    }
}
