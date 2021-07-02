package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.GameModel.FaithCell;
import it.polimi.ingsw.Model.JSON.JSONReader;
import it.polimi.ingsw.Model.GameModel.VaticanReportSection;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RedFaithTrack Class contains data and methods for the faithTrack.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedFaithCell[] "cells": cells of the faith track</li>
 *     <li>RedVaticanReportSection "ONE": first vatican report section</li>
 *     <li>RedVaticanReportSection "TWO": second vatican report section</li>
 *     <li>RedVaticanReportSection "THREE": third vatican report section</li>
 *     <li>boolean "popeSpaceONE": first pope space indicator</li>
 *     <li>boolean "popeSpaceTWO": second pope space indicator</li>
 *     <li>boolean "popeSpaceTHREE": third pope space indicator</li>
 * </ul>
 */
public class RedFaithTrack implements Serializable {
    protected final RedFaithCell[] cells;
    protected final RedVaticanReportSection ONE;
    protected final RedVaticanReportSection TWO;
    protected final RedVaticanReportSection THREE;

    protected boolean popeSpaceONE;
    protected boolean popeSpaceTWO;
    protected boolean popeSpaceTHREE;

    /**
     * Constructor for a Reduced Faith Track
     * @param ONE       First Vatican Report
     * @param TWO       Second Vatican Report
     * @param THREE     Third Vatican Report
     */
    protected RedFaithTrack(VaticanReportSection ONE, VaticanReportSection TWO, VaticanReportSection THREE) {
        this.cells = new FaithCell[25];
        this.ONE = ONE;
        this.TWO = TWO;
        this.THREE = THREE;
    }

    /**
     * Constructor for ReducedFaithTrack from an ArrayList of RedVaticanReportSection
     * @param sections
     */
    protected RedFaithTrack(ArrayList<RedVaticanReportSection> sections) {
        this.cells = new FaithCell[25];
        this.ONE = sections.get(0);
        this.TWO = sections.get(1);
        this.THREE = sections.get(2);
    }

    /**
     * Constructor for ReducedFaithTrack from JSONReader
     */
    protected RedFaithTrack() {
        this.cells = JSONReader.ReadFaithCells();
        VaticanReportSection[] vaticanReportSection = JSONReader.ReadVaticanReportSection();
        this.ONE = vaticanReportSection[0];
        this.TWO = vaticanReportSection[1];
        this.THREE = vaticanReportSection[2];
    }

    /**
     * Getter for the First RedVaticanReportSection
     */
    public RedVaticanReportSection getONE() {
        return ONE;
    }

    /**
     * Getter for the Second RedVaticanReportSection
     */
    public RedVaticanReportSection getTWO() {
        return TWO;
    }

    /**
     * Getter for the Third RedVaticanReportSection
     */
    public RedVaticanReportSection getTHREE() {
        return THREE;
    }

    /**Getter for FaithCell*/
    public RedFaithCell[] getCells() {
        return cells;
    }

    /**
     * True if the first pope space is active
     */
    public boolean isPopeSpaceONE() {
        return popeSpaceONE;
    }

    /**
     * True if the second pope space is active
     */
    public boolean isPopeSpaceTWO() {
        return popeSpaceTWO;
    }

    /**
     * True if the third pope space is active
     */
    public boolean isPopeSpaceTHREE() {
        return popeSpaceTHREE;
    }

    /**Points relative to the player's position in the FaithTrack*/
    public int getTrackPoints(Player player){
        return player.getVictoryPoints();
    }

    /**
     * ReducedFaithTrack to cli mode
     * @return      ArrayList with the FaithTrack information
     */
    public ArrayList<String> toCli(Game game) {
        ArrayList<String> track = this.cells[0].toCli(game);
        ArrayList<String> sections = this.sectionsToCli();
        ArrayList<String> cell;

        for(int i = 1; i < 25; i++) {
            cell = this.cells[i].toCli(game);
            for (int j = 0; j < 5; j++) {
                track.set(j, track.get(j) + cell.get(j));
            }
        }

        track.set(4, track.get(4) + ANSIColors.RESET);

        track.addAll(0, sections);

        for(int i = 0; i < track.size(); i++) {
            track.set(i, "   " + track.get(i) + "    ");
        }
        return track;
    }

