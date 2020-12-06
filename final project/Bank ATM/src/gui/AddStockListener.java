package gui;

import java.io.IOException;
import java.util.EventListener;

public interface AddStockListener extends EventListener {
    public void addStockEventOccurred(AddStockEvent e) throws IOException;
}
