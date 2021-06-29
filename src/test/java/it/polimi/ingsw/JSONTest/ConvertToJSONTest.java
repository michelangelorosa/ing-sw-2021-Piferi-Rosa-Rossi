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






    @Test
    public void convertStringTest() throws FileNotFoundException {
        System.out.println(test.covertFaithString(track.getCells()));
        System.out.println(test.covertVaticanString(vaticanReportSections));
        System.out.println(test.covertDevelopmentCardString(cards));
        System.out.println(test.covertLeaderCardString(leaderCards));
    }


}