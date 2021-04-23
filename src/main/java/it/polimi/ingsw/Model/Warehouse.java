package it.polimi.ingsw.Model;

import java.util.ArrayList;

/**
 * Warehouse Class defines the contents and the behaviour of the warehouse inside the player's board.
 * Each warehouse contains three warehouseDepot-type objects organized in an array, plus two extra
 * warehouseDepot-type objects which are activated if the player has activated the relative Leader Card
 * ability (via the boolean attributes).
 */
public class Warehouse {
    private final WarehouseDepot[] warehouseDepots;
    private final WarehouseDepot extraWarehouseDepot1;
    private final WarehouseDepot extraWarehouseDepot2;
    private boolean extraWarehouseDepot1IsActive;
    private boolean extraWarehouseDepot2IsActive;

    /**
     * Constructor for Warehouse Class. It creates the "pyramid" of fixed-length depots and the two extra
     * depots. In addiction to that, it initializes the boolean attributes to "false".
     */
    public Warehouse() {
        this.warehouseDepots = new WarehouseDepot[3];
        this.warehouseDepots[0] = new WarehouseDepot(3,false);
        this.warehouseDepots[1] = new WarehouseDepot(2,false);
        this.warehouseDepots[2] = new WarehouseDepot(1,false);

        this.extraWarehouseDepot1 = new WarehouseDepot(2,true);
        this.extraWarehouseDepot2 = new WarehouseDepot(2,true);
        this.extraWarehouseDepot1IsActive = false;
        this.extraWarehouseDepot2IsActive = false;
    }

    /**
     * Getter for "warehouseDepots" array attribute in Warehouse Class.
     */
    public WarehouseDepot[] getWarehouseDepots() {
        return warehouseDepots;
    }

    /**
     * Getter for "extraWarehouseDepot1" attribute in Warehouse Class.
     */
    public WarehouseDepot getExtraWarehouseDepot1() {
        return extraWarehouseDepot1;
    }

    /**
     * Getter for "extraWarehouseDepot2" attribute in Warehouse Class.
     */
    public WarehouseDepot getExtraWarehouseDepot2() {
        return extraWarehouseDepot2;
    }

    /**
     * Getter for "extraWarehouseDepot1IsActive" attribute in Warehouse Class.
     */
    public boolean isExtraWarehouseDepot1IsActive() {
        return extraWarehouseDepot1IsActive;
    }

    /**
     * Getter for "extraWarehouseDepot2IsActive" attribute in Warehouse Class.
     */
    public boolean isExtraWarehouseDepot2IsActive() {
        return extraWarehouseDepot2IsActive;
    }

    /**
     * This method resets the Warehouse to its initial state.
     */
    public void reset() {
        this.warehouseDepots[0].setStoredResources(0);
        this.warehouseDepots[1].setStoredResources(0);
        this.warehouseDepots[2].setStoredResources(0);
        this.extraWarehouseDepot1.setStoredResources(0);
        this.extraWarehouseDepot2.setStoredResources(0);
        this.extraWarehouseDepot1IsActive = false;
        this.extraWarehouseDepot2IsActive = false;
    }

    /**
     * This method is used to activate the extra depots and set its resource type when a card leader
     * ability is active.
     * <p>
     * The first time it is called, it activates the first extra depot, while the second time it
     * activates the second extra depot.
     * If the method is called a third time, it just displays a message.
     * @param resourceType is the resource type to assign to the extra depot to activate.
     */
    public void activateLeaderDepot(ResourceType resourceType) {
        if(!this.extraWarehouseDepot1IsActive) {
            this.extraWarehouseDepot1IsActive = true;
            this.extraWarehouseDepot1.setResourceType(resourceType);
        }

        else if(!this.extraWarehouseDepot2IsActive) {
            this.extraWarehouseDepot2IsActive = true;
            this.extraWarehouseDepot2.setResourceType(resourceType);
        }
        else
            System.out.println("Max number of depots already reached!");
    }

    /**
     * This method is used to count all resources of a certain type
     * inside the warehouse (including the leader-activated depots, if they are active).
     * Specifically, the standard depots and the active extra depots are added to an ArrayList and
     * the resources are counted in a cycle.
     * @param resourceType specifies the type of resource searched.
     * @return the number of resources counted.
     */
    public int countResourcesByType(ResourceType resourceType) {
        int resources = 0;
        ArrayList<WarehouseDepot> depots = new ArrayList<>();
        depots.add(warehouseDepots[0]);
        depots.add(warehouseDepots[1]);
        depots.add(warehouseDepots[2]);

        if(extraWarehouseDepot1IsActive)
            depots.add(extraWarehouseDepot1);
        if(extraWarehouseDepot2IsActive)
            depots.add(extraWarehouseDepot2);

        for(WarehouseDepot depot : depots)
            if(depot.getResourceType() == resourceType)
                resources += depot.getStoredResources();

        return resources;
    }

