package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Card;
import models.Player;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BoardGameController implements Initializable{

    @FXML private StackPane rootPane;
    @FXML private AnchorPane pawnBox;
    @FXML private AnchorPane nextPane;
    @FXML private ImageView nextButton;
    @FXML private ImageView playerImage;
    @FXML private Label turnLabel;

    @FXML private ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10;
    @FXML private ImageView tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19, tile20;
    @FXML private ImageView tile21, tile22, tile23, tile24, tile25, tile26, tile27, tile28, tile29, tile30;
    @FXML private ImageView tile31, tile32, tile33, tile34, tile35, tile36, tile37, tile38;
    @FXML private ImageView spongebob_pawn, patrick_pawn, squidward_pawn, sandy_pawn, mrKrabs_pawn;

    private List<Player> players = new ArrayList<>();
    private Map<String, ImageView> pawnMap = new HashMap<>();
    private Map<String, Integer> pawnPositions = new HashMap<>();
    private List<ImageView> tileList = new ArrayList<>();
    private Map<String, String> tileThemes = new HashMap<>();
    private int currentPlayerIndex = 0;

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setPlayers(List<Player> selectedPlayers) {
        this.players = selectedPlayers;
        assignThemesToTiles();

        pawnMap.put("bobAvailable", spongebob_pawn);
        pawnMap.put("PatrickAvailable", patrick_pawn);
        pawnMap.put("SquidAvailable", squidward_pawn);
        pawnMap.put("SandyAvailable", sandy_pawn);
        pawnMap.put("KrabsAvailable", mrKrabs_pawn);

        for (Player p : players) {
            pawnPositions.put(p.getPawnId(), 0);
        }

        showActivePawns();
        javafx.application.Platform.runLater(this::prepareNextTurn);
    }

    public void prepareNextTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        turnLabel.setText(currentPlayer.getName() + ", it's your turn!");

        String pawnId = currentPlayer.getPawnId();
        playerImage.setImage(pawnMap.get(pawnId).getImage());

        double paneWidth = nextPane.getPrefWidth();
        double sceneWidth = rootPane.getWidth();
        nextPane.setLayoutX((sceneWidth - paneWidth) / 2);

        nextPane.setVisible(true);
        nextButton.setVisible(true);
        nextButton.setOnMouseClicked(e -> handlePlayerTurn());
    }

    public void handlePlayerTurn() {
        nextPane.setVisible(false);
        nextButton.setVisible(false);

        Player currentPlayer = players.get(currentPlayerIndex);
        String pawnId = currentPlayer.getPawnId();
        int pos = pawnPositions.get(pawnId);
        ImageView tile = tileList.get(pos);
        String theme = tileThemes.getOrDefault(tile.getId(), "UNKNOWN");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Card_ChooseLevel.fxml"));
            Parent cardNode = loader.load();

            CardChooseLevelController controller = loader.getController();
            controller.setData(theme, currentPlayer, this);

            cardPopup.getChildren().setAll(cardNode);
            overlayShadow.setVisible(true);
            cardPopup.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void advancePlayer(Player player, int steps) {
        String pawnId = player.getPawnId();
        int current = pawnPositions.getOrDefault(pawnId, 0);
        int next = Math.min(current + steps, tileList.size() - 1);
        pawnPositions.put(pawnId, next);
        updatePawnsOnTile(tileList.get(current));
        updatePawnsOnTile(tileList.get(next));

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        prepareNextTurn();
    }

    private void showActivePawns() {
        for (Player player : players) {
            ImageView pawn = pawnMap.get(player.getPawnId());
            if (pawn != null) {
                pawn.setVisible(true);
                updatePawnsOnTile(tileList.get(0));
            }
        }
    }

    private void updatePawnsOnTile(ImageView tile) {
        Bounds bounds = tile.localToScene(tile.getBoundsInLocal());
        Point2D basePos = pawnBox.sceneToLocal(bounds.getMinX(), bounds.getMinY());

        int tileIndex = tileList.indexOf(tile);
        List<ImageView> pawnsOnTile = pawnMap.values().stream()
            .filter(p -> pawnPositions.getOrDefault(getPawnIdFromImageView(p), -1) == tileIndex)
            .toList();

        for (int i = 0; i < pawnsOnTile.size(); i++) {
            ImageView pawn = pawnsOnTile.get(i);
            double offsetX = 0, offsetY = 0;

            switch (pawnsOnTile.size()) {
                case 2 -> {
                    offsetX = (i == 0) ? -15 : 15;
                    offsetY = (i == 0) ? -15 : 15;
                }
                case 3 -> {
                    offsetX = switch (i) {
                        case 0 -> -20;
                        case 1 -> 20;
                        default -> 0;
                    };
                    offsetY = (i < 2) ? -20 : 20;
                }
                case 4 -> {
                    offsetX = (i % 2 == 0) ? -20 : 20;
                    offsetY = (i < 2) ? -20 : 20;
                }
                case 5 -> {
                    offsetX = switch (i) {
                        case 0 -> -20;
                        case 1 -> 20;
                        case 2 -> -20;
                        case 3 -> 20;
                        default -> 0;
                    };
                    offsetY = switch (i) {
                        case 0, 1 -> -20;
                        case 2, 3 -> 20;
                        default -> 0;
                    };
                }
            }

            double finalX = basePos.getX() + 35 + offsetX - 26;
            double finalY = basePos.getY() + 35 + offsetY - 26;

            Timeline move = new Timeline(
                new KeyFrame(Duration.seconds(0.3),
                    new KeyValue(pawn.layoutXProperty(), finalX),
                    new KeyValue(pawn.layoutYProperty(), finalY))
            );
            move.play();

            double scale = switch (pawnsOnTile.size()) {
                case 2 -> 0.9;
                case 3 -> 0.85;
                case 4 -> 0.8;
                case 5 -> 0.75;
                default -> 1.0;
            };

            ScaleTransition st = new ScaleTransition(Duration.seconds(0.2), pawn);
            st.setToX(scale);
            st.setToY(scale);
            st.play();

            pawn.toFront();
        }
    }

    private String getPawnIdFromImageView(ImageView view) {
        return pawnMap.entrySet().stream()
            .filter(entry -> entry.getValue() == view)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);
    }

    private void assignThemesToTiles() {
        tileList = Arrays.asList(
            tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
            tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19, tile20,
            tile21, tile22, tile23, tile24, tile25, tile26, tile27, tile28, tile29, tile30,
            tile31, tile32, tile33, tile34, tile35, tile36, tile37, tile38
        );

        tileThemes.clear();

        for (int i = 0; i < tileList.size(); i++) {
            String tileId = "tile" + (i + 1);
            if (i == tileList.size() - 1) {
                tileThemes.put(tileId, "FINISH");
            } else if ((i + 1) % 10 == 0) {
                tileThemes.put(tileId, "BONUS");
            } else {
                switch ((i % 4)) {
                    case 0 -> tileThemes.put(tileId, "EDUCATION");
                    case 1 -> tileThemes.put(tileId, "IT");
                    case 2 -> tileThemes.put(tileId, "IMPROBABLE");
                    case 3 -> tileThemes.put(tileId, "ENTERTAINMENT");
                }
            }
        }
    }

    @FXML private ImageView backgroundImage;
    @FXML private AnchorPane cardPopup;
    @FXML private AnchorPane overlayShadow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
        assignThemesToTiles();
    }

    public void showCard(Card card, int difficulty, Player player) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Card_Contents.fxml"));
            Parent cardNode = loader.load();

            CardContentsController ctrl = loader.getController();
            ctrl.setData(card, difficulty, player, this);

            cardPopup.getChildren().setAll(cardNode);
            overlayShadow.setVisible(true);
            cardPopup.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hideCard() {
        cardPopup.setVisible(false);
        overlayShadow.setVisible(false);
    }

    public void showAnswer(Card card, int difficulty, Player player) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/card_answer.fxml"));
            Parent node = loader.load();

            CardAnswerController ctrl = loader.getController();
            ctrl.setData(player, difficulty, this);
            ctrl.setCard(card);

            cardPopup.getChildren().setAll(node);
            cardPopup.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
