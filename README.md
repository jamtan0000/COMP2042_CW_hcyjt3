# COMP2042Coursework
 **2048**
 
**Student Name	:James Tang Tzeun Zhu**

**Student ID	:20417757**

To run this code, you need to have below requirment.
1. Have Intellij downloaded.
2. Have Javafx library.

Step to run code:
1. Download the src file
2. Create a javafx project in Intellij.
3. Replace the src file using download the src file.
4. Press the run button.

The javaDoc is in the path "src/jacaDoc".


**List of  features that are implemented and are working properly.**

**Functionallity in menu scene**
1. Player can play self defined grid by put the number at the row and column text field./n
2. Player can go to leaderboard to see a table which list out all the record of username and their score.
3. Player can change background color for the scene by using color picker.

**Saving functionallity of leaderboard**
1. Can read the file which record all the accounts when the programe sart and display the record in the table view.
2. Can go back to menu by click menu button in leaderboard scene.
3. Can display the user name and score.
 
**Functionallity at game scene**
1. Player can press "Restart" button to restart current game with same grid.
2. Player can press "Menu" button to return to menu.
3.The score is added correctly.
   
**Game logic**
1. The tile can spawn correctly, originally the tile is spawn even player press non-arrow key and also spawn even there no change of position for tile after user press the arrow key.
2. The tile can merge correctly, originally at the condition of have multiple pair of tile can merge in a row or column, it will only merge one pair of tile, as example (4,4,4,4) will become (0,4,4,8) but it should be (0,0,8,8).


**Wining of game**
1. Player will recieve a win message pop up win reached wining tile to let player decide whether player want to continue or not.
2. Player will go to end game secene if end game is click.
3. Player will go into endless mode until lose if player click continue.



**Functionallity in end game scene**
1. Player can record their score by clicking "Save record" button, it will request player to give a user name.
2. The user name and score will save and can be seen at the leaderboard scene.
3. Player can click "Menu" button to back to menu.
4. Player can click "Quit" button to exit game.



**Player input validation**
1. Make validation for player input for row and column.
2. Make validation for player given username in end game scene already exist in account array list.
   
   
**List of features that are implemented and are not working properly.**
1. The tile spawning problem.
The bottom right always get no chance to spawn at the start of the game, only when there is tile near the bottom right tile, it can get chance to spawn. I can be seen when restart the game many time. This is because at the randomFillNumber method in GameLogic, it will not get the coordinate of bottom right tile.
2. The number nearby check problem.
When the condition of all the tile cannot move but only bottom right tile have same number tile nearby, if player dont merge the tiles in next move, it will consider as loss, and bring to end game scene.
   
   
   example:
   If player have a 2x2 tile with current status is
   
   |2|4|  
   |8|4|
   
   The player should not reach lose condition if they press right.
   
   
**List of features, that are not implemented with an explanation of why they were not implemented.**
1. Initialy I want to put sound in my project but don't know why cannot add the javafx.media in the moduleInfo.
2. Initialy I try to make Junit test but I messed up by opening my project using Eclips, now there full of error at my local represitory. 
   So I not going to make Junit test and push to here. 

**New Java classes that introduced for the assignment**
1. endGameController
2. gameController
3. leaderBoardController
4. menuController
5. GameLogic (Split from original GameScene)
   
**Java classes that modified from the given code base**
1. Main (Make read txt file at the begining, and put close event handler which will write the file)
2. GameScene (Split to GameLogic and change many code in GameLogic)
3. Account (Adjust makeNewAccount)
4. Cell (Just add in the setArcHeight andsetArcWidth)
   
**Error might occour**

Player may meet error if they try to change the text in leaderBroad.txt file.
