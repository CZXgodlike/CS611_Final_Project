package gui;

import controller.StockDataController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StockMarketFrame extends JFrame {
    private StockDisplayTablePanel tablePanel;
    private StockDataController controller;
    private StockMarketToolBar toolBar;

    public StockMarketFrame() throws IOException {
        super("Stock Market");

        tablePanel = new StockDisplayTablePanel();
        controller = new StockDataController();
        toolBar = new StockMarketToolBar();

        tablePanel.setData(controller.getData());

        setSize(750, 500);
        setVisible(true);

        add(toolBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        toolBar.setRefreshListener(new RefreshListener() {
            @Override
            public void refreshEventOccured() throws IOException {
                tablePanel.refresh();
            }
        });
    }
}
