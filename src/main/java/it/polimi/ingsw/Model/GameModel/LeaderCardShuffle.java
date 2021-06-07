package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.JSON.JSONReader;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

import java.util.ArrayList;

/**
 * Invoked at the start of the game this class shuffles the Leader Cards for the players to chose
*/
public class LeaderCardShuffle {
    private static boolean active = false;
    static ArrayList<LeaderCard> LeaderCards;

    /**
     * Reads the cards from the source and then they're put into the ArrayList LeaderCards
     * @return  ArrayList with all the leader cards
     */
    private static ArrayList<LeaderCard> leaderCardsManager(){
        if(!active){
            active=true;
            LeaderCards = JSONReader.ReadLeaderCards();
        }
        return LeaderCards;
    }

    /**
     * Removes from the ArrayList a LeaderCard that has been given to a player to choose.
     * @param remove    The index of the leader card to remove
     * @return          The ArrayList without the removed card
     */
    private static ArrayList<LeaderCard> leaderCardsManager(int remove){
        LeaderCards.remove(remove);
        return LeaderCards;
    }

    /**
     * Randomly gets 4 Leader Cards for the player to chose at the beginning of the game. The cards that aren't chosen are discarded.
     * @return      LeaderCard[4]
     */
    public static RedLeaderCard[] getLeaderShuffled(){
        ArrayList<LeaderCard> LeaderCards = leaderCardsManager();
        try{
        int size = LeaderCards.size();
        if(size<4)
            return null;
        RedLeaderCard[] cardsPicked = new RedLeaderCard[4];
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
