package jam.Scene;

import jam.Controller.menuController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;


public class GameLogic{

    public static void setHEIGHT(int high) {
        HEIGHT = high;
    }

    private static int HEIGHT = 700;
    private static int n = 4;
    boolean winReached = false;
    private static int colNum = menuController.colNum;
    private static int rowNum = menuController.rowNum;
    public void setColNum(int col) {
        colNum = col;
    }
    public void setRowNum(int row) {
        rowNum = row;
    }

    /**
     *Set n with is grid of the game
     * @param number int
     */
    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    private final static int distanceBetweenCells = 2;

    public static void setLENGTH(int cellsMax) {
        LENGTH = (double) (HEIGHT - (cellsMax + 1) * distanceBetweenCells)/ cellsMax;
    }

    /**
     * LENGTH is length of a side of a cell
     */
    private static double LENGTH = (HEIGHT - ((Math.max(menuController.rowNum,menuController.colNum) + 1) * distanceBetweenCells))
            / (double) Math.max(menuController.rowNum,menuController.colNum);
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells;
    private Group root;
    private Scene scene;
    private Stage stage;
    private long score = 0;
    private Label scoreText;

    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * This function fill a random cell by 2 or 4
     * @param turn
     */
    private void randomFillNumber(int turn) {
        /**
         * This bunch of code detect the empty cell at the current grid and assign it to a same size empty grid.
         */
        Cell[][] emptyCells = new Cell[rowNum][colNum];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    //System.out.println(emptyCells[a][b]+"<>"+cells[i][j]);
                    //System.out.println(b);
                    if (b < colNum-1) {
                        //System.out.println(b+"p");
                        bForBound=b;
                        b++;
                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==rowNum)
                            break outer;
                    }
                }
                //System.out.println(i+" "+j+" "+a+" "+b+" "+aForBound+" "+bForBound);
            }
        }

        /**
         * The code here chooses 2 or 4 by random and make a cell at the random empty cell.
         */
        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        //System.out.println(a+" "+b+" "+aForBound+" "+bForBound+"<<");
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        /*System.out.println(a+" "+b+" "+aForBound+" "+bForBound);
        System.out.println("------------------------------------");*/
        if (putTwo) {
            /*System.out.println("2  xcell "+xCell);
            System.out.println("ycell "+yCell);
            System.out.println(emptyCells[xCell][yCell].getX());
            System.out.println(emptyCells[xCell][yCell].getY());*/
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            /*System.out.println("4  xcell "+xCell);
            System.out.println("ycell "+yCell);
            System.out.println(emptyCells[xCell][yCell].getX());
            System.out.println(emptyCells[xCell][yCell].getY());*/
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);

            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }


    /**
     * This method check through the cell for 0 and wining number.
     * @return return 1 if got empty cell, return 0 when wining number is checked.
     */

    private int  haveEmptyCell() {
        int r = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (cells[i][j].getNumber() == 0)
                    r = 1;
                if(cells[i][j].getNumber() == 2048 && !winReached) {
                    winReached = true;
                    return 0;
                }
            }
        }
        if(r == 1){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * This method return the coordinate of an empty cell along the cell moving direction
     * @param i Cell object x coordinate
     * @param j Cell object y coordinate
     * @param direct    Direction of movement
     * @return　int variable which contain the target x or y coordinate.
     */
    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        /**
         * This if statement check the left side cells of the current cell.
         * When it meets a empty cell, it returns the y coordinate of the empty cell.
         */
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        /**
         * This if statement check the right side cells of the current cell.
         * When it meets a empty cell, it returns the y coordinate of the empty cell.
         */
        if (direct == 'r') {
            for (int k = j + 1; k <= colNum - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == colNum - 1) {
                    coordinate = colNum - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        /**
         * This if statement check the downside cells of the current cell.
         * When it meets a empty cell, it returns the x coordinate of the empty cell.
         */
        if (direct == 'd') {
            for (int k = i + 1; k <= rowNum - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == rowNum - 1) {
                    coordinate = rowNum - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        /**
         * This if statement check the upside cells of the current cell.
         * When it meets a empty cell, it returns the x coordinate of the empty cell.
         */
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    private void moveLeft() {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < colNum; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    private void moveRight() {
        for (int i = 0; i < rowNum; i++) {
            for (int j = colNum - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < colNum; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    private void moveUp() {
        for (int j = 0; j < colNum; j++) {
            for (int i = 1; i < rowNum; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < rowNum; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    private void moveDown() {
        System.out.println("MD");
        for (int j = 0; j < colNum; j++) {
            for (int i = rowNum - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < rowNum; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < colNum && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            score += cells[i][des + sign].getNumber();
            cells[i][des + sign].setModify(true);
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }else{
        }
    }

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < rowNum && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            score += cells[des + sign][j].getNumber();
            cells[des + sign][j].setModify(true);
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }else{
        }
    }

    /**
     * This method check whether there a same number cell nearby.
     * Here have a bug about if the only cell which have number nearby is bottom-right cell, it will still consider as loss.
     * @param i
     * @param j
     * @return
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < rowNum - 1 && j < colNum - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * This method check whether there is a cell have same numbed cell nearby.
     * @return false if have same numbed cell nearby, true if no same numbed cell nearby.
     */
    private boolean canNotMove() {
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This is a method which will with to endGame scene and take the game score and assign in a lable in the endGame scene.
     */
    private void switchToEndGame(){
        root.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("endGameScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent endGameRoot = loader.getRoot();
        Label ensGameScore = (Label) endGameRoot.lookup("#scoreLabel");
        ensGameScore.setText(String.valueOf(this.score));
        Scene scene = Main.getScene();
        scene.setRoot(endGameRoot);
        stage.setScene(scene);
    }

    private String getGridAry(){
        int[] gridAry = new int[rowNum*colNum];
        int index = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                gridAry[index] = cells[i][j].getNumber();
                index++;
            }
        }
        return Arrays.toString(gridAry);
    }
    private void victory(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Win");
        alert.setHeaderText("You win!!");
        alert.setContentText("Do you want to continue or end game?");
        Button conBut = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        conBut.setText("Continue");
        Button endBut = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        endBut.setText("End game");
        Optional<ButtonType> update = alert.showAndWait();
        if (update.get() == ButtonType.CANCEL) {
            switchToEndGame();
        }
        GameLogic.this.randomFillNumber(2);
    }
    public void game(Scene gameScene, Group root, Stage stage, Label scoreText) throws IOException {

        this.setColNum(menuController.colNum);
        this.setRowNum(menuController.rowNum);
        //GameLogic.setHEIGHT((int) ((Node) ((BorderPane) gameScene.getRoot()).getCenter()).getBoundsInLocal().getHeight());
        GameLogic.setLENGTH(Math.max(menuController.colNum, menuController.rowNum));
        cells = new Cell[rowNum][colNum];
        System.out.println(rowNum+"<>"+colNum);
        this.stage = stage;
        this.root = root;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }

        randomFillNumber(1);
        randomFillNumber(1);

        gameScene.setOnKeyPressed(event -> {
            // Check if the event is for an arrow key
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                    event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                // Consume the event to prevent it from being propagated
                event.consume();
            }
        });
        root.requestFocus();
        root.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                int haveEmptyCell;
                String gridBefore = getGridAry();
                if (key.getCode() == KeyCode.DOWN) {
                    GameLogic.this.moveDown();
                } else if (key.getCode() == KeyCode.UP) {
                    GameLogic.this.moveUp();
                } else if (key.getCode() == KeyCode.LEFT) {
                    GameLogic.this.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT) {
                    GameLogic.this.moveRight();
                }
                String gridAfter = getGridAry();
                scoreText.setText(score + "");
                haveEmptyCell = GameLogic.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (GameLogic.this.canNotMove()) {
                        switchToEndGame();
                    }
                } else if (haveEmptyCell == 1 && key.getCode().isArrowKey() &&  !gridBefore.equals(gridAfter)){
                    GameLogic.this.randomFillNumber(2);
                } else if(haveEmptyCell == 0){
                    victory();
                }

            });
        });
    }

}