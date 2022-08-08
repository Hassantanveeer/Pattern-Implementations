package model;


import model.interfaces.IShape;

import java.util.Stack;

public class PasteShape {

    ShapeList shapeList;

    public PasteShape(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public void execute() {
        Stack<IShape> MyClipboard = shapeList.getClipboard();
        Stack<Integer> myPasteList = shapeList.getPasteList();
        int totalObservers = MyClipboard.size();
        System.out.println(totalObservers);
        myPasteList.add(totalObservers);
        createNewShape();
    }

    public void createNewShape() {
        for (IShape observer : shapeList.getClipboard()) {
            int x1 = observer.getShape().getTwoPoint().getStartPoint().getX() - 100;
            int y1 = observer.getShape().getTwoPoint().getStartPoint().getY() - 100;
            int x2 = observer.getShape().getTwoPoint().getEndPoint().getX() - 100;
            int y2 = observer.getShape().getTwoPoint().getEndPoint().getY() - 100;
            Shape shape = new ShapeBuilder()
                    .setPaintCanvas(observer.getShape().getPaintCanvas())
                    .setTwoPoint(new TwoPoint(new Point(x1, y1), new Point(x2, y2)))
                    .setPrimaryColor(observer.getShape().getPrimaryColor())
                    .setSecondaryColor(observer.getShape().getSecondaryColor())
                    .setShapeType(observer.getShape().getShapeType())
                    .setShadingType(observer.getShape().getShadingType())
                    .build();
            ShapeFactory shapeFactory = new ShapeFactory();
            IShape newShape = shapeFactory.getShape(shape);
            shapeList.addShape(newShape);
        }
    }

    public void undoPaste() {
        Stack<Integer> myPasteList = shapeList.getPasteList();
        Stack<Stack<IShape>> myUndoRedoPasteItem = shapeList.getUndoRedoPasteItem();
        if (myPasteList.isEmpty()) {
            return;
        }
        int num = myPasteList.pop();
        Stack<IShape> newPasteItems = new Stack<>();
        while (num > 0) {
            IShape currentShape = shapeList.getShapeList().pop();
            currentShape.clear();
            newPasteItems.add(currentShape);
            num--;
        }
        myUndoRedoPasteItem.add(newPasteItems);
    }

    public void redoPaste() {
        Stack<Integer> myPasteList = shapeList.getPasteList();
        Stack<Stack<IShape>> myUndoRedoPasteItem = shapeList.getUndoRedoPasteItem();
        if (myUndoRedoPasteItem.isEmpty()) {
            return;
        }
        myPasteList.add(myUndoRedoPasteItem.lastElement().size());
        for (IShape observer : myUndoRedoPasteItem.lastElement()) {
            shapeList.getShapeList().add(observer);
        }
    }
}