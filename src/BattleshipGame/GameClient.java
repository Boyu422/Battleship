/**
 * File name: GameClient
 * identification: Boyu Li 041003345 / Golden Zhang 041051971
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A32
 * Professor: Paulo Sousa
 * Date: 8/6/2023
 * Compiler: Oracle Open JDK - version: 20.0.2 2023-07-18
 * Purpose: To implement the game client to communicate with the game server.
 */

package BattleshipGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * Class name: GameClient
 * Methods list:
 * 1. GameClient (Overload Constructor)
 * 2. launchClient
 * Constants list: serialVersionUID
 * Purpose: To implement the interface and listener of the game client
 * 
 * @author Boyu Li / Golden Zhang
 * @version 1.0
 * @see javax.swing
 * @see java.awt
 * @see java.io
 * @see java.net
 * @see java.awt.event
 * @since 20.0.2
 */

public class GameClient extends JFrame {

	/**
	 * serialVersionUID
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	GameClient() {
	}

	/**
	 * button of end
	 */

	JButton buttonEnd;

	/**
	 * button of connect
	 */

	JButton connectButton;

	/**
	 * button of new game
	 */

	JButton newGameButton;

	/**
	 * button of send game
	 */

	JButton sendGameButton;

	/**
	 * button of receive game
	 */

	JButton receiveGameButton;

	/**
	 * button of send data
	 */

	JButton sendDataButton;

	/**
	 * button of play
	 */

	JButton playButton;

	/**
	 * area to display client log
	 */

	JTextArea logArea;

	/**
	 * area to display user name
	 */

	JTextArea nameArea;

	/**
	 * area to display port
	 */

	JTextArea portArea;

	/**
	 * area to display IP address
	 */

	JTextArea ipArea;

	/**
	 * Socket for client running
	 */

	transient Socket clientSocket = null;

	/**
	 * stream for input
	 */

	transient DataInputStream input = null;

	/**
	 * stream for output
	 */

	transient DataOutputStream output = null;

	/**
	 * Stores the ID of a client
	 */
	int clientId = -1;

	/**
	 * Indicates if the client connect to the server
	 */

	boolean isConnected = false;

	/**
	 * Indicates if the client is playing
	 */

	boolean isPlay = false;

	/**
	 * Indicates if the game has been initialized
	 */

	boolean isGameGenerated = false;

	/**
	 * Configuration string for a game
	 */

	String configString = "";

	/**
	 * The data for a game
	 */

	String gameData = "";

	/**
	 * The game modal object
	 */

	transient GameModel gameModel = new GameModel();

	/**
	 * Method name: launchClient
	 * Purpose: launch the client interface
	 * Algorithm: set up the interface of the client and implement all the listener
	 * for the components
	 */

