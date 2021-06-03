package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardDeck;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardSlots;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardTable;
import it.polimi.ingsw.View.ReducedModel.RedFaithTrack;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedMarket;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.Random;

public class CommonViewTestMethods {
    public static Game gameCreator() {
        Game game = new Game();
        game.setDevelopmentCardTable(tableCreator());
        game.setFaithTrack(faithTrackCreator());
        game.setMarket(marketCreator());

        game.getPlayers().add(new Player("antonio", 0, true));
        game.getPlayers().add(new Player("Lorenzo il Magnifico", 1, false));
        game.getPlayers().add(new Player("franco", 2, false));
        game.getPlayers().add(new Player("gianpiero", 3, false));

        game.getPlayers().get(0).setWarehouse(warehouseCreator());
        game.getPlayers().get(0).setStrongbox(strongboxCreator());
        game.getPlayers().get(0).setSlots(slotsCreator());
        leaderCardsCreator(game.getPlayers().get(0));

        game.setMyNickname("antonio");

        return game;
    }

    public static RedFaithTrack faithTrackCreator() {
        VaticanReportSection one = new VaticanReportSection(1, 5, 3);
        VaticanReportSection two = new VaticanReportSection(6, 12, 5);
        VaticanReportSection three = new VaticanReportSection(14, 22, 10);
        RedFaithTrack faithTrack = new FaithTrack(one, two, three);

        for(int i = 0; i < 25; i++) {
            faithTrack.getCells()[i] = new FaithCell(i, i+1);
        }
        return faithTrack;
    }

    public static RedDevelopmentCardTable tableCreator() {
        RedDevelopmentCardDeck[][] decks = new RedDevelopmentCardDeck[3][4];

        Random rand = new Random();
        Color[] colors = Color.values();
        Level[] levels = Level.values();

        RedResourceStack cost;
        RedResourceStack input;
        RedResourceStack output;
        DevelopmentCard card;

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++) {
                Color color = colors[rand.nextInt(4)];
                Level level = levels[rand.nextInt(3)];
                decks[i][j] = new DevelopmentCardDeck(color, level);
                for (int k = 0; k < 4; k++) {
                    cost = new ResourceStack(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), rand.nextInt(11));
                    input = new ResourceStack(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), rand.nextInt(11));
                    output = new ResourceStack(rand.nextInt(11), rand.nextInt(11), rand.nextInt(11), rand.nextInt(11));
                    card = new DevelopmentCard(color, level, 0, rand.nextInt(100), cost, input, output, rand.nextInt(50));
                    ((DevelopmentCardDeck)decks[i][j]).addCard(card);
                }
            }
        return new DevelopmentCardTable(decks);
    }

    public static RedMarket marketCreator() {
        Random rand = new Random();
        Marble[] marbleValues = Marble.values();

        Marble[][] marbles = new Marble[3][4];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++)
                marbles[i][j] = marbleValues[rand.nextInt(6)];

        RedMarket market = new Market(marbles);
        ((Market)market).setExtraMarble(marbleValues[rand.nextInt(6)]);
        return market;
    }

    public static RedWarehouse warehouseCreator() {
        Warehouse warehouse = new Warehouse();
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(3);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        warehouse.activateLeaderDepot(ResourceType.SERVANTS);
        warehouse.getExtraWarehouseDepot1().setStoredResources(1);

        return warehouse;
    }

    public static RedStrongbox strongboxCreator() {
        RedStrongbox strongbox = new Strongbox();

        strongbox.getStoredResources().setResource(2, ResourceType.SHIELDS);
        strongbox.getStoredResources().setResource(3, ResourceType.SERVANTS);
        strongbox.getStoredResources().setResource(11, ResourceType.COINS);
        strongbox.getStoredResources().setResource(44, ResourceType.STONES);

        return strongbox;
    }

    public static RedDevelopmentCardSlots slotsCreator() {
        RedDevelopmentCardSlots slots = new DevelopmentCardSlots();

        RedResourceStack cost = new ResourceStack(1, 1, 0, 0);
        RedResourceStack input = new ResourceStack(1, 1, 10, 1);
        RedResourceStack output = new ResourceStack(10, 10, 23, 0);
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 10, cost, input, output, 10);
        DevelopmentCard card3 = new DevelopmentCard(Color.YELLOW, Level.ONE, 99, 99, cost, input, output, 99);
        DevelopmentCard card4 = new DevelopmentCard(Color.GREEN, Level.ONE, 99, 99, cost, input, output, 99);
        DevelopmentCard card5 = new DevelopmentCard(Color.GREEN, Level.TWO, 99, 99, cost, input, output, 99);
        DevelopmentCard card6 = new DevelopmentCard(Color.PURPLE, Level.THREE, 99, 99, cost, input, output, 99);
        DevelopmentCard card7 = new DevelopmentCard(Color.BLUE, Level.ONE, 99, 99, cost, input, output, 99);

        ((CardSlot)slots.getSlots()[0]).addCard(card3);
        ((CardSlot)slots.getSlots()[1]).addCard(card7);
        ((CardSlot)slots.getSlots()[1]).addCard(card1);
        ((CardSlot)slots.getSlots()[2]).addCard(card4);
        ((CardSlot)slots.getSlots()[2]).addCard(card5);
        ((CardSlot)slots.getSlots()[2]).addCard(card6);

        return slots;
    }

    public static void leaderCardsCreator(Player player) {
        LeaderRequirements leaderRequirements = new LeaderRequirements(1, 2, 1, 0, 0,0,0,0,0,0,0,0);
        ResourceStack resourceStack = new ResourceStack(1,1,1,1);
        player.getLeaderCards()[0] = new LeaderCard(1, 1, resourceStack, leaderRequirements, Marble.PURPLE);
        player.getLeaderCards()[1] = new LeaderCard(1, 1, resourceStack, leaderRequirements, resourceStack, 1, 2);
    }

    public static void giveLeaderCards(Player player, LeaderCard card1, LeaderCard card2) {
        card1.setActive(true);
        card2.setActive(true);
        player.getLeaderCards()[0] = card1;
        player.getLeaderCards()[1] = card2;
    }

    public static Player findPlayerByNickname(Game game, String nickname) {
        for(Player player : game.getPlayers())
            if(player.getNickname().equals(nickname))
                return player;

        return null;
    }

    public static UserInteraction createUserInteraction() {
        UserInteraction userInteraction = new UserInteraction() {
        };

        userInteraction.setGame(gameCreator());
        userInteraction.setUi(new CliController());

        return userInteraction;
    }
}