    /**
     * This method is used to add resources of a certain type to a WarehouseDepot-type object. It returns the
     * number of resources which don't fit inside the depot.
     * The method checks if the depot's resource type is not equal to the specified resource type or if the
     * depot is full, in which case it displays an error message and returns the full number of resources given.
     * @param resourcesToAdd is the number of resources to add.
     * @param resourceType is the type of resources to add.
     * @param depot is the depot in which the resources are to be added (if possible).
     * @return the number of resources which could not fit inside the depot.
     */
    public int addResources(int resourcesToAdd, ResourceType resourceType, WarehouseDepot depot) {
        int extraResources = resourcesToAdd;

        if(this.isFull()) {
            System.err.println("Error: Warehouse is Full!");
            return extraResources;
        }

        if(depot.getResourceType() == ResourceType.NONE)
            depot.setResourceType(resourceType);
        if(depot.getResourceType() == resourceType && depot.getStoredResources() < depot.getMaxResources())
            extraResources = depot.addResources(resourcesToAdd);
        else
            System.out.println("Choose a valid depot! (Has to be same type and not full)");

        return extraResources;
    }

    /**
     * Method used to check if a specified resource can be added to a specified depot.
     * @param type Type of the resource to be added.
     * @param depot Depot where the player wants to add the resource
     * @return True if the resource can be added.
     */
    public boolean canAddToDepot(ResourceType type, WarehouseDepot depot) {
            if(depot.getResourceType() == ResourceType.NONE && this.areEmptyDepotsFillableByType(type))
                return true;
            if(depot.getResourceType() == type && !depot.isFull())
                return true;

            return false;
    }

    /**
     * Method used to check if it is possible to remove resources from a depot.
     * @param depot Depot the player wants to remove resources from.
     * @return True if the depot is not empty.
     */
    public boolean canRemoveFromDepot(WarehouseDepot depot){
        return !depot.isEmpty();
    }

    /**
     * Method used to remove a resource from a specified depot.
     * @param depot Depot the player wants to remove resources from.
     */
    public void removeResourceFromDepot(WarehouseDepot depot) {
        if(!depot.isEmpty())
            depot.removeResources(1);
    }

    /**
     * This method is used to remove a number of resources of a certain type from the depot.
     * <p>
     * --ASSUMPTION--: When a production that requires resources is activated, the resources are automatically
     * extracted in the following order (which we have considered to be the best possible order for the player):
     * <ol>
     * <li>From the standard(not coming from a leader ability) depot of the specified resource type (if there are any)</li>
     * <li>From the first extra depot (if it is active and of the specified resource type)</li>
     * <li>From the second extra depot (if it is active and of the specified resource type)</li>
     * </ol>
     * <p>
     * If the number of resources to remove is greater than the total number of resources of the specified type,
     * the method displays an error message and immediately returns.
     * <p>
     * @param resourcesToRemove is the number of resources to be removed from the warehouse.
     * @param resourceType is the type of resource which are to be removed from the warehouse
     */
    public void removeResourcesByType(int resourcesToRemove, ResourceType resourceType) {
        int resourceCount;
        if(resourcesToRemove > this.countResourcesByType(resourceType)) {
            System.err.println("Error: Warehouse doesn't have that many " + resourceType + " type resources");
            return;
        }

        for(WarehouseDepot depot : warehouseDepots)
            if(depot.getResourceType() == resourceType) {
                resourceCount = depot.getStoredResources();

                if(resourceCount >= resourcesToRemove) {
                    depot.removeResources(resourcesToRemove);
                    return;
                } else {
                    depot.removeResources(resourceCount);
                    resourcesToRemove -= resourceCount;
                }
            }


        if(extraWarehouseDepot1.getResourceType() == resourceType) {
            resourceCount = extraWarehouseDepot1.getStoredResources();

            if(resourceCount >= resourcesToRemove) {
                extraWarehouseDepot1.removeResources(resourcesToRemove);
                return;
            } else {
                extraWarehouseDepot1.removeResources(resourceCount);
                resourcesToRemove -= resourceCount;
            }
        }


        if(extraWarehouseDepot2.getResourceType() == resourceType) {
            resourceCount = extraWarehouseDepot2.getStoredResources();
            if(resourceCount >= resourcesToRemove)
                extraWarehouseDepot2.removeResources(resourcesToRemove);
            else
                System.err.println("Error: Control Failure(1) -> Tried to remove more resources than stored");
            resourcesToRemove = 0;
        }

        if(resourcesToRemove != 0)
            System.err.println("Error: Control Failure(2) -> Tried to remove more resources than stored");
    }

