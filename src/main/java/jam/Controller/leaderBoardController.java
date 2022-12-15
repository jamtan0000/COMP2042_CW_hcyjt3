package jam.Controller;

import jam.Account.Account;
import jam.Scene.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the leaderboard FXML.
 */
public class leaderBoardController implements Initializable {

    @FXML
    private VBox leaderBoardVbox;

    @FXML
    private TableView<Account> Table;

    @FXML
    private TableColumn<Account, Long> score;

    @FXML
    private TableColumn<Account, String> userName;

    /**
     * This event is used to switch back to menu scene.
     *
     * @throws IOException IOError
     */
    @FXML
    void onMenuBtnClick() throws IOException {
        Main.setRoot("menu");
    }

    /**
     * This is used to initialize the background and also display the all the account from accounts array list on the table view.
     *
     * @param url Don't know what this use for but delete will give error.
     * @param resourceBundle Don't know what this use for but delete will give error.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaderBoardVbox.setBackground(new Background(new BackgroundFill(Main.color, null, null)));
        ObservableList<Account> acOL = FXCollections.observableArrayList(Account.accounts);
        score.setComparator(score.getComparator().reversed());
        userName.setCellValueFactory(new PropertyValueFactory<Account, String>("userName"));
        score.setCellValueFactory(new PropertyValueFactory<Account, Long>("score"));
        Table.setItems(acOL);
        Table.getSortOrder().add(score);

    }
}
