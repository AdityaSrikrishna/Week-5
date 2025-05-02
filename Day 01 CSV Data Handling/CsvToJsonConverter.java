import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.*;

public class CsvToJsonConverter {
    public static void main(String[] args) {
        String csvFile = "students.csv";
        String jsonFile = "students_from_csv.json";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> rows = reader.readAll();
            String[] headers = rows.get(0);

            List<Map<String, String>> jsonList = new ArrayList<>();
            for (int i = 1; i < rows.size(); i++) {
                Map<String, String> obj = new HashMap<>();
                for (int j = 0; j < headers.length; j++) {
                    obj.put(headers[j], rows.get(i)[j]);
                }
                jsonList.add(obj);
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), jsonList);

            System.out.println("Converted CSV to JSON successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
