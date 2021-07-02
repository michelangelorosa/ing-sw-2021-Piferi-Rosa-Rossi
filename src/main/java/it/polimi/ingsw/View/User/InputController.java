package it.polimi.ingsw.View.User;

import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;

import java.util.ArrayList;

/**
 * InputController Class is used to check the input given by the player to ensure that the Controller
 * can operate on the messages without Runtime errors.
 *
 * Each method checks a different message.
 */
public abstract class InputController {
    /** Constants are defined to control Client's input indexes. */
    private static final int MIN = 0;
    private static final int MAX_DEPOT = 4;
    private static final int LEADER_CARD_TWO = 1;
    private static final int MAX_ROW = 2;
    private static final int MAX_COLUMN = 3;
    private static final int MAX_CARD_SLOT = 2;
    private static final ResourceType NONE = ResourceType.NONE;

    private static String error;

    /**
     * Method used to return the error String if any method returns false.
     * @return A String containing an error message.
     */
    public static String getError() {
        return error;
    }

    /**
     * Checks if the client is trying to connect to an illegal address
     * @param address the address the user has inserted
     */
    public static boolean addressValidator(String address) {
        String IPv4 = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        String URL = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        if (address.matches(IPv4)||address.equals("localhost")||address.matches(URL)){
            return true;
        }
        error ="Please enter a valid ipv4 address!";

        return false;
    }

