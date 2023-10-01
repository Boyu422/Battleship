/**
 * File name: GameView.java
 * identification: Boyu Li 041003345
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A22
 * Professor: Paulo Sousa
 * Date: 7/9/2023
 * Compiler: Oracle Open JDK - version: 20.0.1 2023-04-18
 * Purpose: To utilize the Java Swing to visualize the game interface
 */

package BattleshipGame;

import javax.swing.*;
import javax.swing.JColorChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.Serial;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class name: Battleship
 * Methods list:
 * 1. initializeMainWindow
 * 2. initializeControlPanel
 * 3. initializeUserPanel
 * 4. initializeAdversaryPanel
 * 5. launchInterface
 * 6. addListenerForComboBox
 * 7. addListenerForButton
 * 8. setLanguage
 * 9. refreshInterface
 * 10. countTime
 * 11. initializeDesignDialog
 * 12. showRemainsLeft
 * 13. showLive
 * 14. initializeMenu
 * 15. initializeSolutionDialog
 * 16. changeColorForSolution
 * 17. displaySplash
 * 18. gameStopSplash
 * 19. displayHistory
 * 20. enterString
 * 21. switchLanguage
 *
 * Constants list:
 * 1. DEFAULT_DIMENSION
 * Purpose: The class implements the user interface for game battleship
 * @author Boyu Li
 * @version 1.0
 * @see java.awt
 * @see javax.swing
 * @see java.io
 * @see java.util
 * @since 20.0.1
 * */

public class GameView extends JFrame {
    /**
     * Method name: Battleship
     * Purpose: Overload constructor
     * Algorithm: no
     * */
    GameView() {
    }

    /**
     * serialVersionUID
     * */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The title of the game
     * */
    String TITLE = "Battleship (Boyu Li 345)";

    /**
     * The label for language button
     * */
    String LANGUAGE = "Languages: ";

    /**
     * The first language option
     * */
    String ENGLISH = "English";

    /**
     * The second language option
     * */
    String CHINESE = "Chinese";

    /**
     * The label for time text field
     * */
    String TIME = "Time: ";

    /**
     * The button name for reset button
     * */
    String RESET = "Reset";

    /**
     * The label for dimension button
     * */
    String DIMENSION = "Dimension: ";

    /**
     * The user panel of the game
     * */
    JPanel userPanel;

    /**
     * The button name for play button
     * */
    String PLAY = "Play";

    /**
     * The button name for random button
     * */

    String RANDOM = "Random";

    /**
     * The button name for design button
     * */
    String DESIGN = "Design";

    /**
     * The adversary panel of the game
     * */
    JPanel adversaryPanel;

    /**
     * The control panel of the game
     * */
    JPanel controlPanel;

    /**
     * The logo of the game
     * */
    JLabel gameLogo;

    /**
     * The combo box for language switching
     * */
    JComboBox<String> languageBox;

    /**
     * The panel for the language switching component of the game
     * */
    JPanel languagePanel;

    /**
     * The combo box for dimension switching
     * */
    JComboBox<Integer> dimensionBox;

    /**
     * The panel for the dimension switching
     */
    JPanel dimensionPanel;

    /**
     * The button for design mode switching
     * */
    JButton designModeButton;

    /**
     * The button for random mode switching
     * */
    JButton randModeButton;

    /**
     * The panel for design mode and random mode
     * */
    JPanel modePanel;

    /**
     * The text area to display the game history/log
     * */
    JTextArea historyArea;

    /**
     * The panel for the game history displaying component
     * */

    JPanel historyAreaPanel;

    /**
     * The label for the game time text area
     * */
    JLabel timeLabel;

    /**
     * The text field to display the game time
     * */
    JTextField timeTextFiled;

    /**
     * the panel for game time displaying component
     * */
    JPanel timePanel;

    /**
     * The game resetting button
     * */
    JButton resetButton;

    /**
     * The panel for game resetting component
     * */
    JPanel resetPanel;

    /**
     * The game playing button
     * */
    JButton playButton;

    /**
     * The panel for game playing component
     * */
    JPanel playPanel;

    /**
     * The panel to display the character I at user panel
     * */
    JPanel panelI;

    /**
     * The panel to display the character U at user panel
     * */
    JPanel panelU;

    /**
     * The x coordinates of the grid on the panel
     * */
    JPanel XCoordinates;

    /**
     * The y coordinates of the grid on the panel
     * */
    JPanel YCoordinates;

    /**
     * The panel to display the grid of user panel
     * */
    JPanel userGridPanel;

    /**
     * The panel for user life displaying component
     * */
    JPanel userLifePanel;

    /**
     * The progress bar to display the life of the user
     * */
    JProgressBar userLifeProgressBar;

    /**
     * The label of user life
     * */
    JLabel userLifeLabel;

    /**
     * The panel to display the grid of adversary panel
     * */
    JPanel adversaryGridPanel;

    /**
     * The panel for adversary life displaying component
     * */
    JPanel adversaryLifePanel;

    /**
     * The progress bar to display the life of the adversary
     * */
    JProgressBar adversaryLifeProgressBar;