    /**
     * Faith Track section to cli
     * @return  ArrayList with the section
     */
    public ArrayList<String> sectionsToCli() {
        ArrayList<String> sections = new ArrayList<>();
        sections.add("");
        sections.add("");
        sections.add("");

        RedVaticanReportSection section = this.ONE;

        ArrayList<RedVaticanReportSection> vatican = new ArrayList<>();
        vatican.add(this.ONE);
        vatican.add(this.TWO);
        vatican.add(this.THREE);

        String c;
        String r = ANSIColors.RESET;

        int i, end;
        for(int j = 0; j < 3; j++) {
            if((j == 0 && !this.popeSpaceONE) || (j == 1 && !this.popeSpaceTWO) || (j == 2 && !this.popeSpaceTHREE))
                c = ANSIColors.FRONT_BRIGHT_RED;
            else
                c = "";

            end = section.getEnd();
            section = vatican.get(j);
            if(j == 0)
                for(i = 0; i < section.getBegin(); i++) {
                    sections.set(0, sections.get(0) + "      ");
                    sections.set(1, sections.get(1) + "      ");
                    sections.set(2, sections.get(2) + "      ");
                }
            else
                for(i = end; i < section.getBegin()-1; i++) {
                    sections.set(0, sections.get(0) + "      ");
                    sections.set(1, sections.get(1) + "      ");
                    sections.set(2, sections.get(2) + "      ");
                }
            for (i = section.getBegin(); i <= section.getEnd(); i++)
                if(i == section.getBegin() && i == section.getEnd()) {
                    if((j == 0 && !this.popeSpaceONE) || (j == 1 && !this.popeSpaceTWO) || (j == 2 && !this.popeSpaceTHREE)) {
                        sections.set(0, sections.get(0) + c +" ╔══╗ " + r);
                        sections.set(1, sections.get(1) + c +" ║XX║ " + r);
                        sections.set(2, sections.get(2) + c +"╔╩══╩╗" + r);
                    }
                    else {
                        sections.set(0, sections.get(0) + c +" ╔══╗ " + r);
                        sections.set(1, sections.get(1) + c +" ║" + section.pointsToCli() + "║ " + r);
                        sections.set(2, sections.get(2) + c +"╔╩══╩╗" + r);
                    }
                }
                else if (i == section.getBegin()) {
                    if((j == 0 && !this.popeSpaceONE) || (j == 1 && !this.popeSpaceTWO) || (j == 2 && !this.popeSpaceTHREE)) {
                        sections.set(0, sections.get(0) + c +" ╔══╗ " + r);
                        sections.set(1, sections.get(1) + c +" ║XX║ " + r);
                        sections.set(2, sections.get(2) + c +"╔╩══╩═" + r);
                    }
                    else {
                        sections.set(0, sections.get(0) + c +" ╔══╗ " + r);
                        sections.set(1, sections.get(1) + c +" ║" + section.pointsToCli() + "║ " + r);
                        sections.set(2, sections.get(2) + c +"╔╩══╩═" + r);
                    }
                }
                else if (i == section.getEnd()) {
                    sections.set(0, sections.get(0) + "      ");
                    sections.set(1, sections.get(1) + "      ");
                    sections.set(2, sections.get(2) + c +"═════╗" + r);
                }
                else {
                    sections.set(0, sections.get(0) + "      ");
                    sections.set(1, sections.get(1) + "      ");
                    sections.set(2, sections.get(2) + c +"══════" + r);
                }
        }
        for(i = sections.get(0).length(); i < 150; i++) {
            sections.set(0, sections.get(0) + " ");
            sections.set(1, sections.get(1) + " ");
            sections.set(2, sections.get(2) + " ");
        }

        return sections;
    }

}





