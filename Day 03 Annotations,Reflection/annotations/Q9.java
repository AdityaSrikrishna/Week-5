package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
   int value();
}
 
class User {
   @MaxLength(10)
   private String username;
 
   public User(String username) {
   	this.username = username;
 
   	// Validation using Reflection API
   	for (Field field : this.getClass().getDeclaredFields()) {
       	if (field.isAnnotationPresent(MaxLength.class)) {
           	MaxLength annotation = field.getAnnotation(MaxLength.class);
           	field.setAccessible(true);
           	try {
               	String value = (String) field.get(this);
               	if (value != null && value.length() > annotation.value()) {
                   	throw new IllegalArgumentException(
                           	"Field '" + field.getName() + "' exceeds max length of " + annotation.value()
                   	);
               	}
           	} catch (IllegalAccessException e) {
               	throw new RuntimeException("Failed to access field: " + field.getName(), e);
           	}
       	}
   	}
   }
 
   public String getUsername() {
   	return username;
   }
}
 
public class Q9 {
   public static void main(String[] args) {
   	try {
       	User user1 = new User("Guru");
       	System.out.println("Valid User: " + user1.getUsername());
 
       	User user2 = new User("VeryLongUsername123");
       	System.out.println("Invalid User: " + user2.getUsername());
   	} catch (IllegalArgumentException e) {
       	System.err.println("Validation Error: " + e.getMessage());
   	}
   }
}
