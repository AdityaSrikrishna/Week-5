package reflection;

import java.lang.reflect.Field;
import java.util.Map;

public class Q8 {
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(obj, entry.getValue());
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = Map.of("name", "Lily", "age", 22);
        Person1 person = toObject(Person1.class, data);
        System.out.println(person.name + " - " + person.age);
    }
}

class Person1 {
    String name;
    int age;
}

