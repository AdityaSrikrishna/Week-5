package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
   String value();
}
 
class AdminService {
   @RoleAllowed("ADMIN")
   public void performAdminTask() {
   	System.out.println("Admin task performed successfully!");
   }
 
   @RoleAllowed("USER")
   public void performUserTask() {
   	System.out.println("User task performed successfully!");
   }
}
 
public class Q10 {
 
   static String currentUserRole = "USER";
 
   public static void main(String[] args) throws Exception {
   	AdminService service = new AdminService();
 
   	invokeIfAllowed(service, "performAdminTask");
   	invokeIfAllowed(service, "performUserTask");
   }
 
   static void invokeIfAllowed(Object obj, String methodName) {
   	try {
       	Method method = obj.getClass().getMethod(methodName);
       	if (method.isAnnotationPresent(RoleAllowed.class)) {
           	RoleAllowed roleAnnotation = method.getAnnotation(RoleAllowed.class);
           	String requiredRole = roleAnnotation.value();
 
           	if (currentUserRole.equals(requiredRole)) {
                   method.invoke(obj);
           	} else {
               	System.out.println("Access Denied to method '" + methodName + "'! Required Role: " + requiredRole);
           	}
       	} else {
               method.invoke(obj);
       	}
   	} catch (Exception e) {
       	System.out.println("Error invoking method: " + e.getMessage());
   	}
   }
}

