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

                ResourceStack cost = new ResourceStack(developmentCardJsonObject.get("costShields").getAsInt(), developmentCardJsonObject.get("costServants").getAsInt(), developmentCardJsonObject.get("costCoins").getAsInt(), developmentCardJsonObject.get("costStones").getAsInt());
                ResourceStack input = new ResourceStack(developmentCardJsonObject.get("inputShields").getAsInt(), developmentCardJsonObject.get("inputServants").getAsInt(), developmentCardJsonObject.get("inputCoins").getAsInt(), developmentCardJsonObject.get("inputStones").getAsInt());
                ResourceStack output = new ResourceStack(developmentCardJsonObject.get("outputShields").getAsInt(), developmentCardJsonObject.get("outputServants").getAsInt(), developmentCardJsonObject.get("outputCoins").getAsInt(), developmentCardJsonObject.get("outputStones").getAsInt());

                outputFaith = developmentCardJsonObject.get("outputFaith").getAsInt();

                cardToRead = new DevelopmentCard(colors[colorInt], levels[levelInt], cardId, victoryPoints, cost, input, output, outputFaith);

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

