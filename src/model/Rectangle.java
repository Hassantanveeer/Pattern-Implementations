package model;

import model.interfaces.IShape;

import java.awt.*;

public class Rectangle implements IShape {

    /*
    It implement IShape so it has functions of drawing shape and erasing shape
     */

    Shape shape;
    private int startPointX;
    private int startPointY;
    // maybe need it later
    private int width;
    private int height;

    public Rectangle(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        startPointX = (int) Math.min(shape.getStartPoint().getX(), shape.getEndPoint().getX());
        startPointY = (int) Math.min(shape.getStartPoint().getY(), shape.getEndPoint().getY());
        width = (int) Math.abs(shape.getStartPoint().getX() - shape.getEndPoint().getX());
        height = (int) Math.abs(shape.getStartPoint().getY() - shape.getEndPoint().getY());
        g.setColor(shape.getPrimaryColor());
        g.fillRect(startPointX, startPointY, width, height);
    }

    @Override
    public void clear(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(startPointX, startPointY, width, height);
    }
}