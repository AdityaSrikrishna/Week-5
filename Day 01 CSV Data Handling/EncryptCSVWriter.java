import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

public class EncryptCSVWriter {
    public static void main(String[] args) {
        String file = "employees_encrypted.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeNext(new String[]{"ID", "Name", "Email", "Salary"});
            writer.writeNext(new String[]{"1", "Alice",
                    AESUtil.encrypt("alice@example.com"), AESUtil.encrypt("55000")});
            writer.writeNext(new String[]{"2", "Bob",
                    AESUtil.encrypt("bob@example.com"), AESUtil.encrypt("62000")});

            System.out.println("Encrypted data written to " + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
