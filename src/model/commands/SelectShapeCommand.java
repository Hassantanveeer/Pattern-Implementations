package model.commands;

import model.*;
import model.interfaces.ICommand;
import model.interfaces.IMovement;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.util.Stack;

/**
 * The main purpose of SelectCommand is to add observer (selected shape(s) are all observer) for observer pattern
 */
public class SelectShapeCommand implements IStrategy, ICommand {

    private TwoPoint twoPoint;
    private ShapeList shapeList;
    MovementAlert movementAlert;
    private Stack<Stack<IShape>> mySelectList;
    private Stack<Stack<IShape>> myUndoRedoList;

    public SelectShapeCommand(TwoPoint twoPoint, ShapeList shapeList) {
        this.twoPoint = twoPoint;
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        mySelectList = shapeList.getSelectList();
        myUndoRedoList = shapeList.getUndoRedoSelectList();
        movementAlert = new MovementAlert(shapeList);
        movementAlert.addObserver(twoPoint);
        movementAlert.updateCurrentObserver();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if (mySelectList.isEmpty()) {
            return;
        }
        myUndoRedoList.add(mySelectList.pop());
        movementAlert.updateCurrentObserver();
    }

    @Override
    public void redo() {
        if (myUndoRedoList.isEmpty()) {
            return;
        }
        mySelectList.add(myUndoRedoList.pop());
        movementAlert.updateCurrentObserver();
    }
}