
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class FileTreeReport {
    public static void main(String[] args) {

        Path folder = Paths.get("myfolder");
        Path report = Paths.get("file_report.txt");

        int fileCount = 0;
        long totalSize = 0;

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        try {
            Files.createDirectories(folder.resolve("subfolder"));

            Files.writeString(folder.resolve("notes.txt"),
                "Java NIO practical");

            Files.writeString(folder.resolve("subfolder/data.txt"),
                "This is a sample file.");

            Files.writeString(folder.resolve("subfolder/info.txt"),
                "File tree report");

            try (var writer = Files.newBufferedWriter(report);
                 Stream<Path> paths = Files.walk(folder)) {

                writer.write("FILE TREE REPORT");
                writer.newLine();
                writer.write("================");
                writer.newLine();

                for (Path path : paths.toList()) {

                    if (Files.isRegularFile(path)) {

                        BasicFileAttributes attr =
                            Files.readAttributes(
                                path, BasicFileAttributes.class);

                        String name = folder.relativize(path).toString();
                        long size = attr.size();

                        String modifiedTime =
                            formatter.format(
                                attr.lastModifiedTime().toInstant());

                        writer.write("Name: " + name);
                        writer.newLine();

                        writer.write("Size: " + size + " bytes");
                        writer.newLine();

                        writer.write("Modified: " + modifiedTime);
                        writer.newLine();

                        writer.write("------------------------");
                        writer.newLine();

                        fileCount++;
                        totalSize += size;
                    }
                }

                writer.newLine();
                writer.write("Total files: " + fileCount);
                writer.newLine();
                writer.write("Total size: " + totalSize + " bytes");
            }

            System.out.println("Report created successfully!");
            System.out.println("Files found: " + fileCount);
            System.out.println("Total size: " + totalSize + " bytes");
            System.out.println("Report saved to: " + report.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
