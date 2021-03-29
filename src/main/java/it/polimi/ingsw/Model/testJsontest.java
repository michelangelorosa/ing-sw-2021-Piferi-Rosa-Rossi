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

public class testJsontest
{
    public static void main( String[] args ) {
        DevelopmentCard[] vector = new DevelopmentCard[49];

        vector = JSONReader.ReadDevelopmentCards();

        for(int i = 10; i < 15; i++) {
            DevelopmentCard card = vector[i];
            System.out.println(" cardID: " + card.getCardId() + " CostShields: " + card.getCostShields() + " CostServants: " + card.getCostServants() + " CostCoins: " + card.getCostCoins() + " CostStones: " + card.getCostStones() + " inputShields : " + card.getInputShields() + " inputServants: " + card.getInputServants() + " inputCoins: " + card.getInputCoins() + " inputStones: " + card.getInputStones() + " outputShields : " + card.getOutputShields() + " outputServants: " + card.getOutputServants() + " outputCoins: " + card.getOutputCoins() + " outputStones: " + card.getOutputStones() + " outputFaith: " + card.getOutputFaith() + " VictoryPoints: " + card.getVictoryPoints());
        }
        }
}