    /**
     * The label of adversary life
     * */
    JLabel adversaryLifeLabel;

    /**
     * The array to manage the buttons from the user panel grid
     * */
    JButton[][] userPanelGridButtons;

    /**
     * The array to manage the buttons from the adversary panel grid
     * */
    JButton[][] adversaryPanelGridButtons;

    /**
     * The mainPanel to collaborate control, user and adversary panels
     * */
    JPanel mainPanel;

    /**
     * The array to manage all the buttons on the design mode dialog
     * */
    JButton[][] designModeGridButtons;

    /**
     * The panel to hold all the buttons on the dialog on the design mode dialog
     * */
    JPanel designModeGridPanel;

    /**
     * The dialog to display the design mode
     * */
    JDialog designModeDialog;

    /**
     * The label of ship on design mode
     * */
    JLabel designModeShipLabel;

    /**
     * The combo box for ship selection on design mode
     * */
    JComboBox<Integer> designModeComboBox;

    /**
     * The panel of ship on design mode
     * */
    JPanel designModeShipPanel;

    /**
     * String "Ship:"
     * */
    String SHIP = "Ship: ";

    /**
     * String "Direction"
     * */
    String DIRECTION = "Direction: ";

    /**
     * The button of horizontal on design mode
     * */
    JRadioButton HButton;

    /**
     * The button of vertical on design mode
     * */
    JRadioButton VButton;

    /**
     * The group of options of direction on design mode
     * */
    ButtonGroup directionOptions;

    /**
     * The panel of direction on design mode
     * */
    JPanel directionPanel;

    /**
     * The control panel of the dialog on design mode
     * */
    JPanel dialogControlPanel;

    /**
     * String "Remains: "
     * */
    String REMAIN = "Remains: ";

    /**
     * The text field of remains on design mode
     * */
    JTextField remains;

    /**
     * The panel of remains on design mode
     * */
    JPanel remainPanel;

    /**
     * The panel that holds the clean and save buttons on design mode
     * */
    JPanel cleanAndSaveButtonPanel;

    /**
     * The button of clean on the design mode
     * */
    JButton cleanButton;

    /**
     * The button of save on the design mode
     * */
    JButton saveButton;

    /**
     * The label of direction on the design mode
     * */
    JLabel directionLabel;

    /**
     * The label of remain on the design mode
     * */
    JLabel remainLabel;

    /**
     * String "Clean"
     * */
    String CLEAN = "Clean";

    /**
     * String "Save"
     * */
    String SAVE = "Save";

    /**
     * String "New"
     * */
    String NEW = "New";

    /**
     * String "Solution"
     * */
    String SOLUTION = "Solution";

    /**
     * String "Exit"
     * */
    String EXIT = "Exit";

    /**
     * String "Help"
     * */
    String HELP = "Help";

    /**
     * String "Game"
     * */
    String GAME = "Game";

    /**
     * String "Colors"
     * */
    String COLORS = "Colors";

    /**
     * String "About"
     * */
    String ABOUT = "About";

    /**
     * String "Live1"
     * */
    String LIVE1 = "Live1";

    /**
     * String "Live2"
     * */
    String LIVE2 = "Live2";

    /**
     * The menu bar to hold the game menu
     * */
    JMenuBar jMenuBar;

    /**
     * The submenu of "Game" option
     * */
    JMenu gameMenu;

    /**
     * The submenu of "Help" option
     * */
    JMenu helpMenu;

    /**
     * The item of new on sub menu "Game"
     * */
    JMenuItem newItem;

    /**
     * The item of solution on sub menu "Game"
     * */
    JMenuItem solutionItem;

    /**
     * The item of exit on sub menu "Game"
     * */
    JMenuItem exitItem;

    /**
     * The item of colors on submenu "About"
     */
    JMenuItem colorsItem;

    /**
     * The item of about on submenu "About"
     * */
    JMenuItem aboutItem;

    /**
     * The dialog for color selection
     * */
    JDialog colorsDialog;

    /**
     * The dialog to display the solution
     * */
    JDialog solutionDialog;

    /**
     * The color for unselected
     * */
    Color color1 = Color.white;

    /**
     * The color for water
     * */
    Color color2 = Color.blue;

    /**
     * The color for ship
     * */
    Color color3 = Color.red;

    /**
     * The panel of unselected color
     * */
    JPanel Color1Panel;

    /**
     * The panel of water color
     * */
    JPanel Color2Panel;

    /**
     * The panel of ship color
     * */
    JPanel Color3Panel;

    /**
     * The button of color of unselected
     * */
    JButton Color1Button;

    /**
     * The button of color of water
     * */
    JButton Color2Button;

    /**
     * The button of color of ship
     * */
    JButton Color3Button;

    /**
     * String "Unselected"
     * */
    String UNSELECTED = "Unselected";

    /**
     * String "Water"
     * */
    String WATER = "Water";

    /**
     * String "Ship"
     * */
    String SHIPC = "Ship";

    /**
     * The scroll pane to display the history
     * */
    JScrollPane historyScrollPane;

