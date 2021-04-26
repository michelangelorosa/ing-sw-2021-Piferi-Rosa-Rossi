package it.polimi.ingsw.View.ReducedModel.Enums;

public enum ResourceType {
    NONE, SHIELDS, SERVANTS, COINS, STONES;

    private static final ResourceType[] resourceTypes = ResourceType.values();
    public static ResourceType getResourceType(int i) { return ResourceType.resourceTypes[i]; }
}
