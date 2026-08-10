public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.print("(" + x + ", " + y + ")");
    }

    public boolean isSame(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}