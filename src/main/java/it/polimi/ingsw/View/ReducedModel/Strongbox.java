package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ANSIColors;
import it.polimi.ingsw.View.ANSIColors.*;
import it.polimi.ingsw.View.ReducedModel.Enums.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Strongbox Class defines the contents and the behaviour of the strongbox inside the player's board.
 * Each strongbox contains a ResourceStack-type object which contains the numbers all the four
 * different resource types.
 */
public class Strongbox implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final ResourceStack storedResources;

    /**
     * Constructor for Strongbox Class. It initializes all resources to 0.
     */
    public Strongbox() {
        this.storedResources = new ResourceStack(0,0,0,0);
    }

    /**
     * Getter for "storedResources" attribute in Strongbox Class.
     */
    public ResourceStack getStoredResources() {
        return storedResources;
    }

    /**
     * Override method used for printing the contents of a Strongbox-type object.
     * @return a String containing the information.
     */
    public String toString() {
        return storedResources.getResource(ResourceType.SHIELDS)+" "+storedResources.getResource(ResourceType.SERVANTS)+" "+storedResources.getResource(ResourceType.COINS)+" "+storedResources.getResource(ResourceType.STONES);
    }

    public String toCli() {
        return  "||===========================||\n" +
                "||" + ANSIColors.BACK_BLACK + ANSIColors.FRONT_BRIGHT_WHITE+"         STRONGBOX         "+ ANSIColors.RESET + "||\n" +
                "||===========================||\n" +
                "|| " + this.storedResources.toCli(ResourceType.SHIELDS) + "    " + this.storedResources.toCli(ResourceType.SERVANTS) + " ||\n" +
                "||                           ||\n" +
                "|| " + this.storedResources.toCli(ResourceType.COINS) + "      " + this.storedResources.toCli(ResourceType.STONES) + "   ||\n" +
                "||===========================||\n";
    }

    public String toCli2() {
        return  ANSIColors.BACK_BROWN + "╔═════════════════════════════╗"+ ANSIColors.RESET +"\n" +
                ANSIColors.BACK_BROWN + "║ "+ ANSIColors.FRONT_GREY +" ░░░░░░░" + ANSIColors.FRONT_BRIGHT_WHITE+ANSIColors.BOLD+" STRONGBOX "+ ANSIColors.RESET + ANSIColors.BACK_BROWN + ANSIColors.FRONT_GREY +"░░░░░░░ "+ ANSIColors.RESET + ANSIColors.BACK_BROWN +" ║" + ANSIColors.RESET + "\n" +
                ANSIColors.BACK_BROWN + "╠═════════════════════════════╣"+ ANSIColors.RESET +"\n" +
                ANSIColors.BACK_BROWN + "║ " + " " + this.storedResources.toCli(ResourceType.SHIELDS) + "    " + this.storedResources.toCli(ResourceType.SERVANTS) + " "+ ANSIColors.RESET + ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET +"\n" +
                ANSIColors.BACK_BROWN + "║ " +  "                           " + ANSIColors.BACK_BROWN + "║"+ ANSIColors.RESET + "\n" +
                ANSIColors.BACK_BROWN + "║ " + " " + this.storedResources.toCli(ResourceType.COINS) + "      " + this.storedResources.toCli(ResourceType.STONES) + "   "+ ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET +"\n" +
                ANSIColors.BACK_BROWN + "╚═════════════════════════════╝"+ ANSIColors.RESET +"\n";
    }

    public ArrayList<String> toCli3() {
        ArrayList<String> strongbox = new ArrayList<>();
        strongbox.add(ANSIColors.BACK_BROWN + "╔═════════════════════════════╗"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ "+ ANSIColors.FRONT_GREY +"░░░░░░░░" + ANSIColors.FRONT_BRIGHT_WHITE+ANSIColors.BOLD+" STRONGBOX "+ ANSIColors.RESET + ANSIColors.BACK_BROWN + ANSIColors.FRONT_GREY +"░░░░░░░░"+ ANSIColors.RESET + ANSIColors.BACK_BROWN +" ║" + ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "╠═════════════════════════════╣"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ " + " " + this.storedResources.toCli(ResourceType.SHIELDS) + "    " + this.storedResources.toCli(ResourceType.SERVANTS) + " "+ ANSIColors.RESET + ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ " +  "                           " + ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "║ " + " " + this.storedResources.toCli(ResourceType.COINS) + "      " + this.storedResources.toCli(ResourceType.STONES) + "   "+ ANSIColors.BACK_BROWN + " ║"+ ANSIColors.RESET);
        strongbox.add(ANSIColors.BACK_BROWN + "╚═════════════════════════════╝"+ ANSIColors.RESET);

        return strongbox;
    }
}
