package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Strongbox Class defines the contents and the behaviour of the strongbox inside the player's board.
 * Each strongbox contains a ResourceStack-type object which contains the numbers all the four
 * different resource types.
 */
public class RedStrongbox implements Serializable {
    protected RedResourceStack storedResources;

    /**
     * Getter for "storedResources" attribute in Strongbox Class.
     */
    public RedResourceStack getStoredResources() {
        return storedResources;
    }

    /**
     * Creates a new ReducedStrongbox for testing purposes only
     */
    public RedStrongbox(){
        this.storedResources = new ResourceStack(0,0,0,0);
    }
    /**
     * Override method used for printing the contents of a Strongbox-type object.
     * @return a String containing the information.
     */
    public String toString() {
        return storedResources.getResource(ResourceType.SHIELDS)+" "+storedResources.getResource(ResourceType.SERVANTS)+" "+storedResources.getResource(ResourceType.COINS)+" "+storedResources.getResource(ResourceType.STONES);
    }
    /**
     * Transform a Reduced Strongbox in text for cli mode
     * @return      An arrayList to be visualized by the cli
     */
    public ArrayList<String> toCli3() {
        ArrayList<String> strongbox = new ArrayList<>();
        strongbox.add(ANSIColors.BACK_BROWN + "╔═════════════════════════════╗"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ "+ ANSIColors.FRONT_GREY +"░░░░░░░░" + ANSIColors.FRONT_BRIGHT_WHITE+ANSIColors.BOLD+" STRONGBOX "+ ANSIColors.RESET + ANSIColors.BACK_BROWN + ANSIColors.FRONT_GREY +"░░░░░░░░"+ ANSIColors.RESET + ANSIColors.BACK_BROWN +" ║" + ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "╠═════════════════════════════╣"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ " + " " + this.storedResources.toCliWord(ResourceType.SHIELDS) + "    " + this.storedResources.toCliWord(ResourceType.SERVANTS) + " "+ ANSIColors.RESET + ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ " +  "                           " + ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ " + " " + this.storedResources.toCliWord(ResourceType.COINS) + "      " + this.storedResources.toCliWord(ResourceType.STONES) + "   "+ ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "╚═════════════════════════════╝"+ ANSIColors.RESET);

        return strongbox;
    }
}
