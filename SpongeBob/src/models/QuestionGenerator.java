package models;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import utils.JSONLoader;

public class QuestionGenerator {
    private final List<Question> questions;
    private final Random random = new Random();

    // Constructeur privé (pour forcer l'usage de la Factory)
    private QuestionGenerator(List<Question> questions) {
        this.questions = questions;
    }

    // Factory Method statique
    public static QuestionGenerator create() {
        List<Question> questions = JSONLoader.loadQuestions(); // Charge via votre JSONLoader
        return new QuestionGenerator(questions);
    }

    // Méthodes de génération
    public Question byDifficulty(double difficulty) {
        return filterQuestions(difficulty).stream()
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Aucune question trouvée pour la difficulté: " + difficulty));
    }

    public Question byThemeAndDifficulty(String theme, double difficulty) {
        List<Question> filtered = filterQuestions(theme, difficulty);
        return filtered.get(random.nextInt(filtered.size()));
    }

    private List<Question> filterQuestions(double difficulty) {
        return questions.stream()
                .filter(q -> q.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    private List<Question> filterQuestions(String theme, double difficulty) {
        return questions.stream()
                .filter(q -> q.getTheme().equals(theme))
                .filter(q -> q.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }
}
