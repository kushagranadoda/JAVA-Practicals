
package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class ReportGenerator {

    public static void generate(Path logsFolder, Path reportFile)
            throws IOException {

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        StringBuilder report = new StringBuilder();

        report.append("===== END-OF-DAY REPORT =====\n\n");

        if (Files.isDirectory(logsFolder)) {

            try (DirectoryStream<Path> files =
                    Files.newDirectoryStream(logsFolder)) {

                for (Path file : files) {

                    if (!Files.isRegularFile(file)) {
                        continue;
                    }

                    report.append("File: ")
                            .append(file.getFileName())
                            .append("\n");

                    BasicFileAttributes attrs =
                            Files.readAttributes(
                                    file,
                                    BasicFileAttributes.class
                            );

                    report.append("Size: ")
                            .append(attrs.size())
                            .append(" bytes\n");

                    report.append("Last Modified: ")
                            .append(Instant.ofEpochMilli(
                                    attrs.lastModifiedTime().toMillis()))
                            .append("\n");

                    try (BufferedReader reader =
                            Files.newBufferedReader(
                                    file, StandardCharsets.UTF_8)) {

                        String line;

                        while ((line = reader.readLine()) != null) {

                            String[] parts = line.split(",");

                            if (parts.length != 3) {
                                continue;
                            }

                            try {
                                String type =
                                        parts[0].trim();

                                long amount =
                                        Long.parseLong(parts[2].trim());

                                if (type.equalsIgnoreCase("DEPOSIT")) {
                                    totalDeposits += amount;
                                } else if (
                                        type.equalsIgnoreCase("WITHDRAW")) {
                                    totalWithdrawals += amount;
                                }

                            } catch (NumberFormatException e) {
                                report.append("Skipped invalid record: ")
                                        .append(line)
                                        .append("\n");
                            }
                        }
                    }

                    report.append("\n");
                }
            }

        } else {
            report.append("Logs folder does not exist.\n");
        }

        report.append("Total Deposits: Rs. ")
                .append(totalDeposits)
                .append("\n");

        report.append("Total Withdrawals: Rs. ")
                .append(totalWithdrawals)
                .append("\n");

        report.append("Net Transaction Amount: Rs. ")
                .append(totalDeposits - totalWithdrawals)
                .append("\n");

        Path parent = reportFile.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer =
                Files.newBufferedWriter(
                        reportFile,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write(report.toString());
        }

        System.out.println(report);
        System.out.println("Report saved to: " + reportFile);
    }

    public static void main(String[] args) throws IOException {

        Path logsFolder = Paths.get("logs");
        Path reportFile = Paths.get("reports", "end-of-day-report.txt");

        generate(logsFolder, reportFile);
    }
}
