package it.polimi.ingsw.Model.JSON;

import com.google.gson.Gson;


import java.util.ArrayList;
import java.io.*;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.Model.GameModel.*;

public class ConvertToJSON {
    public ConvertToJSON() {

    }

    /**
     * Method to convert an Array of FaithCell in a JSON file-
     * @param cells is an array of FaithCell
     * @throws FileNotFoundException
     */
    public void covertFaith(FaithCell[] cells, VaticanReportSection[] reportSections) throws FileNotFoundException {
        FaithCell[] cell = new FaithCell[25];
        VaticanReportSection[] vaticanReportSections = new VaticanReportSection[3];

        for(int i = 0; i < 3; i++){
            int begin, end, points;
            begin = reportSections[i].getBegin();
            end = reportSections[i].getEnd();
            points = reportSections[i].getPoints();

            vaticanReportSections[i] = new VaticanReportSection(begin, end, points);
        }

        Gson gson1 = new Gson();
        String json1 = gson1.toJson(vaticanReportSections);

        for(int i = 0; i < 25; i++) {

            int id, victoryPoints;

            id = cells[i].getIdCell();
            victoryPoints = cells[i].getVictoryPoints();

            cell[i] = new FaithCell(id, victoryPoints);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cell);
        //System.out.println("{ \"LeaderCard\": " + json + "}");
        try (PrintWriter out = new PrintWriter("src/main/resources/JSON/FaithCell2.json")) {
            out.println("{\"VaticanReportSection\": "+ json1 + "," + "\"FaithCell\": " + json + "}");
        }
        //return json;
    }

