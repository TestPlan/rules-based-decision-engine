package helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO: Expand options for more flexibility, i.e. including ability to read headers, pass in the schema, and change delimiter (pass in)
 * A basic CSV File Reader: Uses Apache CVSReader
 * Dependency Needed: org.easybatch:easybatch-apache-commons-csv:5.0.02
 * Created by Ian Markind on 11/5/2016.
 */
public class CSVReader
{
    private CSVParser csvFileParser;

    public CSVReader(String path)
    {
        try
        {
             csvFileParser = CSVFormat.DEFAULT.parse(new FileReader(new File(path)));
        }
        catch (IOException e)
        {
            System.err.println("The CSV file located at: " + path + " was unable to be parsed.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        String output = "";

        for (CSVRecord csvRecord : csvFileParser)
        {
            output += csvRecord.toString() + "\n";
        }

        return output;
    }


    public CSVParser getCsvFileParser()
    {
        return csvFileParser;
    }

    public void setCsvFileParser(CSVParser csvFileParser)
    {
        this.csvFileParser = csvFileParser;
    }
}
