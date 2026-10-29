
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) {

        Path folder = Paths.get("logs");

        try {
            Files.createDirectories(folder);

            Files.write(folder.resolve("app1.txt"), List.of(
                "Application started",
                "User logged in",
                "ERROR: Database connection failed",
                "Application closed"
            ));

            Files.write(folder.resolve("app2.txt"), List.of(
                "Server started",
                "ERROR: File not found",
                "User logged out"
            ));

            Files.write(folder.resolve("app3.txt"), List.of(
                "System running normally",
                "User logged in",
                "ERROR: Network timeout"
            ));

            String keyword = "ERROR";

            int totalLines = 0;
            int matchingLines = 0;

            try (var files = Files.list(folder)) {
                for (Path file : files.filter(Files::isRegularFile).toList()) {

                    BasicFileAttributes attributes =
                        Files.readAttributes(file, BasicFileAttributes.class);

                    System.out.println("\nFile: " + file.getFileName());
                    System.out.println("Size: " + attributes.size() + " bytes");

                    List<String> lines = Files.readAllLines(file);

                    for (String line : lines) {
                        totalLines++;

                        if (line.contains(keyword)) {
                            matchingLines++;
                            System.out.println("Match: " + line);
                        }
                    }
                }
            }

            System.out.println("\n----- SUMMARY -----");
            System.out.println("Total lines: " + totalLines);
            System.out.println("Lines containing '" + keyword
                               + "': " + matchingLines);

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
