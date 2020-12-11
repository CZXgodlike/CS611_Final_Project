package gui;

import assets.Stock;
import controller.StockDataController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StockDisplayTablePanel extends JPanel {

    private JTable table;
    private StockDisplayModel tableModel;
    private StockDataController controller;

    public StockDisplayTablePanel(){
        tableModel = new StockDisplayModel();
        table = new JTable(tableModel);
        controller = new StockDataController();

        Border innerBorder = BorderFactory.createTitledBorder("Stock Market");
        Border outerBorder = BorderFactory.createEmptyBorder(0, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Stock> db){
        tableModel.setData(db);
    }

    public void refresh() throws IOException {
        setData(controller.getData());
        tableModel.fireTableDataChanged();
    }

}
