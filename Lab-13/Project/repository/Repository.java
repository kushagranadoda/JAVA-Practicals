
package repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import model.annotation.Id;

public class Repository<T> {

    private HashMap<String, T> data = new HashMap<>();

    public T save(T entity) {
        try {
            Class<?> cls = entity.getClass();

            while (cls != null) {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        field.setAccessible(true);

                        Object value = field.get(entity);

                        if (value == null) {
                            throw new RuntimeException(
                                "ID value cannot be null"
                            );
                        }

                        String id = value.toString();
                        data.put(id, entity);

                        return entity;
                    }
                }

                cls = cls.getSuperclass();
            }

            throw new RuntimeException(
                "No field marked with @Id found"
            );

        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                "Unable to read ID field", e
            );
        }
    }

    public T findById(String id) {
        return data.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }

        double sum = 0;

        for (N value : values) {
            sum += value.doubleValue();
        }

        return sum / values.length;
    }
}
