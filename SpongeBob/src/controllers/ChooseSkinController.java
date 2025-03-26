package controllers;

import java.util.Objects;

import application.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChooseSkinController {

    @FXML
    private ImageView nextEnabledImage;

    @FXML
    private ImageView nextDisabledImage;

    // Variable to store the selected skin
    private String selectedSkin = null;

    // Initialize the scene and the "Next" button
    @FXML
    private void initialize() {
        // Initially, the Next button is disabled and the grey image is displayed
        nextDisabledImage.setVisible(true);
        nextEnabledImage.setVisible(false);
    }

    // Method to handle skin selection
    @FXML
    private void selectSkin(MouseEvent event) {
        // When a skin is selected
        Node clickedNode = (Node) event.getSource();
        selectedSkin = clickedNode.getId(); // The ID of the selected image

        // Display a debug message to verify the selected skin
        System.out.println("Selected skin: " + selectedSkin);

        // Enable the "Next" button (show the normal image and hide the grey image)
        nextDisabledImage.setVisible(false);
        nextEnabledImage.setVisible(true);
    }

    // Method to proceed to the next scene (BoardGame)
    @FXML
    private void goToBoardGame(MouseEvent event) {
        if (selectedSkin == null) {
            showErrorDialog("Selection required", "Please choose a skin before proceeding.");
            return;
        }

        SceneSwitcher.switchScene(event, "/view/BoardGame.fxml");
    }

    // Method to show an error dialog
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
