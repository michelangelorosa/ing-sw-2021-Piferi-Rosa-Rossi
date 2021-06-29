package it.polimi.ingsw.Model.JSON;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.Persistance.Persistence;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
            Reader reader = new InputStreamReader(JSONReader.class.getResourceAsStream("/JSON/DevelopmentCards.json"), StandardCharsets.UTF_8);
            JsonElement fileElement = JsonParser.parseReader(reader);
            JsonObject fileObject = fileElement.getAsJsonObject();

            //int example = fileObject.get("NumberOfCards").getAsInt();

            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("DevelopmentCards").getAsJsonArray();

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
    public static VaticanReportSection[] ReadVaticanReportSection() {

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
            Reader reader = new InputStreamReader(JSONReader.class.getResourceAsStream("/JSON/FaithTrack.json"), StandardCharsets.UTF_8);
            JsonElement fileElement = JsonParser.parseReader(reader);
            JsonObject fileObject = fileElement.getAsJsonObject();


            JsonArray jsonArrayVatican = fileObject.get("Vatican").getAsJsonArray();
            for (JsonElement vaticanElement : jsonArrayVatican) {
                JsonObject vaticanJsonObject = vaticanElement.getAsJsonObject();
                begin = vaticanJsonObject.get("begin").getAsInt();
                end = vaticanJsonObject.get("end").getAsInt();
                points = vaticanJsonObject.get("points").getAsInt();

                VaticanReportSection vatican = new VaticanReportSection(begin, end, points);
                readSection[vaticans] = vatican;
                vaticans++;
            }

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
            Reader reader = new InputStreamReader(JSONReader.class.getResourceAsStream("/JSON/FaithTrack.json"), StandardCharsets.UTF_8);
            JsonElement fileElement = JsonParser.parseReader(reader);
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayCards = fileObject.get("FaithCells").getAsJsonArray();

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

        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        LeaderCard cardToRead;

        Marble[] marbles = Marble.values();
        ResourceType[] resources = ResourceType.values();

        int cardsInDeck = 0;

        try {
            Reader reader = new InputStreamReader(JSONReader.class.getResourceAsStream("/JSON/LeaderCards.json"), StandardCharsets.UTF_8);
            JsonElement fileElement = JsonParser.parseReader(reader);
            JsonObject fileObject = fileElement.getAsJsonObject();

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
        ArrayList<Player> readPlayer = new ArrayList<>();
        int count = 0;

        String nickname;
        int turnPosition;
        boolean hasInkwell;
        String status;
        PlayerStatus[] playerStatus = PlayerStatus.values();
        int faithTrackPosition;

        try {
            Reader reader = new InputStreamReader(JSONReader.class.getResourceAsStream("/JSON/NamePlayers.json"), StandardCharsets.UTF_8);
            JsonElement fileElement = JsonParser.parseReader(reader);
            JsonObject fileObject = fileElement.getAsJsonObject();


            JsonArray jsonArrayPlayer = fileObject.get("PlayersName").getAsJsonArray();
            for (JsonElement playerElement : jsonArrayPlayer) {
                JsonObject playerJsonObject = playerElement.getAsJsonObject();

                nickname = playerJsonObject.get("nickname").getAsString();

                turnPosition = playerJsonObject.get("turnPosition").getAsInt();
                hasInkwell = playerJsonObject.get("Inkwell").getAsBoolean();
                status = playerJsonObject.get("status").getAsString();
                faithTrackPosition = playerJsonObject.get("faithTrackPosition").getAsInt();
                Player player = new Player(nickname, turnPosition, hasInkwell);
                player.setStatus(convertStatus(status));
                player.setFaithTrackPosition(faithTrackPosition);
                readPlayer.add(player);
                count++;
            }

        //} catch (FileNotFoundException e) {
        //    System.err.println("Error: Missing file!");
        //    e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (count > 4)
            System.err.println("Error: Too many players");
        return readPlayer;
    }

    /**
     * Method used to convert PlayerStatur from a String
     * @param status the String that has to be converted
     * @return the player statur represented by the String
     */
    public static PlayerStatus convertStatus(String status){

        switch (status){
            case "IDLE":
                return PlayerStatus.IDLE;
            case "IN_GAME":
                return PlayerStatus.IN_GAME;
            case "LOST":
                return PlayerStatus.LOST;
            case "WON":
                return PlayerStatus.WON;
        }
        return PlayerStatus.IN_GAME;
    }

    /**
     * Method to read the list of player from the JSON file
     * @return an ArrayList of players
     */
    public static ArrayList<Player> playersDisconnections(DevelopmentCardTable table){
        //File players = new File("src/main/resources/JSON/NamePlayers.json");
        File players = new File("Persistence.json");
        ArrayList<Player> readPlayer = new ArrayList<>();
        int count = 0;

        String nickname;
        int turnPosition;
        boolean hasInkwell;
        String status;
        String pope0, pope1, pope2;
        PlayerStatus[] playerStatus = PlayerStatus.values();
        int faithTrackPosition;
        boolean CHOOSE_ACTION, GAME_SET,INIT_CHOOSE_RESOURCES, INIT_CHOOSE_LEADER_CARDS, MARKET_CHOOSE_ROW, CHOOSE_LEADER_CARD, ADD_RESOURCE, SWITCH_DEPOT, RESET_WAREHOUSE, END_MARKET, BUY_CARD, PAY_RESOURCE_CARD, PAY_RESOURCE_PRODUCTION, END_PAY_CARD, CHOOSE_CARD_SLOT, ACTIVATE_PRODUCTION, END_PAY_PRODUCTION, CHOOSE_PRODUCTION_OUTPUT, ACTIVATE_LEADERCARD, DELETE_LEADERCARD, END_TURN, FINAL_POINTS;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(players));
            JsonObject fileObject = fileElement.getAsJsonObject();


            JsonArray jsonArrayPlayer = fileObject.get("PlayersName").getAsJsonArray();
            for (JsonElement playerElement : jsonArrayPlayer) {
                JsonObject playerJsonObject = playerElement.getAsJsonObject();

                nickname = playerJsonObject.get("nickname").getAsString();

                turnPosition = playerJsonObject.get("turnPosition").getAsInt();
                hasInkwell = playerJsonObject.get("Inkwell").getAsBoolean();
                status = playerJsonObject.get("status").getAsString();
                faithTrackPosition = playerJsonObject.get("faithTrackPosition").getAsInt();
                Player player = new Player(nickname, turnPosition, hasInkwell);
                if(status.equals("IN_GAME")) player.setStatus(PlayerStatus.IN_GAME);
                else player.setStatus(PlayerStatus.IDLE);
                player.setFaithTrackPosition(faithTrackPosition);

                int depot0stored = playerJsonObject.get("depot0Stored").getAsInt();
                int depot1stored = playerJsonObject.get("depot1Stored").getAsInt();
                int depot2stored = playerJsonObject.get("depot2Stored").getAsInt();
                int depot0Max = playerJsonObject.get("depot0Max").getAsInt();
                int depot1Max = playerJsonObject.get("depot1Max").getAsInt();
                int depot2Max = playerJsonObject.get("depot2Max").getAsInt();

                String depot0Type = playerJsonObject.get("depot0Type").getAsString();
                String depot1Type = playerJsonObject.get("depot1Type").getAsString();
                String depot2Type = playerJsonObject.get("depot2Type").getAsString();

                ResourceType depot0 = ResourceType.NONE, depot1 = ResourceType.NONE, depot2 = ResourceType.NONE;

                Warehouse warehouse = new Warehouse();

                if(depot0Max < depot0stored) depot0stored = depot0Max;
                if(depot1Max < depot1stored) depot1stored = depot1Max;
                if(depot2Max < depot2stored) depot2stored = depot2Max;

                player.getBoard().getResourceManager().getWarehouse().addResources(depot0stored, convertType(depot0Type), player.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0]);

                player.getBoard().getResourceManager().getWarehouse().addResources(depot1stored, convertType(depot1Type), player.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1]);

                player.getBoard().getResourceManager().getWarehouse().addResources(depot2stored, convertType(depot2Type), player.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2]);

                int depotLeader1Stored = playerJsonObject.get("depotLeader1Stored").getAsInt();
                player.getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().addResources(depotLeader1Stored);

                int depotLeader2Stored = playerJsonObject.get("depotLeader2Stored").getAsInt();
                player.getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().addResources(depotLeader2Stored);

                int strongboxShields = playerJsonObject.get("strongboxShields").getAsInt();
                int strongboxServants = playerJsonObject.get("strongboxServants").getAsInt();
                int strongboxCoins = playerJsonObject.get("strongboxCoins").getAsInt();
                int strongboxStones = playerJsonObject.get("strongboxStones").getAsInt();
                Strongbox strongbox = new Strongbox();
                strongbox.addResourcesByType(strongboxShields, ResourceType.SHIELDS);
                strongbox.addResourcesByType(strongboxServants, ResourceType.SERVANTS);
                strongbox.addResourcesByType(strongboxCoins, ResourceType.COINS);
                strongbox.addResourcesByType(strongboxStones, ResourceType.STONES);
                player.getBoard().getResourceManager().setStrongbox(strongbox);

                pope0 = playerJsonObject.get("pope0").getAsString();
                switch (pope0) {
                    case "DOWN":
                        player.getPopeTiles()[0].setPopeTile(PopeTile.DOWN);
                        break;
                    case "UP":
                        player.getPopeTiles()[0].setPopeTile(PopeTile.UP);
                        break;
                    case "No":
                        player.getPopeTiles()[0].setPopeTile(PopeTile.No);
                        break;
                }

                pope1 = playerJsonObject.get("pope1").getAsString();
                switch (pope1) {
                    case "DOWN":
                        player.getPopeTiles()[1].setPopeTile(PopeTile.DOWN);
                        break;
                    case "UP":
                        player.getPopeTiles()[1].setPopeTile(PopeTile.UP);
                        break;
                    case "No":
                        player.getPopeTiles()[1].setPopeTile(PopeTile.No);
                        break;
                }

                pope2 = playerJsonObject.get("pope2").getAsString();
                switch (pope2) {
                    case "DOWN":
                        player.getPopeTiles()[2].setPopeTile(PopeTile.DOWN);
                        break;
                    case "UP":
                        player.getPopeTiles()[2].setPopeTile(PopeTile.UP);
                        break;
                    case "No":
                        player.getPopeTiles()[2].setPopeTile(PopeTile.No);
                        break;
                }

                int leaderCard0 = playerJsonObject.get("leader0").getAsInt();
                int leaderCard1 = playerJsonObject.get("leader1").getAsInt();

                int levelOccupied1 = playerJsonObject.get("levelOccupied1").getAsInt();
                int levelOccupied2 = playerJsonObject.get("levelOccupied2").getAsInt();
                int levelOccupied3 = playerJsonObject.get("levelOccupied3").getAsInt();

                int slot1card1 = playerJsonObject.get("slot1card1").getAsInt();
                int slot1card2 = playerJsonObject.get("slot1card2").getAsInt();
                int slot1card3 = playerJsonObject.get("slot1card3").getAsInt();

                int slot2card1 = playerJsonObject.get("slot2card1").getAsInt();
                int slot2card2 = playerJsonObject.get("slot2card2").getAsInt();
                int slot2card3 = playerJsonObject.get("slot2card3").getAsInt();

                int slot3card1 = playerJsonObject.get("slot3card1").getAsInt();
                int slot3card2 = playerJsonObject.get("slot3card2").getAsInt();
                int slot3card3 = playerJsonObject.get("slot3card3").getAsInt();

                if(levelOccupied1 > 0){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[0].addCard(table.getCardFromId(slot1card1));
                }
                if(levelOccupied1 > 1){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[0].addCard(table.getCardFromId(slot1card2));
                }
                if(levelOccupied1 > 2){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[0].addCard(table.getCardFromId(slot1card3));
                }

                if(levelOccupied2 > 0){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[1].addCard(table.getCardFromId(slot2card1));
                }
                if(levelOccupied2 > 1){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[1].addCard(table.getCardFromId(slot2card2));

                }
                if(levelOccupied2 > 2){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[1].addCard(table.getCardFromId(slot2card3));
                }

                if(levelOccupied3 > 0){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[2].addCard(table.getCardFromId(slot3card1));
                }
                if(levelOccupied3 > 1){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[2].addCard(table.getCardFromId(slot3card2));
                }
                if(levelOccupied3 > 2){
                    player.getBoard().getDevelopmentCardSlots().getSlots()[2].addCard(table.getCardFromId(slot3card3));
                }

                CHOOSE_ACTION = playerJsonObject.get("CHOOSE_ACTION").getAsBoolean();
                    if(CHOOSE_ACTION) player.addPossibleAction(ActionType.CHOOSE_ACTION);
                GAME_SET = playerJsonObject.get("GAME_SET").getAsBoolean();
                    if(GAME_SET) player.addPossibleAction(ActionType.GAME_SET);
                INIT_CHOOSE_RESOURCES = playerJsonObject.get("INIT_CHOOSE_RESOURCES").getAsBoolean();
                    if(INIT_CHOOSE_RESOURCES) player.addPossibleAction(ActionType.INIT_CHOOSE_RESOURCES);
                INIT_CHOOSE_LEADER_CARDS = playerJsonObject.get("INIT_CHOOSE_LEADER_CARDS").getAsBoolean();
                    if(INIT_CHOOSE_LEADER_CARDS) player.addPossibleAction(ActionType.INIT_CHOOSE_LEADER_CARDS);
                MARKET_CHOOSE_ROW = playerJsonObject.get("MARKET_CHOOSE_ROW").getAsBoolean();
                    if(MARKET_CHOOSE_ROW) player.addPossibleAction(ActionType.MARKET_CHOOSE_ROW);
                CHOOSE_LEADER_CARD = playerJsonObject.get("CHOOSE_LEADER_CARD").getAsBoolean();
                    if(CHOOSE_LEADER_CARD) player.addPossibleAction(ActionType.CHOOSE_LEADER_CARD);
                ADD_RESOURCE = playerJsonObject.get("ADD_RESOURCE").getAsBoolean();
                    if(ADD_RESOURCE) player.addPossibleAction(ActionType.ADD_RESOURCE);
                SWITCH_DEPOT = playerJsonObject.get("SWITCH_DEPOT").getAsBoolean();
                    if(SWITCH_DEPOT) player.addPossibleAction(ActionType.SWITCH_DEPOT);
                RESET_WAREHOUSE = playerJsonObject.get("RESET_WAREHOUSE").getAsBoolean();
                    if(RESET_WAREHOUSE) player.addPossibleAction(ActionType.RESET_WAREHOUSE);
                END_MARKET = playerJsonObject.get("END_MARKET").getAsBoolean();
                    if(END_MARKET) player.addPossibleAction(ActionType.END_MARKET);
                BUY_CARD = playerJsonObject.get("BUY_CARD").getAsBoolean();
                    if(BUY_CARD) player.addPossibleAction(ActionType.BUY_CARD);
                PAY_RESOURCE_CARD = playerJsonObject.get("PAY_RESOURCE_CARD").getAsBoolean();
                    if(PAY_RESOURCE_CARD) player.addPossibleAction(ActionType.PAY_RESOURCE_CARD);
                PAY_RESOURCE_PRODUCTION = playerJsonObject.get("PAY_RESOURCE_PRODUCTION").getAsBoolean();
                    if(PAY_RESOURCE_PRODUCTION) player.addPossibleAction(ActionType.PAY_RESOURCE_PRODUCTION);
                END_PAY_CARD = playerJsonObject.get("END_PAY_CARD").getAsBoolean();
                    if(END_PAY_CARD) player.addPossibleAction(ActionType.END_PAY_CARD);
                CHOOSE_CARD_SLOT = playerJsonObject.get("CHOOSE_CARD_SLOT").getAsBoolean();
                    if(CHOOSE_CARD_SLOT) player.addPossibleAction(ActionType.CHOOSE_CARD_SLOT);
                ACTIVATE_PRODUCTION = playerJsonObject.get("ACTIVATE_PRODUCTION").getAsBoolean();
                    if(ACTIVATE_PRODUCTION) player.addPossibleAction(ActionType.ACTIVATE_PRODUCTION);
                END_PAY_PRODUCTION = playerJsonObject.get("END_PAY_PRODUCTION").getAsBoolean();
                    if(END_PAY_PRODUCTION) player.addPossibleAction(ActionType.END_PAY_PRODUCTION);
                CHOOSE_PRODUCTION_OUTPUT = playerJsonObject.get("CHOOSE_PRODUCTION_OUTPUT").getAsBoolean();
                    if(CHOOSE_PRODUCTION_OUTPUT) player.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
                ACTIVATE_LEADERCARD = playerJsonObject.get("ACTIVATE_LEADERCARD").getAsBoolean();
                    if(ACTIVATE_LEADERCARD) player.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
                DELETE_LEADERCARD = playerJsonObject.get("DELETE_LEADERCARD").getAsBoolean();
                    if(DELETE_LEADERCARD) player.addPossibleAction(ActionType.DELETE_LEADERCARD);
                END_TURN = playerJsonObject.get("END_TURN").getAsBoolean();
                    if(END_TURN) player.addPossibleAction(ActionType.END_TURN);
                FINAL_POINTS = playerJsonObject.get("FINAL_POINTS").getAsBoolean();
                    if(FINAL_POINTS) player.addPossibleAction(ActionType.FINAL_POINTS);


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

    public static ResourceType convertType(String type){
        switch (type){
            case "SHIELDS":
                return ResourceType.SHIELDS;
            case "SERVANTS":
                return ResourceType.SERVANTS;
            case "COINS":
                return ResourceType.COINS;
            case "STONES":
                return ResourceType.STONES;
            default:
                return ResourceType.NONE;
        }
    }
    /**
     * Method to read the developmentCardDeck from the JSON file
     * @return a DevelopmentCardDeck that will be set in the game
     */
    public static DevelopmentCardDeck[][] developmentCardDecksDisconnection(){
        File developmentCardsDecks = new File("Persistence.json");

        int count = 1;

        DevelopmentCardDeck[][] developmentCardDecks = new DevelopmentCardDeck[3][4];
        developmentCardDecks[0][0] = new DevelopmentCardDeck(Color.GREEN, Level.THREE);
        developmentCardDecks[1][0] = new DevelopmentCardDeck(Color.GREEN, Level.TWO);
        developmentCardDecks[2][0] = new DevelopmentCardDeck(Color.GREEN, Level.ONE);

        developmentCardDecks[0][1] = new DevelopmentCardDeck(Color.BLUE, Level.THREE);
        developmentCardDecks[1][1] = new DevelopmentCardDeck(Color.BLUE, Level.TWO);
        developmentCardDecks[2][1] = new DevelopmentCardDeck(Color.BLUE, Level.ONE);

        developmentCardDecks[0][2] = new DevelopmentCardDeck(Color.YELLOW, Level.THREE);
        developmentCardDecks[1][2] = new DevelopmentCardDeck(Color.YELLOW, Level.TWO);
        developmentCardDecks[2][2] = new DevelopmentCardDeck(Color.YELLOW, Level.ONE);

        developmentCardDecks[0][3] = new DevelopmentCardDeck(Color.PURPLE, Level.THREE);
        developmentCardDecks[1][3] = new DevelopmentCardDeck(Color.PURPLE, Level.TWO);
        developmentCardDecks[2][3] = new DevelopmentCardDeck(Color.PURPLE, Level.ONE);


        int r= 0, c = 0, pos = 0;
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(developmentCardsDecks));
            JsonObject fileObject = fileElement.getAsJsonObject();

            while(count < 49){
                String i = ""+count;
                JsonArray jsonArrayDevelopments = fileObject.get(i).getAsJsonArray();
                for (JsonElement developmentElement : jsonArrayDevelopments) {
                    JsonObject developmentJsonObject = developmentElement.getAsJsonObject();

                    int color = developmentJsonObject.get("color").getAsInt();
                    int level = developmentJsonObject.get("level").getAsInt();
                    int cardId = developmentJsonObject.get("cardId").getAsInt();
                    int victoryPoints = developmentJsonObject.get("victoryPoints").getAsInt();

                    ResourceStack cost = new ResourceStack(developmentJsonObject.get("costShields").getAsInt(), developmentJsonObject.get("costServants").getAsInt(), developmentJsonObject.get("costCoins").getAsInt(), developmentJsonObject.get("costStones").getAsInt());
                    ResourceStack input = new ResourceStack(developmentJsonObject.get("inputShields").getAsInt(), developmentJsonObject.get("inputServants").getAsInt(), developmentJsonObject.get("inputCoins").getAsInt(), developmentJsonObject.get("inputStones").getAsInt());
                    ResourceStack output = new ResourceStack(developmentJsonObject.get("outputShields").getAsInt(), developmentJsonObject.get("outputServants").getAsInt(), developmentJsonObject.get("outputCoins").getAsInt(), developmentJsonObject.get("outputStones").getAsInt());
                    int outputFaith = developmentJsonObject.get("outputFaith").getAsInt();

                    developmentCardDecks[r][c].getCards()[pos] = new DevelopmentCard(Color.getColor(color), Level.getLevel(level), cardId,victoryPoints, cost, input, output, outputFaith);
                    pos++;
                    if(pos == 4){
                        pos = 0;
                        c++;
                        if(c == 4){
                            c = 0;
                            r++;
                        }
                    }
                }
                count++;
            }
        }
        catch (FileNotFoundException e) {
        System.err.println("Error: Missing file!");
        e.printStackTrace();
        } catch (Exception e) {
        System.err.println("Error: Input file is corrupt!");
        e.printStackTrace();
        }

        if (count > 49)
            System.err.println("Error: Too many cards");


        return developmentCardDecks;
    }

    /**
     * This method reads from the JSON file an array of cardsInDeck that will be set in the game
     * @return an array of cardsInDeck
     */
    public static Integer[] convertCardsInDeck(){
        File cardsInDeck = new File("Persistence.json");

        Integer[] cards = new Integer[12];
        for(int i = 0; i < 12; i++){
            cards[i] = 0;
        }


        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cardsInDeck));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayCardsInDeck = fileObject.get("cardsInDeck").getAsJsonArray();

            for (JsonElement cardsElement : jsonArrayCardsInDeck) {
                JsonObject cardsJsonObject = cardsElement.getAsJsonObject();

                cards[0] = cardsJsonObject.get("greenThree").getAsInt();
                cards[1] = cardsJsonObject.get("blueThree").getAsInt();
                cards[2] = cardsJsonObject.get("yellowThree").getAsInt();
                cards[3] = cardsJsonObject.get("purpleThree").getAsInt();
                cards[4] = cardsJsonObject.get("greenTwo").getAsInt();
                cards[5] = cardsJsonObject.get("blueTwo").getAsInt();
                cards[6] = cardsJsonObject.get("yellowTwo").getAsInt();
                cards[7] = cardsJsonObject.get("purpleTwo").getAsInt();
                cards[8] = cardsJsonObject.get("greenOne").getAsInt();
                cards[9] = cardsJsonObject.get("blueOne").getAsInt();
                cards[10] = cardsJsonObject.get("yellowOne").getAsInt();
                cards[11] = cardsJsonObject.get("purpleOne").getAsInt();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }
        return cards;
    }

    /**
     * This method reads the market from the JSON file
     * @return a new market with the marbles in the same position of the file
     */
    public static Market convertMarketPersistence(){
        File marbles = new File("Persistence.json");
        Market market = new Market();
        String[] marble = new String[13];

        for(int i = 0; i < 13; i++){
            marble[i] = "";
        }


        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(marbles));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayMarble = fileObject.get("Marble").getAsJsonArray();

            for (JsonElement marbleElement : jsonArrayMarble) {
                JsonObject marbleJsonObject = marbleElement.getAsJsonObject();

                marble[0] = marbleJsonObject.get("one").getAsString();
                marble[1] = marbleJsonObject.get("two").getAsString();
                marble[2] = marbleJsonObject.get("three").getAsString();
                marble[3] = marbleJsonObject.get("four").getAsString();
                marble[4] = marbleJsonObject.get("five").getAsString();
                marble[5] = marbleJsonObject.get("six").getAsString();
                marble[6] = marbleJsonObject.get("seven").getAsString();
                marble[7] = marbleJsonObject.get("eight").getAsString();
                marble[8] = marbleJsonObject.get("nine").getAsString();
                marble[9] = marbleJsonObject.get("ten").getAsString();
                marble[10] = marbleJsonObject.get("eleven").getAsString();
                marble[11] = marbleJsonObject.get("twelve").getAsString();
                marble[12] = marbleJsonObject.get("extra").getAsString();

                market.setMarbles(0,0, JSONReader.convertMarble(marble[0]));
                market.setMarbles(0,1, JSONReader.convertMarble(marble[1]));
                market.setMarbles(0,2, JSONReader.convertMarble(marble[2]));
                market.setMarbles(0,3, JSONReader.convertMarble(marble[3]));
                market.setMarbles(1,0, JSONReader.convertMarble(marble[4]));
                market.setMarbles(1,1, JSONReader.convertMarble(marble[5]));
                market.setMarbles(1,2, JSONReader.convertMarble(marble[6]));
                market.setMarbles(1,3, JSONReader.convertMarble(marble[7]));
                market.setMarbles(2,0, JSONReader.convertMarble(marble[8]));
                market.setMarbles(2,1, JSONReader.convertMarble(marble[9]));
                market.setMarbles(2,2, JSONReader.convertMarble(marble[10]));
                market.setMarbles(2,3, JSONReader.convertMarble(marble[11]));

                market.setExtraMarble(JSONReader.convertMarble(marble[12]));
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }
        return market;
    }

    /**
     * Method to convert the String of marble in to a Maeble
     * @param marble a String that represents the Marble
     * @return the relative Marble
     */
    public static Marble convertMarble(String marble){
        switch (marble) {
            case "BLUE":
                return Marble.BLUE;
            case "PURPLE":
                return Marble.PURPLE;
            case "YELLOW":
                return Marble.YELLOW;
            case "GREY":
                return Marble.GREY;
            case "WHITE":
                return Marble.WHITE;
            default:
                return Marble.RED;
        }
    }

    /**
     * This method converts the Leader Cards read from the json file
     * @return an ArrayList of the LeaderCards of every player
     */
    public static ArrayList<LeaderCard> convertLeaderCardPersistence(){
        File leader = new File("Persistence.json");
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        LeaderCard cardToRead;
        int cardsInDeck = 0;
        Marble[] marbles = Marble.values();
        ResourceType[] resources = ResourceType.values();

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(leader));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayLeader = fileObject.get("LeaderCards").getAsJsonArray();

            for (JsonElement leaderElement : jsonArrayLeader) {
                JsonObject leaderCardJsonObject = leaderElement.getAsJsonObject();
                byte leaderAbility = leaderCardJsonObject.get("leaderAbility").getAsByte();
                int cardId = leaderCardJsonObject.get("cardId").getAsInt();
                int victoryPoints = leaderCardJsonObject.get("victoryPoints").getAsInt();
                boolean active = leaderCardJsonObject.get("active").getAsBoolean();
                boolean discard = leaderCardJsonObject.get("discarded").getAsBoolean();
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
                        cardToRead.setActive(active);
                        if(discard) cardToRead.discard();
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
                        cardToRead.setActive(active);
                        if(discard) cardToRead.discard();
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
                        cardToRead.setActive(active);
                        if(discard) cardToRead.discard();
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
                        cardToRead.setActive(active);
                        if(discard) cardToRead.discard();
                        leaderCards.add(cardToRead);
                    } catch (Exception e) {
                        System.err.println("Error: cardToRead error!");
                        e.printStackTrace();
                    } finally {
                        cardsInDeck++;
                    }

                }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }
        return leaderCards;
    }

}
