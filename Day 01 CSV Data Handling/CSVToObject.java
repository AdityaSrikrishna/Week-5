import java.io.*;
import java.util.*;

class Student {
    int id, age, marks;
    String name;

    Student(int id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Age: %d, Marks: %d", id, name, age, marks);
    }
}

public class CSVToObject {
    public static void main(String[] args) {
        String filePath = "students.csv";
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3])
                );
                students.add(student);
            }

            // Print all students
            students.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