    /**
     * Checks if the user wants to connect to a reserved port or an illegal port
     * @param port the port the user is trying to establish a connection to
     */
    public static boolean portValidator(int port) {
        error = "Please enter a valid port number!";
        if(port<=1024||port>65535) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the user has provided a valid name
     * @param name the string the user wants to set as a username
     */
    public static boolean nameValidator(String name) {
        if(name.isEmpty()||name.isBlank()||name.length()>16){
            error = "Please insert a valid username with maximum 16 characters!";
            return false;
        }
        return true;
    }

    /**
     * Checker for ActivateLeaderCard message.
     */
    public static boolean checkActivateLeaderCard(int i, Game game) {
        if(i != MIN && i != LEADER_CARD_TWO) {
            error = "Please insert a valid number (1, 2 or 0 to exit)";
            return false;
        }
        for(Player player : game.getPlayers())
            if(game.getMyNickname().equals(player.getNickname()) && player.getLeaderCards()[i].isActive()) {
                error = "Leader Card already active!";
                return false;
            }

        return true;
    }

    /**
     * Checker for ActivateProduction message.
     */
    public static boolean checkActivateProduction(boolean basic, ArrayList<ResourceType> basicInputs, boolean lead1, boolean lead2, Game game) {

        if(basic) {
            for (ResourceType type : basicInputs)
                if (type == NONE) {
                    error = "At least one resources was of type NONE!";
                    return false;
                }
            for(Player player : game.getPlayers())
                if(player.getNickname().equals(game.getMyNickname()) && basicInputs.size() > player.getBasicProduction().getJollyIn()) {
                    error = "Number of Basic Production inputs should be " + player.getBasicProduction().getJollyIn();
                    return false;
                }
        }
        if(lead1 && !leaderCheck(0, game))
            return false;

        if(lead2 && !leaderCheck(1, game))
            return false;

        return true;
    }

    /**
     * Checker for AddResource message.
     */
    public static boolean checkAddResource(int depot, ResourceType type, Game game) {
        if(depot < MIN || depot > MAX_DEPOT) {
            error = "Pick a number from 0 to 4";
            return false;
        }
        if(type == NONE) {
            error = "Can't pick resource of type NONE!";
            return false;
        }
        if(!game.getMyPlayer().getTemporaryResources().hasResource(type)) {
            error = type.toCli() + " not present in resources to add";
            return false;
        }
        if(depot == 3 && !extraDepotCheck(0, game))
            return false;
        if(depot == 4 && !extraDepotCheck(1, game))
            return false;

        return true;
    }

    /**
     * Checker for BuyCard message.
     */
    public static boolean checkBuyCard(int row, int column, Game game) {
        if(row < MIN || row > MAX_ROW) {
            error = "Row index should be between 1 and 3!";
            return false;
        }
        if(column < MIN || column > MAX_COLUMN) {
            error = "Column index should be between 1 and 4!";
            return false;
        }
        if(game.getDevelopmentCardTable().getDeck(row, column).isEmpty()) {
            error = "Cannot buy Card from and empty Deck!";
            return false;
        }

        return true;
    }

    /**
     * Checker for ChooseCardSlot message.
     */
    public static boolean checkChooseCardSlot(int i) {
        if(i < MIN || i > MAX_CARD_SLOT) {
            error = "Card Slot index should be between 1 and 3";
            return false;
        }
        return true;
    }

    /**
     * Checker for ChooseLeaderCard message.
     */
    public static boolean checkChooseLeaderCard(int i, Game game) {
        if(i != MIN && i != LEADER_CARD_TWO) {
            error = "Leader Card Index should be either 0 or 1!";
            return false;
        }

        for(Player player : game.getPlayers())
            if(player.getNickname().equals(game.getMyNickname()))
                if(player.getLeaderCards()[i].getAction() != LeaderCardAction.WHITEMARBLE) {
                    error = "Leader Card must be of type \"WHITEMARBLE\"!";
                    return false;
                }
                else if(!player.getLeaderCards()[i].isActive()) {
                    error = "Leader Card must be active!";
                    return false;
                }

        return true;
    }

    /**
     * Checks if a Leader Card can be discarded
     * @param i         the leader card index
     * @param game      the reference to the game
     * @return          true if it can be discarded, false otherwise
     */
    public static boolean checkDiscardLeaderCard(int i, Game game){
        if(i != MIN && i != LEADER_CARD_TWO){
            error = "Leader Card Index should be either 1 or 2!";
            return false;
        }
        for(Player player : game.getPlayers()){
            if(player.getNickname().equals(game.getMyNickname()))
                if(player.getLeaderCards()[i].isActive()){
                    error = "Leader Card cannot be active";
                    return false;
                }
                else if(player.getLeaderCards()[i].isDiscarded()){
                    error = "This Leader Card has already been discarded";
                    return false;
                }
        }
        return true;
    }

    /**
     * Checker for ChooseProductionOutput message.
     */
    public static boolean checkChooseProductionOutput(boolean basic, boolean lead1, boolean lead2, ArrayList<ResourceType> basicResources, ArrayList<ResourceType> lead1Resources, ArrayList<ResourceType> lead2Resources, Game game) {
        Player player = new Player("", -1, false);

        for(Player p : game.getPlayers())
            if(p.getNickname().equals(game.getMyNickname()))
                player = p;

        if(basic && (!checkResources(basicResources, player.getBasicProduction().getJollyOut()))) {
            return false;
        }
        if(lead1 && (!leaderCheck(MIN, game) || !checkResources(lead1Resources, player.getLeaderCards()[MIN].getJollyOut())))
            return false;
        if(lead2 && (!leaderCheck(LEADER_CARD_TWO, game) || !checkResources(lead2Resources, player.getLeaderCards()[LEADER_CARD_TWO].getJollyOut())))
            return false;

        return true;
    }

    /**
     * Checker for MarketChooseRow message.
     */
    public static boolean checkMarketChooseRow(boolean row, int i) {
        if(row)
            if(i < MIN || i > MAX_ROW) {
                error = "Row index should be between 0 and 2!";
                return false;
            }

        if(i < MIN || i > MAX_COLUMN) {
            error = "Column index should be between 0 and 3!";
            return false;
        }

        return true;
    }

    /**
     * Checker for PayResource message.
     */
    public static boolean checkPayResource(boolean fromWarehouse, int i, ResourceType type, Game game) {
        if(fromWarehouse) {
            if(i < MIN || i > MAX_DEPOT) {
                error = "Depot index should be between 1 and 5";
                return false;
            }
            if(i == 3 && !extraDepotCheck(0, game))
                return false;
            if(i == 4 && !extraDepotCheck(1, game))
                return false;
        }

        else if(type == ResourceType.NONE){
            error = "Resource was of type NONE!";
            return false;
        }

        return true;
    }

    /**
     * Checker for SwitchDepot message.
     */
    public static boolean checkSwitchDepot(int depot1, int depot2, Game game) {
        if(depot1 < MIN || depot1 > MAX_DEPOT) {
            error = "First Depot index should be between 1 and 5";
            return false;
        }
        if(depot2 < MIN || depot2 > MAX_DEPOT) {
            error = "Second Depot index should be between 1 and 5";
            return false;
        }
        if(depot1 == depot2) {
            error = "Cannot switch Depot with itself!";
            return false;
        }

        if((depot1 == 3 || depot2 == 3) && !extraDepotCheck(0, game)) {
            error = "Cannot switch non active Depots";
            return false;
        }

        if((depot1 == 4 || depot2 == 4) && !extraDepotCheck(1, game)) {
            error = "Cannot switch non active Depots";
            return false;
        }

        return true;
    }



    /**
     * Method used to check if the specified Leader Card can be used for certain messages.
     */
    private static boolean leaderCheck(int i, Game game) {
        String lead;

        if(i == 0)
            lead = "(First)";
        else
            lead = "(Second)";

        for(Player player : game.getPlayers())
            if(player.getNickname().equals(game.getMyNickname()))
                if(player.getLeaderCards()[i].getAction() != LeaderCardAction.PRODUCTIONPOWER) {
                    error = lead + " Leader Card needs to be of \"PRODUCTION POWER\" type to be used in Production!";
                    return false;
                }
                else if(!player.getLeaderCards()[i].isActive()) {
                    error = lead + " Leader Card is not active!";
                    return false;
                }

        return true;
    }

    /**
     * Method used to check if the specified Extra Depot can be used for certain messages.
     */
    private static boolean extraDepotCheck(int i, Game game) {
        RedWarehouse warehouse = new Warehouse();

        for(Player player : game.getPlayers())
            if(player.getNickname().equals(game.getMyNickname()))
                warehouse = player.getWarehouse();

        if(i == 0 && !warehouse.isExtraWarehouseDepot1IsActive()) {
            error = "Extra depots are not active!";
            return false;
        }
        else if(i == 1 && !warehouse.isExtraWarehouseDepot2IsActive()) {
            error = "Extra depot 2 is not active!";
            return false;
        }
        return true;
    }

    /**
     * Method used to check if the specified ArrayList of Resources can be used for certain messages.
     */
    private static boolean checkResources(ArrayList<ResourceType> types, int i) {
        for(ResourceType type : types)
            if(type == ResourceType.NONE) {
                error = "One or more resources were of type NONE!";
                return false;
            }

        if(types.size() != i) {
            error =  i + " resources have to be chosen! (You picked " + types.size() + ")";
            return false;
        }

        return true;
    }

}
