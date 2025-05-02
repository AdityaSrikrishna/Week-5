import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class FilterJsonByAge {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("people.json"));

        for (JsonNode person : root) {
            if (person.get("age").asInt() > 25) {
                System.out.println(person);
            }
        }
    }
}
