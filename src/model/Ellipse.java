package model;

import model.interfaces.IShape;

import java.awt.*;

public class Ellipse implements IShape {
    Shape shape;

    public Ellipse(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void clear(Graphics2D g) {

    }
}