package controllers;

import application.SceneSwitcher;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
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
        String clickedSkin = clickedNode.getId(); // The ID of the selected image

        // Reset the effects for all images
        resetSkinEffects();

        // Apply an enlargement and glowing border effect to the selected image
        ImageView selectedImage = (ImageView) clickedNode;
        
        // Enlargement effect
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), selectedImage);
        scaleTransition.setToX(1.2); // Enlarge to 120% of the original size
        scaleTransition.setToY(1.2); // Enlarge to 120% of the original size
        scaleTransition.play();
        
        // Glowing border effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.YELLOW); // Color of the glowing border
        dropShadow.setRadius(15); // Radius of the shadow
        selectedImage.setEffect(dropShadow);

        // Update the selected skin variable
        selectedSkin = clickedSkin;

        // Show the "Next" button
        nextDisabledImage.setVisible(false);
        nextEnabledImage.setVisible(true);
    }

    // Reset the effects for all images
    private void resetSkinEffects() {
        // Reset the effects of all images
        for (Node node : nextEnabledImage.getParent().getChildrenUnmodifiable()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setEffect(null); // Remove the glowing border effect
                imageView.setScaleX(1); // Reset the scale
                imageView.setScaleY(1); // Reset the scale
            }
        }
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
