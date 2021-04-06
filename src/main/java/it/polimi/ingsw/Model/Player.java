package it.polimi.ingsw.Model;

public class Player {
    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    private PlayerStatus status;
    //private final Board board;

    public Player(String nickname, int turnPosition, boolean hasInkwell) {
        this.nickname = nickname;
        this.turnPosition = turnPosition;
        this.Inkwell = hasInkwell;
        this.status = PlayerStatus.IN_GAME;
        //board = new Board();
    }

    public String getNickname() {
        return nickname;
    }

    public int getTurnPosition() {
        return turnPosition;
    }

    public boolean hasInkwell() {
        return Inkwell;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

}
