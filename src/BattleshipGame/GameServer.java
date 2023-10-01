/**
 * File name: GameServer
 * identification: Boyu Li 041003345 / Golden Zhang 041051971
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A32
 * Professor: Paulo Sousa
 * Date: 8/6/2023
 * Compiler: Oracle Open JDK - version: 20.0.2 2023-07-18
 * Purpose: To implement the game server to deal with the requests from multiple clients.
 */

package BattleshipGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serial;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class name: GameServer
 * Methods list:
 * 1. GameServer (Overload Constructor)
 * 2. launchServer
 * Constants list: serialVersionUID
 * Purpose: The class impelements all the components of game server
 * 
 * @author Boyu Li / Golden Zhang
 * @version 1.0
 * @see java.util
 * @see javax.swing
 * @see java.awt
 * @see java.io
 * @see java.net
 * @since 20.0.2
 */

public class GameServer extends JFrame {

	/**
     * serialVersionUID
     * */
    @Serial
    private static final long serialVersionUID = 1L;

	/**
	 * The socket for server running
	 */
	transient ServerSocket serverSocket = null;

	/**
	 * The socket for clients running
	 */
	transient Socket clientSocket = null;

	/**
	 * Indicates whether the server is running
	 */

	boolean isRunning = false;

	/**
	 * Count the number of total connected client
	 */

	int totalNumber = 0;

	/**
	 * Count the number of current connected client
	 * 
	 */

	int clientNumber = 0;

	/**
	 * Stores the configuration string
	 */
	String configString = "";

	/**
	 * check box of finalize
	 */

	JCheckBox finalizeCheckBox;

	/**
	 * button of end
	 */

	JButton endButton;

	/**
	 * button fo start
	 */

	JButton startButton;

	/**
	 * button of result
	 */

	JButton resultsButton;

	/**
	 * area to display server log
	 */

	JTextArea logArea;

	/**
	 * textfield of server port
	 */

	JTextField portTextField;

	/**
	 * Method name: GameServer
	 * Purpose: Overload constructor
	 * Algorithm: no
	 */
	GameServer() {
	}

	/**
	 * Method name: launchServer
	 * Purpose: to launch the server interface
	 * Algorithm: set up the server interface and the listener for its components
	 */
	public void launchServer() {
		//Refresh the interface
		this.getContentPane().removeAll();
		this.revalidate();
		this.repaint();

		//Set up the main interface
		this.setTitle("Game Client - Boyu Li 345 / Golden Zhang 971");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());

		//Setup the center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		//Set up and title panel 
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		//Set up the title and add it to title panel
		JLabel title = new JLabel();
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setIcon(new ImageIcon("../bin/images/server.png"));
		titlePanel.add(title);

		//Set up the board panel
		JPanel boardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		//Set up the port text field
		JLabel portLabel = new JLabel("Port: ");
		portTextField = new JTextField(Integer.toString(GameConfig.DEFAULT_PORT));
		portTextField.setPreferredSize(new Dimension(50, 17));

		//Setup the texts for all the buttons
		startButton = new JButton("Start");
		resultsButton = new JButton("Results");
		finalizeCheckBox = new JCheckBox("finalize");
		endButton = new JButton("End");

		//add all the components to the board panel
		boardPanel.add(portLabel);
		boardPanel.add(portTextField);
		boardPanel.add(startButton);
		boardPanel.add(resultsButton);
		boardPanel.add(finalizeCheckBox);
		boardPanel.add(endButton);

		//Set up log area 
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		logArea = new JTextArea(10, 35);
		logArea.setLineWrap(true);
		logArea.setEditable(false);

		//Set up the scroll pane for log area
		JScrollPane logScrollPane = new JScrollPane(logArea);
		textPanel.add(logScrollPane);

		//Add all the componenns to the center panels
		centerPanel.add(titlePanel);
		centerPanel.add(boardPanel);
		centerPanel.add(textPanel);

		//Add the center panel to the main interface
		this.add(centerPanel);


		//Intialize a server object
		Server server = new Server();

