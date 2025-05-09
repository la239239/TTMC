package models;

public class Card {
    private String theme_name;
    private int difficulty;
    private String question;
    private String answer;

    // Constructor
    public Card(String theme_name, int difficulty, String question, String answer) {
        this.theme_name = theme_name;
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
    }

    // Getters
    public String getThemeName() {
        return theme_name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}