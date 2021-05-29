package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.View.ReducedModel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Cli Class contains all methods to let the Player decide which action he wants to perform, then
 * generate and Action Message to send to the Server accordingly.
 */
public class Cli implements UserInterface{
    /** A Scanner is used to take input from the player */
    private final Scanner sc = new Scanner(System.in);

    /** Booleans are needed to choose Production Outputs when the player has finished paying for a production */
    private boolean firstLeaderCard = false;
    private boolean secondLeaderCard = false;
    private boolean basicProduction = false;

    protected void testSetter(boolean first, boolean second, boolean basic) {
        this.firstLeaderCard = first;
        this.secondLeaderCard = second;
        this.basicProduction = basic;
    }

    /**
     * Method used on startup by the client (if in Cli mode) to print the game title and get
     * the Server address and port as input from the user.
     * @return An ArrayList of Objects containing the address String and the port int.
     */
    @Override
    public ArrayList<Object> init() {
        Scanner sc = new Scanner(System.in);
        String choice;
        String choice2;
        int intChoice2;
        boolean notEnded = true;
        String fc = ANSIColors.TITLE_COLOR_FRONT;
        System.out.println("\n"  + fc + ANSIfont.BOLD +
                " _____ ______   ________  ________  _________  _______   ________  ________           ________  ________      ________  _______   ________   ________  ___  ________   ________  ________  ________   ________  _______      \n" +
                "|\\   _ \\  _   \\|\\   __  \\|\\   ____\\|\\___   ___\\\\  ___ \\ |\\   __  \\|\\   ____\\         |\\   __  \\|\\  _____\\    |\\   __  \\|\\  ___ \\ |\\   ___  \\|\\   __  \\|\\  \\|\\   ____\\ |\\   ____\\|\\   __  \\|\\   ___  \\|\\   ____\\|\\  ___ \\     \n" +
                "\\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\___|\\|___ \\  \\_\\ \\   __/|\\ \\  \\|\\  \\ \\  \\___|_        \\ \\  \\|\\  \\ \\  \\__/     \\ \\  \\|\\  \\ \\   __/|\\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\ \\  \\___|_\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\___|\\ \\   __/|    \n" +
                " \\ \\  \\\\|__| \\  \\ \\   __  \\ \\_____  \\   \\ \\  \\ \\ \\  \\_|/_\\ \\   _  _\\ \\_____  \\        \\ \\  \\\\\\  \\ \\   __\\     \\ \\   _  _\\ \\  \\_|/_\\ \\  \\\\ \\  \\ \\   __  \\ \\  \\ \\_____  \\\\ \\_____  \\ \\   __  \\ \\  \\\\ \\  \\ \\  \\    \\ \\  \\_|/__  \n" +
                "  \\ \\  \\    \\ \\  \\ \\  \\ \\  \\|____|\\  \\   \\ \\  \\ \\ \\  \\_|\\ \\ \\  \\\\  \\\\|____|\\  \\        \\ \\  \\\\\\  \\ \\  \\_|      \\ \\  \\\\  \\\\ \\  \\_|\\ \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \\  \\|____|\\  \\\\|____|\\  \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\____\\ \\  \\_|\\ \\ \n" +
                "   \\ \\__\\    \\ \\__\\ \\__\\ \\__\\____\\_\\  \\   \\ \\__\\ \\ \\_______\\ \\__\\\\ _\\ ____\\_\\  \\        \\ \\_______\\ \\__\\        \\ \\__\\\\ _\\\\ \\_______\\ \\__\\\\ \\__\\ \\__\\ \\__\\ \\__\\____\\_\\  \\ ____\\_\\  \\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\\n" +
                "    \\|__|     \\|__|\\|__|\\|__|\\_________\\   \\|__|  \\|_______|\\|__|\\|__|\\_________\\        \\|_______|\\|__|         \\|__|\\|__|\\|_______|\\|__| \\|__|\\|__|\\|__|\\|__|\\_________\\\\_________\\|__|\\|__|\\|__| \\|__|\\|_______|\\|_______|\n" +
                "                            \\|_________|                             \\|_________|                                                                             \\|_________\\|_________|                                        \n\n" + ANSIColors.RESET);

        System.out.println();

        do {
            System.out.print("\nInsert the Server Address: ");
            choice = sc.nextLine();
            System.out.print("\nInsert a port number: ");
            choice2 = sc.nextLine();
            System.out.println();
            try {
                intChoice2 = Integer.parseInt(choice2);
            }
            catch (NumberFormatException e) {
                intChoice2 = -1;
            }
            if(!InputController.addressValidator(choice))
                displayError(InputController.getError());
            else if(!InputController.portValidator(intChoice2))
                displayError(InputController.getError());
            else
                notEnded = false;
        } while(notEnded);

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(choice);
        objects.add(intChoice2);

        return objects;
    }

