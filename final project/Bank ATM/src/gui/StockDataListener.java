package gui;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface StockDataListener {
    public void rowDeleted(int row) throws IOException, CsvException;
}
