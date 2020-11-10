package Map;


/**
 * Class represents the grids
 */
public abstract class Grids {
    protected Cell[][] cells;

    public Grids(){}

    public void printGrids(){
        System.out.println("\033[40m\033[0;94m");
        for (Cell[] row : cells) {
            System.out.println("\u2012".repeat(3 * cells[0].length + 1 - cells[0].length / 10));
            System.out.print("\uFE31");
            for (Cell cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println("\u2012".repeat(3 * cells[0].length + 1 - cells[0].length / 10));
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }
}
