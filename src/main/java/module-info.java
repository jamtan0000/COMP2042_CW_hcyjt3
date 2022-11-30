module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens jam.game to javafx.fxml;
    exports jam.game;
    exports jam.menu;
    opens jam.menu to javafx.fxml;
}