    public boolean startOrJoin() {
        System.out.println("Do you want to Start a new game or Join an existing one?");
        System.out.println("[1]     Start");
        System.out.println("[other] Join");

        String choice = sc.nextLine();
        return choice.equals("1");
    }

    public int numberOfPlayers() {
        String choice;
        System.out.println("Insert the number of players: ");

        while(true) {
            choice = sc.nextLine();

            if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4"))
                return Integer.parseInt(choice);
            else
                System.out.println("Please insert a valid number (from 1 to 4)");
        }
    }

    public String initialInsertName() {
        String choice;
        System.out.println("\n" + ANSIColors.GAMEPLAY_ACTIONS + ANSIfont.BOLD + "PLEASE INSERT YOUR NAME:" + ANSIColors.RESET);
        System.out.println();
        while(true) {
            choice = sc.nextLine();

            if(choice.length() > 16)
                System.out.println("Your name has to be less then 16 characters long");
            else if(choice.isEmpty())
                System.out.println("Your name cannot be an empty string");
            else if(choice.isBlank())
                System.out.println("Your name cannot be a blank string");
            else
                return choice;
        }
    }

    public void waitingForPlayers() {
        System.out.println("\nWaiting for players to join...");
    }

    public boolean initialLobby() {
        String choice;
        System.out.println("\n\n");
        System.out.println(ANSIColors.GAMEPLAY_ACTIONS + ANSIfont.BOLD + "Welcome to the pre-game Lobby!" + ANSIColors.RESET);
        System.out.println();
        System.out.println("Are you ready to play " + ANSIfont.ITALIC + ANSIfont.BOLD + ANSIColors.FRONT_BLUE + "Masters of Renaissance" + ANSIColors.RESET + "? (y/n)");

        while(true) {
            choice = sc.nextLine();
            if(choice.equals("y")) {
                return true;
            }
            else if(!choice.equals("n"))
                System.out.println("Please insert a valid character");
        }

    }

    public Action initialChooseLeaderCards(ArrayList<RedLeaderCard> leaderCards) throws IllegalArgumentException{
        if(leaderCards.size() != 4)
            throw new IllegalArgumentException("Number of Leader Cards to choose from is" + leaderCards.size() + ", should be 4");

        String choice;
        String choice2;
        int firstLeaderCard;
        int secondLeaderCard;

        System.out.println(ANSIColors.YOUR_TURN_COLOR + " - IT'S YOUR TURN! - " + ANSIColors.RESET);
        for(String s : initLeaderCardsToCli(leaderCards))
            System.out.println(s);
        System.out.println();
        System.out.println("Please choose two Leader Cards (numbered 1 to 4 from left to right");

        while(true) {
            choice = sc.nextLine();
            if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                firstLeaderCard = Integer.parseInt(choice) - 1;
                break;
            }
            else
                System.out.println("Please pick a number between 1 and 4");
        }

