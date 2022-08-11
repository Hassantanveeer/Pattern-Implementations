package model;
import model.interfaces.IShape;

import java.awt.*;
import java.util.Stack;

import java.awt.*;

public class OutlineDrawer {

    public void draw(IShape s) {
        Graphics2D g = s.getShape().getPaintCanvas().getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1,
                new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        if (s.getShape().getShapeType() == ShapeType.RECTANGLE) {
            g.drawRect(s.getShape().getTwoPoint().getMinXY().getX() - 5,
                    s.getShape().getTwoPoint().getMinXY().getY() - 5,
                    s.getShape().getTwoPoint().getWidth() + 10, s.getShape().getTwoPoint().getHeight() + 10);
        } else if (s.getShape().getShapeType() == ShapeType.ELLIPSE) {
            g.drawOval(s.getShape().getTwoPoint().getMinXY().getX() - 5,
                    s.getShape().getTwoPoint().getMinXY().getY() - 5,
                    s.getShape().getTwoPoint().getWidth() + 10, s.getShape().getTwoPoint().getHeight() + 10);
        } else if (s.getShape().getShapeType() == ShapeType.TRIANGLE) {

            Point newPoint = new Point(s.getShape().getTwoPoint().getStartPoint().getX(),
                    s.getShape().getTwoPoint().getEndPoint().getY());

            int startArray[] = new int[3];
            int endArray[] = new int[3];

            startArray[0] = s.getShape().getTwoPoint().getStartPoint().getX();
            startArray[1] = s.getShape().getTwoPoint().getEndPoint().getX();
            startArray[2] = (int) newPoint.getX();

            endArray[0] = s.getShape().getTwoPoint().getStartPoint().getY();
            endArray[1] = s.getShape().getTwoPoint().getEndPoint().getY();
            endArray[2] = (int) newPoint.getY();

            g.drawPolygon(startArray, endArray, 3);
        }
    }
    public void draw(Stack<IShape> groupList) {
        if (groupList.isEmpty()) return;
        if (groupList.lastElement().getShape().isGroup() == true) {
            drawGroupOutline(groupList);
        } else {
            drawEachOutline(groupList);
        }
    }

    private void drawEachOutline(Stack<IShape> groupList) {
        for (IShape observer : groupList) {
            drawOutline(observer);
        }
    }

    private void drawOutline(IShape s) {
        Graphics2D g = s.getShape().getPaintCanvas().getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1,
                new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        if (s.getShape().getShapeType() == ShapeType.RECTANGLE) {
            g.drawRect(s.getShape().getTwoPoint().getMinXY().getX() - 5,
                    s.getShape().getTwoPoint().getMinXY().getY() - 5,
                    s.getShape().getTwoPoint().getWidth() + 10, s.getShape().getTwoPoint().getHeight() + 10);
        } else if (s.getShape().getShapeType() == ShapeType.ELLIPSE) {
            g.drawOval(s.getShape().getTwoPoint().getMinXY().getX() - 5,
                    s.getShape().getTwoPoint().getMinXY().getY() - 5,
                    s.getShape().getTwoPoint().getWidth() + 10, s.getShape().getTwoPoint().getHeight() + 10);
        } else if (s.getShape().getShapeType() == ShapeType.TRIANGLE) {

            Point newPoint = new Point(s.getShape().getTwoPoint().getStartPoint().getX(),
                    s.getShape().getTwoPoint().getEndPoint().getY());

            int startArray[] = new int[3];
            int endArray[] = new int[3];

            startArray[0] = s.getShape().getTwoPoint().getStartPoint().getX();
            startArray[1] = s.getShape().getTwoPoint().getEndPoint().getX();
            startArray[2] = (int) newPoint.getX();

            endArray[0] = s.getShape().getTwoPoint().getStartPoint().getY();
            endArray[1] = s.getShape().getTwoPoint().getEndPoint().getY();
            endArray[2] = (int) newPoint.getY();

            g.drawPolygon(startArray, endArray, 3);
        }
    }

    private void drawGroupOutline(Stack<IShape> groupList) {
        Graphics2D g = groupList.get(0).getShape().getPaintCanvas().getGraphics2D();
        int groupWidth = getMaxXY(groupList).getX() - getMinXY(groupList).getX();
        int groupHeight = getMaxXY(groupList).getY() - getMinXY(groupList).getY();

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawRect(getMinXY(groupList).getX() - 5, getMinXY(groupList).getY() - 5, groupWidth + 10,groupHeight + 10);
    }

    private Point getMinXY(Stack<IShape> groupList) {
        int startX = 9999;
        int startY = 9999;
        for(IShape shape: groupList){
            if(shape.getShape().getTwoPoint().getMinXY().getX() < startX) {
                startX = shape.getShape().getTwoPoint().getMinXY().getX();
            }
            if(shape.getShape().getTwoPoint().getMinXY().getY() < startY) {
                startY = shape.getShape().getTwoPoint().getMinXY().getY();
            }
        }
        Point minXY = new Point(startX, startY);
        return minXY;
    }

    private Point getMaxXY(Stack<IShape> groupList) {
        int shapeEndX = 0;
        int shapeEndY = 0;
        for(IShape shape: groupList){
            if(shape.getShape().getTwoPoint().getMaxXY().getX() > shapeEndX){
                shapeEndX = shape.getShape().getTwoPoint().getMaxXY().getX();
            }
            if(shape.getShape().getTwoPoint().getMaxXY().getY() > shapeEndY){
                shapeEndY = shape.getShape().getTwoPoint().getMaxXY().getY();
            }
        }
        Point maxXY = new Point(shapeEndX,shapeEndY);
        return maxXY;
    }
}