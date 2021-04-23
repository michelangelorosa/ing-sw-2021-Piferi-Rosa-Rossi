package it.polimi.ingsw.Model;

public class PlayerJSON {
    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    private final PlayerStatus status;
    private final int faithTrackPosition;

    /**This class is use to create the file "NamePlayers.json"*/
    public PlayerJSON(String nickname, int turnPosition, boolean hasInkwell) {
        this.nickname = nickname;
        this.turnPosition = turnPosition;
        this.Inkwell = hasInkwell;
        this.status = PlayerStatus.IN_GAME;
        this.faithTrackPosition = 0;
    }
}
