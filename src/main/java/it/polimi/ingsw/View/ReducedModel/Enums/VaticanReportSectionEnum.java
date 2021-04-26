package it.polimi.ingsw.View.ReducedModel.Enums;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */

public enum VaticanReportSectionEnum {
    No, ONE, TWO, THREE;

    /**
     * This particular method uses an array of Color instances containing each different color
     * to return a particular color based on the integer given when calling the method.
     */

    private static final VaticanReportSectionEnum[] reportSections = VaticanReportSectionEnum.values();
    public static VaticanReportSectionEnum getReportSection(int i) { return VaticanReportSectionEnum.reportSections[i]; }
}