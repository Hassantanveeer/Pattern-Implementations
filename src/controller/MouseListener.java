package controller;

import model.*;
import model.Shape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import model.CreateShape;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

public class MouseListener extends MouseAdapter {
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private final ShapeList shapeList;

    private Color primaryColor;
    private Color secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;
    public MouseListener(PaintCanvasBase baseCanvas, ApplicationState baseState, ShapeList shapeList) {
        paintCanvas = baseCanvas;
        appState = baseState;
        this.shapeList = shapeList;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        startPoint = new Point(e.getX(), e.getY());
        shapeType = appState.getActiveShapeType();
        shadingType = appState.getActiveShapeShadingType();
        // color
        ShapeColor getPrimaryColor = appState.getActivePrimaryColor();
        primaryColor = getPrimaryColor.getColor();
        ShapeColor getSecondaryColor = appState.getActiveSecondaryColor();
        secondaryColor = getSecondaryColor.getColor();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        Point endPoint = new Point(e.getX(), e.getY());
        Shape newShape = new Shape.ShapeBuilder()
                .setStartPoint(startPoint)
                .setEndPoint(endPoint)
                .setPrimaryColor(primaryColor)
                .setSecondaryColor(secondaryColor)
                .setShapeType(shapeType)
                .setShadingType(shadingType).build();
        // System.out.println("end point at " + endPoint.getX() + ", " + endPoint.getY());
        if(appState.getActiveMouseMode() == MouseMode.DRAW) {
            CreateShape createShape = new CreateShape(paintCanvas, newShape, shapeList);
            createShape.draw();
        }
    }
}
