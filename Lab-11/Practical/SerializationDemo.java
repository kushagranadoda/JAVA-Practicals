
import java.io.*;

class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    int rollNo;
    String name;
    double marks;
    transient String password;

    Student(int rollNo, String name, double marks, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.password = password;
    }

    void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Password: " + password);
        System.out.println("--------------------");
    }
}

public class SerializationDemo {

    public static void main(String[] args) {

        Student[] students = {
            new Student(1, "Alice", 85.5, "alice123"),
            new Student(2, "Bob", 90.0, "bob456"),
            new Student(3, "Charlie", 78.5, "charlie789")
        };

        try (ObjectOutputStream out =
                 new ObjectOutputStream(
                     new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving objects: " + e.getMessage());
        }

        try (ObjectInputStream in =
                 new ObjectInputStream(
                     new FileInputStream("students.dat"))) {

            Student[] loadedStudents = (Student[]) in.readObject();

            System.out.println("\nLoaded student details:");

            for (Student s : loadedStudents) {
                s.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading objects: " + e.getMessage());
        }
    }
}
