package controllers;

import application.SceneSwitcher;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class ChooseSkinController {

    @FXML
    private ImageView nextEnabledImage;

    @FXML
    private ImageView nextDisabledImage;

    private String selectedSkin = null;

    @FXML
    private void initialize() {
        nextDisabledImage.setVisible(true);
        nextEnabledImage.setVisible(false);
    }

    @FXML
    private void selectSkin(MouseEvent event) {
        Node clickedNode = (Node) event.getSource();
        String clickedSkin = clickedNode.getId();

        resetSkinEffects();

        ImageView selectedImage = (ImageView) clickedNode;
        
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), selectedImage);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.play();
        
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.YELLOW);
        dropShadow.setRadius(15);
        selectedImage.setEffect(dropShadow);

        selectedSkin = clickedSkin;

        nextDisabledImage.setVisible(false);
        nextEnabledImage.setVisible(true);
    }

    private void resetSkinEffects() {
        for (Node node : nextEnabledImage.getParent().getChildrenUnmodifiable()) {
            if (node instanceof ImageView) {
                ImageView imageView = (ImageView) node;
                imageView.setEffect(null);
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        }
    }

    @FXML
    private void goToBoardGame(MouseEvent event) {
        if (selectedSkin == null) {
            openErrorWindow();
            return;
        }

        SceneSwitcher.switchScene(event, "/view/BoardGame.fxml");
    }

    private void openErrorWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Error_MakeAChoice.fxml"));
            Parent root = loader.load();
            
            Stage errorStage = new Stage();
            errorStage.setScene(new Scene(root));
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.setTitle("Selection Required");
            errorStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Error_MakeAChoice.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
