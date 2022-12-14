package jam.Scene;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class TextMaker {
    private static TextMaker singleInstance = null;

    private TextMaker() {

    }

    /**
     * This class make a only TextMaker Instance for the cell.
     * @return
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     *This class make the number text in each cell
     * @param input String input
     * @param xCell x location of the cell
     * @param yCell y location of the cell
     * @param root Group root
     * @return Text at a specific location
     */
    Text madeText(String input, double xCell, double yCell, Group root) {
        double length = GameLogic.getLENGTH();
        double fontSize = (3 * length) / 7.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);
        return text;
    }

    /**
     * This class change the text location to the location of its cell location
     * @param first
     * @param second
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
