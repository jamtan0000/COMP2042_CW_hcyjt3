package jam.accountScene;

import jam.menuScene.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class accountController {

    @FXML
    private Button btnMenu;

    @FXML
    private Label welcomeText;

    @FXML
    void onMenuBtnClick(ActionEvent event) throws IOException {
        Main.setRoot("/jam/menuScene/menu");
    }

}
