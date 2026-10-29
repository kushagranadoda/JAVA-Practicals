public class BoundedStats {

    public static <N extends Number> double average(N[] values) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                "Array must not be null or empty"
            );
        }

        double sum = 0;

        for (N value : values) {
            sum += value.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {

        Integer[] numbers1 = {10, 20, 30, 40};
        Double[] numbers2 = {2.5, 3.5, 4.5};
        Long[] numbers3 = {100L, 200L, 300L};

        System.out.println("Integer average: " + average(numbers1));
        System.out.println("Double average: " + average(numbers2));
        System.out.println("Long average: " + average(numbers3));
    }
}
