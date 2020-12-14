/**
 * Utility class to write info into given file
 */

package utils;

import java.io.*;

public class WriteFileUtil{
    
    // Add new line to csv
    public static void writeLine(String filePath, String content){
        FileWriter fstream = new FileWriter(filePath, true);
        BufferedWriter csvWriter = new BufferedWriter(fstream);
        csvWriter.write(content);
        csvWriter.newLine();
        csvWriter.close();
    }
    
    public static void writeField(String filePath, String key, int fIndex, String target){
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filePath))){
            while ((row = csvWriter.readLine()) != null) {
                String[] data = row.split(",");
                if(data[keyIndex].equals(key)){
                    return data(fieldIndex);
                }
            }
            return row;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
