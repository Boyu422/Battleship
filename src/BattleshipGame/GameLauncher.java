/**
 * File name: LaunchGame
 * identification: Boyu Li 041003345
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A12
 * Professor: Paulo Sousa
 * Date: 6/2/2023
 * Compiler: Oracle Open JDK - version: 20.0.1 2023-04-18
 * Purpose: The start point of the game
 * */

package BattleshipGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class name: LaunchGame
 * Methods list:
 * 1. Game Launcher (Overload Constructor)
 * 2. main
 * Constants list: serialVersionUID
 * Purpose: The class is the starting point of the battleship game
 * 
 * @author Boyu Li
 * @version 1.0
 * @since 20.0.1
 */
public class GameLauncher extends JFrame {

    /**
     * serialVersionUID
     * */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Button of game client
     */
    JButton gameClientButton;

    /**
     * button of game server
     */

    JButton gameServerButton;

    /**
     * button of MVC
     */

    JButton MVCButton;

    /**
     * Method name: GameLauncher
     * Purpose: Overload constructor
     * Algorithm: setup all the interface and listener of all the components
     */

    GameLauncher() {
        this.setTitle("Game Launcher - Boyu Li 345");
        this.setLayout(new FlowLayout());
        gameClientButton = new JButton("Game Client");
        this.add(gameClientButton);
        gameServerButton = new JButton("Game Server");
        this.add(gameServerButton);
        MVCButton = new JButton("Game MVC");
        this.add(MVCButton);

        gameServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameServer gameServer = new GameServer();
                gameServer.launchServer();
            }
        });

        gameClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameClient gameClient = new GameClient();
                gameClient.launchClient();
            }
        });

        MVCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GameModel gameModel = new GameModel();
                GameView gameView = new GameView();
                GameController gameController = new GameController(gameView, gameModel);
            }
        });

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(400, 70));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Method name: main
     * Purpose: The starting point of the project
     * Algorithm: no
     * 
     * @param args - the command line arguments that used to run the project
     */
    public static void main(String args[]) {
        GameLauncher gameLauncher = new GameLauncher();
    }
}
