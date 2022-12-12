package jam.Controller;

import jam.Scene.GameScene;
import jam.Scene.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class gameController{

    private Stage stage;
    private Scene scene;
    @FXML
    private Button btnMenu;

    @FXML
    private Button btnReplay;

    @FXML
    private Group gameRoot;

    @FXML
    private Label score;

    @FXML
    private TextArea txtRule;


    @FXML
    void ClickReplay(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Replay");
        alert.setHeaderText("Game progress will not save. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            gameRoot.getChildren().clear();
            GameScene gameScene = new GameScene(stage, scene);
        }else {
            gameRoot.requestFocus();
        }

    }

    @FXML
    void clickMenu(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Back to menu");
        alert.setHeaderText("Game progress will not save. Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            gameRoot.getChildren().clear();
            Main.setRoot("menu");
        }else {
            gameRoot.requestFocus();
        }
    }


    @FXML
    void clickRule(MouseEvent event) {
        gameRoot.requestFocus();
    }

    public void initialize() {
        this.scene = Main.getScene();
        this.stage = (Stage) scene.getWindow();
        txtRule.setEditable(false);
    }


}
