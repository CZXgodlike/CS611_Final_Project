//This class defines a square board which is a two dimension array of cell and contains functions for game playing

public class gameBoard {

    private Cell[][] Board;
    private final int size;
    private int filled = 0;//Counts how many cells are filled.

    //No arg constructor
    public gameBoard(){
        initializeBoard(0);
        this.size = 0;
    }

    public gameBoard(int size){
        Board = new Cell[size][size];
        initializeBoard(size);
        this.size = size;
        setBoard();
    }

    //Initialize cells iteratively
    public void initializeBoard(int size){
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j ++){
                Board[i][j] = new Cell();
            }
        }
    }
    //Set Index to board
    public void setBoard() {
        System.out.println("Setting game board...");
        int cellIndex = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Board[i][j].setIndex(cellIndex);
                cellIndex++;
            }
        }
        filled = 0;
    }

    public int getSize() {
        return size;
    }

    public String getPiece(int row, int col){
        return Board[row][col].getSign();
    }

    //If there is a sign, print the sign; otherwise, print the index for convenience
    public void printBoard(){
        StringBuffer line = new StringBuffer();

        line.append('+');
        for(int i = 0; i < this.size; i++){
            line.append("--");
            line.append('+');
        }

        for(int i = 0; i < this.size; i++){
            System.out.println(line);
            System.out.print('|');
            for(int j = 0; j < this.size; j++){
                System.out.print(this.Board[i][j]);
                if(this.Board[i][j].getSign().length() == 1){
                    System.out.print(" ");
                } else {
                    if(this.Board[i][j].getSign().length() == 0){
                        System.out.print(Board[i][j].getIndex());
                        if(this.Board[i][j].getIndex() <= 9){
                            System.out.print(" ");
                        }
                    }
                }
                System.out.print('|');
            }
            System.out.println();
        }
        System.out.println(line);
    }

    public void clearBoard(){
        for(int i = 0;  i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                Board[i][j].clearSign();
            }
        }
        filled = 0;
    }

    //Place piece on board and return result
    public boolean placePiece(int Row, int Col, String s){
        if(Board[Row][Col].getSign().equals("")){
            Board[Row][Col].setSign(s);
            filled += 1;
            return true;
        } else {
            return false;
        }

    }

    //Return if every cell is filled
    public boolean isFilled(){
        return filled == size * size;
    }

    //Return row number of an Index
    public int getRow(int Index){
        return (Index - 1) / size;
    }

    //Return col number of an Index
    public int getCol(int Index){
        return (Index - 1) % size;
    }

}
