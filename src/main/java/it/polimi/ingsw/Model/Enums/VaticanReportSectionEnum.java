package it.polimi.ingsw.Model.Enums;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */

public enum VaticanReportSectionEnum {
    No, ONE, TWO, THREE;

    /**
     * Creates an array of all enum fields.
     */
    private static final VaticanReportSectionEnum[] reportSections = VaticanReportSectionEnum.values();

    /**
     * Uses an array of VaticanReportSectionEnum instances containing each different Vatican Report Section type
     * to return a particular vatican report section based on the integer given when calling the method.
     */
    public static VaticanReportSectionEnum getReportSection(int i) { return VaticanReportSectionEnum.reportSections[i]; }
}