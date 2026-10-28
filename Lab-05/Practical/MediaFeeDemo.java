abstract class Media {
    String title;
    int daysLate;

    Media(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    abstract double lateFee();
}

class Book extends Media {
    Book(String title, int daysLate) { super(title, daysLate); }
    double lateFee() { return daysLate * 5; }
}

class Dvd extends Media {
    Dvd(String title, int daysLate) { super(title, daysLate); }
    double lateFee() { return daysLate * 10; }
}

class Magazine extends Media {
    Magazine(String title, int daysLate) { super(title, daysLate); }
    double lateFee() { return daysLate * 2; }
}

public class MediaFeeDemo {
    public static void main(String[] args) {
        Media[] arr = {
            new Book("Java Basics", 3),
            new Dvd("Movie X", 5),
            new Magazine("Tech Weekly", 10)
        };

        double total = 0;

        for (Media m : arr) {
            double fee = m.lateFee();
            total += fee;
            System.out.println(m.title + " (" + m.daysLate + " days): Rs. " + fee);
        }

        System.out.println("Total fees: Rs. " + total);
    }
}