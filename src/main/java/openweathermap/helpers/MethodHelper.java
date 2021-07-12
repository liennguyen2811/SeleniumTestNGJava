package openweathermap.helpers;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class MethodHelper {
    public String formatCurrentDate() {
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public HashMap<String, String>readCSV(String cityNameFile) {
        String fileName = "src/test/resources/filedatatest/"+ cityNameFile + ".csv" ;
        System.out.println("Lien check file name" + fileName);
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


    public Object[][]hashMapToDataProvider(HashMap sourceHashMap) {
        Object[][] result = new Object[sourceHashMap.size()][2];
        Object[] keys = sourceHashMap.keySet().toArray();
        Object[] values = sourceHashMap.values().toArray();
        for (int i = 0; i < keys.length; i++) {
            result[i][0] = keys[i];
            result[i][1] = values[i];
        }
        return result;
    }
    public boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

    }