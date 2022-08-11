package model;

import model.interfaces.IMovement;
import model.interfaces.IShape;

import java.awt.*;
/**
 * It implement IShape so it has functions of drawing shape and erasing shape, also it implement IMovementObserver,
 * so this shape can keep updating its coordinate through movement change
 */
public class Ellipse implements IShape  {

    Shape shape;
    private Graphics2D g;

    public Ellipse(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        g = shape.getPaintCanvas().getGraphics2D();
        g.setColor(shape.getPrimaryColor());
        if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
            g.fillOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
        } else if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
            g.drawOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
        } else {
            g.fillOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
            g.setColor(shape.getSecondaryColor());
            g.drawOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
        }
    }

    @Override
    public void clear() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 9999, 9999);
    }

    @Override
    public void repaint(Graphics g) {
        g.setColor(shape.getPrimaryColor());
        if (shape.getShadingType() == ShapeShadingType.FILLED_IN) {
            g.fillOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
        } else if (shape.getShadingType() == ShapeShadingType.OUTLINE) {
            g.drawOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
        } else {
            g.fillOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
            g.setColor(shape.getSecondaryColor());
            g.drawOval(shape.getTwoPoint().getLeftCornerX(), shape.getTwoPoint().getLeftCornerY(),
                    shape.getTwoPoint().getWidth(), shape.getTwoPoint().getHeight());
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