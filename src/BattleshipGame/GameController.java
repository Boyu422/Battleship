/**
 * File name: GameController
 * identification: Boyu Li 041003345
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A22
 * Professor: Paulo Sousa
 * Date: 7/9/2023
 * Compiler: Oracle Open JDK - version: 20.0.1 2023-04-18
 * Purpose: To implement the game controller by utilizing ActionListener and ItemListener
 */

package BattleshipGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class name: GameController
 * Methods list:
 * 1. GameController(Overload Constructor)
 * 2. refresh
 * 3. ifCanSave
 * Constants list: NULL
 * Purpose: To implement the all the components of the game controller by
 * utilizing ActionListener and ItemListener
 *
 * @author Boyu Li
 * @version 1.0
 * @see java.awt
 * @see java.util
 * @since 20.0.1
 */
public class GameController {
    /**
     * GameView object
     */
    GameView gameView;

    /**
     * GameModel object
     */
    GameModel gameModel;

    /**
     * TimerTask object
     */
    TimerTask timerTask;

    /**
     * Timer object
     */
    Timer timer = new Timer();

    /**
     * The parameter of size
     */
    int paraSize = 1;

    /**
     * The parameter of isVertical
     */
    boolean paraIsVertical = true;

    /**
     * The array that stores all the remains
     */
    int[] remains;

    /**
     * The boolean tha indicates if the user entered the design mode
     */
    boolean isDesignMode = false;

    /**
     * Method name: GameController
     * Purpose: Overload constructor
     * Algorithm: no
     *
     * @param gameView - the class of Battleship
     */
    GameController(GameView gameView, GameModel gameModel) {
        this.gameView = gameView;
        this.gameModel = gameModel;
        gameView.displaySplash();
        gameModel.initializeData();
        gameModel.generateRanShips(false);
        gameModel.generateRanShips(true);
        gameModel.calculateLife();
        gameView.launchInterface(gameModel.getGameDimension(), gameModel.getPlayerLife(), gameModel.getMachineLife(),
                gameModel.getLangOption());
        gameView.addListenerForComboBox(new ComboBoxListener());
        gameView.addListenerForButton(new ButtonListener());
    }

    /**
     * Method name: refresh
     * Purpose: relaunches and the game interface
     * Algorithm: no
     */
    public void refresh() {
        gameView.remove(gameView.mainPanel);
        gameView.launchInterface(gameModel.getGameDimension(), gameModel.getPlayerLife(), gameModel.getMachineLife(),
                gameModel.getLangOption());
        gameView.addListenerForComboBox(new ComboBoxListener());
        gameView.addListenerForButton(new ButtonListener());
    }

    /**
     * Class name: ButtonListener
     * Methods list:
     * 1. actionPerformed
     * Constants list: NULL
     * Purpose: The class implement the ActionListener to implement the components
     * for all the buttons in the game
     *
     * @author Boyu Li
     * @version 1.0
     * @see java.awt
     * @since 20.0.1
     */
    class ButtonListener implements ActionListener {

