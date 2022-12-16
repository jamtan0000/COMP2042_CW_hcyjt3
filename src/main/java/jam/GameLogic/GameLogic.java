package jam.GameLogic;

import jam.Controller.menuController;
import jam.Scene.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 * Contain logic of how game can move.
 */
public class GameLogic {
    /**
     * Height of the game root.
     */
    private static int HEIGHT = 700;
    /**
     * Variable use to record whether win condition reached.
     */
    boolean winReached = false;
    /**
     * Number of the row of the grid.
     */
    private static int rowNum = menuController.rowNum;
    /**
     * Number of column of the grid.
     */
    private static int colNum = menuController.colNum;

    /**
     * Set number of row of the grid.
     *
     * @param row Given row.
     */
    public void setRowNum(int row) {
        rowNum = row;
    }

    /**
     * Set number of column of the grid.
     *
     * @param col Given column.
     */
    public void setColNum(int col) {
        colNum = col;
    }

    /**
     * Space distance between two cell.
     */
    private final static int distanceBetweenCells = 2;

    /**
     * Setter of length which is side of cell.
     *
     * @param cellsMax Get the bigger number between row and column.
     */
    public static void setLENGTH(int cellsMax) {
        LENGTH = (double) (HEIGHT - (cellsMax + 1) * distanceBetweenCells) / cellsMax;
    }

    /**
     * LENGTH is length of a side of a cell
     */
    private static double LENGTH = (HEIGHT - ((Math.max(menuController.rowNum, menuController.colNum) + 1) * distanceBetweenCells))
            / (double) Math.max(menuController.rowNum, menuController.colNum);

    /**
     * Make a single instance of the textmaker.
     */
    private TextMaker textMaker = TextMaker.getSingleInstance();

    /**
     * Cells which build up the grid.
     */
    private Cell[][] cells;
    /**
     * Root of the game.
     */
    private Group root;
    /**
     * Holding current scene.
     */
    private Scene scene;
    /**
     * Holding current stage.
     */
    private Stage stage;
    /**
     * Initial score of the game.
     */
    private long score = 0;

    /**
     * Getter for length of side of the cells.
     *
     * @return side length of cell.
     */
    static double getLENGTH() {
        return LENGTH;
    }

