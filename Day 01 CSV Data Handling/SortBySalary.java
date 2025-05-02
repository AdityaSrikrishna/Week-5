import java.io.*;
import java.util.*;

public class SortBySalary {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // save header
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }

            records.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

            System.out.println("Top 5 highest-paid employees:");
            System.out.println("ID, Name, Department, Salary");
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                String[] emp = records.get(i);
                System.out.println(String.join(", ", emp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
