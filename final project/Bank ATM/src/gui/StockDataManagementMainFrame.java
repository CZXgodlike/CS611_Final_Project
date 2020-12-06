package gui;

import com.opencsv.exceptions.CsvException;
import controller.StockDataController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StockDataManagementMainFrame extends JFrame {

    private TablePanel tablePanel;
    private StockDataController controller;
    private AddStockPanel addStockPanel;

    public StockDataManagementMainFrame() throws IOException {
        super("Stock Market");

        tablePanel = new TablePanel();
        controller = new StockDataController();
        addStockPanel = new AddStockPanel();

        tablePanel.setData(controller.getData());

        // Set listener for deleting stock
        tablePanel.setStockDataListener(new StockDataListener() {
            public void rowDeleted(int row) throws IOException, CsvException {
                int action = JOptionPane.showConfirmDialog(StockDataManagementMainFrame.this, "Are you sure to delete this stock?",
                        "Confirm delete", JOptionPane.YES_NO_OPTION);

                if(action == JOptionPane.YES_OPTION){
                    controller.deleteData(row);
                    tablePanel.setData(controller.getData());
                    tablePanel.refresh();
                }
            }
        });

        // Set listener for adding stock
        addStockPanel.setAddStockListener(new AddStockListener() {

            @Override
            public void addStockEventOccurred(AddStockEvent e) throws IOException {
                controller.addData(e);
                tablePanel.setData(controller.getData());
                tablePanel.refresh();
            }

        });

        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(tablePanel, BorderLayout.CENTER);
        add(addStockPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) throws IOException {
        StockDataManagementMainFrame stockDataManagementMainFrame = new StockDataManagementMainFrame();
    }
}
