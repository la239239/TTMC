package utils;

import models.Card;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;

public class CardIterator {
    private List<Card> filteredCards;  // List of filtered cards
    private List<Card> remainingCards; // Cards that haven't been drawn yet
    private Random random = new Random(); // Random object for drawing cards

    // Constructor initializes the filtered cards and shuffles them
    public CardIterator(List<Card> filteredCards) {
        this.filteredCards = filteredCards;
        this.remainingCards = new ArrayList<>(filteredCards); // Copy the list to keep track of remaining cards
    }

    // Method to check if there are remaining cards
    public boolean hasNext() {
        return !remainingCards.isEmpty(); // True if there are cards remaining
    }

    // Method to get the next random card
    public Card next() {
        if (!hasNext()) {
            // If no cards left, restock with the original cards and shuffle again
            restockAndShuffle();
        }

        // Randomly pick a card from the remaining list
        int index = random.nextInt(remainingCards.size());
        Card nextCard = remainingCards.get(index);

        // Remove the card so it won't be picked again
        remainingCards.remove(index);
        return nextCard;
    }

    // Restock and shuffle the remaining cards when they've been exhausted
    private void restockAndShuffle() {
        System.out.println("No more cards available. Restocking the cards...");
        remainingCards = new ArrayList<>(filteredCards); // Reset the remaining cards list with the original filtered cards
        Collections.shuffle(remainingCards); // Shuffle them again
    }

    // Getter to access the filtered cards
    public List<Card> getFilteredCards() {
        return filteredCards;
    }
}
