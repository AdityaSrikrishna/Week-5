import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class StudentJsonCreator {
    public static void main(String[] args) throws Exception {
        Map<String, Object> student = new LinkedHashMap<>();
        student.put("name", "Alice");
        student.put("age", 21);
        student.put("subjects", Arrays.asList("Math", "Physics", "Chemistry"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(json);
    }
}
