package it.polimi.ingsw;
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
        File cards = new File("cards.json");
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
                JsonObject DevelopmentCardJsonObject = cardElement.getAsJsonObject();

                //Data Extraction
                //Number color = DevelopmentCardJsonObject.get("color").getAsNumber();
                Integer cardId = DevelopmentCardJsonObject.get("cardId").getAsInt();

                System.out.println("Color: "+" idCard: "+cardId);
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
