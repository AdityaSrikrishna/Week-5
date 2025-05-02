import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

class Employee {
    public String name;
    public int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class ListToJsonArray {
    public static void main(String[] args) throws Exception {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30),
            new Employee("Bob", 24)
        );

        ObjectMapper mapper = new ObjectMapper();
        String jsonArray = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
        System.out.println(jsonArray);
    }
}
