# Design Tic-Tac-Toe #

![Screenshot 2024-01-20 at 6 15 53 PM](https://github.com/deepanbme/TicTacToe/assets/57568829/ed1c6a8b-2900-41f8-923f-717e05d702b5)


## What is Tic-Tac-Toe? ##

TicTacToe is a two-player game conducted on a 3 x 3 board. Each participant is assigned a symbol (either X or O). At the start, the board is vacant. Players take turns placing their symbols in any available slot. The first player to successfully align their symbols along an entire row, column, or diagonal emerges as the winner.

Requirements:
1. Board can be of size NxN.
2. There can be N-1 players.
3. Each player can choose a symbol to play the game.
4. A human player can compete against a Bot or Human player.
5. A Player can have a name, email and profile image.
6. The Game can be set with custom rules as in should a player be decided based on completing a row or column or right diagonal or left diagonal with his/her assigned symbol.
7. The game can be set to have different types of Bot difficulty level such as easy, medium and hard.
8. Each player can make a move one after the other.
9. The game ends in a draw if the board is filled but winning criteria is never achieved.

## Entities and their attributes##

* Game
  * Board
  * List of Players
  * List of WinningStrategies
* Board
  * List of cells
* Player
  * Symbol
  * Name
  * Email
  * profileImage
* Cell
  * Row
  * Column
  * CellStatus
  * Player
* Bot
  * DifficultyLevel
 
## Use Case Diagram ##

![Screenshot 2024-01-19 at 6 36 25 PM](https://github.com/deepanbme/TicTacToe/assets/57568829/90e21e6f-bd3a-4561-a37e-a3ec48f23f04)

## Class Diagram ##

![Screenshot 2024-01-20 at 5 54 12 PM](https://github.com/deepanbme/TicTacToe/assets/57568829/bee06910-4165-4efa-b0cd-65201d8382f9)

