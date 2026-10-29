
package service;

import model.Book;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Title cannot be empty"
            );
        }

        books.add(new Book(title));
    }

    public int getBookCount() {
        return books.size();
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void validateAction(int action) {
        if (action < 1 || action > 3) {
            throw new IllegalArgumentException("Invalid action");
        }
    }

    // Save books to a file
    public void saveTo(Path file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(file.toFile()))) {
            out.writeObject(books);
        }
    }

    // Load books from a file
    @SuppressWarnings("unchecked")
    public static LibraryService loadFrom(Path file)
            throws IOException, ClassNotFoundException {

        LibraryService service = new LibraryService();

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file.toFile()))) {
            service.books = (ArrayList<Book>) in.readObject();
        }

        return service;
    }
}
