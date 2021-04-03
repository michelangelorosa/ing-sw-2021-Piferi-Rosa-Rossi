package it.polimi.ingsw.Model;

 /**
  *  WarehouseDepot Class defines the single warehouse's depot. A WarehouseDepot-type Object contains:
  *  a private final int attribute indicating the max number of storable resources,
  *  a private int attribute indicating the current number of resources stored,
  *  a ResourceType-type private attribute indicating which type of resource is stored in the depot.
  */
public class WarehouseDepot {
    private final int maxResources;
    private int storedResources;
    private ResourceType resourceType;
    private final boolean isFromLeaderCardAbility;

     /**
      * Constructor for WarehouseDepot Class.
      */
    public WarehouseDepot(int maxResources, boolean isFromLeaderCardAbility) {
        this.maxResources = maxResources;
        this.storedResources = 0;
        this.resourceType = ResourceType.NONE;
        this.isFromLeaderCardAbility = isFromLeaderCardAbility;
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
      * Setter for "storedResources" attribute in WarehouseDepot Class.
      */
    public void setStoredResources(int storedResources) {
        this.storedResources = storedResources;
    }

     /**
      * Getter for "resourceType" attribute in WarehouseDepot Class.
      */
    public ResourceType getResourceType() {
        return resourceType;
    }

     /**
      * Setter for "resourceType" attribute in WarehouseDepot Class.
      */
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

     /**
      * Getter for "isFromLeaderCardAbility" attribute in WarehouseDepot Class.
      */
    public boolean isFromLeaderCardAbility() {
        return isFromLeaderCardAbility;
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

     /**
      * This method adds a given number of resources to the WarehouseDepot-type object and returns the number of
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
      * This method removes a given number of resources from the WarehouseDepot-type object. If the object
      * does not have any resource left, its "resourceType" attribute is set to "NONE".
      */
    public void removeResources(int resourcesToRemove) {
        this.storedResources -= resourcesToRemove;

        if(storedResources == 0 && !isFromLeaderCardAbility)
            this.resourceType = ResourceType.NONE;

    }



}