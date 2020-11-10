package Market;

import Helper.ItemFactory;
import Items.*;

/**
 * Class represents a market
 */
public class Market {
    private final ItemFactory itemFactory;

    public Market(){
        itemFactory = new ItemFactory();
    }

    public Item getItem(String name){
        return itemFactory.create(name);
    }

}
