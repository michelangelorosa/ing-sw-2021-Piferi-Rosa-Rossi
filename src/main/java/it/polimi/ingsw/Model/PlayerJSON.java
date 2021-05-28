package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.PlayerStatus;

public class PlayerJSON {
    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    int status;
    private final int faithTrackPosition;

    /**This class is use to create the file "NamePlayers.json"*/
    public PlayerJSON(String nickname, int turnPosition, boolean hasInkwell, int status, int faithTrackPosition) {
        this.nickname = nickname;
        this.turnPosition = turnPosition;
        this.Inkwell = hasInkwell;
        this.status = status;
        this.faithTrackPosition = faithTrackPosition;
    }
}
