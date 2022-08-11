package model.commands;

import model.Shape;
import model.ShapeFactory;
import model.ShapeList;
import model.commands.CommandHistory;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.util.Stack;

/**
 *     This class is to create shape through Shape factory, and it implement ICommand so it is able to undo/redo
 *     the shape.
 *     ShapeFactory instance is created in CreateShape.
 */
public class CreateShapeCommand implements IStrategy, ICommand {
    private Shape shape;
    private ShapeList shapeList;
    private Stack<IShape> myShapeList;
    private Stack<IShape> myUndoRedoList;
    public CreateShapeCommand(Shape shape, ShapeList shapeList) {
        this.shape = shape;
        this.shapeList = shapeList;
    }


    @Override
    public void run() {
        myShapeList = shapeList.getShapeList();
        myUndoRedoList = shapeList.getUndoRedoShapeList();
        ShapeFactory shapeFactory = new ShapeFactory();
        IShape newShape = shapeFactory.getShape(shape);
        shapeList.addShape(newShape);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if (myShapeList.isEmpty()) {
            return;
        }
        myShapeList.lastElement().clear();
        myUndoRedoList.add(myShapeList.pop());

    }

    @Override
    public void redo() {
        if (myUndoRedoList.isEmpty()) {
            return;
        }
        shapeList.addShape(myUndoRedoList.pop());
    }
}