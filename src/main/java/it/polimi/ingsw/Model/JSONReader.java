package it.polimi.ingsw.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Model.Enums.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * JSONReader Class contains all the methods needed to read a .json file whenever it is necessary.
 */

public class JSONReader {

    public JSONReader() {

    }

    /**
     * "ReadDevelopmentCards" method reads a .json file containing all information about each card
     * and returns an array of DevelopmentCard objects containing every single development card.
     * A Try and Catch statement is needed because there could be errors while reading and/or parsing
     * the file.
     */

    public static DevelopmentCard[] ReadDevelopmentCards() {

        /**
         * A standard and handwritten file is overwritten whenever the player chooses to edit the game parameters.
         */

        File cards = new File("DevelopmentCards.json");
        //File cards = new File("DevelopmentCard2.json");

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

            //int example = fileObject.get("NumberOfCards").getAsInt();

            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("DevelopmentCard").getAsJsonArray();

            for (JsonElement cardElement : jsonArrayCards) {
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

                cardsInDeck++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (cardsInDeck < 48)
            System.err.println("Error: Not enough cards in JSON file!");
        else if (cardsInDeck > 48)
            System.err.println("Error: Too many cards in JSON file!");

        return readCards;
    }

    /**Json Reader for the vatican report section*/
    public static VaticanReportSection[] ReadVaticanReportSection(){
        File cells = new File("FaithCell2.json");


        VaticanReportSection[] readSection = new VaticanReportSection[3];
        VaticanReportSectionEnum[] vaticanReportSectionEnums = VaticanReportSectionEnum.values();
        PopeSpace[] popeSpaces = PopeSpace.values();
        int vaticans = 0;

        int begin;
        int end;
        int points;

        int idCell;
        int victoryPoints;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cells));
            JsonObject fileObject = fileElement.getAsJsonObject();


            JsonArray jsonArrayVatican = fileObject.get("VaticanReportSection").getAsJsonArray();
            for (JsonElement vaticanElement : jsonArrayVatican) {
                JsonObject vaticanJsonObject = vaticanElement.getAsJsonObject();
                begin = vaticanJsonObject.get("begin").getAsInt();
                end = vaticanJsonObject.get("end").getAsInt();
                points = vaticanJsonObject.get("points").getAsInt();

                VaticanReportSection vatican = new VaticanReportSection(begin, end, points);
                readSection[vaticans] = vatican;
                vaticans++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (vaticans < 3)
            System.err.println("Error: Not enough vatican sections in JSON file!");
        else if (vaticans > 3)
            System.err.println("Error: Too many vatican sections in JSON file!");

        return readSection;
    }

