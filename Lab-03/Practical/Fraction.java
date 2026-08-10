public class Fraction {
    private int num, den;

    public Fraction(int num, int den) {
        int g = gcd(Math.abs(num), Math.abs(den));
        this.num = num / g;
        this.den = den / g;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public void print() {
        System.out.print(num + "/" + den);
    }

    public boolean isSame(Fraction other) {
        return this.num == other.num && this.den == other.den;
    }
}