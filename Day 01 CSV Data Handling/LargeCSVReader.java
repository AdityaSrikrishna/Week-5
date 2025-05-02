import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LargeCSVReader {
    public static void main(String[] args) {
        String filePath = "large_data.csv";
        int batchSize = 100;
        int totalCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header
            System.out.println("Header: " + line);
            int batchCount = 0;

            while ((line = br.readLine()) != null) {
                batchCount++;
                totalCount++;

                if (batchCount == batchSize) {
                    System.out.println("Processed " + totalCount + " records...");
                    batchCount = 0;
                }
            }

            // Final report for leftover records
            if (batchCount > 0) {
                System.out.println("Processed " + totalCount + " records (final batch).");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
