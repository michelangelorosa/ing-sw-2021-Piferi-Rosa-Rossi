package it.polimi.ingsw.Model;

import com.google.gson.Gson;
//import jdk.dynalink.NamedOperation;

import java.util.ArrayList;
import java.io.*;

public class ConvertToJSON {
    public ConvertToJSON() {

    }

    public String covertFaith(FaithCell[] cells) throws FileNotFoundException {
        FaithCellJSON[] cell = new FaithCellJSON[25];

        for(int i = 0; i < 25; i++) {

            int id, victoryPoints, vatican = 0, pope = 0;
            VaticanReportSectionEnum vaticanEnum;
            PopeSpace popeEnum;

            id = cells[i].getIdCell();
            victoryPoints = cells[i].getVictoryPoints();
            vaticanEnum = cells[i].getVaticanReportSection();
            popeEnum = cells[i].getPopeSpace();

            if(vaticanEnum == VaticanReportSectionEnum.No) vatican = 0;
            else if(vaticanEnum == VaticanReportSectionEnum.ONE) vatican = 1;
            else if(vaticanEnum == VaticanReportSectionEnum.TWO) vatican = 2;
            else if(vaticanEnum == VaticanReportSectionEnum.THREE) vatican = 3;

            if(popeEnum == PopeSpace.No) pope = 0;
            else if(popeEnum == PopeSpace.ONE) pope = 1;
            else if(popeEnum == PopeSpace.TWO) pope = 2;
            else if(popeEnum == PopeSpace.THREE) pope = 3;

            cell[i] = new FaithCellJSON(id, victoryPoints, vatican, pope);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cell);
        //System.out.println("{ \"LeaderCard\": " + json + "}");
        try (PrintWriter out = new PrintWriter("FaithCell2.json")) {
            out.println("{ \"FaithCell\": " + json + "}");
        }
        return json;
    }

    public String covertLeaderCard(ArrayList<LeaderCard> leaderCards) throws FileNotFoundException {

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

                needShields = required.getShields();
                needServants = required.getServants();
                needCoins = required.getCoins();
                needStones = required.getStones();

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
                    discountShields = discount.getShields();
                    discountServants = discount.getServants();
                    discountCoins = discount.getCoins();
                    discountStones = discount.getStones();
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
                    inputShields = inputStack.getShields();
                    inputServants = inputStack.getServants();
                    inputCoins = inputStack.getCoins();
                    inputStones = inputStack.getStones();

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
        return json;
    }

    public String covertDevelopmentCard(DevelopmentCard[] cards) throws FileNotFoundException {
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
            costShields = cost.getShields();
            costServants = cost.getServants();
            costCoins = cost.getCoins();
            costStones = cost.getStones();

            ResourceStack input = cards[i].getInput();
            inputShields = input.getShields();
            inputServants = input.getServants();
            inputCoins = input.getCoins();
            inputStones = input.getStones();

            ResourceStack output = cards[i].getOutput();
            outputShields = output.getShields();
            outputServants = output.getServants();
            outputCoins = output.getCoins();
            outputStones = output.getStones();

            outputFaith = cards[i].getOutputFaith();

            cardJSONS[i] = new DevelopmentCardJSON(color, level, cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith);
        }
        Gson gson = new Gson();
        String json = gson.toJson(cardJSONS);
        //System.out.println("{ \"LeaderCard\": " + json + "}");
        try (PrintWriter out = new PrintWriter("DevelopmentCard2.json")) {
            out.println("{ \"DevelopmentCard\": " + json + "}");
        }
        return json;
    }

}