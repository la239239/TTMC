package models;

public class Question {
    private String theme;
    private double difficulty;
    private String questionText;
    private String answer;
    
    public Question(String theme, double difficulty, String questionText, String answer) {
        this.theme = theme;
        this.difficulty = difficulty;
        this.questionText = questionText;
        this.answer = answer;
    }
    
    // Getters
    public String getTheme() { return theme; }
    public double getDifficulty() { return difficulty; }
    public String getQuestionText() { return questionText; }
    public String getAnswer() { return answer; }
}
