package controller;

import model.*;
import model.Point;
import model.Shape;
import model.commands.CreateShapeCommand;
import model.commands.MoveShapeCommand;
import model.commands.SelectShapeCommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private final ShapeList shapeList;

    public MouseListener(PaintCanvasBase baseCanvas, ApplicationState baseState, ShapeList shapeList) {
        paintCanvas = baseCanvas;
        appState = baseState;
        this.shapeList = shapeList;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        startPoint = new Point(e.getX(), e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ModeController controller = new ModeController();
        Point endPoint = new Point(e.getX(), e.getY());
        TwoPoint twoPoint = new TwoPoint(startPoint, endPoint);
        Shape newShape = new ShapeBuilder()
                .setPaintCanvas(paintCanvas)
                .setTwoPoint(twoPoint)
                .setPrimaryColor(appState.getActivePrimaryColor().getColor())
                .setSecondaryColor(appState.getActiveSecondaryColor().getColor())
                .setShapeType(appState.getActiveShapeType())
                .setShadingType(appState.getActiveShapeShadingType()).build();
        if(appState.getActiveMouseMode() == MouseMode.DRAW) {
            controller.setMouseMode(new CreateShapeCommand(newShape, shapeList));
            controller.execute();
        }else if(appState.getActiveMouseMode() == MouseMode.SELECT) {
            controller.setMouseMode(new SelectShapeCommand(twoPoint, shapeList));
            controller.execute();
        } else {
            controller.setMouseMode(new MoveShapeCommand(twoPoint, shapeList));
            controller.execute();
        }
    }
}
