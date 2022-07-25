package model.interfaces;

import model.TwoPoint;

/**
 * This is Observer Pattern Interface, Rectangle, Triangle and Ellipse classes implement this pattern
 * for observing and updating the movement change
 */
public interface IMovementObserver {
    void update(TwoPoint twoPoint);
    void clear();
    void draw();
}