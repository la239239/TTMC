package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private void switchScene(MouseEvent event, String fxmlFile) {
        try {
            URL fxmlLocation = getClass().getResource(fxmlFile);
            if (fxmlLocation == null) {
                throw new IOException("Fichier FXML non trouvé : " + fxmlFile);
            }
            root = FXMLLoader.load(fxmlLocation);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de chargement", "Impossible de charger la scène : " + fxmlFile);
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void BackHome(MouseEvent event) {
        switchScene(event, "Home.fxml");
    }

    @FXML
    private void OpenCredits(MouseEvent event) {
        switchScene(event, "/view/Credits.fxml");
    }

    @FXML
    private void OpenRules(MouseEvent event) {
        switchScene(event, "/view/Rules.fxml");
    }

    @FXML
    private void OpenExit(MouseEvent event) {
        switchScene(event, "/view/AreYouSure.fxml");
    }
    
    @FXML
    private void Exit(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void OpenChooseSkin(MouseEvent event) {
        switchScene(event, "/view/ChooseSkin.fxml");
    }

    @FXML
    private void OpenNumberOfPlayers(MouseEvent event) {
        switchScene(event, "/view/NumberOfPlayers.fxml");
    }
    
    @FXML
    private void OpenBoardGame(MouseEvent event) {
        switchScene(event, "/view/BoardGame.fxml");
    }
    
    @FXML
    private void OpenName(MouseEvent event) {
    	switchScene(event, "/view/ChooseName.fxml");
    }

    @FXML
    private void selectMrKrabs(MouseEvent event) {
        System.out.println("Mr. Krabs selected!");
    }

    @FXML
    private void selectSandy(MouseEvent event) {
        System.out.println("Sandy selected!");
    }

    @FXML
    private void selectSquidward(MouseEvent event) {
        System.out.println("Squidward selected!");
    }

    @FXML
    private void selectSpongebob(MouseEvent event) {
        System.out.println("Spongebob selected!");
    }

    @FXML
    private void selectPatrick(MouseEvent event) {
        System.out.println("Patrick selected!");
    }
}