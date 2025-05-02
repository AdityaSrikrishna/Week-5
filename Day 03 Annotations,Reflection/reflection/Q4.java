package reflection;

import java.lang.reflect.Constructor;

public class Q4 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Student.class;
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        Object studentObj = constructor.newInstance("Pavithra", 22);

        Student student = (Student) studentObj;
        System.out.println(student);
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}