    /**
     * Method to convert an ArrayList of leaderCards in a JSON file-
     * @param leaderCards is an ArrayList of LeaderCards
     * @throws FileNotFoundException
     */
    public void covertLeaderCard(ArrayList<LeaderCard> leaderCards) throws FileNotFoundException {

        ArrayList<LeaderCardJSONAbility> leaderCard = new ArrayList<>();

        for(int i = 0; i < leaderCards.size(); i++) {

            int leaderAbility = 0, id = 0, victoryPoints = 0, needShields = 0, needServants = 0, needCoins = 0, needStones = 0, blueLv1 = 0, blueLv2 = 0, blueLv3 = 0, purpleLv1 = 0, purpleLv2 = 0, purpleLv3 = 0, yellowLv1 = 0,  yellowLv2 = 0, yellowLv3 = 0, greenLv1 = 0, greenLv2 = 0, greenLv3 = 0, discountShields = 0, discountServants = 0, discountCoins = 0, discountStones = 0, depotType = 0, marbleConversion = 0, inputShields = 0, inputServants = 0, inputCoins = 0, inputStones = 0, jollyOut = 0, outputFaith = 0;

            ResourceStack required = leaderCards.get(i).getResourcesRequired();

            LeaderRequirements leaderRequirements;

            ResourceStack discount = leaderCards.get(i).getDiscount();

            leaderRequirements = leaderCards.get(i).getCardsRequired();

            ResourceType depotTypeTemp = leaderCards.get(i).getResource();

            Marble marbleTemp = leaderCards.get(i).getMarble();

            ResourceStack inputStack = leaderCards.get(i).getInput();


            LeaderCardAction leaderAbilityEnum = leaderCards.get(i).getAction();
            if (leaderAbilityEnum == LeaderCardAction.DISCOUNT) leaderAbility = 0;
            else if (leaderAbilityEnum == LeaderCardAction.EXTRADEPOT) leaderAbility = 1;
            else if (leaderAbilityEnum == LeaderCardAction.WHITEMARBLE) leaderAbility = 2;
            else if (leaderAbilityEnum == LeaderCardAction.PRODUCTIONPOWER) leaderAbility = 3;

            id = leaderCards.get(i).getCardId();

            victoryPoints = leaderCards.get(i).getVictoryPoints();

            needShields = required.getResource(ResourceType.SHIELDS);
            needServants = required.getResource(ResourceType.SERVANTS);
            needCoins = required.getResource(ResourceType.COINS);
            needStones = required.getResource(ResourceType.STONES);

            blueLv1 = leaderRequirements.getBlueCardLv1();
            blueLv2 = leaderRequirements.getBlueCardLv2();
            blueLv3 = leaderRequirements.getBlueCardLv3();
            purpleLv1 = leaderRequirements.getPurpleCardLv1();
            purpleLv2 = leaderRequirements.getPurpleCardLv2();
            purpleLv3 = leaderRequirements.getPurpleCardLv3();
            yellowLv1 = leaderRequirements.getYellowCardLv1();
            yellowLv2 = leaderRequirements.getYellowCardLv2();
            yellowLv3 = leaderRequirements.getYellowCardLv3();
            greenLv1 = leaderRequirements.getGreenCardLv1();
            greenLv2 = leaderRequirements.getGreenCardLv2();
            greenLv3 = leaderRequirements.getGreenCardLv3();

            if (leaderAbility == 0) {
                discountShields = discount.getResource(ResourceType.SHIELDS);
                discountServants = discount.getResource(ResourceType.SERVANTS);
                discountCoins = discount.getResource(ResourceType.COINS);
                discountStones = discount.getResource(ResourceType.STONES);
            } else if (leaderAbility == 1) {
                if (depotTypeTemp == ResourceType.SHIELDS) depotType = 1;
                else if (depotTypeTemp == ResourceType.SERVANTS) depotType = 2;
                else if (depotTypeTemp == ResourceType.COINS) depotType = 3;
                else if (depotTypeTemp == ResourceType.STONES) depotType = 4;
            } else if (leaderAbility == 2) {
                if (marbleTemp == Marble.BLUE) marbleConversion = 1;
                else if (marbleTemp == Marble.PURPLE) marbleConversion = 4;
                else if (marbleTemp == Marble.YELLOW) marbleConversion = 3;
                else if (marbleTemp == Marble.GREY) marbleConversion = 2;
            } else {
                inputShields = inputStack.getResource(ResourceType.SHIELDS);
                inputServants = inputStack.getResource(ResourceType.SERVANTS);
                inputCoins = inputStack.getResource(ResourceType.COINS);
                inputStones = inputStack.getResource(ResourceType.STONES);

                jollyOut = leaderCards.get(i).getJollyOut();
                outputFaith = leaderCards.get(i).getFaith();
            }

            leaderCard.add(new LeaderCardJSONAbility(leaderAbility, id, victoryPoints, needShields, needServants, needCoins, needStones, blueLv1, blueLv2, blueLv3, purpleLv1, purpleLv2, purpleLv3, yellowLv1, yellowLv2, yellowLv3, greenLv1, greenLv2, greenLv3, discountShields, discountServants, discountCoins, discountStones, depotType, marbleConversion, inputShields, inputServants, inputCoins, inputStones, jollyOut, outputFaith));
        }

        Gson gson = new Gson();
        String json = gson.toJson(leaderCard);
        //System.out.println("{ \"LeaderCard\": " + json + "}");
        try (PrintWriter out = new PrintWriter("src/main/resources/JSON/LeaderCards2.json")) {
            out.println("{ \"LeaderCard\": " + json + "}");
        }
        //return json;
    }

    /**
     * Method to convert an Array of Development Cards in a JSON file-
     * @param cards is an Array of DevelopmentCard.
     * @throws FileNotFoundException
     */

