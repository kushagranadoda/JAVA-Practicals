
class Stack<T> {
    private Object[] arr;
    private int top;

    public Stack(int capacity) {
        arr = new Object[capacity];
        top = -1;
    }

    public void push(T value) {
        if (top == arr.length - 1) {
            System.out.println("Stack Overflow!");
            return;
        }

        arr[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return null;
        }

        T value = (T) arr[top];
        arr[top] = null;
        top--;

        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return null;
        }

        return (T) arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class GenericStackDemo {
    public static void main(String[] args) {

        Stack<Integer> numbers = new Stack<>(5);

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        System.out.println("Integer stack top: " + numbers.peek());
        System.out.println("Popped: " + numbers.pop());
        System.out.println("Popped: " + numbers.pop());

        Stack<String> names = new Stack<>(5);

        names.push("Amit");
        names.push("Riya");

        System.out.println("\nString stack top: " + names.peek());
        System.out.println("Popped: " + names.pop());
        System.out.println("Popped: " + names.pop());
        System.out.println("\nIs names stack empty? " + names.isEmpty());
        System.out.println("Pop from empty stack: " + names.pop());
    }
}
