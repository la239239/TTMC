package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import models.Card;
import models.CardData;
import models.Player;
import utils.CardIterator;

public class CardChooseLevelController {

    @FXML private Text subjectText;
    @FXML private RadioButton level1, level2, level3, level4;
    @FXML private ImageView nextButton;
    @FXML private ToggleGroup levelGroup;

    private String currentTheme;
    private int selectedLevel = -1;
    private Card selectedCard;

    public void setCurrentTheme(String theme) {
        this.currentTheme = theme;
        subjectText.setText(theme); // affichage dans la vue
    }

    @FXML
    private void onLevelSelected(ActionEvent event) {
        RadioButton selected = (RadioButton) event.getSource();
        selectedLevel = Integer.parseInt(selected.getText());

        // Charger la carte correspondante
        CardIterator iterator = CardData.getIterator(currentTheme, selectedLevel);
        if (iterator != null && iterator.hasNext()) {
            selectedCard = iterator.next();
            nextButton.setOpacity(1.0); // activer visuellement le bouton
        } else {
            selectedCard = null;
            System.out.println("Aucune carte trouvée pour le thème " + currentTheme + " et niveau " + selectedLevel);
            nextButton.setOpacity(0.3); // désactiver visuellement
        }
    }
    private Player currentPlayer;
    private BoardGameController boardController;

    public void setData(String theme, Player player, BoardGameController boardController) {
        this.currentTheme = theme;
        this.currentPlayer = player;
        this.boardController = boardController;
        subjectText.setText(theme);
    }


    @FXML
    private void onNextClicked(MouseEvent event) {
        if (selectedCard == null) return;

        // Appelle directement la méthode du plateau
        boardController.showCard(selectedCard, selectedLevel, currentPlayer);
    }
    
    @FXML
    private void initialize() {
        levelGroup = new ToggleGroup();
        level1.setToggleGroup(levelGroup);
        level2.setToggleGroup(levelGroup);
        level3.setToggleGroup(levelGroup);
        level4.setToggleGroup(levelGroup);
    }


}
