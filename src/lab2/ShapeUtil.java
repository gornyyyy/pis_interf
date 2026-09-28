

public abstract class Shape {
    public abstract String toString();
}

public class Point extends Shape {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point(%.1f, %.1f)", x, y);
    }
}

public class Line extends Shape {
    Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("Line(start: %.1s; end: %.1s)", start, end);
    }
}

public class Circle extends Shape {
    Point center;
    double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Circle(center: %.1s; radius: %.1f)", center, radius);
    }
}

void main() {

}