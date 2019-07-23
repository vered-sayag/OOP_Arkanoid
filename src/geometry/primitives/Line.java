package geometry.primitives;
import java.util.List;

public class Line {

    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    public double length() {
        if (this.start != null && this.end != null) {
            return this.start.distance(this.end);
        }
        return -1;
    }

    public double distanceX() {
        return this.end.getX() - this.start.getX();
    }

    public double distanceY() {
        return this.end.getY() - this.start.getY();
    }


    public Point middle() {
        if (this.start != null && this.end != null) {
            double midX = Math.abs(this.start.getX() + this.end.getX()) / 2;
            double midY = Math.abs(this.start.getY() + this.end.getY()) / 2;
            Point middlePoint = new Point(midX, midY);
            return middlePoint;
        } else {
            return null;
        }
    }

    public Point start() {
        return this.start;
    }

    public Point end() {
        return this.end;
    }

    public boolean isIntersecting(Line other) {
        if (other != null && other.start() != null && other.end() != null && this.start != null && this.end != null) {

            double intersectionX;
            double intersectionY;
            if (this.start.getX() == this.end.getX() && other.start().getX() == other.end().getX()) {
                return false;
            }

            if (this.start.getX() == this.end.getX()) {
                double m2 = other.incline();
                double b2 = other.intersectionY();
                intersectionX = this.start.getX();
                intersectionY = (intersectionX * m2) + b2;

            } else if (other.start().getX() == other.end().getX()) {
                double m1 = this.incline();
                double b1 = this.intersectionY();
                intersectionX = other.start().getX();
                intersectionY = (intersectionX * m1) + b1;
            } else {
                double m1 = this.incline();
                double m2 = other.incline();

                if (m1 == m2) {
                    return false;
                }

                double b1 = this.intersectionY();
                double b2 = other.intersectionY();

                intersectionX = (b1 - b2) / (m2 - m1);
                intersectionY = (intersectionX * m1) + b1;
            }
            return (Math.max(this.start.getX(), this.end.getX()) >= intersectionX
                    && Math.min(this.start.getX(), this.end.getX()) <= intersectionX
                    && Math.max(this.start.getY(), this.end.getY()) >= intersectionY
                    && Math.min(this.start.getY(), this.end.getY()) <= intersectionY
                    && Math.max(other.start().getX(), other.end().getX()) >= intersectionX
                    && Math.min(other.start().getX(), other.end().getX()) <= intersectionX
                    && Math.max(other.start().getY(), other.end().getY()) >= intersectionY
                    && Math.min(other.start().getY(), other.end().getY()) <= intersectionY);
        }
        return false;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.

    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            double intersectionX;
            double intersectionY;
            if (this.start.getX() == this.end.getX()) {
                double m2 = other.incline();
                double b2 = other.intersectionY();
                intersectionX = this.start.getX();
                intersectionY = (intersectionX * m2) + b2;

            } else if (other.start().getX() == other.end().getX()) {
                double m1 = this.incline();
                double b1 = this.intersectionY();
                intersectionX = other.start().getX();
                intersectionY = (intersectionX * m1) + b1;
            } else {
                double m1 = this.incline();
                double m2 = other.incline();
                double b1 = this.intersectionY();
                double b2 = other.intersectionY();

                intersectionX = (b1 - b2) / (m2 - m1);
                intersectionY = intersectionX * m1 + b1;
            }
            return new Point(intersectionX, intersectionY);
        }
        return null;
    }

    public double incline() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    public double intersectionY() {
        return this.start.getY() - (this.start.getX() * this.incline());
    }

    public boolean equals(Line other) {
        if (other != null && other.start() != null && other.end() != null && this.start != null && this.end != null) {
            return ((this.start.equals(other.start()) && this.end.equals(other.end()))
                    || (this.start.equals(other.end()) && this.end.equals(other.start())));
        }
        return false;
    }

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point tempPoint;
        Point closesPoint;
        List<Point> intersection = rect.intersectionPoints(this);
        if (intersection == null || intersection.isEmpty()) {
            return null;
        }
        closesPoint = intersection.get(0);

        for (int i = 0; i < intersection.size(); i++) {
            tempPoint = intersection.get(i);
            if (tempPoint.distance(this.start()) < closesPoint.distance(this.start())) {
                closesPoint = tempPoint;
            }

        }
        return closesPoint;

    }
}
