### TicTacToe second edition
#### Classes
**gameBoard:** Class of game board. A game board is a matrix of Cell. It holds signs or chess for game.\

**Cell:** A cell can hold only one sign. Used for composing a board.\

**Player:** Class represents a player.\

**Team:** A team is an array of Player.\

**Game:** A game class has a board and two teams. Also contains functions that are used for a turn game.\

**TicTacToe:** Subclass of Game class, with TicTacToe's specific rules and logic.\

**OrderAndChaos:** Subclass of Game class, with Order And Chaos's specific rules and logic.\

**Main:** Start point of the game.

#### Game logic
First you'll be asked to select game type you want to play. (TicTacToe or Order and Chaos) After you entered correctly (ignore case), you'll enter the game.
 
Then you need to decide team size ,team name  and even each player's name in the team. For TicTacToe, the game board and winning rule are all scalable. You need 
to decide them before the game starts. The game will randomly select one player from each team and make them battle for one round until a winner occurs or it's a draw.
I didn't make it alternate player every two moves because it will break the game's continuity.

During the game, the index will keep showing for convenience. A player needs to enter the index to put a chess on the cell the index represents. The board will detect
if the input is valid or not. As the game goes, the game will automatically detect a winner or a draw occurs based on the index the user has just put chess on.

Whenever a round ends, the first thing our system do is show the score board for each team and each player. Then our game will ask user whether they want to play for another round. If not, it will also ask if they want to switch to another game type.

Interestingly, I added an MVP judgement function to the system. When user decide to end this type of game, our system will declare MVP of this game.
This prize will be given to the player who got the most score in the winning team.


