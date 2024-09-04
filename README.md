# Battleship
The project implements the MVC framework to design the game 'Battleship' by Java Swing components. The C/S architecture was implemented to provide a solution for multiple players.

# Battleship Game GUI:
![image](https://github.com/Boyu422/Battleship/blob/main/image/README/game.png)

# Game Server:
![image](https://github.com/Boyu422/Battleship/blob/main/image/README/server.png)

# Game Client:
![image](https://github.com/Boyu422/Battleship/blob/main/image/README/client.png)

# Game launcher:
![image](https://github.com/Boyu422/Battleship/blob/main/image/README/luncher.png)

Game features:
- The game rule is as same as game Battleship itself
- The game supports internationlization, Chinese' and English are currently supported
- The game allows multiple players
- The game supports design mode and random mode

MVC Game Components:
- GameModel Class: Stores all the game running data such as user player's life, game playing time, the ship state information...
- GameView Class: Implements the game user interface by Java Swing Components
- GameController Class: Handles all the game events such as hitting adversary ship by Event Listeners

C/S Architecture for Servlet:
The project supports intercommucation between game client and game server through TCP protocol with desinged Protocol.
- GameServer:
  1. The server will handle all the TCP connections by socket and threads, the server is able to handle multiple threads under multi-threading mode
  2. The server is able to transmit and receive the game configuration and game data from the game client
  3. After the game finished, the server will rank all the players by their points
  4. The game server processes the client request by tokenize each request to check its protocol, then the server
     will response the request

Compile the project:
The Windows Bash for project compilation is available, you can just simply run the bash 'JAP-A123.bat' to compile the program. Once the 
project successfully compiled, the binary code will be generated and the project will be automatcially launched.

Upcomming features:
1. Dual player versus mode
2. Linux compailation bash
3. Colored user interface
4. MySQL database association by JDBC
