package model;
import java.awt.*;

public class Shape {

    private Point startPoint;
    private Point endPoint;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;

    private Shape(ShapeBuilder shapeBuilder) {
        this.startPoint = shapeBuilder.startPoint;
        this.endPoint = shapeBuilder.endPoint;
        this.primaryColor = shapeBuilder.primaryColor;
        this.secondaryColor = shapeBuilder.secondaryColor;
        this.shapeType = shapeBuilder.shapeType;
        this.shadingType = shapeBuilder.shadingType;
    }

    public static class ShapeBuilder {
        private Point startPoint;
        private Point endPoint;
        private Color primaryColor;
        private Color secondaryColor;
        private ShapeType shapeType;
        private ShapeShadingType shadingType;

        public ShapeBuilder setStartPoint(Point startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public ShapeBuilder setEndPoint(Point endPoint) {
            this.endPoint = endPoint;
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

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
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