package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ReducedModel.RedWarehouseDepot;

/**
  *  WarehouseDepot Class defines the single warehouse's depot. A WarehouseDepot-type Object contains:
  *  a private final int attribute indicating the max number of storable resources,
  *  a private int attribute indicating the current number of resources stored,
  *  a ResourceType-type private attribute indicating which type of resource is stored in the depot.
  */
public class WarehouseDepot extends RedWarehouseDepot {

     /**
      * Constructor for WarehouseDepot Class.
      */
    public WarehouseDepot(int maxResources, boolean isFromLeaderCardAbility) {
        super(maxResources);
        this.storedResources = 0;
        this.resourceType = ResourceType.NONE;
        this.isFromLeaderCardAbility = isFromLeaderCardAbility;
    }

     /**
      * Setter for "storedResources" attribute.
      */
    public void setStoredResources(int storedResources) {
        if(storedResources == 0 && !this.isFromLeaderCardAbility)
            this.resourceType = ResourceType.NONE;
        this.storedResources = storedResources;
    }

     /**
      * Setter for "resourceType" attribute.
      */
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

     /**
      * Adds a given number of resources to the WarehouseDepot-type object and returns the number of
      * resources that could eventually not fit inside of it.
      */
    public int addResources(int resourcesToAdd) {
        int extraResources = 0;

        if ((resourcesToAdd + storedResources) > maxResources) {
            extraResources = (resourcesToAdd + storedResources) - maxResources;
            storedResources = maxResources;
        } else {
            storedResources += resourcesToAdd;
        }

        return extraResources;
    }

     /**
      * Removes a given number of resources from the WarehouseDepot-type object. If the object
      * does not have any resource left, its "resourceType" attribute is set to "NONE".
      */
    public void removeResources(int resourcesToRemove) {
        this.storedResources -= resourcesToRemove;

        if(storedResources == 0 && !isFromLeaderCardAbility)
            this.resourceType = ResourceType.NONE;
    }
}