import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeJson {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj1 = mapper.createObjectNode();
        obj1.put("id", 1);
        obj1.put("name", "Alice");

        ObjectNode obj2 = mapper.createObjectNode();
        obj2.put("email", "alice@mail.com");
        obj2.put("age", 22);

        obj1.setAll(obj2);  // Merge obj2 into obj1
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj1));
    }
}
