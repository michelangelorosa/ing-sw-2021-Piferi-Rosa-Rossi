package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.View.ANSIColors;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.ReducedModel.Enums.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Print tests for toCli methods in Reduced Model.
 */
public class ToCliTest {
    Strongbox strongbox = new Strongbox();

    @Test
    public void strongboxCliTest() {
        strongbox.getStoredResources().setResource(0, ResourceType.SHIELDS);
        strongbox.getStoredResources().setResource(0, ResourceType.SERVANTS);
        strongbox.getStoredResources().setResource(11, ResourceType.COINS);
        strongbox.getStoredResources().setResource(0, ResourceType.STONES);


        ArrayList<String> strongboxString = strongbox.toCli3();
        for(String string : strongboxString)
            System.out.println(string);
    }

    @Test
    public void warehouseDepotCliTest() {
        WarehouseDepot depot = new WarehouseDepot(3,false);
        depot.setResourceType(ResourceType.SHIELDS);
        depot.setStoredResources(1);
        System.out.println(depot.toCli());

        depot.setResourceType(ResourceType.COINS);
        depot.setStoredResources(2);
        System.out.println(depot.toCli());

        depot.setResourceType(ResourceType.SERVANTS);
        depot.setStoredResources(3);
        System.out.println(depot.toCli());

    }

