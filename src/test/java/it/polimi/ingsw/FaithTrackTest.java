package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

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

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSection.No, PopeSpace.No);

        assertEquals(0, test.getPlayerONE());
        assertEquals(0, test.getPlayerTWO());
        assertEquals(0, test.getPlayerTHREE());
        assertEquals(0, test.getPlayerFOUR());

        assertEquals("0 0 No No", test.getCells()[0].toString());

        players[0].setStatus(PlayerStatus.IN_GAME);
        players[1].setStatus(PlayerStatus.IDLE);
        players[2].setStatus(PlayerStatus.IDLE);
        players[3].setStatus(PlayerStatus.IN_GAME);


        assertEquals(PlayerStatus.IN_GAME, players[0].getStatus());
        assertEquals(PlayerStatus.IDLE, players[1].getStatus());
        assertEquals(PlayerStatus.IDLE, players[2].getStatus());
        assertEquals(PlayerStatus.IN_GAME, players[0].getStatus());


    }



    /**
     * Setter test for Faith Track
     */

    @Test
    public void setterTest(){

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        test.setPlayerONE(12);
        test.setPlayerTWO(15);
        test.setPlayerTHREE(1);
        test.setPlayerFOUR(8);


        assertEquals(12, test.getPlayerONE());
        assertEquals(15, test.getPlayerTWO());
        assertEquals(1, test.getPlayerTHREE());
        assertEquals(8, test.getPlayerFOUR());

        players[0].setStatus(PlayerStatus.IN_GAME);
        players[1].setStatus(PlayerStatus.IDLE);
        players[2].setStatus(PlayerStatus.IDLE);
        players[3].setStatus(PlayerStatus.IN_GAME);

        assertEquals(PlayerStatus.IN_GAME, players[0].getStatus());
        assertEquals(PlayerStatus.IDLE, players[1].getStatus());
        assertEquals(PlayerStatus.IDLE, players[2].getStatus());
        assertEquals(PlayerStatus.IN_GAME, players[0].getStatus());



    }



    /**
     * Here I check that the method "stepAhead()" works properly by passing in input the advancement of the third
     * player by 2 cells. We will use this method every time a player needs to go ahead in the Faith Track.
     */
    @Test
    public void stepAheadTest(){

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSection.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSection.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSection.THREE, PopeSpace.THREE);

        test.stepAhead(players[2], 2);
        /*
            System.out.println("1: " + test.getPlayerONE());
            System.out.println("2: " + test.getPlayerTWO());
            System.out.println("3: " + test.getPlayerTHREE());
            System.out.println("4: " + test.getPlayerFOUR());
         */

        assertEquals(test.getPlayerONE(),0);
        assertEquals(test.getPlayerTWO(), 0);
        assertEquals(test.getPlayerTHREE(), 2);
        assertEquals(test.getPlayerFOUR(),0);
    }

    /**
     * Here I check that the method "allAhead()" works properly by passing in input the player that has to stay in his
     * cell, and the advancement of all the other players that are still on the game.
     */

    @Test
    public void allAheadTest() {

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSection.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSection.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSection.THREE, PopeSpace.THREE);

        test.allAhead(players[2], players, 3);

        /*
            System.out.println("1: " + test.getPlayerONE());
            System.out.println("2: " + test.getPlayerTWO());
            System.out.println("3: " + test.getPlayerTHREE());
            System.out.println("4: " + test.getPlayerFOUR());
        */

        assertEquals(test.getPlayerONE(),3);
        assertEquals(test.getPlayerTWO(), 3);
        assertEquals(test.getPlayerTHREE(), 0);
        assertEquals(test.getPlayerFOUR(),3);
    }


    /**
     * Here I check if the method "checkFinished()" works properly by testing setting playerONE to 21 and then making
     * him advance 3 cells ahead. We can see that the first time the method returns FALSE and the second time it
     * returns TRUE.
     */

    @Test
    public void checkFinishedTest() {

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSection.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSection.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSection.THREE, PopeSpace.THREE);

        test.setPlayerONE(21);
        assertFalse(test.checkFinishedTrack());

        test.stepAhead(players[0],3);
        assertTrue(test.checkFinishedTrack());
    }


    /**
     * In this test I check if the method "getFinalPoints()" works properly by giving to all the players a number,
     * which represents the cell, and sum the victory points of their cell with the points they have gained during the
     * game.
     */

    @Test
    public void getFinalPointsTest() {

        Player[] players = new Player[4];
        players[0] = new Player("zero", 0, true);
        players[1] = new Player("one", 1, false);
        players[2] = new Player("two", 2, false);
        players[3] = new Player("three", 3, false);

        test.getCells()[0] = new FaithCell(0, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[1] = new FaithCell(1, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[2] = new FaithCell(2, 0, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[3] = new FaithCell(3, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[4] = new FaithCell(4, 1, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[5] = new FaithCell(5, 1, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[6] = new FaithCell(6, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[7] = new FaithCell(7, 2, VaticanReportSection.ONE, PopeSpace.No);
        test.getCells()[8] = new FaithCell(8, 2, VaticanReportSection.ONE, PopeSpace.ONE);
        test.getCells()[9] = new FaithCell(9, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[10] = new FaithCell(10, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[11] = new FaithCell(11, 4, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[12] = new FaithCell(12, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[13] = new FaithCell(13, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[14] = new FaithCell(14, 6, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[15] = new FaithCell(15, 9, VaticanReportSection.TWO, PopeSpace.No);
        test.getCells()[16] = new FaithCell(16, 9, VaticanReportSection.TWO, PopeSpace.TWO);
        test.getCells()[17] = new FaithCell(17, 9, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[18] = new FaithCell(18, 12, VaticanReportSection.No, PopeSpace.No);
        test.getCells()[19] = new FaithCell(19, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[20] = new FaithCell(20, 12, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[21] = new FaithCell(21, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[22] = new FaithCell(22, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[23] = new FaithCell(23, 16, VaticanReportSection.THREE, PopeSpace.No);
        test.getCells()[24] = new FaithCell(24, 20, VaticanReportSection.THREE, PopeSpace.THREE);

        test.setPlayerONE(23);
        test.stepAhead(players[0],1);

        test.setPlayerTWO(12);
        test.setPlayerTHREE(22);
        test.setPlayerFOUR(0);

        /*
            System.out.println(test.getFinalPoints(players[0], 0));
            System.out.println(test.getFinalPoints(players[1], 16));
            System.out.println(test.getFinalPoints(players[2], 24));
            System.out.println(test.getFinalPoints(players[3], 9));
         */

        assertEquals(20, test.getFinalPoints(players[0], 0));
        assertEquals(22, test.getFinalPoints(players[1], 16));
        assertEquals(40, test.getFinalPoints(players[2], 24));
        assertEquals(9, test.getFinalPoints(players[3], 9));

        /*
         * In this example we can see that every time the player goes too far in the Faith Track the counter stays on
         * cell #24 (the last of the track
         */

        test.setPlayerONE(23);
        test.stepAhead(players[0],15);
        assertEquals(20, test.getFinalPoints(players[0], 0));



    }
}