    public boolean canSwitchDepots(WarehouseDepot firstDepot, WarehouseDepot secondDepot) {
        if(firstDepot.getStoredResources() > secondDepot.getMaxResources() || secondDepot.getStoredResources() > firstDepot.getMaxResources())
            return false;

        if(firstDepot.isFromLeaderCardAbility() && secondDepot.isFromLeaderCardAbility() && firstDepot.getResourceType() == secondDepot.getResourceType())
            return true;

        if(firstDepot.isFromLeaderCardAbility())
            if(secondDepot.getResourceType() == firstDepot.getResourceType())
                return true;
            else if(secondDepot.getResourceType() == ResourceType.NONE && areEmptyDepotsFillableByType(firstDepot.getResourceType()))
                return true;
            else
                return false;

        if(secondDepot.isFromLeaderCardAbility())
            if(firstDepot.getResourceType() == secondDepot.getResourceType())
                return true;
            else if(firstDepot.getResourceType() == ResourceType.NONE && areEmptyDepotsFillableByType(secondDepot.getResourceType()))
                return true;
            else
                return false;

        return true;
    }

    /**
     * Method used to switch resources of two depots.
     * @param firstDepot First Depot to switch.
     * @param secondDepot Second Depot to switch.
     */
    public void switchDepots(WarehouseDepot firstDepot, WarehouseDepot secondDepot) {
        ResourceType tempType = firstDepot.getResourceType();
        int tempStoredResources = firstDepot.getStoredResources();

        firstDepot.setResourceType(secondDepot.getResourceType());
        firstDepot.setStoredResources(secondDepot.getStoredResources());
        secondDepot.setResourceType(tempType);
        secondDepot.setStoredResources(tempStoredResources);
    }

    /**
     * This method is used to switch resources from a depot to another inside the warehouse.
     * <p>
     * Given both the depots which are to be switched, the method first checks if it is actually possible to switch
     * them by confronting their maximum resource capacity and the number of stored resources.
     * <p>
     * @param firstDepot is the first depot to switch.
     * @param secondDepot is the second depot to switch.
     * @return true if the depots have been switched, false if they cannot be switched.
     */
    public boolean switchDepotResources(WarehouseDepot firstDepot, WarehouseDepot secondDepot) {
        if(firstDepot.getStoredResources() > secondDepot.getMaxResources() || secondDepot.getStoredResources() > firstDepot.getMaxResources()) {
            System.err.println("Error: Too many resources -> Can't switch depots");
            return false;
        }
        else if(firstDepot.getStoredResources() == secondDepot.getStoredResources() || (firstDepot.getStoredResources() <= secondDepot.getMaxResources() && secondDepot.getStoredResources() <= firstDepot.getMaxResources())) {
            ResourceType tempType = firstDepot.getResourceType();
            int tempStoredResources = firstDepot.getStoredResources();

            firstDepot.setResourceType(secondDepot.getResourceType());
            firstDepot.setStoredResources(secondDepot.getStoredResources());
            secondDepot.setResourceType(tempType);
            secondDepot.setStoredResources(tempStoredResources);
            return true;
        }
        else {
            System.err.println("Error: Too many resources -> Can't switch depots");
            return false;
        }
    }

    /**
     * This method returns true if every (active) depot inside the warehouse is full.
     * @return true if full.
     */
    public boolean isFull() {
        if(this.isExtraWarehouseDepot1IsActive() && this.isExtraWarehouseDepot2IsActive())
            return (extraWarehouseDepot1.isFull() && extraWarehouseDepot2.isFull() && warehouseDepots[0].isFull() && warehouseDepots[1].isFull() && warehouseDepots[2].isFull());
        else if (this.isExtraWarehouseDepot1IsActive() && !this.isExtraWarehouseDepot2IsActive())
            return (extraWarehouseDepot1.isFull() && warehouseDepots[0].isFull() && warehouseDepots[1].isFull() && warehouseDepots[2].isFull());
        else if (!this.isExtraWarehouseDepot1IsActive() && this.isExtraWarehouseDepot2IsActive())
            return  (extraWarehouseDepot2.isFull() && warehouseDepots[0].isFull() && warehouseDepots[1].isFull() && warehouseDepots[2].isFull());
        else
            return (warehouseDepots[0].isFull() && warehouseDepots[1].isFull() && warehouseDepots[2].isFull());
    }

