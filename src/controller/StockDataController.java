package controller;

import assets.Stock;
 import com.opencsv.CSVReader;
 import com.opencsv.CSVWriter;
 import com.opencsv.exceptions.CsvException;
import gui.AddStockEvent;
import gui.TablePanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockDataController extends DataController{

    //private StockDatabase db = new StockDatabase();

    public StockDataController(){
        dataPath = "data/stockData.csv";
    }

    public List<Stock> getData() throws IOException {
        return readData();
    }

    private List<Stock> readData() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("data/stockData.csv"));
        List<Stock> stocks = new ArrayList<>();
        String row;
        //csvReader.readLine(); //skip the first line
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            stocks.add(new Stock(Arrays.asList(data)));
        }
        csvReader.close();

        return stocks;
    }

    public void addData(AddStockEvent e) throws IOException {
        String symbol = e.getSymbol();
        String company = e.getCompany();
        String last = e.getLast();
        String change = e.getChange();
        String change_percentage = e.getChange_percentage();
        String volume = e.getVolume();
        String traded = e.getTraded();

//        Stock stock = new Stock(symbol, company, last, change, change_percentage, volume, traded);
//        db.addStock(stock);

        FileWriter csvWriter = new FileWriter("data/stockData.csv", true);
        csvWriter.append(symbol);
        csvWriter.append(",");
        csvWriter.append(company);
        csvWriter.append(",");
        csvWriter.append(last);
        csvWriter.append(",");
        csvWriter.append(change);
        csvWriter.append(",");
        csvWriter.append(change_percentage);
        csvWriter.append(",");
        csvWriter.append(volume);
        csvWriter.append(",");
        csvWriter.append(traded);
        csvWriter.append("\n");

        csvWriter.flush();
        csvWriter.close();
    }



}
