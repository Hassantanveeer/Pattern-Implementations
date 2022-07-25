package model;

import model.interfaces.IMovement;
import model.interfaces.IShape;

import java.awt.*;
/**
 * It implement IShape so it has functions of drawing shape and erasing shape, also it implement IMovementObserver,
 * so this shape can keep updating its coordinate through movement change
 */
public class Triangle implements IShape, IMovement {
    Shape shape;
    private Graphics2D g;
    private Point thirdPoint;
    private int[] xPoints;
    private int[] yPoints;
    private int leftCornerX;
    private int leftCornerY;
    private int width;
    private int height;
    public Triangle(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        g = shape.getPaintCanvas().getGraphics2D();
        leftCornerX = shape.getTwoPoint().getLeftCornerX();
        leftCornerY = shape.getTwoPoint().getLeftCornerY();
        width = shape.getTwoPoint().getWidth();
        height = shape.getTwoPoint().getHeight();
        thirdPoint = new Point(shape.getTwoPoint().getStartPoint().getX(), shape.getTwoPoint().getEndPoint().getY());
        xPoints = new int[] {shape.getTwoPoint().getStartPoint().getX(), shape.getTwoPoint().getEndPoint().getX(), thirdPoint.getX()};
        yPoints = new int[] {shape.getTwoPoint().getStartPoint().getY(), shape.getTwoPoint().getEndPoint().getY(), thirdPoint.getY()};
        g.setColor(shape.getPrimaryColor());
        if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
            g.fillPolygon(xPoints, yPoints,3);
        } else if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
            g.drawPolygon(xPoints, yPoints,3);
        } else {
            g.fillPolygon(xPoints, yPoints,3);
            g.setColor(shape.getSecondaryColor());
            g.drawPolygon(xPoints, yPoints,3);
        }
    }

    @Override
    public void clear() {
        g.setColor(Color.WHITE);
        g.fillPolygon(xPoints, yPoints,3);
        g.drawPolygon(xPoints, yPoints,3);
    }

    public int getLeftCornerX() {
        return leftCornerX;
    }

    public int getLeftCornerY() {
        return leftCornerY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void update(TwoPoint twoPoint) {
        shape.getTwoPoint().getStartPoint().setX(shape.getTwoPoint().getStartPoint().getX() - twoPoint.getStartPoint().getX() + twoPoint.getEndPoint().getX());
        shape.getTwoPoint().getStartPoint().setY(shape.getTwoPoint().getStartPoint().getY() - twoPoint.getStartPoint().getY() + twoPoint.getEndPoint().getY());
        shape.getTwoPoint().getEndPoint().setX(shape.getTwoPoint().getEndPoint().getX() - twoPoint.getStartPoint().getX() + twoPoint.getEndPoint().getX());
        shape.getTwoPoint().getEndPoint().setY(shape.getTwoPoint().getEndPoint().getY() - twoPoint.getStartPoint().getY() + twoPoint.getEndPoint().getY());
    }
}