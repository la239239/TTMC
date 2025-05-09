package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import application.Controller; // Import the initial controller
import controllers.BoardGameController; // Import the new controller

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the initial FXML file with the initial controller
            FXMLLoader initialLoader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            Parent initialRoot = initialLoader.load();
            Controller initialController = initialLoader.getController();

            Scene initialScene = new Scene(initialRoot);
            initialScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(initialScene);
            primaryStage.setTitle("TTMC Game");
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

            // Set exit handling if needed
            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                initialController.showExitConfirmation();
            });

            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Error loading FXML or CSS file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void switchToBoardGameController(Stage primaryStage) {
        try {
            // Load the FXML file with the new controller
            FXMLLoader newLoader = new FXMLLoader(getClass().getResource("/view/BoardGame.fxml"));
            Parent newRoot = newLoader.load();
            BoardGameController newController = newLoader.getController();

            Scene newScene = new Scene(newRoot);
            newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(newScene);

            // Set exit handling if needed
            primaryStage.setOnCloseRequest(event -> {
                event.consume();
               // newController.showExitConfirmation();
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
