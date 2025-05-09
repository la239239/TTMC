package controllers;

import application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Error_MakeAChoiceController {
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
