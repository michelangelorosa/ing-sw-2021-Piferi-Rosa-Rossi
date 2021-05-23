package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.ReducedModel.RedWarehouseDepot;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Warehouse Class defines the contents and the behaviour of the warehouse inside the player's board.
 * Each warehouse contains three warehouseDepot-type objects organized in an array, plus two extra
 * warehouseDepot-type objects which are activated if the player has activated the relative Leader Card
 * ability (via the boolean attributes).
 */
public class Warehouse extends RedWarehouse {
    private static final long serialVersionUID = 0x1;

    /**
     * Constructor for Warehouse Class. It creates the "pyramid" of fixed-length depots and the two extra
     * depots. In addiction to that, it initializes the boolean attributes to "false".
     */
    public Warehouse() {
        super();
        this.extraWarehouseDepot1IsActive = false;
        this.extraWarehouseDepot2IsActive = false;
    }

    public WarehouseDepot[] getWarehouseDepots() {
        return (WarehouseDepot[])this.warehouseDepots;
    }

    /**
     * Getter for "extraWarehouseDepot1" attribute in Warehouse Class.
     */
    public WarehouseDepot getExtraWarehouseDepot1() {
        return (WarehouseDepot) extraWarehouseDepot1;
    }

    /**
     * Getter for "extraWarehouseDepot2" attribute in Warehouse Class.
     */
    public WarehouseDepot getExtraWarehouseDepot2() {
        return (WarehouseDepot) extraWarehouseDepot2;
    }


    /**
     * This method resets the Warehouse to its initial state.
     */
    public void reset() {
        ((WarehouseDepot)this.warehouseDepots[0]).setStoredResources(0);
        ((WarehouseDepot)this.warehouseDepots[1]).setStoredResources(0);
        ((WarehouseDepot)this.warehouseDepots[2]).setStoredResources(0);
        ((WarehouseDepot)this.extraWarehouseDepot1).setStoredResources(0);
        ((WarehouseDepot)this.extraWarehouseDepot2).setStoredResources(0);
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
            ((WarehouseDepot)this.extraWarehouseDepot1).setResourceType(resourceType);
        }

        else if(!this.extraWarehouseDepot2IsActive) {
            this.extraWarehouseDepot2IsActive = true;
            ((WarehouseDepot)this.extraWarehouseDepot2).setResourceType(resourceType);
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
        ArrayList<RedWarehouseDepot> depots = new ArrayList<>();
        depots.add(warehouseDepots[0]);
        depots.add(warehouseDepots[1]);
        depots.add(warehouseDepots[2]);

        if(extraWarehouseDepot1IsActive)
            depots.add(extraWarehouseDepot1);
        if(extraWarehouseDepot2IsActive)
            depots.add(extraWarehouseDepot2);

        for(RedWarehouseDepot depot : depots)
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
        return depot.getResourceType() == type && !depot.isFull();
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

        for(WarehouseDepot depot : ((WarehouseDepot[])warehouseDepots))
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
                ((WarehouseDepot)extraWarehouseDepot1).removeResources(resourcesToRemove);
                return;
            } else {
                ((WarehouseDepot)extraWarehouseDepot1).removeResources(resourceCount);
                resourcesToRemove -= resourceCount;
            }
        }


        if(extraWarehouseDepot2.getResourceType() == resourceType) {
            resourceCount = extraWarehouseDepot2.getStoredResources();
            if(resourceCount >= resourcesToRemove)
                ((WarehouseDepot)extraWarehouseDepot2).removeResources(resourcesToRemove);
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
            else return secondDepot.getResourceType() == ResourceType.NONE && areEmptyDepotsFillableByType(firstDepot.getResourceType());

        if(secondDepot.isFromLeaderCardAbility())
            if(firstDepot.getResourceType() == secondDepot.getResourceType())
                return true;
            else return firstDepot.getResourceType() == ResourceType.NONE && areEmptyDepotsFillableByType(secondDepot.getResourceType());

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
     * Method used to create a copy of the player's Warehouse.
     * @return The copied Warehouse.
     */
    public Warehouse copyWarehouse() {
        Warehouse copy = new Warehouse();

        for(int i = 0; i < 3; i++) {
            ((WarehouseDepot)copy.getWarehouseDepots()[i]).setStoredResources(this.getWarehouseDepots()[i].getStoredResources());
            ((WarehouseDepot)copy.getWarehouseDepots()[i]).setResourceType(this.getWarehouseDepots()[i].getResourceType());
        }

        if(this.isExtraWarehouseDepot1IsActive()) {
            copy.activateLeaderDepot(this.getExtraWarehouseDepot1().getResourceType());
            ((WarehouseDepot)copy.getExtraWarehouseDepot1()).setStoredResources(this.getExtraWarehouseDepot1().getStoredResources());
        }
        if(this.isExtraWarehouseDepot2IsActive()) {
            copy.activateLeaderDepot(this.getExtraWarehouseDepot2().getResourceType());
            ((WarehouseDepot)copy.getExtraWarehouseDepot2()).setStoredResources(this.getExtraWarehouseDepot2().getStoredResources());
        }

        return copy;
    }

    /**
     * Method used to revert the Warehouse to a previous condition.
     * @param warehouse Warehouse for the current warehouse to be reverted to.
     */
    public void revertWarehouse(Warehouse warehouse) {
        for(int i = 0; i < 3; i++) {
            ((WarehouseDepot)this.getWarehouseDepots()[i]).setStoredResources(warehouse.getWarehouseDepots()[i].getStoredResources());
            ((WarehouseDepot)this.getWarehouseDepots()[i]).setResourceType(warehouse.getWarehouseDepots()[i].getResourceType());
        }

        ((WarehouseDepot)this.getExtraWarehouseDepot1()).setStoredResources(warehouse.getExtraWarehouseDepot1().getStoredResources());
        ((WarehouseDepot)this.getExtraWarehouseDepot1()).setResourceType(warehouse.getExtraWarehouseDepot1().getResourceType());

        ((WarehouseDepot)this.getExtraWarehouseDepot2()).setStoredResources(warehouse.getExtraWarehouseDepot2().getStoredResources());
        ((WarehouseDepot)this.getExtraWarehouseDepot2()).setResourceType(warehouse.getExtraWarehouseDepot2().getResourceType());

        this.extraWarehouseDepot1IsActive = warehouse.isExtraWarehouseDepot1IsActive();
        this.extraWarehouseDepot2IsActive = warehouse.isExtraWarehouseDepot2IsActive();
    }

    /**Method for converting model classes to view classes*/
    public RedWarehouse toView() {
        return (RedWarehouse)this;
    }

}
