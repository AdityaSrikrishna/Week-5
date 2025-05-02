package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
   String level() default "HIGH";
}
 
class ProjectTasks {
 
   @ImportantMethod
   public void processData() {
   	System.out.println("Processing data...");
   }
 
   @ImportantMethod(level = "MEDIUM")
   public void cleanUp() {
   	System.out.println("Cleaning up...");
   }
 
   public void helperMethod() {
   	System.out.println("Just a helper...");
   }
}
 
public class Q6 {
   public static void main(String[] args) {
   	Class<ProjectTasks> clazz = ProjectTasks.class;
 
   	for (Method method : clazz.getDeclaredMethods()) {
       	if (method.isAnnotationPresent(ImportantMethod.class)) {
           	ImportantMethod anno = method.getAnnotation(ImportantMethod.class);
           	System.out.println("Method: " + method.getName() + " | Importance Level: " + anno.level());
       	}
   	}
   }
}
