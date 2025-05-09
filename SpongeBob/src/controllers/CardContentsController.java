package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Card;
import models.Player;

import java.io.IOException;

public class CardContentsController {

    @FXML private Label questionText;
    @FXML private Label timerLabel;
    @FXML private ImageView nextImage;
    @FXML private ImageView nextGreyImage;
    @FXML private Text subjectText;

    private Card card;
    private int timeRemaining = 35; // Durée totale avant passage auto
    private Timeline timeline;
    private int difficulty;
    private Player currentPlayer;
    private BoardGameController boardController;

    public void setData(Card card, int difficulty, Player player, BoardGameController boardController) {
        this.card = card;
        this.difficulty = difficulty;
        this.currentPlayer = player;
        this.boardController = boardController;

        questionText.setText(card.getQuestion());
        subjectText.setText(card.getThemeName());

        startCountdown();
    }


    private void startCountdown() {
        timerLabel.setText("Time: " + timeRemaining);
        nextImage.setVisible(false);
        nextGreyImage.setVisible(true);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            timerLabel.setText("Time: " + timeRemaining);

            if (timeRemaining == 30) {
            	nextImage.setVisible(true);
            	nextGreyImage.setVisible(false);
            }

            if (timeRemaining <= 0) {
                timeline.stop();
                goToAnswerScene();
            }
        }));
        timeline.setCycleCount(timeRemaining);
        timeline.play();
    }

    @FXML
    private void onSeeAnswerClicked() {
        timeline.stop();
        goToAnswerScene();
    }

    @FXML
    private void goToAnswerScene() {
        boardController.showAnswer(card, difficulty, currentPlayer);
    }

}
