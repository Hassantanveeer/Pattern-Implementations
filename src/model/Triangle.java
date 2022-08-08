package model;

import model.interfaces.IShape;

import java.awt.*;

public class Triangle implements IShape {

    Shape shape;
    private Graphics2D g;
    private Point thirdPoint;
    private int[] xPoints;
    private int[] yPoints;

    public Triangle(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        g = shape.getPaintCanvas().getGraphics2D();
        thirdPoint = new Point(shape.getTwoPoint().getStartPoint().getX(),
                shape.getTwoPoint().getEndPoint().getY());
        xPoints = new int[]{shape.getTwoPoint().getStartPoint().getX(),
                shape.getTwoPoint().getEndPoint().getX(), thirdPoint.getX()};
        yPoints = new int[]{shape.getTwoPoint().getStartPoint().getY(),
                shape.getTwoPoint().getEndPoint().getY(), thirdPoint.getY()};
        g.setColor(shape.getPrimaryColor());
        if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
            g.drawPolygon(xPoints, yPoints, 3);
        } else {
            g.fillPolygon(xPoints, yPoints, 3);
            g.setColor(shape.getSecondaryColor());
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public void clear() {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1,
                new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.WHITE);
        g.fillPolygon(xPoints, yPoints, 3);
        g.drawPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void repaint(Graphics g) {
        thirdPoint = new Point(shape.getTwoPoint().getStartPoint().getX(),
                shape.getTwoPoint().getEndPoint().getY());
        xPoints = new int[]{shape.getTwoPoint().getStartPoint().getX(),
                shape.getTwoPoint().getEndPoint().getX(), thirdPoint.getX()};
        yPoints = new int[]{shape.getTwoPoint().getStartPoint().getY(),
                shape.getTwoPoint().getEndPoint().getY(), thirdPoint.getY()};
        g.setColor(shape.getPrimaryColor());
        if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
            g.drawPolygon(xPoints, yPoints, 3);
        } else {
            g.fillPolygon(xPoints, yPoints, 3);
            g.setColor(shape.getSecondaryColor());
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(TwoPoint twoPoint) {
        shape.getTwoPoint().getStartPoint().setX(
                shape.getTwoPoint().getStartPoint().getX() - twoPoint.getStartPoint().getX()
                        + twoPoint.getEndPoint().getX());
        shape.getTwoPoint().getStartPoint().setY(
                shape.getTwoPoint().getStartPoint().getY() - twoPoint.getStartPoint().getY()
                        + twoPoint.getEndPoint().getY());
        shape.getTwoPoint().getEndPoint().setX(
                shape.getTwoPoint().getEndPoint().getX() - twoPoint.getStartPoint().getX()
                        + twoPoint.getEndPoint().getX());
        shape.getTwoPoint().getEndPoint().setY(
                shape.getTwoPoint().getEndPoint().getY() - twoPoint.getStartPoint().getY()
                        + twoPoint.getEndPoint().getY());
    }
}