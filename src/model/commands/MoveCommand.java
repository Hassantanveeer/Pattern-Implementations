package model.commands;

import model.Point;
import model.MovementAlert;
import model.ShapeList;
import model.TwoPoint;
import model.interfaces.ICommand;
import model.interfaces.IMovement;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.util.Stack;

/**
 * The main purpose of MoveCommand is to update new coordinate to all observers
 */
public class MoveCommand implements IStrategy, ICommand {

    private TwoPoint twoPoint;
    private ShapeList shapeList;

    MovementAlert movementAlert;
    public MoveCommand(TwoPoint twoPoint, ShapeList shapeList) {
        this.twoPoint = twoPoint;
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        movementAlert = new MovementAlert();
        movementAlert.addMovement(twoPoint, shapeList);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        if (shapeList.getMovementList().isEmpty()) {
            return;
        }
        movementAlert.undoMove(shapeList);
    }

    @Override
    public void redo() {
        if (shapeList.getUndoRedoMovementList().isEmpty()) {
            return;
        }
        movementAlert.redoMove(shapeList);
    }
}