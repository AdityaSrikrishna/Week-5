import java.io.*;
import java.util.*;

public class n {
    public static void main(String[] args) {
        String filePath = "students.csv";
        Set<String> uniqueIds = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0].trim();
                if (!uniqueIds.add(id)) {
                    duplicates.add(line);
                }
            }

            if (duplicates.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate records based on ID:");
                duplicates.forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
