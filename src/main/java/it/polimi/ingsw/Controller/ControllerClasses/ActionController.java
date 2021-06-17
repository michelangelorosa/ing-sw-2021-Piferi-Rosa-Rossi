package it.polimi.ingsw.Controller.ControllerClasses;

import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.Model.MessagesToClient.OtherMessages.DisconnectedMessage;

/**
 * ActionController Class contains all that is needed to compute an Action sent by the Client
 * and to create a MessageToClient message containing a response.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>Game "game": Instance of the Game that all connected players are playing on</li>
 *     <li>ModelToView "modelToView": Instance of a Class that contains data and methods needed
 *     to send a created MessageToClient to all Clients</li>
 *     <li>ResetWarehouse "resetWarehouse": Stores information at the start of a <i>Add Cycle</i> so that the player
 *     can reset his Warehouse to starting conditions</li>
 *     <li>ChooseCardSlot "chooseCardSlot": Stores information to be used when a player has finished buying
 *     a Development Card to remember which Development Card he wanted to buy</li>
 *     <li>ChooseProductionOutput "chooseProductionOutput": Stores information to be used when a player has finished
 *     paying for a Production to remember which different types of productions he started</li>
 * </ul>
 * @author redrick99
 */
public class ActionController {

    private final Game game;
    private final ModelToView modelToView = new ModelToView();

    private final ResetWarehouse resetWarehouse = new ResetWarehouse();
    private final ChooseCardSlot chooseCardSlot = new ChooseCardSlot(-1);
    private final ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();

    /**
     * Constructor for ActionController Class.
     */
    public ActionController() {
        this.game = new Game();
    }

    /**
     * Computes the client's request by calling the following methods inside the Action Class:
     * <ol>
     *     <li><b>doAction</b>: Checks the request and performs changes to Model</li>
     *     <li><b>messagePrepare</b>: Creates a MessageToClient (specific to the Action) ready to be sent to all Clients</li>
     * </ol>
     * @param action A generic action received by the server socket and passed through by the Controller.
     */
    public synchronized void doAction(Action action) {
        MessageToClient message;

        try {
            if (action instanceof ResetWarehouse) {
                resetWarehouse.doAction(this);
                message = resetWarehouse.messagePrepare(this);
            } else if (action instanceof ChooseCardSlot) {
                chooseCardSlot.setCardSlot(((ChooseCardSlot)action).getCardSlot());
                chooseCardSlot.doAction(this);

                message = chooseCardSlot.messagePrepare(this);
            } else if (action instanceof ChooseProductionOutput) {
                chooseProductionOutput.setBasicProductionOutput(((ChooseProductionOutput)action).getBasicProductionOutput());
                chooseProductionOutput.setFirstLeaderCardOutput(((ChooseProductionOutput)action).getFirstLeaderCardOutput());
                chooseProductionOutput.setSecondLeaderCardOutput(((ChooseProductionOutput)action).getSecondLeaderCardOutput());
                chooseProductionOutput.doAction(this);

                message = chooseProductionOutput.messagePrepare(this);
            } else {
                action.doAction(this);
                message = action.messagePrepare(this);
            }
        }
        catch (ModelException e){
            message = new MessageToClient();
            //TODO create exception message
        }

        modelToView.notify(message);
    }

    public ModelToView getModelToView() {
        return modelToView;
    }

    /**
     * Getter for "game" attribute.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for "resetWarehouse" attribute.
     */
    public ResetWarehouse getResetWarehouse() {
        return resetWarehouse;
    }

    /**
     * Getter for "chooseCardSlot" attribute.
     */
    public ChooseCardSlot getChooseCardSlot() {
        return chooseCardSlot;
    }

    /**
     * Getter for "chooseProductionOutput" attribute.
     */
    public ChooseProductionOutput getChooseProductionOutput() {
        return chooseProductionOutput;
    }

    /**
     * Prepares a Reduced version of the Model's game to be sent to all players after passing the initial phase of the game.
     * @return A message containing all information about the Model's game.
     */
    public synchronized GameSetMessage prepareViewGame() {
        System.out.println("[MODEL] current player: " + this.game.getCurrentPlayerNickname());
        GameSetMessage message = new GameSetMessage(this.game.getCurrentPlayerNickname());
        message.setPlayers(this.game.getViewPlayers());
        message.setMarket(this.game.getMarket());
        message.setTable(this.game.getDevelopmentCardTable());
        message.setFaithTrack(this.game.getFaithTrack());

        return message;
    }

    /**
     * Prepares a message to send to the player if the player who was playing his turn disconnected from the Server.
     * <p><b>Message is to be sent only if the CURRENTLY PLAYING player disconnected.</b></p>
     * @param disconnectedPlayer Name of the player who disconnected
     * @return A disconnection message.
     */
    public MessageToClient disconnected(String disconnectedPlayer) {
        this.game.getPlayerByNickname(disconnectedPlayer).setStatus(PlayerStatus.IDLE);

        if(this.game.getCurrentPlayerNickname().equals(disconnectedPlayer) && this.game.getGameType() == GameType.MULTIPLAYER) {
            this.game.nextPlayer();
        }

        return new DisconnectedMessage(this, disconnectedPlayer);
    }
}
