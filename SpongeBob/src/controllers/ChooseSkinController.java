package controllers;

import application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChooseSkinController {

    @FXML
    private ImageView nextButton;

    private String selectedSkin = null;

    @FXML
    private void selectSkin(MouseEvent event) {
        Node clickedNode = (Node) event.getSource();
        selectedSkin = clickedNode.getId(); // Récupère l'ID du skin sélectionné

        // Active le bouton "Next" une fois qu'un skin est choisi
        nextButton.setDisable(false);
    }

    @FXML
    private void goToBoardGame(MouseEvent event) {
        if (selectedSkin == null) {
            showErrorDialog("Sélection requise", "Veuillez choisir un skin avant de continuer.");
            return;
        }
        SceneSwitcher.switchScene(event, "/view/BoardGame.fxml");
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
