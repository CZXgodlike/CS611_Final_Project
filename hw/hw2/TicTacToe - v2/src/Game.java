//This class defines a game and contains functions need for a game

import java.util.*;

public class Game {

    protected int inRow; //Number of the same sign in a row to win
    protected gameBoard board;
    protected String gameType;
    protected Team team1;
    protected Team team2;
    protected Player player1;
    protected Player player2;
    protected Player winner = null;
    protected Team champion = null;
    protected Player currentPlayer;

    //Constructor
    public Game(int size, int inRow) {
        this.board = new gameBoard(size);
        this.inRow = inRow;
    }

    //No arg constructor
    public Game() {
    }

    //Print welcome message and game board, then clear it.
    public void printWelcome() {
        System.out.println("Welcome to " + gameType + "!");
    }

    //Show game board to users
    public void showBoard() {
        System.out.println("Here's the game board:");
        board.printBoard();
    }

    //Allow user to select game type
    public String selectGameType() {
        System.out.println("Please choose game you want to play (TicTacToe/Order and Chaos):");
        Scanner sc = new Scanner(System.in);
        String gametype;

        while (true) {
            try {
                gametype = sc.nextLine();
                if (!(gametype.equalsIgnoreCase("tictactoe") || gametype.equalsIgnoreCase("Order and Chaos"))) {
                    System.out.println("Invalid input! Please enter TicTacToe/Order and Chaos:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please enter Y/N:");
                continue;
            }//End of error handling
            return gametype;
        }
    }

    //Allow user to select the size of board
    public int selectSize() {
        System.out.println("Please select the size of the game board (3~9):");
        Scanner sc = new Scanner(System.in);
        int size;

        while (true) {
            try {
                size = sc.nextInt();
                if (size < 3 || size >= 10) {
                    System.out.println("Invalid input. Please enter a number between 3~9:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 3~9:");
                sc.next();
                continue;
            }
            return size;
        }
    }

    //Allow user to select number of the same sign in a row to win
    public int selectInRow() {
        System.out.println("Please indicate how many same signs in-a-row to win:");
        int inrow;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                inrow = sc.nextInt();
                if (inrow > board.getSize() || inrow <= 1) {
                    System.out.println("Invalid input. Please enter a number between 2~" + board.getSize() + ":");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 2~" + board.getSize() + ":");
                sc.next();
                continue;
            }
            return inrow;
        }
    }

    //Randomly select two players for a round and announce them, then select player1 to play first
    public void selectPlayer() {
        System.out.println("Randomly selecting players...");
        player1 = team1.selectRandomPlayer();
        player2 = team2.selectRandomPlayer();
        currentPlayer = player1;
        System.out.println("This round is between team " + team1 + "'s " + player1 + " and team " + team2 + "'s " + player2 + "!");
    }

    //Game starts here:
    public void Run() {
        gameType = selectGameType();

        if (gameType.equalsIgnoreCase("TicTacToe")) {
            new TicTacToe().run();
        } else {
            new OrderAndChaos().run();
        }
    }

    //Check winner horizontally
    public boolean checkRow(int Row, String Sign) {
        int c = 0;
        int count = 0;
        while (c < board.getSize()) {
            if (board.getPiece(Row, c).equals(Sign)) {
                count++;
            } else {
                count = 0;
            }
            if (count == inRow) {
                return true;
            }
            c++;
        }
        return false;
    }

    //Check winner vertically
    public boolean checkCol(int Col, String Sign) {
        int r = 0;
        int count = 0;
        while (r < board.getSize()) {
            if (board.getPiece(r, Col).equals(Sign)) {
                count++;
            } else {
                count = 0;
            }
            if (count == inRow) {
                return true;
            }
            r++;
        }
        return false;
    }

    //Check winner diagonally
    public boolean checkDiag(int Row, int Col, String Sign) {
        int i = 1;
        int count = 0;

        while (Row - i >= 0 && Col - i >= 0) {
            if (board.getPiece(Row - i, Col - i).equals(Sign)) {
                count++;
            } else {
                break;
            }
            i++;
        }
        if (count >= inRow - 1) {
            return true;
        }

        i = 1;

        while (Row + i < board.getSize() && Col + i < board.getSize()) {
            if (board.getPiece(Row + i, Col + i).equals(Sign)) {
                count++;
            } else {
                break;
            }
            i++;
        }
        if (count >= inRow - 1) {
            return true;
        }

        count = 0;
        i = 1;

        while (Row + i < board.getSize() && Col - i >= 0) {
            if (board.getPiece(Row + i, Col - i).equals(Sign)) {
                count++;
            } else {
                break;
            }
            i++;
        }
        if (count >= inRow - 1) {
            return true;
        }

        i = 1;

        while (Col + i < board.getSize() && Row - i >= 0) {
            if (board.getPiece(Row - i, Col + i).equals(Sign)) {
                count++;
            } else {
                break;
            }
            i++;
        }
        return count >= inRow - 1;
    }

    //Check winner during game
    public boolean winnerOccurs(int Index) {

        int row = board.getRow(Index);
        int col = board.getCol(Index);


        return checkCol(col, currentPlayer.getSign())
                || checkRow(row, currentPlayer.getSign())
                || checkDiag(row, col, currentPlayer.getSign());

    }

    //Get player's move and place move on game board, then return the Index
    public int placeMove() {
        Scanner sc = new Scanner(System.in);
        int Index;

        System.out.println("Please enter the index you want to place your sign(1-" + board.getSize() * board.getSize() + ")" + ":");

        while (true) {
            //Get and validate input plus error handling
            try {
                Index = sc.nextInt();
                if (Index <= 0 || Index > board.getSize() * board.getSize()) {
                    System.out.println("Invalid input. Please enter a number between 1-" + board.getSize() * board.getSize() + ":");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1-" + board.getSize() * board.getSize() + ":");
                sc.next();
                continue;
            }//End of validation

            //Place sign on the index
            int row = (Index - 1) / board.getSize();
            int col = (Index - 1) % board.getSize();
            if (board.placePiece(row, col, currentPlayer.getSign())) {
                return Index;
            } else {
                System.out.println("This cell is filled! Please enter another number:");
            }
        }
    }

    //Ask player if they wanna one more round
    public boolean oneMoreTime() {

        System.out.println("One more time?(Y/N):");
        Scanner sc = new Scanner(System.in);
        String s;

        //Validate input and error handling
        while (true) {
            try {
                s = sc.nextLine();
                if (!(s.equalsIgnoreCase("Yes") ||
                        s.equalsIgnoreCase("Y") ||
                        s.equalsIgnoreCase("No") ||
                        s.equalsIgnoreCase("N"))) {
                    System.out.println("Invalid input! Please enter Y/N:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please enter Y/N:");
                continue;
            }//End of validation and error handling

            return s.equalsIgnoreCase("Yes") || s.equalsIgnoreCase("Y");
        }
    }

    //Print score
    public void printScore() {
        team1.printScore();
        team2.printScore();
    }

    //Switch turn
    public void switchTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        System.out.println(currentPlayer + " from team " + currentPlayer.getTeam() + "'s turn:");
    }

    //Set winner to null, set board size and inRow, select player
    public void initializeGame() {
        winner = null;
        selectPlayer();
        board = new gameBoard(selectSize());
        inRow = selectInRow();
    }

    //Create team: set team name and player name
    public void initializeTeam() {
        team1.setTeam_size();
        team2.setTeam_size();
        team1.rename("");
        team2.rename(team1.getTeam_name());
        team1.setPlayerName();
        team2.setPlayerName();
    }

    //Decide champion and announce
    public void declareChampion() {
        if (team1.getScore() > team2.getScore()) {
            System.out.println("Team " + team1 + " is the champion!");
            champion = team1;
        } else if (team1.getScore() == team2.getScore()) {
            System.out.println("Team " + team1 + " and team " + team2 + " are tied!");
        } else {
            System.out.println("Team " + team2 + " is the champion!");
            champion = team2;
        }
    }

    //Decide MVP and announce
    public void declareMVP() {
        ArrayList<Player> MVP;
        if (champion == null) {
            System.out.println("No one gets MVP!");
        } else {
            MVP = champion.selectBestPlayer();
            if (MVP.size() == 1) {
                System.out.println(MVP.get(0) + " from team " + MVP.get(0).getTeam() + " wins the MVP!");
            } else {
                for (int i = 0; i < MVP.size(); i++) {
                    System.out.print(MVP.get(i));
                    if (i == MVP.size() - 2) {
                        System.out.print(" and ");
                    } else if (i < MVP.size() - 2) {
                        System.out.print(", ");
                    }
                }
                System.out.print(" from team " + MVP.get(0).getTeam() + " share the MVP!");
            }
        }
    }

    //Ask player whether they want to play another type of game
    public boolean otherGames() {
        System.out.println("Other types of games? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String s;

        //Validate input and error handling
        while (true) {
            try {
                s = sc.nextLine();
                if (!(s.equalsIgnoreCase("Yes") ||
                        s.equalsIgnoreCase("Y") ||
                        s.equalsIgnoreCase("No") ||
                        s.equalsIgnoreCase("N"))) {
                    System.out.println("Invalid input! Please enter Y/N:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please enter Y/N:");
                continue;
            }//End of validation and error handling
            return s.equalsIgnoreCase("Yes") || s.equalsIgnoreCase("Y");
        }
    }

}


