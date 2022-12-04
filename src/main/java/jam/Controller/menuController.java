package jam.Controller;

import jam.Scene.*;

import jam.Scene.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class menuController {

    private Group gameRoot = new Group();

    public static Scene scene;

    //private Stage primaryStage;

    private Scene gameScene = new Scene(gameRoot, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @FXML
    private Button btnExit;

    @FXML
    private Button btnGame;

    @FXML
    private Button btnLeaderboard;

    @FXML
    void btnGameClick(ActionEvent event) throws IOException {
        Group endgameRoot = new Group();
        Scene endGameScene = new Scene(endgameRoot, 900, 900, Color.rgb(250, 20, 100, 0.2));
        Group gameRoot = new Group();
        setGameRoot(gameRoot);
        Scene gameScene = new Scene(gameRoot, 900, 900, Color.rgb(189, 177, 92));
        setGameScene(gameScene);
        GameScene game = new GameScene();

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(gameScene);
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
        primaryStage.show();

        //Main.setRoot("/jam/gameScene/game");

    }

    @FXML
    void btnLeaderBoardClick(ActionEvent event) throws IOException {
        Main.setRoot("/jam/accountScene/account");
    }

    @FXML
    void btnExitClick(ActionEvent event) {
        Platform.exit();
    }


}
