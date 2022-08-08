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
         Stack<IShape> lastList = new Stack<>();
         for (IShape shape: shapeList.getShapeList()) {
             int Bx = shape.getShape().getTwoPoint().getLeftCornerX();
             int By = shape.getShape().getTwoPoint().getLeftCornerY();
             int Bw = shape.getShape().getTwoPoint().getWidth();
             int Bh = shape.getShape().getTwoPoint().getHeight();
        
            if (Bx + Bw > Ax && By + Bh > Ay && Ax + Aw > Bx && Ay + Ah > By) {
                lastList.add(shape);
            }
        }
        shapeList.getSelectList().add(lastList);
    }
}