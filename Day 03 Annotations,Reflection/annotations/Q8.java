package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {}
 
class WorkSimulator {
 
   @LogExecutionTime
   public void fastTask() {
   	for (int i = 0; i < 1_000_000; i++) {
       	int x = i * 2;
   	}
   }
 
   @LogExecutionTime
   public void slowTask() {
   	for (int i = 0; i < 10_000_000; i++) {
       	int x = i * 2;
   	}
   }
}
 
public class Q8 {
   public static void main(String[] args) throws Exception {
   	WorkSimulator simulator = new WorkSimulator();
   	Method[] methods = WorkSimulator.class.getDeclaredMethods();
 
   	for (Method method : methods) {
       	if (method.isAnnotationPresent(LogExecutionTime.class)) {
           	long start = System.nanoTime();
 
           	method.invoke(simulator);
 
           	long end = System.nanoTime();
           	long duration = end - start;
 
           	System.out.println("Method: " + method.getName() + " took " + duration / 1_000_000.0 + " ms");
       	}
   	}
   }
}
