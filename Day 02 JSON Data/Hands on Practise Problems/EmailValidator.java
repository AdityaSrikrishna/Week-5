import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

import java.io.File;
import java.util.Set;

public class EmailValidator {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);

        JsonSchema schema = factory.getSchema(new File("email-schema.json"));
        JsonNode data = mapper.readTree(new File("email-data.json"));

        Set<ValidationMessage> errors = schema.validate(data);
        if (errors.isEmpty()) {
            System.out.println("Email is valid.");
        } else {
            errors.forEach(System.out::println);
        }
    }
}
