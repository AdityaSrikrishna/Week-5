import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidateCSV {
    public static void main(String[] args) {
        String filePath = "contacts.csv";
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] data = line.split(",");

                boolean emailValid = emailPattern.matcher(data[2].trim()).matches();
                boolean phoneValid = phonePattern.matcher(data[3].trim()).matches();

                if (!emailValid || !phoneValid) {
                    System.out.println("Invalid record: " + line);
                    if (!emailValid) System.out.println("  -> Invalid email");
                    if (!phoneValid) System.out.println("  -> Invalid phone");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
