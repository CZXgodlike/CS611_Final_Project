package controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class DataController {

    protected String dataPath;

    public DataController(){}

    public void deleteData(int row) throws IOException, CsvException {
        CSVReader reader2 = new CSVReader(new FileReader(dataPath));
        List<String[]> allElements = reader2.readAll();
        allElements.remove(row);
        FileWriter sw = new FileWriter(dataPath);
        CSVWriter writer = new CSVWriter(sw, CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        writer.writeAll(allElements);
        writer.close();
    }

    public void setDataAt(String value, int row, int col) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(dataPath));
        List<String[]> csvBody = null;
        try {
            csvBody = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
// get CSV row column  and replace with by using row and column
        assert csvBody != null;
        csvBody.get(row)[col] = value;
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter(dataPath), CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }

}