    public void covertDevelopmentCard(DevelopmentCard[] cards) throws FileNotFoundException {
        DevelopmentCardJSON[] cardJSONS = new DevelopmentCardJSON[48];

        for(int i = 0; i < 48; i++) {

            int color = 0, level = 0, cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith;

            Color colorTemp = cards[i].getColor();
            Level levelTemp = cards[i].getLevel();

            if(colorTemp == Color.BLUE) color = 0;
            else if(colorTemp == Color.PURPLE) color = 1;
            else if(colorTemp == Color.YELLOW) color = 2;
            else if(colorTemp == Color.GREEN) color = 3;

            if(levelTemp == Level.ONE) level = 0;
            else if(levelTemp == Level.TWO) level = 1;
            else if(levelTemp == Level.THREE) level = 2;

            cardId = cards[i].getCardId();
            victoryPoints = cards[i].getVictoryPoints();

            ResourceStack cost = cards[i].getCost();
            costShields = cost.getResource(ResourceType.SHIELDS);
            costServants = cost.getResource(ResourceType.SERVANTS);
            costCoins = cost.getResource(ResourceType.COINS);
            costStones = cost.getResource(ResourceType.STONES);

            ResourceStack input = cards[i].getInput();
            inputShields = input.getResource(ResourceType.SHIELDS);
            inputServants = input.getResource(ResourceType.SERVANTS);
            inputCoins = input.getResource(ResourceType.COINS);
            inputStones = input.getResource(ResourceType.STONES);

            ResourceStack output = cards[i].getOutput();
            outputShields = output.getResource(ResourceType.SHIELDS);
            outputServants = output.getResource(ResourceType.SERVANTS);
            outputCoins = output.getResource(ResourceType.COINS);
            outputStones = output.getResource(ResourceType.STONES);

            outputFaith = cards[i].getOutputFaith();

            cardJSONS[i] = new DevelopmentCardJSON(color, level, cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cardJSONS);
        //System.out.println("{ \"LeaderCard\": " + json + "}");
        try (PrintWriter out = new PrintWriter("src/main/resources/JSON/DevelopmentCard2.json")) {
            out.println("{ \"DevelopmentCard\": " + json + "}");
        }
        //return json;
    }

    /**
     * Method to convert an Array of players in a JSON file-
     * @param players is an ArrayList of Players
     * @throws FileNotFoundException
     */
    public void covertPlayerName(ArrayList<Player> players) throws FileNotFoundException {

        ArrayList<PlayerJSON> player = new ArrayList<>();

        for(int i = 0; i < players.size(); i++) {

            PlayerStatus statusTemp = players.get(i).getStatus();
            int status;

            String nickname = players.get(i).getNickname();
            int turnPosition = players.get(i).getTurnPosition();
            boolean hasInkwell = players.get(i).hasInkwell();
            int faithTrackPosition = players.get(i).getFaithTrackPosition();
            if(statusTemp == PlayerStatus.IN_GAME) status = 0;
            else if(statusTemp == PlayerStatus.IDLE) status = 1;
            else if(statusTemp == PlayerStatus.LOST) status = 2;
            else status = 3;

            player.add(new PlayerJSON(nickname, turnPosition, hasInkwell, statusTemp, faithTrackPosition));
        }

        Gson gson = new Gson();
        String json = gson.toJson(player);
        try (PrintWriter out = new PrintWriter("src/main/resources/JSON/NamePlayers.json")) {
            out.println("{ \"PlayersName\": " + json + "}");
        }

    }

    /**
     * Method to convert an Array of FaithCell in a String.
     * @param cards is an Array of DevelopmentCard.
     * @return a String in json template.
     */
    public String covertDevelopmentCardString(DevelopmentCard[] cards)  {
        DevelopmentCardJSON[] cardJSONS = new DevelopmentCardJSON[48];

        for(int i = 0; i < 48; i++) {

            int color = 0, level = 0,cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith;

            Color colorTemp = cards[i].getColor();
            Level levelTemp = cards[i].getLevel();

            if(colorTemp == Color.BLUE) color = 0;
            else if(colorTemp == Color.PURPLE) color = 1;
            else if(colorTemp == Color.YELLOW) color = 2;
            else if(colorTemp == Color.GREEN) color = 3;

            if(levelTemp == Level.ONE) level = 0;
            else if(levelTemp == Level.TWO) level = 1;
            else if(levelTemp == Level.THREE) level = 2;

            cardId = cards[i].getCardId();
            victoryPoints = cards[i].getVictoryPoints();

            ResourceStack cost = cards[i].getCost();
            costShields = cost.getResource(ResourceType.SHIELDS);
            costServants = cost.getResource(ResourceType.SERVANTS);
            costCoins = cost.getResource(ResourceType.COINS);
            costStones = cost.getResource(ResourceType.STONES);

            ResourceStack input = cards[i].getInput();
            inputShields = input.getResource(ResourceType.SHIELDS);
            inputServants = input.getResource(ResourceType.SERVANTS);
            inputCoins = input.getResource(ResourceType.COINS);
            inputStones = input.getResource(ResourceType.SERVANTS);

            ResourceStack output = cards[i].getOutput();
            outputShields = output.getResource(ResourceType.SHIELDS);
            outputServants = output.getResource(ResourceType.SERVANTS);
            outputCoins = output.getResource(ResourceType.COINS);
            outputStones = output.getResource(ResourceType.STONES);

            outputFaith = cards[i].getOutputFaith();

            cardJSONS[i] = new DevelopmentCardJSON(color, level, cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cardJSONS);
        String temp;
        temp = "\"DevelopmentCards\": " + json;

        return temp;
    }

    /**
     * Method to convert an ArrayList of LeaderCards in a String.
     * @param leaderCards is an ArrayList of LeaderCards.
     * @return a String in json template.
     */
    public String covertLeaderCardString(ArrayList<LeaderCard> leaderCards) {

        ArrayList<LeaderCardJSONAbility> leaderCard = new ArrayList<>();

        for(int i = 0; i < leaderCards.size(); i++) {

            int leaderAbility = 0, id = 0, victoryPoints = 0, needShields = 0, needServants = 0, needCoins = 0, needStones = 0, blueLv1 = 0, blueLv2 = 0, blueLv3 = 0, purpleLv1 = 0, purpleLv2 = 0, purpleLv3 = 0, yellowLv1 = 0,  yellowLv2 = 0, yellowLv3 = 0, greenLv1 = 0, greenLv2 = 0, greenLv3 = 0, discountShields = 0, discountServants = 0, discountCoins = 0, discountStones = 0, depotType = 0, marbleConversion = 0, inputShields = 0, inputServants = 0, inputCoins = 0, inputStones = 0, jollyOut = 0, outputFaith = 0;

            ResourceStack required = leaderCards.get(i).getResourcesRequired();

            LeaderRequirements leaderRequirements;

            ResourceStack discount = leaderCards.get(i).getDiscount();

            leaderRequirements = leaderCards.get(i).getCardsRequired();

            ResourceType depotTypeTemp = leaderCards.get(i).getResource();

            Marble marbleTemp = leaderCards.get(i).getMarble();

            ResourceStack inputStack = leaderCards.get(i).getInput();


            LeaderCardAction leaderAbilityEnum = leaderCards.get(i).getAction();
            if (leaderAbilityEnum == LeaderCardAction.DISCOUNT) leaderAbility = 0;
            else if (leaderAbilityEnum == LeaderCardAction.EXTRADEPOT) leaderAbility = 1;
            else if (leaderAbilityEnum == LeaderCardAction.WHITEMARBLE) leaderAbility = 2;
            else if (leaderAbilityEnum == LeaderCardAction.PRODUCTIONPOWER) leaderAbility = 3;

            id = leaderCards.get(i).getCardId();

            victoryPoints = leaderCards.get(i).getVictoryPoints();

            needShields = required.getResource(ResourceType.SHIELDS);
            needServants = required.getResource(ResourceType.SERVANTS);
            needCoins = required.getResource(ResourceType.COINS);
            needStones = required.getResource(ResourceType.STONES);

            blueLv1 = leaderRequirements.getBlueCardLv1();
            blueLv2 = leaderRequirements.getBlueCardLv2();
            blueLv3 = leaderRequirements.getBlueCardLv3();
            purpleLv1 = leaderRequirements.getPurpleCardLv1();
            purpleLv2 = leaderRequirements.getPurpleCardLv2();
            purpleLv3 = leaderRequirements.getPurpleCardLv3();
            yellowLv1 = leaderRequirements.getYellowCardLv1();
            yellowLv2 = leaderRequirements.getYellowCardLv2();
            yellowLv3 = leaderRequirements.getYellowCardLv3();
            greenLv1 = leaderRequirements.getGreenCardLv1();
            greenLv2 = leaderRequirements.getGreenCardLv2();
            greenLv3 = leaderRequirements.getGreenCardLv3();

            if (leaderAbility == 0) {
                discountShields = discount.getResource(ResourceType.SHIELDS);
                discountServants = discount.getResource(ResourceType.SERVANTS);
                discountCoins = discount.getResource(ResourceType.COINS);
                discountStones = discount.getResource(ResourceType.STONES);
            } else if (leaderAbility == 1) {
                if (depotTypeTemp == ResourceType.SHIELDS) depotType = 1;
                else if (depotTypeTemp == ResourceType.SERVANTS) depotType = 2;
                else if (depotTypeTemp == ResourceType.COINS) depotType = 3;
                else if (depotTypeTemp == ResourceType.STONES) depotType = 4;
            } else if (leaderAbility == 2) {
                if (marbleTemp == Marble.BLUE) marbleConversion = 1;
                else if (marbleTemp == Marble.PURPLE) marbleConversion = 4;
                else if (marbleTemp == Marble.YELLOW) marbleConversion = 3;
                else if (marbleTemp == Marble.GREY) marbleConversion = 2;
            } else {
                inputShields = inputStack.getResource(ResourceType.SHIELDS);
                inputServants = inputStack.getResource(ResourceType.SERVANTS);
                inputCoins = inputStack.getResource(ResourceType.COINS);
                inputStones = inputStack.getResource(ResourceType.STONES);

                jollyOut = leaderCards.get(i).getJollyOut();
                outputFaith = leaderCards.get(i).getFaith();
            }

            leaderCard.add(new LeaderCardJSONAbility(leaderAbility, id, victoryPoints, needShields, needServants, needCoins, needStones, blueLv1, blueLv2, blueLv3, purpleLv1, purpleLv2, purpleLv3, yellowLv1, yellowLv2, yellowLv3, greenLv1, greenLv2, greenLv3, discountShields, discountServants, discountCoins, discountStones, depotType, marbleConversion, inputShields, inputServants, inputCoins, inputStones, jollyOut, outputFaith));
        }

        Gson gson = new Gson();
        String json = gson.toJson(leaderCard);
        String temp;
        temp = "\"LeaderCard\": " + json;
        return temp;
    }

    /**
     * Method to convert an Array of FaithCell in a String.
     * @param cells is an Array of FaithCell.
     * @return a String in json template.
     */
    public String covertFaithString(FaithCell[] cells){
        FaithCell[] cell = new FaithCell[25];

        for(int i = 0; i < 25; i++) {

            int id, victoryPoints, vatican = 0, pope = 0;

            id = cells[i].getIdCell();
            victoryPoints = cells[i].getVictoryPoints();

            cell[i] = new FaithCell(id, victoryPoints);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cell);
        String temp;
        temp = "\"FaithCells\": " + json;
        return temp;
    }

    /**
     * Method to convert an Array of VaticanReportSection in a String
     * @param reportSections is an Array of VaticanReportSection
     * @return a String in json template.
     */
    public String covertVaticanString(VaticanReportSection[] reportSections){
        VaticanReportSection[] vaticanReportSections = new VaticanReportSection[3];

        for(int i = 0; i < 3; i++){
            int begin, end, points;
            begin = reportSections[i].getBegin();
            end = reportSections[i].getEnd();
            points = reportSections[i].getPoints();

            vaticanReportSections[i] = new VaticanReportSection(begin, end, points);
        }

        Gson gson = new Gson();
        String json = gson.toJson(vaticanReportSections);
        String temp;
        temp = "\"Vatican\": " + json;
        return temp;
    }

    /**
     * Method to convert an ArrayList of Players in a String.
     * @param players is an ArrayList of Players.
     * @return a String in json template.
     */
    public String covertPlayerNameString(ArrayList<Player> players) {

        ArrayList<PlayerJSON> player = new ArrayList<>();

        for (Player value : players) {

            PlayerStatus statusTemp = value.getStatus();
            int status;

            String nickname = value.getNickname();
            int turnPosition = value.getTurnPosition();
            boolean hasInkwell = value.hasInkwell();
            int faithTrackPosition = value.getFaithTrackPosition();

            player.add(new PlayerJSON(nickname, turnPosition, hasInkwell, statusTemp, faithTrackPosition));
        }

        Gson gson = new Gson();
        String json = gson.toJson(player);
        String temp;
        temp = "\"PlayersName\": " + json;
        return temp;
    }



    public String createJSON(String title, String body){
        Gson gson = new Gson();
        String json = gson.toJson(body);
        return "\""+ title +"\": " + json;
    }

    public String covertPlayerDisconnectionString(ArrayList<Player> players) {

        ArrayList<PlayerJSON> player = new ArrayList<>();

        for (Player value : players) {

            PlayerStatus statusTemp = value.getStatus();
            PopeTile popeTile0 = value.getPopeTiles()[0].getPopeTile();
            PopeTile popeTile1 = value.getPopeTiles()[1].getPopeTile();
            PopeTile popeTile2 = value.getPopeTiles()[2].getPopeTile();

            String nickname = value.getNickname();
            int turnPosition = value.getTurnPosition();
            boolean hasInkwell = value.hasInkwell();
            int faithTrackPosition = value.getFaithTrackPosition();
            int victory = value.getVictoryPoints();

            int depot0Max = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getMaxResources();
            int depot0Stored = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources();
            ResourceType depot0Type = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getResourceType();
            boolean depot0IsLeader = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].isFromLeaderCardAbility();

            int depot1Max = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getMaxResources();
            int depot1Stored = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getStoredResources();
            ResourceType depot1Type = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getResourceType();
            boolean depot1IsLeader = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].isFromLeaderCardAbility();


            int depot2Max = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getMaxResources();
            int depot2Stored = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getStoredResources();
            ResourceType depot2Type = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getResourceType();
            boolean depot2IsLeader = value.getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].isFromLeaderCardAbility();
            int leader0;
            {
                if(value.getBoard().getLeaderCards()[0] != null)
                    leader0 = value.getBoard().getLeaderCards()[0].getCardId();
                else leader0 = -1;
            }
            int leader1;
            {
                if(value.getBoard().getLeaderCards()[1] != null)
                    leader1 = value.getBoard().getLeaderCards()[1].getCardId();
                else leader1 = -1;
            }
            boolean CHOOSE_ACTION, GAME_SET,INIT_CHOOSE_RESOURCES, INIT_CHOOSE_LEADER_CARDS, MARKET_CHOOSE_ROW, CHOOSE_LEADER_CARD, ADD_RESOURCE, SWITCH_DEPOT, RESET_WAREHOUSE, END_MARKET, BUY_CARD, PAY_RESOURCE_CARD, PAY_RESOURCE_PRODUCTION, END_PAY_CARD, CHOOSE_CARD_SLOT, ACTIVATE_PRODUCTION, END_PAY_PRODUCTION, CHOOSE_PRODUCTION_OUTPUT, ACTIVATE_LEADERCARD, DELETE_LEADERCARD, END_TURN, FINAL_POINTS;

            CHOOSE_ACTION = value.getPossibleActions().contains(ActionType.CHOOSE_ACTION);
            GAME_SET = value.getPossibleActions().contains(ActionType.GAME_SET);
            INIT_CHOOSE_RESOURCES = value.getPossibleActions().contains(ActionType.INIT_CHOOSE_RESOURCES);
            INIT_CHOOSE_LEADER_CARDS = value.getPossibleActions().contains(ActionType.INIT_CHOOSE_LEADER_CARDS);
            MARKET_CHOOSE_ROW = value.getPossibleActions().contains(ActionType.MARKET_CHOOSE_ROW);
            CHOOSE_LEADER_CARD = value.getPossibleActions().contains(ActionType.CHOOSE_LEADER_CARD);
            ADD_RESOURCE = value.getPossibleActions().contains(ActionType.ADD_RESOURCE);
            SWITCH_DEPOT = value.getPossibleActions().contains(ActionType.SWITCH_DEPOT);
            RESET_WAREHOUSE = value.getPossibleActions().contains(ActionType.RESET_WAREHOUSE);
            END_MARKET = value.getPossibleActions().contains(ActionType.END_MARKET);
            BUY_CARD = value.getPossibleActions().contains(ActionType.BUY_CARD);
            PAY_RESOURCE_CARD = value.getPossibleActions().contains(ActionType.PAY_RESOURCE_CARD);
            PAY_RESOURCE_PRODUCTION = value.getPossibleActions().contains(ActionType.PAY_RESOURCE_PRODUCTION);
            END_PAY_CARD = value.getPossibleActions().contains(ActionType.END_PAY_CARD);
            CHOOSE_CARD_SLOT = value.getPossibleActions().contains(ActionType.CHOOSE_CARD_SLOT);
            ACTIVATE_PRODUCTION = value.getPossibleActions().contains(ActionType.ACTIVATE_PRODUCTION);
            END_PAY_PRODUCTION = value.getPossibleActions().contains(ActionType.END_PAY_PRODUCTION);
            CHOOSE_PRODUCTION_OUTPUT = value.getPossibleActions().contains(ActionType.CHOOSE_PRODUCTION_OUTPUT);
            ACTIVATE_LEADERCARD = value.getPossibleActions().contains(ActionType.ACTIVATE_LEADERCARD);
            DELETE_LEADERCARD = value.getPossibleActions().contains(ActionType.DELETE_LEADERCARD);
            END_TURN = value.getPossibleActions().contains(ActionType.END_TURN);
            FINAL_POINTS = value.getPossibleActions().contains(ActionType.FINAL_POINTS);

            player.add(new PlayerJSON(nickname, turnPosition, hasInkwell, statusTemp, faithTrackPosition, victory, popeTile0, popeTile1, popeTile2, depot0Max, depot1Max, depot2Max, depot0Stored, depot1Stored, depot2Stored, depot0IsLeader, depot1IsLeader, depot2IsLeader, depot0Type, depot1Type, depot2Type, leader0, leader1, CHOOSE_ACTION, GAME_SET,INIT_CHOOSE_RESOURCES, INIT_CHOOSE_LEADER_CARDS, MARKET_CHOOSE_ROW, CHOOSE_LEADER_CARD, ADD_RESOURCE, SWITCH_DEPOT, RESET_WAREHOUSE, END_MARKET, BUY_CARD, PAY_RESOURCE_CARD, PAY_RESOURCE_PRODUCTION, END_PAY_CARD, CHOOSE_CARD_SLOT, ACTIVATE_PRODUCTION, END_PAY_PRODUCTION, CHOOSE_PRODUCTION_OUTPUT, ACTIVATE_LEADERCARD, DELETE_LEADERCARD, END_TURN, FINAL_POINTS));
        }

