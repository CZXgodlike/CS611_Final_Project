//This class contains Order and Chaos's rules and logic

public class OrderAndChaos extends Game {

    public OrderAndChaos(){
        super(6, 5);
        gameType = "Order and Chaos";
        team1 = new Team(0, "Team1");
        team2 = new Team(0, "Team2");
        printWelcome();
        initializeTeam();
        initializeGame();
    }

    //Override initializeGame, because we don't need to select board size and inRow
    public void initializeGame(){
        winner = null;
        selectPlayer();
    }

    //Order and chaos starts
    public void run(){

        int currentIndex;

        //Show game board to user and clear the Index
        showBoard();
        board.clearBoard();

        System.out.println("Team " + player1.getTeam() + "'s " + player1 + " plays first.");

        while(winner == null) {
            currentPlayer.selectSign();
            currentIndex = placeMove();
            board.printBoard();
            if(winnerOccurs(currentIndex)){
                winner = player1;
                winner.scoreIncrease();
                winner.getTeam().scoreIncrease();
                System.out.println(winner + " wins! Congratulations!");
                printScore();
                break;
            } else {
                if (board.isFilled()){
                    winner = player2;
                    winner.scoreIncrease();
                    winner.getTeam().scoreIncrease();
                    System.out.println(winner + " wins! Congratulations!");
                    printScore();
                    break;
                }
            }
            switchTurn();
        }

        //Check for one more time, if not, declare champion and MVP
        if (oneMoreTime()){
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
