package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.View.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Strongbox Class defines the contents and the behaviour of the strongbox inside the player's board.
 * Each strongbox contains a ResourceStack-type object which contains the numbers all the four
 * different resource types.
 */
public class Strongbox implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final RedResourceStack storedResources;

    /**
     * Constructor for Strongbox Class. It initializes all resources to 0.
     */
    public Strongbox() {
        this.storedResources = new ResourceStack(0,0,0,0);
    }

    /**
     * Getter for "storedResources" attribute in Strongbox Class.
     */
    public RedResourceStack getStoredResources() {
        return storedResources;
    }

    /**
     * Override method used for printing the contents of a Strongbox-type object.
     * @return a String containing the information.
     */
    public String toString() {
        return storedResources.getResource(ResourceType.SHIELDS)+" "+storedResources.getResource(ResourceType.SERVANTS)+" "+storedResources.getResource(ResourceType.COINS)+" "+storedResources.getResource(ResourceType.STONES);
    }

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
