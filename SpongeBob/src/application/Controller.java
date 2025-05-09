package application;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Player;

public class Controller {

    private List<Player> players;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

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
    private void CreatedPlayer(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/CreatedPlayer.fxml");
    }

    @FXML
    private void OpenBoardGame(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/BoardGame.fxml", players);
    }
    @FXML
    public void showExitConfirmation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AreYouSure.fxml"));
            Parent root = loader.load();
            Stage confirmStage = new Stage();
            confirmStage.setScene(new Scene(root));
            confirmStage.initModality(Modality.APPLICATION_MODAL);
            confirmStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML private StackPane rootPane;
    @FXML private ImageView backgroundImage;

    @FXML
    public void initialize() {
        if (backgroundImage != null && rootPane != null) {
            backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
            backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
        }
    }

}
