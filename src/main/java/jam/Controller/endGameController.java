package jam.Controller;

import jam.Account.Account;
import jam.Scene.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.io.IOException;
import java.util.Optional;

import static jam.Account.Account.accountHaveBeenExist;
import static jam.Account.Account.makeNewAccount;

/**
 * This is controller for end game scene FXML.
 *
 * @author James Tang
 */
public class endGameController {

    @FXML
    private AnchorPane endGamePane;

    @FXML
    private Label scoreLabel = new Label();

    /**
     * Switch to menu page.
     *
     * @throws IOException
     */
    @FXML
    void clickMenu() throws IOException {
        Main.setRoot("menu");
    }

    /**
     * Go to exit process.
     *
     * @throws IOException
     */
    @FXML
    void clickQuit() throws IOException {
        Main.exitBut();
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void clickSaveRecord(MouseEvent event) throws IOException {
        TextInputDialog pop = new TextInputDialog("");
        pop.setHeaderText("Put your name for record");
        Optional<String> result = pop.showAndWait();
        Long score = Long.parseLong(scoreLabel.getText());

        if (result.isPresent()) {
            String userName = result.get();
            Account existAccount = accountHaveBeenExist(userName);
            if (userName.contains(",") || userName.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("The score is not recorded.");
                alert.setContentText("User name cannot contain comma ',' or blank, please try again.");
                alert.showAndWait();
            } else {
                if (existAccount != null) {
                    long scoreDif = Long.compare(score, existAccount.getScore());
                    if (scoreDif > 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Same Name");
                        alert.setHeaderText("There already have a same name with lower score in the leader bord.");
                        alert.setContentText("Do you want to update the score?");
                        Optional<ButtonType> update = alert.showAndWait();
                        if (update.get() == ButtonType.OK) {
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Same Name");
                            alert2.setHeaderText("Update successfully.");
                            alert2.showAndWait();
                            existAccount.setScore(score);
                        }
                    } else if (scoreDif < 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Same Name");
                        alert.setHeaderText("There already have a same name with higher score in the leader bord.");
                        alert.setContentText("You cannot replace the score.");
                        alert.showAndWait();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Same Name");
                        alert.setHeaderText("There already have a same name same score in the leader bord.");
                        alert.setContentText("You cannot replace the score.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Save");
                    alert.setHeaderText("Save successfully.");
                    alert.showAndWait();
                    Account ac = makeNewAccount(userName, score);
                }
            }
        } else {
            System.out.println("Something goes wrong, no result get from text field.");
        }
    }

    /**
     * Set the background color.
     */
    public void initialize() {
        endGamePane.setBackground(new Background(new BackgroundFill(Main.color, null, null)));

    }

}
