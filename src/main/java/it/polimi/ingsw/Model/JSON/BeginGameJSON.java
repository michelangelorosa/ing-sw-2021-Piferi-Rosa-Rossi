package it.polimi.ingsw.Model.JSON;

import it.polimi.ingsw.Model.GameModel.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BeginGameJSON {

    String developmentCards = "";
    String leaderCardsString = "";
    String faithTrack = "";
    String vaticanString = "";
    String names = "";
    ConvertToJSON json = new ConvertToJSON();
    public BeginGameJSON() {
    }

    /**At the beginning of the game every player downloads a .json file which contains every detail*/
    public void convert(DevelopmentCard[] cards, ArrayList<LeaderCard> leaderCards, int inputBaseProduction, int outputBaseProduction, FaithCell[] cells, VaticanReportSection[] vaticanReportSection, ArrayList<Player> players) throws FileNotFoundException {
        developmentCards = json.covertDevelopmentCardString(cards);
        leaderCardsString = json.covertLeaderCardString(leaderCards);
        faithTrack = json.covertFaithString(cells);
        vaticanString = json.covertVaticanString(vaticanReportSection);
        names = json.covertPlayerNameString(players);

        //System.out.println(developmentCards + leaderCardsString + faithTrack + vaticanString + names +"\"basicProduction\": "+" [{ \"input\": " + inputBaseProduction + ", \"output\": " + outputBaseProduction + "}]}");
        try (PrintWriter out = new PrintWriter("FromServerToClientBegin.json")) {
            out.println("{" + developmentCards + "," + leaderCardsString + "," + faithTrack + "," + vaticanString + "," + names + "," + "\"basicProduction\": "+" [{ \"input\": " + inputBaseProduction + ", \"output\": " + outputBaseProduction + "}]}");
        }
    }
}
