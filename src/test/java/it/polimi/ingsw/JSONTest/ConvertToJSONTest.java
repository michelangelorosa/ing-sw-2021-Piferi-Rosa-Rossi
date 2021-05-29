package it.polimi.ingsw.JSONTest;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.JSON.ConvertToJSON;
import it.polimi.ingsw.Model.JSON.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ConvertToJSONTest {

    ConvertToJSON test = new ConvertToJSON();
    DevelopmentCard[] cards = JSONReader.ReadDevelopmentCards();
    ArrayList<LeaderCard> leaderCards = JSONReader.ReadLeaderCards();
    VaticanReportSection[] vaticanReportSections = JSONReader.ReadVaticanReportSection();
    FaithTrack track = new FaithTrack();
    /**
     * This test is used to see if the method "convertFaith" works properly by saving all the cells in one array from
     * the file FaithTrack.json.
     * @throws FileNotFoundException if the File doesn't exists.
     */
    @Test
    public void convertFaithTest() throws FileNotFoundException {

        test.covertFaith(track.getCells(), vaticanReportSections);
    }

    /**
     * This test is used to see if the method "convertLeaderCard" works properly by saving all the cards in one arrayList from
     * the file LeaderCards.json.
     * @throws FileNotFoundException if the File doesn't exists.
     */
    @Test
    public void convertLeaderCardTest() throws FileNotFoundException {

        test.covertLeaderCard(leaderCards);
    }

    /**
     * This test is used to see if the method "convertDevelopmentCards" works properly by saving all the cards in one array from
     * the file DevelopmentCards.json.
     * @throws FileNotFoundException if the File doesn't exists.
     */
    @Test
    public void convertDevelopmentCardTest() throws FileNotFoundException {
        test.covertDevelopmentCard(cards);
    }

    @Test
    public void convertStringTest() throws FileNotFoundException {
        System.out.println(test.covertFaithString(track.getCells()));
        System.out.println(test.covertVaticanString(vaticanReportSections));
        System.out.println(test.covertDevelopmentCardString(cards));
        System.out.println(test.covertLeaderCardString(leaderCards));
    }

    @Test
    public void convertPlayers() throws FileNotFoundException {
        ArrayList<Player> players = new ArrayList<>();
        Player player0 = new Player("Zero", 0, true);
        Player player1 = new Player("One", 1, false);
        Player player2 = new Player("Two", 2, false);
        Player player3 = new Player("Three", 3, false);

        players.add(player0);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        players.get(0).setStatus(PlayerStatus.IDLE);
        players.get(0).setFaithTrackPosition(12);

        test.covertPlayerName(players);

    }

}