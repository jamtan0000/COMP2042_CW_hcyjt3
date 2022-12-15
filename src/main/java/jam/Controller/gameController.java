package jam.Controller;

import jam.Scene.GameScene;
import jam.Scene.Main;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

/**
 * FXML controller for the game scene.
 */
public class gameController {
    @FXML
    private BorderPane gameSceneBorderPane;
    @FXML
    private Group gameRoot;
    /**
     * This is label of the score, used when updating score.
     */
    @FXML
    private Label score;
    /**
     * This is text area of the rule.
     */
    @FXML
    private TextArea txtRule;

    /**
     * Reset the game root to restart.
     * @throws IOException IOError
     */
    @FXML
    void ClickRestart() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Restart");
        alert.setHeaderText("Game progress will not save. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            gameRoot.getChildren().clear();
            GameScene gameScene = new GameScene();
        } else {
            gameRoot.requestFocus();
        }

    }

    /**
     * Back to mane button, pop up confirmation to end game.
     * @throws IOException IOError.
     */
    @FXML
    void clickMenu() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Back to menu");
        alert.setHeaderText("Game progress will not save. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            gameRoot.getChildren().clear();
            Main.setRoot("menu");
        } else {
            gameRoot.requestFocus();
        }
    }

    /**
     * When text area of rule is click, return focus to game root.
     */
    @FXML
    void clickRule() {
        gameRoot.requestFocus();
    }

    /**
     * Initialize the text area to not editable, set background color of the scene.
     *
     */
    public void initialize() {
        txtRule.setEditable(false);
        gameSceneBorderPane.setBackground(new Background(new BackgroundFill(Main.color, null, null)));
    }


}
