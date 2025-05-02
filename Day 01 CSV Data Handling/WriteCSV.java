import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String[] headers = {"ID", "Name", "Department", "Salary"};
        String[][] records = {
            {"101", "Alice", "HR", "45000"},
            {"102", "Bob", "IT", "55000"},
            {"103", "Charlie", "Finance", "60000"},
            {"104", "Diana", "Marketing", "50000"},
            {"105", "Eve", "IT", "62000"}
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.join(",", headers));
            bw.newLine();
            for (String[] record : records) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
            System.out.println("Data written successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}