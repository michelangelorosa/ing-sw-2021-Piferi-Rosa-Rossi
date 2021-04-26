package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.View.ANSIColors;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.ReducedModel.Enums.Color;
import it.polimi.ingsw.View.ReducedModel.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.Enums.ResourceType;
import org.junit.Test;

import java.util.ArrayList;

public class ToCliTest {
    Strongbox strongbox = new Strongbox();

    @Test
    public void strongboxCliTest() {
        strongbox.getStoredResources().setResource(0, ResourceType.SHIELDS);
        strongbox.getStoredResources().setResource(0, ResourceType.SERVANTS);
        strongbox.getStoredResources().setResource(11, ResourceType.COINS);
        strongbox.getStoredResources().setResource(0, ResourceType.STONES);

        System.out.println(strongbox.toCli());

        System.out.println(strongbox.toCli2());

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

    private static Player playerCreator(String nickname, int turnPosition) {
        Player player = new Player(nickname, turnPosition, true);
        ResourceStack input = new ResourceStack(0, 1, 2, 3);
        BasicProduction basic = new BasicProduction(input, input, 10, 10, 9);

        player.setBasicProduction(basic);
        player.setWarehouse(warehouseCreator());
        player.setStrongbox(strongboxCreator());
        player.setSlots(slotsCreator());

        return player;
    }

    private static Warehouse warehouseCreator() {
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

    private static Strongbox strongboxCreator() {
        Strongbox strongbox = new Strongbox();

        strongbox.getStoredResources().setResource(2, ResourceType.SHIELDS);
        strongbox.getStoredResources().setResource(3, ResourceType.SERVANTS);
        strongbox.getStoredResources().setResource(11, ResourceType.COINS);
        strongbox.getStoredResources().setResource(44, ResourceType.STONES);

        return strongbox;
    }

    private static DevelopmentCardSlots slotsCreator() {
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
}
