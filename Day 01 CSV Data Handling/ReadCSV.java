import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public static void main(String[] args) {
        String filePath = "students.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // Skip header
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                System.out.println("ID: " + data[0] + ", Name: " + data[1] +
                                   ", Age: " + data[2] + ", Marks: " + data[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
