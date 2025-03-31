package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class SceneSwitcher {
    public static void switchScene(MouseEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(root);
            // Appliquez le CSS si nécessaire (comme dans Main.java)
            scene.getStylesheets().add(SceneSwitcher.class.getResource("/application/application.css").toExternalForm());
            
            // Conserve le mode plein écran si déjà activé
            boolean wasFullScreen = stage.isFullScreen();
            stage.setScene(scene);
            stage.setFullScreen(wasFullScreen); // Rétablit le plein écran
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Erreur de chargement", "Impossible de charger la scène : " + fxmlFile);
        }
    }

    private static void showErrorDialog(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}