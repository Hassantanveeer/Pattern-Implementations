package model;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeBuilder {

    private PaintCanvasBase paintCanvas;
    private TwoPoint twoPoint;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;

    public ShapeBuilder setPaintCanvas(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        return this;
    }

    public ShapeBuilder setTwoPoint(TwoPoint twoPoint) {
        this.twoPoint = twoPoint;
        return this;
    }

    public ShapeBuilder setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public ShapeBuilder setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    public ShapeBuilder setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public ShapeBuilder setShadingType(ShapeShadingType shadingType) {
        this.shadingType = shadingType;
        return this;
    }

    public Shape build() {
        return new Shape(paintCanvas, twoPoint, primaryColor, secondaryColor, shapeType, shadingType);
    }
}
