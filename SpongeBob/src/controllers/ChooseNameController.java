package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import application.SceneSwitcher;
import java.util.HashSet;
import java.util.Set;

public class ChooseNameController {

    @FXML
    private TextField playerNameField;
    
    @FXML
    private ImageView nextEnabledImage;
    
    @FXML
    private ImageView nextGreyImage;

    private static Set<String> existingNames = new HashSet<>();

    @FXML
    private void handleBackButton(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/NumberOfPlayers.fxml"); // Adaptez ce chemin
    }
    
    @FXML
    private void initialize() {
        // Configuration initiale
        updateButtonState();
        
        // Écouteur des changements de texte
        playerNameField.textProperty().addListener((obs, oldVal, newVal) -> {
            updateButtonState();
        });
    }

    private void updateButtonState() {
        boolean nameIsEmpty = playerNameField.getText().trim().isEmpty();
        nextGreyImage.setVisible(nameIsEmpty);
        nextEnabledImage.setVisible(!nameIsEmpty);
    }

    @FXML
    private void handleEmptyName(MouseEvent event) {
        // Ouvre l'erreur seulement si le champ est vide (double vérification)
        if (playerNameField.getText().trim().isEmpty()) {
            System.out.println("Opening Error_AddName scene...");
            SceneSwitcher.switchScene(event, "/view/Error_AddName.fxml");
        }
    }

    @FXML
    private void handleValidName(MouseEvent event) {
        String playerName = playerNameField.getText().trim();
        
        // Vérifie les doublons
        if (existingNames.contains(playerName)) {
            SceneSwitcher.switchScene(event, "/view/Error_DoubleName.fxml");
            return;
        }

        // Ajoute le nom et change de scène
        existingNames.add(playerName);
        SceneSwitcher.switchScene(event, "/view/ChooseSkin.fxml");
    }
}