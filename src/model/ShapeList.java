package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.Stack;

public class ShapeList{
    private PaintCanvasBase paintCanvas;
    private Stack<IShape> shapeList = new Stack<>();
    private Stack<IShape> undoRedoShapeList = new Stack<>();

    public ShapeList(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    public void addShape(IShape iShape) {
        shapeList.add(iShape);
        iShape.draw(paintCanvas.getGraphics2D());
    }

    public Stack<IShape> getShapeList() {
        return shapeList;
    }

    public Stack<IShape> getUndoRedoShapeList() {
        return undoRedoShapeList;
    }
}