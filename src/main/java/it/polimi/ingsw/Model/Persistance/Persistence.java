package it.polimi.ingsw.Model.Persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Persistence Class contains attributes and methods used by the Server to remember all information needed when restarting
 * the Server.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>File "file": Used to write a .txt file containing persistence info to be used by the Server</li>
 *     <li>boolean "gameStarted": Indicates whether a game already started or not</li>
 *     <li>int "numberOfPlayers": Indicates the number of players of the game which already started</li>
 *     <li>ArrayList&lt;String&gt; "playerNames": Contains the name of all players who joined the Server</li>
 * </ul>
 * @author redrick99
 */
public class Persistence {
    private File file;

    private boolean gameStarted = false;
    private int numberOfPlayers;
    private final ArrayList<String> playerNames = new ArrayList<>();

    /**
     * Getter for "gameStarted" attribute.
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Getter for "numberOfPlayers" attribute.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Getter for "playerNames" attribute.
     */
    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }

    /**
     * Creates a new .txt file if it doesn't already exist.
     * @throws IOException if there are problems when trying to check if the file exists.
     */
    public void createFile() throws IOException {
        if(!file.createNewFile())
            file = new File("persistence_info.txt");
    }

    /**
     * Writes information given into the .txt file.
     * @param gameStarted boolean indicating if the Game already started.
     * @param numberOfPlayers number of players who are playing the game.
     * @param players ArrayList containing the name of the players who are playing the game.
     */
    public void writeFile(boolean gameStarted, int numberOfPlayers, ArrayList<String> players){
        try {
            if (!file.createNewFile())
                file = new File("persistence_info.txt");

            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write("");
            if(gameStarted)
                fileWriter.write("1\n");
            else
                fileWriter.write("0\n");
            fileWriter.write(numberOfPlayers + "\n");

            if(players.size() > 0)
                for(String s : players)
                    fileWriter.write(s + "\n");

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file and stores information inside the class itself.
     */
    public void readFile() {
        try{
            Scanner sc = new Scanner(file);
            String gameStarted = sc.nextLine();
            this.gameStarted = gameStarted.equals("1");

            String numberOfPlayers = sc.nextLine();
            this.numberOfPlayers = Integer.parseInt(numberOfPlayers);

            this.playerNames.clear();
            for(int i = 0; i < this.numberOfPlayers; i++)
                this.playerNames.add(sc.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
