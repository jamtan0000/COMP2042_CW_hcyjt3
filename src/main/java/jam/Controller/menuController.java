package jam.Controller;


import jam.Scene.GameScene;
import jam.Scene.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

/**
 * This is controller for the menu fxml.
 */
public class menuController {
    /**
     * Text field which contain user input for row.
     */
    @FXML
    private TextField rowText;

    /**
     * Text field which contain user input for column.
     */
    @FXML
    private TextField colText;
    /**
     * Input of number of row.
     */
    public static int rowNum;
    /**
     * Input of number of column.
     */
    public static int colNum;

    public static void setRowNum(int row) {
        rowNum = row;
    }

    public static void setColNum(int col) {
        colNum = col;
    }

    @FXML
    public ColorPicker colorPicker;

    @FXML
    private VBox menuVbox;

    /**
     * Calling exit procedure.
     *
     * @throws IOException IOError.
     */
    @FXML
    void ClickExit() throws IOException {
        Main.exitBut();
    }

    /**
     * Event for clicking play game button.
     * This will start the game.
     *
     * @throws IOException IOError.
     */
    @FXML
    void ClickPlayGame() throws IOException {
        if (inputValidation(rowText.getText(), colText.getText())) {
            setRowNum(Integer.parseInt(rowText.getText()));
            setColNum(Integer.parseInt(colText.getText()));
            GameScene gameScene = new GameScene();
        }
    }

    /**
     * This method is used to change scene to leaderboard.
     *
     * @throws IOException IOError.
     */
    @FXML
    void ClickLeaderboard() throws IOException {
        Main.setRoot("leaderBoard");
    }

    /**
     * This method is used to change color of the background.
     */
    @FXML
    void actionColorPicker() {
        Main.color = colorPicker.getValue();
        menuVbox.setBackground(new Background(new BackgroundFill(Main.color, null, null)));
    }

    /**
     * This function is used to validate the user input of number of row and column.
     *
     * @param text1 This is text of user row input.
     * @param text2 This is text of user column input.
     * @return return false when not valid,it will stay at menu scene, return true to start the game.
     */
    public boolean inputValidation(String text1, String text2) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid input");
        alert.setContentText("Please input a positive integer");
        try {
            int row = Integer.parseInt(text1);
            int col = Integer.parseInt(text2);
            if (Math.signum(row) != 1 && Math.signum(col) != 1) {
                alert.setHeaderText("At least one of the input is not positive integer");
                alert.showAndWait();
                return false;
            }
            if (row == 1 && col == 1) {
                alert.setHeaderText("The smallest grid should be 1x2 or 2x1.");
                alert.setContentText("Please give a bigger number.");
                alert.showAndWait();
                return false;
            }
            if (row > 15 || col > 15) {
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Big number.");
                alert.setHeaderText("Your input number is lager than 15.");
                alert.setContentText("It may cause hard to see, lagging or game crash \nAre you still want to proceed?");
                Button yesBut = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                yesBut.setText("Yes");
                Button noBut = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
                noBut.setText("No");

                Optional<ButtonType> conf = alert.showAndWait();
                if (conf.get() == ButtonType.OK) {
                    return true;
                } else if (conf.get() == ButtonType.CANCEL) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            alert.setHeaderText("At least one of your input is not a valid integer");
            alert.showAndWait();
            return false;
        }
    }

    /**
     * This method is used to initialize the background color and make the colour picker text bigger.
     */
    public void initialize() {
        colorPicker.setValue(Main.color);
        colorPicker.setStyle("-fx-font-size: 32;");
        menuVbox.setBackground(new Background(new BackgroundFill(Main.color, null, null)));
    }
}

