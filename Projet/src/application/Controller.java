package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

	@FXML
    void ChoisirJoueurs(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoisirJoueurs.fxml"));
			Parent root1 = (Parent)loader.load();
			Stage stage = new Stage();
			stage.setTitle("Choisir Joueurs");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

}
