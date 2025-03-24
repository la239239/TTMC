package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {

	@FXML
    void OpenCredits(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Credits.fxml"));
			Parent root1 = (Parent)loader.load();
			Stage stage = new Stage();
			stage.setTitle("Credits");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch(Exception e) {
			System.out.println("Cannot load new window");
		}
    }

    @FXML
    void OpenExit(MouseEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AreYouSure.fxml"));
			Parent root1 = (Parent)loader.load();
			Stage stage = new Stage();
			stage.setTitle("Are you sure ?");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch(Exception e) {
			System.out.println("Cannot load new window  " + e.getMessage());
		}
    }

    @FXML
    void OpenNumberOfPlayers(MouseEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NumberPlayer.fxml"));
			Parent root1 = (Parent)loader.load();
			Stage stage = new Stage();
			stage.setTitle("Choose the right number of players");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch(Exception e) {
			System.out.println("Cannot load new window");
		}
    }

    @FXML
    void OpenRules(MouseEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Rules.fxml"));
			Parent root1 = (Parent)loader.load();
			Stage stage = new Stage();
			stage.setTitle("Rules of the game");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch(Exception e) {
			System.out.println("Cannot load new window");
		}
    }

	@FXML
    void BackHome(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
    }

    @FXML
    void Exit(MouseEvent event) {
    	Platform.exit();
    }

}
