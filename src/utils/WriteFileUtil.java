/**
 * Utility class to write info into given file
 */

package utils;

import java.io.*;

public class WriteFileUtil{
    
    // Add new line to csv
    public static void writeLine(String filePath, String content){
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filePath, true))){
            csvWriter.write(content);
            csvWriter.newLine();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeField(String filePath, int keyIndex, String key, int fIndex, String target){
        String fileBuffer = "";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[keyIndex].equals(key)){
                    data[fIndex] = target;
                }
                fileBuffer += String.join(", ", data);
            }
            csvReader.close();
            try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filePath))){
                csvWriter.write(fileBuffer);
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
