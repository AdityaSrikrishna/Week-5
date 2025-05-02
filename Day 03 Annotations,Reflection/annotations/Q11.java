package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
   String name();
}
 
class Users {
 
   @JsonField(name = "user_name")
   private String username;
 
   @JsonField(name = "user_age")
   private int age;
 
   @JsonField(name = "user_email")
   private String email;
 
   public Users(String username, int age, String email) {
   	this.username = username;
   	this.age = age;
   	this.email = email;
   }
}
 
class JsonSerializer {
 
   public static String toJson(Object obj) throws IllegalAccessException {
   	Class<?> clazz = obj.getClass();
   	StringBuilder json = new StringBuilder();
   	json.append("{\n");
 
   	Field[] fields = clazz.getDeclaredFields();
   	List<String> jsonPairs = new ArrayList<>();
 
   	for (Field field : fields) {
       	if (field.isAnnotationPresent(JsonField.class)) {
           	field.setAccessible(true); 
           	JsonField annotation = field.getAnnotation(JsonField.class);
           	String jsonKey = annotation.name();
           	Object value = field.get(obj);
           	jsonPairs.add("  \"" + jsonKey + "\": \"" + value + "\"");
       	}
   	}
 
   	json.append(String.join(",\n", jsonPairs));
   	json.append("\n}");
   	return json.toString();
   }
}
 
public class Q11 {
   public static void main(String[] args) throws IllegalAccessException {
   	Users user = new Users("Alice", 25, "alice@example.com");
   	String json = JsonSerializer.toJson(user);
   	System.out.println(json);
   }
}
