import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testLogin() {
        System.out.println("testLogin executed");
    }

    public void normalMethod() {
        System.out.println("normalMethod executed");
    }

    @Run
    public void testSignup() {
        System.out.println("testSignup executed");
    }

    public void anotherMethod() {
        System.out.println("anotherMethod executed");
    }

    @Run
    public void testLogout() {
        System.out.println("testLogout executed");
    }
}

public class MiniTestRunner {

    public static void main(String[] args) {

        MyTests testObject = new MyTests();

        Method[] methods = MyTests.class.getDeclaredMethods();

        int count = 0;

        for (Method method : methods) {

            if (method.isAnnotationPresent(Run.class)) {

                try {
                    method.invoke(testObject);
                    count++;

                } catch (Exception e) {
                    System.out.println("Error running: "
                            + method.getName());
                }
            }
        }

        System.out.println("Total tests run: " + count);
    }
}

