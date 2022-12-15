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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

public class menuController {
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField rowText;

    public static void setRowNum(int rowNum) {
        menuController.rowNum = rowNum;
    }

    public static void setColNum(int colNum) {
        menuController.colNum = colNum;
    }

    public static int rowNum;
    @FXML
    private TextField colText;
    public static int colNum;
    @FXML
    public ColorPicker colorPicker;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnGame;

    @FXML
    private Button btnLeaderboard;

    @FXML
    private VBox menuVbox;

    @FXML
    void ClickExit(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Dialog");
        alert.setHeaderText("Exit game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Main.closeEvent();
        }
    }

    @FXML
    void ClickPlayGame(MouseEvent event) throws IOException {
        if(checkIsInt(rowText.getText()) && checkIsInt(colText.getText())){
            Stage stage = (Stage) btnGame.getScene().getWindow();
            Scene scene = btnGame.getScene();
            setRowNum(Integer.parseInt(rowText.getText()));
            setColNum(Integer.parseInt(colText.getText()));
            GameScene gameScene = new GameScene(stage, scene);
        }
    }

    @FXML
    void ClickLeaderboard(MouseEvent event) throws IOException {
        Main.setRoot("leaderBoard");

    }

    @FXML
    void actionColorPicker(ActionEvent event) {
        Main.color = colorPicker.getValue();
        menuVbox.setBackground(new Background(new BackgroundFill(Main.color, null, null)));
    }

    public void initialize() {
        colorPicker.setValue(Main.color);
        menuVbox.setBackground(new Background(new BackgroundFill(Main.color, null, null)));
    }

    public boolean checkIsInt(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");
        alert.setContentText("Please input a positive integer");
        try{
            if(Math.signum(Integer.parseInt(text))!=1){
                alert.setHeaderText(text + " is not a positive integer");
                alert.showAndWait();
                return false;
            }
            return true;
        }
        catch (NumberFormatException e){
            alert.setHeaderText(text + " is not a valid integer");
            alert.showAndWait();
            return false;
        }
    }
}
