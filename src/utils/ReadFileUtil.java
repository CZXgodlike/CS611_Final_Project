/**
 * Utility class to check info in given file
 */

package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileUtil{
    
    // Check if a target exist in file
    public static boolean isExist(String filePath, int fieldIndex, String target){
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[fieldIndex].equals(target)){
                    csvReader.close();
                    return true;
                }
            }
            csvReader.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // find if all target match with a query in file
    public static boolean findMatch(String filePath, int[] fieldIndex, String[] target){
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                for (int i=0; i<fieldIndex.length;i++){
                    if(data[fieldIndex[i]].equals(target[i]) == false){
                        break;
                    }
                    csvReader.close();
                    return true;
                }
            }
            csvReader.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // get specific field in given line of csv
    public static String getField(String filePath, String key, int keyIndex, int fieldIndex){
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
            while ((row = csvReader.readLine()) != null) {
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
