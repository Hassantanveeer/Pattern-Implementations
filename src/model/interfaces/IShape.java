package model.interfaces;

/**
 * This is Abstract Factory Pattern
 * Sprint 1: draw() and clear() method added, Rectangle, Triangle and Ellipse classes implement this pattern
 * Sprint 2: in order to detect shape collision, I implement getter method to get basic coordinate information from each shape
 */
public interface IShape {
    void draw();
    void clear();
    int getLeftCornerX();
    int getLeftCornerY();
    int getWidth();
    int getHeight();
}