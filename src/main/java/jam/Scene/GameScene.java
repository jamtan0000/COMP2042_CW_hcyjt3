package jam.Scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    public void startGame(){

    }

    public GameScene(Stage stage) throws IOException {
        //showGameWindow();
        gameRoot = new Group();
        //setGameRoot(gameRoot);
        //scene.setRoot(gameRoot);
        gameScene = new Scene(gameRoot, 900, 900, Color.rgb(189, 177, 92));
        //setGameScene(scene);
        stage.setScene(gameScene);
        game = new GameLogic();

        Button replay = new Button("Start new game");
        gameRoot.getChildren().add(replay);
        replay.relocate(750, 350);

        Button btnMenu = new Button("Back to menu");;
        gameRoot.getChildren().add(btnMenu);
        btnMenu.relocate(750, 250);

        Text text = new Text();
        gameRoot.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);

        Text scoreText = new Text();
        gameRoot.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        game.game(gameScene, gameRoot, stage, scoreText);
    }
}
