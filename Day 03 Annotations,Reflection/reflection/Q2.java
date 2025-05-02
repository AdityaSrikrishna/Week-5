package reflection;

import java.lang.reflect.Field;

public class Q2 {
    public static void main(String[] args) throws Exception {
    
        Person person = new Person();
        
        Class<?> clazz = person.getClass();

        Field ageField = clazz.getDeclaredField("age");

        ageField.setAccessible(true);

        ageField.setInt(person, 25);

        int age = ageField.getInt(person);
        System.out.println("Modified age: " + age);
    }
}

class Person {
    private int age = 0;
}

