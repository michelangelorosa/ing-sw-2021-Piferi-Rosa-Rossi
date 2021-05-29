package it.polimi.ingsw.JSONTest;

import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.JSON.BeginGameJSON;
import it.polimi.ingsw.Model.JSON.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BeginGameJSONTest {
    @Test
    public void convertTest() throws FileNotFoundException {
        BeginGameJSON begin = new BeginGameJSON();

        FaithCell[] cell = JSONReader.ReadFaithCells();
        VaticanReportSection[] vaticanReportSections = JSONReader.ReadVaticanReportSection();
        ArrayList<LeaderCard> leaderCards = JSONReader.ReadLeaderCards();
        DevelopmentCard[] developmentCards = JSONReader.ReadDevelopmentCards();
        ArrayList<Player> players = JSONReader.ReadPlayersName();


        begin.convert(developmentCards, leaderCards, 2, 1, cell, vaticanReportSections, players);
    }
}
