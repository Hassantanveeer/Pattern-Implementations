package model;


import java.util.Stack;
import model.interfaces.IShape;

public class GroupShape {

    ShapeList shapeList;
    private Stack<Stack<IShape>> mySelectList;
    private Stack<Stack<IShape>> myGroupList;
    private Stack<Stack<IShape>> myUndoRedoList;
    private OutlineDecorator outlineDecorator;

    public GroupShape(ShapeList shapeList) {
        this.shapeList = shapeList;
        mySelectList = shapeList.getSelectList();
        myGroupList = shapeList.getGroupList();
        myUndoRedoList = shapeList.getUndoRedoGroupList();
        outlineDecorator = new OutlineDecorator();
    }

    public void makeGroup() {
        if (mySelectList.isEmpty() || shapeList.getSelectList().lastElement().lastElement().getShape().isGroup()) return;
        shapeList.getSelectList().lastElement().forEach(observer -> observer.getShape().groupSwitcher());
        addToGroup();
    }

    private void addToGroup() {
        Stack<IShape> newGroup = mySelectList.lastElement();
        shapeList.redraw();
        outlineDecorator.draw(newGroup);
        myGroupList.add(newGroup);
    }

    public void undoGroup() {
        if (myGroupList.isEmpty()) return;
        shapeList.getGroupList().lastElement().forEach(observer -> observer.getShape().groupSwitcher());
        shapeList.redraw();
        outlineDecorator.draw(mySelectList.lastElement());
        myUndoRedoList.add(myGroupList.pop());
    }

    public void redoGroup() {
        if (myUndoRedoList.isEmpty()) return;
        Stack<IShape> lastGroup = myUndoRedoList.pop();
        lastGroup.forEach(observer -> observer.getShape().groupSwitcher());
        shapeList.redraw();
        outlineDecorator.draw(lastGroup);
        myGroupList.add(lastGroup);
    }

    public void removeFromGroup() {
        if (mySelectList.isEmpty() || mySelectList.lastElement().lastElement().getShape().isGroup() == false) return;
        IShape lastShape = mySelectList.lastElement().lastElement();
        removeList(lastShape);
        shapeList.redraw();
        outlineDecorator.draw(mySelectList.lastElement());
    }

    public void undoRemove() {
        redoGroup();
    }

    public void redoRemove() {
        undoGroup();
    }

    private void removeList(IShape shape) {
        for (Stack<IShape> list : shapeList.getGroupList()) {
            if (list.contains(shape)) {
                myUndoRedoList.add(list);
                list.forEach(observer -> observer.getShape().groupSwitcher());
                myGroupList.remove(list);
                return;
            }
        }
    }
}