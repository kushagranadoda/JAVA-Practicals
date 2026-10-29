
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add book");
            System.out.println("2. View books");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice;

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            try {
                service.validateAction(choice);

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = sc.nextLine();

                        service.addBook(title);
                        System.out.println("Book added!");
                        break;

                    case 2:
                        System.out.println("Total books: "
                            + service.getBookCount());

                        for (Book book : service.getBooks()) {
                            System.out.println(book.getTitle());
                        }
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
