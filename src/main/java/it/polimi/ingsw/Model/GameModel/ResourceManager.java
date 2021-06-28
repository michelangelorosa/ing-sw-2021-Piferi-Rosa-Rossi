package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Exceptions.ModelException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ResourceManager Class contains both a Warehouse and a Strongbox type objects (as final
 * attributes). It is used to group all methods regarding resource management.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>Warehouse "warehouse": the player's Warehouse</li>
 *     <li>Strongbox "strongbox": the player's Strongbox</li>
 *     <li>ResourceStack "temporaryResourcesToPay": contains the number of resources (divided by type) which the
 *     player has yet to either add to his Warehouse or pay</li>
 *     <li>int "temporaryWhiteMarbles": number of white marbles which the player has to choose from, if he has
 *     more than two leader cards</li>
 * </ul>
 * @author redrick99
 */
public class ResourceManager implements Serializable {
    private Warehouse warehouse;
    private Strongbox strongbox;

    private ResourceStack temporaryResourcesToPay;
    private int temporaryWhiteMarbles;

    /**
     * Constructor for ResourceManagerClass.
     */
    public ResourceManager() {
        warehouse = new Warehouse();
        strongbox = new Strongbox();
        temporaryResourcesToPay = new ResourceStack(0,0,0,0);
        temporaryWhiteMarbles = 0;
    }

    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Getter for "warehouse" attribute.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "warehouseDepots" array inside "warehouse" attribute.
     */
    public WarehouseDepot[] getWarehouseDepots() {
        return this.warehouse.getWarehouseDepots();
    }

    /**
     * Getter for "extraWarehouseDepot1" inside "warehouse" attribute.
     */
    public WarehouseDepot getExtraWarehouseDepotOne() {
        return this.warehouse.getExtraWarehouseDepot1();
    }

    /**
     * Getter for "extraWarehouseDepot2" inside "warehouse" attribute.
     */
    public WarehouseDepot getExtraWarehouseDepotTwo() {
        return this.warehouse.getExtraWarehouseDepot2();
    }

    /**
     * Getter for "isExtraDepot1IsActive" inside "warehouse" attribute.
     */
    public boolean isExtraDepotOneActive() {
        return this.warehouse.isExtraWarehouseDepot1IsActive();
    }

    /**
     * Getter for "isExtraDepot2IsActive" inside "warehouse".
     */
    public boolean isExtraDepotTwoActive() {
        return this.warehouse.isExtraWarehouseDepot2IsActive();
    }

    /**
     * Getter for "strongbox" attribute.
     */
    public Strongbox getStrongbox() {
        return strongbox;
    }

    /**
     * Getter for "temporaryResourcesToPay" attribute.
     */
    public ResourceStack getTemporaryResourcesToPay() {
        return temporaryResourcesToPay;
    }

    /**
     * Setter for "temporaryResourcesToPay" attribute.
     */
    public void setTemporaryResourcesToPay(ResourceStack temporaryResourcesToPay) {
        this.temporaryResourcesToPay = temporaryResourcesToPay;
    }

    /**
     * Getter for "temporaryWhiteMarbles" attribute.
     */
    public int getTemporaryWhiteMarbles() {
        return temporaryWhiteMarbles;
    }

    /**
     * Setter for "temporaryResourcesToPay" attribute.
     */
    public void setTemporaryWhiteMarbles(int temporaryWhiteMarbles) {
        this.temporaryWhiteMarbles = temporaryWhiteMarbles;
    }


    /**
     * Adds one white marble to the temporaryWhiteMarble counter.
     */
    public void addWhiteMarble() {
        temporaryWhiteMarbles++;
    }

    /**
     * Removes one white marble from the temporaryWhiteMarble counter.
     */
    public void removeWhiteMarble() throws IllegalArgumentException {
        temporaryWhiteMarbles--;
        if(temporaryWhiteMarbles < 0)
            throw new IllegalArgumentException("TemporaryWhiteMarbles number is already 0.");
    }

    /**
     * Reverts the Resource Manager to its initial conditions.
     */
    public void reset() {
        this.warehouse.reset();
        this.strongbox.reset();
    }

    /**
     * Count the total number of resources stored in the warehouse and the strongbox, divided by their type.
     * @return a stack containing the counted resources divided by type.
     */
    public ResourceStack countAllResources() {
        ResourceStack resourceStack = new ResourceStack(0,0,0,0);
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++)
            resourceStack.setResource(warehouse.countResourcesByType(resourceTypes[i]) + strongbox.countResourcesByType(resourceTypes[i]), resourceTypes[i]);

