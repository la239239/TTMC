package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Player;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.List;

import controllers.BoardGameController;

public class SceneSwitcher {

    /**
     * Switches to a new scene, injecting players if necessary.
     * @param event MouseEvent that triggered the switch.
     * @param fxmlFile Path to the FXML file.
     * @param players List of players to inject.
     * @return The controller of the new scene.
     */
    public static Object switchScene(MouseEvent event, String fxmlFile, List<Player> players) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFile));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof BoardGameController) {
                ((BoardGameController) controller).setPlayers(players);

                // Let the board show the message and wait for the player's confirmation
                ((BoardGameController) controller).prepareNextTurn();
            }

            Scene scene = new Scene(root);
            scene.getStylesheets().add(SceneSwitcher.class.getResource("/application/application.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 👉 Appliquer le mode plein écran avant de montrer la scène
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(javafx.scene.input.KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.setFullScreen(true); // Toujours en plein écran
            stage.show();
            
            return controller;

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Loading error", "Unable to load scene: " + fxmlFile);
            return null;
        }
    }

    /**
     * Switches to a new scene without injecting players.
     * @param event MouseEvent that triggered the switch.
     * @param fxmlFile Path to the FXML file.
     * @return The controller of the new scene.
     */
    public static Object switchScene(MouseEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(SceneSwitcher.class.getResource("/application/application.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 👉 Appliquer le mode plein écran avant de montrer la scène
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(javafx.scene.input.KeyCombination.NO_MATCH);
            stage.setScene(scene);
            stage.setFullScreen(true); // Toujours en plein écran
            stage.show();


            return loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Loading error", "Unable to load scene: " + fxmlFile);
            return null;
        }
    }

    /**
     * Displays an error dialog.
     * @param title Title of the alert.
     * @param message Message content.
     */
    private static void showErrorDialog(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
