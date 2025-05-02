import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJson {
    public static void main(String[] args) throws Exception {
        String json = "{\"name\":\"Alice\",\"age\":22,\"email\":\"a@mail.com\"}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        if (!node.has("name") || !node.has("email")) {
            System.out.println("Missing required fields.");
        } else if (!node.get("age").isInt()) {
            System.out.println("Age is not an integer.");
        } else {
            System.out.println("JSON is valid.");
        }
    }
}
