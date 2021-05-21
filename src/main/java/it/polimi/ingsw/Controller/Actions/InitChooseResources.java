package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Enums.ResourceType;

import java.util.HashMap;

public class InitChooseResources extends Action{
    private final HashMap<Integer, ResourceType> depotResource;

    public InitChooseResources(HashMap<Integer, ResourceType> depotResource) {
        this.depotResource = depotResource;
    }
}
