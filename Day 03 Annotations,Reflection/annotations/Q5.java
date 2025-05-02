package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
   BugReport[] value();
}
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
   String description();
}
 
class IssueTracker {
 
   @BugReport(description = "Null pointer issue when input is empty")
   @BugReport(description = "UI freezes on clicking submit twice")
   public void submitBug() {
   	System.out.println("Bug submitted!");
   }
}
public class Q5 {
   public static void main(String[] args) throws Exception {
   	Class<?> clazz = IssueTracker.class;
   	Method method = clazz.getDeclaredMethod("submitBug");
 
   	if (method.isAnnotationPresent(BugReport.class) || method.isAnnotationPresent(BugReports.class)) {
 
       	BugReport[] bugReports = method.getAnnotationsByType(BugReport.class);
 
       	System.out.println("Bug Reports for method: " + method.getName());
       	for (BugReport bug : bugReports) {
           	System.out.println("- " + bug.description());
       	}
   	}
   }
}

