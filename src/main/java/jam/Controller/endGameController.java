package jam.Controller;

import jam.Scene.Account;
import jam.Scene.Main;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Popup;

import java.io.IOException;
import java.util.Optional;

import static jam.Scene.Account.accountHaveBeenExist;
import static jam.Scene.Account.makeNewAccount;

public class endGameController {

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnQuit;

    @FXML
    private AnchorPane endGamePane;

    @FXML
    private Label scoreLabel = new Label();

    @FXML
    private Button btnSaveRecord;

    @FXML
    void clickMenu(MouseEvent event) throws IOException {
        Main.setRoot("menu");
    }

    @FXML
    void clickQuit(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Dialog");
        alert.setHeaderText("Quit game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Main.closeEvent();
        }
    }

    @FXML
    void clickSaveRecord(MouseEvent event) throws IOException {
        TextInputDialog pop = new TextInputDialog("");
        pop.setHeaderText("Put your name for record");
        Optional<String> result = pop.showAndWait();
        Long score = Long.parseLong(scoreLabel.getText());

        if (result.isPresent()){
            String userName = result.get();
            Account existAccount = accountHaveBeenExist(userName);
            if (userName.contains(",")||userName.isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println("Hey u no t ah");
                alert.setHeaderText("The score is not recorded.");
                alert.setContentText("User name cannot contain comma ',' or blank, please try again.");
                alert.showAndWait();
            }else {
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
                    System.out.println(ac.getAccounts());
                    System.out.println(userName + " " + score);
                }
            }
        }else {
            System.out.println("Something goes wrong, no result get from text field.");
        }
    }


    public void initialize() {
        endGamePane.setBackground(new Background(new BackgroundFill(Main.color, null,null)));


    }

}
