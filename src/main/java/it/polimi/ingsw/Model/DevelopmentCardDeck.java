package it.polimi.ingsw.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class DevelopmentCardDeck {
    private DevelopmentCard[] cards;
    private Color color;
    private Level level;
    private int cardsInDeck;

    public DevelopmentCardDeck(DevelopmentCard[] cards, Color color, Level level) {
        this.cards = cards;
        this.color = color;
        this.level = level;
    }

    public DevelopmentCard[] getCards() {
        return cards;
    }

    public void setCards(DevelopmentCard[] cards) {
        this.cards = cards;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
