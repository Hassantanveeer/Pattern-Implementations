package model;


import model.commands.CommandHistory;

import model.interfaces.IShape;
import model.persistence.ICommand;
import view.interfaces.PaintCanvasBase;

import java.util.Stack;

public class CreateShape implements ICommand {

    private PaintCanvasBase paintCanvas;
    private Shape shape;
    private ShapeList shapeList;

    public CreateShape(PaintCanvasBase paintCanvas, Shape shape, ShapeList shapeList) {
        this.paintCanvas = paintCanvas;
        this.shape = shape;
        this.shapeList = shapeList;
    }

    @Override
    public void draw() {
        ShapeFactory shapeFactory = new ShapeFactory();
        IShape newShape = shapeFactory.getShape(shape);
        shapeList.addShape(newShape);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        Stack<IShape> myShapeList = shapeList.getShapeList();
        Stack<IShape> myUndoRedoShapeList = shapeList.getUndoRedoShapeList();
        if (myShapeList.isEmpty()) {
            return;
        }
        myShapeList.lastElement().clear(paintCanvas.getGraphics2D());
        myUndoRedoShapeList.add(myShapeList.pop());
    }

    @Override
    public void redo() {
        Stack<IShape> myUndoRedoShapeList = shapeList.getUndoRedoShapeList();
        if (myUndoRedoShapeList.isEmpty()) {
            return;
        }
        shapeList.addShape(myUndoRedoShapeList.pop());
    }

	
}