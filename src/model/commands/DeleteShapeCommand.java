package model.commands;
import java.util.Stack;

import model.DeleteShape;
import model.MovementAlert;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;
public class DeleteShapeCommand implements ICommand {

    ShapeList shapeList;
    MovementAlert movementAlert;
    Stack<Stack<IShape>> myUndoRedoDeleteList;
    DeleteShape deleteShape;

    public DeleteShapeCommand(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public void run() {
        movementAlert = new MovementAlert(shapeList);
        myUndoRedoDeleteList = shapeList.getUndoRedoDeleteList();
        deleteShape = new DeleteShape(shapeList);
        deleteShape.delete();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        deleteShape.deleteShapeUndo();
        movementAlert.updateCurrentObserver();
    }

    @Override
    public void redo() {
        deleteShape.delete();
    }
}