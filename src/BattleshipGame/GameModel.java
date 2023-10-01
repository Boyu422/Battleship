/**
 * File name: GameModel
 * identification: Boyu Li 041003345
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A22
 * Professor: Paulo Sousa
 * Date: 7/9/2023
 * Compiler: Oracle Open JDK - version: 20.0.1 2023-04-18
 * Purpose: To stores and manipulate the game data
 */

package BattleshipGame;

import javax.swing.*;
import java.util.Random;

/**
 * Class name: GameModel
 * Methods list:
 * 1. GameModel (Overload Constructor)
 * 2. getGameDimension
 * 3. setGameDimension
 * 4. setGameTime
 * 5. getGameTime
 * 6. getMachineLife
 * 7. setMachineLife
 * 8. getPlayerLife
 * 9. setPlayerLife
 * 10. getLangOption
 * 11. setLangOption
 * 12. checkGameData
 * 13. updateGameData
 * 14. checkConfigurationData
 * 15. updateConfigurationData
 * 16. initializeData
 * 17. createRanShipForPlayer
 * 18. createRanShipForMachine
 * 19. generateRanShips
 * 20. generateOneShip
 * 21. calculateLife
 * 22. cleanGameData
 * 23. newGame
 * 24. calculateDimension
 * 25. isHit
 * Constants list: NULL
 * Purpose: The class stores and manipulate all the game data
 * 
 * @author Boyu Li
 * @version 1.0
 * @see java.util.Random
 * @see javax.swing
 * @since 20.0.1
 */
public class GameModel {

    /**
     * Method name: GameModel
     * Purpose: Overload constructor
     * Algorithm: no
     */
    GameModel() {
    }

    /**
     * The game dimension data
     */
    private int gameDimension = 5;

    /**
     * The game time data
     */
    private int gameTime = 0;

    /**
     * The player life data
     */
    private int playerLife;

    /**
     * The machine life data
     */
    private int machineLife;

    /**
     * The ship configuration data of player
     */
    private int[][] shipConfigurationPlayer;

    /**
     * The ship configuration data of machine
     */
    private int[][] shipConfigurationMachine;

    /**
     * The game running data of the human player
     */
    private int[][] gameDataPlayer;

    /**
     * The game running game of the machine player
     */
    private int[][] gameDataMachine;

    /**
     * The language option data
     */
    private int langOption;

    private String sConfig;

    /**
     * Method name: getGameDimension
     * Purpose: getter for attribute gameDimension
     * 
     * @return gameDimension attribute
     */
    public int getGameDimension() {
        return gameDimension;
    }

    /**
     * Method name: setGameDimension
     * Purpose: setter for attribute gameDimension
     * 
     * @param gameDimension - the parameter of game dimension
     */
    public void setGameDimension(int gameDimension) {
        this.gameDimension = gameDimension;
    }

    /**
     * Method name: setGameTime
     * Purpose: setter for attribute gameTime
     * 
     * @param gameTime - the parameter of game time
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * Method name: getGameTime
     * Purpose: getter for attribute gameTime
     * 
     * @return gameTime attribute
     */
    public int getGameTime() {
        return gameTime;
    }

    /**
     * Method name: getMachineLife
     * Purpose: getter for attribute machineLife
     * 
     * @return machineLife attribute
     */
    public int getMachineLife() {
        return machineLife;
    }

    /**
     * Method name: setMachineLife
     * Purpose: setter for attribute machineLife
     * 
     * @param machineLife - the parameter of machine life
     */
    public void setMachineLife(int machineLife) {
        this.machineLife = machineLife;
    }

    /**
     * Method name: getPlayerLife
     * Purpose: getter for attribute playerLife
     * 
     * @return playerLife attribute
     */
    public int getPlayerLife() {
        return playerLife;
    }

