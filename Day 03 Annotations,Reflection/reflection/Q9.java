package reflection;

import java.lang.reflect.Field;

public class Q9{
    public static String toJson(Object obj) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append("\"").append(fields[i].getName()).append("\":\"").append(fields[i].get(obj)).append("\"");
            if (i < fields.length - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        User user = new User("abi", "abc@example.com");
        System.out.println(toJson(user));
    }
}

class User {
    String username;
    String email;
    User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
