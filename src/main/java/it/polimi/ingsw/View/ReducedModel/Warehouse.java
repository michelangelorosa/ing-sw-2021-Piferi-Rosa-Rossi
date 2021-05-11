package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Warehouse Class defines the contents and the behaviour of the warehouse inside the player's board.
 * Each warehouse contains three warehouseDepot-type objects organized in an array, plus two extra
 * warehouseDepot-type objects which are activated if the player has activated the relative Leader Card
 * ability (via the boolean attributes).
 */
public class Warehouse implements Serializable {
    private static final long serialVersionUID = 0x1;

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

    public void setExtraWarehouseDepot1IsActive(boolean extraWarehouseDepot1IsActive) {
        this.extraWarehouseDepot1IsActive = extraWarehouseDepot1IsActive;
    }

    public void setExtraWarehouseDepot2IsActive(boolean extraWarehouseDepot2IsActive) {
        this.extraWarehouseDepot2IsActive = extraWarehouseDepot2IsActive;
    }

    /**
     * Method used to check if it is possible to remove resources from a depot.
     * @param depot Depot the player wants to remove resources from.
     * @return True if the depot is not empty.
     */
    public boolean canRemoveFromDepot(it.polimi.ingsw.Model.WarehouseDepot depot){
        return !depot.isEmpty();
    }

    /**
     * Method used to remove a resource from a specified depot.
     * @param depot Depot the player wants to remove resources from.
     */
    public void removeResourceFromDepot(it.polimi.ingsw.Model.WarehouseDepot depot) {
        if(!depot.isEmpty())
            depot.removeResources(1);
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

    public ArrayList<String> toCli() {
        ArrayList<String> warehouse = new ArrayList<>();

        warehouse.add(ANSIColors.BACK_GREY+ "╔═════════════════════════════╗"+ ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║ ░░░░░░░░ " + ANSIColors.BOLD + "WAREHOUSE"+ ANSIColors.RESET +ANSIColors.BACK_GREY+" ░░░░░░░░ ║"+ ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "╠═════════════════════════════╣"+ ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"      ╔═══════════════╗      "+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"      ║    "+ warehouseDepots[2].toCli().get(0) +"   ║      "+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"   ╔══╩═══════════════╩══╗   "+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"   ║   "+ warehouseDepots[1].toCli().get(0) +"  ║   "+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╔══╩═════════════════════╩══╗"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║  "+ warehouseDepots[0].toCli().get(0) +" ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
        warehouse.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╚═══════════════════════════╝"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);


        ArrayList<String> extraDepots = new ArrayList<>();
        if(isExtraWarehouseDepot1IsActive() && isExtraWarehouseDepot2IsActive()) {
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╔═══════════╗   ╔═══════════╗"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║  "+ extraWarehouseDepot1.toCli().get(0) + "  ║   ║  " + extraWarehouseDepot2.toCli().get(0) + "  ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║           ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║  "+ extraWarehouseDepot1.toCli().get(1) + "  ║   ║  " + extraWarehouseDepot2.toCli().get(1) + "  ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╠═══════════╣   ╠═══════════╣"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║ "+ extraWarehouseDepot1.getResourceType().toCliNoColor() + "  ║   ║ " + extraWarehouseDepot2.getResourceType().toCliNoColor() + "  ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╚═══════════╝   ╚═══════════╝"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+"╚═════════════════════════════╝"+ANSIColors.RESET);
        }
        else if(isExtraWarehouseDepot1IsActive()) {
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╔═══════════╗   ╔═══════════╗"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║  "+ extraWarehouseDepot1.toCli().get(0) + "  ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║           ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║  "+ extraWarehouseDepot1.toCli().get(1) + "  ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╠═══════════╣   ╠═══════════╣"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║ "+ extraWarehouseDepot1.getResourceType().toCliNoColor() + "  ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╚═══════════╝   ╚═══════════╝"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+"╚═════════════════════════════╝"+ANSIColors.RESET);
        }
        else {
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╔═══════════╗   ╔═══════════╗"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║           ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║           ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║           ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╠═══════════╣   ╠═══════════╣"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"║           ║   ║           ║"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+ "║"+ ANSIColors.RESET +"╚═══════════╝   ╚═══════════╝"+ANSIColors.BACK_GREY+"║"+ANSIColors.RESET);
            extraDepots.add(ANSIColors.BACK_GREY+"╚═════════════════════════════╝"+ANSIColors.RESET);
        }

        warehouse.addAll(extraDepots);

        return warehouse;
    }

}
