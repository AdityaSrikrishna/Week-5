import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ExtractFields {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("users.json"));

        for (JsonNode node : root) {
            String name = node.get("name").asText();
            String email = node.get("email").asText();
            System.out.println("Name: " + name + ", Email: " + email);
        }
    }
}
