import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Checker {
    public static List<String> check(Object o) throws IllegalAccessException {
        List<String> errs = new ArrayList<>();

        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            String v = (String) f.get(o);

            if (f.isAnnotationPresent(NotBlank.class)) {
                if (v == null || v.isBlank()) {
                    errs.add(f.getName() + " must not be blank");
                }
            }

            if (f.isAnnotationPresent(MaxLength.class)) {
                int max = f.getAnnotation(MaxLength.class).value();
                if (v != null && v.length() > max) {
                    errs.add(f.getName() + " must be at most " + max + " characters");
                }
            }
        }

        return errs;
    }
}