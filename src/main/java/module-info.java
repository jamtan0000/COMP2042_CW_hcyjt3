module jam {
    requires javafx.controls;
    requires javafx.fxml;


    exports jam.Controller;
    opens jam.Controller to javafx.fxml;
    exports jam.Scene;
    opens jam.Scene to javafx.fxml;
    exports jam.GameLogic;
    opens jam.GameLogic to javafx.fxml;
    exports jam.Account;
    opens jam.Account to javafx.fxml;
}