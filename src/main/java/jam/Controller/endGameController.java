package jam.Controller;

import jam.Scene.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class endGameController {

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnQuit;

    @FXML
    private Label score = new Label();

    @FXML
    void clickMenu(MouseEvent event) throws IOException {
        Main.setRoot("menu");
    }

    @FXML
    void clickQuit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Dialog");
        alert.setHeaderText("Quit game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //root.getChildren().clear();
            System.exit(0);
        }
    }

    public void initialize() {

    }

    /*public endGameController(String score) {
        this.score.setText(score);
    }*/

}
