# Battleship Game

This project is a Java-based implementation of the classic game **Battleship**, designed using the **MVC (Model-View-Controller)** framework. The game supports multiplayer functionality through a **Client-Server (C/S)** architecture. All source code is available in the `src` directory.

## Screenshots

### Game Interface:
![Game GUI](https://github.com/Boyu422/Battleship/blob/main/image/README/game.png)

### Server Interface:
![Game Server](https://github.com/Boyu422/Battleship/blob/main/image/README/server.png)

### Client Interface:
![Game Client](https://github.com/Boyu422/Battleship/blob/main/image/README/client.png)

### Game Launcher:
![Game Launcher](https://github.com/Boyu422/Battleship/blob/main/image/README/luncher.png)

## Game Features
- Follows the traditional rules of [Battleship](https://en.wikipedia.org/wiki/Battleship).
- Supports **internationalization** with English and Chinese languages available.
  ![Internationalization](https://github.com/Boyu422/Battleship/blob/main/image/README/internatioanliziation.png)
  
- Multiplayer functionality via client-server architecture.
- Offers both **Design Mode** and **Random Mode** for ship placement:
  - **Design Mode:** Players manually place ships for their opponents.
    ![Design Mode](https://github.com/Boyu422/Battleship/blob/main/image/README/Design%20Mode.png)
  - **Random Mode:** Opponent's ships are randomly placed.
  
- Adjustable game dimensions, allowing players to increase difficulty by expanding the grid size (up to 9x9).
  ![Dimensions](https://github.com/Boyu422/Battleship/blob/main/image/README/Dimension.png)
  
- **Solution View** option allows players to view the correct ship placements after a game.
  ![Solution](https://github.com/Boyu422/Battleship/blob/main/image/README/Solution.png)
  
- **Color Customization** allows players to personalize their game board's appearance.
  ![Colors Customization](https://github.com/Boyu422/Battleship/blob/main/image/README/Colors.png)

## MVC Components

- **GameModel Class:** Manages core game data, including player health, game time, and ship status.
- **GameView Class:** Implements the graphical user interface using Java Swing components.
- **GameController Class:** Handles game interactions, such as responding to player actions and managing game flow through event listeners.

## Client-Server (C/S) Architecture for Multiplayer

The multiplayer functionality is supported by a custom communication protocol over **TCP**. Here's how the server and client interact:

- **GameServer:**
  1. Manages all TCP connections using sockets and threads for handling multiple clients.
  2. Facilitates sending and receiving game configurations and player data.
  3. Provides player rankings based on performance at the end of each game.
  4. Processes client requests by tokenizing, verifying, and responding according to the game’s protocol.

## Compilation Instructions

To compile the project on Windows, ensure you have **OpenJDK 20.0.2** or higher installed. Run the provided batch script `JAP-A123.bat` to compile the source code. Upon successful compilation, the game will automatically launch with the generated binaries.

## Upcoming Features
1. **Two-player versus mode**
2. **Linux support** with a new compilation script
3. **Enhanced color UI** for a more dynamic gaming experience
4. **NoSQL database integration** using MongoDB for storing player data and scores
