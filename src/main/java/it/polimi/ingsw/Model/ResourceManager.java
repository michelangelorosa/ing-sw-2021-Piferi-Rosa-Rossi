package it.polimi.ingsw.Model;

/**
 * ResourceManager Class contains both a Warehouse and a Strongbox type objects (as final
 * attributes). It is used to group all methods regarding resource management.
 */
public class ResourceManager {
    private final Warehouse warehouse;
    private final Strongbox strongbox;

    /**
     * Constructor for ResourceManagerClass.
     */
    public ResourceManager() {
        warehouse = new Warehouse();
        strongbox = new Strongbox();
    }

    /**
     * Getter for "warehouse" attribute in ResourceManagerClass.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Getter for "strongbox" attribute in ResourceManagerClass.
     */
    public Strongbox getStrongbox() {
        return strongbox;
    }

    public void reset() {
        this.warehouse.reset();
        this.strongbox.reset();
    }

    /**
     * This method is used to count the total number of resources stored in the warehouse and the strongbox,
     * divided by their type.
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
     * This method is used to count the total number of resources of a specified type stored
     * in the warehouse and the strongbox.
     * @param resourceType is the type of resource to count.
     * @return the number of resources of the specified type
     */
    public int countAllResourcesByType(ResourceType resourceType) {
        return this.warehouse.countResourcesByType(resourceType) + this.strongbox.countResourcesByType(resourceType);
    }

    /**
     * This method is used to count the number of victory points given to the player at the end of the
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
     * This method is used to add resources to the strongbox when a production process has been completed.
     * @param resourceStack is the stack containing the production's output, divided by type.
     */
    public void addProductionResources(ResourceStack resourceStack) {
        this.strongbox.addToAllTypes(resourceStack);
    }

    /**
     * This method is used to add resources of a specified type coming from the market to a depot
     * inside the Warehouse.
     * <p>
     * The method checks if it is possible to fit the specified type of resources inside the depot,
     * then either proceeds to compute the action, or notifies the player in case the resources
     * cannot be stored inside the depot.
     * @param resourcesToAdd is the number of resources to add.
     * @param type is the type of resources that have to be added
     * @param depot is the depot where the player wants the resources to be added.
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
     * This method is used when the player wants to start a production to remove all resources that have to
     * be payed before actually starting the process.
     * <p>
     * If the number of available resources is not enough to make up for the cost of the production, the
     * player is notified via a message indicating which types of resources are insufficient.
     * @param resourceStack is the set of resources to be extracted from the "Resource System" (Warehouse + Strongbox).
     */
    public void payProductionOrCardPrice(ResourceStack resourceStack) {
        boolean cantPay = false;

        // ---------- CREATES AN ARRAY CONTAINING ALL ResourceTypes (to cycle without counting the NONE type) ---------- //
        ResourceType[] resourceTypes = ResourceType.values();

        // ---------- CYCLES THROUGH ALL TYPES TO CHECK IF THE RESOURCE SYSTEM HAS ENOUGH RESOURCES ---------- //
        for(int i = 1; i <= 4; i++) {
            if (resourceStack.getResource(resourceTypes[i]) > this.countAllResourcesByType(resourceTypes[i])) {
                System.out.println("Number of "+resourceTypes[i]+" insufficient!");
                cantPay = true;
            }
        } if(cantPay) return;

        // ---------- CYCLES THROUGH ALL TYPES TO REMOVE THE RESOURCES ---------- //
        int numberOfResources;

        for(int i = 1; i <= 4; i++) {
            numberOfResources = this.warehouse.countResourcesByType(resourceTypes[i]);

            if(numberOfResources >= resourceStack.getResource(resourceTypes[i])) {
                this.warehouse.removeResourcesByType(resourceStack.getResource(resourceTypes[i]), resourceTypes[i]);
                resourceStack.setResource(0, resourceTypes[i]);
            } else {
                this.warehouse.removeResourcesByType(numberOfResources, resourceTypes[i]);
                resourceStack.removeResource(numberOfResources, resourceTypes[i]);
            }
        }

        this.strongbox.removeFromAllTypes(resourceStack);
    }


}
