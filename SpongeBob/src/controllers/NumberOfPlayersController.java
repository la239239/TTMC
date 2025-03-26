package controllers;

import java.io.IOException;

import application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NumberOfPlayersController {

    @FXML
    private ToggleGroup playerCount;

    @FXML
    private ImageView nextDisabledImage;

    @FXML
    private ImageView nextEnabledImage;

    @FXML
    private void goToNameScreen(MouseEvent event) {
        if (playerCount.getSelectedToggle() == null) {
            try {
                Parent confirmRoot = FXMLLoader.load(getClass().getResource("/view/Error_MakeAChoice.fxml"));
                
                Stage confirmStage = new Stage();
                confirmStage.setScene(new Scene(confirmRoot));
                
                // Make the window modal
                confirmStage.initModality(Modality.APPLICATION_MODAL);  // Important for modals
                confirmStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            SceneSwitcher.switchScene(event, "/view/ChooseName.fxml");
        }
    }


    @FXML
    private void checkSelection() {
        if (playerCount.getSelectedToggle() != null) {
            nextDisabledImage.setVisible(false);
            nextEnabledImage.setVisible(true);
        } else {
            nextDisabledImage.setVisible(true);
            nextEnabledImage.setVisible(false);
        }
    }

    @FXML
    private void initialize() {
        playerCount.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            checkSelection(); 
        });
    }

    @FXML
    private void BackHome(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/Home.fxml");
    }
}
