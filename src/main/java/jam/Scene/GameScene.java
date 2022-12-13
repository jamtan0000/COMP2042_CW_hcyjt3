package jam.Scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScene {

    private Stage stage;

    private Group gameRoot;

    private Scene gameScene;

    private GameLogic game;

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    public void buildScene(){

    }

    public GameScene(Stage stage, Scene scene) throws IOException {
        //showGameWindow();
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
        game.game(scene, gameRoot, stage, score);
        //gameScene = new Scene(gameRoot, 900, 900, Color.rgb(189, 177, 92));

    }
}
