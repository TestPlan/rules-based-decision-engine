package helpers;

import java.io.*;
import java.util.Scanner;

/**
 * This class will utilize a Scanner to parse a text file for data.
 * The data must be formatted as following:
 *      # Comment lines begin with pound/hash
 *      dataName : dataType : value
 *
 * Any lines not formatted as a comment or as data will cause an error
 *
 *
 * @author Michael Crinite
 * @version  0.1 - 10/8/2016
 */
public class InputReader {
    private File file;          //The File being read
    private String[] data;      //The full arguments passed in the .txt data file, formatted in a string array
                                    // data[0] <= name
                                    // data[1] <= Type
                                    // data[2] <= value
    private String dataName;    //The name the data will be referenced by
    private String dataType;    //The type i.e. Float, boolean, String
    private String value;       //The actual value of the data, parsed as a String

    /**
     * Used to test functionality of InputReader as it is being coded
     * @param args Command line arguments
     */
    public static void main(String[] args){
        InputReader ir = new InputReader();
        ir.parseFile();
        System.out.println(ir.toString());
    }

    /**
     * Default constructor.
     *
     * This constructor will request that the user enter the location of a file.
     * Use this constructor when the file is not being passed to the InputReader when it is created.
     */
    public InputReader(){
        data = new String[5];
        try{
            inputFile();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Alternate constructor for when the file can be provided when the InputReader object is created.
     * @param f The provided file
     */
    public InputReader(File f){
        setFile(f);
        data = new String[5];
        try{
            parseFile();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Attempts to parse the given file.
     * The file must be formatted in the following way:
     *
     * Any line beginning with a pound/hash (#) is a comment line
     * The data line must be formatted as "Name : datatype : value"
     *
     * Any line not properly formatted, and not written as a comment, will return an error
     * unless it is past the properly formatted line (currently the method will stop parsing
     * as soon as it has received the proper expected values)
     */
    private void parseFile(){
        try{
            Scanner s = new Scanner(file);
            String curr;

            while(s.hasNext()){
                curr = s.nextLine().trim();
                if(curr.startsWith("#") ||  curr.length()<1){
                    //Do nothing. This is a comment line to disregard
                }else if(isValidFormat(curr)){
                    setData(toArray(curr));
                    setDataName(data[0]);
                    setDataType(data[2]);
                    setValue(data[4]);
                    s.close();
                    return;
                }else{
                    System.out.println("File contains invalid text");
                    s.close();
                    return;
                }
            }
            s.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Requests a file from a user at the command line.
     *
     * @throws IOException When no valid file is entered
     */
    private void inputFile() throws IOException{
        boolean readable;
        do {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input file location:");
            file = new File(in.readLine());
            readable = file.canRead();
            if (!readable) {
                System.out.println("File is not readable.");
            }
            in.close();
        } while (!readable);
    }

    /**
     * Checks if the formatted array is of the correct format as described above
     * @param text Text to check format of
     * @return True if the text was inputted properly, false if not
     */
    private boolean isValidFormat(String text){
        String[] arr = toArray(text);
        // Should now be {"name", ":", "datatype", ":", "value}
        if(!arr[1].equals(":") || !arr[3].equals(":") || arr.length > 5){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Converts a String of text into individual Strings split at spaces
     * @param line Parsed line to format as an array
     * @return String array formatted as specified above.
     */
    private String[] toArray(String line){
        String[] arr;
        if(line != null && !line.equals("\\s+")){ // If line is not null or empty
            arr = line.trim().split("\\s+"); // Remove whitespace, split by spaces
        }else{
            arr = new String[]{"invalid", "array"};
        }
        return arr;
    }

    /**
     * Overridden toString method
     * @return String of formatted Data
     */
    @Override
    public String toString(){
        if(data!=null){
            return dataName + ":" + dataType + ":" + value;
        }else{
            return "";
        }
    }

    /**
     * Returns the File object referenced
     * @return File object being read
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the File object to be read
     * @param file The file to be read
     */
    private void setFile(File file) {
        this.file = file;
    }

    /**
     * Returns the array of Strings containing the read data
     * @return The stored data
     */
    public String[] getData() {
        return data;
    }

    /**
     * Stores the data in a String array
     * @param data data to be stored
     */
    private void setData(String[] data) {
        this.data = data;
    }

    /**
     * Returns the data name
     * @return The data name
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * Stores the data name
     * @param dataName Name to be stored
     */
    private void setDataName(String dataName) {
        this.dataName = dataName;
    }

    /**
     * Returns the data type
     * @return The data type, as a String
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Stores the data type as a String
     * @param dataType The datatype, as a String
     */
    private void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Returns the value as a String
     * @return The value, as a String
     */
    public String getValue() {
        return value;
    }

    /**
     * Stores the value as a String
     * @param value Value to be stored
     */
    private void setValue(String value) {
        this.value = value;
    }
}
