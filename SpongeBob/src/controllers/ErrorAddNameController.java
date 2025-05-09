package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import application.SceneSwitcher;

public class ErrorAddNameController {
    
    @FXML
    private void handleBackButton(MouseEvent event) {
        try {
            SceneSwitcher.switchScene(event, "/view/CreatedPlayer.fxml");
        } catch (Exception e) {
            System.err.println("Failed to return to ChooseName screen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}