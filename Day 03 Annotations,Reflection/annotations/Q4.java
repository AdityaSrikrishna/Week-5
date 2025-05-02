package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
   String priority();
   String assignedTo();
}
 
class TaskManager {
 
   @TaskInfo(priority = "High", assignedTo = "Alice")
   public void generateReport() {
   	System.out.println("Generating report...");
   }
 
   @TaskInfo(priority = "Low", assignedTo = "Bob")
   public void cleanLogs() {
   	System.out.println("Cleaning logs...");
   }
 
   public void untaggedMethod() {
   	System.out.println("No task info here.");
   }
}
 
public class Q4 {
   public static void main(String[] args) throws Exception {
 
   	Class<?> clazz = TaskManager.class;
 
   	TaskManager manager = new TaskManager();
 
   	for (Method method : clazz.getDeclaredMethods()) {
 
       	if (method.isAnnotationPresent(TaskInfo.class)) {
 
         	TaskInfo task = method.getAnnotation(TaskInfo.class);
 
           	System.out.println("Method: " + method.getName());
           	System.out.println("Priority: " + task.priority());
           	System.out.println("Assigned To: " + task.assignedTo());
 
           	method.invoke(manager);
 
           	System.out.println("-------------------------");
       	}
   	}
   }
}
