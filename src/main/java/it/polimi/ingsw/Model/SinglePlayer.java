package it.polimi.ingsw.Model;

import java.util.ArrayList;

/**
 * Handles all the interactions specific with the single player game,
 * each ActionToken is
 */
public class SinglePlayer {
    private SoloActionToken token;
    private SoloActionToken[] tokens;
    /**
     * Constructor for Single Player
     */
    public SinglePlayer(){
        this.token=SoloActionToken.BLACKCROSSPLUS2;
        this.tokens=tokenShuffle();
    }

    /**
     * Handles all of the different solo action token actions.
     * Player 0 is always Lorenzo Il Magnifico,
     * Player 1 is always the Player
     * @return the solo action token deck
     */
    public SoloActionToken[] actionTokenHandler(SoloActionToken actionToken,SoloActionToken[] soloActionToken,ArrayList<Player> players){
        Player lorenzo = players.get(0);
        Player player = players.get(1);
        switch (actionToken){
            case DELETE2BLUE:{

                return soloActionToken;
            }
            case DELETE2GREEN:{

                return soloActionToken;
            }
            case DELETE2PURPLE:{

                return soloActionToken;
            }
            case DELETE2YELLOW:{

                return soloActionToken;
            }
            case BLACKCROSSPLUS2:{

                    lorenzo.stepAhead(2);
                return soloActionToken;
            }
            case BLACKCROSSSHUFFLE:{

                    lorenzo.stepAhead(1);
                return tokenShuffle();
            }
            default: return soloActionToken;
        }
    }

    /**
     * Returns a shuffled Solo Action Token array
     * @return tokenShuffled, an Array of SoloActionToken
     */
    private SoloActionToken[] tokenShuffle(){
        ArrayList<SoloActionToken> defaultTokens = new ArrayList<>();
        SoloActionToken[] tokenShuffled = new SoloActionToken[7];

        defaultTokens.add(SoloActionToken.DELETE2BLUE);
        defaultTokens.add(SoloActionToken.DELETE2GREEN);
        defaultTokens.add(SoloActionToken.DELETE2YELLOW);
        defaultTokens.add(SoloActionToken.DELETE2PURPLE);
        defaultTokens.add(SoloActionToken.BLACKCROSSSHUFFLE);
        defaultTokens.add(SoloActionToken.BLACKCROSSPLUS2);
        defaultTokens.add(SoloActionToken.BLACKCROSSPLUS2);

        for(int i=0;i<7;i++){
            int random=(int)(defaultTokens.size()*Math.random());
            tokenShuffled[i]=defaultTokens.get(random);
            defaultTokens.remove(random);
        }
        return tokenShuffled;
    }

    /**
     * Returns the singlePlayer actionToken Array
     */
    public SoloActionToken[] getTokens(SinglePlayer singlePlayer) {
        return singlePlayer.tokens;
    }
}
