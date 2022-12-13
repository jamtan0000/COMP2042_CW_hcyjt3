package jam.Scene;

import jam.Controller.menuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main extends Application {


    public static Color color = Color.WHITE;
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    private Group gameRoot = new Group();
    private static Scene scene;
    public static FileWriter writer;
    public static Scene getScene() {
        return scene;
    }
    private Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));

    private static Scanner input= new Scanner(System.in);
    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String pathName = "src/main/resources/jam/txt/leaderBoardData.txt";
        File file = new File(pathName);
        file.createNewFile();
        //FileReader reader = new FileReader(pathName);


        // Open the file
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(",");

                // Parse the values from the parts
                String userName = parts[0];
                Long score = Long.parseLong(parts[1]);

                Account.makeNewAccount(userName,score);
            }
        } catch (IOException e) {
            // Handle the exception
        }

        /*Collection<Account> list = Files.readAllLines(new File("leaderboard").toPath()).stream().map(line -> {
            String[] details = line.split(",");
            Account cd = new Account();
            cd.Account(details[0]);
            cd.Account(details[1])
            return cd;
        }).collect(Collectors.toList());*/
        /*Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT);
        Group accountRoot = new Group();
        Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20, 100, 0.2));
        Group getAccountRoot = new Group();
        Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT, Color.rgb(200, 20, 100, 0.2));
        */
        /*Group rankRoot = new Group();
        Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120, 0.3));
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);


        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(WIDTH / 2 - 120);
        backgroundOfMenu.setY(180);
        menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        accountRoot.getChildren().add(backgroundOfMenuForPlay);*/

        scene = new Scene(loadFXML("menu"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("James' 2048");
        primaryStage.show();

        /*Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.showAndWait();*/

        /*Label label = new Label("This is a Popup");
        Popup popup = new Popup();
        label.setStyle(" -fx-background-color: white;");
        popup.getContent().add(label);
        popup.show(primaryStage);*/

        primaryStage.setOnCloseRequest(windowEvent -> {
            try {
                Main.closeEvent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
        }

    public static Parent loadFXML (String fxml) throws IOException {
        //System.out.println(Main.class.getResource(fxml + ".fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void closeEvent() throws IOException {
        System.out.println("Y u closing me?（:へく）");
        String pathName = "src/main/resources/jam/txt/leaderBoardData.txt";
        writer = new FileWriter(pathName);
        for(Account ac:Account.accounts){
            writer.write(ac.getUserName()+","+ac.getScore()+"\n");
        }
        writer.close();
        System.exit(0);
    }

    /*public static void changeColor(Parent root){
        menuVbox.setBackground(new Background(new BackgroundFill(Main.color, null,null)));

    }*/
}
