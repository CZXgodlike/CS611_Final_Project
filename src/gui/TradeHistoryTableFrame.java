package gui;

import controller.TradeHistoryController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TradeHistoryTableFrame extends JFrame {

    private TradeHistoryController controller;
    private TradeHistoryTablePanel tablePanel;
    private JLabel id;

    public TradeHistoryTableFrame(String accountID) throws IOException {
        super("Trading history");

        controller = new TradeHistoryController(accountID);
        tablePanel = new TradeHistoryTablePanel();
        id = new JLabel("account ID: " + accountID, SwingConstants.CENTER);

        tablePanel.setData(controller.getData());

        setSize(750, 500);
        setVisible(true);
        setLayout(new BorderLayout());

        add(id, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }
}
