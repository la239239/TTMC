package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AreYouSureController {

    @FXML
    private void Exit(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close(); // Ferme la fenêtre "Are You Sure"
        System.exit(0); // Ferme l'application complète
    }

    @FXML
    private void GoBack(MouseEvent event) {
        // Ferme uniquement la fenêtre "Are You Sure", la fenêtre précédente reste ouverte.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
