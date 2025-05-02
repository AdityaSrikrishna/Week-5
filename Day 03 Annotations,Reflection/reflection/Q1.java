package reflection;

import java.lang.reflect.*;

public class Q1 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.util.ArrayList");

        System.out.println("Class Name: " + clazz.getName());

        System.out.println("\nConstructors:");
        for (Constructor<?> constructor : clazz.getConstructors()) {
            System.out.println(constructor);
        }

        System.out.println("\nFields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }

        System.out.println("\nMethods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
    }
}


