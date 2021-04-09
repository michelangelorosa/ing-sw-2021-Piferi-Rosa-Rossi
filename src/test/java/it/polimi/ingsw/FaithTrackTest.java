package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
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
    public void getterTest(){

        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);

        assertEquals(0, players.get(0).getFaithTrackPosition());
        assertEquals(0, players.get(1).getFaithTrackPosition());
        assertEquals(0, players.get(2).getFaithTrackPosition());
        assertEquals(0, players.get(3).getFaithTrackPosition());

        assertEquals("0 0 No No", test.getCells()[0].toString());

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
    public void setterTest(){

        ArrayList<Player> players = new ArrayList <>();
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
    public void stepAheadTest(){

        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        test.stepAhead(players.get(2), players, 2);
        /*
            System.out.println("1: " + test.getPlayerONE());
            System.out.println("2: " + test.getPlayerTWO());
            System.out.println("3: " + test.getPlayerTHREE());
            System.out.println("4: " + test.getPlayerFOUR());
         */

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
    public void allAheadTest() {

        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        test.allAhead(players.get(2), players, 3);

        /*
            System.out.println("1: " + test.getPlayerONE());
            System.out.println("2: " + test.getPlayerTWO());
            System.out.println("3: " + test.getPlayerTHREE());
            System.out.println("4: " + test.getPlayerFOUR());
        */

        assertEquals(3, players.get(0).getFaithTrackPosition());
        assertEquals(3, players.get(1).getFaithTrackPosition());
        assertEquals(0, players.get(2).getFaithTrackPosition());
        assertEquals(3, players.get(3).getFaithTrackPosition());
    }


    /**
     * Here I check if the method "checkFinished()" works properly by testing setting playerONE to 21 and then making
     * him advance 3 cells ahead. We can see that the first time the method returns FALSE and the second time it
     * returns TRUE.
     */

    @Test
    public void checkFinishedTest() {

        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        players.get(0).stepAhead(21);
        assertFalse(test.checkFinishedTrack(players));

        test.stepAhead(players.get(0), players, 3);
        assertTrue(test.checkFinishedTrack(players));
    }


    /**
     * In this test I check if the method "getFinalPoints()" works properly by giving to all the players a number,
     * which represents the cell, and sum the victory points of their cell with the points they have gained during the
     * game.
     */

    @Test
    public void getFinalPointsTest() {

        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        players.get(0).stepAhead(23);
        test.stepAhead(players.get(0), players, 1);

        players.get(1).stepAhead(12);
        players.get(2).stepAhead(22);
        players.get(3).stepAhead(0);

        /*
            System.out.println(test.getFinalPoints(players[0], 0));
            System.out.println(test.getFinalPoints(players[1], 16));
            System.out.println(test.getFinalPoints(players[2], 24));
            System.out.println(test.getFinalPoints(players[3], 9));
         */

        assertEquals(20, test.getFinalPoints(players.get(0), players, 0));
        assertEquals(22, test.getFinalPoints(players.get(1), players, 16));
        assertEquals(40, test.getFinalPoints(players.get(2), players, 24));
        assertEquals(9, test.getFinalPoints(players.get(3), players, 9));

        /*
         * In this example we can see that every time the player goes too far in the Faith Track the counter stays on
         * cell #24 (the last of the track
         */

        players.get(0).stepAhead(23);;
        test.stepAhead(players.get(0), players, 15);
        assertEquals(20, test.getFinalPoints(players.get(0), players, 0));
    }

    /**
     * In this test I check if the method "popeSpaceSector()" works properly by a simutaion of the Faith Track.
     */
    @Test
    public void popeSpaceSectorTest(){
        ArrayList<Player> players = new ArrayList <>();
        players.add(new Player("zero", 0, true));
        players.add(new Player("one", 1, false));
        players.add(new Player("two", 2, false));
        players.add(new Player("three", 3, false));

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        players.get(0).stepAhead(8);
        players.get(1).stepAhead(6);
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

        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);

        players.get(3).stepAhead(6);
        test.popeSpaceSector(players);
        players.get(3).victory();
        assertEquals(2, players.get(0).getVictoryPoints());
        assertEquals(5, players.get(1).getVictoryPoints());
        assertEquals(0, players.get(2).getVictoryPoints());
        assertEquals(7, players.get(3).getVictoryPoints());
    }
}