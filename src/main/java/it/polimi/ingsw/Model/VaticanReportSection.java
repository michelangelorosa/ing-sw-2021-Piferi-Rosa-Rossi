package it.polimi.ingsw.Model;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */

public enum VaticanReportSection {
    No, ONE, TWO, THREE;

    /**
     * This particular method uses an array of Color instances containing each different color
     * to return a particular color based on the integer given when calling the method.
     */

    private static final VaticanReportSection[] reportSections = VaticanReportSection.values();
    public static VaticanReportSection getReportSection(int i) { return VaticanReportSection.reportSections[i]; }
}