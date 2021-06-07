package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.View.Utility.ANSIfont;

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
    private static final long serialVersionUID = 0x6;

    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    private PlayerStatus status;
    private int victoryPoints;
    private int faithTrackPosition;

    private RedBasicProduction basicProduction;
    private RedWarehouse warehouse;
    private RedStrongbox strongbox;
    private RedDevelopmentCardSlots slots;
    private RedResourceStack temporaryResources;
    private RedLeaderCard[] leaderCards;
    private RedPopeTileClass[] popeTiles;

    private ArrayList<ActionType> possibleActions;


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
        this.basicProduction = new BasicProduction(2, 1);
        this.warehouse = new Warehouse();
        this.strongbox = new Strongbox();
        this.temporaryResources = new ResourceStack(0,0,0,0);
        this.leaderCards = new LeaderCard[2];
        this.popeTiles = new PopeTileClass[3];
        this.popeTiles[0] = new PopeTileClass(2);
        this.popeTiles[1] = new PopeTileClass(3);
        this.popeTiles[2] = new PopeTileClass(4);
        this.possibleActions = new ArrayList<>();
    }

    public void setPopeTiles(RedPopeTileClass[] popeTiles) {
        this.popeTiles = popeTiles;
    }

    public RedPopeTileClass[] getPopeTiles() {
        return popeTiles;
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

    public void setStatus(PlayerStatus status) {
        this.status = status;
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

    public RedBasicProduction getBasicProduction() {
        return basicProduction;
    }

    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    public RedStrongbox getStrongbox() {
        return strongbox;
    }

    public RedDevelopmentCardSlots getSlots() {
        return slots;
    }

    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public RedLeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    public ArrayList<ActionType> getPossibleActions() {
        return possibleActions;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void setFaithTrackPosition(int faithTrackPosition) {
        this.faithTrackPosition = faithTrackPosition;
    }

    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setStrongbox(RedStrongbox strongbox) {
        this.strongbox = strongbox;
    }

    public void setSlots(RedDevelopmentCardSlots slots) {
        this.slots = slots;
    }

    public void setLeaderCards(RedLeaderCard[] leaderCards) {
        this.leaderCards = leaderCards;
    }

    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    public void printLeader(){
        System.out.println(this.getLeaderCards()[0].getResourcesRequired().getResource(ResourceType.SHIELDS));
        System.out.println(this.getLeaderCards()[1].getResourcesRequired().getResource(ResourceType.SHIELDS));
    }

    public void setBasicProduction(RedBasicProduction basicProduction) {
        this.basicProduction = basicProduction;
    }

    public void setPossibleActions(ArrayList<ActionType> possibleActions) {
        this.possibleActions = possibleActions;
    }

    public ArrayList<String> toCli() {
        int i;
        ArrayList<String> player = this.warehouse.toCli();
        player.addAll(this.strongbox.toCli3());
        ArrayList<String> basic = this.basicProduction.toCli();
        ArrayList<String> slots = this.slots.toCli();
        ArrayList<String> popeTiles = popeTileToCli();

        for(i = 0; i < 5; i++)
            if(i == 2) player.add(i, "    "+ ANSIfont.BOLD +ANSIfont.ITALIC +"Masters of Renaissance"+ANSIfont.RESET+"     ");
            else player.add(i, "                               ");
        int j;
        for(i = 1; i < 9; i++)
            player.set(i, player.get(i) +" "+ basic.get(i-1));

        for(i = 1; i < 9; i++) {
            if (i == 1) player.set(i, player.get(i));
            if(i > 4) player.set(i, player.get(i) + "                  " + popeTiles.get(i - 1));
            else player.set(i, player.get(i) + " " + popeTiles.get(i - 1));
        }

        for(i = 9; i > 6; i--)
            player.set(i, player.get(i));



        player.set(0, "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        for(i = 1; i < 5; i++)
            player.set(i, " "+player.get(i) + "  ");
        for(i = 5; i < 9; i++)
            player.set(i, " "+player.get(i) + "  ");

        for(i = 0; i < slots.size(); i++) {
            j = player.size() - slots.size() + i;
            player.set(j, " "+player.get(j)+" "+slots.get(i));
        }
        player.add("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        return player;
    }

    public ArrayList<String> toCliAdd() {
        ArrayList<String> warehouse = this.warehouse.toCli();
        ArrayList<String> resources = this.temporaryResources.toCliAdd();

        for(int i = 5; i < 12; i++)
            warehouse.set(i, warehouse.get(i) + "      " + resources.get(i - 5));

        return warehouse;
    }

    public ArrayList<String> toCliPay() {
        ArrayList<String> warehouse = this.warehouse.toCli();
        ArrayList<String> resources = this.temporaryResources.toCliPay();

        for(int i = 5; i < 12; i++)
            warehouse.set(i, warehouse.get(i) + "      " + resources.get(i - 5));

        return warehouse;
    }

    public ArrayList<String> leaderPrint(boolean up){
        ArrayList<String> leaderCards = new ArrayList<>();
        if(!this.leaderCards[1].isDiscarded() && !this.leaderCards[0].isDiscarded()) {
            if (up) {
                leaderCards.addAll(this.leaderCards[1].toCliUp());
                leaderCards.add("                            ");
                leaderCards.addAll(this.leaderCards[0].toCliUp());
            } else {
                if (this.getLeaderCards()[1].isActive()) leaderCards.addAll(this.leaderCards[1].toCliUp());
                else leaderCards.addAll(this.leaderCards[1].toCliDown());
                leaderCards.add("                            ");
                if (this.getLeaderCards()[0].isActive()) leaderCards.addAll(this.leaderCards[0].toCliUp());
                else leaderCards.addAll(this.leaderCards[0].toCliDown());
            }
        }
        else if(this.leaderCards[1].isDiscarded()){
            if(this.leaderCards[0].isDiscarded()){
                leaderCards.addAll(this.leaderCards[1].toCliNo());
                leaderCards.add("                            ");
                leaderCards.addAll(this.leaderCards[0].toCliNo());
            }
            else{
                leaderCards.addAll(this.leaderCards[1].toCliNo());
                leaderCards.add("                            ");
                if(up) leaderCards.addAll(this.leaderCards[0].toCliUp());
                else leaderCards.addAll(this.leaderCards[0].toCliDown());

            }
        }
        else if(this.leaderCards[0].isDiscarded()){
            if(this.leaderCards[1].isDiscarded()){
                leaderCards.addAll(this.leaderCards[1].toCliNo());
                leaderCards.add("                            ");
                leaderCards.addAll(this.leaderCards[0].toCliNo());
            }
            else{
                if(up) leaderCards.addAll(this.leaderCards[1].toCliUp());
                else leaderCards.addAll(this.leaderCards[1].toCliDown());
                leaderCards.add("                            ");
                leaderCards.addAll(this.leaderCards[0].toCliNo());

            }
        }

        return leaderCards;
    }

    public ArrayList<String> popeTileToCli(){
        ArrayList<String> pope1 = popeTiles[0].toCli();
        ArrayList<String> pope2 = popeTiles[1].toCli();
        ArrayList<String> pope3 = popeTiles[2].toCli();

        for(int i = 0; i < 7; i++){
            pope1.set(i, " " + pope1.get(i) +"  " + pope2.get(i) + "  " + pope3.get(i));
        }
        pope1.add(0, "░░░░░░░░░░░░░░░░░░░░░ POPE FAVOR TILES ░░░░░░░░░░░░░░░░░░░░░");

        return pope1;
    }
}
