import java.io.FileReader;
import com.opencsv.CSVReader;

public class DecryptCSVReader {
    public static void main(String[] args) {
        String file = "employees_encrypted.csv";
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] row;
            reader.readNext(); // skip header

            while ((row = reader.readNext()) != null) {
                String decryptedEmail = AESUtil.decrypt(row[2]);
                String decryptedSalary = AESUtil.decrypt(row[3]);

                System.out.println("ID: " + row[0] + ", Name: " + row[1] +
                        ", Email: " + decryptedEmail + ", Salary: " + decryptedSalary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
