package Map;

import Market.Market;

/**
 * Class represents a market cell
 */

public class MarketCell extends Cell {

    private Market market;

    public MarketCell(){
        super("\uD83D\uDED2");
        enterable = true;
        hasMarket = true;
    }
}
