package it.polimi.ingsw.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONReader {

    public JSONReader () {

    }

    public static DevelopmentCard[] ReadDevelopmentCards() {
        File cards = new File("DevelopmentCards.json");

        DevelopmentCard[] readCards = new DevelopmentCard[49];
        DevelopmentCard cardToRead;
        int cardsInDeck = 1;

        DevelopmentCard.Color[] colors = DevelopmentCard.Color.values();
        DevelopmentCard.Level[] levels = DevelopmentCard.Level.values();

        int colorInt;

        int levelInt;

        int cardId;
        int victoryPoints;

        int costShields;
        int costServants;
        int costCoins;
        int costStones;

        int inputShields;
        int inputServants;
        int inputCoins;
        int inputStones;

        int outputShields;
        int outputServants;
        int outputCoins;
        int outputStones;

        int outputFaith;

        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(cards));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //Extracting the number of cards in the deck
            int example = fileObject.get("NumberOfCards").getAsInt();
            System.out.println("Number of cards in deck: "+ example);

            //Extracting card values from JSON
            JsonArray jsonArrayCards = fileObject.get("DevelopmentCard").getAsJsonArray();

            for(JsonElement cardElement : jsonArrayCards){
                //Gets json objects
                JsonObject developmentCardJsonObject = cardElement.getAsJsonObject();

                //DevelopmentCard Data Extraction
                colorInt = developmentCardJsonObject.get("color").getAsInt();

                levelInt = developmentCardJsonObject.get("level").getAsInt();

                cardId = developmentCardJsonObject.get("cardId").getAsInt();
                victoryPoints = developmentCardJsonObject.get("victoryPoints").getAsInt();

                costShields = developmentCardJsonObject.get("costShields").getAsInt();
                costServants = developmentCardJsonObject.get("costServants").getAsInt();
                costCoins = developmentCardJsonObject.get("costCoins").getAsInt();
                costStones = developmentCardJsonObject.get("costStones").getAsInt();

                inputShields = developmentCardJsonObject.get("inputShields").getAsInt();
                inputServants = developmentCardJsonObject.get("inputServants").getAsInt();
                inputCoins = developmentCardJsonObject.get("inputCoins").getAsInt();
                inputStones = developmentCardJsonObject.get("inputStones").getAsInt();

                outputShields = developmentCardJsonObject.get("outputShields").getAsInt();
                outputServants = developmentCardJsonObject.get("outputServants").getAsInt();
                outputCoins = developmentCardJsonObject.get("outputCoins").getAsInt();
                outputStones = developmentCardJsonObject.get("outputStones").getAsInt();

                outputFaith = developmentCardJsonObject.get("outputFaith").getAsInt();

                cardToRead = new DevelopmentCard(colors[colorInt], levels[levelInt], cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith);

                System.out.println("Number: " + cardsInDeck + " CardId: " + cardId);

                readCards[cardsInDeck] = cardToRead;

                cardsInDeck ++;
            }

            //Print card

        }catch (FileNotFoundException e){
            System.err.println("Error: Missing file!");
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error: Input file is corrupt!");
            e.printStackTrace();
        }

        if (cardsInDeck < 49)
            System.err.println("Error: Not enough cards in JSON file!");
        else if (cardsInDeck > 49)
            System.err.println("Error: Too many cards in JSON file!");

        return readCards;
    }
}
