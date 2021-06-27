package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  WarehouseDepot Class defines the single warehouse's depot. A WarehouseDepot-type Object contains:
 *  a private final int attribute indicating the max number of storable resources,
 *  a private int attribute indicating the current number of resources stored,
 *  a ResourceType-type private attribute indicating which type of resource is stored in the depot.
 */
public class RedWarehouseDepot implements Serializable {
    protected final int maxResources;
    protected int storedResources;
    protected ResourceType resourceType;
    protected boolean isFromLeaderCardAbility;

    public RedWarehouseDepot(int maxResources) {
        this.maxResources = maxResources;
    }

    /**
     * Getter for "maxResources" attribute in WarehouseDepot Class.
     */
    public int getMaxResources() {
        return maxResources;
    }

    /**
     * Getter for "storedResources" attribute in WarehouseDepot Class.
     */
    public int getStoredResources() {
        return storedResources;
    }

    /**
     * Getter for "isFromLeaderCardAbility" attribute in WarehouseDepot Class.
     */
    public boolean isFromLeaderCardAbility() {
        return isFromLeaderCardAbility;
    }

    /**
     * Getter for "resourceType" attribute in WarehouseDepot Class.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * This method returns a "true" value if the WarehouseDepot-type object is empty.
     */
    public boolean isEmpty() {
        return storedResources == 0;
    }

    /**
     * This method returns a "true" value if the WarehouseDepot-type object is full.
     */
    public boolean isFull() {
        return this.storedResources == this.maxResources;
    }

    public ArrayList<String> toCli() {
        ArrayList<String> arrayDepot = new ArrayList<>();
        int i;
        String depot = "";

        if(!isFromLeaderCardAbility) {
            for (i = 0; i < this.storedResources; i++)
                depot += this.resourceType.toCli() + " ";
            for (i = this.storedResources; i < this.maxResources; i++)
                depot += ResourceType.NONE.toCli() + " ";
            arrayDepot.add(depot);
        }

        else {
            for (i = 0; i < this.storedResources; i++)
                arrayDepot.add(this.resourceType.toCli());
            for (i = this.storedResources; i < this.maxResources; i++)
                arrayDepot.add(ResourceType.NONE.toCli());
        }

        return arrayDepot;
    }

    public ArrayList<String> toCliSymbol() {
        ArrayList<String> arrayDepot = new ArrayList<>();
        int i;
        String depot = "";

        if(!isFromLeaderCardAbility) {
            for (i = 0; i < this.storedResources; i++)
                depot += this.resourceType.toCliSymbol() + " ";
            for (i = this.storedResources; i < this.maxResources; i++)
                depot += ResourceType.NONE.toCliSymbol() + " ";
            arrayDepot.add(depot);
        }

        else {
            for (i = 0; i < this.storedResources; i++)
                arrayDepot.add(this.resourceType.toCliSymbol());
            for (i = this.storedResources; i < this.maxResources; i++)
                arrayDepot.add(ResourceType.NONE.toCliSymbol());
        }

        return arrayDepot;
    }
}
