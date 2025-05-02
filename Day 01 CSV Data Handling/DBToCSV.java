import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DBToCSV {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/yourdb";
        String username = "youruser";
        String password = "yourpass";
        String outputFile = "employees_report.csv";

        String sql = "SELECT emp_id, name, department, salary FROM employees";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            // Write header
            writer.write("Employee ID,Name,Department,Salary");
            writer.newLine();

            // Write data rows
            while (rs.next()) {
                String line = rs.getInt("emp_id") + "," +
                              rs.getString("name") + "," +
                              rs.getString("department") + "," +
                              rs.getDouble("salary");
                writer.write(line);
                writer.newLine();
            }

            System.out.println("CSV report generated: " + outputFile);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