    /**
     * This function fill a random cell by 2 or 4.
     */
    private void randomFillNumber() {
        //This bunch of code detect the empty cell at the current grid and assign it to a same size empty grid.
        Cell[][] emptyCells = new Cell[rowNum][colNum];
        int a = 0;
        int b = 0;
        int aForBound = 0, bForBound = 0;
        outer:
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < colNum - 1) {
                        bForBound = b;
                        b++;
                    } else {
                        aForBound = a;
                        a++;
                        b = 0;
                        if (a == rowNum)
                            break outer;
                    }
                }
            }
        }

        //The code here chooses 2 or 4 by random and make a cell at the random empty cell.
        Text text;
        Random random = new Random();
        boolean putTwo = random.nextInt() % 2 != 0;
        int xCell, yCell;
        xCell = random.nextInt(aForBound + 1);
        yCell = random.nextInt(bForBound + 1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY());
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY());
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }


    /**
     * This method check through the cell for 0 and wining number.
     *
     * @return return 1 if got empty cell, return 0 when wining number is checked, return -1 if no empty cell found.
     */
    private int haveEmptyCell() {
        int r = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (cells[i][j].getNumber() == 0)
                    r = 1;
                if (cells[i][j].getNumber() == 2048 && !winReached) {
                    winReached = true;
                    return 0;
                }
            }
        }
        if (r == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * This method return the coordinate of an empty cell along the cell moving direction
     *
     * @param i      Cell object x coordinate
     * @param j      Cell object y coordinate
     * @param direct Direction of movement
     * @return　int variable which contain the target x or y coordinate.
     */
    private int passDestination(int i, int j, char direct) {
        int coordinate = j;

        /*
        This if statement check the left side cells of the current cell.
         When it meets an empty cell, it returns the y coordinate of the empty cell.
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

        /*
          This if statement check the right side cells of the current cell.
          When it meets an empty cell, it returns the y coordinate of the empty cell.
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
        /*
          This if statement check the downside cells of the current cell.
          When it meets an empty cell, it returns the x coordinate of the empty cell.
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
        /*
          This if statement check the upside cells of the current cell.
          When it meets an empty cell, it returns the x coordinate of the empty cell.
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

    /**
     * Change the position of the cell which can move to left.
     */
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

    /**
     * Change the position of the cell which can move to right.
     */
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

    /**
     * Change the position of the cell which can move to up.
     */
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

    /**
     * Change the position of the cell which can move to down.
     */
    private void moveDown() {
        for (int j = 0; j < colNum; j++) {
            for (int i = rowNum - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < rowNum; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * Check whether the given cell[i][j] can move to given horizontal destination or not.
     *
     * @param i    x coordinate of cell.
     * @param j    coordinate of cell.
     * @param des  Destination to pass.
     * @param sign direction of the pass.
     * @return Return true if can pass, false if cannot.
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < colNum && des + sign >= 0) {
            return cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0;
        }
        return false;
    }

    /**
     * Add the score and value of cell and delete the original cell if can merge, move cell position if can move.
     *
     * @param i    x coordinate of cell.
     * @param j    coordinate of cell.
     * @param des  Destination to pass.
     * @param sign direction of the pass.
     */
    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            score += cells[i][des + sign].getNumber();
            cells[i][des + sign].setModify(true);
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    /**
     * Check whether the given cell[i][j] can move to given vertically destination or not.
     *
     * @param i    x coordinate of cell.
     * @param j    coordinate of cell.
     * @param des  Destination to pass.
     * @param sign direction of the pass.
     * @return true if can pass, false if cannot.
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < rowNum && des + sign >= 0)
            return cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0;
        return false;
    }

    /**
     * Add the score and value of cell and delete the original cell if can merge, move cell position if can move.
     *
     * @param i    x coordinate of cell.
     * @param j    coordinate of cell.
     * @param des  Destination to pass.
     * @param sign direction of the pass.
     */
    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            score += cells[des + sign][j].getNumber();
            cells[des + sign][j].setModify(true);
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
    }

    /**
     * This method check whether there a same number cell at right side or downside.
     * Here have a bug about if the only cell which have number nearby is bottom-right cell, it will still consider as loss.
     *
     * @param i x coordinate for the cell which need to check.
     * @param j y coordinate for the cell which need to check.
     * @return Return true in have same number nearby, false if not.
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
     * This method check whether the game can move or not.
     *
     * @return false if game still can move, true if not.
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
     * This is a method which will switch to endGame scene and take the game score and assign in a lable in the endGame scene.
     */
    private void switchToEndGame() {
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
        scene.setRoot(endGameRoot);
        stage.setScene(scene);
    }

    /**
     * This function used to get array of number.
     *
     * @return Return an array of number which represent current grid status.
     */
    private String getGridAry() {
        int[] gridAry = new int[rowNum * colNum];
        int index = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                gridAry[index] = cells[i][j].getNumber();
                index++;
            }
        }
        return Arrays.toString(gridAry);
    }

    /**
     * This function handle the victory condition.
     * Popup a alert to check user want to continue or not.
     */
    private void victory() {
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
        randomFillNumber();
    }

    /**
     * This is main logic for the game.
     *
     * @param root      This is the root of the game which is center of the border pane.
     * @param scoreText This is label of the score which will update during each move.
     * @throws IOException IOError
     */
    public void game(Group root, Label scoreText) throws IOException {
        //Construct the class
        this.setColNum(menuController.colNum);
        this.setRowNum(menuController.rowNum);
        GameLogic.setLENGTH(Math.max(menuController.colNum, menuController.rowNum));
        cells = new Cell[rowNum][colNum];
        this.root = root;
        this.scene = Main.getScene();
        this.stage = Main.getStage();

        // Make the grid.
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }
        //Fill two random number.
        randomFillNumber();
        randomFillNumber();

        //These lines of code are used to return focus to the game.
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                    event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                event.consume();
            }
        });
        root.requestFocus();

        /*
        Key event handler of the game.
        Here the code how the game can move by arrow key.
        */
        root.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            Platform.runLater(() -> {

                //Get grid before move.
                String gridBefore = getGridAry();
                int haveEmptyCell;
                if (key.getCode() == KeyCode.DOWN) {
                    GameLogic.this.moveDown();
                } else if (key.getCode() == KeyCode.UP) {
                    GameLogic.this.moveUp();
                } else if (key.getCode() == KeyCode.LEFT) {
                    GameLogic.this.moveLeft();
                } else if (key.getCode() == KeyCode.RIGHT) {
                    GameLogic.this.moveRight();
                }

                //Get grid after move.
                String gridAfter = getGridAry();

                //Update score.
                scoreText.setText(score + "");
                haveEmptyCell = GameLogic.this.haveEmptyCell();

                //Check for winning and losing condition.
                if (haveEmptyCell == -1) {
                    if (GameLogic.this.canNotMove()) {
                        switchToEndGame();
                    }
                } else if (haveEmptyCell == 1 && key.getCode().isArrowKey() && !gridBefore.equals(gridAfter)) {
                    GameLogic.this.randomFillNumber();
                } else if (haveEmptyCell == 0) {
                    victory();
                }
            });
        });
    }

}