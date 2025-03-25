package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import application.SceneSwitcher;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChooseNameController {

    @FXML
    private TextField playerNameField;
    
    private static Set<String> existingNames = new HashSet<>();

    @FXML
    private void validateName(MouseEvent event) {
        String playerName = playerNameField.getText().trim();

        if (playerName.isEmpty()) {
            showErrorDialog("Nom requis", "Veuillez entrer un nom.");
            return;
        }
        if (existingNames.contains(playerName)) {
            showErrorDialog("Nom déjà pris", "Ce nom est déjà utilisé.");
            return;
        }

        existingNames.add(playerName);
        SceneSwitcher.switchScene(event, "/view/ChooseSkin.fxml");
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
