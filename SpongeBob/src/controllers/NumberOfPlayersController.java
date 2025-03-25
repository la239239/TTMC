package controllers;

import application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

public class NumberOfPlayersController {

    @FXML
    private ToggleGroup playerCount;

    @FXML
    private void goToNameScreen(MouseEvent event) {
        if (playerCount.getSelectedToggle() == null) {
            SceneSwitcher.switchScene(event, "/view/ErrorChoosePlayer.fxml");
        } else {
            SceneSwitcher.switchScene(event, "/view/ChooseName.fxml");
        }
    }
}
