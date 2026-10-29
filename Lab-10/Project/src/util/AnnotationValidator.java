package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import model.annotation.Positive;
import model.annotation.MaxLength;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Class<?> current = obj.getClass();

        // Check the class and its parent classes
        while (current != null && current != Object.class) {

            Field[] fields = current.getDeclaredFields();

            for (Field field : fields) {

                field.setAccessible(true);

                try {
                    Object value = field.get(obj);

                    // Check @Positive
                    if (field.isAnnotationPresent(Positive.class)) {

                        Positive annotation =
                                field.getAnnotation(Positive.class);

                        if (value instanceof Number
                                && ((Number) value).doubleValue() <= 0) {

                            errors.add(field.getName() + ": "
                                    + annotation.message());
                        }
                    }

                    // Check @MaxLength
                    if (field.isAnnotationPresent(MaxLength.class)) {

                        MaxLength annotation =
                                field.getAnnotation(MaxLength.class);

                        if (value instanceof String
                                && ((String) value).length()
                                > annotation.value()) {

                            errors.add(field.getName()
                                    + ": maximum length is "
                                    + annotation.value());
                        }
                    }

                } catch (IllegalAccessException e) {
                    errors.add("Cannot access field: "
                            + field.getName());
                }
            }

            current = current.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}