        while(true) {
            choice2 = sc.nextLine();
            if(choice2.equals(choice))
                System.out.println("You can't choose the same Leader Card twice!");
            else if(choice2.equals("1") || choice2.equals("2") || choice2.equals("3") || choice2.equals("4")) {
                secondLeaderCard = Integer.parseInt(choice2) - 1;
                return new InitChooseLeaderCards(leaderCards.get(firstLeaderCard), leaderCards.get(secondLeaderCard));
            }
            else
                System.out.println("Please pick a number between 1 and 4");
        }
    }

    public Action initialChooseResources(int resources) {
        System.out.println(ANSIColors.YOUR_TURN_COLOR + ANSIfont.BOLD + " - PREPARING THE GAME - " + ANSIColors.RESET);
        System.out.println();
        if(resources <= 0) {
            HashMap<Integer, ArrayList<ResourceType>> depotResource = new HashMap<>();
            depotResource.put(0, null);
            depotResource.put(1, null);
            depotResource.put(2, null);
            return new InitChooseResources(new HashMap<>());
        }

        int depot;
        ResourceType type;
        HashMap<Integer, ArrayList<ResourceType>> depotResource = new HashMap<>();
        ArrayList<ResourceType> firstDepot = new ArrayList<>();
        ArrayList<ResourceType> secondDepot = new ArrayList<>();
        ArrayList<ResourceType> thirdDepot = new ArrayList<>();

        RedWarehouse warehouse = new Warehouse();
        for(String s : warehouse.toCli())
            System.out.println(s);

        for(; resources > 0; resources--) {
            System.out.println("\nChoose a resource type you want to add to your Warehouse (" + resources + " remaining)");
            type = resourceIntIterator(sc);
            System.out.println("\nSelect the Depot where you want to put the resource: ");
            depot = initialDepotIterator(sc) - 1;

            switch (depot) {
                case 0: firstDepot.add(type);
                break;
                case 1: secondDepot.add(type);
                break;
                case 2: thirdDepot.add(type);
                break;
            }
        }
        depotResource.put(0, firstDepot);
        depotResource.put(1, secondDepot);
        depotResource.put(2, thirdDepot);

        return new InitChooseResources(depotResource);
    }

    /**
     * actionPicker method is used by the player to choose which action he wants to perform.
     * @param game Current game being played by the Player
     * @throws IllegalStateException if the Player does not have any Possible Action left.
     */
    public Action actionPicker(Game game) throws IllegalStateException {
        Player player = game.getMyPlayer();
        if(player.getPossibleActions() == null || player.getPossibleActions().isEmpty())
            throw new IllegalStateException("Player can't have zero possible actions");

        String choice;
        int choiceInt;
        Action action;


        while(true) {
            System.out.println(ANSIColors.YOUR_TURN_COLOR + " - IT'S YOUR TURN! - " + ANSIColors.RESET);
            System.out.println("Please choose one of the following possible Actions");

            // "GAMEPLAY ACTIONS" refers to all Actions that can have an effect on the game's Model
            System.out.println("\n" + ANSIColors.GAMEPLAY_ACTIONS + " GAMEPLAY ACTIONS: " + ANSIColors.RESET);
            for(int i = 0; i < player.getPossibleActions().size(); i++)
                System.out.println((i + 1) + " - " + player.getPossibleActions().get(i).toString());

            // "VISUALIZE" refers to actions that only print certain things on the Terminal
            System.out.println("\n" + ANSIColors.VISUALIZE + " VISUALIZE: " + ANSIColors.RESET);
            System.out.println("a - Your Board");
            System.out.println("b - Another Player's board");
            System.out.println("c - Market");
            System.out.println("d - Development Card Table\n");
            choice = sc.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7") || choice.equals("8") || choice.equals("9")) {
                choiceInt = Integer.parseInt(choice);
                if(choiceInt < 1 || choiceInt > player.getPossibleActions().size())
                    System.out.println("Please insert a valid number");
                else {
                    action = indexToAction(player.getPossibleActions().get(choiceInt - 1), game);
                    if(action != null) {
                        return action;
                    }
                }
            }
            else
                switch (choice) {
                    case "a": printYourBoard(game);
                    break;
                    case "b": printPlayerBoard(game);
                    break;
                    case "c": printMarket(game);
                    break;
                    case "d": printDevTable(game);
                    break;
                    default: System.out.println("Please insert a valid number or character");
                }
        }
    }

    /**
     * Method used to print the client's own Board
     */
    private void printYourBoard(Game game) {
        printList(game.myBoardToCli());
    }

    /**
     * Method used to print the board of a specified Player.
     */
    private void printPlayerBoard(Game game) {
        String choice;
        int choiceInt;
        System.out.println("\nWhich player's Board do you to see? (Select corresponding number, 0 to go back)");
        for(int i = 0; i < game.getPlayers().size(); i++)
            System.out.println((i+1) + " - " + game.getPlayers().get(i).getNickname());

        while(true) {
            choice = sc.nextLine();
            if(choice.equals("0"))
                return;
            else if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                choiceInt = Integer.parseInt(choice);
                if(choiceInt >= 1 && choiceInt <= game.getPlayers().size()) {
                    printList(game.boardToCli(game.getPlayers().get(choiceInt - 1)));
                    return;
                }
            }
            else
                System.out.println("Please insert a valid number");
        }
    }

    /**
     * Method used to print the Market.
     */
    private void printMarket(Game game) {
        printList(game.getMarket().toCli());
    }

    /**
     * Method used to print the Development Card Table.
     */
    private void printDevTable(Game game) {
        printList(game.getDevelopmentCardTable().toCli());
    }

    /**
     * Method used by the player to generate an ActivateLeaderCard message.
     * @return The requested action (null if the player chose to go back).
     */
    public Action activateLeaderCard(Game game) {
        String choice;
        boolean notEnded = true;
        int choiceInt = -1;
        printList(game.myBoardToCli());

        System.out.println("\nChoose which leader card you want to activate: 1 (below), 2 (above)\t(0 to exit): ");

        while(notEnded) {
            choice = sc.nextLine();
            if(choice.equals("0"))
                return null;

            else if(choice.equals("1") || choice.equals("2")) {
                choiceInt = Integer.parseInt(choice);
                if (InputController.checkActivateLeaderCard(choiceInt - 1, game))
                    notEnded = false;
                else
                    System.out.println(InputController.getError());
            }

            else
                System.out.println("Please insert a valid number");
        }

        return new ActivateLeaderCard(choiceInt - 1);
    }

    /**
     * Method used by the player to generate an ActivateProduction message.
     * @return The requested action (null if the player chose to go back).
     */
    public Action activateProduction(Game game) {
        boolean firstSlot = false, secondSlot = false, thirdSlot = false;
        boolean firstLeaderCard = false, secondLeaderCard = false;
        boolean basicProduction;
        boolean decision;
        String choice;
        ArrayList<ResourceType> basicProductionInputs = null;
        ArrayList<ResourceType> basicProductionInputsModel = null;

        printList(game.myBoardToCli());

        System.out.println("\u001B[1m" + "Write 0 at any time to go back" + ANSIColors.RESET);
        System.out.println("\nDo you want to use Development Cards? (y/n)");
        choice = stringAssign(sc);
        if(choice.equals("0"))
            return null;
        else
            decision = boolDecider(choice);

        if(decision) {
            System.out.println("Which cards do you want to use?");
            System.out.println("First slot (left): (y/n)");
            choice = stringAssign(sc);
            if(choice.equals("0"))
                return null;
            else
                firstSlot = boolDecider(choice);

            System.out.println("Second slot (center): (y/n)");
            choice = stringAssign(sc);
            if(choice.equals("0"))
                return null;
            else
                secondSlot = boolDecider(choice);

            System.out.println("Third slot (right): (y/n)");
            choice = stringAssign(sc);
            if(choice.equals("0"))
                return null;
            else
                thirdSlot = boolDecider(choice);
        }

        System.out.println("\nDo you want to use Leader Cards? (y/n)");
        choice = stringAssign(sc);
        decision = boolDecider(choice);

        if(decision) {
            System.out.println("Which leader cards do you want to use?");
            System.out.println("First leader card (below): (y/n)");
            choice = stringAssign(sc);
            if(choice.equals("0"))
                return null;
            else
                firstLeaderCard = boolDecider(choice);

            System.out.println("Second leader card (above): (y/n)");
            choice = stringAssign(sc);
            if(choice.equals("0"))
                return null;
            else
                secondLeaderCard = boolDecider(choice);
        }

        System.out.println("\nDo you want to use Basic Production? (y/n)");
        choice = stringAssign(sc);
        basicProduction = boolDecider(choice);

        if(basicProduction) {
            int jollyIn = game.getMyPlayer().getBasicProduction().getJollyIn();

            System.out.println("Which resources do you want to use as inputs? (One by one, " + jollyIn + " needed)");
            basicProductionInputs = typesFiller(jollyIn, sc);

            if(basicProductionInputs == null)
                return null;
        }

        if(!firstSlot && !secondSlot && !thirdSlot && !firstLeaderCard && !secondLeaderCard && !basicProduction) {
            System.out.println("Cannot start Production without any production power!");
            return null;
        }

        if(!InputController.checkActivateProduction(basicProduction, basicProductionInputs, firstLeaderCard, secondLeaderCard, game)) {
            System.out.println(InputController.getError());
            return null;
        }
        else {
            if(basicProduction) {
                basicProductionInputsModel = new ArrayList<>();
                for (ResourceType type : basicProductionInputs)
                    basicProductionInputsModel.add(type.viewToModel());
            }

            this.firstLeaderCard = firstLeaderCard;
            this.secondLeaderCard = secondLeaderCard;
            this.basicProduction = basicProduction;
            return new ActivateProduction(firstSlot, secondSlot, thirdSlot, firstLeaderCard, secondLeaderCard, basicProduction, basicProductionInputsModel);
        }
    }

    /**
     * Method used by the player to generate an AddMessage.
     * @return The requested action.
     */
    public Action addResource(Game game) {
        int depot;
        ResourceType type;
        printList(game.getMyPlayer().toCliAdd());


        while(true) {
            System.out.println("\nWhich depot do you want to add the resource to?");

            depot = depotIntIterator(game, false, sc);

            System.out.println("\nSelect a resource type: (write corresponding number)");

            type = resourceIntIterator(sc);

            if(InputController.checkAddResource(depot - 1, type, game)) {
                return new AddResource(depot - 1, type.viewToModel());
            }
            else
                System.out.println(InputController.getError());
        }
    }

    /**
     * Method used by the player to generate a BuyCard message.
     * @return The requested action (null if the player chose to go back).
     */
    public Action buyCard(Game game) {
        String choice;
        int row, column;
        printList(game.getDevelopmentCardTable().toCli());

        while(true) {
            System.out.println("\nSelect row of the deck you want to buy the card from: (from 1 to 3, or 0 to go back)");
            choice = sc.nextLine();

            if(choice.equals("0"))
                return null;
            else if(choice.equals("1") || choice.equals("2") || choice.equals("3"))
                row = Integer.parseInt(choice);
            else
                row = -1;

            System.out.println("\nSelect column of the deck you want to buy the card from: (from 1 to 4, or 0 to go back)");
            choice = sc.nextLine();

            if(choice.equals("0"))
                return null;
            else if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4"))
                column = Integer.parseInt(choice);
            else
                column = -1;

            if(InputController.checkBuyCard(row - 1, column - 1, game))
                return new BuyCard(row - 1, column - 1);
            else
                System.out.println(InputController.getError());
        }
    }

    /**
     * Method used by the player to generate a ChooseCardSlot message.
     * @return The requested action.
     */
    public Action chooseCardSlot(Game game) {
        int slot;
        String choice;

        printList(game.myBoardToCli());

        while(true) {
            System.out.println("\nSelect Development Card Slot where you want to put the card: ");
            System.out.println("1 - Left");
            System.out.println("2 - Center");
            System.out.println("3 - Right");

            choice = sc.nextLine();

            if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                slot = Integer.parseInt(choice);

                if(InputController.checkChooseCardSlot(slot - 1))
                    return new ChooseCardSlot(slot - 1);
                else
                    System.out.println(InputController.getError());
            }
            else
                System.out.println("Please insert a valid number");
        }
    }

    /**
     * Method used by the player to generate a ChooseLeaderCard message.
     * @return The requested action (null if the player chose to go back).
     */
    public Action chooseLeaderCard(Game game) {
        String choice;
        int choiceInt;

        printList(game.myBoardToCli());

        while(true) {
            System.out.println("\nSelect Leader Card you want to use: ");
            System.out.println("1 - Below");
            System.out.println("2 - Above");

            choice = sc.nextLine();

            if(choice.equals("1") || choice.equals("2")) {
                choiceInt = Integer.parseInt(choice);
                if(InputController.checkChooseLeaderCard(choiceInt - 1, game))
                    return new ChooseLeaderCard(choiceInt - 1);
                else
                    System.out.println(InputController.getError());
            }
            else
                System.out.println("Please enter a valid number");
        }
    }

    /**
     * Method used by the player to generate a ChooseProductionOutput message.
     * @return The requested action.
     */
    public Action chooseProductionOutput(Game game) {
        ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();
        if(!this.firstLeaderCard && !this.secondLeaderCard && !this.basicProduction)
            return chooseProductionOutput;
        else {
            chooseProductionOutput.setFirstLeaderCard(this.firstLeaderCard);
            chooseProductionOutput.setSecondLeaderCard(this.secondLeaderCard);
            chooseProductionOutput.setBasicProduction(this.basicProduction);
        }

        boolean notCorrect = true;

        printList(game.myBoardToCli());
        System.out.println("Choose Production Output");
        while(notCorrect) {
            boolean notEnded = true;
            ArrayList<ResourceType> firstLeaderOutputs = new ArrayList<>();
            ArrayList<ResourceType> secondLeaderOutputs = new ArrayList<>();
            ArrayList<ResourceType> basicOutputs = new ArrayList<>();

            if (this.firstLeaderCard) {
                System.out.println("First Leader Card, select resources you want to add one by one: ");
                while (notEnded) {
                    firstLeaderOutputs = typesFiller(game.getMyPlayer().getLeaderCards()[0].getJollyOut(), sc);
                    if (firstLeaderOutputs == null)
                        System.out.println("You have to choose which resources you want to add");
                    else
                        notEnded = false;
                }
                chooseProductionOutput.setFirstLeaderCardOutput(firstLeaderOutputs);
            }

            notEnded = true;
            if (this.secondLeaderCard) {
                System.out.println("Second Leader Card, select resources you want to add one by one: ");
                while (notEnded) {
                    secondLeaderOutputs = typesFiller(game.getMyPlayer().getLeaderCards()[1].getJollyOut(), sc);
                    if (secondLeaderOutputs == null)
                        System.out.println("You have to choose which resources you want to add");
                    else
                        notEnded = false;
                }
                chooseProductionOutput.setSecondLeaderCardOutput(secondLeaderOutputs);
            }

            notEnded = true;
            if (this.basicProduction) {
                System.out.println("Basic Production, select resources you want to add one by one: ");
                while (notEnded) {
                    basicOutputs = typesFiller(game.getMyPlayer().getBasicProduction().getJollyOut(), sc);
                    if (basicOutputs == null)
                        System.out.println("You have to choose which resources you want to add");
                    else
                        notEnded = false;
                }
                chooseProductionOutput.setBasicProductionOutput(basicOutputs);
            }

            if(InputController.checkChooseProductionOutput(basicProduction, firstLeaderCard, secondLeaderCard, basicOutputs, firstLeaderOutputs, secondLeaderOutputs, game))
                notCorrect = false;
            else {
                System.out.println(InputController.getError());
                chooseProductionOutput.setSecondLeaderCardOutput(null);
                chooseProductionOutput.setFirstLeaderCardOutput(null);
                chooseProductionOutput.setBasicProductionOutput(null);
            }

        }

        return chooseProductionOutput;
    }

    /**
     * Method used by the player to generate an MarketChooseRow message.
     * @return The requested action (null if the player chose to go back).
     */
    public Action marketChooseRow(Game game) {
        boolean row = false;
        int rowOrColumn;
        String choice;
        boolean notEnded1 = true;

        printList(game.getMarket().toCli());

        while(true) {
            System.out.println("\nDo you want a row or a column?  (0 to go back)");
            while (notEnded1) {
                System.out.println("1 - Row");
                System.out.println("2 - Column");

                choice = sc.nextLine();

                switch (choice) {
                    case "0":
                        return null;
                    case "1":
                        row = true;
                        notEnded1 = false;
                        break;
                    case "2":
                        row = false;
                        notEnded1 = false;
                        break;
                    default:
                        System.out.println("Please enter a valid number");
                        break;
                }
            }

            if(row) {
                System.out.println("\nChoose a row:  (0 to go back)");
                System.out.println("1 - Above");
                System.out.println("2 - Center");
                System.out.println("3 - Below");

                choice = sc.nextLine();

                if(choice.equals("0"))
                    return null;
                else if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                    rowOrColumn = Integer.parseInt(choice);
                    
                    if(InputController.checkMarketChooseRow(true, rowOrColumn - 1))
                        return new MarketChooseRow(true, rowOrColumn - 1);
                    else
                        System.out.println(InputController.getError());
                }
                else
                    System.out.println("Please enter a valid number");
            }
            else {
                System.out.println("\nChoose a column:  (0 to go back)");
                System.out.println("1 - Left");
                System.out.println("2 - Left Center");
                System.out.println("3 - Right Center");
                System.out.println("4 - Right");

                choice = sc.nextLine();

                if(choice.equals("0"))
                    return null;
                else if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                    rowOrColumn = Integer.parseInt(choice);

                    if(InputController.checkMarketChooseRow(false, rowOrColumn - 1))
                        return new MarketChooseRow(false, rowOrColumn - 1);
                    else
                        System.out.println(InputController.getError());
                }
                else
                    System.out.println("Please enter a valid number");
            }
        }
    }

    /**
     * Method used by the player to generate a PayResource message.
     * @return The requested action.
     */
    public Action payResource(Game game) {
        boolean fromWarehouse = false;
        int depot;
        boolean notEnded = true;
        ResourceType type;
        String choice;

        printList(game.getMyPlayer().toCliPay());

        while(true) {
            while(notEnded) {
                System.out.println("\nDo you want to pay from the Warehouse or the Strongbox? (insert relative number)");
                System.out.println("1 - Warehouse");
                System.out.println("2 - Strongbox");

                choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        fromWarehouse = true;
                        notEnded = false;
                        break;
                    case "2":
                        fromWarehouse = false;
                        notEnded = false;
                        break;
                    default:
                        System.out.println("Please enter a valid number");
                        break;
                }
            }

            if(fromWarehouse) {
                System.out.println("\nWhich depot do you want to remove the resource from?");

                depot = depotIntIterator(game, false, sc);

                if(InputController.checkPayResource(true, depot - 1, null, game))
                    return new PayResource(true, depot - 1, null);
                else
                    System.out.println(InputController.getError());
            }
            else {
                System.out.println("\nWhich type of resource do you want to remove from the Strongbox? (Insert relative number");

                type = resourceIntIterator(sc);

                if(InputController.checkPayResource(false, 0, type, game))
                    return  new PayResource(false, 0, type.viewToModel());
                else
                    System.out.println(InputController.getError());
            }
        }
    }

    /**
     * Method used by the player to generate a PayResourceProduction message.
     * @return The requested action.
     */
    public PayResourceProduction payResourceProduction(Game game) {
        Action action = payResource(game);
        return new PayResourceProduction(((PayResource)action).isFromWarehouse(), ((PayResource)action).getDepot(), ((PayResource)action).getResourceType());
    }

    /**
     * Method used by the player to generate an PayResourceBuyCard message.
     * @return The requested action.
     */
    public PayResourceBuyCard payResourceBuyCard(Game game) {
        Action action = payResource(game);
        return new PayResourceBuyCard(((PayResource)action).isFromWarehouse(), ((PayResource)action).getDepot(), ((PayResource)action).getResourceType());
    }

    /**
     * Method used by the player to generate an ResetWarehouse message.
     * @return The requested action.
     */
    public Action resetWarehouse() {
        return new ResetWarehouse();
    }

    /**
     * Method used by the player to generate a SwitchDepot message.
     * @return The requested action (null if the player chose to go back).
     */
    public Action switchDepot(Game game) {
        printList(game.myBoardToCli());

        while(true) {
            System.out.println("\nSelect first Depot you want to switch: (0 to go back)");
            int depot1 = depotIntIterator(game, true, sc);
            if(depot1 == 0)
                return null;

            System.out.println("\nSelect second Depot you want to switch: (0 to go back)");
            int depot2 = depotIntIterator(game, true, sc);
            if(depot2 == 0)
                return null;

            if(InputController.checkSwitchDepot(depot1 - 1, depot2 - 1, game))
                return new SwitchDepot(depot1 - 1, depot2 - 1);
            else
                System.out.println(InputController.getError());
        }
    }

    /**
     * Method needed to map the player's choice to the actual Action that has to be performed.
     */
    public Action indexToAction(ActionType action, Game game) {
        switch(action) {
            case MARKET_CHOOSE_ROW: return marketChooseRow(game);

            case CHOOSE_LEADER_CARD: return chooseLeaderCard(game);

            case ADD_RESOURCE: return addResource(game);

            case SWITCH_DEPOT: return switchDepot(game);

            case RESET_WAREHOUSE: return resetWarehouse();

            case END_MARKET: return new EndMarket();

            case BUY_CARD: return buyCard(game);

            case PAY_RESOURCE_CARD: return payResourceBuyCard(game);

            case PAY_RESOURCE_PRODUCTION: return payResourceProduction(game);

            case CHOOSE_CARD_SLOT: return chooseCardSlot(game);

            case ACTIVATE_PRODUCTION: return activateProduction(game);

            case CHOOSE_PRODUCTION_OUTPUT: return chooseProductionOutput(game);

            case ACTIVATE_LEADERCARD: return activateLeaderCard(game);

            case END_TURN: return new EndTurn();

            default: return null;
        }
    }

    public ArrayList<String> initLeaderCardsToCli(ArrayList<RedLeaderCard> leaderCards) throws IllegalArgumentException {
        ArrayList<String> stringLeaders = leaderCards.get(0).toCliUp();

        for(RedLeaderCard leaderCard : leaderCards)
            for(int i = 0; i < stringLeaders.size(); i++)
                if(leaderCard != leaderCards.get(0))
                    stringLeaders.set(i, stringLeaders.get(i) + " " + leaderCard.toCliUp().get(i));

        return stringLeaders;
    }


    /**
     * Method used to print an ArrayList of Strings.
     * @param list Is the ArrayList to be printed.
     */
    private static void printList(ArrayList<String> list) {
        for(int i = 0; i < 30; i++)
            System.out.println();
        for(String s : list)
            System.out.println(s);
    }

    /**
     * Method used to map a String to a Boolean
     * @param c String to be mapped
     * @return true if c = "y", false if c = "n"
     * @throws IllegalArgumentException if c is neither "y" nor "n"
     */
    private static boolean boolDecider(String c) throws IllegalArgumentException{
        if(c.equals("y"))
            return true;
        else if(c.equals("n"))
            return false;

        throw new IllegalArgumentException("ERROR IN BOOLEAN CONTROL: WAS " + c);
    }

    /**
     * Method used to take a boolean type input from the Player
     * @return String containing "y", "n" or "0"
     */
    private static String stringAssign(Scanner sc) {

        String c = sc.nextLine();

        while(!c.equals("y") && !c.equals("n") && !c.equals("0")) {
            System.out.println("Please insert a valid character ('y' or 'n')");
            c = sc.nextLine();
            System.out.println();
        }
        return c;
    }

    /**
     * Method used whenever an ArrayList of ResourceType has to be generated by Player's input.
     * @param jolly The number of resources which have to be chosen.
     * @return An ArrayList containing ResourceTypes.
     */
    private ArrayList<ResourceType> typesFiller(int jolly, Scanner sc) {
        String choiceString;
        int choice;

        ArrayList<ResourceType> inputs = new ArrayList<>();
        int counter = 0;
        while(counter < jolly) {
            System.out.println("\nSelect a resource type: (write corresponding number)");
            System.out.println("1 - SHIELD");
            System.out.println("2 - SERVANT");
            System.out.println("3 - COIN");
            System.out.println("4 - STONE");

            choiceString = sc.nextLine();

            if(choiceString.equals("0"))
                return null;
            else if(choiceString.equals("1") ||choiceString.equals("2") ||choiceString.equals("3") ||choiceString.equals("4")) {
                choice = Integer.parseInt(choiceString);
                inputs.add(resourceTypeParse(choice));
                counter ++;
            }
            else
                System.out.println("Please enter a valid number.");
        }
        return inputs;
    }

    /**
     * Method used to parse an int into a ResourceType.
     */
    private static ResourceType resourceTypeParse(int i) throws IllegalArgumentException{
        switch(i) {
            case 1: return ResourceType.SHIELDS;
            case 2: return ResourceType.SERVANTS;
            case 3: return ResourceType.COINS;
            case 4: return ResourceType.STONES;

            default: throw new IllegalArgumentException("INVALID INT FOR RESOURCE CONVERSION");
        }
    }

    /**
     * Method used by the Player to choose a Warehouse Depot.
     * @param canExit Boolean which specifies if the player can exit the action or not.
     * @return An int describing the player's choice.
     */
    private static int depotIntIterator(Game game, boolean canExit, Scanner sc) {
        RedWarehouse warehouse = game.getMyPlayer().getWarehouse();
        String choice;
        int depot;

        if(!warehouse.getWarehouseDepots()[0].isEmpty())
            System.out.println("1 - First depot (below)");
        if(!warehouse.getWarehouseDepots()[1].isEmpty())
            System.out.println("2 - Second Depot (center)");
        if(!warehouse.getWarehouseDepots()[2].isEmpty())
            System.out.println("3 - Third Depot (above)");
        if(warehouse.isExtraWarehouseDepot1IsActive() && !warehouse.getExtraWarehouseDepot1().isEmpty())
            System.out.println("4 - Extra Depot 1 (left)");
        if(warehouse.isExtraWarehouseDepot2IsActive() && !warehouse.getExtraWarehouseDepot2().isEmpty())
            System.out.println("5 - Extra Depot 2 (right)");

        while(true) {
            choice = sc.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")) {
                depot = Integer.parseInt(choice);
                return depot;
            }
            else if(canExit && choice.equals("0"))
                return 0;
            else
                System.out.println("Please insert a valid Depot number");
        }
    }

    /**
     * Method used before the game's start to choose where to put the initial resources given
     * by the Server to the Player.
     * @return An int indicating the Player's depot.
     */
    private static int initialDepotIterator(Scanner sc) {
        String choice;
        int depot;

        System.out.println("1 - First depot (below)");
        System.out.println("2 - Second Depot (center)");
        System.out.println("3 - Third Depot (above)");


        while(true) {
            choice = sc.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                depot = Integer.parseInt(choice);
                return depot;
            }
            else
                System.out.println("Please insert a valid Depot number");
        }
    }

    /**
     * Method used by the player to choose a ResourceType.
     * @return The chosen ResourceType.
     */
    private static ResourceType resourceIntIterator(Scanner sc) {
        System.out.println("1 - SHIELD");
        System.out.println("2 - SERVANT");
        System.out.println("3 - COIN");
        System.out.println("4 - STONE");
        String choice;
        int typeInt;
        ResourceType type;

        while(true) {
            choice = sc.nextLine();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                typeInt = Integer.parseInt(choice);
                type = resourceTypeParse(typeInt);

                return type;
            } else
                System.out.println("Please insert a valid Resource number");
        }
    }

    /**
     * Displays an Alert Box for the user to see if the game is set to Cli Mode.
     * @param s the message to display
     */
    public void displayError(String s) {
        System.out.println(ANSIfont.BOLD + "\u001B[38;5;160m" + s + ANSIColors.RESET);
    }

}
