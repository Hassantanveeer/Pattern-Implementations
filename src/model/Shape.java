package model;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 *     This is builder pattern for shape builder, and this is a class for storing parameters so other class are
 *     able to access it through getter.
 */
public class Shape {

    private PaintCanvasBase paintCanvas;
    private TwoPoint twoPoint;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;

    private Shape(ShapeBuilder shapeBuilder) {
        this.paintCanvas = shapeBuilder.paintCanvas;
        this.twoPoint = shapeBuilder.twoPoint;
        this.primaryColor = shapeBuilder.primaryColor;
        this.secondaryColor = shapeBuilder.secondaryColor;
        this.shapeType = shapeBuilder.shapeType;
        this.shadingType = shapeBuilder.shadingType;
    }

    public static class ShapeBuilder {
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
            return new Shape(this);
        }
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