package Map;

/**
 * Class represents an inaccessible cell
 */
public class InaccessibleCell extends Cell {

    public InaccessibleCell(){
        super("\uD83D\uDEAB");
        enterable = false;
    }
}
