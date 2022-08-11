package model.commands;

import java.util.Stack;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;

public class CopyShapeCommand implements ICommand{

    ShapeList shapeList;
    Stack<IShape> myClipboard;

    public CopyShapeCommand(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public void run() {
        if (shapeList.getSelectList().isEmpty()) {
            return;
        }
        myClipboard = shapeList.getClipboard();
        for (IShape shape : shapeList.getSelectList().lastElement()) {
            myClipboard.add(shape);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        myClipboard.clear();
    }

    @Override
    public void redo() {
        for (IShape shape : shapeList.getSelectList().lastElement()) {
            myClipboard.add(shape);
        }
    }
}
