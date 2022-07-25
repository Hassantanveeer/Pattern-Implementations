package model.commands;

import model.*;
import model.interfaces.ICommand;
import model.interfaces.IMovement;
import model.interfaces.IStrategy;

import java.util.Stack;

/**
 * The main purpose of SelectCommand is to add observer (selected shape(s) are all observer) for observer pattern
 */
public class SelectCommand implements IStrategy, ICommand {

    private TwoPoint twoPoint;
    private ShapeList shapeList;
    private Stack<Stack<IMovement>> mySelectList;
    private Stack<Stack<IMovement>> myUndoRedoList;

    public SelectCommand(TwoPoint twoPoint, ShapeList shapeList) {
        this.twoPoint = twoPoint;
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        mySelectList = shapeList.getSelectList();
        myUndoRedoList = shapeList.getUndoRedoSelectList();
        MovementAlert movementAlert = new MovementAlert();
        movementAlert.addObserver(shapeList, twoPoint);
        CommandHistory.add(this);
      
    }

    @Override
    public void undo() {
        if (mySelectList.isEmpty()) {
            return;
        }
        myUndoRedoList.add(mySelectList.pop());
       
    }

    @Override
    public void redo() {
        if (myUndoRedoList.isEmpty()) {
            return;
        }
        mySelectList.add(myUndoRedoList.pop());
        //System.out.println("There are " + mySelectList.lastElement().size() + " shape(s) selected");
    }
}