import com.fasterxml.jackson.databind.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class JsonToCsvConverter {
    public static void main(String[] args) {
        String jsonFile = "students.json";
        String csvFile = "students.csv";

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> students = mapper.readValue(new File(jsonFile), List.class);

            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                // Write header
                Set<String> headers = students.get(0).keySet();
                writer.writeNext(headers.toArray(new String[0]));

                // Write data
                for (Map<String, Object> student : students) {
                    String[] row = headers.stream()
                            .map(k -> String.valueOf(student.get(k)))
                            .toArray(String[]::new);
                    writer.writeNext(row);
                }

                System.out.println("Converted JSON to CSV successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
