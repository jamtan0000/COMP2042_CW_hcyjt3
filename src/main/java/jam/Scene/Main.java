package jam.Scene;

import jam.Account.Account;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

/**
 * This is used to start the menu page.
 */
public class Main extends Application {
    /**
     * This color object is used to set the background color, you can change default background color at here.
     */
    public static Color color = Color.GRAY;
    /**
     * Scene of the game.
     */
    private static Scene scene;

    /**
     * Getter
     * @return
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Getter of the scene.
     * @return return game scene.
     */
    public static Stage getStage() {
        return stage;
    }

    private static Stage stage;
    public static FileWriter writer;


    /**
     * This is Main of the program.
     *
     * @param primaryStage Main stage of the game.
     * @throws IOException IOError.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        readFile();
        scene = new Scene(loadFXML("menu"));
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setTitle("James' 2048");
        primaryStage.show();

        primaryStage.setOnCloseRequest(windowEvent -> {
            try {
                Main.exitEvent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * This is main of the program.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method is used to set the scene root by just calling this method.
     *
     * @param fxml Path of the fxml file.
     * @throws IOException IOError
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * This function load the given fxml path and return fxml parent.
     *
     * @param fxml path of the fxml file.
     * @return return root of given fxml and set as parent
     * @throws IOException IOError.
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * This method is used to read the text file in the path.
     *
     * @throws IOException IOError
     */
    public void readFile() throws IOException {

        //Here will check and create a txt file in the path below.

        String pathName = "src/main/resources/jam/txt/leaderBoardData.txt";
        File file = new File(pathName);
        file.createNewFile();

        //The file get from above will open and read at here.
        BufferedReader reader = new BufferedReader(new FileReader(pathName));
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into parts
            String[] parts = line.split(",");

            // Parse the values from the parts
            String userName = parts[0];
            Long score = Long.parseLong(parts[1]);

            Account.makeNewAccount(userName, score);
        }
    }

    /**
     * This is process for exit game by clicking in game button.
     * @throws IOException
     */
    public static void exitBut() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Dialog");
        alert.setHeaderText("Exit game");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Main.exitEvent();
        }
    }

    /**
     * This method is call when closing the program, such as close by clicking button or x of the window.
     * This method can consider as saving system for the leaderboard.
     * It will read the accounts in the account arraylist and write in the txt file.
     *
     * @throws IOException IOError.
     */
    public static void exitEvent() throws IOException {
        System.out.println("Y u closing me?（:へく）");
        String pathName = "src/main/resources/jam/txt/leaderBoardData.txt";
        writer = new FileWriter(pathName);
        for (Account ac : Account.accounts) {
            writer.write(ac.getUserName() + "," + ac.getScore() + "\n");
        }
        writer.close();
        System.exit(0);
    }
}
