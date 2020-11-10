package Map;

/**
 * Class represents a cell in the map
 */
public abstract class Cell {
    protected String symbol;
    protected boolean enterable;
    protected boolean hasPlayer;
    protected boolean hasMarket;

    public Cell(){
    }

    public Cell(String symbol){
        this.symbol = symbol;
    }

    public Boolean canEnter(){
        return enterable;
    }

    public Boolean hasMarket(){
        return hasMarket;
    }

    public void playerEntered(){
        hasPlayer = true;
    }

    public void playerLeft(){
        hasPlayer = false;
    }

    public Boolean hasPlayer(){
        return hasPlayer;
    }

    public String toString() {
        if(!hasPlayer){
            return String.format("%-2s\uFE31", symbol);
        } else {
            return String.format("%-2s\uFE31", "\uD83D\uDC71");
        }
    }
}
