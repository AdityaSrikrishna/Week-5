import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

public class CsvToJson {
    public static void main(String[] args) throws Exception {
        CSVReader reader = new CSVReader(new FileReader("students.csv"));
        List<String[]> lines = reader.readAll();
        String[] headers = lines.get(0);

        List<Map<String, String>> jsonList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            Map<String, String> obj = new HashMap<>();
            for (int j = 0; j < headers.length; j++) {
                obj.put(headers[j], lines.get(i)[j]);
            }
            jsonList.add(obj);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonList);
        System.out.println(json);
    }
}
