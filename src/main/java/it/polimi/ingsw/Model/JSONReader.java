package it.polimi.ingsw.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

 /**
  * JSONReader Class contains all the methods needed to read a .json file whenever it is necessary.
  */

public class JSONReader {

    public JSONReader () {

    }

     /**
      * "ReadDevelopmentCards" method reads a .json file containing all information about each card
      * and returns an array of DevelopmentCard objects containing every single development card.
      * A Try and Catch statement is needed because there could be errors while reading and/or parsing
      * the file.
      */

    public static DevelopmentCard[] ReadDevelopmentCards() {
        File cards = new File("DevelopmentCards.json");

        DevelopmentCard[] readCards = new DevelopmentCard[48];
        DevelopmentCard cardToRead;
        int cardsInDeck = 0;

        Color[] colors = Color.values();
        Level[] levels = Level.values();

        int colorInt;
        int levelInt;
        int cardId;
        int victoryPoints;
        int outputFaith;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cards));
            JsonObject fileObject = fileElement.getAsJsonObject();

            int example = fileObject.get("NumberOfCards").getAsInt();

            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("DevelopmentCard").getAsJsonArray();

            for(JsonElement cardElement : jsonArrayCards) {
                //Gets json objects
                JsonObject developmentCardJsonObject = cardElement.getAsJsonObject();

                //DevelopmentCard Data Extraction
                colorInt = developmentCardJsonObject.get("color").getAsInt();
                levelInt = developmentCardJsonObject.get("level").getAsInt();
                cardId = developmentCardJsonObject.get("cardId").getAsInt();
                victoryPoints = developmentCardJsonObject.get("victoryPoints").getAsInt();

                ResourceStack cost = new ResourceStack(developmentCardJsonObject.get("costShields").getAsInt(), developmentCardJsonObject.get("costServants").getAsInt(), developmentCardJsonObject.get("costCoins").getAsInt(), developmentCardJsonObject.get("costStones").getAsInt());
                ResourceStack input = new ResourceStack(developmentCardJsonObject.get("inputShields").getAsInt(), developmentCardJsonObject.get("inputServants").getAsInt(), developmentCardJsonObject.get("inputCoins").getAsInt(), developmentCardJsonObject.get("inputStones").getAsInt());
                ResourceStack output = new ResourceStack(developmentCardJsonObject.get("outputShields").getAsInt(), developmentCardJsonObject.get("outputServants").getAsInt(), developmentCardJsonObject.get("outputCoins").getAsInt(), developmentCardJsonObject.get("outputStones").getAsInt());
                outputFaith = developmentCardJsonObject.get("outputFaith").getAsInt();

                //DevelopmentCard object creation and insertion into DevelopmentCard-type array.
                cardToRead = new DevelopmentCard(colors[colorInt], levels[levelInt], cardId, victoryPoints, cost, input, output, outputFaith);
                readCards[cardsInDeck] = cardToRead;

                cardsInDeck ++;
            }

        }catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (cardsInDeck < 48)
            System.err.println("Error: Not enough cards in JSON file!");
        else if (cardsInDeck > 48)
            System.err.println("Error: Too many cards in JSON file!");

        return readCards;
    }

    public static FaithCell[] ReadFaithCells() {
        File cells = new File("FaithTrack.json");

        FaithCell[] readCells = new FaithCell[25];
        FaithCell cellToRead;
        int cellsRead = 0;

        VaticanReportSectionEnum[] vaticanReportSectionEnums = VaticanReportSectionEnum.values();
        PopeSpace[] popeSpaces = PopeSpace.values();

        int vaticanint;

        int popeint;

        int idCell;
        int victoryPoints;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cells));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Extracting the number of cards in the deck
            int example = fileObject.get("NumberOfCells").getAsInt();
            //System.out.println("Number of cells in faithTrack: "+ example);
            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("FaithCell").getAsJsonArray();

            for(JsonElement cellElement : jsonArrayCards){
                //Gets json objects
                JsonObject faithCellJsonObject = cellElement.getAsJsonObject();

                //DevelopmentCard Data Extraction
                vaticanint = faithCellJsonObject.get("VaticanReportSection").getAsInt();

                popeint = faithCellJsonObject.get("PopeSpace").getAsInt();

                idCell = faithCellJsonObject.get("IdCell").getAsInt();
                victoryPoints = faithCellJsonObject.get("VictoryPoints").getAsInt();

                FaithCell cell = new FaithCell(idCell, victoryPoints, vaticanReportSectionEnums[vaticanint], popeSpaces[popeint]);

                //System.out.println("id: " + idCell + " points: " + victoryPoints + " pope Space: " +popeSpaces[popeint] + " vatican:" +vaticanReportSections[vaticanint]);

                readCells[cellsRead] = cell;

                cellsRead ++;
            }

            //Print card

        }catch (FileNotFoundException e){
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (cellsRead < 25)
            System.err.println("Error: Not enough cells in JSON file!");
        else if (cellsRead > 25)
            System.err.println("Error: Too many cells in JSON file!");

        return readCells;
    }

    public static ArrayList<LeaderCard> ReadLeaderCards() {
        File cards = new File("LeaderCards.json");
        ArrayList<LeaderCard> LeaderCards = new ArrayList<>();
        LeaderCard cardToRead;

        Marble[] marbles = Marble.values();
        ResourceType[] resources = ResourceType.values();

        int cardsInDeck = 0;

        try{
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cards));
            JsonObject fileObject = fileElement.getAsJsonObject();

            int example = fileObject.get("NumberOfCards").getAsInt();

            int blueCardLv1;
            int purpleCardLv1;
            int yellowCardLv1;
            int greenCardLv1;
            int blueCardLv2;
            int purpleCardLv2;
            int yellowCardLv2;
            int greenCardLv2;
            int blueCardLv3;
            int purpleCardLv3;
            int yellowCardLv3;
            int greenCardLv3;

            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("LeaderCard").getAsJsonArray();
            for(JsonElement cardElement : jsonArrayCards) {
                //Gets json object
                JsonObject leaderCardJsonObject = cardElement.getAsJsonObject();
                //LeaderCard Data Extraction
                byte leaderAbility = leaderCardJsonObject.get("leaderAbility").getAsByte();
                int cardId = leaderCardJsonObject.get("cardId").getAsInt();
                int victoryPoints = leaderCardJsonObject.get("victoryPoints").getAsInt();
                //Requirements in resources resourcesRequired
                ResourceStack resourcesRequired = new ResourceStack(
                        leaderCardJsonObject.get("needsShields").getAsInt(),
                        leaderCardJsonObject.get("needsServants").getAsInt(),
                        leaderCardJsonObject.get("needsCoins").getAsInt(),
                        leaderCardJsonObject.get("needsStones").getAsInt());
                //Reqirements in cards cards
                LeaderRequirements leaderRequirements = new LeaderRequirements(
                        leaderCardJsonObject.get("needsBlueCardLv1").getAsInt(),
                        leaderCardJsonObject.get("needsPurpleCardLv1").getAsInt(),
                        leaderCardJsonObject.get("needsYellowCardLv1").getAsInt(),
                        leaderCardJsonObject.get("needsGreenCardLv1").getAsInt(),
                        leaderCardJsonObject.get("needsBlueCardLv2").getAsInt(),
                        leaderCardJsonObject.get("needsPurpleCardLv2").getAsInt(),
                        leaderCardJsonObject.get("needsYellowCardLv2").getAsInt(),
                        leaderCardJsonObject.get("needsGreenCardLv2").getAsInt(),
                        leaderCardJsonObject.get("needsBlueCardLv3").getAsInt(),
                        leaderCardJsonObject.get("needsPurpleCardLv3").getAsInt(),
                        leaderCardJsonObject.get("needsYellowCardLv3").getAsInt(),
                        leaderCardJsonObject.get("needsGreenCardLv3").getAsInt());
                //DISCOUNT
                if(leaderAbility==0){
                    ResourceStack discount = new ResourceStack(leaderCardJsonObject.get("discountShields").getAsInt(),leaderCardJsonObject.get("discountServants").getAsInt(),leaderCardJsonObject.get("discountCoins").getAsInt(),leaderCardJsonObject.get("discountStones").getAsInt());
                    cardToRead = new LeaderCard(cardId,victoryPoints,resourcesRequired,leaderRequirements,discount);
                    try{
                        LeaderCards.add(cardToRead);
                    }
                    catch (Exception e){
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    }
                    finally {
                        cardsInDeck ++;
                    }
                }
                //WHITEMARBLE
                else if(leaderAbility==2){
                    int marbleInt = leaderCardJsonObject.get("marbleConversion").getAsInt();
                    cardToRead = new LeaderCard(cardId,victoryPoints,resourcesRequired,leaderRequirements,marbles[marbleInt]);
                    try{
                        LeaderCards.add(cardToRead);
                    }
                    catch (Exception e){
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    }
                    finally {
                        cardsInDeck ++;
                    }
                }
                //PRODUCTIONPOWER
                else if(leaderAbility==3){
                    int faith = leaderCardJsonObject.get("outputFaith").getAsInt();
                    ResourceStack resourcesInput = new ResourceStack(leaderCardJsonObject.get("inputShields").getAsInt(),leaderCardJsonObject.get("inputServants").getAsInt(),leaderCardJsonObject.get("inputCoins").getAsInt(),leaderCardJsonObject.get("inputStones").getAsInt());
                    int jollyOut = leaderCardJsonObject.get("jollyOut").getAsInt();
                    cardToRead = new LeaderCard(cardId,victoryPoints,resourcesRequired,leaderRequirements,resourcesInput,jollyOut,faith);
                    try{
                        LeaderCards.add(cardToRead);
                    }
                    catch (Exception e){
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    }
                    finally {
                        cardsInDeck ++;
                    }
                }
                //EXTRADEPOT
                else if(leaderAbility==1){
                    //        public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceType resource) {
                    int resourceInt = leaderCardJsonObject.get("depotType").getAsInt();
                    cardToRead = new LeaderCard(cardId,victoryPoints,resourcesRequired,leaderRequirements,resources[resourceInt]);
                    try{
                        LeaderCards.add(cardToRead);
                    }
                    catch (Exception e){
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    }
                    finally {
                        cardsInDeck ++;
                    }
                }
            }
        }catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }
        if (cardsInDeck < 16)
            System.err.println("Error: Not enough cards in JSON file!");
        return LeaderCards;
    }

}
