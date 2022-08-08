package model;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class Shape {

    private PaintCanvasBase paintCanvas;
    private TwoPoint twoPoint;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;

    public Shape(PaintCanvasBase paintCanvas, TwoPoint twoPoint, Color primaryColor,
                 Color secondaryColor, ShapeType shapeType, ShapeShadingType shadingType) {
        this.paintCanvas = paintCanvas;
        this.twoPoint = twoPoint;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shapeType = shapeType;
        this.shadingType = shadingType;
    }

    public PaintCanvasBase getPaintCanvas() {
        return paintCanvas;
    }

    public TwoPoint getTwoPoint() {
        return twoPoint;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public ShapeShadingType getShadingType() {
        return shadingType;
    }
}