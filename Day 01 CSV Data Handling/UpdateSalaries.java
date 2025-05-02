import java.io.*;
import java.util.*;

public class UpdateSalaries {
    public static void main(String[] args) {
        String inputFile = "employees.csv";
        String outputFile = "employees_updated.csv";
        List<String[]> updatedRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = br.readLine();
            updatedRecords.add(line.split(",")); // Header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[2].equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(data[3]);
                    salary *= 1.10;
                    data[3] = String.format("%.2f", salary);
                }
                updatedRecords.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String[] record : updatedRecords) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
            System.out.println("Updated salaries written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
