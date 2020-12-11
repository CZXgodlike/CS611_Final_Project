package gui;

import java.io.IOException;
import java.util.EventListener;

public interface RefreshListener extends EventListener {

    void refreshEventOccured() throws IOException;
}
