package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Card;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CardRandom {
    private List<Card> cards;

    public CardRandom(String filePath) {
        this.cards = loadCardsFromFile(filePath);
    }

    private List<Card> loadCardsFromFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> rawList;

        try {
            File file = new File(filePath);
            rawList = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            System.err.println("Error loading the JSON file: " + filePath);
            e.printStackTrace();
            return new ArrayList<>();
        }

        List<Card> cardList = new ArrayList<>();
        for (Map<String, Object> item : rawList) {
            try {
                String theme = item.get("theme") instanceof String ? (String) item.get("theme") : String.valueOf(item.get("theme"));
                int difficulty = item.get("difficulty") instanceof Integer ? (Integer) item.get("difficulty") : Integer.parseInt(item.get("difficulty").toString());
                String question = (String) item.get("question");
                String answer = (String) item.get("answer");

                Card card = new Card(theme, difficulty, question, answer);
                cardList.add(card);
            } catch (Exception e) {
                System.err.println("Error processing a card: " + item);
                e.printStackTrace();
            }
        }

        return cardList;
    }

    // Get the filtered iterator by theme and difficulty
    public CardIterator getIterator(String theme, int difficulty) {
        List<Card> filtered = cards.stream()
                .filter(c -> c.getThemeName().equalsIgnoreCase(theme) && c.getDifficulty() == difficulty)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            return null; // Return null if no cards match the filter
        }

        return new CardIterator(filtered);  // Return the iterator for the filtered cards
    }
}