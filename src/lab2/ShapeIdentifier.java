package lab2;

import com.beust.jcommander.JCommander;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ShapeIdentifier {

    static abstract class Shape {
        public abstract String toString();
    }

    static class Point extends Shape {
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

    static class Line extends Shape {
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

    static class Circle extends Shape {
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

    void main(String[] argv) {
        CliArgs args = new CliArgs();

        try {
            JCommander jc = JCommander.newBuilder()
                    .addObject(args)
                    .build();

            jc.parse(argv);

            if (args.help) {
                jc.usage();
                System.exit(0);
            }

            if (args.filePath == null) {
                System.err.println("Ошибка: не указан путь к файлу (-f или --file)");
                System.exit(1);
            }
            if (args.operation == null) {
                System.err.println("Ошибка: не указана операция (-o или --oper)");
                System.exit(1);
            }
            if (!args.operation.equals("print") && !args.operation.equals("count")) {
                System.err.println("Ошибка: неизвестная операция '" + args.operation + "'");
                System.err.println("Допустимые операции: print, count");
                System.exit(1);
            }

            List<Shape> shapes = new ArrayList<>();

            Scanner fileScanner = new Scanner(new File(args.filePath));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                Shape shape = LineParser.parse(line);

                if (shape != null) {
                    shapes.add(shape);
                } else {
                    System.err.println("Предупреждение: некорректная строка пропущена: " + line);
                }
            }

            switch (args.operation) {
                case "print":
                    for (Shape shape : shapes) System.out.println(shape);
                    break;
                case "count": {
                    int points = 0, lines = 0, circles = 0;
                    for (Shape shape : shapes) {
                        if (shape instanceof Point)        points++;
                        else if (shape instanceof Line)    lines++;
                        else if (shape instanceof Circle)  circles++;
                    }
                    System.out.println("Всего: " + shapes.size());
                    System.out.println("Точек: " + points);
                    System.out.println("Строк: " + lines);
                    System.out.println("Кругов: " + circles);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}