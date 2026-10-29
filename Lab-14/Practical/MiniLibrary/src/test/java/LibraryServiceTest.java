import model.Book;
import service.LibraryService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void addingBookIncreasesCount() {
        LibraryService service = new LibraryService();

        service.addBook("Java Basics");

        assertEquals(1, service.getBookCount());
    }

    @Test
    void invalidActionThrowsException() {
        LibraryService service = new LibraryService();

        assertThrows(
            IllegalArgumentException.class,
            () -> service.validateAction(9)
        );
    }

    @Test
    void savedBooksReloadUnchanged() throws Exception {
        LibraryService original = new LibraryService();

        original.addBook("Java Basics");
        original.addBook("Data Structures");

        Path file = tempDir.resolve("books.dat");

        original.saveTo(file);

        LibraryService restored =
            LibraryService.loadFrom(file);

        assertEquals(2, restored.getBookCount());

        List<String> titles = restored.getBooks()
            .stream()
            .map(Book::getTitle)
            .toList();

        assertEquals(
            List.of("Java Basics", "Data Structures"),
            titles
        );
    }
}
