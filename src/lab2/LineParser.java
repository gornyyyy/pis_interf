package lab2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static lab2.ShapeIdentifier.*;

public class LineParser {

    private static final String NUM = "[-+]?\\d*\\.?\\d+";

    private static final Pattern POINT_PATTERN =
            Pattern.compile("^\\s*Point\\s*\\(\\s*(" + NUM + ")\\s*,\\s*(" + NUM + ")\\s*\\)\\s*$");

    private static final Pattern LINE_PATTERN =
            Pattern.compile("^\\s*Line\\s*\\(\\s*" +
                    "Point\\s*\\(\\s*(" + NUM + ")\\s*,\\s*(" + NUM + ")\\s*\\)\\s*,\\s*" +
                    "Point\\s*\\(\\s*(" + NUM + ")\\s*,\\s*(" + NUM + ")\\s*\\)\\s*\\)\\s*$");

    private static final Pattern CIRCLE_PATTERN =
            Pattern.compile("^\\s*Circle\\s*\\(\\s*" +
                    "Point\\s*\\(\\s*(" + NUM + ")\\s*,\\s*(" + NUM + ")\\s*\\)\\s*,\\s*" +
                    "(" + NUM + ")\\s*\\)\\s*$");

    public static Shape parse(String line) {
        Shape s;
        if ((s = parsePoint(line))  != null) return s;
        if ((s = parseLine(line))   != null) return s;
        if ((s = parseCircle(line)) != null) return s;
        return null;
    }

    private static Point parsePoint(String line) {
        Matcher m = POINT_PATTERN.matcher(line);
        if (m.matches()) {
            return new Point(
                    Double.parseDouble(m.group(1)),
                    Double.parseDouble(m.group(2))
            );
        }
        return null;
    }

    private static Line parseLine(String line) {
        Matcher m = LINE_PATTERN.matcher(line);
        if (m.matches()) {
            Point a = new Point(
                    Double.parseDouble(m.group(1)),
                    Double.parseDouble(m.group(2))
            );
            Point b = new Point(
                    Double.parseDouble(m.group(3)),
                    Double.parseDouble(m.group(4))
            );
            return new Line(a, b);
        }
        return null;
    }

    private static Circle parseCircle(String line) {
        Matcher m = CIRCLE_PATTERN.matcher(line);
        if (m.matches()) {
            Point c = new Point(
                    Double.parseDouble(m.group(1)),
                    Double.parseDouble(m.group(2))
            );
            double r = Double.parseDouble(m.group(3));
            return new Circle(c, r);
        }
        return null;
    }
}