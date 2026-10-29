import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {

    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
    }
}

public class ColumnMapper {

    public static void populate(Object obj, String[] headers, String[] data) {

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column column = field.getAnnotation(Column.class);
            String columnName = column.name();

            int index = -1;

            // Find column in header
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(columnName)) {
                    index = i;
                    break;
                }
            }

            // Missing column
            if (index == -1) {
                System.out.println(
                    "Missing column: " + columnName
                );
                continue;
            }

            try {
                field.setAccessible(true);

                if (field.getType() == int.class) {
                    field.setInt(obj, Integer.parseInt(data[index]));
                } else if (field.getType() == String.class) {
                    field.set(obj, data[index]);
                }

            } catch (Exception e) {
                System.out.println(
                    "Error setting " + columnName
                );
            }
        }
    }

    public static void main(String[] args) {

        String[] headers = {
            "name", "id", "email"
        };

        String[] data = {
            "Kushagra", "101", "kushagra@gmail.com"
        };

        Student student = new Student();

        populate(student, headers, data);

        System.out.println("\nStudent details:");
        student.display();
    }
}

