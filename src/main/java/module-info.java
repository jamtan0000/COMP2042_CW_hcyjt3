module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    exports jam.Controller;
    opens jam.Controller to javafx.fxml;
    exports jam.Scene;
    opens jam.Scene to javafx.fxml;
}