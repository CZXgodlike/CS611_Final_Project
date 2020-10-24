//A cell is a component of a game board. One cell can contain one sign.

public class Cell {
    private String sign;
    private int index;
    private boolean filled;

    public Cell(){
        sign = "";
        filled = false;
    }

    public void setSign(String sign){
        this.sign = sign;
        this.filled = true;
    }

    public String getSign(){
        return sign;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public void clearSign(){
        this.sign = "";
        this.filled = false;
    }

    public String toString() {
        return sign;
    }
}
