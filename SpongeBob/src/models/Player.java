package models;

public class Player {
	private final String name;
    private final String pawnId;
    private Card currentCard; 

    public Player(String name, String pawnId) {
        this.name = name;
        this.pawnId = pawnId;
    }
    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }
    public String getName() {
        return name;
    }

    public String getPawnId() {
        return pawnId;
    }
    
    
}