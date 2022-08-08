package model;

/**
 * This class is to store two start point and end point coordinate and calculate their left corner coordinate width and height
 */
public class TwoPoint {
    private Point startPoint;
    private Point endPoint;
    private int leftCornerX;
    private int leftCornerY;
    private int width;
    private int height;

    public TwoPoint(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    public Point getMinXY() {
        int mouseStartX = Math.min(this.startPoint.getX(), this.endPoint.getX());
        int mouseStartY = Math.min(this.startPoint.getY(), this.endPoint.getY());

        Point point = new Point(mouseStartX, mouseStartY);
        return point;
    }

    public Point getMaxXY() {
        int mouseEndX = Math.max(this.startPoint.getX(), this.endPoint.getX());
        int mouseEndY = Math.max(this.startPoint.getY(), this.endPoint.getY());

        Point point = new Point(mouseEndX, mouseEndY);
        return point;
    }
    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public int getLeftCornerX() {
        leftCornerX = Math.min(startPoint.getX(), endPoint.getX());
        return leftCornerX;
    }

    public int getLeftCornerY() {
        leftCornerY = Math.min(startPoint.getY(), endPoint.getY());
        return leftCornerY;
    }

    public int getWidth() {
        width = Math.abs(startPoint.getX() - endPoint.getX());
        return width;
    }

    public int getHeight() {
        height = Math.abs(startPoint.getY() - endPoint.getY());
        return height;
    }

    public TwoPoint switchPoint() {
        return new TwoPoint(endPoint, startPoint);
    }
}