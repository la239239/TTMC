package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            
            // Gestion de la fermeture de la fenêtre
            primaryStage.setOnCloseRequest(event -> {
                event.consume(); // Empêche la fermeture immédiate
                try {
                    Parent confirmRoot = FXMLLoader.load(getClass().getResource("/view/AreYouSure.fxml"));
                    Stage confirmStage = new Stage();
                    confirmStage.setScene(new Scene(confirmRoot));
                    confirmStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error loading FXML or CSS file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
