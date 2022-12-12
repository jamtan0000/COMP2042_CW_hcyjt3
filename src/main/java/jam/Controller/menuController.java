package jam.Controller;


import jam.Scene.GameScene;
import jam.Scene.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

public class menuController {
    private Scene scene;
    private Stage stage;
    @FXML
    private Button btnExit;

    @FXML
    private Button btnGame;

    @FXML
    private Button btnLeaderboard;

    @FXML
    void ClickExit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Dialog");
        alert.setHeaderText("Exit game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //root.getChildren().clear();
            System.exit(0);
        }
    }

    @FXML
    void ClickGame(MouseEvent event) throws IOException {
        Stage stage = (Stage) btnGame.getScene().getWindow();
        Scene scene = btnGame.getScene();
        GameScene gameScene = new GameScene(stage, scene);
    }

    @FXML
    void ClickLeaderboard(MouseEvent event) throws IOException {
        Main.setRoot("account");

    }
    public void initialize(){
        /*this.scene = Main.getScene();
        this.stage = (Stage) scene.getWindow();*/

    }

}

