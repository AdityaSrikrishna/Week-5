package reflection;

import java.lang.reflect.Field;

public class Q7 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Configuration.class;
        Field field = clazz.getDeclaredField("API_KEY");
        field.setAccessible(true);
        field.set(null, "NEW_API_KEY_12345");

        String value = (String) field.get(null);
        System.out.println("Modified API_KEY: " + value);
    }
}

class Configuration {
    private static String API_KEY = "ORIGINAL_KEY";
}

