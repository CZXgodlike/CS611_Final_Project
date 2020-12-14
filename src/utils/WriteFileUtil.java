/**
 * Utility class to write info into given file
 */

package utils;

public class WriteFileUtil{
    
    // Add new line to csv
    public static void writeLine(String filePath, String content){
        FileWriter fstream = new FileWriter(filePath, true);
        BufferedWriter csvWriter = new BufferedWriter(fstream);
        csvWriter.write(content);
        csvWriter.newLine();
        csvWriter.close();
    }

}