    /**
     * The two D array of panel to display the solution of the game
     * */
    JPanel SolutionPanels[][];

    /**
     * The locale to implement the internationalization
     * */
    Locale locale;

    /**
     * String "Color1"
     * */
    String COLOR1 = "Color1";

    /**
     * String "Color2"
     * */
    String COLOR2 = "Color2";

    /**
     * String "Color3"
     * */
    String COLOR3 = "Color3";

    /**
     * The resource bundler to implement the internationalization
     * */
    private static ResourceBundle texts;

    /**
     * Method name: initializeMainWindow
     * Purpose: To initialize the main interface and the component of the game
     * Algorithm: The method utilizes the Java Swing to visualize the main window of the game
     * */
    public void initializeMainWindow() {
        //Set up and initialize of the main windows with the basic characters
        setTitle(TITLE);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 1, 1);
        mainPanel.setLayout(flowLayout);
        //setLayout(flowLayout);
        setResizable(false);
        setSize(1000, 513);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        /*
         * Set up and initialize the background color of user, adversary and control panel
         * */
        userPanel = new JPanel();
        userPanel.setBackground(Color.blue);

        adversaryPanel = new JPanel();
        adversaryPanel.setBackground(Color.yellow);

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.gray);

        //Set up and initialize the size of the user, adversary and control panel
        userPanel.setPreferredSize(new Dimension(390, 449));
        controlPanel.setPreferredSize(new Dimension(200, 449));
        adversaryPanel.setPreferredSize(new Dimension(390, 449));


        //Adding the three panels to the main frame
        mainPanel.add(userPanel);
        mainPanel.add(controlPanel);
        mainPanel.add(adversaryPanel);
        add(mainPanel);
    }

    /**
     * Method name: initializeUserPanel
     * Purpose: To initialize the interface and components of user panel
     * Algorithm: The method utilizes the Java Swing to visualize the user panel and
     * manage the grids by array
     * @param dimension - game dimension
     * @param langOption - language option
     * */
    public void initializeControlPanel(Integer dimension, Integer langOption) {
        //Set the layout of control panel to box layout
        BoxLayout boxLayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(boxLayout);
        /*
         * Set up and add the game logo for the game
         * */
        JPanel logoPanel;
        logoPanel = new JPanel();
        gameLogo = new JLabel();
        gameLogo.setIcon(new ImageIcon("../bin/images/logo.png"));
        gameLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(gameLogo);
        controlPanel.add(logoPanel);

        //Set up and initialize the panels for game mode switching component
        designModeButton = new JButton(DESIGN);
        randModeButton = new JButton(RANDOM);
        modePanel = new JPanel();
        modePanel.add(designModeButton);
        modePanel.add(randModeButton);
        controlPanel.add(modePanel);

        //Set up and initialize the language box for game language switching component
        JLabel languageLabel = new JLabel(LANGUAGE);
        String[] allLanguages = {ENGLISH, CHINESE};
        languageBox = new JComboBox<>(allLanguages);
        languageBox.setSelectedIndex(langOption);
        languagePanel = new JPanel();
        languagePanel.add(languageLabel);
        languagePanel.add(languageBox);
        controlPanel.add(languagePanel);

        //Set up and initialize the dimension box for game dimension switching component
        JLabel dimensionLabel = new JLabel(DIMENSION);
        Integer[] allDimensions = {2, 3, 4, 5, 6, 7, 8, 9};
        dimensionBox = new JComboBox<>(allDimensions);
        dimensionBox.setSelectedItem(dimension);
        dimensionPanel = new JPanel();
        dimensionPanel.add(dimensionLabel);
        dimensionPanel.add(dimensionBox);
        controlPanel.add(dimensionPanel);

        //Set up and initialize the game log text area for game history displaying component
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setPreferredSize(new Dimension(180, 240));
        historyArea.setBackground(Color.WHITE);
        historyArea.setBounds(100, 100, 180, 240);

        historyAreaPanel = new JPanel();
        historyScrollPane = new JScrollPane();
        historyScrollPane.setPreferredSize(new Dimension(180, 240));
        historyScrollPane = new JScrollPane();
        historyScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        historyScrollPane.setBounds(20, 30, 180, 240);
        historyScrollPane.setViewportView(historyArea);
        historyAreaPanel.add(historyScrollPane);
        controlPanel.add(historyAreaPanel);

        //Set up and initialize the game time text field for game time displaying component
        timeLabel = new JLabel(TIME);
        timeTextFiled = new JTextField();
        timeTextFiled.setEditable(false);
        timeTextFiled.setPreferredSize(new Dimension(50, 20));
        timePanel = new JPanel();
        timePanel.add(timeLabel);
        timePanel.add(timeTextFiled);
        controlPanel.add(timePanel);

        //Set up and initialize the reset button for the game resetting component
        resetButton = new JButton(RESET);
        resetPanel = new JPanel();
        resetPanel.add(resetButton);
        controlPanel.add(resetPanel);

        //Set up and initialize the play button for the game playing component
        playButton = new JButton(PLAY);
        playPanel = new JPanel();
        playPanel.add(playButton);
        controlPanel.add(playPanel);
    }

    /**
     * Method name: initializeUserPanel
     * Purpose: To initialize the interface and components of user panel
     * Algorithm: The method utilizes the Java Swing to visualize the user panel and
     * manage the grids by array
     * @param dimension - game dimension
     * @param userMaxHP - the max HP for uer
     * */
    public void initializeUserPanel(int dimension, int userMaxHP) {
        //Set up the layout of user panel to flowlayout
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 1, 1);
        userPanel.setLayout(flowLayout);
        GridLayout gridLayout1 = new GridLayout(1, dimension, 1, 1);

        //Set up and initialize the X coordinates of the user panel to display the coordinates of the grid
        XCoordinates = new JPanel();
        XCoordinates.setLayout(gridLayout1);
        XCoordinates.setPreferredSize(new Dimension(337, 50));

        //Initialize and set up the label of letter I and add it to the user panel
        panelI = new JPanel();
        JLabel textI = new JLabel("I", JLabel.CENTER);
        panelI.setPreferredSize(new Dimension(50, 50));
        panelI.setLayout(new BorderLayout());
        panelI.add(textI, BorderLayout.CENTER);
        userPanel.add(panelI);

        //Use double for loop to fulfil all the X coordinates for user panel
        for (int i = 97; i < dimension + 97; i++) {
            char letter = (char) i;
            String letterString = Character.toString(letter);
            JLabel coordinateLabel = new JLabel(letterString, JLabel.CENTER);
            XCoordinates.add(coordinateLabel);
        }
        userPanel.add(XCoordinates);

        //Set up and initialize the Y coordinates of the user panel to display the coordinates of the grid
        GridLayout gridLayout2 = new GridLayout(dimension, 1, 1, 1);
        YCoordinates = new JPanel();
        YCoordinates.setPreferredSize(new Dimension(50, 337));
        YCoordinates.setLayout(gridLayout2);

        //Use double for loop to fulfil all the Y coordinates for user panel
        for (int i = 65; i < dimension + 65; i++) {
            char letter = (char) i;
            String letterString = Character.toString(letter);
            JLabel coordinateLabel = new JLabel(letterString, JLabel.CENTER);
            YCoordinates.add(coordinateLabel);
        }
        userPanel.add(YCoordinates);

        //Set up the user panel grid
        userGridPanel = new JPanel();
        userGridPanel.setPreferredSize(new Dimension(337, 337));
        GridLayout gridLayout3 = new GridLayout(dimension, dimension, 1, 1);
        userGridPanel.setLayout(gridLayout3);

        //Initialize each buttons of the grid on user panel by double for loop
        userPanelGridButtons = new JButton[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                JButton button = new JButton();
                button.setEnabled(false);
                button.setBackground(color1);
                userPanelGridButtons[i][j] = button;
                userGridPanel.add(userPanelGridButtons[i][j]);
            }
        }
        userPanel.add(userGridPanel);

        //Initialize and set up the life progress bar for the user panel
        userLifeProgressBar = new JProgressBar(0, userMaxHP);
        userLifeProgressBar.setPreferredSize(new Dimension(280, 50));
        userLifeLabel = new JLabel(LIVE1);
        userLifePanel = new JPanel();
        userLifePanel.setPreferredSize(new Dimension(388, 100));
        userLifePanel.add(userLifeLabel);
        userLifePanel.add(userLifeProgressBar);
        userPanel.add(userLifePanel);
    }


    /**
     * Method name: launchInterface
     * Purpose: To lunch the interface of the game
     * Algorithm: no
     * manage the grids by array
     * @param dimension - game dimension
     * @param userMaxHP - the max HP of user player
     * @param adversaryMaxHP - the max HP of machine player
     * @param langOption - the language option
     * */
    public void launchInterface(int dimension, int userMaxHP, int adversaryMaxHP, int langOption) {
        mainPanel = new JPanel();
        setVisible(false);
        initializeMainWindow();
        initializeControlPanel(dimension, langOption);
        initializeUserPanel(dimension * 2, userMaxHP);
        initializeAdversaryPanel(dimension * 2, adversaryMaxHP);
        initializeMenu();
        refreshInterface();
    }


    /**
     * Method name: initializeAdversaryPanel
     * Purpose: To initialize the interface and components of adversary panel
     * Algorithm: The method utilizes the Java Swing to visualize the adversary panel and
     * manage the grids by array
     * @param dimension - gam dimension
     * @param adversaryMaxHP - the max HP of machine player
     * */
    public void initializeAdversaryPanel(int dimension, int adversaryMaxHP) {
        //Set up the layout of adversary panel to flowlayout
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 1, 1);
        adversaryPanel.setLayout(flowLayout);
        GridLayout gridLayout1 = new GridLayout(1, dimension, 1, 1);

        //Set up and initialize the X coordinates of the adversary panel to display the coordinates of the grid
        XCoordinates = new JPanel();
        XCoordinates.setLayout(gridLayout1);
        XCoordinates.setPreferredSize(new Dimension(337, 50));
        panelU = new JPanel();

        //Initialize and set up the label of letter U and add it to the adversary panel
        JLabel textI = new JLabel("U", JLabel.CENTER);
        panelU.setPreferredSize(new Dimension(50, 50));
        panelU.setLayout(new BorderLayout());
        panelU.add(textI, BorderLayout.CENTER);
        adversaryPanel.add(panelU);

        //Use double for loop to fulfil all the X coordinates for adversary panel
        for (int i = 97; i < dimension + 97; i++) {
            char letter = (char) i;
            String letterString = Character.toString(letter);
            JLabel coordinateLabel = new JLabel(letterString, JLabel.CENTER);
            XCoordinates.add(coordinateLabel);
        }
        adversaryPanel.add(XCoordinates);

        //Set up and initialize the Y coordinates of the adversary panel to display the coordinates of the grid
        GridLayout gridLayout2 = new GridLayout(dimension, 1, 1, 1);
        YCoordinates = new JPanel();
        YCoordinates.setPreferredSize(new Dimension(50, 337));
        YCoordinates.setLayout(gridLayout2);

        //Use double for loop to fulfil all the Y coordinates for adversary panel
        for (int i = 65; i < dimension + 65; i++) {
            char letter = (char) i;
            String letterString = Character.toString(letter);
            JLabel coordinateLabel = new JLabel(letterString, JLabel.CENTER);
            YCoordinates.add(coordinateLabel);
        }

        //Set up the adversary panel grid
        adversaryPanel.add(YCoordinates);
        adversaryGridPanel = new JPanel();
        adversaryGridPanel.setPreferredSize(new Dimension(337, 337));
        GridLayout gridLayout3 = new GridLayout(dimension, dimension, 1, 1);
        adversaryGridPanel.setLayout(gridLayout3);

        //Initialize each buttons of the grid on user panel by double for loop
        adversaryPanelGridButtons = new JButton[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                JButton button = new JButton();
                button.setEnabled(false);
                button.setBackground(color1);
                adversaryPanelGridButtons[i][j] = button;
                adversaryGridPanel.add(adversaryPanelGridButtons[i][j]);
            }
        }

        //Initialize and set up the life progress bar for the adversary panel
        adversaryPanel.add(adversaryGridPanel);
        adversaryLifeProgressBar = new JProgressBar(0, adversaryMaxHP);
        adversaryLifeProgressBar.setPreferredSize(new Dimension(280, 50));
        adversaryLifeLabel = new JLabel(LIVE2);
        adversaryLifePanel = new JPanel();
        adversaryLifePanel.setPreferredSize(new Dimension(388, 100));
        adversaryLifePanel.add(adversaryLifeLabel);
        adversaryLifePanel.add(adversaryLifeProgressBar);
        adversaryPanel.add(adversaryLifePanel);
    }

    /**
     * Method name: addListenerForComboBox
     * Purpose: To set up listener for all Combo box
     * Algorithm: no
     * @param itemListener - the item listener for combo box
     * */
    public void addListenerForComboBox(ItemListener itemListener) {
        languageBox.addItemListener(itemListener);
        dimensionBox.addItemListener(itemListener);
    }

    /**
     * Method name: addListenerForButton
     * Purpose: To set up listener for all Button
     * Algorithm: Use double for loop to set up listener for each button in adversary and user panel
     * @param actionListener - the listener for button
     * */
    public void addListenerForButton(ActionListener actionListener) {
        playButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        designModeButton.addActionListener(actionListener);
        randModeButton.addActionListener(actionListener);
        //Use double for loop to set up listener for each buttons in both user and adversary grid
        for (int i = 0; i < userPanelGridButtons.length; i++) {
            for (int j = 0; j < userPanelGridButtons[0].length; j++) {
                userPanelGridButtons[i][j].addActionListener(actionListener);
                adversaryPanelGridButtons[i][j].addActionListener(actionListener);
            }
        }
        newItem.addActionListener(actionListener);
        colorsItem.addActionListener(actionListener);
        exitItem.addActionListener(actionListener);
        aboutItem.addActionListener(actionListener);
        solutionItem.addActionListener(actionListener);
        Color1Button.addActionListener(actionListener);
        Color2Button.addActionListener(actionListener);
        Color3Button.addActionListener(actionListener);
    }

    /**
     * Method name: initializeUserPanel
     * Purpose: To initialize the interface and components of user panel
     * Algorithm: The method utilizes the Java Swing to visualize the user panel and
     * manage the grids by array
     * */
    public void refreshInterface() {
        revalidate();
        repaint();
        setVisible(true);
    }

    /**
     * Method name: countTime
     * Purpose: To display the game time at time slot
     * Algorithm: The method utilize the setText method to display the time
     * @param timeField - the text field where displays the time
     * @param second - the current playing time in second
     * */
    public void countTime(JTextField timeField, int second) {
        timeField.setText(String.valueOf(second));
    }

    /**
     * Method name: initializeDesignDialog
     * Purpose: To initialize the design mode dialog for user
     * Algorithm: The method utilizes the Java Swing to implement the ship size,direction selection and ship
     * placement saving, cleaning, ship remains displaying component on design mode.
     * @param actionListener - the controller for all the button item components
     * @param dimension - the current game dimension
     * @param defaultRemains  - the amount of remains of the current ship
     * @param itemListener - the controller for all the combo box components
     * */
    public void initializeDesignDialog(int dimension, ActionListener actionListener, ItemListener itemListener, String defaultRemains) {
        //Initialize the components on the design mode dialog
        designModeDialog = new JDialog(this, true);
        designModeGridButtons = new JButton[dimension][dimension];
        designModeGridPanel = new JPanel();

        //Set up the layout of design dialog
        GridLayout gridLayout = new GridLayout(dimension, dimension, 1, 1);

        //use double for loop to add listener for the design mode button
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                JButton button = new JButton();
                designModeGridButtons[i][j] = button;
                designModeGridButtons[i][j].addActionListener(actionListener);
                designModeGridPanel.add(designModeGridButtons[i][j]);
            }
        }

        //Set up the grid panel
        designModeGridPanel.setPreferredSize(new Dimension(500, 400));
        dialogControlPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        dialogControlPanel.setLayout(flowLayout);
        dialogControlPanel.setPreferredSize(new Dimension(500, 50));

        //Set up the ship panel
        designModeShipPanel = new JPanel();
        designModeShipLabel = new JLabel(SHIPC);
        designModeShipPanel.add(designModeShipLabel);

        //Set up the ship combo box
        designModeComboBox = new JComboBox<>();
        for (int i = 0; i < dimension / 2; i++) {
            designModeComboBox.addItem(i + 1);
        }
        designModeComboBox.setSelectedItem(1);
        designModeComboBox.addItemListener(itemListener);
        designModeShipPanel.add(designModeComboBox);

        //Set up the direction panel
        directionPanel = new JPanel();
        directionOptions = new ButtonGroup();
        directionLabel = new JLabel(DIRECTION);
        directionPanel.add(directionLabel);

        //Set up vertical and horizontal button
        VButton = new JRadioButton("V");
        HButton = new JRadioButton("H");

        //Add them to direction panel
        directionOptions.add(HButton);
        directionOptions.add(VButton);
        directionPanel.add(directionLabel);
        directionPanel.add(VButton);
        VButton.setSelected(true);
        directionPanel.add(HButton);
        VButton.addActionListener(actionListener);
        HButton.addActionListener(actionListener);

        remainPanel = new JPanel();
        //Set up remains text field
        remains = new JTextField();
        remains.setText(defaultRemains);
        remains.setPreferredSize(new Dimension(40, 20));
        remains.setEditable(false);

        //setup remains panel
        remainLabel = new JLabel(REMAIN);
        remainPanel.add(remainLabel);
        remainPanel.add(remains);

        cleanAndSaveButtonPanel = new JPanel();

        //Set up clean and save button
        cleanButton = new JButton(CLEAN);
        cleanButton.addActionListener(actionListener);
        saveButton = new JButton(SAVE);
        saveButton.setEnabled(false);
        saveButton.addActionListener(actionListener);

        //adding the clean and save button to pthe anel
        cleanAndSaveButtonPanel.add(cleanButton);
        cleanAndSaveButtonPanel.add(saveButton);

        //Set up the layout for the dialog and grid panel components
        designModeGridPanel.setLayout(gridLayout);
        designModeGridPanel.setLayout(gridLayout);
        designModeDialog.setSize(500, 500);
        designModeDialog.setResizable(false);
        flowLayout = new FlowLayout();
        designModeDialog.setLayout(flowLayout);

        //Add all the components to the main dialog
        designModeDialog.add(designModeGridPanel);
        dialogControlPanel.add(designModeShipPanel);
        dialogControlPanel.add(directionPanel);
        dialogControlPanel.add(remainPanel);
        dialogControlPanel.add(cleanAndSaveButtonPanel);
        designModeDialog.add(dialogControlPanel);

        //set dialog visbile
        designModeDialog.setVisible(true);
    }

    /**
     * Method name: showRemainsLeft
     * Purpose: Display the remains of the current ship at the remains slot
     * Algorithm: display the remains by invoke setText method
     * @param remains - the current remains of the ship
     * @param remainsTextField - the text field for the game remains slot
     * */
    public void showRemainsLeft(JTextField remainsTextField, int remains) {
        //Convert remains to string and display it
        Integer integerRemains = remains;
        String stringRemains = integerRemains.toString();
        remainsTextField.setText(stringRemains);
    }

    /**
     * Method name: showLive
     * Purpose: Show the life (points) of a player
     * Algorithm: The method display the life on the progress bar by invoke set string method
     * @param life - the life of a player
     * @param lifeProgressBar - the progress bar to display the life of a player
     * */
    public void showLive(JProgressBar lifeProgressBar, int life) {
        lifeProgressBar.setValue(life);
        //Get the percent of left live
        Double percent = lifeProgressBar.getPercentComplete();

        //Display it
        String strPercent = String.format("%.2f", percent * 100);
        lifeProgressBar.setStringPainted(true);
        lifeProgressBar.setString(strPercent + "%");
    }

    /**
     * Method name: initializeMenu
     * Purpose: Initialize the menu component for the game
     * Algorithm: The main menu was consist of two sub menu components and five menu items component
     * */
    public void initializeMenu() {
        //Initialize the submenu game and its items
        jMenuBar = new JMenuBar();
        JMenu game = new JMenu(GAME);
        newItem = new JMenuItem(NEW);
        newItem.setIcon(new ImageIcon("../bin/images/iconnew.gif"));
        solutionItem = new JMenuItem(SOLUTION);
        solutionItem.setIcon(new ImageIcon("../bin/images/iconsol.gif"));
        exitItem = new JMenuItem(EXIT);
        exitItem.setIcon(new ImageIcon("../bin/images/iconext.gif"));

        //Adding all the items of game to the sub menu "Game"
        game.add(newItem);
        game.add(solutionItem);
        game.add(exitItem);
        jMenuBar.add(game);

        //Initialize the submenu help and its items
        JMenu help = new JMenu(HELP);
        colorsItem = new JMenuItem(COLORS);
        colorsItem.setIcon(new ImageIcon("../bin/images/iconcol.gif"));
        aboutItem = new JMenuItem(ABOUT);
        aboutItem.setIcon(new ImageIcon("../bin/images/iconabt.gif"));

        //Adding all the items of help to the sub menu "Help"
        help.add(colorsItem);
        help.add(aboutItem);
        jMenuBar.add(help);

        //initialize colors dialog and set up the layout
        colorsDialog = new JDialog(this, COLORS, true);
        colorsDialog.setSize(600, 200);
        GridLayout layout = new GridLayout(2, 3, 1, 1);
        colorsDialog.setLayout(layout);

        //Set up and initialize three color panel
        Color1Panel = new JPanel();
        Color2Panel = new JPanel();
        Color3Panel = new JPanel();
        Color1Panel.setBackground(color1);
        Color2Panel.setBackground(color2);
        Color3Panel.setBackground(color3);

        //Initialize all the color buttons
        Color1Button = new JButton(UNSELECTED);
        Color2Button = new JButton(WATER);
        Color3Button = new JButton(SHIPC);

        //Adding all the panels and buttons to the dialog
        colorsDialog.add(Color1Panel);
        colorsDialog.add(Color2Panel);
        colorsDialog.add(Color3Panel);
        colorsDialog.add(Color1Button);
        colorsDialog.add(Color2Button);
        colorsDialog.add(Color3Button);

        //Add the menu to frame
        this.setJMenuBar(jMenuBar);
    }

    /**
     * Method name: initializeSolutionDialog
     * Purpose: To initialize the dialog to display the solution for the player
     * Algorithm: The method use double for loop to display each button
     * @param dimension - the current dimension of the game
     * */
    public void initializeSolutionDialog(int dimension) {
        //Initialize and set up the layout of solution dialog
        solutionDialog = new JDialog(this, SOLUTION, true);
        solutionDialog.setSize(600, 600);
        GridLayout gridLayout = new GridLayout(dimension * 2, dimension * 2, 2, 2);
        solutionDialog.setLayout(gridLayout);

        //Initialize each buttons of the grid on solution panel by double for loop
        SolutionPanels = new JPanel[dimension * 2][dimension * 2];
        for (int i = 0; i < dimension * 2; i++) {
            for (int j = 0; j < dimension * 2; j++) {
                JPanel panel = new JPanel();
                SolutionPanels[i][j] = panel;
                solutionDialog.add(SolutionPanels[i][j]);
            }
        }
    }

    /**
     * Method name: changeColorForSolution
     * Purpose: To change the color for the different state of grid button
     * Algorithm: no
     * @param isShip - the boolean to indicates if there is a ship on the button
     * @param posX - the position of the ship on X-axis
     * @param posY - the position of the ship on Y-axis
     * */
    public void changeColorForSolution(boolean isShip, int posX, int posY) {
        if (isShip) {
            SolutionPanels[posX][posY].setBackground(color3);
        } else {
            SolutionPanels[posX][posY].setBackground(color2);
        }
    }

    /**
     * Method name: displaySplash
     * Purpose: To display the splash when game start
     * Algorithm: To display the full splash by add the image and make the thread sleep
     * */
    public void displaySplash() {
        //Set up the splash
        JFrame splash = new JFrame();
        splash.setAlwaysOnTop(true);
        splash.setUndecorated(true);
        splash.setSize(new Dimension(500, 250));
        splash.setResizable(false);
        splash.setLocationRelativeTo(null);
        splash.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        splash.setLayout(new BoxLayout(splash.getContentPane(), BoxLayout.Y_AXIS));

        //Set up and initialize the label and its image
        JLabel aboutImage = new JLabel();
        aboutImage.setIcon(new ImageIcon("../bin/images/game_about.jpg"));
        aboutImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add the image to the splash
        splash.add(aboutImage);
        splash.setVisible(true);

        try {
            Thread.sleep(3000);
            splash.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method name: gameStopSplash
     * Purpose: To show the splash when game ends
     * Algorithm: To display the full splash by add the image of win and lose
     * @param isWin - the boolean indicates that if the player is winner
     * */
    public void gameStopSplash(boolean isWin) {
        JDialog dialogEndGame = new JDialog(this, "GAME END", true);
        if (isWin) {
            //If player win, then set up the winning image and display it
            ImageIcon img = new ImageIcon("../bin/images/game_winner.jpg");
            JLabel endLabel = new JLabel(img);
            dialogEndGame.add(endLabel);
            dialogEndGame.setSize(img.getIconWidth(), img.getIconHeight());
            dialogEndGame.setVisible(true);
        } else {
            //If player lose, then set up the losing image and display it
            ImageIcon img = new ImageIcon("../bin/images/game_lost.jpg");
            JLabel endLabel = new JLabel(img);
            dialogEndGame.add(endLabel);
            dialogEndGame.setSize(img.getIconWidth(), img.getIconHeight());
            dialogEndGame.setVisible(true);
        }
    }

    /**
     * Method name: displayHistory
     * Purpose: display the history log of the game
     * Algorithm: To display the history by inserting new log
     * @param history - the area to display the game history
     * @param log - the generated log while game running
     * */
    public void displayHistory(JTextArea history, String log) {
        history.insert(log + "\n", 0);
    }

    /**
     * Method name: selectColor
     * Purpose: To make user select a color on the dialog
     * Algorithm: Pop up a dialog and make user select color
     * @return the color that user selected on the dialog
     * */
    public Color selectColor() {
        Color color = JColorChooser.showDialog(this, "color", null);        // initialization dialog
        return color;
    }

    /**
     * Method name: displayAboutInfo
     * Purpose: To show the "about" information of the game
     * Algorithm: To display the "about" information on the dialog
     * */
    public void displayAboutInfo() {
        String aboutStr = "Game was designed by Boyu Li";
        aboutStr += "\nBy default, the game will randomly generate ships for both player and machine after game launched, ";
        aboutStr += "\nyou can directly click play button to play.";
        aboutStr += "\nAfter you switch the dimension or finished a game, you will need to click random or design mode ";
        aboutStr += "\nto place ships then click the play button to play.";
        aboutStr += "\nTo win the game, you will need to select a button in the adversary panel to attack you opponent, ";
        aboutStr += "\nif you hit the opponent's ship, then its HP will reduce. After your attacking, the opponent will ";
        aboutStr += "\nattack, if your ship got attacked, you HP will reduce.";
        aboutStr += "\nIf your life goes to zero, then you lost the game, reversely, if machine life goes to zero, you are the winner. ";
        JOptionPane.showMessageDialog(null, aboutStr, ABOUT, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Method name: enterString
     * Purpose: To pop up a dialog to make user enter the configuration string
     * Algorithm: To pop up an input dialog for the configuration string entering
     * @return the configuration string that user just entered
     * */
    public String enterString() {
        String gameConfig = JOptionPane.showInputDialog(null, "Please enter the configuration string", NEW, JOptionPane.QUESTION_MESSAGE);
        return gameConfig;
    }

    /**
     * Method name: switchLanguage
     * Purpose: To implement the language switching component of the game
     * Algorithm: To implement the language switching by bundling the language resources files
     * @param option - the int value indicates the language option
     * */
    public void switchLanguage(int option) {
        String language = "";
        String country = "";
        //Check the language option
        if (option == 0) {
            language = "en";
            country = "US";
        } else {
            language = "zh";
            country = "CN";
        }

        //Bundle the game texts for the locale
        locale = new Locale.Builder().setLanguage(language).setRegion(country).build();
        texts = ResourceBundle.getBundle("GameSetting/texts", locale);

        //Get text in language files
        TITLE = texts.getString("TITLE");
        LANGUAGE = texts.getString("LANGUAGE");
        ENGLISH = texts.getString("ENGLISH");
        CHINESE = texts.getString("CHINESE");
        RESET = texts.getString("RESET");
        SOLUTION = texts.getString("SOLUTION");
        SAVE = texts.getString("SAVE");
        RANDOM = texts.getString("RANDOM");
        TIME = texts.getString("TIME");
        GAME = texts.getString("GAME");
        NEW = texts.getString("NEW");
        EXIT = texts.getString("EXIT");
        HELP = texts.getString("HELP");
        COLORS = texts.getString("COLORS");
        ABOUT = texts.getString("ABOUT");
        COLOR1 = texts.getString("COLOR1");
        COLOR2 = texts.getString("COLOR2");
        COLOR3 = texts.getString("COLOR3");
        DIMENSION = texts.getString("DIMENSION");
        PLAY = texts.getString("PLAY");
        DESIGN = texts.getString("DESIGN");
        SHIP = texts.getString("SHIP");
        DIRECTION = texts.getString("DIRECTION");
        REMAIN = texts.getString("REMAIN");
        CLEAN = texts.getString("CLEAN");
        UNSELECTED = texts.getString("UNSELECTED");
        WATER = texts.getString("WATER");
        SHIPC = texts.getString("SHIPC");
        LIVE1 = texts.getString("LIVE1");
        LIVE2 = texts.getString("LIVE2");
    }
}
