package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import application.SceneSwitcher;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Player;

public class PlayersController implements Initializable {
    // Variables des labels pour les noms
    @FXML
    private Label nameRecapBob, nameRecapPatrick, nameRecapKrabs, nameRecapSandy, nameRecapSquid;

    @FXML
    private ImageView recapKrabsImage, recapSandyImage, recapSquidwardImage, recapSpongebob, recapPatrickImage,
    SpongebobImage, PatrickImage, MrKrabsImage, SandyImage, SquidwardImage,
    nextButton, nextGreyImage, playButton; 

    @FXML
    private TextField playerNameField;

    private static List<Player> players = new ArrayList<>();
    private Set<String> addedNames = new HashSet<>();
    private Set<String> usedPawns = new HashSet<>();

    private String selectedPawn;
       
    @FXML
    private void selectPawn(MouseEvent event) {
        ImageView selected = (ImageView) event.getSource();
        String pawnId = selected.getId();

        if (usedPawns.contains(pawnId)) return;

        resetPawnSelectionStyle();

        ScaleTransition zoom = new ScaleTransition(Duration.millis(200), selected);
        zoom.setToX(1.2);
        zoom.setToY(1.2);
        zoom.play();

        DropShadow yellowBorder = new DropShadow();
        yellowBorder.setColor(Color.YELLOW);
        yellowBorder.setWidth(40);
        yellowBorder.setHeight(40);
        yellowBorder.setSpread(0.6);
        selected.setEffect(yellowBorder);

        selectedPawn = pawnId;

        updateButtonsVisibility(); // ➤ Important ici aussi
    }

    
    @FXML
    private void handleEmptyName(MouseEvent event) {
    	 nextButton.setVisible(false);
         nextGreyImage.setVisible(true);
    }

    @FXML
    private void handleAddPlayer(MouseEvent event) {
        String name = playerNameField.getText().trim();

        Player newPlayer = new Player(name, selectedPawn);
        players.add(newPlayer);
        addedNames.add(name);
        usedPawns.add(selectedPawn);

        updateRecap(newPlayer);
        playerNameField.clear();
        selectedPawn = null;
        resetPawnSelectionStyle();

        updateButtonsVisibility();
    }

    public static List<Player> getPlayers() {
		return players;
	}


	private void updateButtonsVisibility() {
        if (nextButton == null || nextGreyImage == null || playButton == null || playerNameField == null)
            return;

        String currentName = playerNameField.getText().trim();
        boolean nameIsValid = !currentName.isEmpty() && !addedNames.contains(currentName);
        boolean pawnIsValid = selectedPawn != null && !usedPawns.contains(selectedPawn);

        boolean canAddPlayer = nameIsValid && pawnIsValid;

        // Gestion des boutons next
        nextButton.setVisible(canAddPlayer);
        nextGreyImage.setVisible(!canAddPlayer);

        // Gestion du bouton play - visible seulement si au moins 2 joueurs
        boolean canPlay = players.size() >= 2;
        playButton.setVisible(canPlay);
        playButton.setManaged(canPlay); // Important pour le layout
    }
	
	 @FXML private StackPane rootPane;
	    @FXML private ImageView backgroundImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialiser les boutons comme invisibles au départ
        nextButton.setVisible(false);
        nextGreyImage.setVisible(true);
        playButton.setVisible(false);
        playButton.setManaged(false);

        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
        
        playerNameField.textProperty().addListener((obs, oldText, newText) -> {
            updateButtonsVisibility();
        });
    }

    private void updateRecap(Player player) {
        String name = player.getName();
        String pawnId = player.getPawnId();

        switch (pawnId) {
            case "bobAvailable":
                nameRecapBob.setText(name);
                break;
            case "PatrickAvailable":
                nameRecapPatrick.setText(name);
                break;
            case "KrabsAvailable":
                nameRecapKrabs.setText(name);
                break;
            case "SandyAvailable":
                nameRecapSandy.setText(name);
                break;
            case "SquidAvailable":
                nameRecapSquid.setText(name);
                break;
        }
    }

    private void resetPawnSelectionStyle() {
        List<ImageView> pawns = List.of(SpongebobImage, PatrickImage, MrKrabsImage, SandyImage, SquidwardImage);

        for (ImageView pawn : pawns) {
            String pawnId = pawn.getId();
            
            if (usedPawns.contains(pawnId)) {
                // Griser et désactiver les pions déjà utilisés
                pawn.setOpacity(0.3);
                pawn.setDisable(true);
                pawn.setEffect(null);
                
                // Réinitialiser la taille
                pawn.setScaleX(1.0);
                pawn.setScaleY(1.0);
            } else {
                // Réinitialiser les autres pions
                pawn.setOpacity(1.0);
                pawn.setDisable(false);
                pawn.setEffect(null);
                
                // Animation pour réinitialiser la taille
                ScaleTransition resetZoom = new ScaleTransition(Duration.millis(200), pawn);
                resetZoom.setToX(1.0);
                resetZoom.setToY(1.0);
                resetZoom.play();
            }
        }
    }
    
    @FXML
    private void handlePlayButton(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BoardGame.fxml"));
        try {
            Parent root = loader.load();
            BoardGameController controller = loader.getController();
            controller.setPlayers(players);
            controller.setStage(stage); // ✅ Passage du stage ici
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());

            stage.setScene(scene);
            stage.setFullScreen(true); // ✅ FORCER le plein écran
            stage.setFullScreenExitHint(""); // ✅ Masquer le message
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); // ✅ Empêcher F11

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleBackButton(MouseEvent event) {
        SceneSwitcher.switchScene(event, "/view/Home.fxml");
    }
}