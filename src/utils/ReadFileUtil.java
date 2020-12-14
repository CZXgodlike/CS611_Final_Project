/**
 * Utility class to check info in given file
 */

package utils;

public class ReadFileUtil{
    
    // find if a target exist in a given field in file
    public static boolean findMatch(String filePath, int fieldIndex, String target){
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if data[fieldIndex].equals(target){
                return true;
            }
        }
        return false;
    }
    
    // find if muiltiple target exist in a given field in file
    public static boolean fieldMultiMath(String filePath, int[] fIndex, String[] fields){
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if data[fieldIndex].equals(target){
                return true;
            }
        }
        return false;
    }

}
