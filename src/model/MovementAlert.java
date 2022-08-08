package model;

import model.interfaces.IMovement;
import model.interfaces.IShape;

import java.util.Stack;

/**
 * This is Observer Pattern
 * It used by SelectCommand to addObserver and used by MoveCommand to update new coordinate to all observers
 */
public class MovementAlert {

    private Stack<IShape> observers;

    public void addObserver(ShapeList shapeList, TwoPoint twoPoint) {
        CollisionDetection collisionDetection = new CollisionDetection(shapeList, twoPoint);
        collisionDetection.addSelectShape();
    }

    public void addMovement(TwoPoint twoPoint, ShapeList shapeList) {
        shapeList.getMovementList().add(twoPoint);
        observers = shapeList.getSelectList().lastElement();
        notifyAllObservers(twoPoint, shapeList);
    }

    public void undoMove(ShapeList shapeList) {
        if (shapeList.getMovementList().isEmpty()) {
            return;
        }
        TwoPoint twoPoint = shapeList.getMovementList().lastElement().switchPoint();
        shapeList.getUndoRedoMovementList().add(shapeList.getMovementList().pop());
        observers = shapeList.getSelectList().lastElement();
        notifyAllObservers(twoPoint, shapeList);
    }

    public void redoMove(ShapeList shapeList) {
        if (shapeList.getUndoRedoMovementList().isEmpty()) {
            return;
        }
        TwoPoint twoPoint = shapeList.getUndoRedoMovementList().lastElement();
        shapeList.getMovementList().add(shapeList.getUndoRedoMovementList().pop());
        observers = shapeList.getSelectList().lastElement();
        notifyAllObservers(twoPoint, shapeList);
    }

    private void notifyAllObservers(TwoPoint twoPoint, ShapeList shapeList) {
        shapeList.getShapeList().forEach(observer -> observer.clear());
        observers.forEach(observer -> observer.update(twoPoint));
        shapeList.getShapeList().forEach(observer -> observer.draw());
        for (IShape observer : shapeList.getSelectList().lastElement()) {
            OutlineDecorator outline = new OutlineDecorator();
            outline.draw(observer);
        }
    }

    public void updateCurrentObserver(ShapeList shapeList) {
        shapeList.getShapeList().forEach(observer -> observer.clear());
        shapeList.getShapeList().forEach(observer -> observer.draw());
        for (IShape observer : shapeList.getSelectList().lastElement()) {
            OutlineDecorator outline = new OutlineDecorator();
            outline.draw(observer);
        }
    }
}