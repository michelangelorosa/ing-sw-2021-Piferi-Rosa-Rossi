package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.JSON.JSONReader;

import java.util.ArrayList;

/**
 * Invoked at the start of the game this class shuffles the Leader Cards for the players to chose
*/
public class LeaderCardShuffle {
    private static boolean active = false;
    static ArrayList<LeaderCard> LeaderCards;
    //Reads the cards once
    private static ArrayList<LeaderCard> leaderCardsManager(){
        if(!active){
            active=true;
            LeaderCards = JSONReader.ReadLeaderCards();
        }
        return LeaderCards;
    }
    //Removes the cards
    private static ArrayList<LeaderCard> leaderCardsManager(int remove){
        LeaderCards.remove(remove);
        return LeaderCards;
    }
    //Returns 4 different Leader Cards
    public static LeaderCard[] getLeaderShuffled(){
        ArrayList<LeaderCard> LeaderCards = leaderCardsManager();
        try{
        int size = LeaderCards.size();
        if(size<4)
            return null;
        LeaderCard[] cardsPicked = new LeaderCard[4];
        for(int i=0;i<4;i++){
            int rand = (int) (Math.random() * size);
            cardsPicked[i]=LeaderCards.get(rand);
            LeaderCards=leaderCardsManager(rand);
            size--;
        }
            return cardsPicked;
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Error: no more Leader Cards!");
            e.printStackTrace();
        }
            return null;
        }
}