	public void launchClient() {
		// Refresh the interface
		this.getContentPane().removeAll();
		this.revalidate();
		this.repaint();

		// Setup the interface
		this.setTitle("Game Client - Boyu Li 345 / Golden Zhang 971");
		this.setSize(570, 520);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Initialize window

		// Set up the center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		// Set up the title panel
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel();

		// Set up the title and add it to the panel
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setIcon(new ImageIcon("../bin/images/client.png"));
		titlePanel.add(title);

		// Setup up the board panel
		JPanel boardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		// Set up the name area
		JLabel userNameLabel = new JLabel("User: ");
		nameArea = new JTextArea(GameConfig.DEFAULT_USER);
		nameArea.setPreferredSize(new Dimension(75, 17));

		// Set up the ip area
		JLabel ipLabel = new JLabel("Server: ");
		ipArea = new JTextArea(GameConfig.DEFAULT_ADDR);
		ipArea.setPreferredSize(new Dimension(75, 17));

		// Set up the port area
		JLabel portLabel = new JLabel("Port: ");
		portArea = new JTextArea(Integer.toString(GameConfig.DEFAULT_PORT));
		portArea.setPreferredSize(new Dimension(50, 17));

		// Add text to all the button
		connectButton = new JButton("Connect");
		buttonEnd = new JButton("End");
		newGameButton = new JButton("New game");
		sendGameButton = new JButton("Send game");
		receiveGameButton = new JButton("Receive game");
		sendDataButton = new JButton("Send data");
		playButton = new JButton("Play");

		// Set up the log area
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		logArea = new JTextArea(10, 35);
		logArea.setLineWrap(true);
		logArea.setEditable(false);

		// Set up the log scrollPane
		JScrollPane logScrollPane = new JScrollPane(logArea);
		textPanel.add(logScrollPane);

		// Add all the components to the board panel
		boardPanel.add(userNameLabel);
		boardPanel.add(nameArea);
		boardPanel.add(ipLabel);
		boardPanel.add(ipArea);
		boardPanel.add(portLabel);
		boardPanel.add(portArea);
		boardPanel.add(connectButton);
		boardPanel.add(buttonEnd);
		boardPanel.add(newGameButton);
		boardPanel.add(sendGameButton);
		boardPanel.add(receiveGameButton);
		boardPanel.add(sendDataButton);
		boardPanel.add(playButton);

		// add all the panel to center panel and to main frame
		centerPanel.add(titlePanel);
		centerPanel.add(boardPanel);
		centerPanel.add(textPanel);
		this.add(centerPanel);

		// Intialize a client object
		Client client = new Client();

		// Add the listeners for each component
		connectButton.addActionListener(new ActionListener() { // Add button event
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isConnected)
					client.connectServer();
			}

		});

		buttonEnd.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				client.closeClient();
			}
		});

		newGameButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				client.generateConfigStr();
				isGameGenerated = true;
			}
		});

		sendGameButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isGameGenerated) {
					client.sendConfigStr();
				} else {
					JOptionPane.showMessageDialog(null,
							"Game has no been initialized yet, please receive or generate right a new first!",
							"Game Not Initialized Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		receiveGameButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				client.receiveConfigStr();
				isGameGenerated = true;
			}
		});

		sendDataButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				client.sendUserInfo();
			}
		});

		playButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isGameGenerated) {
					client.playGame();
				} else {
					JOptionPane.showMessageDialog(null,
							"Game has no been initialized yet, please receive or generate right a new first!",
							"Game Not Initialized Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/*
		 * gameModel.initializeData();
		 * gameModel.newGame(configString);
		 */

		// Visualize the interface

		isGameGenerated = false;
		this.setVisible(true);
	}

	/**
	 * Class name: Client
	 * Methods list:
	 * 1. Client (Overload Constructor)
	 * 2. connectServer
	 * 3. generateConfigStr
	 * 4. sendConfigStr
	 * 5. sendUserInfo
	 * 6. receiveConfigStr
	 * 7. playGame
	 * 8. closeClient
	 * Constants list: NULL
	 * Purpose: To implement all the components of a game client
	 * 
	 * @author Boyu Li / Golden Zhang
	 * @version 1.0
	 * @see javax.swing
	 * @see java.awt
	 * @see java.io
	 * @see java.net
	 * @see java.awt.event
	 * @since 20.0.2
	 */
	class Client {

		/**
		 * Method name: transmitData
		 * Purpose: transmit the protocol and data to the game server
		 * Algorithm: utilize the stream for input and output
		 * 
		 * @param protocolId - the ID of the protocol
		 * @param data       - the data for transmission
		 * @return whether successfully transmit the data
		 */

		private boolean transmitData(String protocolId, String data, int dimension) { // Send the protocol code to the
			// transimit the data to server through output stream
			try {
				output.writeUTF(
						clientId + GameConfig.PROTOCOL_SEPARATOR + protocolId + GameConfig.PROTOCOL_SEPARATOR + data);
				return true;
			} // To catch the IO exception
			catch (IOException e) {
				// Show the exception on dialog
				JOptionPane.showMessageDialog(null, "You didn't connect to the server yet...", "Connection Error",
						JOptionPane.ERROR_MESSAGE);
				logArea.insert("You didn't connect to the server yet...\n", logArea.getDocument().getLength());
				isConnected = false;
				return false;
			}
		}

		/**
		 * Method name: connectServer
		 * Purpose: connect the client to the server
		 * Algorithm: connect to the server through the IP and port information
		 */

		public void connectServer() { // linked server
			try {
				// connect to server and
				clientSocket = new Socket(ipArea.getText(), Integer.valueOf(portArea.getText()));
				output = new DataOutputStream(clientSocket.getOutputStream());
				input = new DataInputStream(clientSocket.getInputStream());
				// receive client id from server through input stream
				clientId = input.readInt();
				isConnected = true;
				logArea.insert("Connecting to the server...\n", logArea.getDocument().getLength());
				logArea.insert(
						"Successfully connect to the server at " + ipArea.getText() + ": " + portArea.getText() + "\n",
						logArea.getDocument().getLength());
			}
			// Catch both unknownHostException and IOException and show them on dialog
			catch (UnknownHostException uhe) {
				JOptionPane.showMessageDialog(null, uhe, "Unknown Host Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "No available server at the specific port", "No Server Found",
						JOptionPane.ERROR_MESSAGE);
				logArea.insert("No available server at the specific port\n", logArea.getDocument().getLength());
			}
			catch (IllegalArgumentException portOutOfRange) {
				JOptionPane.showMessageDialog(null, portOutOfRange, "Port Out Of Range Error", JOptionPane.ERROR_MESSAGE);
				logArea.insert("Port out of range!\n", logArea.getDocument().getLength());
			}
		}

		/**
		 * Method name: generateConfigStr
		 * Purpose: Randomly generate a configuration string
		 * Algorithm: Invoke the method from gameModel to generate
		 * the board
		 */

		private void generateConfigStr() {
			// Intialize game and generate a configuration string
			gameModel.initializeData();
			gameModel.generateRanShips(true);

			// Encapsulaze the configuration string and store it
			configString = gameModel.getGameDimension() + GameConfig.FIELD_SEPARATOR + gameModel.getsConfig();
			logArea.insert("Generating a new game\n", logArea.getDocument().getLength());
			logArea.insert("The generated game configuration : " + configString + "\n",
					logArea.getDocument().getLength());
		}

		/**
		 * Method name: sendConfigStr
		 * Purpose: Send the configuration string to server
		 * Algorithm: Utilize the output stream to transmit the data
		 */
		private void sendConfigStr() { // Send game information to the server
			// Check if the client connect to the server
			if (isConnected) {
				// encapsulate configuration string to the server
				configString = gameModel.getGameDimension() + GameConfig.FIELD_SEPARATOR + gameModel.getsConfig();
				if (transmitData(GameConfig.PROTOCOL_SENDGAME, configString, gameModel.getGameDimension())) {
					try {
						// Send the configuration string to server through input stream
						String reply = input.readUTF();
						logArea.insert("Game configuration sent!\n", logArea.getDocument().getLength());
						logArea.insert(reply + "\n", logArea.getDocument().getLength());
					}
					// Catch the IOException and show it on the dialog
					catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Server Connection lost!", "Connection Error",
								JOptionPane.ERROR_MESSAGE);
						logArea.insert("Server connection lost!\n", logArea.getDocument().getLength());
						isConnected = false;
					}
				}
			} else {
				// if not, show the error on the dialog
				JOptionPane.showMessageDialog(null, "You didn't connect to the server yet...", "Connection Error",
						JOptionPane.ERROR_MESSAGE);
				logArea.insert("You didn't connect to the server yet...\n", logArea.getDocument().getLength());
			}
		}

		/**
		 * Method name: sendUserInfo
		 * Purpose: Send the user game data to the server
		 * Algorithm:Utialize output stream to transmit the data
		 */
		private void sendUserInfo() {
			//Check if the game is connected and the model has been initalized
			if (isConnected && gameModel != null) {
				gameData = nameArea.getText();
				gameData += gameModel.getGameData();
				if (transmitData(GameConfig.PROTOCOL_DATA, gameData, gameModel.getGameDimension())) {
					// log success
					try {
						//Send the game data to the server through stream
						String reply = input.readUTF();
						logArea.insert("Send game information to the server\n", logArea.getDocument().getLength());
						logArea.insert(reply + "\n", logArea.getDocument().getLength());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Server connection lost!", "Connection Error",
								JOptionPane.ERROR_MESSAGE);
						logArea.insert("Server connection lost!\n", logArea.getDocument().getLength());
						isConnected = false;
					}
				}
			} else {
				//If not, show the error on dialog
				JOptionPane.showMessageDialog(null, "You didn't connect to the server yet...","Connection Error", JOptionPane.ERROR_MESSAGE);
				logArea.insert("You didn't connect to server yet...\n", logArea.getDocument().getLength());
			}
		}

		/**
		 * Method name: receiveConfigStr
		 * Purpose: Receive configuration string from game server
		 * Algorithm:Use input stream to receive data
		 */
		private void receiveConfigStr() {
			//Check if the client connect to the server
			if (isConnected) {
				logArea.insert("Receiving game configuration from server...\n", logArea.getDocument().getLength());
				if (transmitData(GameConfig.PROTOCOL_RECVGAME, "", gameModel.getGameDimension())) {
					try {
						//Receive configuration string through input stream
						configString = input.readUTF();
						logArea.insert("Received game configuration from the server: " + configString + "\n",
								logArea.getDocument().getLength());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Server Connection lost!", "Connection Error",
								JOptionPane.ERROR_MESSAGE);
						logArea.insert("Connection lost\n", logArea.getDocument().getLength());
						isConnected = false;
					}
				}
			} else {
				// if not, show the error on the dialog
				JOptionPane.showMessageDialog(null, "You didn't connect to the server yet...", "Connection Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		/**
		 * Method name: playGame
		 * Purpose: generate a game with configuration string
		 * Algorithm: no
		 */
		private void playGame() {
			//Launch the game and place it to the Event Queue
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					GameView gameView = new GameView();
					GameController gameController = new GameController(gameView, gameModel);
					gameModel.newGame(configString.split(";")[1]);
				}
			});

		}

		/**
		 * Method name: closeClient
		 * Purpose: Disconnect the client from server
		 * Algorithm: close the socket for the client
		 */
		public void closeClient() {
			if (isConnected) {
				transmitData(GameConfig.PROTOCOL_END, "", gameModel.getGameDimension());
				try {
					//Close the stream and socket
					output.close();
					clientSocket.close();
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, ioe, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
			GameClient.this.dispose();
		}
	}
}
