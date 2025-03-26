package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import application.SceneSwitcher;
import java.util.HashSet;
import java.util.Set;

public class ChooseNameController {

    @FXML
    private TextField playerNameField;
    
    @FXML
    private ImageView nextEnabledImage;
    
    @FXML
    private ImageView nextGreyImage;

    private static Set<String> existingNames = new HashSet<>();

    @FXML
    private void BackPlayerName(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/ChooseName.fxml");
    }
    
    @FXML
    private void initialize() {
        // Initialize the Next buttons: initially show the greyed-out image
        nextEnabledImage.setVisible(false);
        nextGreyImage.setVisible(true);

        // Add listener for player name field to toggle the visibility of the Next button
        playerNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            String playerName = playerNameField.getText().trim();
            if (playerName.isEmpty()) {
                nextGreyImage.setVisible(true);
                nextEnabledImage.setVisible(false);
            } else {
                nextGreyImage.setVisible(false);
                nextEnabledImage.setVisible(true);
            }
        });
    }

    @FXML
    private void validateName(MouseEvent event) {
        String playerName = playerNameField.getText().trim();

        if (playerName.isEmpty()) {
            // Open Error_AddName scene if the name field is empty
            openErrorScene("/view/Error_AddName.fxml");
            return;
        }
        
        if (existingNames.contains(playerName)) {
            // Open Error_DoubleName scene if the name already exists
            openErrorScene("/view/Error_DoubleName.fxml");
            return;
        }

        // If validation passes, add the name to the set and proceed to next scene
        existingNames.add(playerName);
        SceneSwitcher.switchScene(event, "/view/ChooseSkin.fxml");
    }

    // Method to open an error scene
    private void openErrorScene(String fxmlPath) {
        SceneSwitcher.switchScene(null, fxmlPath); // Null event to indicate scene switch without passing any event
    }
}
