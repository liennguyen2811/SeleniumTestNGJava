package openweathermap.helpers;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import jxl.Sheet;
import jxl.Workbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class MethodHelper {
    public static String formatCurrentDate() {
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static Object[][] ProvideData(String xlFilePath, String sheetName) throws Exception {
        //open excel file
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        //the required sheet
        Sheet sheet = workbook.getSheet(sheetName);
        //return number of rows(rowCount)
        int rowCount = sheet.getRows() - 1;
        int columnCount = sheet.getColumns();
        int currentPosition = 1;
        Object[][] values = new Object[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++, currentPosition++) {
            //loop over the rows
            for (int j = 0; j < columnCount; j++)
                values[i][j] = sheet.getCell(j, currentPosition).getContents();
        }
        workbook.close();
        return values;
    }

    public static HashMap<String, String>readCSV() {
        String fileName = "/Users/liennth7/Documents/Lien/build_demo/java-testng-selenium/src/test/resources/filedatatest/cityname.csv";
        HashMap<String, String> listCountryName = new HashMap<>();
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()){
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                String[] country = lineInArray[0].split(":");
                listCountryName.put(country[0], country[1]);
            }
        } catch (FileNotFoundException | CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCountryName;
    }


    public static Object[][]hashMapToDataProvider(HashMap sourceHashMap) {
        Object[][] result = new Object[sourceHashMap.size()][2];
        Object[] keys = sourceHashMap.keySet().toArray();
        Object[] values = sourceHashMap.values().toArray();
        for (int i = 0; i < keys.length; i++) {
            result[i][0] = keys[i];
            result[i][1] = values[i];
            System.out.println("Lien check array " + result[i][0]);
            System.out.println("Lien check array " + result[i][1]);
        }
        return result;
    }

    }