    @Test
    public void warehouseToCli() {
        Warehouse warehouse = new Warehouse();
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[0].setStoredResources(1);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[1].setStoredResources(1);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);
        for(String s : warehouse.toCli())
            System.out.println(s);

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(2);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.STONES);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.NONE);
        warehouse.getWarehouseDepots()[2].setStoredResources(0);
        for(String s : warehouse.toCli())
            System.out.println(s);

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.NONE);
        warehouse.getWarehouseDepots()[0].setStoredResources(0);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.NONE);
        warehouse.getWarehouseDepots()[2].setStoredResources(0);

        for(String s : warehouse.toCli())
            System.out.println(s);

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(3);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        for(String s : warehouse.toCli())
            System.out.println(s);

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(3);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        warehouse.setExtraWarehouseDepot1IsActive(true);
        warehouse.getExtraWarehouseDepot1().setResourceType(ResourceType.SERVANTS);
        warehouse.getExtraWarehouseDepot1().setStoredResources(1);

        for(String s : warehouse.toCli())
            System.out.println(s);

        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(3);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        warehouse.setExtraWarehouseDepot1IsActive(true);
        warehouse.setExtraWarehouseDepot2IsActive(true);
        warehouse.getExtraWarehouseDepot1().setResourceType(ResourceType.SERVANTS);
        warehouse.getExtraWarehouseDepot2().setResourceType(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot1().setStoredResources(1);
        warehouse.getExtraWarehouseDepot2().setStoredResources(2);

        for(String s : warehouse.toCli())
            System.out.println(s);
    }

    @Test
    public void warehouseAndStrongboxCliTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(3);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        warehouse.setExtraWarehouseDepot1IsActive(true);
        warehouse.setExtraWarehouseDepot2IsActive(true);
        warehouse.getExtraWarehouseDepot1().setResourceType(ResourceType.SERVANTS);
        warehouse.getExtraWarehouseDepot2().setResourceType(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot1().setStoredResources(1);
        warehouse.getExtraWarehouseDepot2().setStoredResources(2);

        for(String s : warehouse.toCli())
            System.out.println(s);

        strongbox.getStoredResources().setResource(0, ResourceType.SHIELDS);
        strongbox.getStoredResources().setResource(0, ResourceType.SERVANTS);
        strongbox.getStoredResources().setResource(11, ResourceType.COINS);
        strongbox.getStoredResources().setResource(0, ResourceType.STONES);
        ArrayList<String> strongboxString = strongbox.toCli3();
        for(String string : strongboxString)
            System.out.println(string);
    }

    @Test
    public void developmentCardToCli() {
        ResourceStack cost = new ResourceStack(1, 1, 0, 0);
        ResourceStack input = new ResourceStack(1, 1, 10, 1);
        ResourceStack output = new ResourceStack(10, 10, 23, 0);
        DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 10, cost, input, output, 10);
        for(String string : card.toCli())
            System.out.println(string);

        cost = new ResourceStack(1, 1, 0, 0);
        input = new ResourceStack(1, 1, 10, 1);
        output = new ResourceStack(1, 4, 0, 1);
        card = new DevelopmentCard(Color.PURPLE, Level.THREE, 1, 7, cost, input, output, 1);
        for(String string : card.toCli())
            System.out.println(string);

        cost = new ResourceStack(99, 99, 99, 99);
        input = new ResourceStack(99, 99, 99, 99);
        output = new ResourceStack(99, 99, 99, 99);
        card = new DevelopmentCard(Color.YELLOW, Level.ONE, 99, 99, cost, input, output, 99);
        for(String string : card.toCli())
            System.out.println(string);

        cost = new ResourceStack(0, 0, 0, 0);
        input = new ResourceStack(0, 0, 0, 0);
        output = new ResourceStack(0, 0, 0, 0);
        card = new DevelopmentCard(Color.GREEN, Level.THREE, 0, 0, cost, input, output, 0);
        for(String string : card.toCli())
            System.out.println(string);
    }

    @Test
    public void cardSlotToCliTest() {
        CardSlot cardSlot = new CardSlot();
        ResourceStack cost = new ResourceStack(1, 1, 0, 0);
        ResourceStack input = new ResourceStack(1, 1, 10, 1);
        ResourceStack output = new ResourceStack(10, 10, 23, 0);
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 10, cost, input, output, 10);

        cost = new ResourceStack(1, 1, 0, 0);
        input = new ResourceStack(1, 1, 10, 1);
        output = new ResourceStack(1, 4, 0, 1);
        DevelopmentCard card2 = new DevelopmentCard(Color.PURPLE, Level.THREE, 1, 7, cost, input, output, 1);

        cost = new ResourceStack(99, 99, 99, 99);
        input = new ResourceStack(99, 99, 99, 99);
        output = new ResourceStack(99, 99, 99, 99);
        DevelopmentCard card3 = new DevelopmentCard(Color.YELLOW, Level.ONE, 99, 99, cost, input, output, 99);

        cardSlot.addCard(card3);
        for(String s : cardSlot.toCli())
            System.out.println(s);

        cardSlot.addCard(card1);
        for(String s : cardSlot.toCli())
            System.out.println(s);

        cardSlot.addCard(card2);
        for(String s : cardSlot.toCli())
            System.out.println(s);
    }

    @Test
    public void developmentCardSlotsToCliTest() {
        DevelopmentCardSlots slots = new DevelopmentCardSlots();

        ResourceStack cost = new ResourceStack(1, 1, 0, 0);
        ResourceStack input = new ResourceStack(1, 1, 10, 1);
        ResourceStack output = new ResourceStack(10, 10, 23, 0);
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 10, cost, input, output, 10);
        DevelopmentCard card2 = new DevelopmentCard(Color.PURPLE, Level.THREE, 1, 7, cost, input, output, 1);
        DevelopmentCard card3 = new DevelopmentCard(Color.YELLOW, Level.ONE, 99, 99, cost, input, output, 99);
        DevelopmentCard card4 = new DevelopmentCard(Color.GREEN, Level.ONE, 99, 99, cost, input, output, 99);
        DevelopmentCard card5 = new DevelopmentCard(Color.GREEN, Level.TWO, 99, 99, cost, input, output, 99);
        DevelopmentCard card6 = new DevelopmentCard(Color.PURPLE, Level.THREE, 99, 99, cost, input, output, 99);
        DevelopmentCard card7 = new DevelopmentCard(Color.BLUE, Level.ONE, 99, 99, cost, input, output, 99);

        slots.getSlots()[0].addCard(card3);
        slots.getSlots()[1].addCard(card7);
        slots.getSlots()[1].addCard(card1);
        slots.getSlots()[2].addCard(card4);
        slots.getSlots()[2].addCard(card5);
        slots.getSlots()[2].addCard(card6);

        ArrayList<String> list = slots.toCli();
        for(String s : list)
            System.out.println(s);
    }

    @Test
    public void basicProductionToCliTest() {
        ResourceStack inputs = new ResourceStack(1, 2, 3, 10);
        ResourceStack outputs = new ResourceStack(10, 0, 23, 0);

        BasicProduction basic = new BasicProduction(inputs, outputs, 1, 2, 3);

        for(String s : basic.toCli())
            System.out.println(s);

        basic = new BasicProduction(10, 23);
        for(String s : basic.toCli())
            System.out.println(s);
    }

    @Test
    public void playerBoardToCliTest() {
        Player player = playerCreator("Giacomo", 0);

        for(String s : player.toCli())
            System.out.println(s);
    }

    @Test
    public void CliTest() {
        System.out.println(ANSIColors.FRONT_BLUE + "\u26CA:8 " + ANSIColors.FRONT_PURPLE + "\u265F:3 "+ ANSIColors.FRONT_YELLOW + "\u26C2:3 "+ ANSIColors.FRONT_GREY + "\u26F0:3 ");
    }

    public static Player playerCreator(String nickname, int turnPosition) {
        Player player = new Player(nickname, turnPosition, true);
        ResourceStack input = new ResourceStack(0, 1, 2, 3);
        BasicProduction basic = new BasicProduction(input, input, 10, 10, 9);

        player.setBasicProduction(basic);
        player.setWarehouse(warehouseCreator());
        player.setStrongbox(strongboxCreator());
        player.setSlots(slotsCreator());

        return player;
    }

    public static Warehouse warehouseCreator() {
        Warehouse warehouse = new Warehouse();
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].setStoredResources(3);
        warehouse.getWarehouseDepots()[1].setResourceType(ResourceType.COINS);
        warehouse.getWarehouseDepots()[1].setStoredResources(2);
        warehouse.getWarehouseDepots()[2].setResourceType(ResourceType.SERVANTS);
        warehouse.getWarehouseDepots()[2].setStoredResources(1);

        warehouse.setExtraWarehouseDepot1IsActive(true);
        warehouse.setExtraWarehouseDepot2IsActive(true);
        warehouse.getExtraWarehouseDepot1().setResourceType(ResourceType.SERVANTS);
        warehouse.getExtraWarehouseDepot2().setResourceType(ResourceType.COINS);
        warehouse.getExtraWarehouseDepot1().setStoredResources(1);
        warehouse.getExtraWarehouseDepot2().setStoredResources(2);

        return warehouse;
    }

    public static Strongbox strongboxCreator() {
        Strongbox strongbox = new Strongbox();

        strongbox.getStoredResources().setResource(2, ResourceType.SHIELDS);
        strongbox.getStoredResources().setResource(3, ResourceType.SERVANTS);
        strongbox.getStoredResources().setResource(11, ResourceType.COINS);
        strongbox.getStoredResources().setResource(44, ResourceType.STONES);

        return strongbox;
    }

    public static DevelopmentCardSlots slotsCreator() {
        DevelopmentCardSlots slots = new DevelopmentCardSlots();

        ResourceStack cost = new ResourceStack(1, 1, 0, 0);
        ResourceStack input = new ResourceStack(1, 1, 10, 1);
        ResourceStack output = new ResourceStack(10, 10, 23, 0);
        DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.TWO, 1, 10, cost, input, output, 10);
        DevelopmentCard card2 = new DevelopmentCard(Color.PURPLE, Level.THREE, 1, 7, cost, input, output, 1);
        DevelopmentCard card3 = new DevelopmentCard(Color.YELLOW, Level.ONE, 99, 99, cost, input, output, 99);
        DevelopmentCard card4 = new DevelopmentCard(Color.GREEN, Level.ONE, 99, 99, cost, input, output, 99);
        DevelopmentCard card5 = new DevelopmentCard(Color.GREEN, Level.TWO, 99, 99, cost, input, output, 99);
        DevelopmentCard card6 = new DevelopmentCard(Color.PURPLE, Level.THREE, 99, 99, cost, input, output, 99);
        DevelopmentCard card7 = new DevelopmentCard(Color.BLUE, Level.ONE, 99, 99, cost, input, output, 99);

        slots.getSlots()[0].addCard(card3);
        slots.getSlots()[1].addCard(card7);
        slots.getSlots()[1].addCard(card1);
        slots.getSlots()[2].addCard(card4);
        slots.getSlots()[2].addCard(card5);
        slots.getSlots()[2].addCard(card6);

        return slots;
    }
    @Test
    public void leaderCardToCliTest() {
        Game game = new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player = new Player("Lello", 0, true);
        Player player1 = new Player("Antonino", 1, false);
        Player player2 = new Player("Marcantonio", 2, false);
        players.add(player);
        players.add(player1);
        players.add(player2);
        game.setPlayers(players);
        game.setMyNickname("Lello");

        ResourceStack resourceStack = new ResourceStack(1,4,3,2);
        LeaderRequirements requirements = new LeaderRequirements(1,2,2,2,2,1,2,2,1,4,2,4);
        ResourceStack discount = new ResourceStack(1,0,0,0);
        LeaderCard card = new LeaderCard(49, 12, resourceStack, requirements, discount);
        card.setActive(true);
        game.getPlayers().get(0).getLeaderCards()[0] = card;

        Marble marble = Marble.RED;
        card = new LeaderCard(50, 12, resourceStack, requirements, marble);
        card.setActive(false);
        game.getPlayers().get(0).getLeaderCards()[1] = card;

        card = new LeaderCard(51,4,resourceStack, requirements, ResourceType.COINS);
        card.setActive(true);
        game.getPlayers().get(1).getLeaderCards()[0] = card;

        ResourceStack input = new ResourceStack(11,2,3,4);
        card = new LeaderCard(52, 78, resourceStack, requirements, input, 5, 4);
        game.getPlayers().get(1).getLeaderCards()[1] = card;

        input = new ResourceStack(11,2,3,4);
        card = new LeaderCard(52, 78, resourceStack, requirements, input, 5, 4);
        game.getPlayers().get(2).getLeaderCards()[0] = card;

        input = new ResourceStack(11,2,3,4);
        card = new LeaderCard(52, 78, resourceStack, requirements, input, 5, 4);
        game.getPlayers().get(2).getLeaderCards()[1] = card;

        boolean up = false;
        if(game.getMyNickname().equals(game.getCurrentPlayer().getNickname())) up = true;

        for(String s : player1.leaderPrint(up))
            System.out.println(s);

    }



    @Test
    public void boardTest(){
        Game game = new Game();
        ArrayList<Player> players= new ArrayList<>();
        Player player = new Player("Lello", 0, true);
        Player player1 = new Player("Antonino", 1, false);
        Player player2 = new Player("Marcantonio", 2, false);
        players.add(player);
        players.add(player1);
        players.add(player2);
        game.setPlayers(players);
        game.setMyNickname("Marcantonio");

        ResourceStack input = new ResourceStack(0, 1, 2, 3);
        BasicProduction basic = new BasicProduction(input, input, 10, 10, 9);

        for(int i = 0; i < game.getPlayers().size(); i++) {
            players.get(i).setBasicProduction(basic);
            players.get(i).setWarehouse(warehouseCreator());
            players.get(i).setStrongbox(strongboxCreator());
            players.get(i).setSlots(slotsCreator());
        }

        ResourceStack resourceStack = new ResourceStack(1,4,3,2);
        LeaderRequirements requirements = new LeaderRequirements(1,2,2,2,2,1,2,2,1,4,2,4);
        ResourceStack discount = new ResourceStack(1,0,0,0);
        LeaderCard card = new LeaderCard(49, 12, resourceStack, requirements, discount);
        card.setActive(true);
        game.getPlayers().get(0).getLeaderCards()[0] = card;

        Marble marble = Marble.GREY;
        card = new LeaderCard(50, 12, resourceStack, requirements, marble);
        card.setActive(false);
        game.getPlayers().get(0).getLeaderCards()[1] = card;

        card = new LeaderCard(51,4,resourceStack, requirements, ResourceType.COINS);
        card.setActive(true);
        game.getPlayers().get(1).getLeaderCards()[0] = card;

        input = new ResourceStack(11,2,3,4);
        card = new LeaderCard(52, 78, resourceStack, requirements, input, 5, 4);
        game.getPlayers().get(1).getLeaderCards()[1] = card;

        input = new ResourceStack(11,2,3,4);
        card = new LeaderCard(52, 78, resourceStack, requirements, input, 5, 4);
        game.getPlayers().get(2).getLeaderCards()[0] = card;

        input = new ResourceStack(11,2,3,4);
        card = new LeaderCard(52, 78, resourceStack, requirements, input, 5, 4);
        game.getPlayers().get(2).getLeaderCards()[1] = card;

        game.boardToCli(game.getPlayers().get(2));

        for(String s : game.boardToCli(game.getPlayers().get(2)))
            System.out.println(s);
    }

    public static DevelopmentCardDeck[][] tableCreator() {
        DevelopmentCardDeck[][] decks = new DevelopmentCardDeck[3][4];

        Random rand = new Random();
        Color[] colors = Color.values();
        Level[] levels = Level.values();

        ResourceStack cost;
        ResourceStack input;
        ResourceStack output;
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
                    decks[i][j].addCard(card);
                }
            }

        return decks;
    }

    @Test
    public void tableToCliTest() {
        DevelopmentCardTable table = new DevelopmentCardTable(tableCreator());
        ArrayList<String> tableString = table.toCli();

        for(String s : tableString)
            System.out.println(s);
    }


    @Test
    public void faithCellToCliTest() {
        FaithCell cell = new FaithCell(0, 15);

        Game game = new Game();
        Player player = new Player("antonio", 0, true);
        Player player1 = new Player("Lorenzo il Magnifico", 1, false);
        Player player2 = new Player("franco", 2, false);
        Player player3 =  new Player("gianpiero", 3, false);

        game.getPlayers().add(player);
        game.getPlayers().add(player1);

        ArrayList<String> cellString = cell.toCli(game);
        for(String s : cellString)
            System.out.println(s);

        game.setGameType(GameType.MULTIPLAYER);

        cellString = cell.toCli(game);
        for(String s : cellString)
            System.out.println(s);

        game.getPlayers().add(player2);

        cellString = cell.toCli(game);
        for(String s : cellString)
            System.out.println(s);

        game.getPlayers().add(player3);

        cellString = cell.toCli(game);
        for(String s : cellString)
            System.out.println(s);
    }

    @Test
    public void vaticanSectionsToCliTest() {
        VaticanReportSection section1 = new VaticanReportSection(0, 5, 30);
        VaticanReportSection section2 = new VaticanReportSection(6, 18, 5);
        VaticanReportSection section3 = new VaticanReportSection(19, 24, 1);

        ArrayList<VaticanReportSection> sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);

        FaithTrack track = new FaithTrack(sections);

        ArrayList<String> trackString = track.sectionsToCli();

        for(String s : trackString)
            System.out.println(s);

        section1 = new VaticanReportSection(1, 2, 30);
        section2 = new VaticanReportSection(10, 18, 5);
        section3 = new VaticanReportSection(23, 24, 1);

        sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);

        track = new FaithTrack(sections);

        trackString = track.sectionsToCli();
        for(String s : trackString)
            System.out.println(s);

        section1 = new VaticanReportSection(1, 1, 30);
        section2 = new VaticanReportSection(10, 10, 5);
        section3 = new VaticanReportSection(22, 22, 1);

        sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);

        track = new FaithTrack(sections);

        trackString = track.sectionsToCli();
        for(String s : trackString)
            System.out.println(s);
    }

    @Test
    public void faithTrackToCliTest() {
        Random rand = new Random();

        VaticanReportSection section1 = new VaticanReportSection(0, 5, 30);
        VaticanReportSection section2 = new VaticanReportSection(6, 18, 5);
        VaticanReportSection section3 = new VaticanReportSection(19, 24, 1);

        ArrayList<VaticanReportSection> sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);

        FaithTrack track = new FaithTrack(sections);

        int vp;

        for(int i = 0; i < 25; i++) {
            vp = rand.nextInt(100);
            track.getCells()[i] = new FaithCell(i, vp);
        }

        Game game = gameCreator(4);

        ArrayList<String> trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceONE(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceTWO(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceTHREE(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);


        section1 = new VaticanReportSection(1, 2, 30);
        section2 = new VaticanReportSection(10, 18, 5);
        section3 = new VaticanReportSection(23, 24, 1);

        sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);

        track = new FaithTrack(sections);

        for(int i = 0; i < 25; i++) {
            vp = rand.nextInt(100);
            track.getCells()[i] = new FaithCell(i, vp);
        }

        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceONE(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceTWO(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceTHREE(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);


        section1 = new VaticanReportSection(0, 0, 30);
        section2 = new VaticanReportSection(1, 1, 5);
        section3 = new VaticanReportSection(22, 22, 1);

        sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);

        track = new FaithTrack(sections);

        for(int i = 0; i < 25; i++) {
            vp = rand.nextInt(100);
            track.getCells()[i] = new FaithCell(i, vp);
        }

        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceONE(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceTWO(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);

        track.setPopeSpaceTHREE(false);
        trackString = track.toCli(game);
        for(String s : trackString)
            System.out.println(s);
    }

    @Test
    public void marbleToCli() {
        Marble marble = Marble.WHITE;
        for(String s : marble.toCli())
            System.out.println(s);

        marble = Marble.BLUE;
        for(String s : marble.toCli())
            System.out.println(s);

        marble = Marble.PURPLE;
        for(String s : marble.toCli())
            System.out.println(s);

        marble = Marble.YELLOW;
        for(String s : marble.toCli())
            System.out.println(s);

        marble = Marble.GREY;
        for(String s : marble.toCli())
            System.out.println(s);

        marble = Marble.RED;
        for(String s : marble.toCli())
            System.out.println(s);
    }

    @Test
    public void marketToCliTest() {
        Random rand = new Random();
        Marble[] marbleValues = Marble.values();

        Marble[][] marbles = new Marble[3][4];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++)
                marbles[i][j] = marbleValues[rand.nextInt(6)];

        Market market = new Market(marbles);
        market.setExtraMarble(marbleValues[rand.nextInt(6)]);

        for(String s : market.toCli())
            System.out.println(s);
    }

    private static Game gameCreator(int numberOfPlayers) {
        Game game = new Game();
        Player player = new Player("antonio", 0, true);
        Player player1 = new Player("Lorenzo il Magnifico", 1, false);
        Player player2 = new Player("franco", 2, false);
        Player player3 =  new Player("gianpiero", 3, false);

        if(numberOfPlayers > 0) {
            game.getPlayers().add(player);
            if(numberOfPlayers > 1) {
                game.getPlayers().add(player1);
                if(numberOfPlayers > 2) {
                    game.getPlayers().add(player2);
                    if(numberOfPlayers > 3) {
                        game.getPlayers().add(player3);
                    }
                }
            }
        }

        return game;
    }

    @Test
    public void popeTilesToCliTest(){
        VaticanReportSection section1 = new VaticanReportSection(0, 5, 2);
        VaticanReportSection section2 = new VaticanReportSection(6, 18, 3);
        VaticanReportSection section3 = new VaticanReportSection(19, 24, 4);

        Game game = gameCreator(4);
        FaithTrack track = new FaithTrack(section1, section2, section3);
        gameCreator(4);

        int vp;

        for(int i = 0; i < 25; i++) {
            vp = 15;
            track.getCells()[i] = new FaithCell(i, vp);
        }
        game.setFaithTrack(track);

        ArrayList<String> trackString = game.getCurrentPlayer().popeTileToCli();
        for(String s : trackString)
            System.out.println(s);

    }

}

