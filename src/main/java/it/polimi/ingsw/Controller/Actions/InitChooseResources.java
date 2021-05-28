package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Enums.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;

public class InitChooseResources extends Action{
    private final HashMap<Integer, ArrayList<ResourceType>> depotResource;

    public InitChooseResources(HashMap<Integer, ArrayList<ResourceType>> depotResource) {
        this.depotResource = depotResource;
    }

    public HashMap<Integer, ArrayList<ResourceType>> getDepotResource() {
        return depotResource;
    }
}
