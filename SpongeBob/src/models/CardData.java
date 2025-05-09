package models;

import utils.CardIterator;
import utils.CardRandom;

public class CardData {
    public static CardRandom cardRandom = new CardRandom("resource/question.json"); // Chemin à adapter si besoin

    public static void init(String filePath) {
        cardRandom = new CardRandom(filePath);
    }

    public static CardIterator getIterator(String theme, int level) {
        return cardRandom.getIterator(theme, level);
    }
}
