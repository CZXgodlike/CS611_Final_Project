import controller.StockDataController;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        StockDataController c = new StockDataController();
        c.getData();
    }
}
