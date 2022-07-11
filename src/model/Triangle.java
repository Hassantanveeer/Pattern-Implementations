package model;

import model.interfaces.IShape;

import java.awt.*;

public class Triangle implements IShape {

    /*
    It implement IShape so it has functions of drawing shape and erasing shape
    */

    Shape shape;

    public Triangle(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void clear(Graphics2D g) {

    }
}