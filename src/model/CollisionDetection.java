package model;

import model.interfaces.IMovement;
import model.interfaces.IShape;

import java.util.Stack;


public class CollisionDetection { 

    ShapeList shapeList;
    TwoPoint twoPoint;

    public CollisionDetection(ShapeList shapeList, TwoPoint twoPoint) {
        this.shapeList = shapeList;
        this.twoPoint = twoPoint;
    }

    public void addSelectShape() {
        int Ax = twoPoint.getLeftCornerX();
        int Ay = twoPoint.getLeftCornerY();
        int Aw = twoPoint.getWidth();
        int Ah = twoPoint.getHeight();
        Stack<IMovement> lastList = new Stack<>();
        for (IShape shape: shapeList.getShapeList()) {
            int Bx = shape.getLeftCornerX();
            int By = shape.getLeftCornerY();
            int Bw = shape.getWidth();
            int Bh = shape.getHeight();
        
            if (Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By) {
                lastList.add((IMovement) shape);
            }
        }
        shapeList.getSelectList().add(lastList);
    }
}