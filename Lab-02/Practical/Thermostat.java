import java.util.Scanner;

public class Thermostat {
    private String location;
    private int temperature;
    private final int minTemp;   // now instance-specific, not a fixed constant
    private final int maxTemp;
    private static int activeCount = 0;

    // Constructor with location, starting temperature, and custom min/max range
    public Thermostat(String location, int startTemp, int minTemp, int maxTemp) {
        this.location = location;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        if (startTemp >= minTemp && startTemp <= maxTemp) {
            this.temperature = startTemp;
        } else {
            // fall back to the midpoint of the range instead of a hardcoded 22
            this.temperature = (minTemp + maxTemp) / 2;
        }
        activeCount++;
    }

    // Constructor chaining: default starting temperature is the midpoint of the range
    public Thermostat(String location, int minTemp, int maxTemp) {
        this(location, (minTemp + maxTemp) / 2, minTemp, maxTemp);
    }

    public void raise() {
        if (temperature < maxTemp) {
            temperature++;
        } else {
            System.out.println("Already at maximum (" + maxTemp + ")");
        }
    }

    public void lower() {
        if (temperature > minTemp) {
            temperature--;
        } else {
            System.out.println("Already at minimum (" + minTemp + ")");
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public static int getActiveCount() {
        return activeCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter minimum temperature: ");
        int min = sc.nextInt();

        System.out.print("Enter maximum temperature: ");
        int max = sc.nextInt();

        // basic safety check so max isn't accidentally below min
        if (min > max) {
            System.out.println("Minimum was greater than maximum — swapping values.");
            int temp = min;
            min = max;
            max = temp;
        }

        Thermostat t1 = new Thermostat("Living Room", min, max);
        Thermostat t2 = new Thermostat("Bedroom", min, max);

        System.out.println("Raising t1's temperature:");
        for (int i = 0; i < 10; i++) {
            t1.raise();
            System.out.println(t1.getTemperature());
        }

        System.out.println("Lowering t1's temperature:");
        for (int i = 0; i < 20; i++) {
            t1.lower();
            System.out.println(t1.getTemperature());
        }

        System.out.println("Active thermostat count: " + Thermostat.getActiveCount());

        sc.close();
    }
}