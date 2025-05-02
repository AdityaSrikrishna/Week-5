package reflection;

import java.lang.reflect.Method;

public class Q12 {
    public static void main(String[] args) throws Exception {
        Method[] methods = TimedOperations.class.getDeclaredMethods();
        TimedOperations obj = new TimedOperations();
        for (Method method : methods) {
            long start = System.nanoTime();
            method.invoke(obj);
            long end = System.nanoTime();
            System.out.println(method.getName() + " took " + (end - start) + " ns");
        }
    }
}

class TimedOperations {
    public void taskOne() {
        for (int i = 0; i < 1000000; i++);
    }

    public void taskTwo() {
        for (int i = 0; i < 2000000; i++);
    }
}


