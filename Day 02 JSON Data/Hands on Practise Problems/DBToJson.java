import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.*;

class Employee {
    public int id;
    public String name;
    public String department;
    public double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class DBToJson {
    public static void main(String[] args) throws Exception {
        List<Employee> employees = new ArrayList<>();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        while (rs.next()) {
            employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("salary")
            ));
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
        System.out.println(json);

        rs.close();
        stmt.close();
        conn.close();
    }
}
