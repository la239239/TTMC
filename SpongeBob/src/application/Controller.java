package application;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private void BackHome(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/Home.fxml");
    }

    @FXML
    private void OpenCredits(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/Credits.fxml");
    }

    @FXML
    private void OpenRules(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/Rules.fxml");
    }

    @FXML
    private void OpenExit(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/AreYouSure.fxml");
    }

    @FXML
    private void OpenChooseSkin(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/ChooseSkin.fxml");
    }

    @FXML
    private void OpenNumberOfPlayers(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/NumberOfPlayers.fxml");
    }

    @FXML
    private void OpenBoardGame(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/BoardGame.fxml");
    }

    @FXML
    private void OpenName(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/ChooseName.fxml");
    }
}
