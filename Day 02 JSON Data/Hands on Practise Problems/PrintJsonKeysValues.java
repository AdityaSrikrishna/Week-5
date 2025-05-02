import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class PrintJsonKeysValues {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("data.json"));

        if (root.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } else if (root.isArray()) {
            for (JsonNode node : root) {
                node.fields().forEachRemaining(field -> 
                    System.out.println(field.getKey() + " : " + field.getValue()));
            }
        }
    }
}
