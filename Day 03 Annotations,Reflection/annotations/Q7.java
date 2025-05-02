package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
   String task();
   String assignedTo();
   String priority() default "MEDIUM";
}
class ProjectWork {
 
   @Todo(task = "Implement login feature", assignedTo = "Alice", priority = "HIGH")
   public void loginFeature() {
   	System.out.println("Work in progress: login feature");
   }
 
   @Todo(task = "Write unit tests for API", assignedTo = "Bob")
   public void writeTests() {
   	System.out.println("Writing unit tests");
   }
 
   public void helperFunction() {
   	System.out.println("No task annotation here.");
   }
}
 
public class Q7 {
   public static void main(String[] args) {
   	Class<ProjectWork> clazz = ProjectWork.class;
 
   	for (Method method : clazz.getDeclaredMethods()) {
       	if (method.isAnnotationPresent(Todo.class)) {
           	Todo todo = method.getAnnotation(Todo.class);
           	System.out.println("Method: " + method.getName());
           	System.out.println("  Task: " + todo.task());
           	System.out.println("  Assigned To: " + todo.assignedTo());
           	System.out.println("  Priority: " + todo.priority());
           	System.out.println("---------------------------");
       	}
   	}
   }
}