        /**
         * Method name: actionPerformed
         * Purpose: The override method to implement the functionalities of the listener
         * for each specific components of a button
         * Algorithm: double for loop to check the position of the grid which needs to
         * be performed
         *
         * @param e - the event which needs to be performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == gameView.playButton) {
                gameModel.setGameTime(0);
                timing();
                // Start timing when game start
                // Display the life of each player
                gameView.showLive(gameView.userLifeProgressBar, gameModel.getPlayerLife());
                gameView.showLive(gameView.adversaryLifeProgressBar, gameModel.getMachineLife());

                // Set buttons on adversary panel editable
                for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                    for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                        gameView.adversaryPanelGridButtons[i][j].setEnabled(true);
                    }
                }

                // Display message
                gameView.displayHistory(gameView.historyArea, "Game Start!");
            }

            if (e.getSource() == gameView.solutionItem) {
                // Pop up the solution dialog
                gameView.initializeSolutionDialog(gameModel.getGameDimension());

                // Change the color of the solution
                for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                    for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                        if (gameModel.checkConfigurationData(i, j, false) != 0) {
                            gameView.changeColorForSolution(true, i, j);
                        } else {
                            gameView.changeColorForSolution(false, i, j);
                        }
                    }
                }

                // Set it visible
                gameView.solutionDialog.setVisible(true);
            }

            // Check if the user are clicking the user panel buttons or adversary panel
            // buttons, if so, then check the operation
            for (int i = 0; i < gameView.userPanelGridButtons.length; i++) {
                for (int j = 0; j < gameView.userPanelGridButtons[0].length; j++) {
                    if (e.getSource().equals(gameView.userPanelGridButtons[i][j])) {
                        int value = gameModel.checkGameData(i, j, true);
                        if (value != 0) {
                            gameView.userPanelGridButtons[i][j].setEnabled(false);
                            gameView.userPanelGridButtons[i][j].setBackground(Color.red);
                        } else {
                            gameView.userPanelGridButtons[i][j].setEnabled(false);
                            gameView.userPanelGridButtons[i][j].setBackground(Color.blue);
                        }
                    }

                    if (e.getSource().equals(gameView.adversaryPanelGridButtons[i][j])) {
                        // Check if the user successfully hit the ship
                        gameModel.isHit(i, j, true, gameView);

                        // To check if the HP of machine went to zero
                        boolean isMachineWinner = gameModel.getMachineLife() == 0;

                        // Make machine do the attacking and make sure it doesn't attack the place which
                        // already attacked
                        int randomPosX;
                        int randomPosY;
                        do {
                            Random random = new Random();
                            randomPosX = random.nextInt(gameModel.getGameDimension() * 2);
                            randomPosY = random.nextInt(gameModel.getGameDimension() * 2);
                        } while (gameModel.checkGameData(randomPosX, randomPosY, true) == 1);

                        // Check if machine successfully hit the ship
                        gameModel.isHit(randomPosX, randomPosY, false, gameView);

                        // To check if the HP of player went to zero
                        boolean isPlayerWinner = gameModel.getPlayerLife() == 0;

                        // If machine player winning the game, then stop the game and display splash
                        if (isMachineWinner) {
                            gameModel.setGameTime(0);
                            timing();
                            gameView.gameStopSplash(true);
                            refresh();
                            gameView.playButton.setEnabled(false);

                            // If human player winning the game, then stop the game and display splash
                        } else if (isPlayerWinner) {
                            gameModel.setGameTime(0);
                            timing();
                            gameView.gameStopSplash(false);
                            refresh();
                            gameView.playButton.setEnabled(false);
                        }
                    }
                }
            }

            if (e.getSource() == gameView.randModeButton) {
                // Initialize the data and life
                gameModel.calculateLife();
                gameModel.initializeData();

                // generate the ships for machine and human player
                gameModel.generateRanShips(false);
                gameModel.generateRanShips(true);

                // Initialize the progress bars
                gameView.adversaryLifeProgressBar.setMaximum(gameModel.getMachineLife());
                gameView.userLifeProgressBar.setMaximum(gameModel.getPlayerLife());

                // Enable the play button
                gameView.playButton.setEnabled(true);

                // Update the log
                gameView.displayHistory(gameView.historyArea, "You randomise the ships");
            }

            if (e.getSource() == gameView.newItem) {

                // Initialize the game data
                gameModel.initializeData();

                // Enter the game string
                String configStr = gameView.enterString();

                // Check if the configuration string is empty
                if (configStr == null) {
                    gameView.displayHistory(gameView.historyArea, "The game configuration can't be\n null!");
                    gameView.playButton.setEnabled(false);
                    for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                        for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                            gameView.adversaryPanelGridButtons[i][j].setEnabled(true);
                            gameView.adversaryPanelGridButtons[i][j].setBackground(gameView.color1);
                            gameView.userPanelGridButtons[i][j].setEnabled(true);
                            gameView.userPanelGridButtons[i][j].setBackground(gameView.color1);
                        }
                    }
                } else {
                    if (configStr.isEmpty()) {
                        // If so, disable the play button and reset the game
                        gameView.displayHistory(gameView.historyArea, "The game configuration can't be 0!");
                        gameView.playButton.setEnabled(false);
                        for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                            for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                                gameView.adversaryPanelGridButtons[i][j].setEnabled(true);
                                gameView.adversaryPanelGridButtons[i][j].setBackground(gameView.color1);
                                gameView.userPanelGridButtons[i][j].setEnabled(true);
                                gameView.userPanelGridButtons[i][j].setBackground(gameView.color1);
                            }
                        }
                    } else {
                        gameModel.calculateDimension(configStr);
                        // Start a new game based on configuration string
                        gameModel.newGame(configStr);
                        gameModel.setPlayerLife(gameModel.getGameDimension() * 2);
                        gameModel.setMachineLife(gameModel.getGameDimension() * 2);
                        gameModel.generateRanShips(false);
                        refresh();
                    }
                }
            }

            if (e.getSource() == gameView.designModeButton) {
                // Initialize the direction
                paraIsVertical = true;

                // Initialize the live
                gameModel.calculateLife();

                // Receive the data and calculate all the remains
                isDesignMode = true;
                paraSize = 1;
                gameModel.initializeData();
                remains = new int[gameModel.getGameDimension()];
                for (int i = 0; i < gameModel.getGameDimension(); i++) {
                    remains[i] = gameModel.getGameDimension() - i;
                }

                // Set up the default remains and pop up the design mode
                Integer defaultRemain = remains[0];
                String stringDefaultRemain = defaultRemain.toString();
                gameView.initializeDesignDialog(gameModel.getGameDimension() * 2, this, new ComboBoxListener(),
                        stringDefaultRemain);
                gameView.displayHistory(gameView.historyArea, "You entered the design mode");
            }

            // Switch the direction of boats based on the user selection
            if (e.getSource() == gameView.VButton) {
                paraIsVertical = true;
                gameView.displayHistory(gameView.historyArea, "You set direction to vertical");
            }

            if (e.getSource() == gameView.HButton) {
                paraIsVertical = false;
                gameView.displayHistory(gameView.historyArea, "You set direction to horizontal");
            }

            if (isDesignMode) {
                for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                    for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                        if (e.getSource() == gameView.designModeGridButtons[i][j]) {
                            // Generate ships based on user's selection
                            gameModel.generateOneShip(paraSize, i, j, paraIsVertical, gameView, remains);
                            gameView.showRemainsLeft(gameView.remains, remains[paraSize - 1]);

                            // If all the remains come to zero, then enable the save button
                            if (ifCanSave()) {
                                gameView.saveButton.setEnabled(true);
                            }
                        }
                    }
                }
            }

            if (e.getSource() == gameView.saveButton) {
                // save the configuration and enable the play button
                gameView.playButton.setEnabled(true);
                gameModel.generateRanShips(false);
                gameView.designModeDialog.dispose();
                gameView.displayHistory(gameView.historyArea, "You save the ship config");
            }

            if (e.getSource() == gameView.cleanButton) {
                // Initialize all the data and enable all the buttons
                gameModel.initializeData();
                for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                    for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                        gameView.designModeGridButtons[i][j].setEnabled(true);
                        gameView.designModeGridButtons[i][j].setText(null);
                        gameModel.updateGameData(i, j, true, 0);
                    }
                }

                // Recalculate the remains
                remains = new int[gameModel.getGameDimension()];
                for (int i = 0; i < gameModel.getGameDimension(); i++) {
                    remains[i] = gameModel.getGameDimension() - i;
                }
                gameView.showRemainsLeft(gameView.remains, remains[paraSize - 1]);
                gameView.displayHistory(gameView.historyArea, "You clean the ships");
            }

            if (e.getSource() == gameView.resetButton) {
                // Clean all the game data
                gameModel.cleanGameData();

                // Initialize the game time and life
                gameModel.setGameTime(0);
                gameModel.calculateLife();

                // Reset all the buttons
                for (int i = 0; i < gameModel.getGameDimension() * 2; i++) {
                    for (int j = 0; j < gameModel.getGameDimension() * 2; j++) {
                        gameView.adversaryPanelGridButtons[i][j].setEnabled(true);
                        gameView.adversaryPanelGridButtons[i][j].setBackground(gameView.color1);
                        gameView.userPanelGridButtons[i][j].setEnabled(true);
                        gameView.userPanelGridButtons[i][j].setBackground(gameView.color1);
                    }
                }
                timing();
                // restart the timer and progress bars
                gameView.displayHistory(gameView.historyArea, "You reset the game");
                gameView.showLive(gameView.userLifeProgressBar, gameModel.getPlayerLife());
                gameView.showLive(gameView.adversaryLifeProgressBar, gameModel.getMachineLife());
            }

            // Pop up color selectio dialog
            if (e.getSource() == gameView.colorsItem) {
                gameView.colorsDialog.setVisible(true);
                refresh();
            }

            // Make user select colors
            if (e.getSource() == gameView.Color1Button) {
                gameView.displayHistory(gameView.historyArea, "You change the color for unselected");// choose the color
                gameView.color1 = gameView.selectColor();
                gameView.Color1Panel.setBackground(gameView.color1);
            }

            if (e.getSource() == gameView.Color2Button) {
                gameView.displayHistory(gameView.historyArea, "You change the color for water");// choose the color
                gameView.color2 = gameView.selectColor();
                gameView.Color2Panel.setBackground(gameView.color2);
            }

            if (e.getSource() == gameView.Color3Button) {
                gameView.displayHistory(gameView.historyArea, "You change the color for ship");// choose the color
                gameView.color3 = gameView.selectColor();
                gameView.Color3Panel.setBackground(gameView.color3);
            }

            // Exit the game
            if (e.getSource() == gameView.exitItem) {
                gameView.displayHistory(gameView.historyArea, "Game is exiting...");// turn off an app
                gameView.dispose();
            }

            // Pop up about dialog
            if (e.getSource() == gameView.aboutItem) {
                gameView.displayAboutInfo();// show popup
            }
        }
    }

    /**
     * Class name: ComboBoxListener
     * Methods list:
     * 1. itemStateChanged
     * Constants list: NULL
     * Purpose: The class implement the AItemListener to implement the components
     * for all the Combo Box in the game
     *
     * @author Boyu Li
     * @version 1.0
     * @see java.awt
     * @since 20.0.1
     */
    class ComboBoxListener implements ItemListener {
        /**
         * Method name: itemStateChanged
         * Purpose: The override method to implement the functionalities of the listener
         * for each specific components of a combo box
         * Algorithm:no
         *
         * @param e - the event which needs to be performed
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == gameView.languageBox) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Switch the language based on the langauge option
                    int option = gameView.languageBox.getSelectedIndex();
                    if (option == 0) {
                        gameModel.setLangOption(0);
                        gameView.switchLanguage(0);
                        refresh();
                        gameView.displayHistory(gameView.historyArea, "You switch the language to English");
                    }
                    if (option == 1) {
                        gameModel.setLangOption(1);
                        gameView.switchLanguage(1);
                        refresh();
                        gameView.displayHistory(gameView.historyArea, "You switch the language to Chinese");
                    }
                }
            }

            // Switch the dimension based user's option
            if (e.getSource() == gameView.dimensionBox) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Integer option = (Integer) gameView.dimensionBox.getSelectedItem();
                    gameModel.setGameDimension(option);
                    refresh();
                    gameView.playButton.setEnabled(false);
                    gameView.displayHistory(gameView.historyArea, "You switch the dimension to " + option);
                }
            }

            // Switch the ship size based on user's option
            if (e.getSource() == gameView.designModeComboBox) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Integer option = (Integer) gameView.designModeComboBox.getSelectedItem();
                    paraSize = option;
                    gameView.showRemainsLeft(gameView.remains, remains[paraSize - 1]);
                }
            }

        }
    }

    /**
     * Method name: timing
     * Purpose: The method to implement the timer components
     * Algorithm:no
     */
    private void timing() {
        if (timerTask != null) {
            timerTask.cancel();
        }

        timerTask = new TimerTask() {
            @Override
            public void run() {
                gameModel.setGameTime(gameModel.getGameTime() + 1);
                gameView.countTime(gameView.timeTextFiled, gameModel.getGameTime());
            }
        };

        try {
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method name: ifCanSave
     * Purpose: The method to check if the user can save the configuration
     * Algorithm: To check if each remains of ship is zero
     *
     * @return the boolean indicates if the user can save the config
     */
    private boolean ifCanSave() {
        for (int i = 0; i < remains.length; i++) {
            if (remains[i] != 0)
                return false;
        }
        return true;
    }
}
