package annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
 
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {
}
 
class ExpensiveService {
 
   private static final Map<String, Object> cache = new HashMap<>();
 
   @CacheResult
   public int computeSquare(int num) {
   	String key = "computeSquare:" + num;
 
   	if (cache.containsKey(key)) {
       	System.out.println("Returning cached result for " + num);
       	return (int) cache.get(key);
   	}
 
   	System.out.println("Computing result for " + num);
   	int result = num * num;
 
   	cache.put(key, result);
   	return result;
   }
 
   public Object invokeWithCache(String methodName, Object... args) throws Exception {
   	Method method = this.getClass().getMethod(methodName, int.class);
   	if (method.isAnnotationPresent(CacheResult.class)) {
       	String key = methodName + ":" + Arrays.toString(args);
       	if (cache.containsKey(key)) {
           	System.out.println("Returning cached result from reflection for " + key);
           	return cache.get(key);
       	} else {
           	Object result = method.invoke(this, args);
           	cache.put(key, result);
           	return result;
       	}
   	} else {
       	return method.invoke(this, args);
   	}
   }
}
 
public class Q12 {
   public static void main(String[] args) throws Exception {
   	ExpensiveService service = new ExpensiveService();
 
   	System.out.println("Result 1: " + service.invokeWithCache("computeSquare", 5));
   	System.out.println("Result 2: " + service.invokeWithCache("computeSquare", 5));
   	System.out.println("Result 3: " + service.invokeWithCache("computeSquare", 10));
   	System.out.println("Result 4: " + service.invokeWithCache("computeSquare", 10));
   }
}