        Gson gson = new Gson();
        String json = gson.toJson(player);
        String temp;
        temp = "\"PlayersName\": " + json;
        return temp;
    }

    public String convertMarket(Game game){
        MarketJSON market = new MarketJSON(game.getMarket().getMarbles()[0][0], game.getMarket().getMarbles()[0][1], game.getMarket().getMarbles()[0][2], game.getMarket().getMarbles()[0][3], game.getMarket().getMarbles()[1][0], game.getMarket().getMarbles()[1][1], game.getMarket().getMarbles()[1][2], game.getMarket().getMarbles()[1][3], game.getMarket().getMarbles()[2][0], game.getMarket().getMarbles()[2][1], game.getMarket().getMarbles()[2][2], game.getMarket().getMarbles()[2][3], game.getMarket().getExtraMarble());
        Gson gson = new Gson();
        String json = gson.toJson(market);
        String temp;
        temp = "\"Marble\": " + json;
        return temp;
    }

    public String convertDevelopmentCardDeck(Game game){
        DevelopmentCardDeckJSON deck = new DevelopmentCardDeckJSON(game.getDevelopmentCardTable().getDeck(0,0).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(0,1).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(0,2).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(0,3).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(1,0).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(1,1).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(1,2).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(1,3).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(2,0).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(2,1).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(2,2).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(2,3).getCardsInDeck(), game.getDevelopmentCardTable().getDeck(0,0).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(0,0).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(0,0).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(0,0).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(0,1).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(0,1).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(0,1).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(0,1).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(0,2).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(0,2).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(0,2).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(0,2).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(0,3).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(0,3).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(0,3).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(0,3).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(1,0).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(1,0).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(1,0).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(1,0).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(1,1).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(1,1).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(1,1).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(1,1).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(1,2).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(1,2).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(1,2).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(1,2).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(1,3).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(1,3).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(1,3).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(1,3).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(2,0).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(2,0).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(2,0).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(2,0).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(2,1).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(2,1).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(2,1).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(2,1).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(2,2).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(2,2).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(2,2).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(2,2).getCards()[3].getCardId(), game.getDevelopmentCardTable().getDeck(2,3).getCards()[0].getCardId(), game.getDevelopmentCardTable().getDeck(2,3).getCards()[1].getCardId(), game.getDevelopmentCardTable().getDeck(2,3).getCards()[2].getCardId(), game.getDevelopmentCardTable().getDeck(2,3).getCards()[3].getCardId());
        Gson gson = new Gson();
        String json = gson.toJson(deck);
        String temp;
        temp = "\"DevelopmentCardDeck\": " + json;
        return temp;
    }


    public void convertGame(Game game) throws FileNotFoundException {
        String player = covertPlayerDisconnectionString(game.getPlayers());
        String type = "SINGLEPLAYER";
        if(game.getGameType() == GameType.MULTIPLAYER) type = "MULTIPLAYER";
        String gameType = createJSON("GameType", type);
        String market = convertMarket(game);
        String deck = convertDevelopmentCardDeck(game);

        try (PrintWriter out = new PrintWriter("src/main/resources/JSON/Disconnection.json")) {
            out.println("{"+ player + "," + gameType + "," + market + "," + deck + "," + "}");
        }
    }
}