package it.polimi.ingsw.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/*
From a JSON File reads the values, if succeeds those are printed on screen.
#TODO Link the values to the objects in the right class
 */

public class testJson
{
    public static void main(String[] args) {
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
            for(int i=0;i<cardsInDeck;i++)
                System.out.println(LeaderCards.get(i));
            System.out.println("Read "+cardsInDeck+" cards");
        }catch (FileNotFoundException e) {
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (cardsInDeck < 16)
            System.err.println("Error: Not enough cards in JSON file!");
    }
}