    /**
     * Method name: setPlayerLife
     * Purpose: setter for attribute playerLife
     * 
     * @param playerLife - the parameter of player life
     */
    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }

    /**
     * Method name: getLangOption
     * Purpose: getter for attribute langOption
     * 
     * @return langOption attribute
     */
    public int getLangOption() {
        return langOption;
    }

    /**
     * Method name: setLangOption
     * Purpose: setter for attribute langOption
     * 
     * @param langOption - the parameter of life option
     */
    public void setLangOption(int langOption) {
        this.langOption = langOption;
    }

    /**
     * Method name: checkGameData
     * Purpose: To check the data of game data array
     * Algorithm: no
     * 
     * @param isPlayer  - the boolean indicates if the checking target is player
     * @param positionX - the position of square on x-axis
     * @param positionY - the position of square on y-axis
     * @return the int that indicates the state of that square
     */
    public int checkGameData(int positionX, int positionY, boolean isPlayer) {
        if (isPlayer)
            return gameDataPlayer[positionX][positionY];
        return gameDataMachine[positionX][positionY];
    }

    /**
     * Method name: updateGameData
     * Purpose: To update a value on the game data array
     * Algorithm: no
     * 
     * @param positionX - the position of square on x-axis
     * @param positionY - the position of square on y-axis
     * @param isPlayer  - the boolean indicates if the checking target is player
     * @param value     - the value that will be the newest value
     */
    public void updateGameData(int positionX, int positionY, boolean isPlayer, int value) {
        if (isPlayer) {
            gameDataPlayer[positionX][positionY] = value;
        } else {
            gameDataMachine[positionX][positionY] = value;
        }
    }

    /**
     * Method name: checkConfigurationData
     * Purpose: To check the data of game configuration array
     * Algorithm: no
     * 
     * @param positionX - the position of square on x-axis
     * @param positionY - the position of square on y-axis
     * @param isPlayer  - the boolean indicates if the checking target is player
     * @return the int that indicates the value(size) of that square
     */
    public int checkConfigurationData(int positionX, int positionY, boolean isPlayer) {
        if (isPlayer)
            return shipConfigurationPlayer[positionX][positionY];
        return shipConfigurationMachine[positionX][positionY];
    }

    /**
     * Method name: updateConfigurationData
     * Purpose: To update a value on the configuration array
     * Algorithm: no
     * 
     * @param positionX - the position of square on x-axis
     * @param positionY - the position of square on y-axis
     * @param isPlayer  - the boolean indicates if the checking target is player
     * @param value     - the value(size) that will be the newest value
     */
    public void updateConfigurationData(int positionX, int positionY, boolean isPlayer, int value) {
        if (isPlayer) {
            shipConfigurationPlayer[positionX][positionY] = value;
        } else {
            shipConfigurationMachine[positionX][positionY] = value;
        }
    }

    /**
     * Method name: initializeData
     * Purpose: set the values on the array of game data and array of configuration
     * data to zero
     * Algorithm: no
     */
    public void initializeData() {
        gameDataPlayer = new int[gameDimension * 2][gameDimension * 2];
        gameDataMachine = new int[gameDimension * 2][gameDimension * 2];
        shipConfigurationPlayer = new int[gameDimension * 2][gameDimension * 2];
        shipConfigurationMachine = new int[gameDimension * 2][gameDimension * 2];
        for (int i = 0; i < gameDimension * 2; i++) {
            for (int j = 0; j < gameDimension * 2; j++) {
                gameDataMachine[i][j] = 0;
                gameDataPlayer[i][j] = 0;
                shipConfigurationMachine[i][j] = 0;
                shipConfigurationPlayer[i][j] = 0;
            }
        }
    }

    /**
     * Method name: createRanShipForPlayer
     * Purpose: randomise ships for human player
     * Algorithm: Randomise positions by invoke the component on random package
     * 
     * @param size - the size of a ship
     */
    private void createRanShipForPlayer(int size) {
        Random random;
        boolean isSuccess;
        do {
            // If the size is out of board, then break the current loop and regenerate
            isSuccess = true;
            random = new Random();
            boolean isVertical = random.nextBoolean();
            int randRow, randCol;
            randRow = random.nextInt(gameDimension * 2);
            randCol = random.nextInt(gameDimension * 2);
            if (randRow + size > gameDimension * 2 - 1 || randCol + size > gameDimension * 2 - 1) {
                isSuccess = false;
                continue;
            }
            if (isVertical) {
                // If the place was occupied, then break the current loop and regenerate
                for (int pos = 1; pos <= size; pos++) {
                    // if (gameModel.checkConfigurationData(randRow, randCol + pos, true) != 0) {
                    if (shipConfigurationPlayer[randRow][randCol + pos] != 0) {
                        isSuccess = false;
                    }
                }
                if (!isSuccess)
                    continue;
                // If the place is reasonable the place the ship
                for (int pos = 1; pos <= size; pos++) {
                    // gameModel.updateConfigurationData(randRow, randCol + pos, true, size);
                    shipConfigurationPlayer[randRow][randCol + pos] = size;
                }
            } else {
                for (int pos = 1; pos <= size; pos++) {
                    // if (gameModel.checkConfigurationData(randRow + pos, randCol, true) != 0) {
                    if (shipConfigurationPlayer[randRow + pos][randCol] != 0) {
                        isSuccess = false;
                    }
                }
                if (!isSuccess)
                    continue;
                for (int pos = 1; pos <= size; pos++) {
                    // gameModel.updateConfigurationData(randRow + pos, randCol, true, size);
                    shipConfigurationPlayer[randRow + pos][randCol] = size;
                }
            }
        } while (!isSuccess);
    }

    /**
     * Method name: createRanShipForMachine
     * Purpose: randomise ships for machine player
     * Algorithm: Randomise positions by invoke the component on random package
     * 
     * @param size - the size of a ship
     */
    private void createRanShipForMachine(int size) {
        Random random;
        boolean isSuccess;
        do {
            isSuccess = true;
            random = new Random();
            boolean isVertical = random.nextBoolean();
            int randRow, randCol;
            randRow = random.nextInt(gameDimension * 2);
            randCol = random.nextInt(gameDimension * 2);
            if (randRow + size > gameDimension * 2 - 1 || randCol + size > gameDimension * 2 - 1) {
                isSuccess = false;
                continue;
            }
            if (isVertical) {
                for (int pos = 1; pos <= size; pos++) {
                    // if (gameModel.checkConfigurationData(randRow, randCol + pos, false) != 0) {
                    if (shipConfigurationMachine[randRow][randCol + pos] != 0) {
                        isSuccess = false;
                    }
                }
                if (!isSuccess)
                    continue;
                for (int pos = 1; pos <= size; pos++) {
                    // gameModel.updateConfigurationData(randRow, randCol + pos, false, size);
                    shipConfigurationMachine[randRow][randCol + pos] = size;
                }
            } else {
                for (int pos = 1; pos <= size; pos++) {
                    // if (gameModel.checkConfigurationData(randRow + pos, randCol, false) != 0) {
                    if (shipConfigurationMachine[randRow + pos][randCol] != 0) {
                        isSuccess = false;
                    }
                }
                if (!isSuccess)
                    continue;
                for (int pos = 1; pos <= size; pos++) {
                    // gameModel.updateConfigurationData(randRow + pos, randCol, false, size);
                    shipConfigurationMachine[randRow + pos][randCol] = size;
                }
            }
        } while (!isSuccess);
    }

    /**
     * Method name: generateRanShips
     * Purpose: randomise ships for either human player or machine player
     * Algorithm: The algorithm that professor gave
     * 
     * @param isPlayer - the boolean value indicates whether the target is machine
     *                 or human player
     */
    public void generateRanShips(boolean isPlayer) {
        for (int i = gameDimension; i > 0; i--) {
            for (int j = 1; j < gameDimension - i + 2; j++) {
                if (isPlayer) {
                    createRanShipForPlayer(i);
                } else {
                    createRanShipForMachine(i);
                }
            }
        }
    }

    /**
     * Method name: generateOneShip
     * Purpose: generate a ship for user player based on the user selected position
     * Algorithm: Validating the legibility of the user selected position and then
     * generate the ship
     * 
     * @param size       - the size of a ship
     * @param row        - the position of row of a user selected ship
     * @param col        - the position of col of a user selected ship
     * @param isVertical - the boolean indicates if the direction of the ship is
     *                   vertical
     * @param gameView   - GameView object
     * @param remains    - the array that holds the remains of each ship
     */
    public void generateOneShip(int size, int row, int col, boolean isVertical, GameView gameView, int[] remains) {
        // Check if the ship has remains
        if (remains[size - 1] == 0) {
            JOptionPane.showMessageDialog(gameView.designModeDialog, "No Ship Remains",
                    "Sorry, there is no ship remains, please try another size of ship!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isVertical) {
            // Check if the ship is out of board
            if (row + size - 1 > gameDimension * 2 - 1) {
                JOptionPane.showMessageDialog(gameView.designModeDialog, "Ship Placement Error",
                        "Sorry, you can't place the ship at that place, please try again!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the place(s) have already been occupied
            for (int pos = 0; pos <= size - 1; pos++) {
                // if (gameModel.checkConfigurationData(row + pos, col, true) != 0) {
                if (shipConfigurationPlayer[row + pos][col] != 0) {
                    JOptionPane.showMessageDialog(gameView.designModeDialog, "Ship Placement Error",
                            "Sorry, you can't place the ship at that place, please try again!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // If the place is reasonable, then place the ship
            for (int pos = 0; pos <= size - 1; pos++) {
                // gameModel.updateConfigurationData(row + pos, col, true, size);
                shipConfigurationPlayer[row + pos][col] = size;
                gameView.designModeGridButtons[row + pos][col].setEnabled(false);
                Integer objSize = size;
                gameView.designModeGridButtons[pos + row][col].setText(objSize.toString());
            }
            // reduce the amount of remains
            remains[size - 1]--;
        } else {
            if (col + size - 1 > gameDimension * 2 - 1) {
                JOptionPane.showMessageDialog(gameView.designModeDialog, "Ship Placement Error",
                        "Sorry, you can't place the ship at that place, please try again!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int pos = 0; pos <= size - 1; pos++) {
                // if (gameModel.checkConfigurationData(row, col + pos, true) != 0) {
                if (shipConfigurationPlayer[row][col + pos] != 0) {
                    JOptionPane.showMessageDialog(gameView.designModeDialog, "Ship Placement Error",
                            "Sorry, you can't place the ship at that place, please try again!",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            for (int pos = 0; pos <= size - 1; pos++) {
                // gameModel.updateConfigurationData(row, col + pos, true, size);
                shipConfigurationPlayer[row][col + pos] = size;
                gameView.designModeGridButtons[row][pos + col].setEnabled(false);
                Integer objSize = size;
                gameView.designModeGridButtons[row][pos + col].setText(objSize.toString());
            }

            remains[size - 1]--;
        }
    }

    /**
     * Method name: calculateLife
     * Purpose: Calculate the life of both players based on the game dimension
     * Algorithm: Implement the formula that professor gave
     */
    public void calculateLife() {
        this.machineLife = this.playerLife = gameDimension * (gameDimension + 1) * (gameDimension + 2) / 6;
    }

    /**
     * Method name: initializeGameData
     * Purpose: Initialize the two arrays of game data to zero
     * Algorithm: Implement the formula that professor gave
     */
    public void cleanGameData() {
        for (int i = 0; i < gameDimension * 2; i++) {
            for (int j = 0; j < gameDimension * 2; j++) {
                gameDataMachine[i][j] = 0;
                gameDataPlayer[i][j] = 0;
            }
        }
    }

    /**
     * Method name: newGame
     * Purpose: place the ships based on the user entered configuration string
     * Algorithm: Assigned the value of each char of the user game configuration
     * 
     * @param configStr - the configuration string
     */
    public void newGame(String configStr) {
        int count = 0;
        for (int i = 0; i < gameDimension * 2; i++) {
            for (int j = 0; j < gameDimension * 2; j++) {
                // Change the value of ASCII to digit
                shipConfigurationPlayer[i][j] = configStr.charAt(count) - 48;
                count++;
            }
        }
    }

    /**
     * Method name: calculateDimension
     * Purpose: Calculate the game dimension based on the configuration string
     * Algorithm: Get the root of the configuration string by math method
     * 
     * @param configStr - the configuration string
     */
    public void calculateDimension(String configStr) {
        gameDimension = (int) Math.sqrt(configStr.length()) / 2;
    }

    /**
     * Method name: isHit
     * Purpose: The method to check if the player successfully hit the ship
     * Algorithm: To check if the game data at the position is zero
     * 
     * @param gameView   - the GameView object
     * @param positionY- the Y position of current button
     * @param positionX  - the X position of current button
     * @param isPlayer   - the boolean indicates if the target is player
     */
    public void isHit(int positionX, int positionY, boolean isPlayer, GameView gameView) {
        if (isPlayer) {
            // int value = gameModel.checkConfigurationData(positionX, positionY, false);
            int value = shipConfigurationMachine[positionX][positionY];
            // To check if the player successfully hit the ship
            if (value != 0) {
                // If so, disable the button and change the color then reduce the life
                gameView.adversaryPanelGridButtons[positionX][positionY].setEnabled(false);
                gameView.adversaryPanelGridButtons[positionX][positionY].setBackground(gameView.color3);
                setMachineLife(machineLife - 1);
                gameView.showLive(gameView.adversaryLifeProgressBar, machineLife);
                // gameModel.updateGameData(positionX,positionY,false,1);
                gameDataMachine[positionX][positionY] = 1;
                gameView.displayHistory(gameView.historyArea, "You hit the opponent's ship");
            } else {
                // If not, disable the button and change the color then keep the life same
                gameView.adversaryPanelGridButtons[positionX][positionY].setEnabled(false);
                gameView.adversaryPanelGridButtons[positionX][positionY].setBackground(gameView.color2);
                // gameModel.updateGameData(positionX,positionY,false,1);
                gameDataMachine[positionX][positionY] = 1;
                gameView.displayHistory(gameView.historyArea, "You hit the water");
            }
            // To check if the machine successfully hit the ship
        } else {
            // int value = gameModel.checkConfigurationData(positionX, positionY, true);
            int value = shipConfigurationPlayer[positionX][positionY];
            if (value != 0) {
                gameView.userPanelGridButtons[positionX][positionY].setBackground(gameView.color3);
                setPlayerLife(playerLife - 1);
                gameView.showLive(gameView.userLifeProgressBar, playerLife);
                // gameModel.updateGameData(positionX,positionY,true,1);
                gameDataPlayer[positionX][positionY] = 1;
                gameView.displayHistory(gameView.historyArea, "Opponent hit your ship");
            } else {
                gameView.userPanelGridButtons[positionX][positionY].setEnabled(false);
                gameView.userPanelGridButtons[positionX][positionY].setBackground(gameView.color2);
                // gameModel.updateGameData(positionX,positionY,true,1);
                gameDataPlayer[positionX][positionY] = 1;
                gameView.displayHistory(gameView.historyArea, "Opponent hit the water");
            }
        }
    }

    /**
     * Method name: run
     * Purpose: get the configuration string
     * Algorithm: use double for loop to get each value of the player square
     * @return configuration string
     */

    public String getsConfig() {
        String configStr = "";
        for (int i = 0; i < gameDimension * 2; i++) {
            for (int j = 0; j < gameDimension * 2; j++) {
                Integer integerData = (Integer) shipConfigurationPlayer[i][j];
                configStr += integerData.toString();
            }
        }
        return configStr;
    }

    /**
     * Method name: getGameData
     * Purpose: Get player life and game time data
     * Algorithm: no
     * @return life and time data of player
     */
    public String getGameData() {
        return ", points: " + playerLife + ", time: " + gameTime;
    }
}
