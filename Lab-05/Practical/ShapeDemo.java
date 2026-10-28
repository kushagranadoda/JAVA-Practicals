abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double r;
    Circle(double r) { this.r = r; }
    double area() { return Math.PI * r * r; }
}

class Rectangle extends Shape {
    double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    double area() { return w * h; }
}

class Triangle extends Shape {
    double b, h;
    Triangle(double b, double h) { this.b = b; this.h = h; }
    double area() { return 0.5 * b * h; }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] arr = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 7)
        };

        double sum = 0;
        double max = 0;
        Shape big = null;

        for (Shape s : arr) {
            double a = s.area();
            sum += a;
            if (a > max) {
                max = a;
                big = s;
            }
            System.out.println(s.getClass().getSimpleName() + " area: " + a + ", running total: " + sum);
        }

        System.out.println("Largest: " + big.getClass().getSimpleName() + " with area " + max);
    }
}