        return resourceStack;
    }

    /**
     * Counts the total number of resources of a specified type stored
     * in both the warehouse and the strongbox.
     * @param resourceType Type of resource to count.
     * @return the number of resources of the specified type
     */
    public int countAllResourcesByType(ResourceType resourceType) {
        return this.warehouse.countResourcesByType(resourceType) + this.strongbox.countResourcesByType(resourceType);
    }

    /**
     * Counts the number of victory points given to the player at the end of the
     * game based of how many resources he has in his warehouse and strongbox.
     * @return the corresponding victory points (total number of resources divided by 5).
     */
    public int countResourcesToVictoryPoints() {
        int allResources = 0;
        ResourceType[] resourceTypes = ResourceType.values();
        for(int i = 1; i <= 4; i++)
            allResources += warehouse.countResourcesByType(resourceTypes[i]) + strongbox.countResourcesByType(resourceTypes[i]);

        return allResources/5;
    }

    /**
     * Adds resources to the strongbox when a production process has been completed.
     * @param resourceStack Stack containing the production's output, divided by type.
     */
    public void addProductionResources(ResourceStack resourceStack) {
        this.strongbox.addToAllTypes(resourceStack);
    }

    /**
     * Adds a number of resources of a specified type coming from the market to a depot inside the Warehouse.
     * <p>
     * The method checks if it is possible to fit the specified type of resources inside the depot,
     * then either proceeds to compute the action, or notifies the player in case the resources
     * cannot be stored inside the depot.
     * @param resourcesToAdd Number of resources to add.
     * @param type Type of resources that have to be added
     * @param depot Depot where the player wants the resources to be added.
     * @return the number of resources that could not fit inside the specified depot
     */
    public int addMarketResourcesByType(int resourcesToAdd, ResourceType type, WarehouseDepot depot) {
        if(this.warehouse.isFull()) {
            System.out.println("The Warehouse is full!");
            return resourcesToAdd;
        } else if(!this.warehouse.resourceTypeFits(type)) {
            System.out.println("Can't fit any more "+type+" inside the Warehouse!");
            return resourcesToAdd;
        } else if(depot.getResourceType() != type && depot.getResourceType() != ResourceType.NONE) {
            System.out.println("Can't fit "+type+" inside a " + depot.getResourceType() + " Depot!");
            return resourcesToAdd;
        } else if(depot.getResourceType() == ResourceType.NONE && !this.warehouse.areEmptyDepotsFillableByType(type)) {
            System.out.println("Can't fit "+type+" inside empty depot as there are "+type+" already stored in the Warehouse!");
            return resourcesToAdd;
        } else if(depot.isFull()) {
            System.out.println("This Depot is full. Select a valid Depot!");
            return resourcesToAdd;
        }

        return this.warehouse.addResources(resourcesToAdd, type, depot);
    }

    /**
     * Checks if the player is able to fit any resource inside the Warehouse.
     * @return true if at least one resources of any type fits.
     */
    public boolean canAddMarketResources() {
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++)
            if (this.warehouse.canAddResource(resourceTypes[i]))
                return true;

        return false;
    }

    /**
     * Checks if one Type of resource can fit inside a specified Depot.
     * @param type ResourceType of the resource to insert.
     * @param depot WarehouseDepot where the resource needs to be inserted.
     * @return true if the resource can fit.
     */
    public boolean canAddToDepot(ResourceType type, WarehouseDepot depot) {
        return this.warehouse.canAddToDepot(type, depot);
    }

    /**
     * Adds one Resource of a specified Type inside a specified Depot.
     * The Resource is also removed from the Temporary Resources to Pay.
     * @param type Type of the resource to be inserted
     * @param depot Depot where the Resource will be inserted.
     */
    public void addOneResourceToDepot(ResourceType type, WarehouseDepot depot) {
        this.temporaryResourcesToPay.removeResource(1, type);
        this.warehouse.addResources(1, type, depot);
    }

    /**
     * Transforms remaining resources of a market interaction into faith points
     * for all non currently playing players.
     * @param player Player who's playing now.
     * @param players All player's of the current game.
     * @param faithTrack Faith Track of the current game.
     */
    public void remainingResourcesToFaith(Player player, ArrayList<Player> players, FaithTrack faithTrack) throws ModelException {
        int faith = this.temporaryResourcesToPay.totalResourcesToInt();
        this.temporaryResourcesToPay = new ResourceStack(0,0,0,0);
        faithTrack.allAhead(player, players, faith);
    }

    /**
     * Checks if the player finished paying the correct amount when activating
     * production o buying a card.
     * @return True if the player has payed all.
     */
    public boolean hasPayed() {
        return temporaryResourcesToPay.isEmpty();
    }

    /**
     * Checks if a specified Resource is needed to pay for the current transaction.
     * @param resourceType Type of resource selected by the player.
     * @return True if the Resource Type is needed to pay.
     */
    public boolean resourceIsNeededToPay(ResourceType resourceType) {
        return temporaryResourcesToPay.getResource(resourceType) > 0;
    }

    /**
     * Pays one resource from a Warehouse Depot.
     * @param depot Warehouse Depot to take the resource from.
     */
    public void payOneResourceWarehouse(WarehouseDepot depot) {
        temporaryResourcesToPay.removeResource(1, depot.getResourceType());
        depot.removeResources(1);
    }

    /**
     * Pays one resource from the Strongbox.
     * @param type Resource Type the player wants to pay.
     */
    public void payOneResourceStrongbox(ResourceType type) {
        temporaryResourcesToPay.removeResource(1, type);
        strongbox.removeOneResourcesByType(type);
    }

    /**
     * Checks if a specified card is buyable from the player.
     * <p>
     * The player is notified if he doesn't have enough resources to buy the card.
     * @param developmentCard Card the player wants to buy.
     * @return true if the card is buyable.
     */
    public boolean cardIsBuyable(DevelopmentCard developmentCard, LeaderCard[] leaderCards) {
        boolean isBuyable = true;
        ResourceStack cardCost = developmentCard.getCost();
        ResourceStack cardToBuyCost = cardCost.copyStack();
        ResourceType[] resourceTypes = ResourceType.values();

        for(LeaderCard leaderCard : leaderCards)
            if(leaderCard.isActive() && leaderCard.getAction() == LeaderCardAction.DISCOUNT)
                cardToBuyCost.removeFromAllTypes(leaderCard.getDiscount());

        for(int i = 1; i <= 4; i++)
            if(cardToBuyCost.getResource(resourceTypes[i]) > countAllResourcesByType(resourceTypes[i])) {
                System.out.println("Not enough "+resourceTypes[i]+" to buy card!");
                isBuyable = false;
            }

        return isBuyable;
    }

    /**
     * Checks if a specified leader card can be activated by the player.
     * <p>
     * The player is notified if he doesn't have enough resources to activate the leader card.
     * @param leaderCard Card the player wants to activate.
     * @return true if the card can be activated.
     */
    public boolean hasResourcesToActivateLeaderCard(LeaderCard leaderCard) {
        ResourceStack resourcesNeeded = leaderCard.getResourcesRequired();
        ResourceStack resourcesHad = this.countAllResources();
        ResourceType[] resourceTypes = ResourceType.values();

        for(int i = 1; i <= 4; i++)
            if(resourcesHad.getResource(resourceTypes[i]) < resourcesNeeded.getResource(resourceTypes[i]))
                return false;

        return true;
    }

    /**
     * Activates an extra Leader Depot of the type specified by a Leader Card.
     * <ul>
     *      <li>The LeaderCard's ability has to be of type "EXTRADEPOT" and ACTIVE, or else
     *      an Error message is displayed.</li>
     *      <li>This method is to be called ONCE (when it's activated) for each LeaderCard the player
     *      possesses, only if the LeaderCard's ability is of type "EXTRADEPOT".</li>
     * </ul>
     * @param leaderCard LeaderCard used to activate the depot
     */
    public void activateLeaderDepot(LeaderCard leaderCard) throws IllegalArgumentException {
        if(leaderCard.getAction() != LeaderCardAction.EXTRADEPOT)
            throw new IllegalArgumentException("Needed EXTRADEPOT LeaderAbility to activate extra depot (was "+leaderCard.getAction()+" instead)");
        else if(!leaderCard.isActive())
            throw new IllegalArgumentException("EXTRADEPOT LeaderAbility has to be active to activate extra depot");
        else
            this.warehouse.activateLeaderDepot(leaderCard.getResource());
    }


}
