package it.polimi.ingsw.JSONTest;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.JSON.ConvertToJSON;
import it.polimi.ingsw.Model.JSON.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Class to test ConvertToJson class
 */
public class ConvertToJSONTest {

    ConvertToJSON test = new ConvertToJSON();
    DevelopmentCard[] cards = JSONReader.ReadDevelopmentCards();
    ArrayList<LeaderCard> leaderCards = JSONReader.ReadLeaderCards();
    VaticanReportSection[] vaticanReportSections = JSONReader.ReadVaticanReportSection();
    FaithTrack track = new FaithTrack();

    /**
     * Test to check if the methods convert...String work properly in convertToJson class
     */
    @Test
    public void convertStringTest() {
        System.out.println(test.covertFaithString(track.getCells()));
        System.out.println(test.covertVaticanString(vaticanReportSections));
        System.out.println(test.covertDevelopmentCardString(cards));
        System.out.println(test.covertLeaderCardString(leaderCards));
    }

    /**
     * Test to check if the method works properly
     */
    @Test
    public void leaderConverterTest(){
        LeaderCard leaderTest = leaderCards.get(0);
        assertEquals(0, test.leaderCardAction(leaderTest));
    }

}