package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.TextField;
import java.io.IOException;

public class Controller {
//	home.fxml
    @FXML
    private void OpenNumberOfPlayers(MouseEvent event) {
        try {
            // Load the Number of Players FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/numberplayer.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Number of Players");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void OpenCredits(MouseEvent event) {
        try {
            // Load the Credits FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/credits.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Credits");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void OpenRules(MouseEvent event) {
        try {
            // Load the Rules FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/rules.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Rules");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void OpenExit(MouseEvent event) {
        try {
            // Load the Are You Sure FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/areyousure.fxml")); 
            Stage stage = new Stage(); 
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Exit Confirmation");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

//    AreYouSure.fxml
    @FXML
    private void BackHome(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close(); 

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/home.fxml")); // Home screen FXML path
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Exit(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close(); 
        System.exit(0); 
    }
    
//   NumberPlayer.fxml 
    @FXML
    private TextField playerNameTextField;

    // Other FXML elements can be added here

    @FXML
    private void initialize() {
        // Code to initialize any UI elements
    }

    // Example method for handling a button click or other event
    @FXML
    private void handleStartButtonClick() {
        String playerName = playerNameTextField.getText();
        System.out.println("Player Name: " + playerName);
        // Your logic to handle the name input, for example, starting the game
    }
    
    @FXML
    private void OpenChooseName(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/choosename.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Name");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
