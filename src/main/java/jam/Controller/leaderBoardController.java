package jam.Controller;

import jam.Scene.Account;
import jam.Scene.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class leaderBoardController implements Initializable {

    @FXML
    private Button btnMenu;

    @FXML
    private TableView<Account> Table;

    @FXML
    private TableColumn<Account, Long> score;

    @FXML
    private TableColumn<Account, String> userName;

    @FXML
    private Label welcomeText;

    @FXML
    void onMenuBtnClick(ActionEvent event) throws IOException {
        Main.setRoot("/jam/Scene/menu");
    }

    /*public void initialize() throws IOException {
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Account> acOL = FXCollections.observableArrayList(Account.accounts);
        score.setComparator(score.getComparator().reversed());
        userName.setCellValueFactory(new PropertyValueFactory<Account,String>("userName"));
        score.setCellValueFactory(new PropertyValueFactory<Account,Long>("score"));
        Table.setItems(acOL);
        Table.getSortOrder().add(score);

    }
}
