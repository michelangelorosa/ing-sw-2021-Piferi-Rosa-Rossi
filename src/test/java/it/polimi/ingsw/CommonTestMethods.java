package it.polimi.ingsw;

import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.Model.GameModel.*;

import java.util.ArrayList;


/**
 * CommonTestMethods Class contains static methods to simplify complex Tests.
 */
public class CommonTestMethods {

    /**
     * Method to initialize a game with four player each having two Leader Cards.
     * @param game Game to be initialized.
     */
    public static void gameInitOne(Game game) {
        playersInit(game);
        leaderCardInit(game);
    }

    /**
     * Method to add four players to a game.
     * @param game Game to be initialized.
     */
    public static void playersInit(Game game) {
        Player player0 = new Player("Zero", 0, true);
        Player player1 = new Player("One", 1, false);
        Player player2 = new Player("Two", 2, false);
        Player player3 = new Player("Three", 3, false);

        player0.setStatus(PlayerStatus.IN_GAME);
        player1.setStatus(PlayerStatus.IN_GAME);
        player2.setStatus(PlayerStatus.IN_GAME);
        player3.setStatus(PlayerStatus.IN_GAME);

        game.getPlayers().add(player0);
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);
        game.getPlayers().add(player3);

        game.setGameType(GameType.MULTIPLAYER);
    }

    /**
     * Method to give each player of a game two fixed Leader Cards.
     * @param game Game to be initialized.
     */
    public static void leaderCardInit(Game game) {
        game.setLeaderCards(new ArrayList<>());

        ResourceStack stack1 = new ResourceStack(1,1,0,0);
        ResourceStack stack2 = new ResourceStack(0,0,1,1);
        ResourceStack stack3 = new ResourceStack(1,1,1,1);
        ResourceStack stack4 = new ResourceStack(2,0,0,0);

        LeaderRequirements requirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);

        LeaderCard discountOne = new LeaderCard(0, 1, stack1, requirements, stack1);
        LeaderCard discountTwo = new LeaderCard(1, 1, stack1, requirements, stack2);

        LeaderCard whiteMarbleOne = new LeaderCard(2, 1, stack1, requirements, Marble.YELLOW);
        LeaderCard whiteMarbleTwo = new LeaderCard(3, 2, stack1, requirements, Marble.PURPLE);

        LeaderCard productionOne = new LeaderCard(4, 3, stack2, requirements, stack1, 3, 1);
        LeaderCard productionTwo = new LeaderCard(5, 1, stack2, requirements, stack2, 3, 0);

        LeaderCard extraDepotOne = new LeaderCard(1, 2, stack3, requirements, ResourceType.SHIELDS);
        LeaderCard extraDepotTwo = new LeaderCard(1, 2, stack4, requirements, ResourceType.SERVANTS);

        game.getLeaderCards().add(discountOne);
        game.getLeaderCards().add(discountTwo);
        game.getLeaderCards().add(whiteMarbleOne);
        game.getLeaderCards().add(whiteMarbleTwo);
        game.getLeaderCards().add(productionOne);
        game.getLeaderCards().add(productionTwo);
        game.getLeaderCards().add(extraDepotOne);
        game.getLeaderCards().add(extraDepotTwo);
    }

    /**
     * Method used to give a player two given Leader Cards.
     * @param player Player who gets the two cards.
     * @param leaderCard1 First Leader Card to be given
     * @param leaderCard2 Second Leader Card to be given
     */
    public static void givePlayerLeaderCards(Player player, LeaderCard leaderCard1, LeaderCard leaderCard2) {
        player.getBoard().getLeaderCards()[0] = leaderCard1;
        player.getBoard().getLeaderCards()[1] = leaderCard2;
    }

    /**
     * Method used to add Development Cards to a player's Board.
     * @param player Player who is given the cards.
     */
    public static void addCardsToPlayersBoard(Player player) {
        ResourceStack cost;
        ResourceStack input;
        ResourceStack output;
        int outputFaith;

        cost = new ResourceStack(2,0,0,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 1;
        DevelopmentCard card1 = new DevelopmentCard(Color.GREEN, Level.ONE, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(1,1,0,0);
        input = new ResourceStack(0,0,1,1);
        output = new ResourceStack(0,0,1,1);
        outputFaith = 0;
        DevelopmentCard card2 = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,1,1);
        input = new ResourceStack(1,1,0,0);
        output = new ResourceStack(3,0,0,0);
        outputFaith = 2;
        DevelopmentCard card3 = new DevelopmentCard(Color.YELLOW, Level.ONE, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,1,1);
        input = new ResourceStack(2,1,0,0);
        output = new ResourceStack(2,3,0,0);
        outputFaith = 0;
        DevelopmentCard card4 = new DevelopmentCard(Color.PURPLE, Level.TWO, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(1,0,1,1);
        input = new ResourceStack(0,0,0,5);
        output = new ResourceStack(2,3,0,0);
        outputFaith = 0;
        DevelopmentCard card5 = new DevelopmentCard(Color.PURPLE, Level.THREE, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,1,1);
        input = new ResourceStack(2,1,1,0);
        output = new ResourceStack(1,1,1,3);
        outputFaith = 2;
        DevelopmentCard card6 = new DevelopmentCard(Color.GREEN, Level.ONE, 1, 1, cost, input, output, outputFaith);

        player.getBoard().getDevelopmentCardSlots().addCard(0, card1);
        player.getBoard().getDevelopmentCardSlots().addCard(0, card2);

        player.getBoard().getDevelopmentCardSlots().addCard(1, card3);
        player.getBoard().getDevelopmentCardSlots().addCard(1, card4);
        player.getBoard().getDevelopmentCardSlots().addCard(1, card5);

        player.getBoard().getDevelopmentCardSlots().addCard(2, card6);
    }

    /**
     * Method used to give resources to the player
     * @param player current player
     * @param num1 number of resource to add to the first depot
     * @param num2 number of resource to add to the second depot
     * @param num3 number of resource to add to the third depot
     * @param type1 type of the first depot
     * @param type2 type of the second depot
     * @param type3 type of the third depot
     */
    public static void giveResourcesToPlayer(Player player, int num1, int num2, int num3, ResourceType type1, ResourceType type2, ResourceType type3, ResourceStack stack) {
        ResourceManager manager = player.getBoard().getResourceManager();
        manager.reset();
        manager.addMarketResourcesByType(num1, type1, manager.getWarehouseDepots()[0]);
        manager.addMarketResourcesByType(num2, type2, manager.getWarehouseDepots()[1]);
        manager.addMarketResourcesByType(num3, type3, manager.getWarehouseDepots()[2]);
        manager.addProductionResources(stack);
    }

}