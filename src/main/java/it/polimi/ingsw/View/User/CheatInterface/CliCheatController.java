package it.polimi.ingsw.View.User.CheatInterface;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.Cheats.*;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.util.Scanner;

/**
 * Handles the cheat interaction available only in cli mode
 */
public class CliCheatController {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays all the possible cheats that can be activated
     * @return  The corresponding cheat action if the interaction was legal
     */
    public Action cheatParser() {
        String choice;
        while(true) {
            System.out.println(ANSIColors.BOLD + "{ CHEAT SELECTION }" + ANSIColors.RESET);
            System.out.println("{a} Strongbox Filler");
            System.out.println("{b} Faith Giver");
            System.out.println("{c} Development Card Giver");
            System.out.println("{d} Leader Card Activator");
            System.out.println("{e} Victory Points Giver");
            System.out.println("[0] Go Back");

            choice = sc.nextLine();

            switch (choice) {
                case "a": return new StrongboxCheat();

                case "b": return new FaithTrackCheat();

                case "c": return new DevCardsCheat();

                case "d": return new LeaderCardsCheat();

                case "e": return new VictoryPointsCheat();

                case "0": return null;

                default: System.out.println(ANSIColors.FRONT_BRIGHT_RED + "Please enter a valid character or number!" + ANSIColors.RESET);
            }
        }
    }
}
