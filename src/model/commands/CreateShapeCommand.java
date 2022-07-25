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

    /*
    Once we start run this class, it will push the new shape into ShapeList
    Same time, we push our command to CommandHistory
     */
    @Override
    public void run() {
        myShapeList = shapeList.getShapeList();
        myUndoRedoList = shapeList.getUndoRedoShapeList();
        ShapeFactory shapeFactory = new ShapeFactory();
        IShape newShape = shapeFactory.getShape(shape);
        shapeList.addShape(newShape);
        CommandHistory.add(this);
    }

    /*
    Undo method just pop last shape from shapeList and add it to undoRedoList.
    Before we do that, we clear the shape on canvas.
     */
    @Override
    public void undo() {
        if (myShapeList.isEmpty()) {
            return;
        }
        myShapeList.lastElement().clear();
        myUndoRedoList.add(myShapeList.pop());
        // System.out.println("create: undo");
    }

    /*
    Redo is similar as undo, we pop shape from undoRedoList and add it to shapeList, after that redraw this shape on canvas.
     */
    @Override
    public void redo() {
        if (myUndoRedoList.isEmpty()) {
            return;
        }
        shapeList.addShape(myUndoRedoList.pop());
        // System.out.println("create: redo");
    }
}