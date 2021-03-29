package it.polimi.ingsw.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    public static void main( String[] args ) {
        File cards = new File("DevelopmentCards.json");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cards));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Extracting the number of cards in the deck
            Integer example = fileObject.get("NumberOfCards").getAsInt();
            System.out.println("Number of cards in deck: "+example);
            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("DevelopmentCard").getAsJsonArray();
            for(JsonElement cardElement : jsonArrayCards){
                //Gets json objects
                JsonObject developmentCardJsonObject = cardElement.getAsJsonObject();

                //Data Extraction
                int colorInt = developmentCardJsonObject.get("color").getAsInt();

                int levelInt = developmentCardJsonObject.get("level").getAsInt();

                int cardId = developmentCardJsonObject.get("cardId").getAsInt();
                int victoryPoints = developmentCardJsonObject.get("victoryPoints").getAsInt();

                int costShields = developmentCardJsonObject.get("costShields").getAsInt();
                int costServants = developmentCardJsonObject.get("costServants").getAsInt();
                int costCoins = developmentCardJsonObject.get("costCoins").getAsInt();
                int costStones = developmentCardJsonObject.get("costStones").getAsInt();

                int inputShields = developmentCardJsonObject.get("inputShields").getAsInt();
                int inputServants = developmentCardJsonObject.get("inputServants").getAsInt();
                int inputCoins = developmentCardJsonObject.get("inputCoins").getAsInt();
                int inputStones = developmentCardJsonObject.get("inputStones").getAsInt();

                int outputShields = developmentCardJsonObject.get("outputShields").getAsInt();
                int outputServants = developmentCardJsonObject.get("outputServants").getAsInt();
                int outputCoins = developmentCardJsonObject.get("outputCoins").getAsInt();
                int outputStones = developmentCardJsonObject.get("outputStones").getAsInt();

                int outputFaith = developmentCardJsonObject.get("outputFaith").getAsInt();

                System.out.println("Carta numero: "+cardId+" Colore: "+colorInt);
            }

            //Print card

        }catch (FileNotFoundException e){
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }
    }
}
