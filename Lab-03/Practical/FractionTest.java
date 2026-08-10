public class FractionTest {
    public static void main(String[] args) {
        Fraction a = new Fraction(1, 2);
        Fraction b = new Fraction(2, 4);
        Fraction c = new Fraction(3, 6);

        a.print();
        System.out.println();
        b.print();
        System.out.println();
        c.print();
        System.out.println();

        System.out.println("a equals b: " + a.isSame(b));
        System.out.println("b equals c: " + b.isSame(c));
        System.out.println("a equals c: " + a.isSame(c));
    }
}