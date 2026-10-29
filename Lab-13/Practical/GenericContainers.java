
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {

    public static <T> T firstElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(
                "Array must not be empty or null"
            );
        }

        return array[0];
    }

    public static void main(String[] args) {

        Box<Integer> numberBox = new Box<>();
        numberBox.set(100);
        System.out.println("Number: " + numberBox.get());

        Box<String> textBox = new Box<>();
        textBox.set("Hello Java");
        System.out.println("Text: " + textBox.get());

        Pair<String, Integer> student =
            new Pair<>("Amit", 20);

        System.out.println("Name: " + student.getFirst());
        System.out.println("Age: " + student.getSecond());

        String[] names = {"Riya", "Amit", "Neha"};
        Integer[] numbers = {10, 20, 30};

        String firstName = firstElement(names);
        Integer firstNumber = firstElement(numbers);

        System.out.println("First name: " + firstName);
        System.out.println("First number: " + firstNumber);
    }
}
