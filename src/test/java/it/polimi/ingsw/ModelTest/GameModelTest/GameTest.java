package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.EndMarket;
import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.JSON.ConvertToJSON;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameTest {
    Game game = new Game();

    @Test
    public void  joinTest() {
        joiner(game);

        assertEquals("Giacomo", game.getPlayers().get(0).getNickname());
        assertEquals("Valentino", game.getPlayers().get(1).getNickname());
        assertEquals("Andrea", game.getPlayers().get(2).getNickname());
        assertEquals("Lorenzo", game.getPlayers().get(3).getNickname());
    }

    @Test
    public void getCurrentPlayerTest() {
        joiner(game);
        assertEquals("Giacomo", game.getCurrentPlayer().getNickname());
    }


    private void joiner(Game game) {
        game.join("Giacomo");
        game.join("Valentino");
        game.join("Andrea");
        game.join("Lorenzo");

        ResourceStack resourcesRequired = new ResourceStack(0,0,0,0);
        LeaderRequirements cards = new LeaderRequirements(0,0,1,1,0,0,0,0,0,0,0,0);
        ResourceStack discount = new ResourceStack(0,1,0,0);
        game.getCurrentPlayer().getBoard().getLeaderCards()[0] = new LeaderCard(49, 2, resourcesRequired, cards, discount);

        game.getCurrentPlayer().addPossibleAction(ActionType.END_MARKET);
        game.getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
        game.getCurrentPlayer().addPossibleAction(ActionType.RESET_WAREHOUSE);
    }

    @Test
    public void jsonTest() throws FileNotFoundException, ModelException {
        ConvertToJSON convert = new ConvertToJSON();
        int cardId1=43,victoryPoints1=32,blueCardLv1=1,purpleCardLv1=2,yellowCardLv1=1,greenCardLv1=0,blueCardLv2=0,purpleCardLv2=0,yellowCardLv2=0,greenCardLv2=4,blueCardLv3=0,purpleCardLv3=0,yellowCardLv3=2,greenCardLv3=0;
        Marble marble = Marble.RED;
        ResourceType resourceType = ResourceType.COINS;
        ResourceStack resourceStack1 = new ResourceStack(1,4,6,7);
        ResourceStack resourceStack2 = new ResourceStack(6,5,2,4);
        ResourceStack resourceStack3 = new ResourceStack(0,0,0,0);
        ResourceStack resourceStack4 = new ResourceStack(6,4,1,8);
        ResourceStack resourceStackDiscount = new ResourceStack(5,7,0,0);
        ResourceStack resourceStackInput = new ResourceStack(4,3,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(blueCardLv1,purpleCardLv1,yellowCardLv1,greenCardLv1,blueCardLv2,purpleCardLv2,yellowCardLv2,greenCardLv2,blueCardLv3,purpleCardLv3,yellowCardLv3,greenCardLv3);

        LeaderCard leaderCard1 = new LeaderCard(cardId1,victoryPoints1,resourceStack1,leaderRequirements,resourceStackDiscount);
        joiner(game);
        for(int i = 0; i < game.getPlayers().size(); i++){
            game.getPlayers().get(i).getBoard().getLeaderCards()[0] = leaderCard1;
            game.getPlayers().get(i).getBoard().getLeaderCards()[1] = leaderCard1;
        }
        game.getDevelopmentCardTable().shuffleTable();
        game.getDevelopmentCardTable().drawCardFromDeck(0,0);
        convert.convertGame(game);
    }

    @Test
    public void getPlayerByNicknameTest() throws FileNotFoundException {
        joiner(game);
        Player Giacomo = game.getPlayerByNickname("Giacomo");
        assertEquals(ActionType.END_MARKET, Giacomo.getPossibleActions().get(0));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->{game.getPlayerByNickname("Giovanni");});
        assertEquals("[Model.Game]: cannot find player named Giovanni", e.getMessage());
    }

    @Test
    public  void getAllPlayersNameInOrderTest() throws FileNotFoundException {
        joiner(game);
        ArrayList<String> players = new ArrayList<>();
        players.add("Giacomo");
        players.add("Valentino");
        players.add("Andrea");
        players.add("Lorenzo");
        assertEquals(players, game.getAllPlayersNameInOrder());
    }

    @Test
    public void gameStartPlayersTest() throws ModelException {
        ArrayList<String> players = new ArrayList<>();
        ArrayList<String> playersMulti = new ArrayList<>();
        players.add("Giacomo");
        playersMulti.add("Gianni");
        playersMulti.add("Micky");
        playersMulti.add("Cyrano");

        game.gameStartPlayers(players, 1);
        assertEquals(GameType.SINGLEPLAYER, game.getGameType());

        game.gameStartPlayers(playersMulti, 4);
        assertEquals(GameType.MULTIPLAYER, game.getGameType());

    }

    @Test
    public void persistenceTest() throws FileNotFoundException, ModelException {
        joiner(game);
        ConvertToJSON convert = new ConvertToJSON();
        int cardId1=43,victoryPoints1=32,blueCardLv1=1,purpleCardLv1=2,yellowCardLv1=1,greenCardLv1=0,blueCardLv2=0,purpleCardLv2=0,yellowCardLv2=0,greenCardLv2=4,blueCardLv3=0,purpleCardLv3=0,yellowCardLv3=2,greenCardLv3=0;
        Marble marble = Marble.RED;
        ResourceType resourceType = ResourceType.COINS;
        ResourceStack resourceStack1 = new ResourceStack(1,4,6,7);
        ResourceStack resourceStack2 = new ResourceStack(6,5,2,4);
        ResourceStack resourceStack3 = new ResourceStack(0,0,0,0);
        ResourceStack resourceStack4 = new ResourceStack(6,4,1,8);
        ResourceStack resourceStackDiscount = new ResourceStack(5,7,0,0);
        ResourceStack resourceStackInput = new ResourceStack(4,3,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(blueCardLv1,purpleCardLv1,yellowCardLv1,greenCardLv1,blueCardLv2,purpleCardLv2,yellowCardLv2,greenCardLv2,blueCardLv3,purpleCardLv3,yellowCardLv3,greenCardLv3);
        LeaderRequirements leaderGeneric = new LeaderRequirements(blueCardLv1, purpleCardLv1, yellowCardLv1, greenCardLv1);

        LeaderCard leaderCard1 = new LeaderCard(1,victoryPoints1,resourceStack1,leaderRequirements,ResourceType.COINS);
        LeaderCard leaderCard2 = new LeaderCard(2,victoryPoints1,resourceStack1,leaderGeneric,ResourceType.COINS);
        LeaderCard leaderCard3 = new LeaderCard(3,victoryPoints1,resourceStack2,leaderRequirements,ResourceType.COINS);
        LeaderCard leaderCard4 = new LeaderCard(4,victoryPoints1,resourceStack1,leaderGeneric,ResourceType.COINS);
        LeaderCard leaderCard5 = new LeaderCard(5,victoryPoints1,resourceStack1,leaderRequirements,ResourceType.COINS);
        LeaderCard leaderCard6 = new LeaderCard(6,victoryPoints1,resourceStack3,leaderGeneric,ResourceType.COINS);
        LeaderCard leaderCard7 = new LeaderCard(7,victoryPoints1,resourceStack4,leaderRequirements,ResourceType.COINS);
        LeaderCard leaderCard8 = new LeaderCard(8,victoryPoints1,resourceStack1,leaderGeneric,ResourceType.COINS);

        leaderCard1.setActive(true);
        leaderCard2.setActive(true);
        leaderCard3.setActive(true);
        leaderCard4.setActive(true);
        leaderCard5.setActive(true);
        leaderCard6.setActive(true);
        leaderCard7.setActive(true);
        leaderCard8.setActive(true);

        game.getPlayers().get(0).getBoard().getLeaderCards()[0] = leaderCard1;
        game.getPlayers().get(0).getBoard().getLeaderCards()[1] = leaderCard2;
        game.getPlayers().get(1).getBoard().getLeaderCards()[0] = leaderCard3;
        game.getPlayers().get(1).getBoard().getLeaderCards()[1] = leaderCard4;
        game.getPlayers().get(2).getBoard().getLeaderCards()[0] = leaderCard5;
        game.getPlayers().get(2).getBoard().getLeaderCards()[1] = leaderCard6;
        game.getPlayers().get(3).getBoard().getLeaderCards()[0] = leaderCard7;
        game.getPlayers().get(3).getBoard().getLeaderCards()[1] = leaderCard8;


        game.getDevelopmentCardTable().drawCardFromDeck(0,0);

        game.getPlayers().get(0).getBoard().getLeaderCards()[0].setActive(true);
        Warehouse warehouse = new Warehouse();
        warehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        warehouse.getWarehouseDepots()[0].addResources(2);
        game.getPlayers().get(0).getBoard().getResourceManager().setWarehouse(warehouse);
        game.getPlayers().get(1).getBoard().getResourceManager().setWarehouse(warehouse);
        game.getPlayers().get(2).getBoard().getResourceManager().setWarehouse(warehouse);
        game.getPlayers().get(3).getBoard().getResourceManager().setWarehouse(warehouse);
        Strongbox strongbox = new Strongbox();
        strongbox.addResourcesByType(3, ResourceType.SHIELDS);
        strongbox.addResourcesByType(14, ResourceType.SERVANTS);
        strongbox.addResourcesByType(10, ResourceType.COINS);
        strongbox.addResourcesByType(99, ResourceType.STONES);
        game.getPlayers().get(0).getBoard().getResourceManager().setStrongbox(strongbox);

        game.getPlayers().get(0).getBoard().getDevelopmentCardSlots().getSlots()[0].addCard(game.getDevelopmentCardTable().getCardFromId(3));
        convert.convertGame(game);

        //System.out.println("---------- game2 = new Game AND game2.persistence() ----------");
        Game game2 = new Game();
        game2.persistence();
        assertEquals(game.getPlayers().get(0).getNickname(), game2.getPlayers().get(0).getNickname());
        assertEquals(game.getDevelopmentCardTable().getDeck(0,0).getCards()[0].getCost().getResource(ResourceType.STONES), game2.getDevelopmentCardTable().getDeck(0,0).getCards()[0].getCost().getResource(ResourceType.STONES));
        assertEquals(game.getDevelopmentCardTable().getDeck(0,0).getCardsInDeck(), game2.getDevelopmentCardTable().getDeck(0,0).getCardsInDeck());

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                assertEquals(game.getMarket().getMarble(i,j), game2.getMarket().getMarble(i,j));
            }
        }
        assertEquals(game.getPlayers().get(0).getBoard().getLeaderCards()[0].getCardId(), game2.getPlayers().get(0).getBoard().getLeaderCards()[0].getCardId());
        assertEquals(game.getPlayers().get(0).getBoard().getLeaderCards()[1].getCardId(), game2.getPlayers().get(0).getBoard().getLeaderCards()[1].getCardId());
        assertEquals(game.getPlayers().get(1).getBoard().getLeaderCards()[0].getCardId(), game2.getPlayers().get(1).getBoard().getLeaderCards()[0].getCardId());
        assertEquals(game.getPlayers().get(1).getBoard().getLeaderCards()[1].getCardId(), game2.getPlayers().get(1).getBoard().getLeaderCards()[1].getCardId());
        assertEquals(game.getPlayers().get(2).getBoard().getLeaderCards()[0].getCardId(), game2.getPlayers().get(2).getBoard().getLeaderCards()[0].getCardId());
        assertEquals(game.getPlayers().get(2).getBoard().getLeaderCards()[1].getCardId(), game2.getPlayers().get(2).getBoard().getLeaderCards()[1].getCardId());
        assertEquals(game.getPlayers().get(3).getBoard().getLeaderCards()[0].getCardId(), game2.getPlayers().get(3).getBoard().getLeaderCards()[0].getCardId());
        assertEquals(game.getPlayers().get(3).getBoard().getLeaderCards()[1].getCardId(), game2.getPlayers().get(3).getBoard().getLeaderCards()[1].getCardId());

        assertEquals(game.getPlayers().get(0).getBoard().getLeaderCards()[0].isActive(), game2.getPlayers().get(0).getBoard().getLeaderCards()[0].isActive());
        assertEquals(game.getPlayers().get(0).getBoard().getLeaderCards()[1].isActive(), game2.getPlayers().get(0).getBoard().getLeaderCards()[1].isActive());
        assertEquals(game.getPlayers().get(1).getBoard().getLeaderCards()[0].isActive(), game2.getPlayers().get(1).getBoard().getLeaderCards()[0].isActive());
        assertEquals(game.getPlayers().get(1).getBoard().getLeaderCards()[1].isActive(), game2.getPlayers().get(1).getBoard().getLeaderCards()[1].isActive());
        assertEquals(game.getPlayers().get(2).getBoard().getLeaderCards()[0].isActive(), game2.getPlayers().get(2).getBoard().getLeaderCards()[0].isActive());
        assertEquals(game.getPlayers().get(2).getBoard().getLeaderCards()[1].isActive(), game2.getPlayers().get(2).getBoard().getLeaderCards()[1].isActive());
        assertEquals(game.getPlayers().get(3).getBoard().getLeaderCards()[0].isActive(), game2.getPlayers().get(3).getBoard().getLeaderCards()[0].isActive());
        assertEquals(game.getPlayers().get(3).getBoard().getLeaderCards()[1].isActive(), game2.getPlayers().get(3).getBoard().getLeaderCards()[1].isActive());
        for(int i = 0; i < 4; i++) {
            assertEquals(game.getPlayers().get(i).getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getResourceType(), game2.getPlayers().get(i).getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getResourceType());
            assertEquals(game.getPlayers().get(i).getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources(), game2.getPlayers().get(i).getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources());
        }
        for (int i = 0; i < 4; i++) {
            assertEquals(game.getPlayers().get(i).getBoard().getResourceManager().getStrongbox().getStoredResources().getResource(ResourceType.STONES), game2.getPlayers().get(i).getBoard().getResourceManager().getStrongbox().getStoredResources().getResource(ResourceType.STONES));
        }
        //System.out.println("PRIMA: " + game.getPlayers().get(0).getBoard().getDevelopmentCardSlots().getSlots()[0].getCards()[0].getCost().getResource(ResourceType.COINS) + " DOPO: " + game2.getPlayers().get(0).getBoard().getDevelopmentCardSlots().getSlots()[0].getCards()[0].getCost().getResource(ResourceType.COINS));

        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 4; c++){
                for(int pos = 0; pos < 4; pos++){
                    System.out.println("P: " + game.getDevelopmentCardTable().getDeck(r,c).getCards()[pos]);
                    System.out.println("D: " + game2.getDevelopmentCardTable().getDeck(r,c).getCards()[pos]);
                }
                System.out.println("");
            }
        }

        game.getPlayers().remove(game.getPlayerByNickname("Valentino"));
        game.getPlayers().add(1, new Player("Lorenzo il Magnifico", 1, false));
        game.persistence();
    }

    @Test
    public void gameStartResourcesTest() {
        joiner(game);
        assertEquals(0, game.gameStartResources("Giacomo"));
        assertEquals(1, game.gameStartResources("Valentino"));
        assertEquals(1, game.gameStartResources("Andrea"));
        assertEquals(1, game.getPlayers().get(2).getFaithTrackPosition());
        assertEquals(2, game.gameStartResources("Lorenzo"));
        assertEquals(1, game.getPlayers().get(3).getFaithTrackPosition());

        game.getPlayers().add(new Player("pippo", 5, false));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {game.gameStartResources("pippo");});
        assertEquals("[Model.Game.gameStartResources]: player's position was 5", e.getMessage());
    }

    @Test
    public void changeCurrentPlayerReconnectionTest(){
        joiner(game);
        assertEquals("Giacomo", game.getCurrentPlayer().getNickname());
        game.changeCurrentPlayerReconnection("Andrea");
        assertEquals("Andrea", game.getCurrentPlayer().getNickname());
    }

    @Test
    public void isPlayerInGameNicknameTest(){
        assertFalse(game.isPlayerInGameNickname("Giacomo"));
        joiner(game);
        assertFalse(game.isPlayerInGameNickname("Antonio"));
        assertTrue(game.isPlayerInGameNickname("Giacomo"));
    }

    @Test
    public void removePlayerByNickname(){
        joiner(game);
        assertTrue(game.isPlayerInGameNickname("Giacomo"));
        game.removePlayerByNickname("Giacomo");
        assertFalse(game.isPlayerInGameNickname("Giacomo"));
    }

    @Test
    public void fillerTest() {
        joiner(game);
        assertEquals("Lorenzo", game.getPreviousPlayer().getNickname());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> game.join("Gianni"));
        assertEquals("Maximum number of players reached!", e.getMessage());
        game.nextPlayer();
        game.nextPlayer();
        game.nextPlayer();
        game.removePlayerByNickname("Lorenzo");
        game.join("Giacomo");

        ModelException e1 = assertThrows(ModelException.class, () -> game.gameStartPlayers(new ArrayList<>(), 10));
        assertEquals("Too many players", e1.getMessage());
    }

}
