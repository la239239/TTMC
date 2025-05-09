package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import models.Card;
import models.Player;

public class CardAnswerController {

    @FXML private ImageView correctButton;
    @FXML private ImageView wrongButton;
    @FXML private javafx.scene.control.Label answerLabel;


    private Card card;
    private Player currentPlayer;
    private int difficulty;
    private BoardGameController boardController;

    // Méthode pour définir la carte (affichage de la réponse)
    public void setCard(Card card) {
        this.card = card;
        if (answerLabel != null) {
            answerLabel.setText(card.getAnswer());
        }
    }


    // Méthode pour injecter les données nécessaires (joueur, difficulté et contrôleur)
    public void setData(Player player, int difficulty, BoardGameController boardController) {
        this.currentPlayer = player;
        this.difficulty = difficulty;
        this.boardController = boardController;
    }

    @FXML
    private void onCorrectClicked() {
        correctButton.setDisable(true);
        wrongButton.setDisable(true);
        boardController.advancePlayer(currentPlayer, difficulty);
        boardController.hideCard(); // ⬅️ Ajouté pour fermer l'overlay
    }

    @FXML
    private void onWrongClicked() {
        correctButton.setDisable(true);
        wrongButton.setDisable(true);
        boardController.advancePlayer(currentPlayer, 0);
        boardController.hideCard(); // ⬅️ Ajouté aussi ici
    }


    // Effet hover sur le bouton "OUI"
    @FXML
    private void onCorrectButtonHover() {
        correctButton.setOpacity(0.8); // Change l'opacité pour donner un effet de survol
    }

    @FXML
    private void onCorrectButtonExit() {
        correctButton.setOpacity(1.0); // Réinitialise l'opacité lorsque la souris quitte
    }

    // Effet hover sur le bouton "NON"
    @FXML
    private void onWrongButtonHover() {
        wrongButton.setOpacity(0.8); // Change l'opacité pour donner un effet de survol
    }

    @FXML
    private void onWrongButtonExit() {
        wrongButton.setOpacity(1.0); // Réinitialise l'opacité lorsque la souris quitte
    }
}
