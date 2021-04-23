package it.polimi.ingsw.Model;

import com.google.gson.Gson;


import java.util.ArrayList;
import java.io.*;
import it.polimi.ingsw.Model.*;

public class ConvertToJSON {
    public ConvertToJSON() {

    }

    /**
     * Method to convert an Array of FaithCell in a JSON file-
     * @param cells is an array of FaithCell
     * @return
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

            int id, victoryPoints, vatican = 0, pope = 0;

            id = cells[i].getIdCell();
            victoryPoints = cells[i].getVictoryPoints();

            cell[i] = new FaithCell(id, victoryPoints);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cell);
        //System.out.println("{ \"LeaderCard\": " + json + "}");
        try (PrintWriter out = new PrintWriter("FaithCell2.json")) {
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
        try (PrintWriter out = new PrintWriter("LeaderCards2.json")) {
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
        try (PrintWriter out = new PrintWriter("DevelopmentCard2.json")) {
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

            String nickname = players.get(i).getNickname();
            int turnPosition = players.get(i).getTurnPosition();
            boolean hasInkwell = players.get(i).hasInkwell();

            player.add(new PlayerJSON(nickname, turnPosition, hasInkwell));
        }

        Gson gson = new Gson();
        String json = gson.toJson(player);
        try (PrintWriter out = new PrintWriter("NamePlayers.json")) {
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
        String temp = new String();
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
        String temp = new String();
        temp = "\"LeaderCard\": " + json;
        return temp;
    }

    /**
     * Method to convert an Array of FaithCell in a String.
     * @param cells is an Array of FaithCell.
     * @return a String in json template.
     * @throws FileNotFoundException
     */
    public String covertFaithString(FaithCell[] cells) throws FileNotFoundException {
        FaithCell[] cell = new FaithCell[25];

        for(int i = 0; i < 25; i++) {

            int id, victoryPoints, vatican = 0, pope = 0;

            id = cells[i].getIdCell();
            victoryPoints = cells[i].getVictoryPoints();

            cell[i] = new FaithCell(id, victoryPoints);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cell);
        String temp = new String();
        temp = "\"FaithCells\": " + json;
        return temp;
    }

    /**
     * Method to convert an Array of VaticanReportSection in a String
     * @param reportSections is an Array of VaticanReportSection
     * @return a String in json template.
     * @throws FileNotFoundException
     */
    public String covertVaticanString(VaticanReportSection[] reportSections) throws FileNotFoundException {
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
        String temp = new String();
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

        for(int i = 0; i < players.size(); i++) {

            String nickname = players.get(i).getNickname();
            int turnPosition = players.get(i).getTurnPosition();
            boolean hasInkwell = players.get(i).hasInkwell();

            player.add(new PlayerJSON(nickname, turnPosition, hasInkwell));
        }

        Gson gson = new Gson();
        String json = gson.toJson(player);
        String temp = new String();
        temp = "\"PlayersName\": " + json;
        return temp;
    }




}