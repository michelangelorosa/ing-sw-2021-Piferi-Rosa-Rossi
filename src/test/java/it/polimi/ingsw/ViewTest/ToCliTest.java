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
    }

    @Test
    public void CliTest() {
        System.out.println(ANSIColors.FRONT_BLUE + "\u26CA:8 " + ANSIColors.FRONT_PURPLE + "\u265F:3 "+ ANSIColors.FRONT_YELLOW + "\u26C2:3 "+ ANSIColors.FRONT_GREY + "\u26F0:3 ");
    }
}