    /**
     * This method returns true if a certain type of resource can fit inside the warehouse.
     * @return true if the resource can fit.
     */
    public boolean resourceTypeFits(ResourceType resourceType) {
        boolean fitsInStandardDepot = false;
        boolean fitsInExtraDepot1 = false;
        boolean fitsInExtraDepot2 = false;

        for(WarehouseDepot depot : warehouseDepots)
            if(depot.getResourceType() == resourceType)
                fitsInStandardDepot = !depot.isFull();

        if(extraWarehouseDepot1IsActive && extraWarehouseDepot1.getResourceType() == resourceType)
            fitsInExtraDepot1 = !extraWarehouseDepot1.isFull();
        if(extraWarehouseDepot2IsActive && extraWarehouseDepot2.getResourceType() == resourceType)
            fitsInExtraDepot2 = !extraWarehouseDepot2.isFull();

        return fitsInStandardDepot || fitsInExtraDepot1 || fitsInExtraDepot2 || (this.areEmptyDepotsFillableByType(resourceType) && this.emptyDepotExists());
    }

    /**
     * This method returns true if there is at least one empty depot (not including leader ability ones)
     * inside the Warehouse.
     * @return true if there is at least one empty depot.
     */
    public boolean emptyDepotExists() {
        for(WarehouseDepot depot : this.getWarehouseDepots())
            if(depot.getResourceType() == ResourceType.NONE)
                return true;

        return false;
    }

    /**
     * This method returns true if one or more empty depots inside the warehouse can be filled with the
     * specified type of resource.
     * @param resourceType is the type of resources that have to be checked.
     * @return true if the resource can fit.
     */
    public boolean areEmptyDepotsFillableByType(ResourceType resourceType) {
        for(WarehouseDepot depot : this.getWarehouseDepots())
            if(depot.getResourceType() == resourceType)
                return false;

        return true;
    }

    /**
     * Method used to check if a player can add a specified type of resource to the Warehouse.
     * @param type Type of resource the player wants to add.
     * @return True id the resource can be added.
     */
    public boolean canAddResource(ResourceType type) {
        boolean canAdd = false;

        if(emptyDepotExists() && areEmptyDepotsFillableByType(type))
            canAdd = true;

        for(int i = 0; i <= 2; i++)
            if(this.warehouseDepots[i].getResourceType() == type && !this.warehouseDepots[i].isFull())
                canAdd = true;

        if(this.extraWarehouseDepot1IsActive && this.extraWarehouseDepot1.getResourceType() == type && !this.extraWarehouseDepot1.isFull())
            canAdd = true;

        if(this.extraWarehouseDepot2IsActive && this.extraWarehouseDepot2.getResourceType() == type && !this.extraWarehouseDepot2.isFull())
            canAdd = true;

        return canAdd;
    }

    /**
     * Method used to create a copy of the player's Warehouse.
     * @return The copied Warehouse.
     */
    public Warehouse copyWarehouse() {
        Warehouse copy = new Warehouse();

        for(int i = 0; i < 3; i++) {
            copy.getWarehouseDepots()[i].setStoredResources(this.getWarehouseDepots()[i].getStoredResources());
            copy.getWarehouseDepots()[i].setResourceType(this.getWarehouseDepots()[i].getResourceType());
        }

        if(this.isExtraWarehouseDepot1IsActive()) {
            copy.activateLeaderDepot(this.getExtraWarehouseDepot1().getResourceType());
            copy.getExtraWarehouseDepot1().setStoredResources(this.getExtraWarehouseDepot1().getStoredResources());
        }
        if(this.isExtraWarehouseDepot2IsActive()) {
            copy.activateLeaderDepot(this.getExtraWarehouseDepot2().getResourceType());
            copy.getExtraWarehouseDepot2().setStoredResources(this.getExtraWarehouseDepot2().getStoredResources());
        }

        return copy;
    }

    /**
     * Method used to revert the Warehouse to a previous condition.
     * @param warehouse Warehouse for the current warehouse to be reverted to.
     */
    public void revertWarehouse(Warehouse warehouse) {
        for(int i = 0; i < 3; i++) {
            this.getWarehouseDepots()[i].setStoredResources(warehouse.getWarehouseDepots()[i].getStoredResources());
            this.getWarehouseDepots()[i].setResourceType(warehouse.getWarehouseDepots()[i].getResourceType());
        }

        this.getExtraWarehouseDepot1().setStoredResources(warehouse.getExtraWarehouseDepot1().getStoredResources());
        this.getExtraWarehouseDepot1().setResourceType(warehouse.getExtraWarehouseDepot1().getResourceType());

        this.getExtraWarehouseDepot2().setStoredResources(warehouse.getExtraWarehouseDepot2().getStoredResources());
        this.getExtraWarehouseDepot2().setResourceType(warehouse.getExtraWarehouseDepot2().getResourceType());

        this.extraWarehouseDepot1IsActive = warehouse.isExtraWarehouseDepot1IsActive();
        this.extraWarehouseDepot2IsActive = warehouse.isExtraWarehouseDepot2IsActive();
    }

}
