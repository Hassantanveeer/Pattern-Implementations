package model;

import model.interfaces.IShape;

public class DeleteShape {

    ShapeList shapeList;

    public DeleteShape(ShapeList shapeList) {
        this.shapeList = shapeList; 
    }

    public void delete() {
        if (shapeList.getSelectList().isEmpty()) {
            return;
        }
        for (IShape observer : shapeList.getSelectList().lastElement()) {
            IShape removedObserver = observer;
            removedObserver.clear();
            shapeList.getShapeList().remove(removedObserver);
        }
        shapeList.getUndoRedoDeleteList().add(shapeList.getSelectList().pop());
        shapeList.getShapeList().forEach(shape -> shape.draw());
    }

    public void deleteShapeUndo() {
        if (shapeList.getUndoRedoDeleteList().isEmpty()) {
            return;
        }
        for (IShape observer : shapeList.getUndoRedoDeleteList().lastElement()) {
            shapeList.getShapeList().add(observer);
        }
        shapeList.getSelectList().add(shapeList.getUndoRedoDeleteList().pop());
    }

}
