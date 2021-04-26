package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player Class defines attributes and methods of a player.
 * It consists in six attributes being respectively:
 * <ul>
 *     <li>nickname: the player's nickname</li>
 *     <li>turnPosition: the player's position in the defined turn changing mechanism</li>
 *     <li>Inkwell: if the player is the first to play, he has the inkwell</li>
 *     <li>status: the player's status indicating if he's playing, temporary disabled or has finished the game</li>
 *     <li>victoryPoints: the player's number of victory points</li>
 *     <li>board: the player's personal board containing all objects of the game</li>
 * </ul>
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    private PlayerStatus status;
    private int victoryPoints;
    private int faithTrackPosition;

    private BasicProduction basicProduction;
    private Warehouse warehouse;
    private Strongbox strongbox;
    private DevelopmentCardSlots slots;
    private ResourceStack temporaryResources;

    /**
     * Constructor for Player Class.
     */
    public Player(String nickname, int turnPosition, boolean hasInkwell) {
        this.nickname = nickname;
        this.turnPosition = turnPosition;
        this.Inkwell = hasInkwell;
        this.status = PlayerStatus.IN_GAME;
        this.victoryPoints = 0;
        this.faithTrackPosition = 0;
        this.warehouse = new Warehouse();
        this.strongbox = new Strongbox();
        this.temporaryResources = new ResourceStack(0,0,0,0);
    }

    /**
     * Getter for "nickname" attribute in Player Class.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for "turnPosition" attribute in Player Class.
     */
    public int getTurnPosition() {
        return turnPosition;
    }

    /**
     * Getter for "Inkwell" attribute in Player Class.
     */
    public boolean hasInkwell() {
        return Inkwell;
    }

    /**
     * Getter for "status" attribute in Player Class.
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * Getter for "victoryPoints" attribute in Player Class.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }
    /**
     * Getter for "faithTrackPosition" attribute in Player Class.
     */
    public int getFaithTrackPosition() {
        return faithTrackPosition;
    }

    public BasicProduction getBasicProduction() {
        return basicProduction;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public DevelopmentCardSlots getSlots() {
        return slots;
    }

    public ResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void setFaithTrackPosition(int faithTrackPosition) {
        this.faithTrackPosition = faithTrackPosition;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(Strongbox strongbox) {
        this.strongbox = strongbox;
    }

    public void setSlots(DevelopmentCardSlots slots) {
        this.slots = slots;
    }

    public void setTemporaryResources(ResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    public void setBasicProduction(BasicProduction basicProduction) {
        this.basicProduction = basicProduction;
    }

    public ArrayList<String> toCli() {
        int i;
        ArrayList<String> player = this.warehouse.toCli();
        player.addAll(this.strongbox.toCli3());
        ArrayList<String> basic = this.basicProduction.toCli();
        ArrayList<String> slots = this.slots.toCli();

        for(i = 0; i < 5; i++)
            player.add(0, "                               ");
        int j;
        for(i = 1; i < 9; i++)
            player.set(i, player.get(i) +" "+ basic.get(i-1));


        player.set(0, "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        for(i = 1; i < 5; i++)
            player.set(i, " "+player.get(i) + "                                                              ");
        for(i = 5; i < 9; i++)
            player.set(i, " "+player.get(i) + "                                                                               ");

        for(i = 0; i < slots.size(); i++) {
            j = player.size() - slots.size() + i;
            player.set(j, " "+player.get(j)+" "+slots.get(i));
        }
        player.add("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        return player;
    }
}