		//Setup all the listeners for the components
		startButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRunning)
					server.bindServerOnPort();
			}

		});

		endButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				server.closeServer();
			}

		});

		resultsButton.addActionListener(new ActionListener() { // Add button event

			@Override
			public void actionPerformed(ActionEvent e) {
				server.displayResults();
				;
			}

		});

		this.setVisible(true);
	}

	/**
	 * Class name: Server
	 * Methods list:
	 * 1. run (Overload method)
	 * 2. bindServerOnPort
	 * 3. displayResults
	 * 4. closeServer
	 * Constants list: NULL
	 * Purpose: The implementaion of runnable interface
	 * 
	 * @author Boyu Li / Golden Zhang
	 * @version 1.0
	 * @see java.util
	 * @see javax.swing
	 * @see java.awt
	 * @see java.io
	 * @see java.net
	 * @since 20.0.2
	 */

	class Server implements Runnable {
		/**
		 * The Arraylist to hold clients' threads
		 */
		ArrayList<ClientThread> clientThreads = new ArrayList<ClientThread>(5);

		/**
		 * Method name: run
		 * Purpose: Overwrite method to start a new thread for client
		 * Algorithm: no
		 */

		@Override
		public void run() {
			while (isRunning) {
				logArea.insert("Waiting for the requests from client...\n", logArea.getDocument().getLength());
				try {
					clientSocket = serverSocket.accept();
				} catch (IOException ioe) {
					// break out when the server is not running
					if (!isRunning)
						return;
					JOptionPane.showMessageDialog(null, ioe, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}

				ClientThread clientThread = new ClientThread(clientSocket);
				clientThread.start();
				clientThreads.add(clientThread);
			}
		}

		/**
		 * Method name: message
		 * Purpose: bind server socket at a specific port
		 * Algorithm: no
		 */
		public void bindServerOnPort() {
			logArea.insert("Trying to bind server on the specific port...\n", logArea.getDocument().getLength());
			try {
				//bind the server socket at the port
				serverSocket = new ServerSocket(Integer.valueOf(portTextField.getText()));
				logArea.insert("The server socket is listening on port " + portTextField.getText() + "\n",
						logArea.getDocument().getLength());
				isRunning = true;

				//Start a new thread
				Thread serverT = new Thread(this);
				serverT.start();
			} 
			// Catch IOException and show it on the dialog
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe, "I/O Exception", JOptionPane.ERROR_MESSAGE);
			}
			catch (IllegalArgumentException portOutOfRange) {
				JOptionPane.showMessageDialog(null, portOutOfRange, "Port Out Of Range Error", JOptionPane.ERROR_MESSAGE);
				logArea.insert("Port out of range!\n", logArea.getDocument().getLength());
			}
		}

		/**
		 * Method name: message
		 * Purpose: display the game results in a dialog
		 * Algorithm: use for loop to retrieve each client information
		 */
		public void displayResults() {
			if (clientThreads.size() > 0) {
				String results = "Game Results: \n";
				for (int i = 0; i < clientThreads.size(); i++) {
					results += clientThreads.get(i).getInfo() + "\n";
				}
				//Accumalte all the clients' information and show it on the dialog
				JOptionPane.showMessageDialog(null, results, "Results", JOptionPane.PLAIN_MESSAGE);
			} else {
				//If there is no player, display the error on dialog
				JOptionPane.showMessageDialog(null, "No clients' information, nothing can be displayed",
						"No Client Information", JOptionPane.ERROR_MESSAGE);
				logArea.insert("No clients' information, nothing can be displayed\n",
						logArea.getDocument().getLength());
			}
		}

		/**
		 * Method name: message
		 * Purpose: close all the threads of client and the server
		 * Algorithm: use for loop to close each thread of client
		 */
		private void closeServer() { // break link
			try {
				if (isRunning) {
					isRunning = false;
					//Close all the thread of the clients
					for (int i = clientThreads.size() - 1; i >= 0; i--) {
						clientThreads.get(i).closeIOStream();
					}
					//Close the server socket
					serverSocket.close();
				}
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe, "I/O Exception", JOptionPane.ERROR_MESSAGE);
			}
			GameServer.this.dispose();
		}

		/**
		 * Class name: ClientThread
		 * Methods list:
		 * 1. ClientThread (Overload Constructor)
		 * 2. getInfo
		 * 3. closeIOStream
		 * 4. removeClient
		 * 5. sendDataToClient
		 * 6. ReceiveDataFromClient
		 * 7. receiveClientInfo
		 * 8. run
		 * Constants list: NULL
		 * Purpose: The implementation of the Thread interface
		 * 
		 * @author Boyu Li / Golden Zhang
		 * @version 1.0
		 * @see java.util
		 * @see javax.swing
		 * @see java.awt
		 * @see java.io
		 * @see java.net
		 * @since 20.0.2
		 */

		class ClientThread extends Thread {

			/**
			 * received data
			 */
			String data = "";

			/**
			 * user information
			 */
			String info = "";

			/**
			 * stream for input
			 */
			DataInputStream input = null;

			/**
			 * stream for output
			 */
			DataOutputStream output = null;

			/**
			 * socket for thread running
			 */
			Socket threadSocket = null;

			/**
			 * Method name: ClientThread
			 * Purpose: Overload constructor
			 * Algorithm: initialize input and output streams
			 * 
			 * @param clientSocket- the socket of the client
			 */

			public ClientThread(Socket clientSocket) {
				//Increase the counter of the thread
				this.threadSocket = clientSocket;
				totalNumber++;
				clientNumber++;

				try {
					//Intialize input and output streams
					input = new DataInputStream(clientSocket.getInputStream());
					output = new DataOutputStream(clientSocket.getOutputStream());
					output.writeInt(totalNumber); 
				} 
				// Catch IOException and show it on the dialog
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
				// log success
				logArea.insert("Client ID: " + totalNumber + " connected to the server\n",
						logArea.getDocument().getLength());
			}

			/**
			 * Method name: getInfo
			 * Purpose: getter method for user information
			 * Algorithm: no
			 * 
			 * @return the user information
			 */

			public String getInfo() {
				return this.info;
			}

			/**
			 * Method name: closeIOStream
			 * Purpose: close input and output streams
			 * Algorithm: no
			 */

			public void closeIOStream() { // close stream
				try {
					//Close the input and output thread
					output.close();
					input.close();
					threadSocket.close();
				}
				// Catch IOException and show it on the dialog
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}

			/**
			 * Method name: removeClient
			 * Purpose: remove a specific client thread
			 * Algorithm: remove the client thread and input and output stream
			 * 
			 * @param clientId - the id of the client that need to be removed
			 */

			private void removeClient(String clientId) {
				logArea.insert("Client ID: " + clientId + " has been disconnect from server\n",
						logArea.getDocument().getLength());

				//Remove the client thread from the arraylist
				clientThreads.remove(clientThreads.indexOf(this));
				//Decrease the counter
				clientNumber--;
				//Close all the streams
				closeIOStream();
				//If the client number is zero and fianlized box was checked, close the server
				if (clientNumber == 0 && finalizeCheckBox.isSelected())
					closeServer();
			}

			/**
			 * Method name: sendDataToClient
			 * Purpose: send data to a specific client
			 * Algorithm: no
			 * 
			 * @param clientId - the id of the client that need to be sent message
			 */

			private void sendDataToClient(String clientId) {
				try {
					//Send configuration string through output stream to client
					output.writeUTF(configString);
					logArea.insert("Sent configuration string to the client: #" + clientId + "\n",
							logArea.getDocument().getLength());
				} 
				// Catch IOException and show it on the dialog
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}

			/**
			 * Method name: ReceiveDataToClient
			 * Purpose: receive data from a specific client
			 * Algorithm: no
			 * 
			 * @param clientData - the recived client data
			 */

			private void ReceiveDataFromClient(String clientData) {
				try {
					// response the client
					output.writeUTF("Server Response: your configuration string has been received.");
					//
					configString = clientData;
					logArea.insert(
							"Received configuration string from client\nThe configuration string is: " + configString
									+ "\n",
							logArea.getDocument().getLength());
				} 
				// Catch IOException and show it on the dialog
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}

			}

			/**
			 * Method name: receiveDataToClient
			 * Purpose: send data to a specific client
			 * Algorithm: no
			 * 
			 * @param clientId - the id of the client that need to be sent message
			 * @param received - the received client information
			 */

			private void receiveClientInfo(String clientId, String received) {
				try {
					//Response the client
					output.writeUTF("Server Response: your game information has been received");
					data = "Player[" + clientId + "]: ";
					data += received;
					this.info = data;
					logArea.insert("Received the information from client: #" + clientId + "\n",
							logArea.getDocument().getLength());
				} 
				// Catch IOException and show it on the dialog
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}

			/**
			 * Method name: sendDataToClient
			 * Purpose: receieve protocol from client
			 * Algorithm: utilize the input stream to recieve protocol and use tokenizer to
			 * recongnize data
			 */
			public void run() {
				String clientId = "";
				String protocolId = "";
				String receivedData = "";
				StringTokenizer st;

				try {
					//Check if the protocol is first protocl
					while (!protocolId.equals("P0")) {
						//Read the data through input stream
						data = input.readUTF();
						
						logArea.insert(data + "\n", logArea.getDocument().getLength());

						st = new StringTokenizer(data, "#");
						//Read each piece of data
						clientId = st.nextToken();
						protocolId = st.nextToken();

						//Check if there are more tokens
						if (st.hasMoreTokens()) {
							receivedData = st.nextToken();
						}

						//Do the different tasks based on the protocol IDs
						switch (protocolId) {
							case "P1":
								ReceiveDataFromClient(receivedData);
								break;
							case "P2":
								sendDataToClient(clientId);
								break;
							case "P3":
								receiveClientInfo(clientId, receivedData);
								break;
						}
					}
					removeClient(clientId);
				} 
				// Catch SocketException and show it on the dialog
				catch (SocketException e) {
					// log failure
					JOptionPane.showMessageDialog(null, "connection terminated!", "Connection Error",
							JOptionPane.ERROR_MESSAGE);
					logArea.insert("connection terminated!\n",
							logArea.getDocument().getLength());
				}
				// Catch IOException and show it on the dialog
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, e, "I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
