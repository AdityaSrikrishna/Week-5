package reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

public class Q11 {
    public static void main(String[] args) throws Exception {
        ServiceConsumer consumer = new ServiceConsumer();
        injectDependencies(consumer);
        consumer.useService();
    }

    public static void injectDependencies(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(obj, field.getType().getDeclaredConstructor().newInstance());
            }
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Inject {}

class MyService {
    void serve() {
        System.out.println("Service executed");
    }
}

class ServiceConsumer {
    @Inject
    MyService service;

    void useService() {
        service.serve();
    }
}


