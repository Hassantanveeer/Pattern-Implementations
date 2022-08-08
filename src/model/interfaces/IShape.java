package model.interfaces;

import java.awt.Graphics;

import model.Shape;
import model.TwoPoint;


public interface IShape {
    void draw();

    void clear();

    void update(TwoPoint twoPoint);

    void repaint(Graphics g);

    Shape getShape();
}