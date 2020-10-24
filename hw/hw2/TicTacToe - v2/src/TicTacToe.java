//This class contains TicTacToe's rules and logic

public class TicTacToe extends Game{

    public TicTacToe(){
        super();
        this.gameType = "TicTacToe";
        team1 = new Team(0, "O", "Team1");
        team2 = new Team(0, "X", "Team2");
        printWelcome();
        initializeTeam();
        initializeGame();
    }

    //TicTacToe starts
    public void run(){

        int currentIndex;

        //Show game board to user and clear the Index
        showBoard();
        board.clearBoard();

        System.out.println("Team " + player1.getTeam() + "'s " + player1 + " plays first.");

        while(winner == null) {
            currentIndex = placeMove();
            board.printBoard();
            if(winnerOccurs(currentIndex)){
                winner = currentPlayer;
                winner.scoreIncrease();
                winner.getTeam().scoreIncrease();
                System.out.println(currentPlayer + " from team " + currentPlayer.getTeam() + " wins! Congratulations!");
                printScore();
                break;
            } else {
                if (board.isFilled()){
                    System.out.println("Game ends! It's a draw!");
                    printScore();
                    break;
                }//Check draw
            }
            switchTurn();
        }

        //Check for one more time, if not, declare champion and MVP
        if(oneMoreTime()){
            initializeGame();
            run();
        } else {
            declareChampion();
            declareMVP();
            if(otherGames()){
                new Game().Run();
            } else {
                System.out.println("Thanks for using!");
            }
        }
    }
}
