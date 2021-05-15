package it.polimi.ingsw.Model.Enums;

/**
 * GameStatus contains all the possible status the server can have during the gameplay.
 * Lobby: waiting for players to join
 * Param: waiting for a client to finish editing the parameters
 * Game: doing normal gameplay
 * Idle: no player is connected
 * End: the game has declared a winner
 */
public enum GameStatus {
    LOBBY,PARAM,GAME,IDLE,END;
}
