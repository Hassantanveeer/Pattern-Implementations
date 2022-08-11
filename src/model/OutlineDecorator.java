package model;

import java.util.Stack;

import model.interfaces.IShape;

public class OutlineDecorator {

    private OutlineDrawer outlineDrawer;

    void draw(IShape shape) {
        outlineDrawer = new OutlineDrawer();
        outlineDrawer.draw(shape);
    }
    public void draw(Stack<IShape> groupList) {
        outlineDrawer = new OutlineDrawer();
        outlineDrawer.draw(groupList);
    }

}
