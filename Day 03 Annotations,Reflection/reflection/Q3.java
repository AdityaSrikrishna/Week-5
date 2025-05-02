package reflection;

import java.lang.reflect.Method;

public class Q3 {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        Class<?> clazz = calculator.getClass();

        Method multiplyMethod = clazz.getDeclaredMethod("multiply", int.class, int.class);
        multiplyMethod.setAccessible(true);

        int result = (int) multiplyMethod.invoke(calculator, 6, 7);
        System.out.println("Result: " + result);
    }
}

class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}
