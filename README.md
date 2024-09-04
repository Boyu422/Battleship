# Battleship

This project implements the MVC (Model-View-Controller) framework to design the classic game "Battleship" using Java Swing components. The Client-Server (C/S) architecture is implemented to support multiplayer functionality. All source code is located in the `src` directory.

## Screenshots

### Battleship Game GUI:
![Game GUI](https://github.com/Boyu422/Battleship/blob/main/image/README/game.png)

### Game Server:
![Game Server](https://github.com/Boyu422/Battleship/blob/main/image/README/server.png)

### Game Client:
![Game Client](https://github.com/Boyu422/Battleship/blob/main/image/README/client.png)

### Game Launcher:
![Game Launcher](https://github.com/Boyu422/Battleship/blob/main/image/README/luncher.png)

## Game Features
- The game follows the standard rules of [Battleship](https://en.wikipedia.org/wiki/Battleship).
- Supports internationalization with both English and Chinese currently available.
  
  __Internationalization:__
  ![Internationalization](https://github.com/Boyu422/Battleship/blob/main/image/README/internatioanliziation.png)

- Allows for multiple players.
- Offers both design mode and random mode for ship placement.
  __Design Mode:__ The players can manually place the ships for their opponents.
  ![image](https://github.com/Boyu422/Battleship/blob/main/image/README/Design%20Mode.png)
  __Ramdom Mode:__ The opponent's ships will be randomly generated.
- Players can increase the difficulties by increasing the dimensions of the game up to 9.
  __Dimensions:__ ![image](https://github.com/Boyu422/Battleship/blob/main/image/README/Dimension.png)
- Players can check the solution from the menu.  
  __Solutions:__ ![image](https://github.com/Boyu422/Battleship/blob/main/image/README/Solution.png)
- Players can customize the colors from the menu.
  __Colors Customization:__ ![image](https://github.com/Boyu422/Battleship/blob/main/image/README/Colors.png)

## MVC Components

- **GameModel Class:** Manages all game data, such as player health, game time, and ship status.
- **GameView Class:** Implements the game’s user interface using Java Swing components.
- **GameController Class:** Handles game events like hitting an adversary's ship through event listeners.

## C/S Architecture for Multiplayer

The project facilitates communication between the game client and server via the TCP protocol using a custom-designed protocol.

- **GameServer:**
  1. Manages all TCP connections using sockets and threads, supporting multiple threads in multi-threading mode.
  2. Transmits and receives game configuration and data from the client.
  3. Ranks players by their points after the game concludes.
  4. Processes client requests by tokenizing each request, verifying the protocol, and responding accordingly.

## Compilation Instructions

To compile the project on Windows, ensure that your OpenJDK version is 20.0.2 or higher. You can compile the program by running the provided batch file `JAP-A123.bat`. Upon successful compilation, the binary code will be generated, and the project will automatically launch.

## Upcoming Features

1. Dual-player versus mode
2. Linux compilation script
3. Enhanced colored user interface
4. NoSQL database integration using MongoDB
