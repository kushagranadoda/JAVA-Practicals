import java.util.ArrayList;

public class LibraryService {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        books.add(new Book(title));
    }

    public int getBookCount() {
        return books.size();
    }

    public ArrayList<Book> getBooks() {
    return new ArrayList<>(books);
}

    public void validateAction(int action) {
        if (action < 1 || action > 3) {
            throw new IllegalArgumentException("Invalid action");
        }
    }
}
