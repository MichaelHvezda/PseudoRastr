package snake;

import transforms.Point2D;

import java.awt.*;

public class BodBarva {

    private Point2D point2D;
    private Color color;

    public BodBarva(Point2D point2D, Color color) {
        this.point2D = point2D;
        this.color = color;
    }

    public Point2D getPoint2D() {

        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
