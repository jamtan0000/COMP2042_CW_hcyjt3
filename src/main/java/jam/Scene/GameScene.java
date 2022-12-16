package jam.Scene;

import jam.GameLogic.GameLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Use to make game scene.
 *
 * @author James Tang
 */
public class GameScene {

    private Stage stage;

    private Group gameRoot;

    private Scene scene;

    private GameLogic game;

    /**
     * This is used to create game root in the fxml, is called when start a game or replay.
     *
     * @throws IOException IOError.
     */
    public GameScene() throws IOException {
        this.stage = Main.getStage();
        this.scene = Main.getScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScene.fxml"));
        loader.load();
        BorderPane gameSceneRoot = loader.getRoot();
        Label score = (Label) gameSceneRoot.lookup("#score");
        Node centerNode = gameSceneRoot.getCenter();
        if (centerNode instanceof Group) {
            gameRoot = (Group) centerNode;
        }
        scene.setRoot(gameSceneRoot);
        stage.setScene(scene);
        game = new GameLogic();
        game.game(gameRoot, score);
    }
}