    /**Json Reader for faith cells*/
    public static FaithCell[] ReadFaithCells() {
        /**
         * A standard and handwritten file is overwritten whenever the player chooses to edit the game parameters.
         */
        //File cells = new File("FaithTrack.json");
        File cells = new File("FaithCell2.json");


        FaithCell[] readCells = new FaithCell[25];
        FaithCell cellToRead;
        int cellsRead = 0;

        VaticanReportSectionEnum[] vaticanReportSectionEnums = VaticanReportSectionEnum.values();
        PopeSpace[] popeSpaces = PopeSpace.values();

        int begin;
        int end;
        int points;

        int idCell;
        int victoryPoints;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cells));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayCards = fileObject.get("FaithCell").getAsJsonArray();

            for (JsonElement cellElement : jsonArrayCards) {
                //Gets json objects
                JsonObject faithCellJsonObject = cellElement.getAsJsonObject();

                //DevelopmentCard Data Extraction

                idCell = faithCellJsonObject.get("idCell").getAsInt();
                victoryPoints = faithCellJsonObject.get("victoryPoints").getAsInt();

                FaithCell cell = new FaithCell(idCell, victoryPoints);//, vaticanReportSectionEnums[vaticanint], popeSpaces[popeint]);

                readCells[cellsRead] = cell;

                cellsRead++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (cellsRead < 25)
            System.err.println("Error: Not enough cells in JSON file!");
        else if (cellsRead > 25)
            System.err.println("Error: Too many cells in JSON file!");

        return readCells;
    }

    /**Json Reade for Leader cards*/
    public static ArrayList<LeaderCard> ReadLeaderCards() {

        /**
         * A standard and handwritten file is overwritten whenever the player chooses to edit the game parameters.
         */
        //File cards = new File("LeaderCards.json");
        File cards = new File("LeaderCards2.json");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        LeaderCard cardToRead;

        Marble[] marbles = Marble.values();
        ResourceType[] resources = ResourceType.values();

        int cardsInDeck = 0;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cards));
            JsonObject fileObject = fileElement.getAsJsonObject();

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
            for (JsonElement cardElement : jsonArrayCards) {
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
                if (leaderAbility == 0) {
                    ResourceStack discount = new ResourceStack(leaderCardJsonObject.get("discountShields").getAsInt(), leaderCardJsonObject.get("discountServants").getAsInt(), leaderCardJsonObject.get("discountCoins").getAsInt(), leaderCardJsonObject.get("discountStones").getAsInt());
                    cardToRead = new LeaderCard(cardId, victoryPoints, resourcesRequired, leaderRequirements, discount);
                    try {
                        leaderCards.add(cardToRead);
                    } catch (Exception e) {
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    } finally {
                        cardsInDeck++;
                    }
                }
                //WHITEMARBLE
                else if (leaderAbility == 2) {
                    int marbleInt = leaderCardJsonObject.get("marbleConversion").getAsInt();
                    cardToRead = new LeaderCard(cardId, victoryPoints, resourcesRequired, leaderRequirements, marbles[marbleInt]);
                    try {
                        leaderCards.add(cardToRead);
                    } catch (Exception e) {
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    } finally {
                        cardsInDeck++;
                    }
                }
                //PRODUCTIONPOWER
                else if (leaderAbility == 3) {
                    int faith = leaderCardJsonObject.get("outputFaith").getAsInt();
                    ResourceStack resourcesInput = new ResourceStack(leaderCardJsonObject.get("inputShields").getAsInt(), leaderCardJsonObject.get("inputServants").getAsInt(), leaderCardJsonObject.get("inputCoins").getAsInt(), leaderCardJsonObject.get("inputStones").getAsInt());
                    int jollyOut = leaderCardJsonObject.get("jollyOut").getAsInt();
                    cardToRead = new LeaderCard(cardId, victoryPoints, resourcesRequired, leaderRequirements, resourcesInput, jollyOut, faith);
                    try {
                        leaderCards.add(cardToRead);
                    } catch (Exception e) {
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    } finally {
                        cardsInDeck++;
                    }
                }
                //EXTRADEPOT
                else if (leaderAbility == 1) {
                    //        public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceType resource) {
                    int resourceInt = leaderCardJsonObject.get("depotType").getAsInt();
                    cardToRead = new LeaderCard(cardId, victoryPoints, resourcesRequired, leaderRequirements, resources[resourceInt]);
                    try {
                        leaderCards.add(cardToRead);
                    } catch (Exception e) {
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    } finally {
                        cardsInDeck++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }
        if (cardsInDeck < 16)
            System.err.println("Error: Not enough cards in JSON file!");
        return leaderCards;
    }

    /**JSON Reader for the players name*/
    public static ArrayList<Player> ReadPlayersName(){
        File players = new File("NamePlayers.json");

        ArrayList<Player> readPlayer = new ArrayList<>();
        int count = 0;

        String nickname;
        int turnPosition = 0;
        boolean hasInkwell;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(players));
            JsonObject fileObject = fileElement.getAsJsonObject();


            JsonArray jsonArrayPlayer = fileObject.get("PlayersName").getAsJsonArray();
            for (JsonElement playerElement : jsonArrayPlayer) {
                JsonObject playerJsonObject = playerElement.getAsJsonObject();

                nickname = playerJsonObject.get("nickname").getAsString();

                turnPosition = playerJsonObject.get("turnPosition").getAsInt();
                hasInkwell = playerJsonObject.get("Inkwell").getAsBoolean();

                Player player = new Player(nickname, turnPosition, hasInkwell);
                readPlayer.add(player);
                count++;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (count > 4)
            System.err.println("Error: Too many players");
        return readPlayer;
    }
}
