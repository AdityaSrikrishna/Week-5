import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

class Product {
    public String name;
    public double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class ListToJson {
    public static void main(String[] args) throws Exception {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99),
            new Product("Phone", 499.50)
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
        System.out.println(json);
    }
}
