package it.polimi.ingsw.Model.JSON;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.GameStatus;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Enums.PopeTile;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Board;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.View.ReducedModel.Player;

import java.util.ArrayList;

public class PlayerJSON {
    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    PlayerStatus status;
    private final int faithTrackPosition;
    int victoryPoints;
    PopeTile pope0, pope1, pope2;
    int depot0Max, depot1Max, depot2Max, depot0Stored, depot1Stored, depot2Stored, depotLeader1Stored, depotLeader2Stored;
    ResourceType depot0Type, depot1Type, depot2Type;
    int leader0, leader1, strongboxShields, strongboxServants, strongboxCoins, strongboxStones, levelOccupied1, levelOccupied2, levelOccupied3, slot1card1, slot1card2, slot1card3, slot2card1, slot2card2, slot2card3, slot3card1, slot3card2, slot3card3;
    boolean CHOOSE_ACTION, GAME_SET,INIT_CHOOSE_RESOURCES, INIT_CHOOSE_LEADER_CARDS, MARKET_CHOOSE_ROW, CHOOSE_LEADER_CARD, ADD_RESOURCE, SWITCH_DEPOT, RESET_WAREHOUSE, END_MARKET, BUY_CARD, PAY_RESOURCE_CARD, PAY_RESOURCE_PRODUCTION, END_PAY_CARD, CHOOSE_CARD_SLOT, ACTIVATE_PRODUCTION, END_PAY_PRODUCTION, CHOOSE_PRODUCTION_OUTPUT, ACTIVATE_LEADERCARD, DELETE_LEADERCARD, END_TURN, FINAL_POINTS;

    /**This class is use to create the file "Persistence.json"*/
    public PlayerJSON(String nickname, int turnPosition, boolean inkwell, PlayerStatus status, int faithTrackPosition, int victoryPoints, PopeTile pope0, PopeTile pope1, PopeTile pope2, int depot0Max, int depot1Max, int depot2Max, int depot0Stored, int depot1Stored, int depot2Stored, int depotLeader1Stored, int depotLeader2Stored, ResourceType depot0Type, ResourceType depot1Type, ResourceType depot2Type, int leader0, int leader1, int strongboxShields, int strongboxServants, int strongboxCoins, int strongboxStones, int levelOccupied1, int levelOccupied2, int levelOccupied3, int slot1card1, int slot1card2, int slot1card3, int slot2card1, int slot2card2, int slot2card3, int slot3card1, int slot3card2, int slot3card3, boolean CHOOSE_ACTION, boolean GAME_SET, boolean INIT_CHOOSE_RESOURCES, boolean INIT_CHOOSE_LEADER_CARDS, boolean MARKET_CHOOSE_ROW, boolean CHOOSE_LEADER_CARD, boolean ADD_RESOURCE, boolean SWITCH_DEPOT, boolean RESET_WAREHOUSE, boolean END_MARKET, boolean BUY_CARD, boolean PAY_RESOURCE_CARD, boolean PAY_RESOURCE_PRODUCTION, boolean END_PAY_CARD, boolean CHOOSE_CARD_SLOT, boolean ACTIVATE_PRODUCTION, boolean END_PAY_PRODUCTION, boolean CHOOSE_PRODUCTION_OUTPUT, boolean ACTIVATE_LEADERCARD, boolean DELETE_LEADERCARD, boolean END_TURN, boolean FINAL_POINTS) {
        this.nickname = nickname;
        this.turnPosition = turnPosition;
        this.Inkwell = inkwell;
        this.status = status;
        this.faithTrackPosition = faithTrackPosition;
        this.victoryPoints = victoryPoints;
        this.pope0 = pope0;
        this.pope1 = pope1;
        this.pope2 = pope2;
        this.depot0Max = depot0Max;
        this.depot1Max = depot1Max;
        this.depot2Max = depot2Max;
        this.depot0Stored = depot0Stored;
        this.depot1Stored = depot1Stored;
        this.depot2Stored = depot2Stored;
        this.depotLeader1Stored = depotLeader1Stored;
        this.depotLeader2Stored = depotLeader2Stored;
        this.depot0Type = depot0Type;
        this.depot1Type = depot1Type;
        this.depot2Type = depot2Type;
        this.leader0 = leader0;
        this.leader1 = leader1;
        this.strongboxShields = strongboxShields;
        this.strongboxServants = strongboxServants;
        this.strongboxCoins = strongboxCoins;
        this.strongboxStones = strongboxStones;
        this.levelOccupied1 = levelOccupied1;
        this.levelOccupied2 = levelOccupied2;
        this.levelOccupied3 = levelOccupied3;
        this.slot1card1 = slot1card1;
        this.slot1card2 = slot1card2;
        this.slot1card3 = slot1card3;
        this.slot2card1 = slot2card1;
        this.slot2card2 = slot2card2;
        this.slot2card3 = slot2card3;
        this.slot3card1 = slot3card1;
        this.slot3card2 = slot3card2;
        this.slot3card3 = slot3card3;
        this.CHOOSE_ACTION = CHOOSE_ACTION;
        this.GAME_SET = GAME_SET;
        this.INIT_CHOOSE_RESOURCES = INIT_CHOOSE_RESOURCES;
        this.INIT_CHOOSE_LEADER_CARDS = INIT_CHOOSE_LEADER_CARDS;
        this.MARKET_CHOOSE_ROW = MARKET_CHOOSE_ROW;
        this.CHOOSE_LEADER_CARD = CHOOSE_LEADER_CARD;
        this.ADD_RESOURCE = ADD_RESOURCE;
        this.SWITCH_DEPOT = SWITCH_DEPOT;
        this.RESET_WAREHOUSE = RESET_WAREHOUSE;
        this.END_MARKET = END_MARKET;
        this.BUY_CARD = BUY_CARD;
        this.PAY_RESOURCE_CARD = PAY_RESOURCE_CARD;
        this.PAY_RESOURCE_PRODUCTION = PAY_RESOURCE_PRODUCTION;
        this.END_PAY_CARD = END_PAY_CARD;
        this.CHOOSE_CARD_SLOT = CHOOSE_CARD_SLOT;
        this.ACTIVATE_PRODUCTION = ACTIVATE_PRODUCTION;
        this.END_PAY_PRODUCTION = END_PAY_PRODUCTION;
        this.CHOOSE_PRODUCTION_OUTPUT = CHOOSE_PRODUCTION_OUTPUT;
        this.ACTIVATE_LEADERCARD = ACTIVATE_LEADERCARD;
        this.DELETE_LEADERCARD = DELETE_LEADERCARD;
        this.END_TURN = END_TURN;
        this.FINAL_POINTS = FINAL_POINTS;
    }
}
