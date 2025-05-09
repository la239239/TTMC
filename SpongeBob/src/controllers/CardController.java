package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Question;
import models.QuestionGenerator;

public class CardController {

    @FXML
    private RadioButton difficulty1;

    @FXML
    private RadioButton difficulty2;

    @FXML
    private RadioButton difficulty3;

    @FXML
    private RadioButton difficulty4;

    private final QuestionGenerator generator = QuestionGenerator.create();

    @FXML
    public void initialize() {
        // Initialiser les RadioButton, et associer l'événement de sélection
        difficulty1.setOnAction(e -> generateQuestion(1.0));
        difficulty2.setOnAction(e -> generateQuestion(2.0));
        difficulty3.setOnAction(e -> generateQuestion(3.0));
        difficulty4.setOnAction(e -> generateQuestion(4.0));
    }

    private void generateQuestion(double difficulty) {
        try {
            // Générer une question avec la difficulté choisie
            Question question = generator.byDifficulty(difficulty);
            showAlert("Question", question.getQuestionText() + "\nRéponse: " + question.getAnswer());
        } catch (Exception e) {
            showAlert("Erreur", "Aucune question trouvée pour la difficulté " + difficulty);
        }
    }

    // Méthode utilitaire pour afficher des alertes
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


