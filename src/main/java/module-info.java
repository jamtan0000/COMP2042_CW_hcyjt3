module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens jam.gameScene to javafx.fxml;
    exports jam.gameScene;
    exports jam.menuScene;
    opens jam.menuScene to javafx.fxml;
    exports jam.accountScene;
    opens jam.accountScene to javafx.fxml;
    exports jam.endGameScene;
    opens jam.endGameScene to javafx.fxml;
}