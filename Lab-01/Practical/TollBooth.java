import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        int bike = 0, car = 0, truck = 0;

        while (true) {
            System.out.print("Enter vehicle number (or 'done'): ");
            String number = sc.next();
            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.next().toLowerCase();
            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };
            total += toll;

            switch (vehicle.type()) {
                case "bike" -> bike++;
                case "car" -> car++;
                case "truck" -> truck++;
            }
        }

        System.out.println("Total toll: " + total);

        String mostFrequent = "bike";
        int maxCount = bike;
        if (car > maxCount) { mostFrequent = "car"; maxCount = car; }
        if (truck > maxCount) { mostFrequent = "truck"; maxCount = truck; }

        System.out.println("Most frequent: " + mostFrequent);
    }
}
