package Map;

/**
 * Class represents a map for Legends: Monsters and heroes
 */
public class LegendMap extends Grids{

    public LegendMap(int i, int j){
        cells = new Cell[i][j];
        createMap();
        setPlayer();
    }

    public LegendMap(){}

    private void createMap(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                double index = Math.random();
                if (index < 0.1){
                    cells[i][j] = new InaccessibleCell();
                } else if (index < 0.4){
                    cells[i][j] = new MarketCell();
                } else {
                    cells[i][j] = new NormalCell();
                }
            }
        }
    }

    public void setPlayer(){
        for(Cell[] row: cells){
            for(Cell cell: row){
                if(cell.canEnter()){
                    cell.playerEntered();
                    return;
                }
            }
        }
    }

    public int getLength(){
        return cells.length;
    }

    public int getWidth(){
        return cells[0].length;
    }
}
