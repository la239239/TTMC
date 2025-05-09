package controllers;

import application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NumberOfPlayersController {

    @FXML
    private ToggleGroup playerCount; // Groupe de boutons radio pour choisir le nombre de joueurs

    @FXML
    private ImageView nextDisabledImage; // Bouton "Next" désactivé
    @FXML
    private ImageView nextEnabledImage; // Bouton "Next" activé

    @FXML
    private void initialize() {
        updateNextButtonState();
        
        // Ajout d'un écouteur pour réagir aux changements de sélection
        playerCount.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updateNextButtonState();
        });
    }

    @FXML
    private void goToPlayersScreen(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/CreatedPlayer.fxml");
    }

    @FXML
    private void updateNextButtonState() {
        boolean selected = playerCount.getSelectedToggle() != null;
        nextDisabledImage.setVisible(!selected);
        nextEnabledImage.setVisible(selected);
    }

    @FXML
    private void BackHome(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/Home.fxml");
    }
}

